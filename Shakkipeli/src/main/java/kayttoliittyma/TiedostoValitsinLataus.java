/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import kayttoliittyma.kuuntelijat.LataaNappiKuuntelija;

/**
 *
 * @author elias
 */
public class TiedostoValitsinLataus extends TiedostoValitsin implements Runnable {

    public TiedostoValitsinLataus(Kayttoliittyma kayttolittyma) {
        super(kayttolittyma);
    }

    @Override
    public void run() {
        super.run();
        ruutu.setTitle("Lataa peli");
        ruutu.pack();
        ruutu.setVisible(true);
    }

    @Override
    protected void luoKomponentit(Container container) {
        super.luoKomponentit(container);
        tiedostonNimi.setEditable(false);

        nappi.setText("Lataa");
        nappi.addActionListener(new LataaNappiKuuntelija(this.kayttoliittyma, tiedostonNimi, this));

        container.add(tiedostonNimi, BorderLayout.NORTH);
        container.add(nappi, BorderLayout.SOUTH);

    }
}
