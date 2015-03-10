/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import logiikka.peli.Pelaaja;
import logiikka.peli.Peli;
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
public class PeliTest {

    Peli peli;

    public PeliTest() {
    }

    @Before
    public void setUp() {
        peli = new Peli();
    }

    @Test
    public void syonninJalkeenSyotyNappulaEiPelaajanNappuloissa() {
        this.peli.asetaAktiivinen(0, 0);
        this.peli.siirto(0, 7);
        assertEquals(2, this.peli.getVuorossa().getNappulat().size());

    }

    @Test
    public void siirronJalkeenVuorossaOlevaVaihtuu() {
        Pelaaja eka = this.peli.getVuorossa();
        this.peli.asetaAktiivinen(0, 0);
        this.peli.siirto(1, 0);
        assert (!eka.equals(peli.getVuorossa()));
    }

    @Test
    public void epaOnnistuneenSiirronJalkeenVuorossaOlevaEiVaihtuu() {
        Pelaaja eka = this.peli.getVuorossa();
        this.peli.asetaAktiivinen(0, 0);
        this.peli.siirto(-1, 0);
        assert (eka.equals(peli.getVuorossa()));
    }

    @Test
    public void onnistunutSiirtoPalauttaaTrue() {
        this.peli.asetaAktiivinen(0, 0);
        assertEquals(true, this.peli.siirto(1, 0));
    }

    @Test
    public void EpaonnistunutSiirtoPalauttaaFalse() {
        this.peli.asetaAktiivinen(0, 0);
        assertEquals(false, this.peli.siirto(7, 0));//oman päälle
    }

    @Test
    public void JosEiAktiivinenEpaonnistunutSiirtoPalauttaaFalse() {
        assertEquals(false, this.peli.siirto(1, 0));
    }

    @Test
    public void KuninkaanUhatuksiJattaminenPalauttaaSiirroltaFalse() {
        this.peli.asetaAktiivinen(3, 0);
        this.peli.siirto(4, 0);
        this.peli.asetaAktiivinen(0, 7);
        this.peli.siirto(1, 7);

        this.peli.asetaAktiivinen(0, 0);
        this.peli.siirto(3, 0);
        this.peli.asetaAktiivinen(3, 7);

        assertEquals(false, this.peli.siirto(3, 6));
    }

    @Test
    public void KuninkaanUhatuksiJattaminenEiTeeSiirtoa() {
        this.peli.asetaAktiivinen(3, 0);
        this.peli.siirto(4, 0);
        this.peli.asetaAktiivinen(0, 7);
        this.peli.siirto(1, 7);

        this.peli.asetaAktiivinen(0, 0);
        this.peli.siirto(3, 0);
        this.peli.asetaAktiivinen(3, 7);

        assertEquals(3, this.peli.getAktiivinen().getX());
        assertEquals(7, this.peli.getAktiivinen().getY());
    }

    @Test
    public void OnnistuneenSiirronJalkeenAktiivinenOnNull() {
        this.peli.asetaAktiivinen(0, 0);
        this.peli.siirto(1, 0);
        assertEquals(null, this.peli.getAktiivinen());
    }

    @Test
    public void KertooOikeinUhataankoRuutuaKunUhataan() {
        assertEquals(true, this.peli.onkoUhattuna(0, 0));

    }

    @Test
    public void KertooOikeinUhataankoRuutuaKunEiUhata() {
        assertEquals(false, this.peli.onkoUhattuna(3, 0));

    }

    @Test
    public void KertooOikeinKukaUhkaaKunUhataan() {
        assertEquals(peli.getLauta().haeNappula(0, 7), peli.uhkaavaNappula(0, 0));
    }

    @Test
    public void KertooOikeinKukaUhkaaKunEiUhata() {
        assertEquals(null, peli.uhkaavaNappula(3, 3));
    }

    @Test

    public void KertooOikeinUhkaakoUseampiKunKukaanEiUhkaa() {
        assertEquals(false, peli.uhkaakoUseampi(3, 3));
    }

    @Test
    public void KertooOikeinUhkaakoUseampiKunYksiUhkaa() {
        assertEquals(false, peli.uhkaakoUseampi(0, 0));
    }

    @Test
    public void KertooOikeinUhkaakoUseampiKunKaksiUhkaa() {
        peli.asetaAktiivinen(0, 0);
        peli.siirto(0, 3);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 3);

        assertEquals(true, peli.uhkaakoUseampi(0, 3));
    }

    @Test
    public void KunkkuVoiLiikkua() {
        assertEquals(false, peli.kunkkuEiVoiLiikkua());
    }

    @Test
    public void KunkkuEiVoiLiikkua() {
        peli.asetaAktiivinen(0, 0);
        peli.siirto(2, 0);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(7, 0);
        peli.siirto(4, 0);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 0);
        peli.siirto(3, 1);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(3, 1);
        peli.siirto(3, 2);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 2);
        peli.siirto(3, 3);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(3, 3);
        peli.siirto(3, 4);
        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 4);
        peli.siirto(3, 5);

        assertEquals(true, peli.kunkkuEiVoiLiikkua());

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        assertEquals(false, peli.kunkkuEiVoiLiikkua());

    }

    @Test
    public void KunMattiNiinMatti() {
        peli.asetaAktiivinen(0, 0);
        peli.siirto(2, 0);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(7, 0);
        peli.siirto(5, 0);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 0);
        peli.siirto(3, 1);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(3, 1);
        peli.siirto(3, 2);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 2);
        peli.siirto(3, 3);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(3, 3);
        peli.siirto(3, 4);
        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 4);
        peli.siirto(3, 5);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(5, 0);
        peli.siirto(5, 7);

//        peli.tulosta();
        assertEquals(true, peli.onMatissa());

    }

    @Test
    public void KunEiMattiNiinEiMatti() {
        peli.asetaAktiivinen(0, 0);
        peli.siirto(2, 0);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(7, 0);
        peli.siirto(4, 0);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 0);
        peli.siirto(3, 1);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(3, 1);
        peli.siirto(3, 2);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 2);
        peli.siirto(3, 3);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(3, 3);
        peli.siirto(3, 4);
        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 4);
        peli.siirto(3, 5);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(4, 0);
        peli.siirto(4, 7);

        //peli.tulosta();
        assertEquals(false, peli.onMatissa());

    }

    @Test
    public void KunVoiBlokatNiinEiMatti() {
        peli.asetaAktiivinen(0, 0);
        peli.siirto(2, 0);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(7, 0);
        peli.siirto(5, 0);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 0);
        peli.siirto(3, 1);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(3, 1);
        peli.siirto(3, 2);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 2);
        peli.siirto(3, 3);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(3, 3);
        peli.siirto(3, 4);
        peli.asetaAktiivinen(7, 1);
        peli.siirto(4, 1);

        peli.asetaAktiivinen(3, 4);
        peli.siirto(3, 5);

        peli.asetaAktiivinen(0, 7);
        peli.siirto(0, 1);

        peli.asetaAktiivinen(5, 0);
        peli.siirto(5, 7);

        //  peli.tulosta();
        assertEquals(true, peli.voiBlokata());
        assertEquals(false, peli.onMatissa());

    }

    @Test
    public void KunVoiSyodaNiinEiMatti() {
        peli.asetaAktiivinen(0, 0);
        peli.siirto(2, 0);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(7, 0);
        peli.siirto(5, 0);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 0);
        peli.siirto(3, 1);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(3, 1);
        peli.siirto(3, 2);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 2);
        peli.siirto(3, 3);

        peli.asetaAktiivinen(7, 7);
        peli.siirto(7, 1);

        peli.asetaAktiivinen(3, 3);
        peli.siirto(3, 4);

        peli.asetaAktiivinen(7, 1);
        peli.siirto(7, 7);

        peli.asetaAktiivinen(3, 4);
        peli.siirto(3, 5);

        peli.asetaAktiivinen(0, 7);
        peli.siirto(0, 1);

        peli.asetaAktiivinen(5, 0);
        peli.siirto(5, 7);

//        peli.tulosta();
        assertEquals(true, peli.voiSyodaNappulan(peli.uhkaavaNappula(3, 7)));
        assertEquals(false, peli.onMatissa());

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
