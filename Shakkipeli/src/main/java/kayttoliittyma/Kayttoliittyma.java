package kayttoliittyma;

import kayttoliittyma.kuuntelijat.LautaKuuntelija;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import kayttoliittyma.kuuntelijat.LataaPeliKuuntelija;
import kayttoliittyma.kuuntelijat.TallennaPeliKuuntelija;
import kayttoliittyma.kuuntelijat.UusiPeliKuuntelija;
import kayttoliittyma.nappulapiirto.TorniPiirto;
import logiikka.peli.Peli;
import logiikka.peli.PeliHallinta;

public class Kayttoliittyma implements Runnable {

    private JFrame ruutu;
    private PeliHallinta peliHallinta;
    private int ikkunanKoko;

    public Kayttoliittyma() {
        this.peliHallinta = new PeliHallinta();
        this.peliPiirto = new PeliPiirto(this);

    }

    public JFrame getRuutu() {
        return ruutu;
    }

    public PeliHallinta getPeliHallinta() {
        return peliHallinta;
    }

    public PeliPiirto getPeliPiirto() {
        return peliPiirto;
    }
    private PeliPiirto peliPiirto;

    @Override
    public void run() {

        ruutu = new JFrame("Shakkipeli");
        ruutu.setPreferredSize(new Dimension(650,700));

        ruutu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoPiirtoKomponentit(ruutu.getContentPane());

        ruutu.pack();
        ruutu.setVisible(true);
    }

    private void luoPiirtoKomponentit(Container container) {
        container.add(luoValikkoNapit(), BorderLayout.NORTH);
        PeliPiirto peliPiirto = this.peliPiirto;

        peliPiirto.addMouseListener(new LautaKuuntelija(this));

        container.add(peliPiirto, BorderLayout.CENTER);

    }

    private JPanel luoValikkoNapit() {
        JPanel panel = new JPanel(new GridLayout(1, 3));

        JButton uusiPeli = new JButton("Uusi peli");
        uusiPeli.addActionListener(new UusiPeliKuuntelija(this));
        panel.add(uusiPeli);

        JButton lataaPeli = new JButton("Lataa peli");
        lataaPeli.addActionListener(new LataaPeliKuuntelija(this));
        panel.add(lataaPeli);

        JButton tallennaPeli = new JButton("Tallenna peli");
        tallennaPeli.addActionListener(new TallennaPeliKuuntelija(this));
        panel.add(tallennaPeli);

        return panel;
    }

    public JFrame getFrame() {
        return ruutu;
    }
}
