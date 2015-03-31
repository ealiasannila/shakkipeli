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
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.PeliPiirto;
import kayttoliittyma.PeliPiirto;
import logiikka.peli.Peli;
import logiikka.peli.PeliHallinta;

/**
 * Kuuntelee pelilaudalla tapahtuneita klikkauksia
 *
 * @author elias
 */
public class LautaKuuntelija extends Kuuntelija implements MouseListener {

    public LautaKuuntelija(Kayttoliittyma kayttoliittyma) {
        super(kayttoliittyma);
    }

    /**
     * pyytää asettamaan nappulan aktiiviseksi, jos tämä ei onnistu pyydetään
     * tekemään siirto
     *
     * @param me
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        if (this.kayttoliittyma.getPeliPiirto().sotilaanKorotusOnKesken()) {
            return;
        }
        int x = me.getX() / this.kayttoliittyma.getPeliPiirto().getSivunPituus();
        int y = 7 - (me.getY() / this.kayttoliittyma.getPeliPiirto().getSivunPituus());

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Laudan ulkopuolelta");
            return;
        }
        if (this.kayttoliittyma.getPeliHallinta().getPeli().getLauta().haeNappula(x, y) != null) {
            if (this.kayttoliittyma.getPeliHallinta().getPeli().asetaAktiivinen(x, y)) {
                this.kayttoliittyma.getPeliPiirto().repaint();
                return;
            }
        }
        if (!this.kayttoliittyma.getPeliHallinta().getPeli().siirto(x, y)) {
            this.kayttoliittyma.getPeliPiirto().asetaVaaraSiirto(x, y);
        }
        this.kayttoliittyma.getPeliPiirto().repaint();

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
