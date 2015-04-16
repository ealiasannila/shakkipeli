/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import logiikka.nappulat.Nappula;

/**
 * Piirtää pelilaudan ja nappulat
 *
 * @author elias
 */
public class PeliPiirto extends JPanel {

    private Kayttoliittyma kayttoliittyma;
    private boolean sotilaanKorotusKesken;
    private int vaaraSiirtoY;
    private int vaaraSiirtoX;

    public PeliPiirto(Kayttoliittyma kayttoliittyma) {
        super.setBackground(Color.lightGray);

        this.kayttoliittyma = kayttoliittyma;
        this.sotilaanKorotusKesken = false;
        this.vaaraSiirtoX = -1;
        this.vaaraSiirtoY = -1;
    }

    /**
     * palauttaa ruudun sivunpituuden pikseleissä
     *
     * @return
     */
    public int getSivunPituus() {
        return Math.min((this.getWidth()) / 8, (this.getHeight()) / 8);

    }

    private void piirraRuudut(Graphics graphics) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.kayttoliittyma.getPeliHallinta().getPeli().getAktiivinen() != null && this.kayttoliittyma.getPeliHallinta().getPeli().getAktiivinen().getX() == j && this.kayttoliittyma.getPeliHallinta().getPeli().getAktiivinen().getY() == 7 - i) {
                    graphics.setColor(Color.orange);
                } else {
                    if ((j + i) % 2 == 0) {
                        graphics.setColor(Color.WHITE);
                    } else {
                        graphics.setColor(Color.BLACK);
                    }
                }
                graphics.fillRect(j * this.getSivunPituus(), i * this.getSivunPituus(), this.getSivunPituus(), this.getSivunPituus());
            }
        }
    }

    private void peliLoppu(Graphics graphics) {
        graphics.setColor(Color.GRAY);

        if (this.kayttoliittyma.getPeliHallinta().getPeli().getPelitarkistus().onPatissa()) {
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, this.getSivunPituus()));
            graphics.drawString("PATTITILANNE ", this.getSivunPituus(), this.getSivunPituus() * 3);

        }

        if (this.kayttoliittyma.getPeliHallinta().getPeli().getPelitarkistus().onMatissa() || this.kayttoliittyma.getPeliHallinta().getPeli().getPelitarkistus().aikaLoppu()) {
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

    /**
     * kertoo onko sotilaan korotus kesken. Tarvitaan muiden siirtojen tekemisen
     * estämiseen sillä aikaa kun valitaan miksi korotetaan
     *
     * @return
     */
    public boolean sotilaanKorotusOnKesken() {
        return sotilaanKorotusKesken;
    }

    public void setSotilaanKorotusKesken(boolean sotilaanKorotusKesken) {
        this.sotilaanKorotusKesken = sotilaanKorotusKesken;
    }

    /**
     * Asettaa väärän siirron koordinaatit muistiin
     *
     * @param x
     * @param y
     */
    public void asetaVaaraSiirto(int x, int y) {
        this.vaaraSiirtoX = x;
        this.vaaraSiirtoY = y;
    }

    /**
     * väläyttää ei sallittua ruutua punaisella, ja kuningasta jos siirto ei ole
     * sallittu siksi että kuningas jää uhatuksi
     *
     * @param graphics
     */
    private void vaaraSiirtoFlash(Graphics graphics) {
        if (this.vaaraSiirtoX != -1) {
            graphics.setColor(Color.RED);
            graphics.fillRect((this.vaaraSiirtoX) * this.getSivunPituus(), (7 - this.vaaraSiirtoY) * this.getSivunPituus(), this.getSivunPituus(), this.getSivunPituus());
            if (this.kayttoliittyma.getPeliHallinta().getPeli().getPelitarkistus().onShakissa()) {
                graphics.fillRect((this.kayttoliittyma.getPeliHallinta().getPeli().getVuorossa().getKunkku().getX()) * this.getSivunPituus(), (7 - this.kayttoliittyma.getPeliHallinta().getPeli().getVuorossa().getKunkku().getY()) * this.getSivunPituus(), this.getSivunPituus(), this.getSivunPituus());

            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(PeliPiirto.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.vaaraSiirtoX = -1;
            this.vaaraSiirtoY = -1;
            this.repaint();
        }

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (!this.sotilaanKorotusOnKesken()) {
            this.kysyMiksiKorotetaan();
        }
        this.piirraRuudut(graphics);
        this.vaaraSiirtoFlash(graphics);

        this.piirraNappulat(graphics);

        this.peliLoppu(graphics);

    }

}
