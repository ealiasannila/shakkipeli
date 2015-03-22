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
import kayttoliittyma.TiedostoValitsinTallennus;

/**
 *
 * @author elias
 */
public class TallennaNappiKuuntelija extends Kuuntelija implements ActionListener {

    private JTextArea tekstiKentta;
    TiedostoValitsinTallennus tiedostoValitsin;

    public TallennaNappiKuuntelija(Kayttoliittyma kayttoliittyma, JTextArea tekstiKentta, TiedostoValitsinTallennus tiedostoValitsin) {
        super(kayttoliittyma);
        this.tekstiKentta = tekstiKentta;
        this.tiedostoValitsin = tiedostoValitsin; 
       
    }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
        String polku = "tallennetutPelit/omat/"+this.tekstiKentta.getText();
        
        this.kayttoliittyma.getPeliHallinta().tallennaPeli(polku);
        this.tiedostoValitsin.getRuutu().setVisible(false);
        this.tiedostoValitsin.getRuutu().dispose();
        this.kayttoliittyma.getPeliPiirto().repaint();

        
    }
    
}
