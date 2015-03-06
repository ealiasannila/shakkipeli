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

    private void alkuAsetelma() {
        //valkoiset:
        new Torni(VALKOINEN, 0, 0, this.lauta);
        new Torni(VALKOINEN, 7, 0, this.lauta);
        this.valkoinen.setKunkku(new Kunkku(VALKOINEN, 3, 0, this.lauta, this));

        //mustat:
        this.musta.setKunkku(new Kunkku(MUSTA, 3, 7, this.lauta, this));
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
                //           System.out.println("returnaan: " + nappula.getX() + nappula.getY());
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
//                System.out.println("x: " + x + " y: " + y);

                if (this.vuorossa.getKunkku().onSallittuSiirto(x, y)) { //jos kunkku voi liikkua tarkistetaan onko kunkku uhattuna liikkuimsen jälkeen (ettei pääse "itsensä taakse suojaan"
                    if (kokeileSiirtoa(x, y)) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    public boolean kokeileSiirtoa(int x, int y) {
        //   System.out.println("koikeilen siirtoa");
        int vanhaX = this.vuorossa.getKunkku().getX();
        int vanhaY = this.vuorossa.getKunkku().getY();
        Nappula vastustajaTalteen = this.lauta.haeNappula(x, y);

        boolean teeSiirto = this.lauta.teeSiirto(x, y, this.vuorossa.getKunkku());
        if (teeSiirto) {
            this.paivitaPelaajienNappulat();
            if (this.onkoUhattuna(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY())) { //jos on edelleen uhattuna, palautetaan tilanne
                this.lauta.teeTestiSiirto(vanhaX, vanhaY, this.vuorossa.getKunkku());
                this.lauta.asetaNappula(vastustajaTalteen, x, y);

                this.paivitaPelaajienNappulat();

            } else {
                this.lauta.teeTestiSiirto(vanhaX, vanhaY, this.vuorossa.getKunkku());
                this.lauta.asetaNappula(vastustajaTalteen, x, y);

                this.paivitaPelaajienNappulat();

                return true;
            }
        }
        return false;

    }

    public boolean onShakissa() {
        return onkoUhattuna(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY());
    }

    public boolean onMatissa() {
        if (!this.onShakissa()) {
            //         System.out.println("Ei ole shakissa");
            return false;
        }
        //    System.out.println("on shakissa");
        if (!this.kunkkuEiVoiLiikkua()) {
            //         System.out.println("kunkku voi liikkua");
            return false;
        }
        //    System.out.println("ei voi liikkua");
        if (this.uhkaakoUseampi(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY())) { //Onko tästä metodista mitään iloa kellekkään??
            //        System.out.println("useampi uhkaa");
            return true;
        }

        if (this.voiBlokata()) {
            return false;
        }
        //    System.out.println("ei voi blokata");

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
        if (voiSyodaNappulan(uhkaava)) {
            return true;
        }
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

    public boolean siirto(int x, int y) {
        if (this.aktiivinen == null) {
            System.out.println("valitse nappula");
            return false;
        }

        if (!this.lauta.teeSiirto(x, y, this.aktiivinen)) {
            return false;
        }

        if (this.vuorossa == this.valkoinen) {
            this.vuorossa = this.musta;
        } else {
            this.vuorossa = this.valkoinen;
        }
        this.paivitaPelaajienNappulat();
        this.aktiivinen = null;
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
