/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import logiikka.nappulat.Nappula;

/**
 *
 * @author elias
 */
public class PeliPiirto extends JPanel {

    private Kayttoliittyma kayttoliittyma;
    private boolean sotilaanKorotusKesken;

    public PeliPiirto(Kayttoliittyma kayttoliittyma) {
        super.setBackground(Color.lightGray);

        this.kayttoliittyma = kayttoliittyma;
        this.sotilaanKorotusKesken = false;
    }

    public int getSivunPituus() {
        return Math.min((this.getWidth()) / 8, (this.getHeight()) / 8);

    }

    private void piirraRuudut(Graphics graphics) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((j + i) % 2 == 0) {
                    graphics.setColor(Color.WHITE);
                } else {
                    graphics.setColor(Color.BLACK);
                }
                graphics.fillRect(j * this.getSivunPituus(), i * this.getSivunPituus(), this.getSivunPituus(), this.getSivunPituus());
            }
        }
    }

    public void peliLoppu(Graphics graphics) {
        graphics.setColor(Color.GRAY);

        if (this.kayttoliittyma.getPeliHallinta().getPeli().onPatissa()) {
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, this.getSivunPituus()));
            graphics.drawString("PATTITILANNE ", this.getSivunPituus(), this.getSivunPituus() * 3);

        }

        if (this.kayttoliittyma.getPeliHallinta().getPeli().onMatissa()) {
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, this.getSivunPituus()));
            graphics.drawString("HÄVISIT ", this.getSivunPituus(), this.getSivunPituus() * 3);
            graphics.drawString("" + this.kayttoliittyma.getPeliHallinta().getPeli().getVuorossa().getMaa(), this.getSivunPituus(), (int) (this.getSivunPituus() * 4.5));

        }
    }

    private void piirraNappulat(Graphics graphics) {
        for (Nappula nappula : this.kayttoliittyma.getPeliHallinta().getPeli().getMusta().getNappulat()) {
            this.piirraNappula(graphics, nappula);
        }

        for (Nappula nappula : this.kayttoliittyma.getPeliHallinta().getPeli().getValkoinen().getNappulat()) {
            this.piirraNappula(graphics, nappula);
        }
    }

    private void piirraNappula(Graphics graphics, Nappula nappula) {
        int nappulanKoko = (int) (this.getSivunPituus() * 0.7);

        graphics.drawImage(nappula.getPiirto().haeKuva(nappula.getMaa()),
                nappula.getPiirto().piirtoX(nappula.getX(), this, nappulanKoko),
                nappula.getPiirto().piirtoY(nappula.getY(), this, nappulanKoko),
                nappulanKoko, nappulanKoko, null);

    }

    private void kysyMiksiKorotetaan() {

        if (this.kayttoliittyma.getPeliHallinta().getPeli().getValkoinen().getKorotettava() != null) {
            this.sotilaanKorotusKesken = true;
            SwingUtilities.invokeLater(new KorotusValikko(this.kayttoliittyma, this.kayttoliittyma.getPeliHallinta().getPeli().getValkoinen()));

        }
        if (this.kayttoliittyma.getPeliHallinta().getPeli().getMusta().getKorotettava() != null) {
            this.sotilaanKorotusKesken = true;
            SwingUtilities.invokeLater(new KorotusValikko(this.kayttoliittyma, this.kayttoliittyma.getPeliHallinta().getPeli().getMusta()));

        }

    }

    public boolean sotilaanKorotusOnKesken() {
        return sotilaanKorotusKesken;
    }

    public void setSotilaanKorotusKesken(boolean sotilaanKorotusKesken) {
        this.sotilaanKorotusKesken = sotilaanKorotusKesken;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.kysyMiksiKorotetaan();

        this.piirraRuudut(graphics);
        this.piirraNappulat(graphics);
        this.peliLoppu(graphics);

    }
}
