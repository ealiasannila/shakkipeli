/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.PeliPiirto;
import logiikka.peli.Peli;
import logiikka.peli.PeliHallinta;

/**
 *
 * @author elias
 */
public class LataaPeliKuuntelija extends Kuuntelija implements ActionListener {

    public LataaPeliKuuntelija(Kayttoliittyma kayttoliittyma) {
        super(kayttoliittyma);
    }




    @Override
    public void actionPerformed(ActionEvent ae) {
        this.kayttoliittyma.getPeliHallinta().lataaPeli();
        this.kayttoliittyma.getPeliPiirto().repaint();
    }

}
