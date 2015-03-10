/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.nappulapiirto;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import logiikka.nappulat.Maa;
import static logiikka.nappulat.Maa.MUSTA;

/**
 *
 * @author elias
 */
public class KunkkuPiirto extends NappulaPiirto {

    @Override
    public Image haeKuva(Maa maa) {
        BufferedImage kunkkuKuva = null;
        File kuva = null;
        if (maa == MUSTA) {
            kuva = new File("src/main/java/kayttoliittyma/nappuloidenPiirto/nappuloidenKuvat/musta/kunkku.png");

        } else {
            kuva = new File("src/main/java/kayttoliittyma/nappuloidenPiirto/nappuloidenKuvat/valkoinen/kunkku.png");

        }

        try {
            kunkkuKuva = ImageIO.read(kuva);
        } catch (IOException e) {
            System.out.println("EI LÖYDY KUNKKUA");
        }

        return kunkkuKuva;

    }
}
