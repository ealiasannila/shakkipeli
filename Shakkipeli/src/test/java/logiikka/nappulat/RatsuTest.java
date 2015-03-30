/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import static logiikka.peli.Maa.MUSTA;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author elias
 */
public class RatsuTest {

    private Pelilauta testiLauta;
    Nappula ratsu;

    @Before
    public void setUp() {
        testiLauta = new Pelilauta();

        ratsu = new Ratsu(VALKOINEN, 4, 4, testiLauta);

    }

    @Test
    public void liikkuuEtuOikea1() {
        assert (this.ratsu.onSallittuSiirto(5, 6));
    }

    @Test
    public void liikkuuEtuOikea2() {
        assert (this.ratsu.onSallittuSiirto(6, 5));
    }

    @Test
    public void liikkuuTakaOikea1() {
        assert (this.ratsu.onSallittuSiirto(5, 2));
    }

    @Test
    public void liikkuuTakaOikea2() {
        assert (this.ratsu.onSallittuSiirto(6, 3));
    }

    @Test
    public void liikkuuEtuVasen1() {
        assert (this.ratsu.onSallittuSiirto(3, 6));
    }

    @Test
    public void liikkuuEtuVasen2() {
        assert (this.ratsu.onSallittuSiirto(2, 5));
    }

    @Test
    public void liikkuuTakaVasen1() {
        assert (this.ratsu.onSallittuSiirto(3, 2));
    }

    @Test
    public void liikkuuTakaVasen2() {
        assert (this.ratsu.onSallittuSiirto(2, 3));
    }

    @Test
    public void eiLiikuVaarin() {
        assert (!this.ratsu.onSallittuSiirto(4, 5));
        assert (!this.ratsu.onSallittuSiirto(5, 5));
        assert (!this.ratsu.onSallittuSiirto(6, 6));
        assert (!this.ratsu.onSallittuSiirto(2, 4));

    }

    @Test
    public void sallittuLiikkumisTapa() {
        for (int i = 0; i < 8; i++) {
            assert (!this.ratsu.sallittuLiikkumisTapa(i, 4));
        }
        for (int i = 0; i < 8; i++) {
            assert (!this.ratsu.sallittuLiikkumisTapa(4, i));
        }
        for (int i = 0; i < 8; i++) {
            assert(!this.ratsu.sallittuLiikkumisTapa(i, i));
        }
    }

    @Test
    public void reitillaEiMuitaNappuloitaTest() {
        assert (this.ratsu.reitillaEiMuitaNappuloita(2, 3));
    }

    @Test
    public void uhkausLinjaTyhjaTest() {
        assert (this.ratsu.uhkausLinja(4, 3) != null);
        assert (this.ratsu.uhkausLinja(2, 3).isEmpty());
        assert (this.ratsu.uhkausLinja(4, 5).isEmpty());
        assert (this.ratsu.uhkausLinja(4, 5).getClass() == ArrayList.class);
    }

    @Test
    public void toStringTest() {
        assert this.ratsu.toString().equals("r");
        Ratsu musta = new Ratsu(MUSTA, 1, 1, this.testiLauta);
        assert musta.toString().equals("R");

    }

    @Test
    public void mahdollisetRuudutTest() {
        System.out.println(this.ratsu.mahdollisetRuudut().toString());
        assert (this.ratsu.mahdollisetRuudut().toString().equals("[[6,5], [2,5], [5,6], [3,6], [6,3], [2,3], [5,2], [3,2]]"));
    }

}
