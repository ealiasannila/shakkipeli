/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import kayttoliittyma.kuuntelijat.KelloKuuntelija;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import kayttoliittyma.kuuntelijat.AloitaPeliKuuntelija;
import kayttoliittyma.kuuntelijat.KayttoliittymanTuntevaLuokka;
import kayttoliittyma.kuuntelijat.KelloKuuntelija;

/**
 *
 * @author elias
 */
public class UusiPeliValikko extends KayttoliittymanTuntevaLuokka implements Runnable {

    private JFrame ruutu;
    private JButton aloitaNappi;
    private JCheckBox kelloCheckBox;
    private JFormattedTextField aikaKentta;
    private boolean onkoKello;

    public UusiPeliValikko(Kayttoliittyma kayttoliittyma) {
        super(kayttoliittyma);
    }

    protected void luoKomponentit(Container container) {
        JPanel kelloPaneeli = new JPanel(new FlowLayout(3));
        kelloPaneeli.setMaximumSize(new Dimension(10, 10));

        this.aloitaNappi = new JButton("Aloita");
        this.aloitaNappi.addActionListener(new AloitaPeliKuuntelija(this.kayttoliittyma, this));
        this.kelloCheckBox = new JCheckBox("kello?");
        this.onkoKello = false;
        this.kelloCheckBox.addItemListener(new KelloKuuntelija(this));

        MaskFormatter muoto = null;
        try {
            muoto = new MaskFormatter("***");
        } catch (ParseException ex) {
            System.exit(1);
        }
        muoto.setValidCharacters("0123456789 ");
        this.aikaKentta = new JFormattedTextField(muoto);
        this.aikaKentta.setColumns(3);
        kelloPaneeli.add(this.kelloCheckBox);
        kelloPaneeli.add(this.aikaKentta);
        kelloPaneeli.add(new JLabel("minuuttia"));

        container.add(kelloPaneeli);
        container.add(aloitaNappi, BorderLayout.SOUTH);

    }

    public void setOnkoKello(Boolean onkoKello) {
        this.onkoKello = onkoKello;
    }

    public Boolean getOnkoKello() {
        return onkoKello;
    }

    public JFormattedTextField getAikaKentta() {
        return aikaKentta;
    }

    public JFrame getRuutu() {
        return ruutu;
    }

    @Override
    public void run() {
        this.ruutu = new JFrame("Uusi peli");
        ruutu.setPreferredSize(new Dimension(250, 170));

        ruutu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(ruutu.getContentPane());

        ruutu.pack();
        ruutu.setVisible(true);

    }

}
