package ba.unsa.etf.rpr.tutoriajal7;

import java.io.Serializable;

public class Grad implements Serializable {
    private String naziv;
    private int brojStanovnika;
    private double[] temperature;

    public Grad() {
        naziv = "";
        brojStanovnika = 0;
        temperature = new double[1000];
    }

    public Grad(String naziv, int brojStanovnika) {
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        temperature = new double[1000];
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }
}
