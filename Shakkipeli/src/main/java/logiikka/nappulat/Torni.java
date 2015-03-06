/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 *
 * @author elias
 */
public class Torni extends Nappula {

    public Torni(Maa maa, int x, int y, Pelilauta pelilauta) {
        super(maa, x, y, pelilauta);
    }

    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "[t]";
        } else {
            return "[T]";
        }
    }

    private boolean sallittuLiikkumisTapa(int x, int y) {
        return !(this.getX() != x && this.getY() != y);
    }

    private boolean reitillaEiMuitaNappuloita(int x, int y) {
        if (this.getX() < x) { //Tarkastetaan onko reitillä muita nappuloita
            for (int i = this.getX() + 1; i < x; i++) {
                if (!tarkistaOnkoKohdeVapaa(i, y)) {
                    return false;
                }
            }
        } else if (this.getX() > x) {
            for (int i = this.getX() - 1; i > x; i--) {
                if (!tarkistaOnkoKohdeVapaa(i, y)) {
                    return false;
                }
            }
        } else if (this.getY() < y) {
            for (int i = this.getY() + 1; i < y; i++) {
                if (!tarkistaOnkoKohdeVapaa(x, i)) {
                    return false;
                }
            }
        } else if (this.getY() > y) {
            for (int i = this.getY() - 1; i > y; i--) {
                if (!tarkistaOnkoKohdeVapaa(x, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean tarkistaReitti(int x, int y) {
        return !(this.kohdeSamaKuinOmaSijainti(x, y) || !sallittuLiikkumisTapa(x, y) || !this.reitillaEiMuitaNappuloita(x, y));
    }

    @Override
    public ArrayList<Ruutu> uhkausLinja(int x, int y) { //palauttaa ne ruudut joista uhkauksen voi blokata
        if (!this.tarkistaReitti(x, y)) {//jos ei voi ylipäätänsä uhata ruutua ei silloin linjalla ole ruutuja
            return null;
        }
        ArrayList<Ruutu> uhatutRuudut = new ArrayList<>();
        if (x < this.getX()) {
            for (int i = x; i < this.getX(); i++) {
                uhatutRuudut.add(new Ruutu(i, y));
            }
        } else if (x > this.getX()) {
            for (int i = this.getX() + 1; i <= x; i++) {
                uhatutRuudut.add(new Ruutu(i, y));
            }
        } else if (y < this.getY()) {
            for (int i = y; i > this.getY(); i++) {
                uhatutRuudut.add(new Ruutu(x, i));
            }
        } else if (y > this.getY()) {
            for (int i = this.getY() + 1; i <= y; i++) {
                uhatutRuudut.add(new Ruutu(x, i));
            }
        }
        return uhatutRuudut;
    }

}
