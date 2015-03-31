/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author elias
 */
public class LahettiTest {

    private Pelilauta testiLauta;
    Nappula lahetti;

    @Before
    public void setUp() {
        testiLauta = new Pelilauta();

        lahetti = new Lahetti(VALKOINEN, 4, 4, testiLauta);

    }

    @Test
    public void liikuuVinottainEtuOikea() {
        assertTrue (this.lahetti.onSallittuSiirto(6, 6));
    }

    @Test
    public void liikuuVinottainTakaVasen() {
        assertTrue (this.lahetti.onSallittuSiirto(2, 2));
    }

    @Test
    public void liikuuVinottainTakaOikea() {
        assertTrue (this.lahetti.onSallittuSiirto(6, 2));
    }

    @Test
    public void liikuuVinottainEtuVasen() {
        assertTrue (this.lahetti.onSallittuSiirto(2, 6));
    }
    
     @Test
    public void liikuuEteen() {
        assertFalse(this.lahetti.onSallittuSiirto(4, 7));
    }
    @Test
    public void liikuuTaakse() {
        assertFalse(this.lahetti.onSallittuSiirto(4, 0));
    }
    @Test
    public void liikuuVasemmalle() {
        assertFalse(this.lahetti.onSallittuSiirto(2, 4));
    }
    @Test
    public void liikuuOikealle() {
        assertFalse(this.lahetti.onSallittuSiirto(7, 4));
    }
    
    
    
    @Test
    public void eiLiikuVaariin() {
        assertFalse(this.lahetti.onSallittuSiirto(1, 2));
        assertFalse(this.lahetti.onSallittuSiirto(2, 1));
        assertFalse(this.lahetti.onSallittuSiirto(1, 5));
        assertFalse(this.lahetti.onSallittuSiirto(0, 2));
        assertFalse(this.lahetti.onSallittuSiirto(2, 1));
        assertFalse(this.lahetti.onSallittuSiirto(4, 4));
        assertFalse(this.lahetti.onSallittuSiirto(5, 0));
    }
    
    @Test
    public void uhkaaOikeitaRuutuja(){
        assertEquals(3,this.lahetti.uhkausLinja(1, 7).size());
        assertEquals(2,this.lahetti.uhkausLinja(2, 2).size());
    }
    
}
