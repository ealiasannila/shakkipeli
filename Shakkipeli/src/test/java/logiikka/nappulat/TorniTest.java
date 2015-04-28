package logiikka.nappulat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import static logiikka.peli.Maa.*;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elias
 */
public class TorniTest {

    private Pelilauta testiLauta;
    Nappula torni;

    @Before
    public void setUp() {
        testiLauta = new Pelilauta();

        torni = new Torni(VALKOINEN, 1, 0, testiLauta);

    }

    @Test
    public void LiikuVinottainEtuVasen() {
        assertEquals(false, torni.onSallittuSiirto(0, 1));
    }

    @Test
    public void LiikuVinottainEtuOikee() {
        assertEquals(false, torni.onSallittuSiirto(2, 1));
    }

    @Test
    public void LiikuVinottainTakaOikee() {
        torni = new Torni(VALKOINEN, 1, 1, this.testiLauta);
        assertEquals(false, torni.onSallittuSiirto(2, 0));
    }

    @Test
    public void LiikuVinottainTakaVasen() {

        torni = new Torni(VALKOINEN, 1, 1, this.testiLauta);
        assertEquals(false, torni.onSallittuSiirto(0, 0));
    }

    @Test
    public void UhkaaOikeitaRuutujaTyhjaRuutu() {
        ArrayList<Ruutu> uhatutRuudut = torni.nappulanReitti(1, 5);
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 5)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 4)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 3)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 2)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 1)));
        assertEquals(5, uhatutRuudut.size());
    }

    @Test
    public void UhkaaOikeitaRuutujaVastustajanNappula() {
        Nappula vastustajanTorni = new Torni(MUSTA, 1, 5, this.testiLauta);
        ArrayList<Ruutu> uhatutRuudut = torni.nappulanReitti(1, 5);
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 4)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 3)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 2)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 1)));
        assertEquals(5, uhatutRuudut.size());
    }

    @Test
    public void UhkaaOikeitaRuutujaOmaNappula() {
        Nappula omaTorni = new Torni(VALKOINEN, 1, 5, this.testiLauta);
        ArrayList<Ruutu> uhatutRuudut = torni.nappulanReitti(1, 5);
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 5)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 4)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 3)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 2)));
        assertTrue(uhatutRuudut.contains(new Ruutu(1, 1)));
        assertEquals(5, uhatutRuudut.size());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
