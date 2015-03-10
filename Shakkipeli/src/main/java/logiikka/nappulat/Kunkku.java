/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.KunkkuPiirto;
import kayttoliittyma.nappulapiirto.TorniPiirto;
import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.peli.Peli;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 *
 * @author elias
 */
public class Kunkku extends Nappula {

    public Kunkku(Maa maa, int x, int y, Pelilauta pelilauta) {
        super(maa, x, y, pelilauta);
        this.piirto = new KunkkuPiirto();

    }

    @Override
    public boolean tarkistaReitti(int x, int y) {

        return this.sallittuLiikkumisTapa(x, y);
    }

    @Override
    public ArrayList<Ruutu> uhkausLinja(int x, int y) {//kunkku ei voi uhata toista kunkkua, toteuta jos käytät uhkauslinjaa muuhun.
        if (!this.tarkistaReitti(x, y)) {
            System.out.println("Ei uhkaa, mutta kysytään linjaa");
            return null;
        }
        ArrayList<Ruutu> uhatutRuudut = new ArrayList<Ruutu>();
        uhatutRuudut.add(new Ruutu(x, y));
        return uhatutRuudut;
    }

    @Override
    protected boolean sallittuLiikkumisTapa(int x, int y) {
        if (Math.abs(this.getX() - x) > 1 || Math.abs(this.getY() - y) > 1) {
            //    System.out.println("LIIAN KAUKANA");
            return false;
        }
        return true;
    }

    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {
        return true; //kunkku liikkuu vain yhden
    }

    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "k";
        } else {
            return "K";
        }
    }
}
