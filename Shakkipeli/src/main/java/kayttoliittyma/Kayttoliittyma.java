package kayttoliittyma;

import kayttoliittyma.kuuntelijat.LautaKuuntelija;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import kayttoliittyma.kuuntelijat.LataaPeliKuuntelija;
import kayttoliittyma.kuuntelijat.TallennaPeliKuuntelija;
import kayttoliittyma.kuuntelijat.UusiPeliKuuntelija;
import logiikka.peli.PeliHallinta;

/**
 * Kayttöliittymän ylin luokka, luo konstruktorissa peliHallinnan
 *
 * @author elias
 */
public class Kayttoliittyma implements Runnable {

    private JFrame ruutu;
    private PeliHallinta peliHallinta;
    private int ikkunanKoko;
    private boolean valikkoAuki;

    public Kayttoliittyma() {
        this.peliHallinta = new PeliHallinta();
        this.peliPiirto = new PeliPiirto(this);
        this.valikkoAuki = false;

    }

    public boolean valikkoAuki() {
        return valikkoAuki;
    }

    public void setValikkoAuki(boolean valikkoAuki) {
        this.valikkoAuki = valikkoAuki;
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
        ruutu.setPreferredSize(new Dimension(650, 750));

        ruutu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoPiirtoKomponentit(ruutu.getContentPane());

        ruutu.pack();
        ruutu.setVisible(true);
    }

    /**
     * luo peliPiirron ja pyytää luomaan valikkonapit
     *
     * @param container
     */
    private void luoPiirtoKomponentit(Container container) {
        container.add(luoValikkoNapit(), BorderLayout.NORTH);
        container.add(luoKello(), BorderLayout.SOUTH);
        PeliPiirto peliPiirto = this.peliPiirto;

        peliPiirto.addMouseListener(new LautaKuuntelija(this));

        container.add(peliPiirto, BorderLayout.CENTER);

    }

    /**
     * luo yläreunan päävalikon
     *
     * @return
     */

    private JPanel luoKello() {
        JPanel kello = new JPanel(new GridLayout(1, 2));

        KelloPiirto valkoisenAika = new KelloPiirto(this, this.peliHallinta.getPeli().getValkoinen().getKello().getAika(), "Valkoisen aika: ");
        KelloPiirto mustanAika = new KelloPiirto(this, this.peliHallinta.getPeli().getMusta().getKello().getAika(), "Mustan aika: ");
        this.peliHallinta.getPeli().getValkoinen().getKello().setKellonPiirto(valkoisenAika);
        this.peliHallinta.getPeli().getMusta().getKello().setKellonPiirto(mustanAika);

        valkoisenAika.setPreferredSize(new Dimension(50, 50));
        mustanAika.setPreferredSize(new Dimension(50, 50));

        kello.add(valkoisenAika);
        kello.add(mustanAika);
        return kello;
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
