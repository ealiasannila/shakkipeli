/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import javax.swing.JLabel;

/**
 *
 * @author elias
 */
public class KelloPiirto extends JLabel {

    private Kayttoliittyma kayttoliittyma;
    private String perusOsa;

    public KelloPiirto(Kayttoliittyma kayttoliittyma, int aika, String perusOsa) {
        this.perusOsa = perusOsa;
        this.kayttoliittyma = kayttoliittyma;
        this.asetaAika(aika);
    }

    private void asetaAika(int aika) {
        if (aika != -1) {
            this.setText(this.perusOsa + (aika / 60) + "min " + (aika % 60) + "s");
        } else {
            this.setText("Kello ei käytössä");
        }
        this.repaint();
    }

    public void paivita(int aika) {
        this.asetaAika(aika);
        this.kayttoliittyma.getPeliPiirto().repaint();
    }

}
