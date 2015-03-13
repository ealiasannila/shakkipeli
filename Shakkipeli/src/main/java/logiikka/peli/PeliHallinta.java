/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.peli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 *
 * @author elias
 */
public class PeliHallinta {

    private Peli peli;

    public PeliHallinta() {
    this.peli = new Peli();
    this.uusiPeli();
    }
    
    

    public void uusiPeli() {
        this.lataaPeli("tallennetutPelit/uusi.txt");
    }

    public void lataaPeli(){
        this.lataaPeli("tallennetutPelit/peli.txt");
    }

    public void lataaPeli(String tiedostonNimi) {
        File peli = new File(tiedostonNimi);
        try{
        this.peli.lataaPeli(peli);
        }catch(FileNotFoundException ex){
            System.out.println("Tiedostoa ei löytynyt: " + tiedostonNimi);
        }
        
        
    }

    public void tallennaPeli() {
        PrintWriter writer;
        try {
            writer = new PrintWriter("tallennetutPelit/peli.txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            return;
        } catch (UnsupportedEncodingException ex) {
            return;
        }
        writer.print(this.peli.toString());

        writer.close();
    }

    public Peli getPeli() {
        return peli;
    }

}
