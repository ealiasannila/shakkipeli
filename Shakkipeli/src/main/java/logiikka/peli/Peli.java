/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import logiikka.nappulat.HaamuSotilas;
import logiikka.nappulat.Kuningatar;
import logiikka.nappulat.Kunkku;
import logiikka.nappulat.Lahetti;
import static logiikka.peli.Maa.MUSTA;
import static logiikka.peli.Maa.VALKOINEN;
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

    public boolean onShakissa() {
        return onkoUhattuna(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY());
    }

    /**
     * kertoo onko peli tullut päätökseen On matti jos: on shakki kunkku ei voi
     * liikkua useampi uhkaa tai ei voi syödä uhkaajaa ja ei voi blokata shakkia
     *
     * @return
     */
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

    /**
     * kertoo onko peli päättynyt tasapeliin peli on tasapeli jos: kumpikaan ei
     * voi tehdä mattia jäljellä olevilla nappuloilla tai kumpikaan ei ole
     * shakissa ja kumpikaan ei voi liikuttaa mitään nappulaa
     *
     * @return
     */
    public boolean onPatissa() {

        if (this.eiVoiTehdaMattia()) {
            return true;
        }
        if (this.onShakissa()) {
            return false;
        }

        if (this.eiVoiLiikkuaMillaanNappulalla()) {
            return true;
        }

        return false;

    }

    public void korotaSotilas(Pelaaja korotettava, char miksiKorotetaan) {
        switch (miksiKorotetaan) {
            case 'q':
                new Kuningatar(korotettava.getMaa(), korotettava.getKorotettava().getX(), korotettava.getKorotettava().getY(), this.getLauta());
                break;
            case 't':
                new Torni(korotettava.getMaa(), korotettava.getKorotettava().getX(), korotettava.getKorotettava().getY(), this.getLauta());
                break;
            case 'l':
                new Lahetti(korotettava.getMaa(), korotettava.getKorotettava().getX(), korotettava.getKorotettava().getY(), this.getLauta());
                break;
            case 'r':
                new Ratsu(korotettava.getMaa(), korotettava.getKorotettava().getX(), korotettava.getKorotettava().getY(), this.getLauta());
                break;

        }
        this.paivitaPelaajienNappulat();
        korotettava.setKorotettava(null);
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
        this.sotilasLiikkuiEkalla2(x, y); //jos liikutettiin sotilasta alussa 2 asetetaan näkymätön "haamusotilas" ohestalyöntiä varten
        this.aktiivinen.setEnsimmainenSiirto(false);
        this.sotilaanKorotus();

        this.vaihdaVuoroa();
        return true;
    }

    /**
     * tarkistaa onko valittu nappula oma ja asettaa nappulan aktiiviseksi
     * siirtoa varten
     *
     * @param x
     * @param y
     */
    public boolean asetaAktiivinen(int x, int y) {

        if (this.lauta.haeNappula(x, y) != null) {
            if (this.lauta.haeNappula(x, y).getMaa() == this.vuorossa.getMaa()) {
                this.aktiivinen = this.lauta.haeNappula(x, y);
                return true;
            }
        }
        return false;
    }

    public void lataaPeli(File peli) throws FileNotFoundException {
        Scanner lukija = new Scanner(peli);
        this.lauta = new Pelilauta();
        this.lataaVuoro(lukija.nextLine());
        this.lataaNappulat(lukija);
    }

    /**
     * kertoo onko jokin ruutu uhattuna
     *
     * @param x
     * @param y
     * @return
     */
    private boolean onkoUhattuna(int x, int y) {
        if (this.uhkaavaNappula(x, y) == null) {
            return false;
        }
        return true;
    }

    /**
     * kertoo mikä nappula jos mikään uhkaa tiettyä ruutua
     *
     * @param x
     * @param y
     * @return
     */
    private Nappula uhkaavaNappula(int x, int y) {
        if (this.vuorossa.getMaa() == MUSTA) {
            return this.haeUhkaava(x, y, this.valkoinen.getNappulat());
        } else {
            return this.haeUhkaava(x, y, this.musta.getNappulat());
        }
    }

    /**
     * käy kaikki vastustajan nappulat läpi ja tarkistaa onko niistä jollain
     * reitti annettuun ruutuun. Palauttaa ensimmäisen nappulan jonka löytää tai
     * null
     *
     * @param x
     * @param y
     * @param vastustajanNappulat
     * @return
     */
    private Nappula haeUhkaava(int x, int y, ArrayList<Nappula> vastustajanNappulat) {
        for (Nappula nappula : vastustajanNappulat) {
            if (nappula.tarkistaReitti(x, y)) {
                return nappula;
            }
        }
        return null;

    }

    /**
     * kertoo uhkaako useampi nappula, käytetään matin tarkistuksessa
     *
     * @param x
     * @param y
     * @return
     */
    private boolean uhkaakoUseampi(int x, int y) {
        if (this.vuorossa.getMaa() == MUSTA) {
            return this.uhkaakoUseampi(x, y, this.valkoinen.getNappulat());

        } else {

            return this.uhkaakoUseampi(x, y, this.musta.getNappulat());

        }
    }

    private boolean uhkaakoUseampi(int x, int y, ArrayList<Nappula> vastustajanNappulat) {
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

    private boolean kunkkuEiVoiLiikkua() {
        for (int y = Math.max(0, this.vuorossa.getKunkku().getY() - 1); y <= Math.min(lauta.getSize() - 1, this.vuorossa.getKunkku().getY() + 1); y++) {
            for (int x = Math.max(0, this.vuorossa.getKunkku().getX() - 1); x <= Math.min(lauta.getSize() - 1, this.vuorossa.getKunkku().getX() + 1); x++) {
                if (kokeileSiirtoa(x, y, this.vuorossa.getKunkku())) {
                    return false;
                }

            }
        }
        return true;
    }

    private boolean kokeileSiirtoa(int x, int y, Nappula nappula) {
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
            this.palautaTilanne(vanhaX, vanhaY, nappula, x, y, vastustajaTalteen);

        }
        return teeSiirto;

    }

    private void palautaTilanne(int vanhaX, int vanhaY, Nappula nappula, int vastustajanX, int vastustajanY, Nappula vastustajaTalteen) {
        this.lauta.teeSiirtoIlmanTarkistusta(vanhaX, vanhaY, nappula); //palauta tilanne
        this.lauta.asetaNappula(vastustajaTalteen, vastustajanX, vastustajanY);
        this.paivitaPelaajienNappulat();
    }

    /**
     * kertoo onko kuningas uhattuna, Pelin lisäksi käytetään väärästä siirrosta
     * kertovan punaisen väläyksen tekemisessä
     *
     * @return
     */
    private boolean eiVoiLiikkuaMillaanNappulalla() {
        for (Nappula nappula : this.vuorossa.getNappulat()) {
            for (Ruutu ruutu : nappula.mahdollisetRuudut()) {
                if (this.kokeileSiirtoa(ruutu.getX(), ruutu.getY(), nappula)) {
                    return false;
                }
            }
        }
        return true;

    }

    private boolean eiVoiTehdaMattia() {
        return this.eiVoiTehdaMattia(this.getMusta()) && this.eiVoiTehdaMattia(this.getValkoinen());
    }

    private boolean eiVoiTehdaMattia(Pelaaja pelaaja) {
        if (this.vainKunkku(pelaaja)) {
            return true;
        }
        if (this.vainKunkkuJaRatsu(pelaaja)) {
            return true;
        }
        if (this.vainKunkkuJaYhdellaVarillaLahetteja(pelaaja)) {
            return true;
        }
        return false;

    }

    private boolean vainKunkku(Pelaaja pelaaja) {
        return pelaaja.getNappulat().size() == 1;
    }

    private boolean vainKunkkuJaRatsu(Pelaaja pelaaja) {
        if (pelaaja.getNappulat().size() != 2) {
            return false;
        }
        for (Nappula nappula : pelaaja.getNappulat()) {
            if (nappula.getClass() != Kunkku.class && nappula.getClass() != Ratsu.class) {
                return false;
            }
        }
        return true;
    }

    private boolean vainKunkkuJaYhdellaVarillaLahetteja(Pelaaja pelaaja) {
        boolean onMustaLahetti = false;
        boolean onVaaleaLahetti = false;

        for (int i = 0; i < pelaaja.getNappulat().size(); i++) {
            Nappula nappula = pelaaja.getNappulat().get(i);
            if (nappula.equals(pelaaja.getKunkku())) {
                continue;
            } else if (nappula.getClass() == Lahetti.class) {
                if ((nappula.getX() + nappula.getY()) % 2 == 0) {
                    onVaaleaLahetti = true;
                } else {
                    onMustaLahetti = true;
                }
            } else {
                return false;
            }

        }

        return !(onVaaleaLahetti && onMustaLahetti);

    }

    private boolean voiSyodaNappulan(Nappula nappula) {
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

    private boolean voiBlokata() {
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

    private void paivitaPelaajienNappulat() {
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

    private void vaihdaVuoroa() {
        if (this.vuorossa == this.valkoinen) {
            this.vuorossa = this.musta;
        } else {
            this.vuorossa = this.valkoinen;
        }
        this.aktiivinen = null;
        this.ohestaLyonti();
        this.paivitaPelaajienNappulat();

    }

    private void sotilaanKorotus() {
        for (Nappula nappula : this.vuorossa.getNappulat()) {
            if (nappula.getClass() == Sotilas.class) {
                if (this.vuorossa.getMaa() == VALKOINEN) {
                    if (nappula.getY() == 7) {
                        this.asetaKorotettavaksi(nappula);
                    }
                } else {
                    if (nappula.getY() == 0) {
                        this.asetaKorotettavaksi(nappula);
                    }
                }
            }
        }
    }

    private void asetaKorotettavaksi(Nappula sotilas) {
        this.vuorossa.setKorotettava(sotilas);

    }

    private void ohestaLyonti() {

        if (this.getVuorossa().getOhestaLyontiX() != -1) {

            if (this.lauta.haeNappula(this.vuorossa.getOhestaLyontiX(), this.vuorossa.getOhestaLyontiY()).getClass() != HaamuSotilas.class) {

                this.lauta.asetaNappula(null, this.vuorossa.getOhestaLyontiX(), this.vuorossa.getLyotyY());
            } else {
                this.lauta.asetaNappula(null, this.vuorossa.getOhestaLyontiX(), this.vuorossa.getOhestaLyontiY());
            }
            this.vuorossa.setOhestaLyontiX(-1);
        }
    }

    private void sotilasLiikkuiEkalla2(int x, int y) {
        if (this.aktiivinen.getClass().equals(Sotilas.class)) {
            if (this.aktiivinen.onEnsimmainenSiirto()) {
                if (y == this.vuorossa.getLyotyY()) {
                    this.vuorossa.setOhestaLyontiX(x);
                    new HaamuSotilas(this.vuorossa.getMaa(), x, this.vuorossa.getOhestaLyontiY(), this.lauta);
                }
            }
        }
    }

    /**
     * kokeilee siirtoa ja jos siirto ei jätä peliä ei sallittuun tilaan jää
     * siirto voimaan. Jos kunkku jää uhatuksi ei siirto ole sallittu ja se
     * perutaan.
     *
     * @param x
     * @param y
     * @return
     */
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
        if (y != this.getVuorossa().getPerusRivi()) {
            return false;
        }
        if (this.getLauta().haeNappula(7, this.vuorossa.getPerusRivi()) == null) {
            return false;
        }
        if (!(this.getVuorossa().getKunkku().onEnsimmainenSiirto() && this.getLauta().haeNappula(7, this.vuorossa.getPerusRivi()).onEnsimmainenSiirto())) {
            return false;
        }
        if (!(this.getLauta().tarkistaOnkoKohdeVapaa(5, this.getVuorossa().getPerusRivi()) && this.getLauta().tarkistaOnkoKohdeVapaa(6, this.vuorossa.getPerusRivi()))) {
            return false;
        }

        if (this.onkoUhattuna(5, this.getVuorossa().getPerusRivi()) || this.onkoUhattuna(6, this.getVuorossa().getPerusRivi()) || this.onkoUhattuna(4, this.getVuorossa().getPerusRivi())) {
            return false;
        }
        return true;
    }

    private boolean voiTehdaPitkanTornituksen(int x, int y) {
        if (x != 2 || y != this.vuorossa.getPerusRivi()) { //pitkässä tornituksessa kohteen x:n pitää olla 2
            return false;
        }
        if (this.getLauta().haeNappula(0, this.vuorossa.getPerusRivi()) == null) {
            return false;
        }
        if (!(this.getVuorossa().getKunkku().onEnsimmainenSiirto() && this.getLauta().haeNappula(0, this.vuorossa.getPerusRivi()).onEnsimmainenSiirto())) {
            return false;
        }
        if (!(this.getLauta().tarkistaOnkoKohdeVapaa(1, this.vuorossa.getPerusRivi()) && this.getLauta().tarkistaOnkoKohdeVapaa(2, this.vuorossa.getPerusRivi()) && this.getLauta().tarkistaOnkoKohdeVapaa(3, this.vuorossa.getPerusRivi()))) {
            return false;
        }
        if (this.onkoUhattuna(2, this.vuorossa.getPerusRivi()) || this.onkoUhattuna(3, this.vuorossa.getPerusRivi()) || this.onkoUhattuna(4, this.vuorossa.getPerusRivi())) {
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

                    case 'h':
                        new HaamuSotilas(VALKOINEN, i, riviNro, this.lauta);
                        break;
                    case 'H':
                        new HaamuSotilas(MUSTA, i, riviNro, this.lauta);
                        break;
                }
            }
            riviNro--;
        }
        this.paivitaPelaajienNappulat();
    }

    /**
     * Lataa tiedostosta pelitilanteen
     *
     * @param peli
     * @throws FileNotFoundException
     */
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

    @Override
    public String toString() {
        return this.vuorossa.getMaa() + "\n" + this.lauta.toString();

    }

}
