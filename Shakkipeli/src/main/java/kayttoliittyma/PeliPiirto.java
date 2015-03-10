/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import kayttoliittyma.nappuloidenPiirto.NappulaPiirto;
import kayttoliittyma.nappuloidenPiirto.TorniPiirto;
import logiikka.nappulat.Nappula;
import logiikka.peli.Peli;

/**
 *
 * @author elias
 */
public class PeliPiirto extends JPanel {
    
    private int sivunPituus;
    private int nappulanKoko;
    private Peli peli;
    
    public PeliPiirto(int sp, Peli peli) {
        super.setBackground(Color.RED);
        
        this.sivunPituus = sp;
        this.nappulanKoko = (int) (this.sivunPituus * 0.7);
        this.peli = peli;
    }
    
    public int getSivunPituus() {
        return sivunPituus;
        
    }
    
    private void piirraRuudut(Graphics graphics) {
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((j + i) % 2 == 0) {
                    graphics.setColor(Color.WHITE);
                } else {
                    graphics.setColor(Color.BLACK);
                }
                graphics.fillRect(j * sivunPituus, i * sivunPituus, sivunPituus, sivunPituus);
            }
        }
    }
    
    public void peliLoppu(Graphics graphics) {
        if (this.peli.onMatissa()) {
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, this.sivunPituus)); 
            graphics.drawString("HÄVISIT " + this.peli.getVuorossa().getMaa(), this.sivunPituus, this.sivunPituus*3);
        }
    }
    
    private void piirraNappulat(Graphics graphics) {
        for (Nappula nappula : this.peli.getMusta().getNappulat()) {
            this.piirraNappula(graphics, nappula);
        }
        
        for (Nappula nappula : this.peli.getValkoinen().getNappulat()) {
            this.piirraNappula(graphics, nappula);
        }
    }
    
    private void piirraNappula(Graphics graphics, Nappula nappula) {
        graphics.setColor(Color.GREEN);
        graphics.drawImage(nappula.getPiirto().haeKuva(nappula.getMaa()),
                nappula.getPiirto().piirtoX(nappula.getX(), this, nappulanKoko),
                nappula.getPiirto().piirtoY(nappula.getY(), this, nappulanKoko),
                nappulanKoko, nappulanKoko, null);
        
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.piirraRuudut(graphics);
        this.piirraNappulat(graphics);
        this.peliLoppu(graphics);
    }
}
