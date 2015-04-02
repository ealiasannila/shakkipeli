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
 * Kuuntelee korotusvalikon nappeja
 *
 * @author elias
 */
public class KorotusKuuntelija extends Kuuntelija implements ActionListener {

    private Pelaaja korotettava;
    private KorotusValikko valikko;
    private char miksiKorotetaan;

    public KorotusKuuntelija( KorotusValikko valikko, char miksiKorotetaan) {
        super(valikko.getKayttoliittyma());

        this.valikko = valikko;
        this.korotettava = valikko.getKorotettavaPelaaja();
        this.valikko = valikko;
        this.miksiKorotetaan = miksiKorotetaan;
    }

    /**
     * pyytää peliä korottamaan korotusta odottavan sotilaan valituksi
     * upseeriksi, sulkee korotusikkunan
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.kayttoliittyma.getPeliHallinta().getPeli().korotaSotilas(this.korotettava, miksiKorotetaan);

        this.kayttoliittyma.getPeliPiirto().setSotilaanKorotusKesken(false);
        this.kayttoliittyma.getPeliPiirto().repaint();

        this.valikko.getRuutu().dispose();
    }

}
