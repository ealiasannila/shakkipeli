/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import kayttoliittyma.kuuntelijat.KayttoliittymanTuntevaLuokka;
import kayttoliittyma.kuuntelijat.KorotusKuuntelija;
import static logiikka.peli.Maa.MUSTA;
import logiikka.peli.Pelaaja;

/**
 * Näyttää valikon, josta valitaan miksi nappulaksi korotetaan vastustajan
 * perusriville päässyt sotilas
 *
 * @author elias
 */
public class KorotusValikko extends KayttoliittymanTuntevaLuokka implements Runnable {

    private JFrame ruutu;
    private Pelaaja korotettavaPelaaja;

    public Pelaaja getKorotettavaPelaaja() {
        return korotettavaPelaaja;
    }

    public Kayttoliittyma getKayttoliittyma() {
        return kayttoliittyma;
    }

    public KorotusValikko(Kayttoliittyma kayttoliittyma, Pelaaja korotettava) {
        super(kayttoliittyma);
        this.korotettavaPelaaja = korotettava;
    }

    @Override
    public void run() {
        ruutu = new JFrame("Miksi korotat?");
        this.kayttoliittyma.getPeliHallinta().getPeli().asetaKellot(this.kayttoliittyma.getPeliHallinta().getPeli().getValkoinen().getKello().getAika(), this.kayttoliittyma.getPeliHallinta().getPeli().getMusta().getKello().getAika());
        ruutu.setBounds(this.kayttoliittyma.getFrame().getBounds());

        ruutu.setAlwaysOnTop(true);
        ruutu.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        luoKomponentit(ruutu.getContentPane());

        this.kayttoliittyma.getPeliHallinta().getPeli().getAjastin().pysayta();
        this.kayttoliittyma.setValikkoAuki(true);

        ruutu.pack();
        ruutu.setVisible(true);
    }

    /**
     * luo näytettävät valintanapit ja lisää niihin kuuntelijat
     *
     * @param container
     */
    private void luoKomponentit(Container container) {

        GridLayout layout = new GridLayout(1, 4);
        container.setLayout(layout);

        String polku;
        if (this.korotettavaPelaaja.getMaa() == MUSTA) {
            polku = "nappulakuvat/musta/";
        } else {
            polku = "nappulakuvat/valkoinen/";

        }

        JButton kuningatarNappi = new JButton(new ImageIcon(polku + "kuningatar.png"));
        kuningatarNappi.addActionListener(new KorotusKuuntelija(this, 'q'));
        container.add(kuningatarNappi);

        JButton torniNappi = new JButton(new ImageIcon(polku + "torni.png"));
        torniNappi.addActionListener(new KorotusKuuntelija(this, 't'));
        container.add(torniNappi);

        JButton lahettiNappi = new JButton(new ImageIcon(polku + "lahetti.png"));
        lahettiNappi.addActionListener(new KorotusKuuntelija(this, 'l'));
        container.add(lahettiNappi);

        JButton ratsuNappi = new JButton(new ImageIcon(polku + "ratsu.png"));
        ratsuNappi.addActionListener(new KorotusKuuntelija(this, 'r'));
        container.add(ratsuNappi);

    }

    public JFrame getRuutu() {
        return ruutu;
    }

}
