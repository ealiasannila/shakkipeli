/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.peli.Pelilauta;

/**
 *
 * @author elias
 */
public class Kunkku extends Nappula {
    
    
    public Kunkku(Maa maa, int x, int y, Pelilauta pelilauta) {
        super(maa, x, y, pelilauta);
     
    }
    
    public String toString() {
        if (this.getMaa() == VALKOINEN) {
            return "[k]";
        } else {
            return "[K]";
        }
    }

    @Override
    public boolean tarkistaReitti(int x, int y) {
        if(Math.abs(this.getX()-x)>1||Math.abs(this.getY()-y)>1){
        //    System.out.println("LIIAN KAUKANA");
            return false;
        }
        
        return true;
    }
    
    
    
    

}
