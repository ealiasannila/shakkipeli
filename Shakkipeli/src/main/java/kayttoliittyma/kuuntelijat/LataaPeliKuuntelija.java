/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.PeliPiirto;
import kayttoliittyma.TiedostoValitsinLataus;
import kayttoliittyma.TiedostoValitsinTallennus;
import logiikka.peli.Peli;
import logiikka.peli.PeliHallinta;

/**
 *Kuuntelee päävalikon Lataa peli nappia
 * @author elias
 */
public class LataaPeliKuuntelija extends KayttoliittymanTuntevaLuokka implements ActionListener {

    public LataaPeliKuuntelija(Kayttoliittyma kayttoliittyma) {
        super(kayttoliittyma);
    }

    /**
     * avaa uuden tiedostovalitsin lataus ikkunan
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.kayttoliittyma.getPeliPiirto().sotilaanKorotusOnKesken()) {
            return;
        }

        SwingUtilities.invokeLater(new TiedostoValitsinLataus(this.kayttoliittyma));
        this.kayttoliittyma.getPeliHallinta().getPeli().getAjastin().pysayta();
        this.kayttoliittyma.getFrame().repaint();

    }

}
