/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.util.ArrayList;
import logiikka.nappulat.Kunkku;
import static logiikka.nappulat.Maa.MUSTA;
import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.nappulat.Nappula;
import logiikka.nappulat.Torni;

/**
 *
 * @author elias
 */
public class Peli {

    private Pelilauta lauta;
    private Pelaaja musta;
    private Pelaaja valkoinen;
    private Pelaaja vuorossa;
    private Nappula aktiivinen;

    public Peli() {
        this.valkoinen = new Pelaaja(VALKOINEN);
        this.musta = new Pelaaja(MUSTA);
        this.uusiPeli();
    }

    public Pelaaja getMusta() {
        return musta;
    }

    public Pelaaja getValkoinen() {
        return valkoinen;
    }
    

    private void alkuAsetelma() {
        //valkoiset:
        new Torni(VALKOINEN, 0, 0, this.lauta);
        new Torni(VALKOINEN, 7, 0, this.lauta);
        this.valkoinen.setKunkku(new Kunkku(VALKOINEN, 3, 0, this.lauta));

        //mustat:
        this.musta.setKunkku(new Kunkku(MUSTA, 3, 7, this.lauta));
        new Torni(MUSTA, 7, 7, this.lauta);
        new Torni(MUSTA, 0, 7, this.lauta);
    }

    public void paivitaPelaajienNappulat() {

        this.musta.getNappulat().clear();
        this.valkoinen.getNappulat().clear();
        for (int y = 0; y < this.lauta.getSize(); y++) {
            for (int x = 0; x < this.lauta.getSize(); x++) {
                if (this.lauta.haeNappula(x, y) != null) {
                    if (this.lauta.haeNappula(x, y).getMaa() == MUSTA) {
                        this.musta.getNappulat().add(this.lauta.haeNappula(x, y));
                    } else {
                        this.valkoinen.getNappulat().add(this.lauta.haeNappula(x, y));
                    }
                }
            }
        }
    }

    public boolean onkoUhattuna(int x, int y) {
        if (this.uhkaavaNappula(x, y) == null) {
            return false;
        }
        return true;
    }

    public Nappula uhkaavaNappula(int x, int y) {
        if (this.vuorossa.getMaa() == MUSTA) {
            return this.haeUhkaava(x, y, this.valkoinen.getNappulat());
        } else {
            return this.haeUhkaava(x, y, this.musta.getNappulat());
        }
    }

    public Nappula haeUhkaava(int x, int y, ArrayList<Nappula> vastustajanNappulat) {
        for (Nappula nappula : vastustajanNappulat) {
            if (nappula.tarkistaReitti(x, y)) {
                return nappula;
            }
        }
        return null;

    }

    public boolean uhkaakoUseampi(int x, int y) {
        if (this.vuorossa.getMaa() == MUSTA) {
            return this.uhkaakoUseampi(x, y, this.valkoinen.getNappulat());

        } else {

            return this.uhkaakoUseampi(x, y, this.musta.getNappulat());

        }
    }

    public boolean uhkaakoUseampi(int x, int y, ArrayList<Nappula> vastustajanNappulat) {
        int uhkaajat = 0;
        for (Nappula nappula : vastustajanNappulat) {
            if (nappula.tarkistaReitti(x, y)) {
                uhkaajat++;
            }
        }
        if (uhkaajat > 1) {
            return true;
        }
        return false;
    }

    public boolean kunkkuEiVoiLiikkua() {  //MITEN TARKISTETAAN VOIKO MENNÄ VÄLIIN/SYÖDÄ? EHKÄ REITTIÄ KUKA UHKAA-mitkä ruudut välillä, voiko joku oma nappi mennä väliin?
        for (int y = Math.max(0, this.vuorossa.getKunkku().getY() - 1); y <= Math.min(lauta.getSize() - 1, this.vuorossa.getKunkku().getY() + 1); y++) {
            for (int x = Math.max(0, this.vuorossa.getKunkku().getX() - 1); x <= Math.min(lauta.getSize() - 1, this.vuorossa.getKunkku().getX() + 1); x++) {
                if (this.vuorossa.getKunkku().onSallittuSiirto(x, y)) { //jos kunkku voi liikkua tarkistetaan onko kunkku uhattuna liikkuimsen jälkeen (ettei pääse "itsensä taakse suojaan"
                    if (kokeileSiirtoa(x, y, this.vuorossa.getKunkku())) {//täytyykö erikseen kokeilla kun nyt joka siirto kokeilee?
                        return false;
                    }
                }

            }
        }
        return true;
    }

    public boolean kokeileSiirtoa(int x, int y, Nappula nappula) {
        int vanhaX = nappula.getX();
        int vanhaY = nappula.getY();
        Nappula vastustajaTalteen = this.lauta.haeNappula(x, y);

        boolean teeSiirto = this.lauta.teeSiirto(x, y, nappula); //tee siirto
        if (teeSiirto) {
            this.paivitaPelaajienNappulat();
            if (this.onkoUhattuna(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY())) { //jos on edelleen uhattuna, palautetaan tilanne
                teeSiirto = false;
            } else {
                teeSiirto = true;
            }
            this.lauta.teeTestiSiirto(vanhaX, vanhaY, nappula); //palauta tilanne
            this.lauta.asetaNappula(vastustajaTalteen, x, y);
            this.paivitaPelaajienNappulat();
        }
        return teeSiirto;

    }

    public boolean onShakissa() {
        return onkoUhattuna(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY());
    }

    public boolean onMatissa() {
        if (!this.onShakissa()) {
            return false;
        }
        if (!this.kunkkuEiVoiLiikkua()) {
            return false;
        }
        if (this.uhkaakoUseampi(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY())) { //Onko tästä metodista mitään iloa kellekkään??
            return true;
        }
        if (voiSyodaNappulan(this.uhkaavaNappula(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY()))) {
            return false;
        }
        if (this.voiBlokata()) {
            return false;
        }
        return true;
    }

    public boolean voiSyodaNappulan(Nappula nappula) {
        for (Nappula oma : vuorossa.getNappulat()) {
            if (oma.onSallittuSiirto(nappula.getX(), nappula.getY())) {
                return true;
            }
        }
        return false;
    }

    public boolean voiBlokata() {
        Nappula uhkaava = this.uhkaavaNappula(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY());

        //Toteuta hevonen ja toteuta hevosta ei voi blokata
        ArrayList<Ruutu> uhatutRuudut = uhkaava.uhkausLinja(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY());

        for (Nappula oma : vuorossa.getNappulat()) {
            if (oma.equals(vuorossa.getKunkku())) {
                continue;
            }
            for (Ruutu ruutu : uhatutRuudut) {
                if (oma.onSallittuSiirto(ruutu.getX(), ruutu.getY())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void uusiPeli() {
        this.lauta = new Pelilauta();
        this.alkuAsetelma();
        this.paivitaPelaajienNappulat();
        this.vuorossa = this.valkoinen;
    }

    public Pelilauta getLauta() {
        return lauta;
    }

    public void vaihdaVuoroa() {
        if (this.vuorossa == this.valkoinen) {
            this.vuorossa = this.musta;
        } else {
            this.vuorossa = this.valkoinen;
        }
        this.aktiivinen = null;
    }

    public boolean siirto(int x, int y) {
        if (this.aktiivinen == null) {//pitää olla nappula valittuna
            return false;
        }

        if (!this.kokeileSiirtoa(x, y, this.aktiivinen)) {//Jos siirto jättää kunkun uhatuksi ei sitä voi tehdä
            return false;
        }
        this.lauta.teeSiirto(x, y, aktiivinen);
        this.paivitaPelaajienNappulat();

        this.vaihdaVuoroa();

        return true;

    }

    public Pelaaja getVuorossa() {
        return vuorossa;
    }

    public void asetaAktiivinen(int x, int y) {

        if (this.lauta.haeNappula(x, y) != null) {
            if (this.lauta.haeNappula(x, y).getMaa() == this.vuorossa.getMaa()) {
                this.aktiivinen = this.lauta.haeNappula(x, y);
            } else {
                System.out.println("et voi siirtää vastustajan nappulaa");
            }
        }
    }

    public Nappula getAktiivinen() {
        return aktiivinen;
    }

    public void tulosta() {
        System.out.println("Vuorossa: " + this.vuorossa.toString());
        System.out.println("");
        this.lauta.tulosta();
        System.out.println("");
    }

}
