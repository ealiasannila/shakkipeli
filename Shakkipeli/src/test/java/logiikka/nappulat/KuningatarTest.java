/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author elias
 */
public class KuningatarTest {

    private Pelilauta testiLauta;
    Nappula kuningatar;

    @Before
    public void setUp() {
        testiLauta = new Pelilauta();

        kuningatar = new Kuningatar(VALKOINEN, 4, 4, testiLauta);

    }

    @Test
    public void liikuuVinottainEtuOikea() {
        assertTrue(this.kuningatar.onSallittuSiirto(6, 6));
    }

    @Test
    public void liikuuVinottainTakaVasen() {
        assertTrue(this.kuningatar.onSallittuSiirto(2, 2));
    }

    @Test
    public void liikuuVinottainTakaOikea() {
        assertTrue(this.kuningatar.onSallittuSiirto(6, 2));
    }

    @Test
    public void liikuuVinottainEtuVasen() {
        assertTrue(this.kuningatar.onSallittuSiirto(2, 6));
    }

    @Test
    public void liikuuEteen() {
        assertTrue(this.kuningatar.onSallittuSiirto(4, 7));
    }

    @Test
    public void liikuuTaakse() {
        assertTrue(this.kuningatar.onSallittuSiirto(4, 0));
    }

    @Test
    public void liikuuVasemmalle() {
        assertTrue(this.kuningatar.onSallittuSiirto(2, 4));
    }

    @Test
    public void liikuuOikealle() {
        assertTrue(this.kuningatar.onSallittuSiirto(7, 4));
    }

    @Test
    public void eiLiikuVaariin() {
        assertFalse(this.kuningatar.onSallittuSiirto(1, 2));
        assertFalse(this.kuningatar.onSallittuSiirto(2, 1));
        assertFalse(this.kuningatar.onSallittuSiirto(1, 5));
        assertFalse(this.kuningatar.onSallittuSiirto(0, 2));
        assertFalse(this.kuningatar.onSallittuSiirto(2, 1));
        assertFalse(this.kuningatar.onSallittuSiirto(4, 4));
        assertFalse(this.kuningatar.onSallittuSiirto(5, 0));
    }

    @Test
    public void uhkaaOikeitaRuutuja() {
        assertEquals(4, this.kuningatar.uhkausLinja(0, 4).size());
        assertEquals(2, this.kuningatar.uhkausLinja(2, 2).size());
    }

}
