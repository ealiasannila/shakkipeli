/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.LahettiPiirto;
import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 *
 * @author elias
 */
public class Lahetti extends Nappula {

    public Lahetti(Maa maa, int x, int y, Pelilauta lauta) {
        super(maa, x, y, lauta);
        this.piirto = new LahettiPiirto();
    }

    @Override
    protected boolean sallittuLiikkumisTapa(int x, int y) {
        return Math.abs(this.getX() - x) == Math.abs(this.getY() - y);
    }

    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {

        if (this.getX() < x && this.getY() < y) { //kohde yläoikealle
            int j = this.getY() + 1;
            for (int i = this.getX() + 1; i < x; i++) {
                if (!tarkistaOnkoKohdeVapaa(i, j)) {
                    return false;
                }
                j++;
            }
        } else if (this.getX() > x && this.getY() > y) {//kohde alavasemmalle
            int j = this.getY() - 1;
            for (int i = this.getX() - 1; i > x; i--) {
                if (!tarkistaOnkoKohdeVapaa(i, j)) {
                    return false;
                }
                j--;
            }
        } else if (this.getY() < y && this.getX() > x) {//kohde ylävasemmalle
            int j = this.getX() - 1;
            for (int i = this.getY() + 1; i < y; i++) {
                if (!tarkistaOnkoKohdeVapaa(j, i)) {
                    return false;
                }
                j--;
            }
        } else if (this.getY() > y && this.getX() < x) {//kohde alaoikealle
            int j = this.getX() + 1;
            for (int i = this.getY() - 1; i > y; i--) {
                if (!tarkistaOnkoKohdeVapaa(j, i)) {
                    return false;
                }
                j++;
            }
        }
        return true;

    }

    @Override
    public ArrayList<Ruutu> uhkausLinja(int x, int y) {
        if (!this.tarkistaReitti(x, y)) {//jos ei voi ylipäätänsä uhata ruutua ei silloin linjalla ole ruutuja
            return null;
        }
        ArrayList<Ruutu> uhatutRuudut = new ArrayList<>();

        if (this.getX() < x && this.getY() < y) { //kohde yläoikealle
            int j = this.getY() + 1;
            for (int i = this.getX() + 1; i < x; i++) {
                if (!tarkistaOnkoKohdeVapaa(i, j)) {
                    uhatutRuudut.add(new Ruutu(i, j));
                }
                j++;
            }
        } else if (this.getX() > x && this.getY() > y) {//kohde alavasemmalle
            int j = this.getY() - 1;
            for (int i = this.getX() - 1; i > x; i--) {
                if (!tarkistaOnkoKohdeVapaa(i, j)) {

                    uhatutRuudut.add(new Ruutu(i, j));
                }
                j--;
            }
        } else if (this.getY() < y && this.getX() > x) {//kohde ylävasemmalle
            int j = this.getX() - 1;
            for (int i = this.getY() + 1; i < y; i++) {
                if (!tarkistaOnkoKohdeVapaa(j, i)) {

                    uhatutRuudut.add(new Ruutu(j, i));
                }
                j--;
            }
        } else if (this.getY() > y && this.getX() < x) {//kohde alaoikealle
            int j = this.getX() + 1;
            for (int i = this.getY() - 1; i > y; i--) {
                if (!tarkistaOnkoKohdeVapaa(j, i)) {

                    uhatutRuudut.add(new Ruutu(j, i));
                }
                j++;
            }
        }
        return uhatutRuudut;
    }

    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "l";
        } else {
            return "L";
        }
    }

}
