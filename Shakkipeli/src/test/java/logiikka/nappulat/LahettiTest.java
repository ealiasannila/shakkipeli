/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import static org.junit.Assert.assertEquals;
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
        assert (this.lahetti.onSallittuSiirto(6, 6));
    }

    @Test
    public void liikuuVinottainTakaVasen() {
        assert (this.lahetti.onSallittuSiirto(2, 2));
    }

    @Test
    public void liikuuVinottainTakaOikea() {
        assert (this.lahetti.onSallittuSiirto(6, 2));
    }

    @Test
    public void liikuuVinottainEtuVasen() {
        assert (this.lahetti.onSallittuSiirto(2, 6));
    }
    
     @Test
    public void liikuuEteen() {
        assert(!this.lahetti.onSallittuSiirto(4, 7));
    }
    @Test
    public void liikuuTaakse() {
        assert(!this.lahetti.onSallittuSiirto(4, 0));
    }
    @Test
    public void liikuuVasemmalle() {
        assert(!this.lahetti.onSallittuSiirto(2, 4));
    }
    @Test
    public void liikuuOikealle() {
        assert(!this.lahetti.onSallittuSiirto(7, 4));
    }
    
    
    
    @Test
    public void eiLiikuVaariin() {
        assert(!this.lahetti.onSallittuSiirto(1, 2));
        assert(!this.lahetti.onSallittuSiirto(2, 1));
        assert(!this.lahetti.onSallittuSiirto(1, 5));
        assert(!this.lahetti.onSallittuSiirto(0, 2));
        assert(!this.lahetti.onSallittuSiirto(2, 1));
        assert(!this.lahetti.onSallittuSiirto(4, 4));
        assert(!this.lahetti.onSallittuSiirto(5, 0));
    }
    
    @Test
    public void uhkaaOikeitaRuutuja(){
        assertEquals(3,this.lahetti.uhkausLinja(1, 7).size());
        assertEquals(2,this.lahetti.uhkausLinja(2, 2).size());
    }
    
}
