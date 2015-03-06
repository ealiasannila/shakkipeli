/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.util.ArrayList;
import logiikka.nappulat.Kunkku;
import logiikka.nappulat.Maa;
import logiikka.nappulat.Nappula;


/**
 *
 * @author elias
 */
public class Pelaaja {
    private Kunkku kunkku;
    private Maa maa;

    public Pelaaja(Maa maa) {
        this.maa = maa;
        this.nappulat = new ArrayList<Nappula>();
    }
    
    

    public Maa getMaa() {
        return maa;
    }

    public void setMaa(Maa maa) {
        this.maa = maa;
    }

    public Kunkku getKunkku() {
        return kunkku;
    }

    public void setKunkku(Kunkku kunkku) {
        this.kunkku = kunkku;
    }

    public ArrayList<Nappula> getNappulat() {
        return nappulat;
    }

    public void setNappulat(ArrayList<Nappula> nappulat) {
        this.nappulat = nappulat;
    }
    private ArrayList<Nappula> nappulat;
    
    public String toString(){
        return this.getMaa().toString();
    }
}
