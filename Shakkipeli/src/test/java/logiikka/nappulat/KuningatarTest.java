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
        assert(this.kuningatar.onSallittuSiirto(6, 6));
    }
    
    
    @Test
    public void liikuuVinottainTakaVasen() {
        assert(this.kuningatar.onSallittuSiirto(2, 2));
    }
    
    
    @Test
    public void liikuuVinottainTakaOikea() {
        assert(this.kuningatar.onSallittuSiirto(6, 2));
    }
    
    
    @Test
    public void liikuuVinottainEtuVasen() {
        assert(this.kuningatar.onSallittuSiirto(2, 6));
    }

    
    @Test
    public void liikuuEteen() {
        assert(this.kuningatar.onSallittuSiirto(4, 7));
    }
    @Test
    public void liikuuTaakse() {
        assert(this.kuningatar.onSallittuSiirto(4, 0));
    }
    @Test
    public void liikuuVasemmalle() {
        assert(this.kuningatar.onSallittuSiirto(2, 4));
    }
    @Test
    public void liikuuOikealle() {
        assert(this.kuningatar.onSallittuSiirto(7, 4));
    }
    
    
    
    @Test
    public void eiLiikuVaariin() {
        assert(!this.kuningatar.onSallittuSiirto(1, 2));
        assert(!this.kuningatar.onSallittuSiirto(2, 1));
        assert(!this.kuningatar.onSallittuSiirto(1, 5));
        assert(!this.kuningatar.onSallittuSiirto(0, 2));
        assert(!this.kuningatar.onSallittuSiirto(2, 1));
        assert(!this.kuningatar.onSallittuSiirto(4, 4));
        assert(!this.kuningatar.onSallittuSiirto(5, 0));
    }
    
    @Test
    public void uhkaaOikeitaRuutuja(){
        assertEquals(4,this.kuningatar.uhkausLinja(0, 4).size());
        assertEquals(2,this.kuningatar.uhkausLinja(2, 2).size());
    }
    
    
    
}