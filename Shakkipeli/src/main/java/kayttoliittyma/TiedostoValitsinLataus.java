/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import kayttoliittyma.kuuntelijat.LataaNappiKuuntelija;
import kayttoliittyma.kuuntelijat.TiedostoListaValitsinLatus;
import kayttoliittyma.kuuntelijat.TiedostoListaValitsinTallennus;

/**
 *
 * @author elias
 */
public class TiedostoValitsinLataus implements Runnable {
  
    private Kayttoliittyma kayttoliittyma;
    private JFrame ruutu;
    JList tiedostoLista;
    JTextArea tiedostonNimi;

    @Override
    public void run() {
        ruutu = new JFrame("Lataa Peli");
        ruutu.setPreferredSize(new Dimension(700, 400));

        ruutu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(ruutu.getContentPane());

        ruutu.pack();
        ruutu.setVisible(true);
    }

    private void luoTiedostoLita(Container container) {
        File kansio = new File("tallennetutPelit/omat");
        File[] tiedostot = kansio.listFiles();

        DefaultListModel<String> lista = new DefaultListModel<>();

        for (int i = 0; i < tiedostot.length; i++) {
            lista.addElement(tiedostot[i].getName());
        }

        tiedostoLista = new JList(lista);
        tiedostoLista.addListSelectionListener(new TiedostoListaValitsinLatus(this));

        container.add(tiedostoLista);
    }

    private void luoKomponentit(Container container) {

        GridLayout layout = new GridLayout(1, 3);
        container.setLayout(layout);

        tiedostonNimi = new JTextArea("Pelin nimi");
        tiedostonNimi.setEditable(false);
        this.luoTiedostoLita(container);

        JButton lataaNappi = new JButton("Lataa");
        lataaNappi.addActionListener(new LataaNappiKuuntelija(this.kayttoliittyma, tiedostonNimi, this));

        container.add(tiedostonNimi);
        container.add(lataaNappi);

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

    public TiedostoValitsinLataus(Kayttoliittyma kayttolittyma) {
        this.kayttoliittyma = kayttolittyma;

    }
    
}
