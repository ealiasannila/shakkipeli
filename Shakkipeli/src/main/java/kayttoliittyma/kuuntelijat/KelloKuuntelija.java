package kayttoliittyma.kuuntelijat;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import kayttoliittyma.UusiPeliValikko;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elias
 */
public class KelloKuuntelija implements ItemListener {

    private UusiPeliValikko valikko;

    public KelloKuuntelija(UusiPeliValikko valikko) {
        this.valikko = valikko;
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange() == ItemEvent.DESELECTED) {
            this.valikko.setOnkoKello(false);
        } else {
            this.valikko.setOnkoKello(true);
        }
    }

}
