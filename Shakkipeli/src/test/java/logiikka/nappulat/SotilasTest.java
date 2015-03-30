/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import static logiikka.peli.Maa.MUSTA;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Peli;
import logiikka.peli.PeliHallinta;
import logiikka.peli.Pelilauta;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author elias
 */
public class SotilasTest {

    
    private PeliHallinta testiPeli;
    Nappula mustaSotilas;
    Nappula valkoinenSotilas;

    @Before
    public void setUp() {
        testiPeli = new PeliHallinta();

        mustaSotilas = new Sotilas(MUSTA,4, 6,this.testiPeli.getPeli().getLauta() );
        valkoinenSotilas = new Sotilas(VALKOINEN, 4, 1, this.testiPeli.getPeli().getLauta());

    }

    @Test
    public void mustaLiikkuuEteenPain() {
        assert (this.mustaSotilas.onSallittuSiirto(4, 5));
    }

    @Test
    public void valkoinenLiikkuuEteenPain() {
        assert (this.valkoinenSotilas.onSallittuSiirto(4, 2));
    }

    @Test
    public void mustaEiLiikuTaaksePain() {
        assert (!this.mustaSotilas.onSallittuSiirto(4, 7));
    }

    @Test
    public void valkoinenEiLiikuTaaksePain() {
        assert (!this.valkoinenSotilas.onSallittuSiirto(4, 0));
    }

    @Test
    public void mustaEiLiikuVinoon() {
        assert (!this.mustaSotilas.onSallittuSiirto(3, 5));
    }

    @Test
    public void valkoinenEiLiikuVinoon() {
        assert (!this.valkoinenSotilas.onSallittuSiirto(5, 2));
    }

    @Test 
    public void valkoinenSyoVinoon(){
        Sotilas mustaSotilas2 = new Sotilas(MUSTA, 3,2, this.testiPeli.getPeli().getLauta());
        assert (this.valkoinenSotilas.onSallittuSiirto(3, 2));
        
    }
    
    @Test 
    public void mustaSyoVinoon(){
        Sotilas valkoinenSotilas2 = new Sotilas(VALKOINEN, 5,5, this.testiPeli.getPeli().getLauta());
        assert (this.mustaSotilas.onSallittuSiirto(5, 5));
        
    }
    
    
    @Test 
    public void eiSyoSuoraan(){
        Sotilas mustaSotilas2 = new Sotilas(MUSTA, 4,2, this.testiPeli.getPeli().getLauta());
        assert (!this.valkoinenSotilas.onSallittuSiirto(4, 2));
        
    }
    
    
    @Test 
    public void eiSyoVaakaan(){
        Sotilas mustaSotilas2 = new Sotilas(MUSTA, 3,1, this.testiPeli.getPeli().getLauta());
        assert (!this.valkoinenSotilas.onSallittuSiirto(3, 1));
        
    }
    
    
    @Test 
    public void voiLiikkua2EkallaSiirrolla(){
        assert (this.valkoinenSotilas.onSallittuSiirto(4, 2));
    }
    
    @Test 
    public void eiVoiLiikkua2TokallaSiirrolla(){
        this.testiPeli.uusiPeli();
        this.testiPeli.getPeli().siirto(4, 2);
        assert (!this.valkoinenSotilas.onSallittuSiirto(4, 4));
        
    }
    
    @Test
    public void uhkausLinjaTyha(){
        assert(this.mustaSotilas.uhkausLinja(1, 2).isEmpty());
    }
    
}
