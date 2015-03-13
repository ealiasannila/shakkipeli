/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import logiikka.peli.Pelaaja;
import logiikka.peli.Peli;
import logiikka.peli.PeliHallinta;
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

    PeliHallinta peliHallinta;
    Peli peli;

    public PeliTest() {
    }

    @Before
    public void setUp() {
        this.peliHallinta = new PeliHallinta();
        peli = this.peliHallinta.getPeli();
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/testiOletus.txt");

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
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/useampiUhkaa.txt");
        assertEquals(true, peli.uhkaakoUseampi(0, 7));
    }

    @Test
    public void KunkkuVoiLiikkua() {
        assertEquals(false, peli.kunkkuEiVoiLiikkua());
    }

    @Test
    public void KunkkuEiVoiLiikkua() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuEiVoiLiikkua.txt");

        assertEquals(true, peli.kunkkuEiVoiLiikkua());

    }

    @Test
    public void KunMattiNiinMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuMatissa.txt");

        assertEquals(true, peli.onMatissa());

    }

    @Test
    public void KunEiMattiNiinEiMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuEiVoiLiikkua.txt");

        assertEquals(false, peli.onMatissa());

    }

    @Test
    public void KunVoiBlokatNiinEiMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuUhattunaVoiBlokata.txt");

        assertEquals(true, peli.voiBlokata());
        assertEquals(false, peli.onMatissa());

    }

    @Test
    public void KunVoiSyodaNiinEiMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuUhattunaVoiSyoda.txt");

        assertEquals(true, peli.voiSyodaNappulan(peli.uhkaavaNappula(0, 7)));
        assertEquals(false, peli.onMatissa());

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
