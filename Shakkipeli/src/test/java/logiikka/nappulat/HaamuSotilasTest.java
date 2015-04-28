package logiikka.nappulat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import logiikka.nappulat.HaamuSotilas;
import static logiikka.peli.Maa.MUSTA;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elias
 */
public class HaamuSotilasTest {

    private Pelilauta testilauta;
    private HaamuSotilas haamuSotilas;

    @Before
    public void setUp() {
        this.testilauta = new Pelilauta();
        this.haamuSotilas = new HaamuSotilas(VALKOINEN, 0, 2, this.testilauta);
    }

    @Test
    public void eiLiikuTest() {
        assertFalse(this.haamuSotilas.onSallittuSiirto(0, 3));
    }

    @Test
    public void eiUhkaaTest() {
        assertTrue(this.haamuSotilas.nappulanReitti(1, 3).isEmpty());
    }

    @Test
    public void eiMahdollisiaRuutujaTest() {
        assertTrue(this.haamuSotilas.mahdollisetRuudut().isEmpty());
    }

    @Test
    public void toStringTest() {
        assertEquals(this.haamuSotilas.toString(), "h");
        this.haamuSotilas = new HaamuSotilas(MUSTA, 0, 5, this.testilauta);
        assertEquals(this.haamuSotilas.toString(), "H");

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
