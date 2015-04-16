/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.util.logging.Level;
import java.util.logging.Logger;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.KelloPiirto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    public void aikaLoppuu() {

        this.peliHallinta.getPeli().asetaKellot(0, 0);
        assertTrue(this.peliHallinta.getPeli().aikaLoppu());

    }

    @Test
    public void aikaEiLopuKunEiKelloa() {
        this.peliHallinta.uusiPeli(-1, -1);
        assertFalse(this.peliHallinta.getPeli().aikaLoppu());
    }

    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
