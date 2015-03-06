package logiikka.nappulat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import static logiikka.nappulat.Maa.*;
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
public class TorniTest {

    private Pelilauta testiLauta;
    Nappula torni;

    public TorniTest() {
    }

    @Before
    public void setUp() {
        testiLauta = new Pelilauta();

        torni = new Torni(VALKOINEN, 1, 0, testiLauta);

    }

    ///TESTAA KONSTRUKTORIA:
    @Test
    public void maaTuleeKonstruktorissaOikein() {
        assertEquals(VALKOINEN, torni.getMaa());
    }

    @Test
    public void xTuleeKonstruktorissaOikein() {
        assertEquals(1, torni.getX());
    }

    @Test
    public void yTuleeKonstruktorissaOikein() {
        assertEquals(0, torni.getY());
    }

    @Test
    public void lautaTuleeKonstruktorissaOikein() {
        assertEquals(this.testiLauta, torni.getLauta());
    }

    //NAPPULAN YLEISET METODIT:
    @Test
    public void eiSalliLaudanAlapuolelleLiikkumista() {
        assertEquals(false, torni.onSallittuSiirto(1, -1));
    }

    @Test
    public void eiSalliLaudanVasemmallePuolelleLiikkumista() {
        assertEquals(false, torni.onSallittuSiirto(-1, 0));
    }

    @Test
    public void eiSalliLaudanYlapuolelleLiikkumista() {
        assertEquals(false, torni.onSallittuSiirto(1, 8));
    }

    @Test
    public void eiSalliLaudanOikealleLiikkumista() {
        assertEquals(false, torni.onSallittuSiirto(8, 0));
    }

    @Test
    public void eiSalliPaikallaanPysymista() {
        assertEquals(false, torni.onSallittuSiirto(1, 0));
    }

    @Test
    public void salliiLaudanAlareunaanLiikkumisen() {
        assertEquals(true, torni.onSallittuSiirto(0, 0));
    }

    @Test
    public void salliiLaudanYlareunaanLiikkumisen() {
        assertEquals(true, torni.onSallittuSiirto(1, 7));
    }

    @Test
    public void salliiLaudanVasempaanReunaanLiikkumisen() {
        assertEquals(true, torni.onSallittuSiirto(0, 0));
    }

    @Test
    public void salliiLaudanOikeaanReunaanLiikkumisen() {
        assertEquals(true, torni.onSallittuSiirto(7, 0));
    }

    @Test
    public void eiSalliiOmanPaalleLiikkumista() {
        Nappula omaTorni = new Torni(VALKOINEN, 1, 5, this.testiLauta);

        assertEquals(false, torni.onSallittuSiirto(1, 5));
    }

    @Test
    public void eiSalliiOmanLapiLiikkumista() {
        Nappula omaTorni = new Torni(VALKOINEN, 1, 5, this.testiLauta);

        assertEquals(false, torni.onSallittuSiirto(1, 7));
    }

    @Test
    public void eiSalliiVastustajanLapiLiikkumista() {
        Nappula vastustajanTorni = new Torni(MUSTA, 1, 5, this.testiLauta);

        assertEquals(false, torni.onSallittuSiirto(1, 7));
    }

    @Test
    public void SalliiVastustajanPaalleLiikkumisen() {
        Nappula omaTorni = new Torni(MUSTA, 1, 5, this.testiLauta);

        assertEquals(true, torni.onSallittuSiirto(1, 5));
    }

    @Test
    public void TunnistaOmaKohdeOnOma() {
        Nappula omaTorni = new Torni(VALKOINEN, 1, 5, this.testiLauta);
        assertEquals(true, torni.tarkistaOnkoKohdeOma(1, 5));
    }

    @Test
    public void TunnistaOmaKohdeEiOleOma() {
        Nappula vastustajanTorni = new Torni(MUSTA, 1, 5, this.testiLauta);
        assertEquals(false, torni.tarkistaOnkoKohdeOma(1, 5));
    }

    @Test
    public void TunnistaVastustajanKohdeOnOma() {
        Nappula omaTorni = new Torni(VALKOINEN, 1, 5, this.testiLauta);
        assertEquals(false, torni.tarkistaOnkoKohdeVastustajan(1, 5));
    }

    @Test
    public void TunnistaVastustajanKohdeEiOleOma() {
        Nappula vastustajanTorni = new Torni(MUSTA, 1, 5, this.testiLauta);
        assertEquals(true, torni.tarkistaOnkoKohdeVastustajan(1, 5));
    }

    @Test
    public void TunnistaOnkoKohdeVapaKohdeOnOma() {
        Nappula omaTorni = new Torni(VALKOINEN, 1, 5, this.testiLauta);
        assertEquals(false, torni.tarkistaOnkoKohdeVapaa(1, 5));
    }

    @Test
    public void TunnistaOnkoKohdeVapaKohdeOnVastustajan() {
        Nappula vastustajanTorni = new Torni(MUSTA, 1, 5, this.testiLauta);
        assertEquals(false, torni.tarkistaOnkoKohdeVapaa(1, 5));
    }

    @Test
    public void TunnistaOnkoKohdeVapaKohdeOnVapaa() {
        assertEquals(true, torni.tarkistaOnkoKohdeVapaa(1, 5));
    }

    @Test
    public void TunnistaOnkoKohdeVapaKohdeOnVastustajanKohteenViereen() {
        Nappula vastustajanTorni = new Torni(MUSTA, 1, 5, this.testiLauta);
        assertEquals(true, torni.tarkistaOnkoKohdeVapaa(1, 6));
    }

    //TORNIN OMAT TESTIT:
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
        ArrayList<Ruutu> uhatutRuudut = torni.uhkausLinja(1, 5);
        System.out.println(uhatutRuudut);
        assert (uhatutRuudut.contains(new Ruutu(1, 5)));
        assert (uhatutRuudut.contains(new Ruutu(1, 4)));
        assert (uhatutRuudut.contains(new Ruutu(1, 3)));
        assert (uhatutRuudut.contains(new Ruutu(1, 2)));
        assert (uhatutRuudut.contains(new Ruutu(1, 1)));
        assertEquals(5, uhatutRuudut.size());
    }
    
    @Test
    public void UhkaaOikeitaRuutujaVastustajanNappula() {
        Nappula vastustajanTorni = new Torni(MUSTA, 1, 5, this.testiLauta);
        ArrayList<Ruutu> uhatutRuudut = torni.uhkausLinja(1, 5);
       // assert (uhatutRuudut.contains(new Ruutu(1, 5)));
        assert (uhatutRuudut.contains(new Ruutu(1, 4)));
        assert (uhatutRuudut.contains(new Ruutu(1, 3)));
        assert (uhatutRuudut.contains(new Ruutu(1, 2)));
        assert (uhatutRuudut.contains(new Ruutu(1, 1)));
        assertEquals(5, uhatutRuudut.size());
    }

    @Test
    public void UhkaaOikeitaRuutujaOmaNappula() {
        Nappula omaTorni = new Torni(VALKOINEN, 1, 5, this.testiLauta);
        ArrayList<Ruutu> uhatutRuudut = torni.uhkausLinja(1, 5);
        assert (uhatutRuudut.contains(new Ruutu(1, 5)));
        assert (uhatutRuudut.contains(new Ruutu(1, 4)));
        assert (uhatutRuudut.contains(new Ruutu(1, 3)));
        assert (uhatutRuudut.contains(new Ruutu(1, 2)));
        assert (uhatutRuudut.contains(new Ruutu(1, 1)));
        assertEquals(5, uhatutRuudut.size());
    }

    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
