/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import logiikka.peli.Maa;
import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.SotilasPiirto;
import static logiikka.peli.Maa.MUSTA;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 * Sotilas liikkuu yhden eteenpäin tai syö etuviistoon. Sotilas saa alussa
 * liikkua kaksi askelta.
 *
 */
public class Sotilas extends Nappula {

    public Sotilas(Maa maa, int x, int y, Pelilauta lauta) {
        super(maa, x, y, lauta);
        this.piirto = new SotilasPiirto();
        
    }
    public Sotilas(Maa maa, int x, int y, Pelilauta lauta, boolean ekaSiirto) {
        super(maa, x, y, lauta);
        this.piirto = new SotilasPiirto();
        this.setEnsimmainenSiirto(ekaSiirto);
        
    }

    /**
     * tarkistaa onko siirto sallittu mustalle sotilaalle Musta ja valkoinen
     * tarkistetaan erikseen koska ne liikkuvat laudalla eri suuntiin
     *
     * @param x
     * @param y
     * @return
     */
    private boolean mustanSallittuLiikkumistapa(int x, int y) {
        if (y == this.getY() - 1 && this.getX() == x) {
            return true;
        }
        if (y == this.getY() - 1 && (this.getX() == x - 1 || this.getX() == x + 1)) {
            if (this.tarkistaOnkoKohdeVastustajan(x, y)) {
                return true;
            }
        }
        if (this.onEnsimmainenSiirto()) {
            if (y == this.getY() - 2 && this.getX() == x) {
                if (this.getLauta().tarkistaOnkoKohdeVapaa(x, y)) {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean valkoisenSallittuLiikkumistapa(int x, int y) {
        if (y == this.getY() + 1 && this.getX() == x) {
            return true;
        }
        if (y == this.getY() + 1 && (this.getX() == x - 1 || this.getX() == x + 1)) {
            if (this.tarkistaOnkoKohdeVastustajan(x, y)) {
                return true;
            }
        }
        if (this.onEnsimmainenSiirto()) {
            if (y == this.getY() + 2 && this.getX() == x) {
                if (this.getLauta().tarkistaOnkoKohdeVapaa(x, y)) {
                    return true;
                }

            }
        }
        return false;

    }

    @Override
    protected boolean sallittuLiikkumisTapa(int x, int y) {
        if (this.getMaa() == MUSTA) {
            return this.mustanSallittuLiikkumistapa(x, y);
        } else {
            return this.valkoisenSallittuLiikkumistapa(x, y);
        }
    }

    /**
     * tarkistaa onko sotilaan edessä seuraavassa ruudussa nappulaa. Olennainen
     * vain jos liikutaan 2 ensimmäisellä siirrolla. Jos sotilas liikkuu vain
     * yhden ei reittia ole ja päätepiste tarkistetaan erikseen.
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {
        if (x == this.getX()) {
            if (this.getMaa() == VALKOINEN) {
                return this.getLauta().tarkistaOnkoKohdeVapaa(x, this.getY() + 1);
            } else {
                return this.getLauta().tarkistaOnkoKohdeVapaa(x, this.getY() - 1);
            }

        }
        return true;
    }

    /**
     * sotilas palauttaa tyhjän uhkauslinjan koska se uhkaa vain yhden päähän
     * eikä sitä näinollen voi blokata
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
            return "s";
        } else {
            return "S";
        }
    }

    @Override
    public ArrayList<Ruutu> mahdollisetRuudut() {
        ArrayList<Ruutu> ruudut = new ArrayList<Ruutu>();
        if (this.getMaa() == VALKOINEN) {
            ruudut.add(new Ruutu(this.getX(), this.getY() + 1));
            ruudut.add(new Ruutu(this.getX() - 1, this.getY() + 1));
            ruudut.add(new Ruutu(this.getX() + 1, this.getY() + 1));

        } else {

            ruudut.add(new Ruutu(this.getX(), this.getY() - 1));
            ruudut.add(new Ruutu(this.getX() - 1, this.getY() - 1));
            ruudut.add(new Ruutu(this.getX() + 1, this.getY() - 1));
        }
        return ruudut;
    }

}
