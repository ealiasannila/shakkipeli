/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

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

    public boolean kohdeLaudanUlkopuolella(int x, int y) {
        return x < 0 || y < 0 || x > this.getLauta().getSize() - 1 || y > this.getLauta().getSize() - 1;
    }

    public boolean kohdeSamaKuinOmaSijainti(int x, int y) {     
        return this.getX() == x && this.getY() == y;
    }

    public boolean onSallittuSiirto(int x, int y) {
        if (this.kohdeLaudanUlkopuolella(x, y)) {
            return false;
        } else if (this.tarkistaOnkoKohdeOma(x, y)) { //Kohde ruudussa oma nappula
            return false;
        } else if (!this.tarkistaReitti(x, y)) { //Siirto ei sallittu
            return false;
        }
        return true;

    }

    public abstract boolean tarkistaReitti(int x, int y);

    public abstract ArrayList<Ruutu> uhkausLinja(int x, int y);

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
