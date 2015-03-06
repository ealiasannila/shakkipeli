/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.peli.Peli;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 *
 * @author elias
 */
public class Kunkku extends Nappula {

    private Peli peli; //kunkku tarvitsee peliä, koska ei saa liikkua uhattuun ruutuun

    public Kunkku(Maa maa, int x, int y, Pelilauta pelilauta, Peli peli) {
        super(maa, x, y, pelilauta);
        this.peli = peli;

    }

    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "[k]";
        } else {
            return "[K]";
        }
    }

    @Override
    public boolean onSallittuSiirto(int x, int y) {

        if(this.peli.onkoUhattuna(x, y)){
            //System.out.println("on uhattuna ja olen kunkku");
            return false;
        }
        
        if (x < 0 || y < 0 || x > this.getLauta().getSize() || y > this.getLauta().getSize()) {
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

    @Override
    public boolean tarkistaReitti(int x, int y) {

        if (Math.abs(this.getX() - x) > 1 || Math.abs(this.getY() - y) > 1) {
            //    System.out.println("LIIAN KAUKANA");
            return false;
        }

        return true;
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

}
