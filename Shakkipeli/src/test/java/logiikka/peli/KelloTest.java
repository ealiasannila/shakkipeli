/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.KelloPiirto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elias
 */
public class KelloTest {

    private PeliHallinta peliHallinta;

    public KelloTest() {
    }

    @Before
    public void setUp() {
        this.peliHallinta = new PeliHallinta();
        this.peliHallinta.uusiPeli(2, 2);
    }

    @Test
    public void paivitaTestEiPiirtoa() {
        Kello kello = new Kello(10);
        assertFalse(kello.paivita());
        assertEquals(10, kello.getAika());

    }

    @Test
    public void paivitaTest() {
        Kello kello = new Kello(10);
        kello.setKellonPiirto(new KelloPiirto(new Kayttoliittyma(), 1, "perusosa"));
        assertTrue(kello.paivita());
        assertEquals(9, kello.getAika());

    }

    @Test
    public void paivitaTestAikaAlleNollan() {
        Kello kello = new Kello(-1);
        kello.setKellonPiirto(new KelloPiirto(new Kayttoliittyma(), 1, "perusosa"));
        assertTrue(kello.paivita());
        assertEquals(-1, kello.getAika());

    }

    @Test
    public void aikaLoppuu() {

        this.peliHallinta.getPeli().asetaKellot(0, 0);
        assertTrue(this.peliHallinta.getPeli().getPelitarkistus().aikaLoppu());

    }

    @Test
    public void aikaEiLopuKunEiKelloa() {
        this.peliHallinta.uusiPeli(-1, -1);
        assertFalse(this.peliHallinta.getPeli().getPelitarkistus().aikaLoppu());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
