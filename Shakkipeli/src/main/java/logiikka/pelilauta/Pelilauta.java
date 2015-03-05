/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.pelilauta;

import java.util.ArrayList;
import static logiikka.nappulat.Maa.*;
import logiikka.nappulat.*;

/**
 *
 * @author elias
 */
public class Pelilauta {

    private Nappula[][] lauta;
    private Nappula aktiivinen;
    private ArrayList<Nappula> mustat;
    private ArrayList<Nappula> valkoiset;
    private Kunkku mustaKunkku;
    private Kunkku valkonenKunkku;

    public Pelilauta() {
        this.lauta = new Nappula[8][8];
        this.alkuAsetelma();
        this.mustat = new ArrayList<Nappula>();
        this.valkoiset = new ArrayList<Nappula>();
        this.paivitaPelaajienNappulat();

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

    private void alkuAsetelma() {
        //valkoiset:
        new Torni(VALKOINEN, 0, 0, this);
        new Torni(VALKOINEN, 7, 0, this);
        new Kunkku(VALKOINEN, 3, 0, this);
        //mustat:
        new Torni(MUSTA, 0, 7, this);
        new Torni(MUSTA, 7, 7, this);
        new Kunkku(MUSTA, 3, 7, this);
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

        this.paivitaPelaajienNappulat();
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

    public boolean onkoUhattuna(int x, int y, Maa oma) {
        ArrayList<Nappula> vastustajanNappulat;
        if (oma == MUSTA) {
            vastustajanNappulat = this.valkoiset;
        } else {
            vastustajanNappulat = this.mustat;
        }
        for (Nappula nappula : vastustajanNappulat) {
            if (nappula.tarkistaReitti(x, y)) {
                return true;
            }
        }

        return false;

    }

    public Kunkku getMustaKunkku() {
        return mustaKunkku;
    }

    public void setMustaKunkku(Kunkku mustaKunkku) {
        this.mustaKunkku = mustaKunkku;
    }

    public Kunkku getValkonenKunkku() {
        return valkonenKunkku;
    }

    public void setValkonenKunkku(Kunkku valkonenKunkku) {
        this.valkonenKunkku = valkonenKunkku;
    }

    public void paivitaPelaajienNappulat() {
        this.mustat.clear();
        this.valkoiset.clear();
        for (int y = 0; y < this.getSize(); y++) {
            for (int x = 0; x < this.getSize(); x++) {
                if (haeNappula(x, y) != null) {
                    if (this.haeNappula(x, y).getMaa() == MUSTA) {
                        this.mustat.add(this.haeNappula(x, y));
                    } else {
                        this.valkoiset.add(this.haeNappula(x, y));
                    }
                }
            }
        }
    }

    public Nappula getAktiivinen() {
        return aktiivinen;
    }

    public void setAktiivinen(Nappula aktiivinen) {
        this.aktiivinen = aktiivinen;
    }

}
