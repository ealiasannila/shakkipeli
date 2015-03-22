/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.PeliPiirto;
import kayttoliittyma.TiedostoValitsinTallennus;
import logiikka.peli.Peli;
import logiikka.peli.PeliHallinta;

/**
 *
 * @author elias
 */
public class TallennaPeliKuuntelija extends Kuuntelija implements ActionListener {

    
    public TallennaPeliKuuntelija(Kayttoliittyma kayttoliittyma) {
        super(kayttoliittyma);
    }




    @Override
    public void actionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(new TiedostoValitsinTallennus(this.kayttoliittyma));
        
        this.kayttoliittyma.getFrame().repaint();
    }

}
