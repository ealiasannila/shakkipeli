/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import logiikka.peli.Maa;
import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.NappulaPiirto;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 * Nappula on abstrakti luokka jonka eri nappuloita kuvaavat luokat perivät.
 * Nappulat tarkistavat onko niiltä pyydetyt siirrot sallittuja nappulan
 * näkökulmasta. Esim onko liikkumistapa nappulan sääntöjen mukainen. Nappulat
 * myös kertovat mitkä ruudut ovat niiden ja jonkin toisen ruudun välillä, jotta
 * tiedetään mistä ruuudusta voi shakin blokata.
 */
public abstract class Nappula {

    private boolean ensimmainenSiirto;

    protected NappulaPiirto piirto;
    private Maa maa;
    private Pelilauta lauta;
    private int x;
    private int y;

    /**
     * Kun nappula luodaan asetetaan se konstruktorin parametrina annetulle
     * laudalle
     *
     * @param maa
     * @param x
     * @param y
     * @param lauta
     */
    public Nappula(Maa maa, int x, int y, Pelilauta lauta) {
        this.maa = maa;
        this.lauta = lauta;
        this.getLauta().asetaNappula(this, x, y);
        this.ensimmainenSiirto = true;

    }

    /**
     * Kertoo onko nappula jo liikkunut. Tarvitaan Sotilaan ensimmäisellä kahden
     * askeleen siirrolla sekä tornituksessa
     *
     * @return
     */
    public boolean onEnsimmainenSiirto() {
        return ensimmainenSiirto;
    }

    /**
     * Kertoo mihink kaikkiin ruutuihin nappula voisi tyhjällä laudalla liikkua.
     * Käytetään tasapelin tunnistamisessa
     *
     * @return
     */
    public abstract ArrayList<Ruutu> mahdollisetRuudut();

    /**
     * Kertoo onko kohde ruutu saavutettavissa tyhjällä laudalla yhdellä
     * siirrolla nappulan liikkumissääntöjen puitteissa
     *
     * @param x
     * @param y
     * @return
     */
    protected abstract boolean sallittuLiikkumisTapa(int x, int y);

    /**
     * Tarkistaa onko nappulan ja ruudun x, y välillä muita nappuloita jotka
     * estävät liikkumisen
     *
     * @param x
     * @param y
     * @return
     */
    protected abstract boolean reitillaEiMuitaNappuloita(int x, int y);

    /**
     * Kertoo onko kohderuutu laudalla
     *
     * @param x
     * @param y
     * @return
     */
    public boolean kohdeLaudanUlkopuolella(int x, int y) {
        return x < 0 || y < 0 || x > this.getLauta().getSize() - 1 || y > this.getLauta().getSize() - 1;
    }

    /**
     * Kertoo onko kohde sama kuin oma sijainti
     *
     * @param x
     * @param y
     * @return
     */

    public boolean kohdeSamaKuinOmaSijainti(int x, int y) {
        return this.getX() == x && this.getY() == y;
    }

    /**
     * Siirto on sallittu nappulan näkökulmasta jos kohde ei ole laudalla,
     * kohderuudussa ei ole omaa nappulaa, ja reitti kohteeseen on sallittu
     *
     * @param x
     * @param y
     * @return
     */
    public boolean onSallittuSiirto(int x, int y) {
        if (this.kohdeLaudanUlkopuolella(x, y)) {
            return false;
        } else if (this.tarkistaOnkoKohdeOma(x, y)) { //Kohde ruudussa oma nappula
            return false;
        } else if (!this.tarkistaReitti(x, y)) { //Siirto ei sallittu
            return false;
        }
        return true;

    }

    /**
     * Jos kohde on eri kuin oma sijainti, kohde on sallittu tyhjällä laudalla,
     * eikä reitillä ole muita nappuloita on reitti sallittu
     *
     * @param x
     * @param y
     * @return
     */
    public boolean tarkistaReitti(int x, int y) {
        return !(this.kohdeSamaKuinOmaSijainti(x, y) || !sallittuLiikkumisTapa(x, y) || !this.reitillaEiMuitaNappuloita(x, y));
    }

    /**
     * Palauttaa ruudut, joita nappula uhkaa oman sijaintinsa ja toisen ruudun
     * välillä. Nappula ei uhkaa omaa ruutuaan
     *
     * @param x
     * @param y
     * @return
     */
    public abstract ArrayList<Ruutu> uhkausLinja(int x, int y);

    /**
     * kertoo onko kohderuudussa oma nappula
     *
     * @param x
     * @param y
     * @return
     */
    public boolean tarkistaOnkoKohdeOma(int x, int y) {
        if (!this.tarkistaOnkoKohdeVapaa(x, y)) {
            if (this.getLauta().haeNappula(x, y).getMaa() == this.getMaa()) {
                return true;
            }
        }
        return false;
    }

    /**
     * kertoo onko kohderuudussa vastustajan nappula
     *
     * @param x
     * @param y
     * @return
     */
    public boolean tarkistaOnkoKohdeVastustajan(int x, int y) {
        if (!this.tarkistaOnkoKohdeOikeastiTyhja(x, y)) {
            if (this.getLauta().haeNappula(x, y).getMaa() != this.getMaa()) {
                return true;
            }
        }
        return false;
    }

    /**
     * kertoo onko kohderuutu oikeasti tyhjä (eli ei haamusotilasta)
     *
     * @param x
     * @param y
     * @return
     */

    public boolean tarkistaOnkoKohdeOikeastiTyhja(int x, int y) {
        if (this.getLauta().haeNappula(x, y) == null) {
            return true;
        }
        return false;
    }

    /**
     * kertoo onko kohderuutu tyhjä tai onko siinä HaamuSotilas
     *
     * @param x
     * @param y
     * @return
     */
    public boolean tarkistaOnkoKohdeVapaa(int x, int y) {
        if (this.tarkistaOnkoKohdeOikeastiTyhja(x, y)) {
            return true;
        }
        if (this.getLauta().haeNappula(x, y).getClass() == HaamuSotilas.class) {
            return true;
        }
        return false;

    }

    public NappulaPiirto getPiirto() {
        return piirto;
    }

    public void setEnsimmainenSiirto(boolean ensimmainenSiirto) {
        this.ensimmainenSiirto = ensimmainenSiirto;
    }

    public Maa getMaa() {
        return maa;
    }

    public void setMaa(Maa maa) {
        this.maa = maa;
    }

    public Pelilauta getLauta() {
        return lauta;
    }

    public void setLauta(Pelilauta lauta) {
        this.lauta = lauta;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
