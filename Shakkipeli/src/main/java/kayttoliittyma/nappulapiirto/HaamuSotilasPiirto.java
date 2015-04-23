/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.nappulapiirto;

import java.awt.Image;
import logiikka.peli.Maa;

/**
 * Haamusotilasta ei piirretä joten palauttaa null
 *
 * @author elias
 */
public class HaamuSotilasPiirto extends NappulaPiirto {

    @Override
    public Image haeKuva(Maa maa) {

        return null;
    }

}
