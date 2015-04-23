package main;

import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;


/**
 * Käynnistää shakkipelin
 * @author elias
 */
public class Main {

    public static void main(String args[]) throws InterruptedException {
        SwingUtilities.invokeLater(new Kayttoliittyma());

    }

}
