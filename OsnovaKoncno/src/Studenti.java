
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author neon
 */


public class Studenti<Tip> implements java.io.Serializable{
    protected String ime;
    protected String priimek;
    protected String ID;
    protected double povpOcena;
    

    public Studenti(String ime, String priimek, String vpisnaStevilka, double povpOcena) 
    {
        this.ime = ime;
        this.priimek = priimek;
        this.ID = vpisnaStevilka;
        this.povpOcena = povpOcena;
    }
    
    public String getIme()
    {
        return ime;
    }

    public void setIme(String ime)
    {
        this.ime = ime;
    }

    public String getPriimek()
    {
        return priimek;
    }

    public void setPriimek(String priimek)
    {
        this.priimek = priimek;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }
    
    public double getAvgGrade()
    {
        return povpOcena;
    }

    public void setAvgGrade(float avgGrade)
    {
        this.povpOcena = avgGrade;
    }
    
    @Override
    public String toString()
    {
        return ID + " | "+priimek+", "+ime+" | "+povpOcena;
    }
    
}

    
    
