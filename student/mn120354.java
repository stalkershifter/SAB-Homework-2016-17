/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import funkcionalnosti.Funkcionalnosti;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefan
 */
public class mn120354 extends Funkcionalnosti{

    private Connection con = null;
    
    public mn120354(){
            try {
                String url = "jdbc:sqlserver://STALKERSHIFTER\\SQLEXPRESS;databaseName=GradjevinskaFirma;integratedSecurity=true";
                //  try {
                //       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //  } catch (ClassNotFoundException ex) {
                //         Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
                //   }
                con = DriverManager.getConnection(url,"","");
            } catch (SQLException ex) {
                Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
            
    @Override
    public int unesiGradiliste(String naziv, Date datumOsnivanja) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into dbo.Gradiliste (Naziv, DatumOsnivanja) Values (?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, naziv);
            stm.setString(2, datumOsnivanja.toString());
            int rowsInserted = stm.executeUpdate();
         
            if (rowsInserted == 0) 
                throw new SQLException("Insert failed!");
        
                ResultSet genKeys = stm.getGeneratedKeys();
            if(genKeys.next())
                rtnValue = genKeys.getInt(1);
            else
                throw new SQLException("No id obtained!");
         
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtnValue;
    }

    @Override
    public int obrisiGradiliste(int idGradiliste) {
        int succ = 1;
        try{
        String querry = "Delete From dbo.Gradiliste Where idGradilista = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idGradiliste);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 0;
        
        } catch (SQLException ex) {
            succ = 0;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public List<Integer> dohvatiSvaGradilista() {
        List<Integer> svaGradilista = new LinkedList<>();
        try{
            String querry = "Select idGradilista From dbo.Gradiliste";
            PreparedStatement stm = con.prepareStatement(querry);
            ResultSet rs = stm.executeQuery();
         
            while(rs.next()){
                svaGradilista.add(rs.getInt("idGradilista"));
            }
            
            if(svaGradilista.isEmpty())
                svaGradilista = null;
        
        } catch (SQLException ex) {
            svaGradilista = null;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return svaGradilista;
    }

    @Override
    public int unesiObjekat(String naziv, int idGradiliste) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into [dbo].[Objekat] (Naziv, idGradilista) Values (?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, naziv);
            stm.setInt(2, idGradiliste);
            int rowsInserted = stm.executeUpdate();
         
            if (rowsInserted == 0) 
                throw new SQLException("Insert failed!");
        
                ResultSet genKeys = stm.getGeneratedKeys();
                
            if(genKeys.next())
                rtnValue = genKeys.getInt(1);
            else
                throw new SQLException("No id obtained!");
         
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtnValue;
    }

    @Override
    public int obrisiObjekat(int idObjekat) {
       int succ = 1;
        try{
        String querry = "Delete From [dbo].[Objekat] Where idObjekta = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idObjekat);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 0;
        
        } catch (SQLException ex) {
            succ = 0;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public int unesiSprat(int brSprata, int idObjekat) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into [dbo].[Sprat] (idObjekta, BrojSprata) Values (?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, idObjekat);
            stm.setInt(2, brSprata);
            int rowsInserted = stm.executeUpdate();
         
            if (rowsInserted == 0) 
                throw new SQLException("Insert failed!");
        
                ResultSet genKeys = stm.getGeneratedKeys();
                
            if(genKeys.next())
                rtnValue = genKeys.getInt(1);
            else
                throw new SQLException("No id obtained!");
         
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtnValue;
    }

    @Override
    public int obrisiSprat(int idSprat) {
        int succ = 1;
        try{
        String querry = "Delete From [dbo].[Sprat] Where idSprata = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idSprat);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 0;
        
        } catch (SQLException ex) {
            succ = 0;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public int unesiZaposlenog(String ime, String prezime, String jmbg, String pol, String ziroRacun, String email, String brojTelefona) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into [dbo].[Zaposleni] (Ime, Prezime, JMBG, Pol, ZiroRacun, EMail, BrojTelefona) " 
                    + "Values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, ime);
            stm.setString(2, prezime);
            stm.setString(3, jmbg);
            stm.setString(4, pol);
            stm.setString(5, ziroRacun);
            stm.setString(6, email);
            stm.setString(7, brojTelefona);
            int rowsInserted = stm.executeUpdate();
         
            if (rowsInserted == 0) 
                throw new SQLException("Insert failed!");
        
                ResultSet genKeys = stm.getGeneratedKeys();
                
            if(genKeys.next())
                rtnValue = genKeys.getInt(1);
            else
                throw new SQLException("No id obtained!");
         
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtnValue;
    }

    @Override
    public int obrisiZaposlenog(int idZaposleni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal dohvatiUkupanIsplacenIznosZaZaposlenog(int idZaposleni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal dohvatiProsecnuOcenuZaZaposlenog(int idZaposleni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int dohvatiBrojTrenutnoZaduzeneOpremeZaZaposlenog(int idZaposleni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> dohvatiSveZaposlene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int unesiMagacin(int idSef, BigDecimal plata, int idGradiliste) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obrisiMagacin(int idMagacin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int izmeniSefaZaMagacin(int idMagacin, int idSefNovo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int izmeniPlatuZaMagacin(int idMagacin, BigDecimal plataNovo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int isplatiPlateZaposlenimaUSvimMagacinima() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int isplatiPlateZaposlenimaUMagacinu(int idMagacin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int unesiRobuUMagacinPoKolicini(int idRoba, int idMagacin, BigDecimal kolicina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int unesiRobuUMagacinPoBrojuJedinica(int idRoba, int idMagacin, int brojJedinica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal uzmiRobuIzMagacinaPoKolicini(int idRoba, int idMagacin, BigDecimal kolicina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int uzmiRobuIzMagacinaPoBrojuJedinica(int idRoba, int idMagacin, int brojJedinca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal pogledajKolicinuRobeUMagacinu(int idRoba, int idMagacin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int pogledajBrojJedinicaRobeUMagacinu(int idRoba, int idMagacin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int unesiTipRobe(String naziv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obrisiTipRobe(int idTipRobe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int unesiRobu(String naziv, String kod, int idTipRobe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obrisiRobu(int idRoba) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> dohvatiSvuRobu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int zaposleniRadiUMagacinu(int idZaposleni, int idMagacin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int zaposleniNeRadiUMagacinu(int idZaposleni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int zaposleniZaduzujeOpremu(int idZaposlenogKojiZaduzuje, int idMagacin, int idRoba, Date datumZaduzenja, String napomena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int zaposleniRazduzujeOpremu(int idZaduzenjaOpreme, Date datumRazduzenja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int unesiNormuUgradnogDela(String naziv, BigDecimal cenaIzrade, BigDecimal jedinicnaPlataRadnika) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obrisiNormuUgradnogDela(int idNormaUgradnogDela) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal dohvatiJedinicnuPlatuRadnikaNormeUgradnogDela(int idNR) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int unesiPotrebanMaterijalPoBrojuJedinica(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela, int brojJedinica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int unesiPotrebanMaterijalPoKolicini(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela, BigDecimal kolicina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obrisiPotrebanMaterijal(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int unesiPosao(int idNormaUgradnogDela, int idSprat, Date datumPocetka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obrisiPosao(int idPosao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int izmeniDatumPocetkaZaPosao(int idPosao, Date datumPocetka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int zavrsiPosao(int idPosao, Date datumKraja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int zaposleniRadiNaPoslu(int idZaposleni, int idPosao, Date datumPocetka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int zaposleniJeZavrsioSaRadomNaPoslu(int idZaposleniNaPoslu, Date datumKraja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int izmeniDatumPocetkaRadaZaposlenogNaPoslu(int idZaposleniNaPoslu, Date datumPocetkaNovo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int izmeniDatumKrajaRadaZaposlenogNaPoslu(int idZaposleniNaPoslu, Date datumKrajaNovo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int zaposleniDobijaOcenu(int idZaposleniNaPoslu, int ocena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obrisiOcenuZaposlenom(int idZaposleniNaPoslu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int izmeniOcenuZaZaposlenogNaPoslu(int idZaposleniNaPoslu, int ocenaNovo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
