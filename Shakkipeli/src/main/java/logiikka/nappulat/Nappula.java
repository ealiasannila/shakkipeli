/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import logiikka.pelilauta.Pelilauta;

/**
 *
 * @author elias
 */
public abstract class Nappula {

    private Maa maa;
    private Pelilauta lauta;
    private int x;
    private int y;

    public Nappula(Maa maa, int x, int y, Pelilauta lauta) {
        this.maa = maa;
        this.lauta = lauta;
        this.getLauta().asetaNappula(this, x, y);
    }

    public boolean tarkistaSiirto(int x, int y) {

        if (x < 0 || y < 0 || x > 8 || y > 8) {
            System.out.println("SIIRTO LAUDAN ULKOPUOLELLE");
            return false;
        } else if (this.getX() == x && this.getY() == y) {
            System.out.println("PITÄÄ LIIKKUA");
            return false;
        } else if (this.tarkistaOnkoKohdeOma(x, y)) { //Kohde ruudussa oma nappula
            System.out.println("KOHDE OMA");
            return false;
        } else if (!this.tarkistaReitti(x, y)) { //Siirto ei sallittu
            return false;

        }

        return true;

    }

    public abstract boolean tarkistaReitti(int x, int y);

    public boolean tarkistaOnkoKohdeOma(int x, int y) {
        if (!this.tarkistaOnkoKohdeVapaa(x, y)) {
            if (this.getLauta().haeNappula(x, y).getMaa() == this.getMaa()) {
                return true;
            }
        }
        return false;
    }

    public boolean tarkistaOnkoKohdeVastustajan(int x, int y) {
        if (!this.tarkistaOnkoKohdeVapaa(x, y)) {
            if (this.getLauta().haeNappula(x, y).getMaa() != this.getMaa()) {
                return true;
            }
        }
        return false;
    }

    public boolean tarkistaOnkoKohdeVapaa(int x, int y) {
        return this.getLauta().haeNappula(x, y) == null;

    }

    public Maa getMaa() {
        return maa;
    }

    public void setMaa(Maa maa) {
        this.maa = maa;
    }

    public Pelilauta getLauta() {
        return lauta;
    }

    public void setLauta(Pelilauta lauta) {
        this.lauta = lauta;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
