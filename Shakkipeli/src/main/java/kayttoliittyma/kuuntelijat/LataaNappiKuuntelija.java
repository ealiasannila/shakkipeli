/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.TiedostoValitsinLataus;
import kayttoliittyma.kuuntelijat.Kuuntelija;

/**
 *
 * @author elias
 */
public class LataaNappiKuuntelija extends Kuuntelija implements ActionListener {

    private JTextArea tekstiKentta;
    private TiedostoValitsinLataus tiedostoValitsin;

    public LataaNappiKuuntelija(Kayttoliittyma kayttoliittyma, JTextArea tiedostonNimi, TiedostoValitsinLataus tiedostonValitsin) {
        super(kayttoliittyma);
        this.tiedostoValitsin = tiedostonValitsin;
        this.tekstiKentta = tiedostonNimi;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String polku = "tallennetutPelit/omat/" + this.tekstiKentta.getText();

        this.kayttoliittyma.getPeliHallinta().lataaPeli(polku);
        this.tiedostoValitsin.getRuutu().setVisible(false);
        this.tiedostoValitsin.getRuutu().dispose();
        this.kayttoliittyma.getPeliPiirto().repaint();

    }

}
