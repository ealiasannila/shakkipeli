/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import logiikka.peli.Maa;
import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.KuningatarPiirto;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 *Liikkuu kuin torni tai lähetti, käyttää samoja apumetodeja NappulaApumetodeja luokasta
 * @author elias
 */
public class Kuningatar extends Nappula {

    public Kuningatar(Maa maa, int x, int y, Pelilauta lauta) {
        super(maa, x, y, lauta);
        this.piirto = new KuningatarPiirto();
    }

    @Override
    protected boolean sallittuLiikkumisTapa(int x, int y) {
        return NappulaApumetodeja.onSamallaPystyTaiVaakaRivilla(x, y, this.getX(), this.getY()) || NappulaApumetodeja.onSamallaVinoRivilla(x, y, this.getX(), this.getY());
    }

    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {
        if (NappulaApumetodeja.onSamallaVinoRivilla(x, y, this.getX(), this.getY())) {
            return NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(x, y, this);
        } else {
            return NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(x, y, this);
        }
    }

    @Override
    public ArrayList<Ruutu> uhkausLinja(int x, int y) {
        if (!this.tarkistaReitti(x, y)) {//jos ei voi ylipäätänsä uhata ruutua ei silloin linjalla ole ruutuja
            return null;
        }
        ArrayList<Ruutu> uhatutRuudut = new ArrayList<>();
        uhatutRuudut.addAll(NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(x, y, this));
        uhatutRuudut.addAll(NappulaApumetodeja.uhkausLinjaVino(x, y, this));
        return uhatutRuudut;
    }

    @Override
    public ArrayList<Ruutu> mahdollisetRuudut() {
        ArrayList<Ruutu> ruudut = new ArrayList<Ruutu>();
        ruudut.addAll(NappulaApumetodeja.mahdollisetPystyTaiVaakaRuudut(this.getX(), this.getY()));
        ruudut.addAll(NappulaApumetodeja.mahdollisetVinoRuudut(this.getX(), this.getY()));
        return ruudut;
    }

    @Override
    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "q";
        } else {
            return "Q";
        }
    }

}
