/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import kayttoliittyma.nappuloidenPiirto.RatsuPiirto;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 *
 * @author elias
 */
public class Ratsu extends Nappula {

    public Ratsu(Maa maa, int x, int y, Pelilauta lauta) {
        super(maa, x, y, lauta);
        this.piirto = new RatsuPiirto();
    }

    @Override
    public boolean tarkistaReitti(int x, int y) {
        return this.sallittuLiikkumisTapa(x, y);
    }

    @Override
    public ArrayList<Ruutu> uhkausLinja(int x, int y) {
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

}
