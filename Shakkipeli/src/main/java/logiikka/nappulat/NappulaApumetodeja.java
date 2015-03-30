/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import java.util.ArrayList;
import logiikka.peli.Ruutu;

/**
 *Tässä luokassa on tornin ja kuningattaren sekä lähetin ja kuningattaren jakamia metodeja
 * 
 */
public class NappulaApumetodeja {

    
    /**
     * Kertoo onko kohde ja oma sijainti samalla pysty tai vaakarivillä
     * @param x
     * @param y
     * @param omaX
     * @param omaY
     * @return 
     */
    public static boolean onSamallaPystyTaiVaakaRivilla(int x, int y, int omaX, int omaY) {
        return !(omaX != x && omaY != y);
    }

    /**
     * kertoo onko kohde ja oma sijainti samalla vinorivillä
     * @param x
     * @param y
     * @param omaX
     * @param omaY
     * @return 
     */
    public static boolean onSamallaVinoRivilla(int x, int y, int omaX, int omaY) {
        return Math.abs(omaX - x) == Math.abs(omaY - y);

    }

    /**
     * palauttaa vinottaisella uhkauslinjalla olevat ruudut
     * @param x
     * @param y
     * @param nappula
     * @return 
     */
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

    /**
     * palauttaa pysty tai vaakasuuntaisella uhkauslinjalla olevat ruudut 
     * @param x
     * @param y
     * @param nappula
     * @return 
     */
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
            for (int i = y; i < nappula.getY(); i++) {
                uhatutRuudut.add(new Ruutu(x, i));
            }
        } else if (y > nappula.getY()) {
            for (int i = nappula.getY() + 1; i <= y; i++) {
                uhatutRuudut.add(new Ruutu(x, i));
            }
        }
        return uhatutRuudut;
    }

    /**
     * kertoo onko nappulan ja annetun vinosuuntaisen kohteen välillä muita nappuloita
     * @param x
     * @param y
     * @param nappula
     * @return 
     */
    
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

    /**
     * kertoo onko nappulan ja annetun pysty tai vaakasuuntaisen kohteen välillä muita nappuloita
     * @param x
     * @param y
     * @param nappula
     * @return 
     */
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

    /**
     * palauttaa listan ruuduista joiden x tai y koordinaatti on sama kuin parametrina annettu
     * @param x
     * @param y
     * @return 
     */
    public static ArrayList<Ruutu> mahdollisetPystyTaiVaakaRuudut(int x, int y) {
        ArrayList<Ruutu> ruudut = new ArrayList<Ruutu>();
        for (int i = 0; i < 8; i++) {
            ruudut.add(new Ruutu(x, i));
            ruudut.add(new Ruutu(i, y));

        }
        return ruudut;
    }

    
    /**
     * palauttaa listan ruuduista jotka ovat vinottain samalla linjalla annetun ruudun kanssa
     * @param x
     * @param y
     * @return 
     */
    public static ArrayList<Ruutu> mahdollisetVinoRuudut(int x, int y) {
        ArrayList<Ruutu> ruudut = new ArrayList<Ruutu>();
        int j = y;
        for (int i = x; i < 8 && j < 8; i++) {
            ruudut.add(new Ruutu(i, j));

            j++;
        }

        j = y;
        for (int i = x; i >= 0 && j >= 0; i--) {
            ruudut.add(new Ruutu(i, j));

            j--;
        }

        j = y;
        for (int i = x; i < 8 && j >= 0; i++) {
            ruudut.add(new Ruutu(i, j));

            j--;
        }

        j = y;
        for (int i = x; i >= 0 && j < 8; i--) {
            ruudut.add(new Ruutu(i, j));
            j++;
        }
        return ruudut;
    }

}
