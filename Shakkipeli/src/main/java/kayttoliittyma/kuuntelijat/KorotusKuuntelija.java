/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.KorotusValikko;
import logiikka.nappulat.Nappula;
import logiikka.peli.Pelaaja;

/**
 *
 * @author elias
 */
public class KorotusKuuntelija extends Kuuntelija implements ActionListener {

    private Pelaaja korotettava;
    private KorotusValikko valikko;
    private char miksiKorotetaan;

    public KorotusKuuntelija(Kayttoliittyma kayttoliittyma, Pelaaja korotettava, KorotusValikko valikko, char miksiKorotetaan) {
        super(kayttoliittyma);
        this.korotettava = korotettava;
        this.valikko = valikko;
        this.miksiKorotetaan = miksiKorotetaan;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.kayttoliittyma.getPeliHallinta().getPeli().korotaSotilas(this.korotettava, miksiKorotetaan);

        this.kayttoliittyma.getPeliPiirto().setSotilaanKorotusKesken(false);
        this.kayttoliittyma.getPeliPiirto().repaint();

        this.valikko.getRuutu().dispose();
    }

}
