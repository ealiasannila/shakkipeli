/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.util.ArrayList;
import logiikka.nappulat.Kunkku;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.nappulat.Nappula;

/**
 * Pitää tallessa tietoa pelaajan nappuloihin liittyvästä erikoisinformaatiosta.
 * Esim. mikä nappula on pelaajan kuningas
 *
 */
public class Pelaaja {

    private Kello kello;

    private ArrayList<Nappula> nappulat;
    private Kunkku kunkku;
    private int ohestaLyontiX;
    private int sotilaastaSeuraavaRivi;
    private int kaksiRiviaSotilaasta;
    private Maa maa;
    private Nappula korotettava;
    private int perusRivi;
    private int sotilasRivi;

    /**
     * asettaa pelaajan tiedot riippuen maasta.
     *
     * @param maa
     */
    public Pelaaja(Maa maa) {
        this.maa = maa;
        this.nappulat = new ArrayList<Nappula>();
        this.ohestaLyontiX = -1;
        this.kello = new Kello(5);
        if (maa == VALKOINEN) {
            this.sotilaastaSeuraavaRivi = 2;
            this.kaksiRiviaSotilaasta = 3;
            this.perusRivi = 0;
            this.sotilasRivi = 1;
        } else {
            this.sotilaastaSeuraavaRivi = 5;
            this.kaksiRiviaSotilaasta = 4;
            this.perusRivi = 7;
            this.sotilasRivi = 6;
        }
    }

    /**
     * palauttaa pelaajan korotettavana olevan sotilaan
     *
     * @return
     */
    public Nappula getKorotettava() {
        return korotettava;
    }

    /**
     * asettaa pelaajan sotilaan korotettavaksi odottamaan pelaajan valintaa,
     * miksi korotetaan
     *
     * @param korotettava
     */
    public void setKorotettava(Nappula korotettava) {
        this.korotettava = korotettava;
    }

    /**
     * rivi jolla on pelaajan upseerit alussa
     *
     * @return
     */
    public int getPerusRivi() {
        return perusRivi;
    }

    /**
     * rivi jolla pelaajan sotilaat ovat alussa
     *
     * @return
     */
    public int getSotilasRivi() {
        return sotilasRivi;
    }

    /**
     * kertoo miltä riviltä löytyy ohestalyönnillä syötävät nappulat
     *
     * @return
     */
    public int getKaksiRiviaSotilaasta() {
        return kaksiRiviaSotilaasta;
    }

    /**
     * kertoo miltä riviltä ohestälyötävät nappulat voidaan syödä (sotilasrivi
     * +/- 1)
     *
     * @return
     */
    public int getSotilaastaSeuraavaRivi() {
        return sotilaastaSeuraavaRivi;
    }

    /**
     * kertoo mistä x koordinaatista ohestalyötävä nappula (sotilas joka on
     * juuri liikkunut 2) löytyy jos mikään sotilas ei ole juuri liikkunut kahta
     * palauttaa -1;
     *
     * @return
     */
    public int getOhestaLyontiX() {
        return ohestaLyontiX;
    }

    public void setOhestaLyontiX(int ohestaLyonti) {
        this.ohestaLyontiX = ohestaLyonti;
    }

    public Maa getMaa() {
        return maa;
    }

    public Kunkku getKunkku() {
        return kunkku;
    }

    public void setKunkku(Kunkku kunkku) {
        this.kunkku = kunkku;
    }

    /**
     * palauttaa listan pelaajan nappuloista
     *
     * @return
     */
    public ArrayList<Nappula> getNappulat() {
        return nappulat;
    }

    public Kello getKello() {
        return kello;
    }

    public String toString() {
        return this.getMaa().toString();
    }
}
