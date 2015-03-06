import logiikka.nappulat.Maa;
import logiikka.peli.Peli;
import logiikka.peli.Pelilauta;

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
        Peli peli = new Peli();
        peli.tulosta();
        System.out.println(peli.onShakissa());
        System.out.println("-----------");
        
        peli.asetaAktiivinen(0,0);
        peli.siirto(0, 7);
        peli.tulosta();
        System.out.println(peli.onShakissa());
        System.out.println("-----------");
        
        peli.asetaAktiivinen(3,7);
        peli.siirto(3, 6);
        peli.tulosta();
        System.out.println(peli.onShakissa());
        
        System.out.println("-----------");
        peli.asetaAktiivinen(0,7);
        peli.siirto(0, 6);
        peli.tulosta();
        System.out.println(peli.onShakissa());
        
        
        
    }

}
