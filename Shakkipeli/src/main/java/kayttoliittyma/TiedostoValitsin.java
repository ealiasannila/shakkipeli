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
public class TiedostoValitsin implements Runnable {

    protected Kayttoliittyma kayttoliittyma;
    protected JFrame ruutu;
    protected JButton nappi;
    JList tiedostoLista;
    JTextField tiedostonNimi;

    @Override
    public void run() {
        ruutu = new JFrame("TiedostoValitisin");
        ruutu.setPreferredSize(new Dimension(900, 250));
        
        ruutu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(ruutu.getContentPane());

    }

    protected void luoTiedostoLita(Container container) {
        File kansio = new File("tallennetutPelit/omat");
        File[] tiedostot = kansio.listFiles();

        DefaultListModel<String> lista = new DefaultListModel<String>();

        for (int i = 0; i < tiedostot.length; i++) {
            lista.addElement(tiedostot[i].getName());
        }

        tiedostoLista = new JList(lista);
        tiedostoLista.addListSelectionListener(new TiedostoListaKuuntelija(this));

        container.add(tiedostoLista);
    }

    protected void luoKomponentit(Container container) {

        tiedostonNimi = new JTextField(new Date().toString());
        tiedostonNimi.setHorizontalAlignment(JTextField.CENTER);
        this.luoTiedostoLita(container);

        nappi = new JButton("nappi");

    }

    public JTextField getTiedostonNimi() {
        return tiedostonNimi;
    }

    public JList getTiedostoLista() {
        return tiedostoLista;
    }

    public JFrame getRuutu() {
        return ruutu;
    }

    public TiedostoValitsin(Kayttoliittyma kayttolittyma) {
        this.kayttoliittyma = kayttolittyma;

    }

}
