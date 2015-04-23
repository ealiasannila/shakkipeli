/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elias
 */
public class RuutuTest {

    private Ruutu ruutu1;
    private Ruutu ruutu2;
    private Ruutu ruutu3;

    @Before
    public void setUp() {
        this.ruutu1 = new Ruutu(0, 0);
        this.ruutu2 = new Ruutu(0, 0);

        this.ruutu3 = new Ruutu(0, 1);
    }

    @Test
    public void ruudutJoillaSamaSijaintiSamaRuutu() {
        assertEquals(this.ruutu1, ruutu2);

    }

    @Test
    public void ruudutJoillaEriSijaintiEriRuutu() {
        assertFalse(this.ruutu1.equals(ruutu3));

    }

    @Test
    public void toStringTest() { //käytetään muussa testauksessa niin testaan
        assertEquals(this.ruutu1.toString(), ("[0,0]"));

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
