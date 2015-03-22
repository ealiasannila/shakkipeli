package kayttoliittyma.nappulapiirto;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import kayttoliittyma.PeliPiirto;
import logiikka.nappulat.Maa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elias
 */
public abstract class NappulaPiirto {

    public int piirtoX(int xRuutu, PeliPiirto alusta, int nappulanKoko) {
        return (xRuutu * (alusta.getSivunPituus()) + alusta.getSivunPituus() / 2 - nappulanKoko / 2);
    }

    public int piirtoY(int yRuutu, PeliPiirto alusta, int nappulanKoko) {
        return ((7 - yRuutu) * (alusta.getSivunPituus()) + alusta.getSivunPituus() / 2 - nappulanKoko / 2);

    }

    public abstract Image haeKuva(Maa maa);

}
