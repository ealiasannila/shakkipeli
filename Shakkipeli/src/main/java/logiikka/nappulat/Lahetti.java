/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import logiikka.peli.Maa;
import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.LahettiPiirto;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 * Lähetti liikkuu vinottain
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
        return NappulaApumetodeja.onSamallaVinoRivilla(x, y, this.getX(), this.getY());
    }

    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {
        return NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(x, y, this);
    }

    @Override
    public ArrayList<Ruutu> uhkausLinja(int x, int y) {
        return NappulaApumetodeja.uhkausLinjaVino(x, y, this);
    }

    @Override
    public ArrayList<Ruutu> mahdollisetRuudut() {
        return NappulaApumetodeja.mahdollisetVinoRuudut(this.getX(), this.getY());
    }

    @Override
    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "l";
        } else {
            return "L";
        }
    }

}
