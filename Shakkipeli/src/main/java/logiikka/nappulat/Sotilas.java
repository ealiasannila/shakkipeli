/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.SotilasPiirto;
import static logiikka.nappulat.Maa.MUSTA;
import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 *
 * @author elias
 */
public class Sotilas extends Nappula {

    public Sotilas(Maa maa, int x, int y, Pelilauta lauta) {
        super(maa, x, y, lauta);
        this.piirto = new SotilasPiirto();
    }

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
                return true;
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
                return true;
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

    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {
        if (x == this.getX()) {//jos liikkuu vinottain liikkuu vain yhden, eikä reittiä ole. Jos yrittää liikkua liian pitkälle palauttaa onSallittuLiikkumistapa jokatapauksessa false
            if (this.getMaa() == VALKOINEN) {
                return this.tarkistaOnkoKohdeVapaa(x, this.getY() + 1);
            } else {
                return this.tarkistaOnkoKohdeVapaa(x, this.getY() - 1);
            }

        }
        return true;
    }

    @Override
    public ArrayList<Ruutu> uhkausLinja(int x, int y) {
        if (!this.tarkistaReitti(x, y)) {
            System.out.println("Ei uhkaa, mutta kysytään linjaa");
            return null;
        }
        ArrayList<Ruutu> uhatutRuudut = new ArrayList<Ruutu>();
        uhatutRuudut.add(new Ruutu(x, y));
        return uhatutRuudut;
    }

    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "s";
        } else {
            return "S";
        }
    }

}
