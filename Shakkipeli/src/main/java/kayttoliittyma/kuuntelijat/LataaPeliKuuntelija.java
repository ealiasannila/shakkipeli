/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittyma.PeliPiirto;
import logiikka.peli.Peli;

/**
 *
 * @author elias
 */
public class LataaPeliKuuntelija extends Kuuntelija implements ActionListener {

    public LataaPeliKuuntelija(Peli peli, PeliPiirto peliPiirto) {
        super(peli, peliPiirto);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.peli.lataaPeli();
        this.peliPiirto.repaint();
    }

}
