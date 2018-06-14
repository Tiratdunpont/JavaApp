/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor.biblioteca;

/**
 *
 * @author IRA_a
 */
public class ElementBiblioteca {
    private String tipus, titol, editorial, ISBN, anyadquisicio, dataprestec, personaprestada;
    private boolean prestat, baixa;
    private int codi, numExemplar;
    public ElementBiblioteca(){
    }
    public ElementBiblioteca(String tipus, int codi, String titol, String editorial, String anyadquisicio, int numExemplar, String dataprestec, String personaprestada, boolean prestat, boolean baixa, String ISBN){
        this.tipus = tipus;
        this.codi = codi;
        this.titol = titol;
        this.editorial = editorial;
        this.anyadquisicio = anyadquisicio;
        this.numExemplar = numExemplar;
        this.prestat = prestat;
        this.baixa = baixa;
        this.ISBN = ISBN;
        this.personaprestada = personaprestada;
        this.dataprestec  = dataprestec;
    }
    @Override
    public String toString(){
        return this.titol+", Num: "+this.numExemplar;
    }

    public String getTipus() {
        return tipus;
    }

    public String getTitol() {
        return titol;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAnyadquisicio() {
        return anyadquisicio;
    }

    public String getDataprestec() {
        return dataprestec;
    }

    public String getPersonaprestada() {
        return personaprestada;
    }

    public boolean isPrestat() {
        return prestat;
    }

    public boolean isBaixa() {
        return baixa;
    }


    public int getNumExemplar() {
        return numExemplar;
    }
    public int getCodi(){
        return this.codi;
    }
    public String toDB(){
        int prestats = 0;
        int baixats = 0;
        if (prestat){
            prestats = 1;
        }
        if (baixa){
            baixats = 1;
        }
        System.out.println("Dins");
        return "INSERT INTO Elements (tipus, titol, editorial, anyadquisicio, numExemplar, dataprestec, personaprestada, prestat, baixa, ISBN ) VALUES ('"+tipus+"', '"+ titol+"', '"+editorial+"', '"+anyadquisicio+"', '"+numExemplar+"', '"+dataprestec+"', '"+personaprestada+"', '"+prestats+"', '"+baixats+"', '"+ISBN+"');";
    }
    public String Update(){
        int prestats = 0;
        int baixats = 0;
        if (prestat){
            prestats = 1;
        }
        if (baixa){
            baixats = 1;
        }
        return "UPDATE Elements SET tipus = '"+tipus+"', titol = '"+titol+"', editorial = '"+editorial+"', anyadquisicio = '"+anyadquisicio+"', numExemplar = '"+numExemplar+"', dataprestec = '"+dataprestec+"', personaprestada = '"+personaprestada+"', prestat = '"+prestats+"', baixa = '"+baixats+"', ISBN = '"+ISBN+"'" + " WHERE codi = " + codi + ";";
    }
}
