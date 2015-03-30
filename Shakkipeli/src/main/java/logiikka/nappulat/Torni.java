/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import logiikka.peli.Maa;
import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.TorniPiirto;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 *Torni liikkuu pysty tai vaakariveillä
 * @author elias
 */
public class Torni extends Nappula {

    public Torni(Maa maa, int x, int y, Pelilauta pelilauta) {
        super(maa, x, y, pelilauta);
        this.piirto = new TorniPiirto();
    }

    @Override
    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "t";
        } else {
            return "T";
        }
    }

    @Override
    protected boolean sallittuLiikkumisTapa(int x, int y) {
       return NappulaApumetodeja.onSamallaPystyTaiVaakaRivilla(x, y, this.getX(), this.getY());
    }

    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {
        return NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(x, y, this);
    }

    @Override
    public ArrayList<Ruutu> uhkausLinja(int x, int y) { //palauttaa ne ruudut joista uhkauksen voi blokata
       return NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(x, y, this);
    }

    @Override
    public ArrayList<Ruutu> mahdollisetRuudut() {
        return NappulaApumetodeja.mahdollisetPystyTaiVaakaRuudut(this.getX(), this.getY());
    }

}
