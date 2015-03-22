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
import kayttoliittyma.kuuntelijat.TiedostoListaValitsinTallennus;
import kayttoliittyma.kuuntelijat.UusiPeliKuuntelija;

/**
 *
 * @author elias
 */
public class TiedostoValitsinTallennus implements Runnable {

    private Kayttoliittyma kayttolittyma;
    private JFrame ruutu;
    JList tiedostoLista;
    JTextArea tiedostonNimi;

    @Override
    public void run() {
        ruutu = new JFrame("Tallenna Peli");
        ruutu.setPreferredSize(new Dimension(700, 400));

        ruutu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(ruutu.getContentPane());

        ruutu.pack();
        ruutu.setVisible(true);
    }

    private void luoTiedostoLita(Container container) {
        File kansio = new File("tallennetutPelit/omat");
        File[] tiedostot = kansio.listFiles();

        DefaultListModel<String> lista = new DefaultListModel<String>();

        for (int i = 0; i < tiedostot.length; i++) {
            lista.addElement(tiedostot[i].getName());
        }

        tiedostoLista = new JList(lista);
        tiedostoLista.addListSelectionListener(new TiedostoListaValitsinTallennus(this));

        container.add(tiedostoLista);
    }

    private void luoKomponentit(Container container) {

        GridLayout layout = new GridLayout(1, 3);
        container.setLayout(layout);

        tiedostonNimi = new JTextArea(this.kayttolittyma.getPeliHallinta().getPeli().getMusta() + " vs " + this.kayttolittyma.getPeliHallinta().getPeli().getValkoinen() + " " + new Date());
        this.luoTiedostoLita(container);

        JButton tallennaNappi = new JButton("Tallenna");
        tallennaNappi.addActionListener(new TallennaNappiKuuntelija(this.kayttolittyma, tiedostonNimi, this));

        container.add(tiedostonNimi);
        container.add(tallennaNappi);

    }

    public JTextArea getTiedostonNimi() {
        return tiedostonNimi;
    }

    public JList getTiedostoLista() {
        return tiedostoLista;
    }

    public JFrame getRuutu() {
        return ruutu;
    }

    public TiedostoValitsinTallennus(Kayttoliittyma kayttolittyma) {
        this.kayttolittyma = kayttolittyma;

    }

}
