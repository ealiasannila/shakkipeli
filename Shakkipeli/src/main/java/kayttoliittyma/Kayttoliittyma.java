package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import kayttoliittyma.nappuloidenPiirto.TorniPiirto;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;

    public Kayttoliittyma() {
    }

    @Override
    public void run() {
        frame = new JFrame("Shakkipeli");
        frame.setPreferredSize(new Dimension(600, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.add(luoValikkoNapit(), BorderLayout.NORTH);
        
        container.add(new PeliPiirto());
    }

    private JPanel luoValikkoNapit() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(new JButton("Aloita peli!"));
        panel.add(new JButton("Joku muu nappula"));
        panel.add(new JButton("Lisää kivoja nappei"));
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }
}
