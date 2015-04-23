/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import kayttoliittyma.kuuntelijat.TallennaNappiKuuntelija;

/**
 * Tallennusikkuna
 *
 * @author elias
 */
public class TiedostoValitsinTallennus extends TiedostoValitsin implements Runnable {

    public TiedostoValitsinTallennus(Kayttoliittyma kayttolittyma) {
        super(kayttolittyma);
    }

    @Override
    public void run() {
        super.run();
        ruutu.setTitle("Tallenna peli");

        ruutu.pack();
        ruutu.setVisible(true);
    }

    @Override
    protected void luoKomponentit(Container container) {
        super.luoKomponentit(container);
        nappi.addActionListener(new TallennaNappiKuuntelija(this.kayttoliittyma, tiedostonNimi, this));
        nappi.setText("Tallenna");

        container.add(tiedostonNimi, BorderLayout.NORTH);
        container.add(nappi, BorderLayout.SOUTH);

    }

}
