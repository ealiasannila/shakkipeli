package logiikka.peli;

import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.KelloPiirto;
import logiikka.nappulat.Sotilas;
import logiikka.peli.Ajastin;
import logiikka.peli.Kello;
import static logiikka.peli.Maa.MUSTA;
import logiikka.peli.Peli;
import logiikka.peli.PeliHallinta;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elias
 */
public class AjastinTest {

    private Kello kello;
    private Ajastin ajastin;
    private Peli peli;

    @Before
    public void setUp() {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        PeliHallinta pelihallinta = kayttoliittyma.getPeliHallinta();
        peli = pelihallinta.getPeli();
        pelihallinta.uusiPeli(10, 10);
        this.kello = peli.getVuorossa().getKello();
        this.kello.setKellonPiirto(new KelloPiirto(kayttoliittyma, 10, "perusosa"));
        this.peli.getVastustaja().getKello().setKellonPiirto(new KelloPiirto(kayttoliittyma, 10, "perusosa"));
        this.ajastin = peli.getAjastin();
    }

    @Test(timeout = 3000)
    public void paivittaaKellon() throws InterruptedException {
        Thread.sleep(2000);
        assertEquals(8, kello.getAika());
    }

    @Test(timeout = 3000)
    public void pysayttaa() throws InterruptedException {
        Thread.sleep(1000);
        ajastin.pysayta();
        Thread.sleep(1000);
        assertEquals(9, kello.getAika());
    }

    @Test(timeout = 3000)
    public void paivitaaKunKorotusOnKesken() throws InterruptedException {
        peli.getVastustaja().setKorotettava(new Sotilas(MUSTA, 0, 0, this.peli.getLauta()));
        Thread.sleep(2000);
        assertEquals(8, this.peli.getVastustaja().getKello().getAika());
        assertEquals(10, this.kello.getAika());
    }

}
