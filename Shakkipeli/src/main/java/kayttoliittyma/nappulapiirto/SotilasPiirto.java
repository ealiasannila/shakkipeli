/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.nappulapiirto;

import java.awt.Image;
import logiikka.peli.Maa;

/**
 * palauttaa sotilaan kuvan
 *
 * @author elias
 */
public class SotilasPiirto extends NappulaPiirto {

    @Override
    public Image haeKuva(Maa maa) {
        return this.haeKuva(maa, "sotilas.png");
    }

}
