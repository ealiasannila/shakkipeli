/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.util.ArrayList;
import static logiikka.peli.Maa.*;
import logiikka.nappulat.*;

/**
 * Pelilauta on 8x8 taulukko nappuloita. Tämä luokka toteuttaa siirrot kun muut
 * ovat testanneet niiden sallittavuuden
 *
 */
public class Pelilauta {

    private Nappula[][] lauta;

    public Pelilauta() {
        this.lauta = new Nappula[8][8];
    }

    /**
     * palauttaa pelilaudan koon (=8)
     *
     * @return
     */
    public int getSize() {
        return this.lauta.length;
    }

    /**
     * asettaa nappulan johonkin pelilaudan ruutuun, jos nappula ei ole null
     * pyydetään nappulaa ottamaan omat koordinaattinsa talteen
     *
     * @param nappula
     * @param x
     * @param y
     */
    public void asetaNappula(Nappula nappula, int x, int y) {
        this.lauta[x][y] = nappula;
        if (nappula != null) {
            nappula.setX(x);
            nappula.setY(y);
        }
    }

    /**
     * palauttaa tietyssä ruudussa olevan nappulan
     *
     * @param x
     * @param y
     * @return
     */
    public boolean tarkistaOnkoKohdeOikeastiTyhja(int x, int y) {
        if (this.haeNappula(x, y) == null) {
            return true;
        }
        return false;
    }

    public boolean tarkistaOnkoKohdeVapaa(int x, int y) {
        if (this.tarkistaOnkoKohdeOikeastiTyhja(x, y)) {
            return true;
        }
        if (this.haeNappula(x, y).getClass() == HaamuSotilas.class) {
            return true;
        }
        return false;

    }

    public Nappula haeNappula(int x, int y) {
        if (x > lauta.length - 1 || y > lauta.length - 1 || x < 0 || y < 0) {
            return null;
        }
        return this.lauta[x][y];
    }

    /**
     * Kysyy nappulalta onko siirto sallittu ja sen jälkeen toteuttaa siirron
     *
     * @param x
     * @param y
     * @param aktiivinen
     * @return
     */
    public boolean teeSiirto(int x, int y, Nappula aktiivinen) {
        if (!aktiivinen.onSallittuSiirto(x, y)) {//tarkistetaan onko siirto sallittu
            return false;
        }
        this.asetaNappula(null, aktiivinen.getX(), aktiivinen.getY());
        this.asetaNappula(aktiivinen, x, y);
        return true;

    }

    /**
     * tekee siirron ilman että tarkistetaan onko siirto sallittu. Käytetään
     * tilanteen palauttamiseen jos peli luokka toteaa siirron kokeilun jälkeen
     * kuninkaan olevan uhattuna
     *
     * @param x
     * @param y
     * @param aktiivinen
     */
    public void teeSiirtoIlmanTarkistusta(int x, int y, Nappula aktiivinen) {

        this.asetaNappula(null, aktiivinen.getX(), aktiivinen.getY());
        this.asetaNappula(aktiivinen, x, y);

    }

    /**
     * tulostaa ascii laudan. Käytetään pelin tallennuksessa ja latauksessa sekä
     * testauksessa
     *
     * @return
     */
    public String toString() {
        StringBuilder tuloste = new StringBuilder();
        for (int y = this.getSize() - 1; y >= 0; y--) {
            for (int x = 0; x < this.getSize(); x++) {
                if (this.haeNappula(x, y) == null) {
                    tuloste.append("o");
                } else {
                    tuloste.append(this.haeNappula(x, y));
                }
            }
            tuloste.append("\n");
        }
        return tuloste.toString();
    }

}
