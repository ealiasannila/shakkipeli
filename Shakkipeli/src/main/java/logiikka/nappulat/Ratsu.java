/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import logiikka.peli.Maa;
import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.RatsuPiirto;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 * Ratsu liikkuu +/- 1 pystyrivillä ja +/- 2 vaakarivillä tai +/- 2 pystyrivillä
 * ja +/-1 vaakarivillä. Ratsu voi liikkua muiden nappuloiden ylitse
 */
public class Ratsu extends Nappula {

    public Ratsu(Maa maa, int x, int y, Pelilauta lauta) {
        super(maa, x, y, lauta);
        this.piirto = new RatsuPiirto();

    }

    /**
     * Ratsu palauttaa aina tyhjän uhkauslinjan sillä ratsun aiheuttamaa shakkia
     * ei voi blokata
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public ArrayList<Ruutu> nappulanReitti(int x, int y) {
        return new ArrayList<Ruutu>(); //Ratsua ei voi blokata
    }

    @Override
    protected boolean sallittuLiikkumisTapa(int x, int y) {
        if (x == this.getX() + 2 || x == this.getX() - 2) {
            if (y == this.getY() + 1 || y == this.getY() - 1) {
                return true;
            }
        } else if (y == this.getY() + 2 || y == this.getY() - 2) {
            if (x == this.getX() + 1 || x == this.getX() - 1) {
                return true;
            }
        }

        return false;

    }

    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {
        return true; //ratsu loikkii
    }

    @Override
    public ArrayList<Ruutu> mahdollisetRuudut() {
        ArrayList<Ruutu> ruudut = new ArrayList<Ruutu>();
        ruudut.add(new Ruutu(this.getX() + 2, this.getY() + 1));
        ruudut.add(new Ruutu(this.getX() - 2, this.getY() + 1));
        ruudut.add(new Ruutu(this.getX() + 1, this.getY() + 2));
        ruudut.add(new Ruutu(this.getX() - 1, this.getY() + 2));
        ruudut.add(new Ruutu(this.getX() + 2, this.getY() - 1));
        ruudut.add(new Ruutu(this.getX() - 2, this.getY() - 1));
        ruudut.add(new Ruutu(this.getX() + 1, this.getY() - 2));
        ruudut.add(new Ruutu(this.getX() - 1, this.getY() - 2));

        return ruudut;
    }

    @Override
    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "r";
        } else {
            return "R";
        }
    }
}
