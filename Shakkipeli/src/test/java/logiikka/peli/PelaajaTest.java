/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

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
public class PelaajaTest {
    private PeliHallinta peliHallinta;
    
    @Before
    public void setUp() {
    this.peliHallinta = new PeliHallinta();
    }
    
    @Test
    public void mustanRivitTulevatOikein(){
        assertEquals(7, this.peliHallinta.getPeli().getMusta().getPerusRivi());
        assertEquals(6, this.peliHallinta.getPeli().getMusta().getSotilasRivi());
        assertEquals(5, this.peliHallinta.getPeli().getMusta().getSotilaastaSeuraavaRivi());
        assertEquals(4, this.peliHallinta.getPeli().getMusta().getKaksiRiviaSotilaasta());
        assertEquals(-1, this.peliHallinta.getPeli().getMusta().getOhestaLyontiX());
    }
    @Test
    public void valkoisenRivitTulevatOikein(){
        assertEquals(0, this.peliHallinta.getPeli().getValkoinen().getPerusRivi());
        assertEquals(1, this.peliHallinta.getPeli().getValkoinen().getSotilasRivi());
        assertEquals(2, this.peliHallinta.getPeli().getValkoinen().getSotilaastaSeuraavaRivi());
        assertEquals(3, this.peliHallinta.getPeli().getValkoinen().getKaksiRiviaSotilaasta());
        assertEquals(-1, this.peliHallinta.getPeli().getValkoinen().getOhestaLyontiX());
    }
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
