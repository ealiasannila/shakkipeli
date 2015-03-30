/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import logiikka.peli.Maa;
import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.HaamuSotilasPiirto;
import static logiikka.peli.Maa.HAAMU;
import static logiikka.peli.Maa.MUSTA;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 *
 * @author elias
 */
public class HaamuSotilas extends Nappula {

    private Sotilas kenenHaamu;

    public HaamuSotilas(Maa maa, int x, int y, Pelilauta lauta) {
        super(maa, x, y, lauta);
        this.setMaa(HAAMU);
        this.piirto = new HaamuSotilasPiirto();

    }

    @Override
    protected boolean sallittuLiikkumisTapa(int x, int y) {
        return false; //Haamusotilasta ei voi liikuttaa.
    }

    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {
        return true; //Haamusotilasta ei voi liikuttaa.
    }

    @Override
    public ArrayList<Ruutu> uhkausLinja(int x, int y) {
        return new ArrayList<Ruutu>();
    }

    public String toString() {
        return "h";

    }

    @Override
    public ArrayList<Ruutu> mahdollisetRuudut() {
        return new ArrayList<Ruutu>();

    }

}
