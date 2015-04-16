/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import kayttoliittyma.KelloPiirto;

/**
 *
 * @author elias
 */
public class Kello {

    private KelloPiirto kellonPiirto;
    private int aika;

    public Kello(int alkuaika) {
        this.aika = alkuaika;
        //this.paivita();
    }

    public KelloPiirto getKellonPiirto() {
        return kellonPiirto;
    }

    public void setKellonPiirto(KelloPiirto kellonPiirto) {
        this.kellonPiirto = kellonPiirto;
    }

    public void aseta(int alkuaika) {
        this.aika = alkuaika;
        this.paivita();

    }

    public boolean paivita() {
        if (this.kellonPiirto == null) {
            return false;
        }
        if (this.aika > 0) {

            this.aika--;
        }
        this.kellonPiirto.paivita(aika);

        return true;
    }

    public int getAika() {
        return aika;
    }

    public boolean aikaLoppu() {
        return this.aika == 0;
    }

    @Override
    public String toString() {
        return "" + this.aika;
    }

}
