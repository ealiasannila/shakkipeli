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
import kayttoliittyma.UusiPeliValikko;
import logiikka.peli.Peli;
import logiikka.peli.PeliHallinta;

/**
 * Kuuntelee päävalikossa olevaa Uusi peli nappia
 *
 * @author elias
 */
public class UusiPeliKuuntelija extends KayttoliittymanTuntevaLuokka implements ActionListener {

    public UusiPeliKuuntelija(Kayttoliittyma kayttoliittyma) {
        super(kayttoliittyma);
    }

    /**
     * pyytää PeliHallintaa lataamaan uuden pelin
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.kayttoliittyma.getPeliPiirto().sotilaanKorotusOnKesken()) {
            return;
        }
        SwingUtilities.invokeLater(new UusiPeliValikko(this.kayttoliittyma));

    }

}
