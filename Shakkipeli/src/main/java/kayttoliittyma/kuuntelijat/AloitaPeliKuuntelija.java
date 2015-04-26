/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.UusiPeliValikko;

/**
 *
 * @author elias
 */
public class AloitaPeliKuuntelija extends KayttoliittymanTuntevaLuokka implements ActionListener {

    private UusiPeliValikko valikko;

    public AloitaPeliKuuntelija(Kayttoliittyma kayttoliittyma, UusiPeliValikko valikko) {
        super(kayttoliittyma);
        this.valikko = valikko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (this.valikko.getOnkoKello()) {
            if (this.valikko.getAikaKentta().getValue() == null) {
                return;
            }
            int aika;
            try {
                aika = Integer.parseInt(this.valikko.getAikaKentta().getValue().toString().trim()) * 60;
            } catch (NumberFormatException e) {
                return;
            }
            this.kayttoliittyma.getPeliHallinta().uusiPeli(aika, aika);

        } else {
            this.kayttoliittyma.getPeliHallinta().uusiPeli(-1, -1);

        }
        this.kayttoliittyma.setValikkoAuki(false);
        this.kayttoliittyma.getPeliPiirto().repaint();
        this.valikko.getRuutu().dispose();

    }

}
