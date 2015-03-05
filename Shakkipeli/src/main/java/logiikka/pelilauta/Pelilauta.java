/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.pelilauta;

import static logiikka.nappulat.Maa.*;
import logiikka.nappulat.*;

/**
 *
 * @author elias
 */
public class Pelilauta {

    private Nappula[][] lauta;
    private Nappula aktiivinen;

    public Pelilauta() {
        this.lauta = new Nappula[8][8];
        this.alkuAsetelma();

    }

    public void asetaNappula(Nappula nappula, int x, int y) {
        this.lauta[x][y] = nappula;
        if (nappula != null) {
            nappula.setX(x);
            nappula.setY(y);
        }
    }

    private void alkuAsetelma() {
        //valkoiset:
        new Torni(VALKOINEN, 0, 0, this);
        new Torni(VALKOINEN, 7, 0, this);

        //mustat:
        new Torni(MUSTA, 0, 7, this);
        new Torni(MUSTA, 7, 7, this);
    }

    public Nappula haeNappula(int x, int y) {
        return this.lauta[x][y];
    }

    public void teeSiirto(int x, int y) {
        
        if (!this.aktiivinen.tarkistaSiirto(x, y)) {
            System.out.println("EI SALLITTU SIIRTO");
            return;
        }

        this.asetaNappula(null, this.aktiivinen.getX(), this.aktiivinen.getY());
        this.asetaNappula(aktiivinen, x, y);
        

    }

    public void tulosta() {
        for (int y = this.lauta.length - 1; y >= 0; y--) {
            for (int x = 0; x < this.lauta[0].length; x++) {
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

    public Nappula getAktiivinen() {
        return aktiivinen;
    }

    public void setAktiivinen(Nappula aktiivinen) {
        this.aktiivinen = aktiivinen;
    }

}
