/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;
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
public class KunkkuTest {

    private Pelilauta testiLauta;
    Nappula kunkku;

    public KunkkuTest() {
    }

    @Before
    public void setUp() {
        testiLauta = new Pelilauta();

        kunkku = new Kunkku(VALKOINEN, 1, 0, testiLauta);

    }

    @Test
    public void kunkkuUhkaaOikeaaRuutua() {
        ArrayList<Ruutu> uhatutRuudut = kunkku.uhkausLinja(1, 5);
        assert(uhatutRuudut.isEmpty());
    }
    
    @Test
    public void kunkkuLiikkuuYhdenVinottain(){
        assertEquals(true, kunkku.onSallittuSiirto(0, 1));
    }
    
    @Test
    public void kunkkuLiikkuuYhdenSuoraan(){
        assertEquals(true, kunkku.onSallittuSiirto(2, 0));
    }
    
    @Test
    public void kunkkuEiLiikkuKahtaSuoraan(){
        assertEquals(false, kunkku.onSallittuSiirto(3, 0));
    }
    @Test
    public void kunkkuEiLiikkuKahtaVinoon(){
        assertEquals(false, kunkku.onSallittuSiirto(3, 2));
    }
    
    
    

}
    // TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
// @Test
// public void hello() {}

