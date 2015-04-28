/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import kayttoliittyma.kuuntelijat.KayttoliittymanTuntevaLuokka;
import kayttoliittyma.kuuntelijat.TiedostoListaKuuntelija;

/**
 * Tallennus ja latausikkunoissa käytettävä tiedostovalitsin.
 *
 * @author elias
 */
public abstract class TiedostoValitsin extends Valikko {

    protected JButton nappi;
    private JList tiedostoLista;
    protected JTextField tiedostonNimi;

    public TiedostoValitsin(Kayttoliittyma kayttoliittyma) {
        super(kayttoliittyma);
    }

    @Override
    public void run() {
        ruutu = new JFrame("TiedostoValitisin");
        ruutu.setPreferredSize(new Dimension(900, 250));
        super.run();
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

}
