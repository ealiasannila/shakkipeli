/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import logiikka.nappulat.Kunkku;
import static logiikka.nappulat.Maa.MUSTA;
import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.nappulat.Nappula;
import logiikka.nappulat.Torni;

/**
 *
 * @author elias
 */
public class Peli {

    private Pelilauta lauta;
    private Pelaaja musta;
    private Pelaaja valkoinen;
    private Pelaaja vuorossa;
    private Nappula aktiivinen;

    public Peli() {
        this.valkoinen = new Pelaaja(VALKOINEN);
        this.musta = new Pelaaja(MUSTA);
        this.uusiPeli();
    }

    private void alkuAsetelma() {
        //valkoiset:
        new Torni(VALKOINEN, 0, 0, this.lauta);
        new Torni(VALKOINEN, 7, 0, this.lauta);
        this.valkoinen.setKunkku(new Kunkku(VALKOINEN, 3, 0, this.lauta));

        //mustat:
        this.musta.setKunkku(new Kunkku(MUSTA, 3, 7, this.lauta));
        new Torni(MUSTA, 7, 7, this.lauta);
        new Torni(MUSTA, 0, 7, this.lauta);
    }

    public void paivitaPelaajienNappulat() {

        this.musta.getNappulat().clear();
        this.valkoinen.getNappulat().clear();
        for (int y = 0; y < this.lauta.getSize(); y++) {
            for (int x = 0; x < this.lauta.getSize(); x++) {
                if (this.lauta.haeNappula(x, y) != null) {
                    if (this.lauta.haeNappula(x, y).getMaa() == MUSTA) {
                        this.musta.getNappulat().add(this.lauta.haeNappula(x, y));
                    } else {
                        this.valkoinen.getNappulat().add(this.lauta.haeNappula(x, y));
                    }
                }
            }
        }
    }

    public boolean onkoUhattuna(int x, int y) {
        if (this.vuorossa.getMaa() == MUSTA) {
            return this.lauta.onkoUhattuna(x, y, this.valkoinen.getNappulat());
        } else {
            return this.lauta.onkoUhattuna(x, y, this.musta.getNappulat());
        }
    }

    public boolean myosKunkkuaYmparoivatRuudutUhattuna() {  //MITEN TARKISTETAAN VOIKO MENNÄ VÄLIIN/SYÖDÄ? EHKÄ REITTIÄ KUKA UHKAA-mitkä ruudut välillä, voiko joku oma nappi mennä väliin?
        for (int y = this.vuorossa.getKunkku().getY() - 1; y < this.vuorossa.getKunkku().getY() + 1; y++) {
            for (int x = this.vuorossa.getKunkku().getX() - 1; x < this.vuorossa.getKunkku().getX() + 1; x++) {
                if (!this.onkoUhattuna(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean onShakissa() {
        return onkoUhattuna(this.vuorossa.getKunkku().getX(), this.vuorossa.getKunkku().getY());
    }

    public void uusiPeli() {
        this.lauta = new Pelilauta();
        this.alkuAsetelma();
        this.paivitaPelaajienNappulat();
        this.vuorossa = this.valkoinen;
    }

    public void siirto(int x, int y) {
        if(this.aktiivinen==null){
            System.out.println("valitse nappula");
            return;
        }
        
        if(!this.lauta.teeSiirto(x, y, this.aktiivinen)){
            return;
        }
        
        if (this.vuorossa == this.valkoinen) {
            this.vuorossa = this.musta;
        } else {
            this.vuorossa = this.valkoinen;
        }
        this.paivitaPelaajienNappulat();
        this.aktiivinen=null;

    }

    public void asetaAktiivinen(int x, int y) {

        if (this.lauta.haeNappula(x, y) != null) {
            if (this.lauta.haeNappula(x, y).getMaa() == this.vuorossa.getMaa()) {
                this.aktiivinen = this.lauta.haeNappula(x, y);
            } else {
                System.out.println("et voi siirtää vastustajan nappulaa");
            }
        }
    }

    public Nappula getAktiivinen() {
        return aktiivinen;
    }

    public void tulosta() {
        System.out.println("Vuorossa: " + this.vuorossa.toString());
        System.out.println("");
        this.lauta.tulosta();
        System.out.println("");
    }

}
