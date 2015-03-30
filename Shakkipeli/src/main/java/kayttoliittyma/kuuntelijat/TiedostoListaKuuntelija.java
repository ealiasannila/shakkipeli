/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import kayttoliittyma.TiedostoValitsin;
import kayttoliittyma.TiedostoValitsinTallennus;

/**
 *
 * @author elias
 */
public class TiedostoListaKuuntelija implements ListSelectionListener {

    private TiedostoValitsin tiedostoValitsin;
    
    public TiedostoListaKuuntelija(TiedostoValitsin tiedostoValitsin) {
        this.tiedostoValitsin = tiedostoValitsin;
    }

    
    @Override
    public void valueChanged(ListSelectionEvent lse) {
      String valittu = (String) this.tiedostoValitsin.getTiedostoLista().getSelectedValue();
    
      this.tiedostoValitsin.getTiedostonNimi().setText(valittu);
      
    }
    
}
