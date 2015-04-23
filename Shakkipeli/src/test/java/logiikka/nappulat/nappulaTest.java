package logiikka.nappulat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static logiikka.peli.Maa.MUSTA;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.nappulat.Nappula;
import logiikka.nappulat.Torni;
import logiikka.peli.Pelilauta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elias
 */
public class nappulaTest {

    private Pelilauta testiLauta;
    Nappula torni;

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
        assertTrue(this.torni.kohdeSamaKuinOmaSijainti(1, 0));
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

}
