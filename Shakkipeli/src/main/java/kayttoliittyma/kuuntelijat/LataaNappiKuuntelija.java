/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.TiedostoValitsinLataus;
import kayttoliittyma.kuuntelijat.KayttoliittymanTuntevaLuokka;

/**
 * Kuuntelee latausikkunassa olevaa lataa nappia
 *
 * @author elias
 */
public class LataaNappiKuuntelija extends KayttoliittymanTuntevaLuokka implements ActionListener {

    private JTextField tekstiKentta;
    private TiedostoValitsinLataus tiedostoValitsin;

    public LataaNappiKuuntelija(Kayttoliittyma kayttoliittyma, JTextField tiedostonNimi, TiedostoValitsinLataus tiedostonValitsin) {
        super(kayttoliittyma);
        this.tiedostoValitsin = tiedostonValitsin;
        this.tekstiKentta = tiedostonNimi;
    }

    /**
     * pyytää pelihallintaa lataamaan pelin ja sulkee latausikkunan
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        String polku = "tallennetutPelit/omat/" + this.tekstiKentta.getText();

        this.kayttoliittyma.getPeliHallinta().lataaPeli(polku);
        this.kayttoliittyma.setValikkoAuki(false);
        this.tiedostoValitsin.getRuutu().dispose();
        this.kayttoliittyma.getPeliPiirto().repaint();

    }

}
