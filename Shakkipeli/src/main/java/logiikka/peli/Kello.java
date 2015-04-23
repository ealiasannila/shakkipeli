/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import kayttoliittyma.KelloPiirto;

/**
 * Kello pitää kirjaa pelaajan jäljellä olevasta ajasta, ja vähentää sitä
 * ajastimen kutsuessa paivita metodia
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

    /**
     * asettaa kellolle sen piirtoelementin. Kello pyytää paivita() metodissa
     * myös kellon piirtoa paivittamaan itsensa
     *
     * @param kellonPiirto
     */
    public void setKellonPiirto(KelloPiirto kellonPiirto) {
        this.kellonPiirto = kellonPiirto;
    }

    /**
     * asettaa kellolle ajan. Jos kellolla on piirtoelementii, päivitetään sen
     * aika
     *
     * @param alkuaika
     */
    public void aseta(int alkuaika) {
        this.aika = alkuaika;
        if (this.kellonPiirto != null) {
            this.kellonPiirto.paivita(aika);

        }

    }

    /**
     * vähentää kellossa jäljellä olevia sekunteja yhdellä, kunnes aika on nolla
     *
     * @return
     */
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
