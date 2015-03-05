/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.nappulat;

import static logiikka.nappulat.Maa.VALKOINEN;
import logiikka.pelilauta.Pelilauta;

/**
 *
 * @author elias
 */
public class Kunkku extends Nappula {
    
    
    public Kunkku(Maa maa, int x, int y, Pelilauta pelilauta) {
        super(maa, x, y, pelilauta);
        if(this.getMaa()==VALKOINEN){
            this.getLauta().setValkonenKunkku(this);
        }else{
            this.getLauta().setMustaKunkku(this);
        }
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
        if(Math.abs(this.getX()-x)>1||Math.abs(this.getX()-y)>1){
    ///        System.out.println("LIIAN KAUKANA");
            return false;
        }
        
        return true;
    }
    
    public boolean onShakissa(){
        return this.getLauta().onkoUhattuna(this.getX(), this.getY(), this.getMaa());
    }
    
    public boolean myosYmparoivatRuudutUhattuna(){  //MITEN TARKISTETAAN VOIKO MENNÄ VÄLIIN/SYÖDÄ? EHKÄ REITTIÄ KUKA UHKAA-mitkä ruudut välillä, voiko joku oma nappi mennä väliin?
        for (int y = this.getY()-1; y < this.getY()+1; y++) {
            for (int x = this.getX()-1; x < this.getX()+1; x++) {
                if(!this.getLauta().onkoUhattuna(x, y, this.getMaa())){
                    return false;
                }
            }
        }
        return true;
    }
    
    

}
