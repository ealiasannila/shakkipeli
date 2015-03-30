/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Logiikan ylin elementti. Vastaa uuden pelin käynnistämisestä, sekä
 * tallennuksesta ja latauksesta
 *
 */
public class PeliHallinta {

    private Peli peli;

    public PeliHallinta() {
        this.peli = new Peli();
        this.uusiPeli();
    }

    /**
     * lataa tiedostoon tallennetun uuden pelin pohjan
     */
    public void uusiPeli() {
        this.lataaPeli("tallennetutPelit/uusi.txt");
    }

    /**
     * lataa annetun nimisessä tiedostossa olevan pelin
     *
     * @param tiedostonNimi
     */
    public void lataaPeli(String tiedostonNimi) {
        File peli = new File(tiedostonNimi);
        try {
            this.peli.lataaPeli(peli);
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt: " + tiedostonNimi);
        }

    }

    /**
     * Tallentaa pelin annetun nimiseen tiedostoon
     * peli esitetään ascii lautana
     * @param tiedostonNimi 
     */

    public void tallennaPeli(String tiedostonNimi) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(tiedostonNimi, "UTF-8");
        } catch (FileNotFoundException ex) {
            return;
        } catch (UnsupportedEncodingException ex) {
            return;
        }
        writer.print(this.peli.toString());

        writer.close();
    }

    public Peli getPeli() {
        return peli;
    }

}
