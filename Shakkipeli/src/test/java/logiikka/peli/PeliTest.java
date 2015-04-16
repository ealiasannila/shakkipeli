package logiikka.peli;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import logiikka.nappulat.Torni;
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

    public PeliTest() {
    }

    @Before
    public void setUp() {
        this.peliHallinta = new PeliHallinta();
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/testiOletus.txt");

    }

    @Test
    public void syonninJalkeenSyotyNappulaEiPelaajanNappuloissa() {
        this.peliHallinta.getPeli().asetaAktiivinen(0, 0);
        this.peliHallinta.getPeli().siirto(0, 7);
        assertEquals(2, this.peliHallinta.getPeli().getVuorossa().getNappulat().size());

    }

    @Test
    public void siirronJalkeenVuorossaOlevaVaihtuu() {
        Pelaaja eka = this.peliHallinta.getPeli().getVuorossa();
        this.peliHallinta.getPeli().asetaAktiivinen(0, 0);
        this.peliHallinta.getPeli().siirto(1, 0);
        assertFalse(eka.equals(peliHallinta.getPeli().getVuorossa()));
    }

    @Test
    public void epaOnnistuneenSiirronJalkeenVuorossaOlevaEiVaihdu() {
        Pelaaja eka = this.peliHallinta.getPeli().getVuorossa();
        this.peliHallinta.getPeli().asetaAktiivinen(0, 0);
        this.peliHallinta.getPeli().siirto(-1, 0);
        assertEquals(eka, (peliHallinta.getPeli().getVuorossa()));
    }

    @Test
    public void onnistunutSiirtoPalauttaaTrue() {
        this.peliHallinta.getPeli().asetaAktiivinen(0, 0);
        assertEquals(true, this.peliHallinta.getPeli().siirto(1, 0));
    }

    @Test
    public void epaonnistunutSiirtoPalauttaaFalse() {
        this.peliHallinta.getPeli().asetaAktiivinen(0, 0);
        assertEquals(false, this.peliHallinta.getPeli().siirto(8, 0));//laudan ulkopuolelle
        assertEquals(false, this.peliHallinta.getPeli().siirto(-6, 0));//laudan ulkopuolelle
        assertEquals(false, this.peliHallinta.getPeli().siirto(6, 0));//oman läpi
        assertEquals(false, this.peliHallinta.getPeli().siirto(4, 0));//oman päälle
        assertEquals(false, this.peliHallinta.getPeli().siirto(7, 7));//ei liiku näin

    }

    @Test
    public void josEiAktiivinenEpaonnistunutSiirtoPalauttaaFalse() {
        assertEquals(false, this.peliHallinta.getPeli().siirto(1, 0));
    }

    @Test
    public void kuninkaanUhatuksiSiirtaminenPalauttaaFalse() {
        this.peliHallinta.getPeli().asetaAktiivinen(3, 0);
        this.peliHallinta.getPeli().siirto(4, 0);
        this.peliHallinta.getPeli().asetaAktiivinen(0, 7);
        this.peliHallinta.getPeli().siirto(1, 7);

        this.peliHallinta.getPeli().asetaAktiivinen(0, 0);
        this.peliHallinta.getPeli().siirto(3, 0);
        this.peliHallinta.getPeli().asetaAktiivinen(3, 7);

        assertEquals(false, this.peliHallinta.getPeli().siirto(3, 6));
    }

    @Test
    public void kuninkaanUhatuksiPaljastaminenEiTeeSiirtoa() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkunPaljastavaSiirto.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(0, 5);
        this.peliHallinta.getPeli().siirto(1, 4);
        assertEquals("MUSTA\n"
                + "Kooooooo\n"
                + "oooooooo\n"
                + "Looooooo\n"
                + "tooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n" 
                + "-1\n"
                + "-1", this.peliHallinta.getPeli().toString());
    }

    @Test
    public void kuninkaanUhatuksiJattaminenEiTeeSiirtoa() {
        this.peliHallinta.getPeli().asetaAktiivinen(3, 0);
        this.peliHallinta.getPeli().siirto(4, 0);
        this.peliHallinta.getPeli().asetaAktiivinen(0, 7);
        this.peliHallinta.getPeli().siirto(1, 7);

        this.peliHallinta.getPeli().asetaAktiivinen(0, 0);
        this.peliHallinta.getPeli().siirto(3, 0);
        this.peliHallinta.getPeli().asetaAktiivinen(3, 7);
        this.peliHallinta.getPeli().siirto(3, 6);//ei pitäisi toteutua

        assertEquals("MUSTA\n"
                + "oToKoooT\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oootkoot\n" 
                + "-1\n"
                + "-1", this.peliHallinta.getPeli().toString());
        assertEquals(3, this.peliHallinta.getPeli().getAktiivinen().getX());
        assertEquals(7, this.peliHallinta.getPeli().getAktiivinen().getY());
    }

    @Test
    public void onnistuneenSiirronJalkeenAktiivinenOnNull() {
        this.peliHallinta.getPeli().asetaAktiivinen(0, 0);
        this.peliHallinta.getPeli().siirto(1, 0);
        assertEquals(null, this.peliHallinta.getPeli().getAktiivinen());
    }

    @Test
    public void onShakissaTest() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuMatissa.txt");
        assertTrue(this.peliHallinta.getPeli().onShakissa());
    }

    @Test
    public void kunMattiNiinMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuMatissa.txt");

        assertEquals(true, peliHallinta.getPeli().onMatissa());

    }

    @Test
    public void kunEiMattiNiinEiMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuEiVoiLiikkua.txt");

        assertEquals(false, peliHallinta.getPeli().onMatissa());

    }

    @Test
    public void kunKunkkuVoiLiikkuaEiMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuUhattunaVoiLiikkua.txt");
        assertFalse(this.peliHallinta.getPeli().onMatissa());
        assertTrue(this.peliHallinta.getPeli().onShakissa());
    }

    @Test
    public void kunVoiBlokatNiinEiMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuUhattunaVoiBlokata.txt");

        assertEquals(false, peliHallinta.getPeli().onMatissa());

    }

    @Test
    public void kunEiVoiBlokatNiinMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuUhattunaEiVoiBlokata.txt");

        assertEquals(true, peliHallinta.getPeli().onMatissa());

    }

    @Test
    public void kunVoiSyodaNiinEiMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/kunkkuUhattunaVoiSyoda.txt");

        assertEquals(false, peliHallinta.getPeli().onMatissa());

    }

    @Test
    public void kunkkuEiVoiSyodaSuojattua() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/mattiKunkkuEiVoiSyodaSuojattua.txt");
        assertTrue(this.peliHallinta.getPeli().onMatissa());
    }

    @Test
    public void kaksiUhkaaMattiMusta() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/mattiKaksiUhkaaMusta.txt");
        assertTrue(this.peliHallinta.getPeli().onMatissa());
    }

    @Test
    public void kaksiUhkaaMattiValkoinen() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/mattiKaksiUhkaaValkoinen.txt");
        assertTrue(this.peliHallinta.getPeli().onMatissa());
    }

    @Test
    public void kaksiUhkaaEiMatti() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/shakkiKaksiUhkaa.txt");
        assertFalse(this.peliHallinta.getPeli().onMatissa());
        assertTrue(this.peliHallinta.getPeli().onShakissa());
    }

    //TORNITUKSEN TESTAUS:
    @Test
    public void voiTehdaLyhyenTornituksenValkoinen() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/valkoinenTornitus.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(4, 0);
        assertTrue(this.peliHallinta.getPeli().siirto(6, 0));
        assertTrue(this.peliHallinta.getPeli().getLauta().haeNappula(6, 0) == (this.peliHallinta.getPeli().getValkoinen().getKunkku()));
        assertTrue(this.peliHallinta.getPeli().getLauta().haeNappula(5, 0).getClass() == (Torni.class));

    }

    @Test
    public void voiTehdaPitkanTornituksenValkoinen() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/valkoinenTornitus.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(4, 0);
        assertTrue(this.peliHallinta.getPeli().siirto(2, 0));
        assertTrue(this.peliHallinta.getPeli().getLauta().haeNappula(2, 0) == (this.peliHallinta.getPeli().getValkoinen().getKunkku()));
        assertTrue(this.peliHallinta.getPeli().getLauta().haeNappula(3, 0).getClass() == (Torni.class));

    }

    @Test
    public void voiTehdaLyhyenTornituksenMusta() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/mustaTornitus.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(4, 7);
        assertTrue(this.peliHallinta.getPeli().siirto(6, 7));
        assertTrue(this.peliHallinta.getPeli().getLauta().haeNappula(6, 7) == this.peliHallinta.getPeli().getMusta().getKunkku());
        assertTrue(this.peliHallinta.getPeli().getLauta().haeNappula(5, 7).getClass() == Torni.class);

    }

    @Test
    public void voiTehdaPitkanTornituksenMusta() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/mustaTornitus.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(4, 7);
        assertTrue(this.peliHallinta.getPeli().siirto(2, 7));
        assertTrue(this.peliHallinta.getPeli().getLauta().haeNappula(2, 7) == (this.peliHallinta.getPeli().getMusta().getKunkku()));
        assertTrue(this.peliHallinta.getPeli().getLauta().haeNappula(3, 7).getClass() == (Torni.class));

    }

    @Test
    public void voiTehdaOhestaLyonnin() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/ohestaLyonti.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(1, 1);
        this.peliHallinta.getPeli().siirto(1, 3);
        assertEquals(this.peliHallinta.getPeli().toString(), ("MUSTA\n"
                + "ooooKooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooQoooo\n"
                + "osoooooo\n"
                + "ohoooooo\n"
                + "sossssss\n"
                + "toookoot\n"
                + "-1\n"
                + "-1"));
        this.peliHallinta.getPeli().asetaAktiivinen(3, 4);
        this.peliHallinta.getPeli().siirto(1, 2);
        assertEquals(this.peliHallinta.getPeli().toString(), ("VALKOINEN\n"
                + "ooooKooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oQoooooo\n"
                + "sossssss\n"
                + "toookoot\n" 
                + "-1\n"
                + "-1"));
    }

    @Test
    public void poistaaHaamuSotilaan() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/ohestaLyonti.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(1, 1);
        this.peliHallinta.getPeli().siirto(1, 3);
        assertEquals(this.peliHallinta.getPeli().toString(), ("MUSTA\n"
                + "ooooKooo\n"
                + "oooooooo\n"
                + "oooooooo\n"
                + "oooQoooo\n"
                + "osoooooo\n"
                + "ohoooooo\n"
                + "sossssss\n"
                + "toookoot\n" 
                + "-1\n"
                + "-1"));
        this.peliHallinta.getPeli().asetaAktiivinen(3, 4);
        this.peliHallinta.getPeli().siirto(3, 5);
        assertEquals(this.peliHallinta.getPeli().toString(), ("VALKOINEN\n"
                + "ooooKooo\n"
                + "oooooooo\n"
                + "oooQoooo\n"
                + "oooooooo\n"
                + "osoooooo\n"
                + "oooooooo\n"
                + "sossssss\n"
                + "toookoot\n" 
                + "-1\n"
                + "-1"));
    }

    @Test
    public void eiOlePatissa() {
        assertFalse(this.peliHallinta.getPeli().onPatissa());

    }

    @Test
    public void eiOlePattiToinenVoiTehdaMatinKaksiRatsua() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/eiPattiOnKuningatar.txt");
        assertFalse(this.peliHallinta.getPeli().onPatissa());
    }

    @Test
    public void eiOlePattiToinenVoiTehdaMatinKaksiLahettiaSamallaVarilla() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/eiPattiOnKuningatarLahetitSamallaVarilla.txt");
        assertFalse(this.peliHallinta.getPeli().onPatissa());
    }

    @Test
    public void pattiTestEiVoiLiikkua() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/pattiEiVoiLiikkua.txt");
        assertTrue(this.peliHallinta.getPeli().onPatissa());

    }

    @Test
    public void pattiTestVainKunkut() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/pattiVainKunkut.txt");
        assertTrue(this.peliHallinta.getPeli().onPatissa());

    }

    @Test
    public void pattiTestVainKunkkuJaRatsu() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/pattiKunkkuJaRatsu.txt");
        assertTrue(this.peliHallinta.getPeli().onPatissa());

    }

    @Test
    public void pattiTestVainKunkkuJaLahettejaSamallaVarilla() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/pattiKunkkuJaLahettejaSamallaVarilla.txt");
        assertTrue(this.peliHallinta.getPeli().onPatissa());

    }

    @Test
    public void pattiTestVainKunkkuJaLahettejaEriVarilla() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/pattiKunkkuJaLahettejaEriVarilla.txt");
        assertFalse(this.peliHallinta.getPeli().onPatissa());

    }

    @Test
    public void pattiTestVainKunkkuJa2Ratsua() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/pattiKunkkuJa2Ratsua.txt");
        assertFalse(this.peliHallinta.getPeli().onPatissa());

    }

    @Test
    public void sotilaanKorotusTest() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/korotus.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(0, 6);
        this.peliHallinta.getPeli().siirto(0, 7);
        assertEquals(this.peliHallinta.getPeli().getValkoinen().getKorotettava(), (this.peliHallinta.getPeli().getLauta().haeNappula(0, 7)));
    }

    @Test
    public void korottaaLahetiksi() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/korotus.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(0, 6);
        this.peliHallinta.getPeli().siirto(0, 7);
        this.peliHallinta.getPeli().korotaSotilas(this.peliHallinta.getPeli().getValkoinen(), 'l');
        assertEquals("MUSTA\n"
                + "looooooo\n"
                + "osssssss\n"
                + "oooKoooo\n"
                + "oooooooo\n"
                + "oookoooo\n"
                + "oooooooo\n"
                + "SSSSSSSS\n"
                + "oooooooo\n" 
                + "-1\n"
                + "-1", this.peliHallinta.getPeli().toString());

    }

    @Test
    public void korottaaTorniksi() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/korotus.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(0, 6);
        this.peliHallinta.getPeli().siirto(0, 7);
        this.peliHallinta.getPeli().korotaSotilas(this.peliHallinta.getPeli().getValkoinen(), 't');
        assertEquals("MUSTA\n"
                + "tooooooo\n"
                + "osssssss\n"
                + "oooKoooo\n"
                + "oooooooo\n"
                + "oookoooo\n"
                + "oooooooo\n"
                + "SSSSSSSS\n"
                + "oooooooo\n" 
                + "-1\n"
                + "-1", this.peliHallinta.getPeli().toString());

    }

    @Test
    public void korottaaRatsuksi() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/korotus.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(0, 6);
        this.peliHallinta.getPeli().siirto(0, 7);
        this.peliHallinta.getPeli().korotaSotilas(this.peliHallinta.getPeli().getValkoinen(), 'r');
        assertEquals("MUSTA\n"
                + "rooooooo\n"
                + "osssssss\n"
                + "oooKoooo\n"
                + "oooooooo\n"
                + "oookoooo\n"
                + "oooooooo\n"
                + "SSSSSSSS\n"
                + "oooooooo\n" 
                + "-1\n"
                + "-1", this.peliHallinta.getPeli().toString());

    }

    @Test
    public void korottaaKuningattareksi() {
        this.peliHallinta.lataaPeli("tallennetutPelit/testiTilanteet/korotus.txt");
        this.peliHallinta.getPeli().asetaAktiivinen(0, 6);
        this.peliHallinta.getPeli().siirto(0, 7);
        this.peliHallinta.getPeli().korotaSotilas(this.peliHallinta.getPeli().getValkoinen(), 'q');
        assertEquals("MUSTA\n"
                + "qooooooo\n"
                + "osssssss\n"
                + "oooKoooo\n"
                + "oooooooo\n"
                + "oookoooo\n"
                + "oooooooo\n"
                + "SSSSSSSS\n"
                + "oooooooo\n" 
                + "-1\n"
                + "-1", this.peliHallinta.getPeli().toString());

    }

}
