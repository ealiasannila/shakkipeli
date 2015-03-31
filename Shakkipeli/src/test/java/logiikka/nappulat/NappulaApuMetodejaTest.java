/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.PeliHallinta;
import logiikka.peli.Pelilauta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NappulaApuMetodejaTest {

    private PeliHallinta peliHallinta;
    private Pelilauta testiLauta;
    private Kuningatar kuningatar;

    @Before
    public void setUp() {
        this.peliHallinta = new PeliHallinta();
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/tyhja.txt");
        testiLauta = peliHallinta.getPeli().getLauta();

        kuningatar = new Kuningatar(VALKOINEN, 4, 4, testiLauta);

    }

    @Test
    public void onSamallaPystyRivillaTest() {
        assertTrue (NappulaApumetodeja.onSamallaPystyTaiVaakaRivilla(1, 3, 1, 6));
    }

    @Test
    public void onSamallaVaakaRivillaTest() {
        assertTrue (NappulaApumetodeja.onSamallaPystyTaiVaakaRivilla(4, 4, 4, 3));
    }

    @Test
    public void eiOleSamallaPystyTaiVaakaRivillaTest() {
        assertFalse (NappulaApumetodeja.onSamallaPystyTaiVaakaRivilla(0, 1, 3, 2));
    }

    @Test
    public void onSamallaVinorivilla() {
        assertTrue (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 2, 2));
        assertTrue (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 0, 0));
        assertTrue (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 0, 2));
        assertTrue (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 2, 0));
    }

    @Test
    public void eiOleSamallaVinorivilla() {
        assertFalse (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 2, 1));
        assertFalse (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 0, 1));
        assertFalse (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 0, 1));
        assertFalse (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 1, 2));
    }

    @Test
    public void uhkausLinjaTyha() {
        assertTrue (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(3, 3, this.kuningatar) != null);
        assertTrue (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(5, 6, this.kuningatar) != null);
        assertTrue (NappulaApumetodeja.uhkausLinjaVino(5, 6, kuningatar) != null);
        assertTrue (NappulaApumetodeja.uhkausLinjaVino(4, 3, kuningatar) != null);

        assertTrue (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(3, 3, this.kuningatar).isEmpty());
        assertTrue (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(5, 6, this.kuningatar).isEmpty());
        assertTrue (NappulaApumetodeja.uhkausLinjaVino(5, 6, kuningatar).isEmpty());
        assertTrue (NappulaApumetodeja.uhkausLinjaVino(4, 3, kuningatar).isEmpty());

    }

    @Test
    public void uhkausLinjaVino() {
        assertEquals (NappulaApumetodeja.uhkausLinjaVino(3, 3, this.kuningatar).toString(),("[[3,3]]"));
        assertEquals (NappulaApumetodeja.uhkausLinjaVino(5, 5, this.kuningatar).toString(),("[[5,5]]"));
        assertEquals (NappulaApumetodeja.uhkausLinjaVino(3, 5, this.kuningatar).toString(),("[[3,5]]"));
        assertEquals (NappulaApumetodeja.uhkausLinjaVino(5, 3, this.kuningatar).toString(),("[[5,3]]"));
    }

    @Test
    public void uhkausLinjaPystyTaiVaaka() {
        assertEquals (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(4, 3, this.kuningatar).toString(),("[[4,3]]"));
        assertEquals (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(4, 5, this.kuningatar).toString(),("[[4,5]]"));

        assertEquals (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(3, 4, this.kuningatar).toString(),("[[3,4]]"));
        assertEquals (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(5, 4, this.kuningatar).toString(),("[[5,4]]"));
    }

    @Test
    public void reitllaEiMuitaNappuloitaVinoTest() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/reitillaEiMuitaNappuloita.txt");
        this.kuningatar = (Kuningatar) this.peliHallinta.getPeli().getLauta().haeNappula(2, 2);

        assertTrue (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(0, 0, kuningatar));
        assertTrue (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(4, 4, kuningatar));
        assertTrue (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(4, 0, kuningatar));
        assertTrue (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(0, 4, kuningatar));

        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(5, 0, kuningatar));
    }

    @Test
    public void reitllaMuitaNappuloitaVinoTest() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/reitillaMuitaNappuloita.txt");
        this.kuningatar = (Kuningatar) this.peliHallinta.getPeli().getLauta().haeNappula(2, 2);

        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(0, 0, kuningatar));
        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(4, 4, kuningatar));
        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(4, 0, kuningatar));
        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(0, 4, kuningatar));

        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(5, 0, kuningatar));
    }

    @Test
    public void reitllaMuitaNappuloitaPystyTaiVaakaTest() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/reitillaMuitaNappuloita.txt");
        this.kuningatar = (Kuningatar) this.peliHallinta.getPeli().getLauta().haeNappula(2, 2);

        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(2, 0, kuningatar));
        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(2, 4, kuningatar));
        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(4, 2, kuningatar));
        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(2, 4, kuningatar));

        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(5, 0, kuningatar));
    }

    @Test
    public void reitllaEiMuitaNappuloitaPystyTaiVaakaTest() {

        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/reitillaEiMuitaNappuloita.txt");
        this.kuningatar = (Kuningatar) this.peliHallinta.getPeli().getLauta().haeNappula(2, 2);

        assertTrue (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(2, 0, kuningatar));
        assertTrue (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(2, 4, kuningatar));
        assertTrue (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(4, 2, kuningatar));
        assertTrue (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(2, 4, kuningatar));

        assertFalse (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(5, 0, kuningatar));
    }

    @Test
    public void MahdollisetPystyTaiVaakaRuudut() {
        assertEquals (NappulaApumetodeja.mahdollisetPystyTaiVaakaRuudut(this.kuningatar.getX(), this.kuningatar.getY()).toString(), "[[4,0], [0,4], [4,1], [1,4], [4,2], [2,4], [4,3], [3,4], [4,4], [4,4], [4,5], [5,4], [4,6], [6,4], [4,7], [7,4]]");
    }

    @Test
    public void MahdollisetVinoRuudut() {
        assertEquals (NappulaApumetodeja.mahdollisetVinoRuudut(this.kuningatar.getX(), this.kuningatar.getY()).toString(), "[[4,4], [5,5], [6,6], [7,7], [4,4], [3,3], [2,2], [1,1], [0,0], [4,4], [5,3], [6,2], [7,1], [4,4], [3,5], [2,6], [1,7]]");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
