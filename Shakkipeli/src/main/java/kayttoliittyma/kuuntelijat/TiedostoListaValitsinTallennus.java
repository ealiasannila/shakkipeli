/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import kayttoliittyma.TiedostoValitsinTallennus;

/**
 *
 * @author elias
 */
public class TiedostoListaValitsinTallennus implements ListSelectionListener {

    private TiedostoValitsinTallennus tiedostoValitsinTallennus;
    
    public TiedostoListaValitsinTallennus(TiedostoValitsinTallennus tiedostoValitsinTallennus) {
        this.tiedostoValitsinTallennus = tiedostoValitsinTallennus;
    }

    
    @Override
    public void valueChanged(ListSelectionEvent lse) {
      String valittu = (String) this.tiedostoValitsinTallennus.getTiedostoLista().getSelectedValue();
    
      this.tiedostoValitsinTallennus.getTiedostonNimi().setText(valittu);
      
    }
    
}
