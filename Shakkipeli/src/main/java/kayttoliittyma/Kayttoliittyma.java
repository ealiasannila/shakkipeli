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

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private LautaKuuntelija hiiriKuuntelija;
    private Peli peli;
    private PeliPiirto peliPiirto;

    public Kayttoliittyma() {
        this.peli = new Peli();
        this.peliPiirto = new PeliPiirto(60, this.peli);
        this.hiiriKuuntelija = new LautaKuuntelija(this.peli, this.peliPiirto);

    }

    @Override
    public void run() {

        frame = new JFrame("Shakkipeli");
        frame.setPreferredSize(new Dimension(this.peliPiirto.getSivunPituus() * 8 + 10, this.peliPiirto.getSivunPituus() * 8 + 55));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoPiirtoKomponentit(frame.getContentPane());

        frame.pack();

        frame.setVisible(true);
    }

    private void luoPiirtoKomponentit(Container container) {
        container.add(luoValikkoNapit(), BorderLayout.NORTH);
        PeliPiirto peliPiirto = this.peliPiirto;

        peliPiirto.addMouseListener(hiiriKuuntelija);

        container.add(peliPiirto, BorderLayout.CENTER);

    }

    private JPanel luoValikkoNapit() {
        JPanel panel = new JPanel(new GridLayout(1, 3));

        JButton uusiPeli = new JButton("Uusi peli!");
        uusiPeli.addActionListener(new UusiPeliKuuntelija(this.peli, this.peliPiirto));
        panel.add(uusiPeli);

        JButton lataaPeli = new JButton("Lataa peli!");
        lataaPeli.addActionListener(new LataaPeliKuuntelija(this.peli, this.peliPiirto));
        panel.add(lataaPeli);

        JButton tallennaPeli = new JButton("Tallenna peli!");
        tallennaPeli.addActionListener(new TallennaPeliKuuntelija(this.peli, this.peliPiirto));
        panel.add(tallennaPeli);

        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }
}
