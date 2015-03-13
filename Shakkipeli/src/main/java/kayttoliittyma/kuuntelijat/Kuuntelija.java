/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.PeliPiirto;
import logiikka.peli.Peli;
import logiikka.peli.PeliHallinta;

/**
 *
 * @author elias
 */
public abstract class Kuuntelija {

    protected Kayttoliittyma kayttoliittyma;
    //protected PeliPiirto peliPiirto;

    public Kuuntelija(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }
}
