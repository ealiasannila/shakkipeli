/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import static logiikka.peli.Maa.MUSTA;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.PeliHallinta;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author elias
 */
public class SotilasTest {

    private PeliHallinta testiPeliHallinta;
    Nappula mustaSotilas;
    Nappula valkoinenSotilas;

    @Before
    public void setUp() {
        testiPeliHallinta = new PeliHallinta();

        mustaSotilas = new Sotilas(MUSTA, 4, 6, this.testiPeliHallinta.getPeli().getLauta());
        valkoinenSotilas = new Sotilas(VALKOINEN, 4, 1, this.testiPeliHallinta.getPeli().getLauta());

    }

    @Test
    public void mustaLiikkuuEteenPain() {
        assertTrue(this.mustaSotilas.onSallittuSiirto(4, 5));
    }

    @Test
    public void valkoinenLiikkuuEteenPain() {
        assertTrue(this.valkoinenSotilas.onSallittuSiirto(4, 2));
    }

    @Test
    public void mustaEiLiikuTaaksePain() {
        assertFalse(this.mustaSotilas.onSallittuSiirto(4, 7));
    }

    @Test
    public void valkoinenEiLiikuTaaksePain() {
        assertFalse(this.valkoinenSotilas.onSallittuSiirto(4, 0));
    }

    @Test
    public void mustaEiLiikuVinoon() {
        assertFalse(this.mustaSotilas.onSallittuSiirto(3, 5));
        assertFalse(this.mustaSotilas.onSallittuSiirto(5, 5));
    }

    @Test
    public void valkoinenEiLiikuVinoon() {
        assertFalse(this.valkoinenSotilas.onSallittuSiirto(5, 2));
        assertFalse(this.valkoinenSotilas.onSallittuSiirto(3, 2));
    }

    @Test
    public void valkoinenSyoVinoon() {
        Sotilas mustaSotilas2 = new Sotilas(MUSTA, 3, 2, this.testiPeliHallinta.getPeli().getLauta());
        assertTrue(this.valkoinenSotilas.onSallittuSiirto(3, 2));

    }

    @Test
    public void mustaSyoVinoon() {
        Sotilas valkoinenSotilas2 = new Sotilas(VALKOINEN, 5, 5, this.testiPeliHallinta.getPeli().getLauta());
        assertTrue(this.mustaSotilas.onSallittuSiirto(5, 5));

    }

    @Test
    public void eiSyoSuoraan() {
        Sotilas mustaSotilas2 = new Sotilas(MUSTA, 4, 2, this.testiPeliHallinta.getPeli().getLauta());
        assertFalse(this.valkoinenSotilas.onSallittuSiirto(4, 2));
        assertFalse(this.valkoinenSotilas.tarkistaReitti(4, 2));

    }

    @Test
    public void eiSyoVaakaan() {
        Sotilas mustaSotilas2 = new Sotilas(MUSTA, 3, 1, this.testiPeliHallinta.getPeli().getLauta());
        assertFalse(this.valkoinenSotilas.onSallittuSiirto(3, 1));

    }

    @Test
    public void voiLiikkua2EkallaSiirrolla() {
        assertTrue(this.valkoinenSotilas.onSallittuSiirto(4, 3));
        assertTrue(this.valkoinenSotilas.tarkistaReitti(4, 3));
    }

    @Test
    public void eiVoiLiikkuaVinottain2Siirtoa() {
        assertFalse(this.valkoinenSotilas.onSallittuSiirto(3, 3));
        assertFalse(this.valkoinenSotilas.onSallittuSiirto(2, 3));
        assertFalse(this.valkoinenSotilas.onSallittuSiirto(5, 3));
        assertFalse(this.valkoinenSotilas.onSallittuSiirto(6, 3));

    }

    @Test
    public void eiVoiLiikkua2TokallaSiirrolla() {
        this.testiPeliHallinta.uusiPeli(-1, -1);
        this.testiPeliHallinta.getPeli().siirto(4, 2);
        assertFalse(this.valkoinenSotilas.onSallittuSiirto(4, 4));

    }

    @Test
    public void uhkausLinjaTyha() {
        assertTrue(this.mustaSotilas.uhkausLinja(1, 2) != null);
        assertTrue(this.mustaSotilas.uhkausLinja(1, 2).isEmpty());
    }

    @Test
    public void mahdollisetRuudutTest() {
        assertEquals(this.mustaSotilas.mahdollisetRuudut().toString(), ("[[4,5], [3,5], [5,5]]"));
        assertEquals(this.valkoinenSotilas.mahdollisetRuudut().toString(), ("[[4,2], [3,2], [5,2]]"));
    }

}
