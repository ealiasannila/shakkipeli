/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import static logiikka.peli.Maa.MUSTA;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.nappulat.Nappula;
import logiikka.nappulat.Torni;
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
public class PelilautaTest {

    public PelilautaTest() {
    }
    Pelilauta lauta;
    Nappula valkoinenTorni;
    Nappula mustaTorni;

    @Before
    public void setUp() {
        lauta = new Pelilauta();
        mustaTorni = new Torni(MUSTA, 3, 2, lauta);
        valkoinenTorni = new Torni(VALKOINEN, 3, 5, lauta);
    }

    @Test
    public void haeNappulaPalauttaOikeanNappulanKunRuudussaOnNappula() {
        assertEquals(mustaTorni, lauta.haeNappula(3, 2));
        assertEquals(valkoinenTorni, lauta.haeNappula(3, 5));
    }

    @Test
    public void haeNappulaPalauttaaNullKunEiNappulaa() {
        assertEquals(null, lauta.haeNappula(3, 1));
        assertEquals(null, lauta.haeNappula(2, 5));
    }

    @Test
    public void haeNappulaLaudanUlkopuolelta() {
        assertEquals(null, lauta.haeNappula(8, 2));
        assertEquals(null, lauta.haeNappula(3, 8));
        assertEquals(null, lauta.haeNappula(-1, 2));
        assertEquals(null, lauta.haeNappula(3, -1));
    }

    @Test
    public void siirronJalkeenUudessaRuudussaOikeaNappula() {
        this.lauta.teeSiirto(1, 2, mustaTorni);
        assertEquals(this.mustaTorni, this.lauta.haeNappula(1, 2));

    }

    @Test
    public void siirronJalkeenNappulaOikeassaRuudussa() {
        this.lauta.teeSiirto(4, 2, mustaTorni);
        assertEquals(4, this.mustaTorni.getX());
        assertEquals(2, this.mustaTorni.getY());
    }

    @Test
    public void epaonnistuneenSiirronJalkeenUudessaRuudussaEiNappulaa() {
        this.lauta.teeSiirto(3, 6, mustaTorni);
        assertEquals(null, this.lauta.haeNappula(3, 6));

    }

    @Test
    public void epaonnistuneenSiirronJalkeenNappulaOikeassaRuudussa() {
        this.lauta.teeSiirto(3, 6, mustaTorni);
        assertEquals(3, this.mustaTorni.getX());
        assertEquals(2, this.mustaTorni.getY());
    }

    @Test
    public void syotyNappulaEiJaaRuutuun() {
        this.lauta.teeSiirto(3, 5, mustaTorni);
        assertEquals(this.mustaTorni, this.lauta.haeNappula(3, 5));
        this.lauta.teeSiirto(3, 6, mustaTorni);
        assertEquals(null, this.lauta.haeNappula(3, 5));
    }

    @Test
    public void siirronJalkeenVanhassaRudussaNull() {
        this.lauta.teeSiirto(3, 3, mustaTorni);
        assertEquals(null, this.lauta.haeNappula(3, 2));
    }

  
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
