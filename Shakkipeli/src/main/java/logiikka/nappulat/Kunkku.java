/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import logiikka.peli.Maa;
import java.util.ArrayList;
import kayttoliittyma.nappulapiirto.KunkkuPiirto;
import kayttoliittyma.nappulapiirto.TorniPiirto;
import static logiikka.peli.Maa.VALKOINEN;
import logiikka.peli.Peli;
import logiikka.peli.Pelilauta;
import logiikka.peli.Ruutu;

/**
 * Kunkku liikkuu yhden joka suuntaan. Ei voi liikkua uhattuun ruutuun, mutta
 * tämän tarkistus on toteutettu peli luokassa.
 *
 */
public class Kunkku extends Nappula {

    public Kunkku(Maa maa, int x, int y, Pelilauta pelilauta) {
        super(maa, x, y, pelilauta);
        this.piirto = new KunkkuPiirto();

    }

    @Override
    public boolean tarkistaReitti(int x, int y) {

        return this.sallittuLiikkumisTapa(x, y);
    }

    /**
     * kunkku uhkaa vain yhden päähän joten uhattuja ruutuja joista voisi
     * blokata ei ole.
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public ArrayList<Ruutu> uhkausLinja(int x, int y) {
        return new ArrayList<Ruutu>();
    }

    @Override
    protected boolean sallittuLiikkumisTapa(int x, int y) {
        if (Math.abs(this.getX() - x) > 1 || Math.abs(this.getY() - y) > 1) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean reitillaEiMuitaNappuloita(int x, int y) {
        return true;
    }

    @Override
    public ArrayList<Ruutu> mahdollisetRuudut() {
        ArrayList<Ruutu> ruudut = new ArrayList<Ruutu>();
        ruudut.add(new Ruutu(this.getX() + 1, this.getY() + 1));
        ruudut.add(new Ruutu(this.getX() - 1, this.getY() + 1));
        ruudut.add(new Ruutu(this.getX() + 1, this.getY() - 1));
        ruudut.add(new Ruutu(this.getX() - 1, this.getY() - 1));

        ruudut.add(new Ruutu(this.getX(), this.getY() - 1));
        ruudut.add(new Ruutu(this.getX(), this.getY() + 1));
        ruudut.add(new Ruutu(this.getX() + 1, this.getY()));
        ruudut.add(new Ruutu(this.getX() - 1, this.getY()));

        return ruudut;
    }

    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "k";
        } else {
            return "K";
        }
    }
}
