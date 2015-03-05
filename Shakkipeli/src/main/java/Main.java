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
        lauta.setAktiivinen(lauta.haeNappula(0, 0));
        lauta.teeSiirto(0, 7);
        lauta.tulosta();
        lauta.teeSiirto(7, 7);
        lauta.tulosta();
        lauta.teeSiirto(2, 3);
        lauta.tulosta();
        lauta.teeSiirto(7, 10);
        lauta.tulosta();
        lauta.teeSiirto(-1, 7);
        lauta.tulosta();
        lauta.teeSiirto(7, 0);
        lauta.tulosta();
        lauta.teeSiirto(7, 1);
        lauta.tulosta();
        lauta.teeSiirto(7, 7);
        lauta.tulosta();
    }

}
