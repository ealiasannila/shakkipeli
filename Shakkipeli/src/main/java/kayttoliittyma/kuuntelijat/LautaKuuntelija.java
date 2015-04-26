/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import kayttoliittyma.Kayttoliittyma;

/**
 * Kuuntelee pelilaudalla tapahtuneita klikkauksia
 *
 * @author elias
 */
public class LautaKuuntelija extends KayttoliittymanTuntevaLuokka implements MouseListener {

    public LautaKuuntelija(Kayttoliittyma kayttoliittyma) {
        super(kayttoliittyma);
    }

    /**
     * pyytää asettamaan nappulan aktiiviseksi, jos tämä ei onnistu pyydetään
     * tekemään siirto
     *
     * @param me
     */
    private int muutaX(int x) {
        return x / this.kayttoliittyma.getPeliPiirto().getSivunPituus();
    }

    private int muutaY(int y) {
        return 7 - (y / this.kayttoliittyma.getPeliPiirto().getSivunPituus());
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (this.kayttoliittyma.getPeliHallinta().getPeli().getPelitarkistus().onMatissa()
                || this.kayttoliittyma.getPeliHallinta().getPeli().getPelitarkistus().onPatissa()
                || this.kayttoliittyma.getPeliHallinta().getPeli().getPelitarkistus().aikaLoppu()) {
            return;
        }

        if (this.kayttoliittyma.valikkoAuki()) {
            return;
        }

        int x = muutaX(me.getX());
        int y = muutaY(me.getY());

        if (this.kayttoliittyma.getPeliHallinta().getPeli().getLauta().ruutuLaudanUlkopuolella(x, y)) {
            return;
        }

        if (this.kayttoliittyma.getPeliHallinta().getPeli().asetaAktiivinen(x, y)) {
            this.kayttoliittyma.getPeliPiirto().repaint();
            return;

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
