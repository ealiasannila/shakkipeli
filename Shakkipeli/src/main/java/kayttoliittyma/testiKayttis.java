/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.util.Scanner;
import logiikka.peli.Peli;

/**
 *
 * @author elias
 */
public class testiKayttis {

    private Peli peli;
    private Scanner lukija;

    public testiKayttis() {
        this.peli = new Peli();
        this.lukija = new Scanner(System.in);
    }

    public void pelaa() {
        while (!this.peli.onMatissa()) {
            this.peli.tulosta();

            while (peli.getAktiivinen() == null) {
                System.out.println("Valitse nappulan x:");
                int x = Integer.parseInt(lukija.nextLine());

                System.out.println("Valitse nappulan y:");
                int y = Integer.parseInt(lukija.nextLine());
                this.peli.asetaAktiivinen(x, y);
            }

            boolean siirtoOnnistui = false;
            while (!siirtoOnnistui) {
                System.out.println("Anna kohde x:");
                int kohdeX = Integer.parseInt(lukija.nextLine());
                System.out.println("Anna kohde y:");
                int kohdeY = Integer.parseInt(lukija.nextLine());

                siirtoOnnistui = this.peli.siirto(kohdeX, kohdeY);
            }
        }
        
        this.peli.tulosta();
        System.out.println("HAH HÄVISIT " + peli.getVuorossa() + "!");
        
    }

}
