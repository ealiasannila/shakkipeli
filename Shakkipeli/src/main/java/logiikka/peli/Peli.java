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
 * Peli tarkistaa onko siirrot sallittuja, ja pyytää sitten lautaa tekemään ne.
 */
public class Peli {

    private Pelilauta lauta;
    private Pelaaja musta;
    private Pelaaja valkoinen;
    private Pelaaja vuorossa;
    private Pelaaja vastustaja;
    private Nappula aktiivinen;
    private Ajastin ajastin;
    private PeliTarkistus pelitarkistus;

    public Peli() {

        this.valkoinen = new Pelaaja(VALKOINEN);
        this.musta = new Pelaaja(MUSTA);
        this.lauta = new Pelilauta();
        this.ajastin = new Ajastin(this);
        this.pelitarkistus = new PeliTarkistus(this);
    }

    /**
     * korottaa sotilaan parametrina annetuksi upseeriksi
     *
     * @param korotettava
     * @param miksiKorotetaan
     */
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

    /**
     * tekee aktiivisena olevalla nappulalla ruutuun x,y
     *
     * @param x
     * @param y
     * @return
     */
    public boolean siirto(int x, int y) {
        if (this.lauta.ruutuLaudanUlkopuolella(x, y)) {
            return false;
        }
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
     * @return
     */
    public boolean asetaAktiivinen(int x, int y) {
        if (this.lauta.ruutuLaudanUlkopuolella(x, y)) {
            return false;
        }

        if (this.lauta.haeNappula(x, y) != null) {
            if (this.lauta.haeNappula(x, y).getMaa() == this.vuorossa.getMaa()) {
                this.aktiivinen = this.lauta.haeNappula(x, y);
                return true;
            }
        }
        return false;
    }

    /**
     * Lataa pelitilanteen
     *
     * @param lukija
     * @param peli
     * @throws FileNotFoundException
     */
    public void lataaPeli(Scanner lukija) {
        this.ajastin.pysayta();
        this.lauta = new Pelilauta();
        this.lataaVuoro(lukija.nextLine());
        this.lataaNappulat(lukija);
        this.asetaKellot(Integer.parseInt(lukija.nextLine()), Integer.parseInt(lukija.nextLine()));

    }

    /**
     * asettaa kelloihin tietyn ajan ja käynnistää ajastimen jos kello on
     * käytössä public koska tarvitaan kellojen pysäyttämiseen kun avataan
     * lataus tai tallennus ikkuna
     *
     * @param valkoisenAika
     * @param mustanAika
     */
    public void asetaKellot(int valkoisenAika, int mustanAika) {

        this.valkoinen.getKello().aseta(valkoisenAika);
        this.musta.getKello().aseta(mustanAika);
        if (valkoisenAika != -1) {
            this.ajastin = new Ajastin(this);

            this.ajastin.kaynnista();
        }
    }

    /**
     * kokeilee siirtoa ja jos siirto ei jätä peliä ei sallittuun tilaan jää
     * siirto voimaan. Jos kunkku jää uhatuksi ei siirto ole sallittu ja se
     * perutaan.
     *
     * @param x
     * @param y
     * @param nappula
     * @return
     */
    boolean kokeileSiirtoa(int x, int y, Nappula nappula) {
        int vanhaX = nappula.getX();
        int vanhaY = nappula.getY();
        Nappula vastustajaTalteen = this.lauta.haeNappula(x, y);

        boolean teeSiirto = this.lauta.teeSiirto(x, y, nappula); //tee siirto
        if (teeSiirto) {
            this.paivitaPelaajienNappulat();
            if (this.pelitarkistus.onkoUhattuna(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY())) { //jos on edelleen uhattuna, palautetaan tilanne
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
        Pelaaja tmp = this.vuorossa;
        this.vuorossa = this.vastustaja;
        this.vastustaja = tmp;

        this.aktiivinen = null;
        this.ohestaLyonti();
        this.paivitaPelaajienNappulat();
    }

    private void sotilaanKorotus() {
        for (Nappula nappula : this.vuorossa.getNappulat()) {
            if (nappula.getClass() == Sotilas.class) {
                if (nappula.getY() == this.vastustaja.getPerusRivi()) {
                    this.asetaKorotettavaksi(nappula);
                }

            }
        }
    }

    private void asetaKorotettavaksi(Nappula sotilas) {
        this.vuorossa.setKorotettava(sotilas);

    }

    private void ohestaLyonti() {

        if (this.getVuorossa().getOhestaLyontiX() != -1) {

            if (!this.lauta.tarkistaOnkoKohdeOikeastiTyhja(this.vuorossa.getOhestaLyontiX(), this.vuorossa.getSotilaastaSeuraavaRivi())
                    && this.lauta.haeNappula(this.vuorossa.getOhestaLyontiX(), this.vuorossa.getSotilaastaSeuraavaRivi()).getClass() != HaamuSotilas.class) {
                this.lauta.asetaNappula(null, this.vuorossa.getOhestaLyontiX(), this.vuorossa.getKaksiRiviaSotilaasta());
            } else {
                this.lauta.asetaNappula(null, this.vuorossa.getOhestaLyontiX(), this.vuorossa.getSotilaastaSeuraavaRivi());
            }
            this.vuorossa.setOhestaLyontiX(-1);
        }
    }

    private void sotilasLiikkuiEkalla2(int x, int y) {
        if (this.aktiivinen.getClass().equals(Sotilas.class)) {
            if (this.aktiivinen.onEnsimmainenSiirto()) {
                if (y == this.vuorossa.getKaksiRiviaSotilaasta()) {
                    this.vuorossa.setOhestaLyontiX(x);
                    new HaamuSotilas(this.vuorossa.getMaa(), x, this.vuorossa.getSotilaastaSeuraavaRivi(), this.lauta);
                }
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

        if (this.pelitarkistus.onkoUhattuna(5, this.getVuorossa().getPerusRivi()) || this.pelitarkistus.onkoUhattuna(6, this.getVuorossa().getPerusRivi()) || this.pelitarkistus.onkoUhattuna(4, this.getVuorossa().getPerusRivi())) {
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
        if (this.pelitarkistus.onkoUhattuna(2, this.vuorossa.getPerusRivi()) || this.pelitarkistus.onkoUhattuna(3, this.vuorossa.getPerusRivi()) || this.pelitarkistus.onkoUhattuna(4, this.vuorossa.getPerusRivi())) {
            return false;
        }
        return true;
    }

    public Pelaaja getVastustaja() {
        return vastustaja;
    }

    private void lataaVuoro(String maa) {
        if (this.musta.getMaa().toString().equals(maa)) {
            this.vuorossa = this.musta;
            this.vastustaja = this.valkoinen;
        } else {
            this.vuorossa = this.valkoinen;
            this.vastustaja = this.musta;
        }
        this.aktiivinen = null;
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

    public PeliTarkistus getPelitarkistus() {
        return pelitarkistus;
    }

    public Ajastin getAjastin() {
        return ajastin;
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

    @Override
    public String toString() {
        return this.vuorossa.getMaa() + "\n"
                + this.lauta.toString()
                + this.valkoinen.getKello().getAika() + "\n"
                + this.musta.getKello().getAika();

    }

}
