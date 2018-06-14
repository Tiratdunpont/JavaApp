/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IRA_a
 */
public class Usuari {
    private String DNI, Nom;
    private int edat;
    private boolean sancionat, llogat;
    private List<String> el;
    @Override
    public String toString(){
        return this.Nom+", DNI: "+this.DNI;
    }
    public String getDNI() {
        return DNI;
    }

    public String getNom() {
        return Nom;
    }

    public int getEdat() {
        return edat;
    }
    public Usuari(String Nom, String DNI, int edat){
        this.sancionat = false;
        this.llogat = false;
        this.edat = edat;
        this.DNI = DNI;
        this.Nom = Nom;
        this.el = new ArrayList<String>();
    }
    public Usuari(String Nom, String DNI, int edat, boolean llogat, boolean sancionat){
        this.sancionat = sancionat;
        this.llogat = llogat;
        this.edat = edat;
        this.DNI = DNI;
        this.Nom = Nom;
        this.el = new ArrayList<String>();
    }
    public boolean isSancionat() {
        return sancionat;
    }

    public void setSancionat(boolean sancionat) {
        this.sancionat = sancionat;
    }

    public boolean isLlogat() {
        return llogat;
    }

    public void setLlogat(boolean llogat) {
        this.llogat = llogat;
    }

    public void setEl(List<String> el) {
        this.el = el;
    }
    public String toDB(){
        int prestats = 0;
        int baixats = 0;
        if (llogat){
            prestats = 1;
        }
        if (sancionat){
            baixats = 1;
        }
        System.out.println("Dins");
        return "INSERT INTO Users (Nom, DNI, edat, llogat, sancionat) VALUES ('"+Nom+"', '"+ DNI+"', '"+edat+"', '"+prestats+"', '"+baixats+"');";
    }
    //Aixo falla i no se perque
    //public String Update(String DNI){
        
      //  System.out.println("UPDATE Users SET `Nom` = '"+Nom+"', `DNI` = '"+this.DNI+"', `edat` = '"+edat+"', `llogat` = '"+prestats+"', `sancionat` = '"+baixats+"', WHERE `DNI` = '" + DNI + "'");
        //return ;
    //}
}
