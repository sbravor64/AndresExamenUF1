package com.company;

import java.io.Serializable;

public class Equip implements Serializable {
    private String nom, lliga, classificació, sobrenom;
    private final static long serialVersionUID =14L;
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLliga() {
        return lliga;
    }

    public void setLliga(String lliga) {
        this.lliga = lliga;
    }

    public String getClassificació() {
        return classificació;
    }

    public void setClassificació(String classificació) {
        this.classificació = classificació;
    }

    public String getSobrenom() {
        return sobrenom;
    }

    public void setSobrenom(String sobrenom) {
        this.sobrenom = sobrenom;
    }

    public Equip(String nom, String lliga, String classificació, String sobrenom) {
        this.nom = nom;
        this.lliga = lliga;
        this.classificació = classificació;
        this.sobrenom = sobrenom;
    }

    @Override
    public String toString() {

        return "Nom: " + nom + "\n" +
                "Sobrenom: " + sobrenom  + "\n" +
                "Lliga: " + lliga + "\n" +
                "Classificació: " + classificació + "\n" +
                "_______________" + "\n";
    }
}
