/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.util.ArrayList;
import logiikka.nappulat.Kunkku;
import logiikka.nappulat.Lahetti;
import logiikka.nappulat.Nappula;
import logiikka.nappulat.Ratsu;

/**
 * PeliTarkistus tarjoaa metodeja erilaisiin pelitilanteeseen liittyviin tarkistuksiin.
 *
 * 
 */
public class PeliTarkistus {

    private Peli peli;

    public PeliTarkistus(Peli peli) {
        this.peli = peli;
    }

    public boolean aikaLoppu() {
        return this.peli.getVuorossa().getKello().aikaLoppu();
    }

    /**
     * kertoo onko kuningas uhattuna, Pelin lisäksi käytetään väärästä siirrosta
     * kertovan punaisen väläyksen tekemisessä
     *
     * @return
     */
    public boolean onShakissa() {
        return onkoUhattuna(this.peli.getVuorossa().getKunkku().getX(), this.peli.getVuorossa().getKunkku().getY());
    }

    /**
     * kertoo onko peli tullut päätökseen On matti jos: on shakki kunkku ei voi
     * liikkua useampi uhkaa tai ei voi syödä uhkaajaa ja ei voi blokata shakkia
     *
     * @return
     */
    public boolean onMatissa() {
        if (!this.onShakissa()) {
            return false;
        }
        if (!this.kunkkuEiVoiLiikkua()) {
            return false;
        }
        if (this.uhkaakoUseampi(this.peli.getVuorossa().getKunkku().getX(), this.peli.getVuorossa().getKunkku().getY())) { //Onko tästä metodista mitään iloa kellekkään??
            return true;
        }
        if (voiSyodaNappulan(this.uhkaavaNappula(this.peli.getVuorossa().getKunkku().getX(), this.peli.getVuorossa().getKunkku().getY()))) {
            return false;
        }
        if (this.voiBlokata()) {
            return false;
        }
        return true;
    }

    /**
     * kertoo onko peli päättynyt tasapeliin peli on tasapeli jos: kumpikaan ei
     * voi tehdä mattia jäljellä olevilla nappuloilla tai kumpikaan ei ole
     * shakissa ja kumpikaan ei voi liikuttaa mitään nappulaa
     *
     * @return
     */
    public boolean onPatissa() {

        if (this.eiVoiTehdaMattia()) {
            return true;
        }
        if (this.onShakissa()) {
            return false;
        }

        if (this.eiVoiLiikkuaMillaanNappulalla()) {
            return true;
        }

        return false;

    }

    public boolean onkoUhattuna(int x, int y) {
        if (this.uhkaavaNappula(x, y) == null) {
            return false;
        }
        return true;
    }

    /**
     * kertoo mikä nappula jos mikään uhkaa tiettyä ruutua
     *
     * @param x
     * @param y
     * @return
     */
    private Nappula uhkaavaNappula(int x, int y) {
        return this.haeUhkaava(x, y, this.peli.getVastustaja().getNappulat());

    }

    /**
     * käy kaikki vastustajan nappulat läpi ja tarkistaa onko niistä jollain
     * reitti annettuun ruutuun. Palauttaa ensimmäisen nappulan jonka löytää tai
     * null
     *
     * @param x
     * @param y
     * @param vastustajanNappulat
     * @return
     */
    private Nappula haeUhkaava(int x, int y, ArrayList<Nappula> vastustajanNappulat) {
        for (Nappula nappula : vastustajanNappulat) {
            if (nappula.tarkistaReitti(x, y)) {
                return nappula;
            }
        }
        return null;

    }

    /**
     * kertoo uhkaako useampi nappula, käytetään matin tarkistuksessa
     *
     * @param x
     * @param y
     * @return
     */
    private boolean uhkaakoUseampi(int x, int y) {
        return this.uhkaakoUseampi(x, y, this.peli.getVastustaja().getNappulat());

    }

    private boolean uhkaakoUseampi(int x, int y, ArrayList<Nappula> vastustajanNappulat) {
        int uhkaajat = 0;
        for (Nappula nappula : vastustajanNappulat) {
            if (nappula.tarkistaReitti(x, y)) {
                uhkaajat++;
            }
        }
        if (uhkaajat > 1) {
            return true;
        }
        return false;
    }

    private boolean kunkkuEiVoiLiikkua() {
        for (int y = Math.max(0, this.peli.getVuorossa().getKunkku().getY() - 1); y <= Math.min(this.peli.getLauta().getSize() - 1, this.peli.getVuorossa().getKunkku().getY() + 1); y++) {
            for (int x = Math.max(0, this.peli.getVuorossa().getKunkku().getX() - 1); x <= Math.min(this.peli.getLauta().getSize() - 1, this.peli.getVuorossa().getKunkku().getX() + 1); x++) {
                if (this.peli.kokeileSiirtoa(x, y, this.peli.getVuorossa().getKunkku())) {
                    return false;
                }

            }
        }
        return true;
    }

    private boolean eiVoiLiikkuaMillaanNappulalla() {
        for (Nappula nappula : this.peli.getVuorossa().getNappulat()) {
            for (Ruutu ruutu : nappula.mahdollisetRuudut()) {
                if (this.peli.kokeileSiirtoa(ruutu.getX(), ruutu.getY(), nappula)) {
                    return false;
                }
            }
        }
        return true;

    }

    private boolean eiVoiTehdaMattia() {
        return this.eiVoiTehdaMattia(this.peli.getMusta()) && this.eiVoiTehdaMattia(this.peli.getValkoinen());
    }

    private boolean eiVoiTehdaMattia(Pelaaja pelaaja) {
        if (this.vainKunkku(pelaaja)) {
            return true;
        }
        if (this.vainKunkkuJaRatsu(pelaaja)) {
            return true;
        }
        if (this.vainKunkkuJaYhdellaVarillaLahetteja(pelaaja)) {
            return true;
        }
        return false;

    }

    private boolean vainKunkku(Pelaaja pelaaja) {
        return pelaaja.getNappulat().size() == 1;
    }

    private boolean vainKunkkuJaRatsu(Pelaaja pelaaja) {
        if (pelaaja.getNappulat().size() != 2) {
            return false;
        }
        for (Nappula nappula : pelaaja.getNappulat()) {
            if (nappula.getClass() != Kunkku.class && nappula.getClass() != Ratsu.class) {
                return false;
            }
        }
        return true;
    }

    private boolean vainKunkkuJaYhdellaVarillaLahetteja(Pelaaja pelaaja) {
        boolean onMustaLahetti = false;
        boolean onVaaleaLahetti = false;

        for (int i = 0; i < pelaaja.getNappulat().size(); i++) {
            Nappula nappula = pelaaja.getNappulat().get(i);
            if (nappula.equals(pelaaja.getKunkku())) {
                continue;
            } else if (nappula.getClass() == Lahetti.class) {
                if ((nappula.getX() + nappula.getY()) % 2 == 0) {
                    onVaaleaLahetti = true;
                } else {
                    onMustaLahetti = true;
                }
            } else {
                return false;
            }

        }

        return !(onVaaleaLahetti && onMustaLahetti);

    }

    private boolean voiSyodaNappulan(Nappula nappula) {
        for (Nappula oma : this.peli.getVuorossa().getNappulat()) {
            if (oma.onSallittuSiirto(nappula.getX(), nappula.getY())) {
                if (oma.equals(this.peli.getVuorossa().getKunkku())) {
                    if (this.onkoUhattuna(nappula.getX(), nappula.getY())) {
                        continue;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean voiBlokata() {
        Nappula uhkaava = this.uhkaavaNappula(this.peli.getVuorossa().getKunkku().getX(), this.peli.getVuorossa().getKunkku().getY());
        ArrayList<Ruutu> uhatutRuudut = uhkaava.uhkausLinja(this.peli.getVuorossa().getKunkku().getX(), this.peli.getVuorossa().getKunkku().getY());
        for (Nappula oma : this.peli.getVuorossa().getNappulat()) {
            if (oma.equals(this.peli.getVuorossa().getKunkku())) {
                continue;
            }
            for (Ruutu ruutu : uhatutRuudut) {
                if (oma.onSallittuSiirto(ruutu.getX(), ruutu.getY())) {
                    return true;
                }
            }
        }
        return false;
    }

}
