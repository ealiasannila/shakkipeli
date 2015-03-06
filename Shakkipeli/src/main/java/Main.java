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
        System.out.println(peli.onMatissa());
        System.out.println("-----------");
        
        peli.asetaAktiivinen(0,0);
        peli.siirto(0, 6);
        peli.tulosta();
        System.out.println(peli.onMatissa());
        System.out.println("-----------");
        
        peli.asetaAktiivinen(7,7);
        peli.siirto(7, 2);
        peli.tulosta();
        System.out.println(peli.onMatissa());
        
        System.out.println("-----------");
        peli.asetaAktiivinen(7,0);
        peli.siirto(5, 0);
        peli.tulosta();
        System.out.println(peli.onMatissa());
        
        peli.asetaAktiivinen(7,2);
        peli.siirto(7, 3);
        peli.tulosta();
        System.out.println(peli.onMatissa());
        
        System.out.println("-----------");
        peli.asetaAktiivinen(5,0);
        peli.siirto(5, 7);
        peli.tulosta();
        System.out.println(peli.onMatissa());
        
        peli.tulosta();
        
        
        
    }

}
