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
        this.uusiPeli(-1, -1);
    }

    /**
     * lataa tiedostoon tallennetun uuden pelin pohjan
     */
    public void uusiPeli(int valkoisenAika, int mustanAika) {
        this.peli.lataaPeli(new Scanner(
                "VALKOINEN\n"
                + "TRLQKLRT\n"
                + "SSSSSSSS\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "ssssssss\n"
                + "trlqklrt\n"
                + valkoisenAika + "\n"
                + mustanAika));

    }

    /**
     * lataa annetun nimisessä tiedostossa olevan pelin
     *
     * @param tiedostonNimi
     */
    public void lataaPeli(String tiedostonNimi) {
        File peli = new File(tiedostonNimi);
        Scanner lukija = null;
        try {
            lukija = new Scanner(peli);
        } catch (FileNotFoundException ex) {
            this.uusiPeli(-1, -1);
            return;
        }
        StringBuilder peliString = new StringBuilder();
        while (lukija.hasNext()) {
            peliString.append(lukija.nextLine() + "\n");
        }
        this.peli.lataaPeli(new Scanner(peliString.toString()));

    }

    /**
     * Tallentaa pelin annetun nimiseen tiedostoon peli esitetään ascii lautana
     *
     * @param tiedostonNimi
     */
    public boolean tallennaPeli(String tiedostonNimi) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(tiedostonNimi.trim(), "UTF-8");

            writer.print(this.peli.toString());

            writer.close();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (UnsupportedEncodingException ex) {
            return false;
        }
    }

    public Peli getPeli() {
        return peli;
    }

}
