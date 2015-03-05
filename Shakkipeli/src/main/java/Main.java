import logiikka.nappulat.Maa;
import logiikka.pelilauta.Pelilauta;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elias
 */
public class Main {

    public static void main(String args[]) {

        Pelilauta lauta = new Pelilauta();
        lauta.tulosta();
        System.out.println(lauta.getMustaKunkku().onShakissa() + "musta");
        System.out.println(lauta.getValkonenKunkku().onShakissa() + "valkoinen");

        lauta.setAktiivinen(lauta.haeNappula(0, 0));
        lauta.teeSiirto(0, 7);
        System.out.println(lauta.getMustaKunkku().onShakissa() + "musta");
        System.out.println(lauta.getValkonenKunkku().onShakissa() + "valkoinen");
        lauta.tulosta();
        System.out.println(lauta.getMustaKunkku().myosYmparoivatRuudutUhattuna());

        lauta.setAktiivinen(lauta.haeNappula(7, 0));
        lauta.teeSiirto(7, 6);
        lauta.tulosta();
        System.out.println(lauta.getMustaKunkku().myosYmparoivatRuudutUhattuna());

        
    }

}
