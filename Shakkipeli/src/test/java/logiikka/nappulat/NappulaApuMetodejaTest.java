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
        assert (NappulaApumetodeja.onSamallaPystyTaiVaakaRivilla(1, 3, 1, 6));
    }

    @Test
    public void onSamallaVaakaRivillaTest() {
        assert (NappulaApumetodeja.onSamallaPystyTaiVaakaRivilla(4, 4, 4, 3));
    }

    @Test
    public void eiOleSamallaPystyTaiVaakaRivillaTest() {
        assert (!NappulaApumetodeja.onSamallaPystyTaiVaakaRivilla(0, 1, 3, 2));
    }

    @Test
    public void onSamallaVinorivilla() {
        assert (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 2, 2));
        assert (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 0, 0));
        assert (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 0, 2));
        assert (NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 2, 0));
    }

    @Test
    public void eiOleSamallaVinorivilla() {
        assert (!NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 2, 1));
        assert (!NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 0, 1));
        assert (!NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 0, 1));
        assert (!NappulaApumetodeja.onSamallaVinoRivilla(1, 1, 1, 2));
    }

    @Test
    public void uhkausLinjaTyha() {
        assert (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(3, 3, this.kuningatar) != null);

        assert (NappulaApumetodeja.uhkausLinjaVino(4, 3, this.kuningatar).isEmpty());
        assert (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(3, 3, this.kuningatar).isEmpty());
    }

    @Test
    public void uhkausLinjaVino() {
        assert (NappulaApumetodeja.uhkausLinjaVino(4, 3, kuningatar).isEmpty());
        assert (NappulaApumetodeja.uhkausLinjaVino(3, 3, this.kuningatar).toString().equals("[[3,3]]"));
        assert (NappulaApumetodeja.uhkausLinjaVino(5, 5, this.kuningatar).toString().equals("[[5,5]]"));
        assert (NappulaApumetodeja.uhkausLinjaVino(3, 5, this.kuningatar).toString().equals("[[3,5]]"));
        assert (NappulaApumetodeja.uhkausLinjaVino(5, 3, this.kuningatar).toString().equals("[[5,3]]"));
    }

    @Test
    public void uhkausLinjaPystyTaiVaaka() {
        assert (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(3, 3, kuningatar).isEmpty());
        assert (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(4, 3, this.kuningatar).toString().equals("[[4,3]]"));
        assert (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(4, 5, this.kuningatar).toString().equals("[[4,5]]"));

        assert (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(3, 4, this.kuningatar).toString().equals("[[3,4]]"));
        assert (NappulaApumetodeja.uhkausLinjaPystyTaiVaaka(5, 4, this.kuningatar).toString().equals("[[5,4]]"));
    }

    @Test
    public void reitllaEiMuitaNappuloitaVinoTest() {
        assert (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(3, 3, kuningatar));
        assert (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(5, 5, kuningatar));
        assert (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(3, 5, kuningatar));
        assert (NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(5, 3, kuningatar));
    }

    @Test
    public void reitllaMuitaNappuloitaVinoTest() {
        this.peliHallinta.uusiPeli();
        this.kuningatar = (Kuningatar) this.peliHallinta.getPeli().getLauta().haeNappula(3, 0);
      
        assert (!NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(5, 2, kuningatar));
        assert (!NappulaApumetodeja.reitillaEiMuitaNappuloitaVino(1, 2, kuningatar));
    }

    
    @Test
    public void reitllaEiMuitaNappuloitaPystyTaiVaakaTest() {
        assert (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(4, 3, kuningatar));
        assert (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(4, 5, kuningatar));
        assert (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(3, 4, kuningatar));
        assert (NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(5, 4, kuningatar));
    }

    @Test
    public void reitllaMuitaNappuloitaPystyTaiVaakaTest() {
        this.peliHallinta.uusiPeli();
        this.kuningatar = (Kuningatar) this.peliHallinta.getPeli().getLauta().haeNappula(3, 0);
        assert (!NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(3, 2, kuningatar));
        assert (!NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(5, 0, kuningatar));
        assert (!NappulaApumetodeja.reitillaEiMuitaNappuloitaPystyTaiVaaka(0, 0, kuningatar));
    }

    @Test
    public void MahdollisetPystyTaiVaakaRuudut(){
        assert(NappulaApumetodeja.mahdollisetPystyTaiVaakaRuudut(this.kuningatar.getX(), this.kuningatar.getY()).toString().equals("[[4,0], [0,4], [4,1], [1,4], [4,2], [2,4], [4,3], [3,4], [4,4], [4,4], [4,5], [5,4], [4,6], [6,4], [4,7], [7,4]]"));
    }

    
    @Test
    public void MahdollisetVinoRuudut(){
        assert(NappulaApumetodeja.mahdollisetVinoRuudut(this.kuningatar.getX(), this.kuningatar.getY()).toString().equals("[[4,4], [5,5], [6,6], [7,7], [4,4], [3,3], [2,2], [1,1], [0,0], [4,4], [5,3], [6,2], [7,1], [4,4], [3,5], [2,6], [1,7]]"));
    }

    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
