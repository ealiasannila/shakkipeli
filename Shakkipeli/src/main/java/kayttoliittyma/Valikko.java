/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import kayttoliittyma.kuuntelijat.KayttoliittymanTuntevaLuokka;

/**
 * Valikon toteuttavat luokat pysäyttävät kellon kun ne käynnistetään, ja jos ne
 * suljetaan, käynnistetään kellot uudestaan.
 *
 * @author elias
 */
class Valikko extends KayttoliittymanTuntevaLuokka implements Runnable {

    protected JFrame ruutu;

    public Valikko(Kayttoliittyma kayttoliittyma) {
        super(kayttoliittyma);
    }

    /**
     * pysäytetään kellot run metodin alussa, ja lisätään sulkeutumiskuuntelija,
     * joka käynnistää kellot uudelleen, kun ikkuna suljetaan
     */
    @Override
    public void run() {
        this.kayttoliittyma.getPeliHallinta().getPeli().getAjastin().pysayta();
        this.kayttoliittyma.setValikkoAuki(true);
        ruutu.setAlwaysOnTop(true);

        WindowListener exitKuuntelija = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                
                kayttoliittyma.getPeliHallinta().getPeli().asetaKellot(
                        kayttoliittyma.getPeliHallinta().getPeli().getValkoinen().getKello().getAika(),
                        kayttoliittyma.getPeliHallinta().getPeli().getMusta().getKello().getAika());
                ruutu.dispose();
                kayttoliittyma.setValikkoAuki(false);
            }
        };
        ruutu.addWindowListener(exitKuuntelija);

    }

}
