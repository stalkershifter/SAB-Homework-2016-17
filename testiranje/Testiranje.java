/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testiranje;

import funkcionalnosti.Funkcionalnosti;
import java.sql.Date;
import java.util.List;
import student.mn120354;
import testovi.JavniTest;

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

       // System.out.println("Sprat id: " + funkcionalnosti.unesiSprat(3, 13));
        funkcionalnosti.unesiZaposlenog("Milos", "Milosevic", "2503989720031", "M", "370-11032274-01", "milos@google.com", "069/1245301");
        funkcionalnosti.unesiZaposlenog("Jovan", "Jovanovic", "2403989720031", "M", "370-11032274-02", "jovan@google.com", "069/1245302");
        funkcionalnosti.unesiZaposlenog("Marko", "Markovic", "2402989720031", "M", "370-11032274-03", "marko@google.com", "069/1245303");
        funkcionalnosti.unesiZaposlenog("Magdalena", "Despotovic", "2403987720031", "Z", "370-11032274-03", "magdalena@google.com", "069/1245303");
        funkcionalnosti.unesiZaposlenog("Katarina", "Vasic", "1204990720031", "Z", "370-11032274-04", "katarina@google.com", "069/1245304");
        double procenata = 100; //JavniTest.test(funkcionalnosti);
        double koeficijentDomaci = 0.2;
        double koeficijentJavniTest = 0.5;
        double koeficijentTajniTestovi = 0.5;
        
        System.out.println();
        System.out.println("==============================================");
        System.out.println("Na javnom testu osvojili ste " + procenata * koeficijentDomaci * koeficijentJavniTest + " poena");
        System.out.println("==============================================");
    }
    
}
