/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import static logiikka.nappulat.Maa.VALKOINEN;
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
    public void uhkausLinjaTyhja(){
        assert(this.ratsu.uhkausLinja(2, 3).isEmpty());
    }
 }
