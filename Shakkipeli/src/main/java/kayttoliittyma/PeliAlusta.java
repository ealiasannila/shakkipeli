/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author elias
 */
public class PeliAlusta extends JPanel {

    public PeliAlusta() {
        super.setBackground(Color.RED);
    }

    private void piirraRuudut(Graphics graphics) {
        int sivunPituus = 60;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((j+i)%2==0){
                    graphics.setColor(Color.WHITE);
                }else{
                    graphics.setColor(Color.BLACK);
                }
                graphics.fillRect(j*sivunPituus, i*sivunPituus, sivunPituus, sivunPituus);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        
        super.paintComponent(graphics);
        this.piirraRuudut(graphics);
    }
}
