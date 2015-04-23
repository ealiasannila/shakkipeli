/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.nappulapiirto;

import java.awt.Image;
import logiikka.peli.Maa;

/**
 * Hakee kuningattern kuvan
 *
 * @author elias
 */
public class KuningatarPiirto extends NappulaPiirto {

    public Image haeKuva(Maa maa) {
        return this.haeKuva(maa, "kuningatar.png");
    }

}
