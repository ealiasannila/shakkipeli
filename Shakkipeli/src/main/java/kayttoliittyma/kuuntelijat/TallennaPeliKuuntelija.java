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
import kayttoliittyma.TiedostoValitsinTallennus;

/**
 * Kuuntelee pääikkunassa olevaa Tallenna peli nappia
 *
 * @author elias
 */
public class TallennaPeliKuuntelija extends KayttoliittymanTuntevaLuokka implements ActionListener {

    public TallennaPeliKuuntelija(Kayttoliittyma kayttoliittyma) {
        super(kayttoliittyma);
    }

    /**
     * kun Tallenna Peli nappia painetaan pyydetään näyttämään
     * TiedostValitsinTallennus ikkuna
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.kayttoliittyma.getPeliPiirto().sotilaanKorotusOnKesken()) {
            return;
        }

        this.kayttoliittyma.getPeliHallinta().getPeli().getAjastin().pysayta();
        SwingUtilities.invokeLater(new TiedostoValitsinTallennus(this.kayttoliittyma));

        this.kayttoliittyma.getFrame().repaint();
    }

}
