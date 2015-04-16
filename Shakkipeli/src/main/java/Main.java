import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import logiikka.peli.Kello;
import logiikka.peli.Maa;
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

    public static void main(String args[]) throws InterruptedException {
        SwingUtilities.invokeLater(new Kayttoliittyma());

    }

}
