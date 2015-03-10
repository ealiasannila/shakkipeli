/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import kayttoliittyma.PeliPiirto;
import kayttoliittyma.PeliPiirto;
import logiikka.peli.Peli;

/**
 *
 * @author elias
 */
public class LautaKuuntelija extends Kuuntelija implements MouseListener {

    public LautaKuuntelija(Peli peli, PeliPiirto peliPiirto) {
        super(peli, peliPiirto);
    }



    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX() / this.peliPiirto.getSivunPituus();
        int y = 7-(me.getY() / this.peliPiirto.getSivunPituus());

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Laudan ulkopuolelta");
            return;
        }
        if (this.peli.getLauta().haeNappula(x, y) != null) {
            if (this.peli.getLauta().haeNappula(x, y).getMaa() == this.peli.getVuorossa().getMaa()) {
                this.peli.asetaAktiivinen(x, y);
                return;
            }
        }
        this.peli.siirto(x, y);
        this.peliPiirto.repaint();
        

    }

    public Point haeKoordinaatit(MouseEvent me) {

        System.out.println(me.getPoint().toString());
        return me.getPoint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //System.out.println(me.getX());
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // System.out.println(me.getX());
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //System.out.println(me.getX());
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //System.out.println(me.getX());
    }

}
