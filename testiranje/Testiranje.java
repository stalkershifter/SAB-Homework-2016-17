/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testiranje;

import funkcionalnosti.Funkcionalnosti;
import java.math.BigDecimal;
import java.sql.Date;
//import java.math.BigDecimal;
//import java.util.List;
import student.mn120354;
//import testovi.JavniTest;

/**
 *
 * @author stefan
 */
public class Testiranje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Funkcionalnosti funkcionalnosti = new mn120354();

       // System.out.println("Sprat id: " + funkcionalnosti.unesiSprat(3, 13));nutnoZaduzeneOpremeZaZaposlenog(11));
        
       // System.out.println("Unet magacin: " + funkcionalnosti.unesiMagacin(7, new BigDecimal(300), 13));
       int id; 
//       System.err.println("ID: " + (funkcionalnosti.unesiNormuUgradnogDela("Ugradni Deo 2", new BigDecimal(1000), new BigDecimal(100)))); 
       System.err.println(funkcionalnosti.obrisiNormuUgradnogDela(3) == 0 ? "Uspesno" : "Neuspesno");
        /*double procenata = 100; //JavniTest.test(funkcionalnosti);
        double koeficijentDomaci = 0.2;
        double koeficijentJavniTest = 0.5;
        double koeficijentTajniTestovi = 0.5;
        
        System.out.println();
        System.out.println("==============================================");
        System.out.println("Na javnom testu osvojili ste " + procenata * koeficijentDomaci * koeficijentJavniTest + " poena");
        System.out.println("==============================================");*/
    }
    
}
