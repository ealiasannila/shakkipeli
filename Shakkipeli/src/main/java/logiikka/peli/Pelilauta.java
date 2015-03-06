/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.util.ArrayList;
import static logiikka.nappulat.Maa.*;
import logiikka.nappulat.*;

/**
 *
 * @author elias
 */
public class Pelilauta {

    private Nappula[][] lauta;

    public Pelilauta() {
        this.lauta = new Nappula[8][8];
    }

    public int getSize() {
        return this.lauta.length;
    }

    public void asetaNappula(Nappula nappula, int x, int y) {
        this.lauta[x][y] = nappula;
        if (nappula != null) {
            nappula.setX(x);
            nappula.setY(y);
        }
    }

    public Nappula haeNappula(int x, int y) {
        if (x > lauta.length - 1 || y > lauta.length - 1 || x < 0 || y < 0) {
            return null;
        }
        return this.lauta[x][y];
    }

    public boolean teeSiirto(int x, int y, Nappula aktiivinen) {

        if (!aktiivinen.onSallittuSiirto(x, y)) {
            System.out.println("EI SALLITTU SIIRTO");
            return false;
        }

        this.asetaNappula(null, aktiivinen.getX(), aktiivinen.getY());
        this.asetaNappula(aktiivinen, x, y);

        return true;

    }

    public void teeTestiSiirto(int x, int y, Nappula aktiivinen) {

        this.asetaNappula(null, aktiivinen.getX(), aktiivinen.getY());
        this.asetaNappula(aktiivinen, x, y);

    }

    public void tulosta() {
        for (int y = this.getSize() - 1; y >= 0; y--) {
            for (int x = 0; x < this.getSize(); x++) {
                if (this.haeNappula(x, y) == null) {
                    System.out.print("[ ]");
                } else {
                    System.out.print(this.haeNappula(x, y));
                }
            }
            System.out.println("");
        }
        System.out.println("    -    -    - -  ");
    }

}
