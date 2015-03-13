/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import logiikka.nappulat.Kuningatar;
import logiikka.nappulat.Kunkku;
import logiikka.nappulat.Lahetti;
import static logiikka.nappulat.Maa.MUSTA;
import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.nappulat.Nappula;
import logiikka.nappulat.Ratsu;
import logiikka.nappulat.Sotilas;
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
        this.lauta = new Pelilauta();
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

    public boolean kunkkuEiVoiLiikkua() {
        for (int y = Math.max(0, this.vuorossa.getKunkku().getY() - 1); y <= Math.min(lauta.getSize() - 1, this.vuorossa.getKunkku().getY() + 1); y++) {
            for (int x = Math.max(0, this.vuorossa.getKunkku().getX() - 1); x <= Math.min(lauta.getSize() - 1, this.vuorossa.getKunkku().getX() + 1); x++) {
                if (kokeileSiirtoa(x, y, this.vuorossa.getKunkku())) {
                    return false;
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
            this.lauta.teeSiirtoIlmanTarkistusta(vanhaX, vanhaY, nappula); //palauta tilanne
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
                if (oma.equals(vuorossa.getKunkku())) {
                    if (this.onkoUhattuna(nappula.getX(), nappula.getY())) {
                        continue;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean voiBlokata() {
        Nappula uhkaava = this.uhkaavaNappula(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY());
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
        if (!this.tornitus(x, y)) {        //TORNITUS kokeile tehdä tornitus, jos ei onnistu yritä muuta siirtoa
            if (!this.kokeileSiirtoa(x, y, this.aktiivinen)) {//Jos siirto jättää kunkun uhatuksi ei sitä voi tehdä
                return false;
            }
            this.lauta.teeSiirto(x, y, aktiivinen);
        }
        this.aktiivinen.setEnsimmainenSiirto(false);
        this.paivitaPelaajienNappulat();
        this.vaihdaVuoroa();
        return true;
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

    private boolean tornitus(int x, int y) {
        if (this.aktiivinen.equals(this.getVuorossa().getKunkku())) {
            if (this.voiTehdaLyhyenTornituksen(x, y) || this.voiTehdaPitkanTornituksen(x, y)) {
                this.teeTornitus(x, y);
                return true;
            }
        }
        return false;
    }

    private void teeTornitus(int x, int y) {
        this.lauta.teeSiirtoIlmanTarkistusta(x, y, aktiivinen);
        if (x == 6) {//lyhyt tornitus
            this.lauta.teeSiirtoIlmanTarkistusta(5, y, this.lauta.haeNappula(7, y));
        } else {//pitkä tornitus
            this.lauta.teeSiirtoIlmanTarkistusta(3, y, this.lauta.haeNappula(0, y));
        }
    }

    private boolean voiTehdaLyhyenTornituksen(int x, int y) {
        if (x != 6) {
            return false;
        }
        if (this.vuorossa.getMaa() == VALKOINEN) {
            if (y != 0) {
                return false;
            }
            return this.valkoinenVoiTehdaLyhyenTornituksen();
        } else {
            if (y != 7) {
                return false;
            }
            return this.mustaVoiTehdaLyhyenTornituksen();
        }
    }

    private boolean valkoinenVoiTehdaLyhyenTornituksen() {
        if (!this.getVuorossa().getKunkku().tarkistaOnkoKohdeOma(7, 0)) {
            return false;
        }
        if (!(this.getVuorossa().getKunkku().onEnsimmainenSiirto() && this.getLauta().haeNappula(7, 0).onEnsimmainenSiirto())) {
            return false;
        }
        if (!(this.getVuorossa().getKunkku().tarkistaOnkoKohdeVapaa(5, 0) && this.getVuorossa().getKunkku().tarkistaOnkoKohdeVapaa(6, 0))) {
            return false;
        }
        if (this.onkoUhattuna(5, 0) || this.onkoUhattuna(6, 0) || this.onkoUhattuna(4, 0)) {
            return false;
        }
        return true;
    }

    private boolean mustaVoiTehdaLyhyenTornituksen() {
        if (!this.getVuorossa().getKunkku().tarkistaOnkoKohdeOma(7, 7)) {
            return false;
        }
        if (!(this.getVuorossa().getKunkku().onEnsimmainenSiirto() && this.getLauta().haeNappula(7, 7).onEnsimmainenSiirto())) {
            return false;
        }
        if (!(this.getVuorossa().getKunkku().tarkistaOnkoKohdeVapaa(5, 7) && this.getVuorossa().getKunkku().tarkistaOnkoKohdeVapaa(6, 7))) {
            return false;
        }
        if (this.onkoUhattuna(5, 7) || this.onkoUhattuna(6, 7) || this.onkoUhattuna(4, 7)) {
            return false;
        }
        return true;
    }

    private boolean voiTehdaPitkanTornituksen(int x, int y) {
        if (x != 2) { //pitkässä tornituksessa kohteen x:n pitää olla 2
            return false;
        }
        if (this.vuorossa.getMaa() == VALKOINEN) {
            if (y != 0) {
                return false;
            }
            return this.valkoinenVoiTehdaPitkanTornituksen();
        } else {
            if (y != 7) {
                return false;
            }
            return this.mustaVoiTehdaPitkanTornituksen();
        }
    }

    private boolean valkoinenVoiTehdaPitkanTornituksen() {
        if (!this.getVuorossa().getKunkku().tarkistaOnkoKohdeOma(0, 0)) {
            return false;
        }
        if (!(this.getVuorossa().getKunkku().onEnsimmainenSiirto() && this.getLauta().haeNappula(0, 0).onEnsimmainenSiirto())) {
            return false;
        }
        if (!(this.getVuorossa().getKunkku().tarkistaOnkoKohdeVapaa(1, 0) && this.getVuorossa().getKunkku().tarkistaOnkoKohdeVapaa(2, 0) && this.getVuorossa().getKunkku().tarkistaOnkoKohdeVapaa(3, 0))) {
            return false;
        }
        if (this.onkoUhattuna(2, 0) || this.onkoUhattuna(3, 0) || this.onkoUhattuna(4, 0)) {
            return false;
        }
        return true;
    }

    private boolean mustaVoiTehdaPitkanTornituksen() {
        if (!this.getVuorossa().getKunkku().tarkistaOnkoKohdeOma(0, 7)) {
            return false;
        }
        if (!(this.getVuorossa().getKunkku().onEnsimmainenSiirto() && this.getLauta().haeNappula(0, 7).onEnsimmainenSiirto())) {
            return false;
        }
        if (!(this.getVuorossa().getKunkku().tarkistaOnkoKohdeVapaa(1, 7) && this.getVuorossa().getKunkku().tarkistaOnkoKohdeVapaa(2, 7) && this.getVuorossa().getKunkku().tarkistaOnkoKohdeVapaa(3, 7))) {
            return false;
        }
        if (this.onkoUhattuna(2, 7) || this.onkoUhattuna(3, 7) || this.onkoUhattuna(4, 7)) {
            return false;
        }
        return true;
    }

    private void lataaVuoro(String maa) {
        if (this.musta.getMaa().toString().equals(maa)) {
            this.vuorossa = this.musta;
        } else {
            this.vuorossa = this.valkoinen;
        }
    }

    private void lataaNappulat(Scanner lukija) {
        int riviNro = 7;
        while (riviNro >= 0) {
            String rivi = lukija.nextLine();
            for (int i = 0; i < rivi.length(); i++) {
                switch (rivi.charAt(i)) {
                    case 'K':
                        this.musta.setKunkku(new Kunkku(MUSTA, i, riviNro, this.lauta));
                        break;
                    case 'Q':
                        new Kuningatar(MUSTA, i, riviNro, this.lauta);
                        break;
                    case 'L':
                        new Lahetti(MUSTA, i, riviNro, this.lauta);
                        break;
                    case 'R':
                        new Ratsu(MUSTA, i, riviNro, this.lauta);
                        break;
                    case 'T':
                        new Torni(MUSTA, i, riviNro, this.lauta);
                        break;
                    case 'S':
                        new Sotilas(MUSTA, i, riviNro, this.lauta);
                        break;

                    case 'k':
                        this.valkoinen.setKunkku(new Kunkku(VALKOINEN, i, riviNro, this.lauta));
                        break;
                    case 'q':
                        new Kuningatar(VALKOINEN, i, riviNro, this.lauta);
                        break;
                    case 'l':
                        new Lahetti(VALKOINEN, i, riviNro, this.lauta);
                        break;
                    case 'r':
                        new Ratsu(VALKOINEN, i, riviNro, this.lauta);
                        break;
                    case 't':
                        new Torni(VALKOINEN, i, riviNro, this.lauta);
                        break;
                    case 's':
                        new Sotilas(VALKOINEN, i, riviNro, this.lauta);
                        break;
                }
            }
            riviNro--;
        }
        this.paivitaPelaajienNappulat();
    }

    public void lataaPeli(File peli) throws FileNotFoundException {
        Scanner lukija = new Scanner(peli);
        this.lauta = new Pelilauta();
        this.lataaVuoro(lukija.nextLine());
        this.lataaNappulat(lukija);
    }

    public Pelaaja getMusta() {
        return musta;
    }

    public Pelaaja getValkoinen() {
        return valkoinen;
    }

    public Pelaaja getVuorossa() {
        return vuorossa;
    }

    public Nappula getAktiivinen() {
        return aktiivinen;
    }

    public Pelilauta getLauta() {
        return lauta;
    }

    public String toString() {
        return this.vuorossa.getMaa() + "\n" + this.lauta.toString();

    }

}
