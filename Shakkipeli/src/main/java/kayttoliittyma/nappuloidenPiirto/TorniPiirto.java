/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.nappuloidenPiirto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author elias
 */
public class TorniPiirto extends NappulaPiirto {

    public TorniPiirto() {
    }

    @Override
    public Image haeKuva() {

        BufferedImage torniKuva = null;
        File kuva = new File("src/main/java/kayttoliittyma/nappuloidenPiirto/nappuloidenKuvat/torni.png");

        try {
            torniKuva = ImageIO.read(kuva);
        } catch (IOException e) {
            System.out.println("EI LÖYDY");
        }

        return torniKuva;

    }

}
