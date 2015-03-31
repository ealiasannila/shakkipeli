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
public class PeliHallintaTest {

    private PeliHallinta peliHallinta;

    @Before
    public void setUp() {
        this.peliHallinta = new PeliHallinta();
    }

    @Test
    public void uusiPeliTest() {
        this.peliHallinta.uusiPeli();
        assertEquals (this.peliHallinta.getPeli().toString(),(
                "VALKOINEN\n"
                + "TRLQKLRT\n"
                + "SSSSSSSS\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "ssssssss\n"
                + "trlqklrt\n"
        ));
    }

    @Test
    public void lataaPeliTest() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/mustaTornitus.txt");
        assertEquals (this.peliHallinta.getPeli().toString(),(
                "MUSTA\n"
                + "ToooKooT\n"
                + "SSSSSSSS\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "ssssssss\n"
                + "toookoot\n"
        ));
    }

    @Test
    public void tallennaPeliTest() {

        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/uusi.txt");
        this.peliHallinta.tallennaPeli("tallennetutPelit/testiTilanteet/tallennusTesti.txt");
        assertEquals (this.peliHallinta.getPeli().toString(),(
                "VALKOINEN\n"
                + "TRLQKLRT\n"
                + "SSSSSSSS\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "ssssssss\n"
                + "trlqklrt\n"));
        this.peliHallinta.getPeli().asetaAktiivinen(0, 1);
        this.peliHallinta.getPeli().siirto(0, 3);
        this.peliHallinta.tallennaPeli("tallennetutPelit/testiTilanteet/tallennusTesti.txt");
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/tallennusTesti.txt");
        assertEquals (this.peliHallinta.getPeli().toString(),(
                "MUSTA\n"
                + "TRLQKLRT\n"
                + "SSSSSSSS\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "sooooooo\n"
                + "hooooooo\n"
                + "osssssss\n"
                + "trlqklrt\n"));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
