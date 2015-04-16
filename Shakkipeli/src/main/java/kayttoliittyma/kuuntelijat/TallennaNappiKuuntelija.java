/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.TiedostoValitsinTallennus;

/**
 * Kuuntelee tallenna nappia tallennusikkunassa
 *
 * @author elias
 */
public class TallennaNappiKuuntelija extends KayttoliittymanTuntevaLuokka implements ActionListener {

    private JTextField tekstiKentta;
    TiedostoValitsinTallennus tiedostoValitsin;

    public TallennaNappiKuuntelija(Kayttoliittyma kayttoliittyma, JTextField tekstiKentta, TiedostoValitsinTallennus tiedostoValitsin) {
        super(kayttoliittyma);
        this.tekstiKentta = tekstiKentta;
        this.tiedostoValitsin = tiedostoValitsin;

    }

    /**
     * pyytää pelihallintaa tallentamaan pelin ja sulkee tallennusikkunan
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        String polku = "tallennetutPelit/omat/" + this.tekstiKentta.getText();

        if (this.kayttoliittyma.getPeliHallinta().tallennaPeli(polku)) {
            this.kayttoliittyma.getPeliHallinta().lataaPeli(polku);
            this.tiedostoValitsin.getRuutu().dispose();
            this.kayttoliittyma.getPeliPiirto().repaint();
        }

    }

}
