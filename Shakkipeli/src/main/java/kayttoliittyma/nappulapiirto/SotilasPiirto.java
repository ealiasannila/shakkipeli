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
import logiikka.peli.Maa;
import static logiikka.peli.Maa.MUSTA;

/**
 *
 * @author elias
 */
public class SotilasPiirto extends NappulaPiirto {

    @Override
    public Image haeKuva(Maa maa) {
        BufferedImage sotilaskuva = null;
        File kuva = null;
        if (maa == MUSTA) {
            kuva = new File("src/main/java/kayttoliittyma/nappulapiirto/nappulakuvat/musta/sotilas.png");

        } else {
            kuva = new File("src/main/java/kayttoliittyma/nappulapiirto/nappulakuvat/valkoinen/sotilas.png");

        }

        try {
            sotilaskuva = ImageIO.read(kuva);
        } catch (IOException e) {
            System.out.println("EI LÖYDY KUNINGATARTA");
        }

        return sotilaskuva;
    }

}
