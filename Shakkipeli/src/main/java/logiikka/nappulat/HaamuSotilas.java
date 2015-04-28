/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import logiikka.peli.Maa;
import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.HaamuSotilasPiirto;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 * HaamuSotilas ei näy laudalla, mutta se luodaan seuraavan siirron ajaksi, kun
 * sotilas liikku 2 ensimmäisellä siirrolla. HaamuSotilasta käytetään
 * ohestalyönnin toteuttamiseen.
 *
 * @author elias
 */
public class HaamuSotilas extends Nappula {

    public HaamuSotilas(Maa maa, int x, int y, Pelilauta lauta) {
        super(maa, x, y, lauta);
        this.piirto = new HaamuSotilasPiirto();

    }

    /**
     * Haamusotilasta ei voi liikuttaa. palauttaa aina false
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    protected boolean sallittuLiikkumisTapa(int x, int y) {
        return false;
    }

    /**
     * Haamusotilasta ei voi liikuttaa. palauttaa aina true
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {
        return true; //Haamusotilasta ei voi liikuttaa.
    }

    /**
     * Haamusotilasta ei voi liikuttaa. palauttaa aina tyhjän ArrayListin
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public ArrayList<Ruutu> nappulanReitti(int x, int y) {
        return new ArrayList<Ruutu>();
    }

    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "h";
        } else {
            return "H";
        }

    }

    /**
     * Haamusotilasta ei voi liikuttaa. palauttaa aina false
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public ArrayList<Ruutu> mahdollisetRuudut() {
        return new ArrayList<Ruutu>();

    }

}
