/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.util.ArrayList;
import logiikka.nappulat.Kunkku;
import logiikka.nappulat.Maa;
import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.nappulat.Nappula;

/**
 *
 * @author elias
 */
public class Pelaaja {

    private Kunkku kunkku;
    private int ohestaLyontiX;
    private int ohestaLyontiY;
    private int lyotyY;
    private Maa maa;
    private Nappula korotettava;

    public Nappula getKorotettava() {
        return korotettava;
    }

    public void setKorotettava(Nappula korotettava) {
        this.korotettava = korotettava;
    }

    public Pelaaja(Maa maa) {
        this.maa = maa;
        this.nappulat = new ArrayList<Nappula>();
        this.ohestaLyontiX = -1;
        if (maa == VALKOINEN) {
            this.ohestaLyontiY = 2;
            this.lyotyY = 3;
        } else {
            this.ohestaLyontiY = 5;
            this.lyotyY = 4;
        }
    }

    public int getLyotyY() {
        return lyotyY;
    }

    public int getOhestaLyontiY() {
        return ohestaLyontiY;
    }

    public int getOhestaLyontiX() {
        return ohestaLyontiX;
    }

    public void setOhestaLyontiX(int ohestaLyonti) {
        this.ohestaLyontiX = ohestaLyonti;
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

    public String toString() {
        return this.getMaa().toString();
    }
}
