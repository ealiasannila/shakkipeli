/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import logiikka.peli.Ruutu;

/**
 *
 * @author elias
 */
public class NappulaApumetodeja {

    public static boolean onSamallaPystyTaiVaakaRivilla(int x, int y, int omaX, int omaY) {
        return !(omaX != x && omaY != y);
    }

    public static boolean onSamallaVinoRivilla(int x, int y, int omaX, int omaY) {
        return Math.abs(omaX - x) == Math.abs(omaY - y);

    }

    public static ArrayList<Ruutu> uhkausLinjaVino(int x, int y, Nappula nappula) {
        if (!nappula.tarkistaReitti(x, y)) {
            return new ArrayList<Ruutu>();
        }

        if (!onSamallaVinoRivilla(x, y, nappula.getX(), nappula.getY())) {//jos ei voi ylipäätänsä uhata ruutua ei silloin linjalla ole ruutuja
            return new ArrayList<Ruutu>();
        }
        ArrayList<Ruutu> uhatutRuudut = new ArrayList<>();

        if (nappula.getX() < x && nappula.getY() < y) { //kohde yläoikealle
            int j = nappula.getY() + 1;
            for (int i = nappula.getX() + 1; i <= x; i++) {
                uhatutRuudut.add(new Ruutu(i, j));
                j++;
            }
        } else if (nappula.getX() > x && nappula.getY() > y) {//kohde alavasemmalle
            int j = nappula.getY() - 1;
            for (int i = nappula.getX() - 1; i >= x; i--) {
                uhatutRuudut.add(new Ruutu(i, j));
                j--;
            }
        } else if (nappula.getY() < y && nappula.getX() > x) {//kohde ylävasemmalle
            int j = nappula.getX() - 1;
            for (int i = nappula.getY() + 1; i <= y; i++) {
                uhatutRuudut.add(new Ruutu(j, i));
                j--;
            }
        } else if (nappula.getY() > y && nappula.getX() < x) {//kohde alaoikealle
            int j = nappula.getX() + 1;
            for (int i = nappula.getY() - 1; i >= y; i--) {
                uhatutRuudut.add(new Ruutu(j, i));
                j++;
            }
        }
        return uhatutRuudut;

    }

    public static ArrayList<Ruutu> uhkausLinjaPystyTaiVaaka(int x, int y, Nappula nappula) {
        if (!nappula.tarkistaReitti(x, y)) {
            return new ArrayList<Ruutu>();
        }

        if (!onSamallaPystyTaiVaakaRivilla(x, y, nappula.getX(), nappula.getY())) {//jos ei voi ylipäätänsä uhata ruutua ei silloin linjalla ole ruutuja
            return new ArrayList<Ruutu>();
        }
        ArrayList<Ruutu> uhatutRuudut = new ArrayList<>();
        if (x < nappula.getX()) {
            for (int i = x; i < nappula.getX(); i++) {
                uhatutRuudut.add(new Ruutu(i, y));
            }
        } else if (x > nappula.getX()) {
            for (int i = nappula.getX() + 1; i <= x; i++) {
                uhatutRuudut.add(new Ruutu(i, y));
            }
        } else if (y < nappula.getY()) {
            for (int i = y; i > nappula.getY(); i++) {
                uhatutRuudut.add(new Ruutu(x, i));
            }
        } else if (y > nappula.getY()) {
            for (int i = nappula.getY() + 1; i <= y; i++) {
                uhatutRuudut.add(new Ruutu(x, i));
            }
        }
        return uhatutRuudut;
    }

    public static boolean reitillaEiMuitaNappuloitaVino(int x, int y, Nappula nappula) {

        if (nappula.getX() < x && nappula.getY() < y) { //kohde yläoikealle
            int j = nappula.getY() + 1;
            for (int i = nappula.getX() + 1; i < x; i++) {
                if (!nappula.tarkistaOnkoKohdeVapaa(i, j)) {
                    return false;
                }
                j++;
            }
        } else if (nappula.getX() > x && nappula.getY() > y) {//kohde alavasemmalle
            int j = nappula.getY() - 1;
            for (int i = nappula.getX() - 1; i > x; i--) {
                if (!nappula.tarkistaOnkoKohdeVapaa(i, j)) {
                    return false;
                }
                j--;
            }
        } else if (nappula.getY() < y && nappula.getX() > x) {//kohde ylävasemmalle
            int j = nappula.getX() - 1;
            for (int i = nappula.getY() + 1; i < y; i++) {
                if (!nappula.tarkistaOnkoKohdeVapaa(j, i)) {
                    return false;
                }
                j--;
            }
        } else if (nappula.getY() > y && nappula.getX() < x) {//kohde alaoikealle
            int j = nappula.getX() + 1;
            for (int i = nappula.getY() - 1; i > y; i--) {
                if (!nappula.tarkistaOnkoKohdeVapaa(j, i)) {
                    return false;
                }
                j++;
            }
        }
        return true;

    }

    public static boolean reitillaEiMuitaNappuloitaPystyTaiVaaka(int x, int y, Nappula nappula) {
        if (nappula.getX() < x) { //Tarkastetaan onko reitillä muita nappuloita
            for (int i = nappula.getX() + 1; i < x; i++) {
                if (!nappula.tarkistaOnkoKohdeVapaa(i, y)) {
                    return false;
                }
            }
        } else if (nappula.getX() > x) {
            for (int i = nappula.getX() - 1; i > x; i--) {
                if (!nappula.tarkistaOnkoKohdeVapaa(i, y)) {
                    return false;
                }
            }
        } else if (nappula.getY() < y) {
            for (int i = nappula.getY() + 1; i < y; i++) {
                if (!nappula.tarkistaOnkoKohdeVapaa(x, i)) {
                    return false;
                }
            }
        } else if (nappula.getY() > y) {
            for (int i = nappula.getY() - 1; i > y; i--) {
                if (!nappula.tarkistaOnkoKohdeVapaa(x, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static ArrayList<Ruutu> mahdollisetPystyTaiVaakaRuudut(int x, int y) {
        ArrayList<Ruutu> ruudut = new ArrayList<Ruutu>();
        for (int i = 0; i < 8; i++) {
            ruudut.add(new Ruutu(x, i));
            ruudut.add(new Ruutu(i, y));

        }
        return ruudut;
    }

    public static ArrayList<Ruutu> mahdollisetVinoRuudut(int x, int y) {
        ArrayList<Ruutu> ruudut = new ArrayList<Ruutu>();
        int j = y;
        for (int i = x; i < 8 && j < 8; i++) {
            j++;
            ruudut.add(new Ruutu(i, j));
        }

        j = y;
        for (int i = x; i >= 0 && j >= 0; i--) {
            j--;
            ruudut.add(new Ruutu(i, j));
        }

        j = y;
        for (int i = x; i < 8 && j >= 0; i++) {
            j--;
            ruudut.add(new Ruutu(i, j));
        }

        j = y;
        for (int i = x; i >= 0 && j < 8; i--) {
            j++;
            ruudut.add(new Ruutu(i, j));
        }
        return ruudut;
    }

}
