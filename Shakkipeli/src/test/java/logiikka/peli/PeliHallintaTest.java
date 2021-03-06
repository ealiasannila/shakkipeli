/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elias
 */
public class PeliHallintaTest {

    private PeliHallinta peliHallinta;

    @Before
    public void setUp() {
        this.peliHallinta = new PeliHallinta();
    }

    @Test
    public void uusiPeliTest() {
        this.peliHallinta.uusiPeli(-1, -1);
        assertEquals(this.peliHallinta.getPeli().toString(), ("VALKOINEN\n"
                + "YRLQJLRY\n"
                + "SSSSSSSS\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "ssssssss\n"
                + "yrlqjlry\n"
                + "-1\n"
                + "-1"));
    }

    @Test
    public void lataaPeliTest() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/mustaTornitus.txt");
        assertEquals(this.peliHallinta.getPeli().toString(), ("MUSTA\n"
                + "YoooJooY\n"
                + "SSSSSSSS\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "ssssssss\n"
                + "yooojooy\n"
                + "-1\n"
                + "-1"));
    }

    @Test
    public void tallennaPeliTest() {

        this.peliHallinta.uusiPeli(-1, -1);
        this.peliHallinta.tallennaPeli("tallennetutPelit/testiTilanteet/tallennusTesti.txt");
        assertEquals(this.peliHallinta.getPeli().toString(), ("VALKOINEN\n"
                + "YRLQJLRY\n"
                + "SSSSSSSS\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "ssssssss\n"
                + "yrlqjlry\n"
                + "-1\n"
                + "-1"));
        this.peliHallinta.getPeli().asetaAktiivinen(0, 1);
        this.peliHallinta.getPeli().siirto(0, 3);
        assertTrue(this.peliHallinta.tallennaPeli("tallennetutPelit/testiTilanteet/tallennusTesti.txt"));
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/tallennusTesti.txt");
        assertEquals(this.peliHallinta.getPeli().toString(), ("MUSTA\n"
                + "YRLQJLRY\n"
                + "SSSSSSSS\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "sooooooo\n"
                + "hooooooo\n"
                + "osssssss\n"
                + "yrlqjlry\n"
                + "-1\n"
                + "-1"));

    }

    @Test
    public void tallennusTestTyhjaNimi() {
        assertFalse(this.peliHallinta.tallennaPeli(""));
    }

    @Test
    public void eiSallittuNimi() {
        assertFalse(this.peliHallinta.tallennaPeli("\n"));
    }

    @Test
    public void tiedostoaEiLoydyLatausTest() {
        this.peliHallinta.uusiPeli(10, 10);
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/tataTiedostoaEiOleOlemassa.txt");
        assertEquals(this.peliHallinta.getPeli().toString(), ("VALKOINEN\n"
                + "YRLQJLRY\n"
                + "SSSSSSSS\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "ssssssss\n"
                + "yrlqjlry\n"
                + "-1\n"
                + "-1"));

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
