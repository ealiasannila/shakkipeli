/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import kayttoliittyma.kuuntelijat.KorotusKuuntelija;
import kayttoliittyma.kuuntelijat.LataaNappiKuuntelija;
import logiikka.nappulat.Nappula;
import logiikka.peli.Pelaaja;

/**
 *
 * @author elias
 */
public class KorotusValikko implements Runnable{
    
    private Kayttoliittyma kayttoliittyma;
    private JFrame ruutu;
    private Pelaaja korotettavaPelaaja;

    public KorotusValikko(Kayttoliittyma kayttoliittyma, Pelaaja korotettava) {
        this.kayttoliittyma = kayttoliittyma;
        this.korotettavaPelaaja = korotettava;
    }
    
    
    
 @Override
    public void run() {
        ruutu = new JFrame("Miksi korotat?");
        ruutu.setPreferredSize(new Dimension(700, 400));

        ruutu.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        luoKomponentit(ruutu.getContentPane());

        ruutu.pack();
        ruutu.setVisible(true);
    }
    
     private void luoKomponentit(Container container) {

        GridLayout layout = new GridLayout(1, 4);
        container.setLayout(layout);

        JButton kuningatarNappi = new JButton("Kuningatar");
        kuningatarNappi.addActionListener(new KorotusKuuntelija(this.kayttoliittyma, this.korotettavaPelaaja, this, 'q'));
        container.add(kuningatarNappi);
        
        JButton torniNappi = new JButton("Torni");
        torniNappi.addActionListener(new KorotusKuuntelija(this.kayttoliittyma, this.korotettavaPelaaja, this,'t'));
        container.add(torniNappi);
        
        JButton lahettiNappi = new JButton("Lahetti");
        lahettiNappi.addActionListener(new KorotusKuuntelija(this.kayttoliittyma, this.korotettavaPelaaja, this,'l'));
        container.add(lahettiNappi);
        
        JButton ratsuNappi = new JButton("Ratsu");
        ratsuNappi.addActionListener(new KorotusKuuntelija(this.kayttoliittyma, this.korotettavaPelaaja, this,'r'));
        container.add(ratsuNappi);
        
        
        

    }

    public JFrame getRuutu() {
        return ruutu;
    }

    
}
