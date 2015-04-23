package kayttoliittyma.nappulapiirto;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import kayttoliittyma.PeliPiirto;
import logiikka.peli.Maa;
import static logiikka.peli.Maa.MUSTA;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Palauttaa nappulan koordinaatit pikseleinä, ja nappulan kuvatiedoston
 *
 * @author elias
 */
public abstract class NappulaPiirto {

    /**
     * palauttaa x koordinaatin pikseleinä
     *
     * @param xRuutu
     * @param alusta
     * @param nappulanKoko
     * @return
     */
    public int piirtoX(int xRuutu, PeliPiirto alusta, int nappulanKoko) {
        return (xRuutu * (alusta.getSivunPituus()) + alusta.getSivunPituus() / 2 - nappulanKoko / 2);
    }

    /**
     * palauttaa y koordinaatin pikseleinä
     *
     * @param yRuutu
     * @param alusta
     * @param nappulanKoko
     * @return
     */
    public int piirtoY(int yRuutu, PeliPiirto alusta, int nappulanKoko) {
        return ((7 - yRuutu) * (alusta.getSivunPituus()) + alusta.getSivunPituus() / 2 - nappulanKoko / 2);

    }

    public abstract Image haeKuva(Maa maa);

    protected Image haeKuva(Maa maa, String tiedostonNimi) {
        String polku = "nappulakuvat/";
        if (maa == MUSTA) {
            polku += "musta/" + tiedostonNimi;
        } else {
            polku += "valkoinen/" + tiedostonNimi;
        }
        File tiedosto = new File(polku);

        BufferedImage kuva = null;
        try {
            kuva = ImageIO.read(tiedosto);
        } catch (IOException e) {
            System.out.println("Nappulan kuvatiedosto puuttuu");
            System.exit(1);
        }

        return kuva;
    }

}
