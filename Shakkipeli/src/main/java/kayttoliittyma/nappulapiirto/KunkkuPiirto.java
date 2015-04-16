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
 * Palauttaa kuninkaan kuvan
 *
 * @author elias
 */
public class KunkkuPiirto extends NappulaPiirto {

    @Override
    public Image haeKuva(Maa maa) {
        if (maa == MUSTA) {
            return this.haeKuva("src/main/java/kayttoliittyma/nappulapiirto/nappulakuvat/musta/kunkku.png");

        } else {
            return this.haeKuva("src/main/java/kayttoliittyma/nappulapiirto/nappulakuvat/valkoinen/kunkku.png");
        }

    }
}
