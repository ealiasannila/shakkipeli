/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.security.Timestamp;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionListener;
import kayttoliittyma.kuuntelijat.LataaPeliKuuntelija;
import kayttoliittyma.kuuntelijat.LautaKuuntelija;
import kayttoliittyma.kuuntelijat.TallennaPeliKuuntelija;
import kayttoliittyma.kuuntelijat.TallennaNappiKuuntelija;
import kayttoliittyma.kuuntelijat.TiedostoListaKuuntelija;
import kayttoliittyma.kuuntelijat.UusiPeliKuuntelija;

/**
 *
 * @author elias
 */
public class TiedostoValitsinTallennus extends TiedostoValitsin implements Runnable {

    public TiedostoValitsinTallennus(Kayttoliittyma kayttolittyma) {
        super(kayttolittyma);
    }

    @Override
    public void run() {
        super.run();
        ruutu.setTitle("Tallenna peli");

        ruutu.pack();
        ruutu.setVisible(true);
    }

    @Override
    protected void luoKomponentit(Container container) {
        super.luoKomponentit(container);
        nappi.addActionListener(new TallennaNappiKuuntelija(this.kayttoliittyma, tiedostonNimi, this));
        nappi.setText("Tallenna");

        container.add(tiedostonNimi, BorderLayout.NORTH);
        container.add(nappi, BorderLayout.SOUTH);

    }

}
