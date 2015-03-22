/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import kayttoliittyma.TiedostoValitsinLataus;
import kayttoliittyma.TiedostoValitsinTallennus;

/**
 *
 * @author elias
 */
public class TiedostoListaValitsinLatus implements ListSelectionListener {

    private TiedostoValitsinLataus tiedostoValitsinLatautaus;

    public TiedostoListaValitsinLatus(TiedostoValitsinLataus tiedostoValitsinLatautaus) {
        this.tiedostoValitsinLatautaus = tiedostoValitsinLatautaus;
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        String valittu = (String) this.tiedostoValitsinLatautaus.getTiedostoLista().getSelectedValue();

        this.tiedostoValitsinLatautaus.getTiedostonNimi().setText(valittu);

    }

}
