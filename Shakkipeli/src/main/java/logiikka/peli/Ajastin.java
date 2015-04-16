/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author elias
 */
public class Ajastin {

    private Peli peli;
    private Timer ajastin;
    private TimerTask ajastinTehtava;

    public Ajastin(Peli annettuPeli) {
        this.peli = annettuPeli;
        this.ajastin = new Timer();
        this.ajastinTehtava = new TimerTask() {
            @Override
            public void run() {

                if (!peli.getVuorossa().getKello().paivita()) {
                    this.cancel();

                }

            }
        };

    }

    public void kaynnista() {
        this.ajastin.schedule(ajastinTehtava, 1000, 1000);
    }

    public void pysayta() {
        this.ajastin.cancel();
        this.ajastin.purge();
    }

}
