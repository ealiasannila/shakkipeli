/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import kayttoliittyma.Kayttoliittyma;

/**
 * Lisää kuuntelijan periviin luokkiin käyttöliittymän oliomuuttujaksi
 *
 * @author elias
 */
public abstract class KayttoliittymanTuntevaLuokka {

    protected Kayttoliittyma kayttoliittyma;
    //protected PeliPiirto peliPiirto;

    public KayttoliittymanTuntevaLuokka(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }
}
