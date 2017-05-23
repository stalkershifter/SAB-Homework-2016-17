/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import funkcionalnosti.Funkcionalnosti;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
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
        int succ = 0;
        try{
        String querry = "Delete From dbo.Gradiliste Where idGradilista = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idGradiliste);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
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
       int succ = 0;
        try{
        String querry = "Delete From [dbo].[Objekat] Where idObjekta = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idObjekat);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
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
        int succ = 0;
        try{
        String querry = "Delete From [dbo].[Sprat] Where idSprata = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idSprat);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
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
        int succ = 0;
        try{
        String querry = "Delete From [dbo].[Zaposleni] Where idZaposlenog = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idZaposleni);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public BigDecimal dohvatiUkupanIsplacenIznosZaZaposlenog(int idZaposleni) {
        BigDecimal ukupnaIsplata = new BigDecimal(-1);
        try{
            String querry = "Select UkupnaZarada From [dbo].[Zaposleni] Where idZaposlenog = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idZaposleni);
            ResultSet rs = stm.executeQuery();
         
            if(rs.next()){
                ukupnaIsplata = new BigDecimal(rs.getInt(1));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ukupnaIsplata;
    }

    @Override
    public BigDecimal dohvatiProsecnuOcenuZaZaposlenog(int idZaposleni) { 
        BigDecimal prosecnaOcena = new BigDecimal(-1);
        try{
            String querry = "Select ProsecnaOcena From [dbo].[Zaposleni] Where idZaposlenog = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idZaposleni);
            ResultSet rs = stm.executeQuery();
         
            if(rs.next()){
                prosecnaOcena = new BigDecimal(rs.getInt(1));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prosecnaOcena;        
    }

    @Override
    public int dohvatiBrojTrenutnoZaduzeneOpremeZaZaposlenog(int idZaposleni) {
        int zaduzenaOprema = -1;
        try{
            String querry = "Select ZaduzenaOprema From [dbo].[Zaposleni] Where idZaposlenog = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idZaposleni);
            ResultSet rs = stm.executeQuery();
         
            if(rs.next()){
                zaduzenaOprema = rs.getInt(1);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zaduzenaOprema;
    }

    @Override
    public List<Integer> dohvatiSveZaposlene() {
        List<Integer> sviZaposleni = new LinkedList<>();
        try{
            String querry = "Select idZaposlenog From [dbo].[Zaposleni]";
            PreparedStatement stm = con.prepareStatement(querry);
            ResultSet rs = stm.executeQuery();
         
            while(rs.next()){
                sviZaposleni.add(rs.getInt("idZaposlenog"));
            }
            
            if(sviZaposleni.isEmpty())
                sviZaposleni = null;
        
        } catch (SQLException ex) {
            sviZaposleni = null;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sviZaposleni;
    }

    @Override
    public int unesiMagacin(int idSef, BigDecimal plata, int idGradiliste) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into Magacin (idSefa, idGradilista, Plata) " +
                            "Values (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, idSef);
            stm.setInt(2, idGradiliste);
            stm.setBigDecimal(3, plata);
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
    public int obrisiMagacin(int idMagacin) {
        int succ = 0;
        try{
        String querry = "Delete From [dbo].[Magacin] Where idMagacina = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idMagacin);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public int izmeniSefaZaMagacin(int idMagacin, int idSefNovo) {
        int succ = 0;
        try {
            String querry = "Update [dbo].[Magacin] Set idSefa = ? Where idMagacina = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idSefNovo);
            stm.setInt(2, idMagacin);
            int rowsInserted = stm.executeUpdate();
         
            if (rowsInserted == 0)
                throw new SQLException("Update failed!");
            
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;        
    }

    @Override
    public int izmeniPlatuZaMagacin(int idMagacin, BigDecimal plataNovo) {
        int succ = 0;
        try {
            String querry = "Update [dbo].[Magacin] Set Plata = ? Where idMagacina = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setBigDecimal(1, plataNovo);
            stm.setInt(2, idMagacin);
            int rowsUpdated = stm.executeUpdate();
         
            if (rowsUpdated == 0)
                throw new SQLException("Update failed!");
            
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;  
    }

    @Override
    public int isplatiPlateZaposlenimaUSvimMagacinima() {
        int succ = 0;
        try {
            String querry = "Exec isplataPlatePoMagacinima ?";
            CallableStatement stm = con.prepareCall(querry);
            
            stm.setNull(1, Types.NULL);
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public int isplatiPlateZaposlenimaUMagacinu(int idMagacin) {
        int succ = 0;
        try {
            String querry = "Exec isplataPlatePoMagacinima ?";
            CallableStatement stm = con.prepareCall(querry);
           
            stm.setInt(1, idMagacin);
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public int unesiRobuUMagacinPoKolicini(int idRoba, int idMagacin, BigDecimal kolicina) {
        int rtnValue = -1;
        try {
            String querry = "{? = Call unosRobePoKolicini (?, ?, ?)}";
            CallableStatement stm = con.prepareCall(querry);
            
            stm.registerOutParameter(1, Types.INTEGER);
            stm.setInt(2, idRoba);
            stm.setInt(3, idMagacin);
            stm.setBigDecimal(4, kolicina);
            
            stm.execute();
            
            rtnValue = stm.getInt(1);
           
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtnValue;
    }

    @Override
    public int unesiRobuUMagacinPoBrojuJedinica(int idRoba, int idMagacin, int brojJedinica) {
        int rtnValue = -1;
        try {
            String querry = "{? = Call unosRobePoJedinici (?, ?, ?)}";
            CallableStatement stm = con.prepareCall(querry);
            
            stm.registerOutParameter(1, Types.INTEGER);
            stm.setInt(2, idRoba);
            stm.setInt(3, idMagacin);
            stm.setInt(4, brojJedinica);
            
            stm.execute();
            
            rtnValue = stm.getInt(1);
           
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtnValue;
    }

    @Override
    public BigDecimal uzmiRobuIzMagacinaPoKolicini(int idRoba, int idMagacin, BigDecimal kolicina) {
        BigDecimal rtnValue = new BigDecimal(-1);
        try {
            String querry = "{? = Call uzimanjeRobePoKolicini (?, ?, ?)}";
            CallableStatement stm = con.prepareCall(querry);
            
            stm.registerOutParameter(1, Types.DECIMAL);
            stm.setInt(2, idRoba);
            stm.setInt(3, idMagacin);
            stm.setBigDecimal(4, kolicina);
            
            stm.execute();
            
            rtnValue = stm.getBigDecimal(1);
                
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtnValue;
    }

    @Override
    public int uzmiRobuIzMagacinaPoBrojuJedinica(int idRoba, int idMagacin, int brojJedinca) {
            int rtnValue = -1;
        try {
            String querry = "{? = Call uzimanjeRobePoJedinicama (?, ?, ?)}";
            CallableStatement stm = con.prepareCall(querry);
            
            stm.registerOutParameter(1, Types.INTEGER);
            stm.setInt(2, idRoba);
            stm.setInt(3, idMagacin);
            stm.setInt(4, brojJedinca);
            
            stm.execute();
            
            rtnValue = stm.getInt(1);
                
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtnValue;
    }

    @Override
    public BigDecimal pogledajKolicinuRobeUMagacinu(int idRoba, int idMagacin) {
        BigDecimal kolicina = new BigDecimal(-1);
        try{
            String querry = "Select Kolicina From [dbo].[RobaPoKolicini] Where idRobe = ? And idMagacina = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idRoba);
            stm.setInt(2, idMagacin);
            ResultSet rs = stm.executeQuery();
         
            if(rs.next()){
                kolicina = rs.getBigDecimal(1);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kolicina;
    }

    @Override
    public int pogledajBrojJedinicaRobeUMagacinu(int idRoba, int idMagacin) {
        int brojJedinica = -1;
        try{
            String querry = "Select BrojJedinica From [dbo].[RobaPoJedinici] Where idRobe = ? And idMagacina = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idRoba);
            stm.setInt(2, idMagacin);
            ResultSet rs = stm.executeQuery();
         
            if(rs.next()){
                brojJedinica = rs.getInt(1);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brojJedinica;
    }

    @Override
    public int unesiTipRobe(String naziv) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into dbo.TipRobe (Naziv) Values (?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, naziv);
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
    public int obrisiTipRobe(int idTipRobe) {
    int succ = 0;
        try{
        String querry = "Delete From [dbo].[TipRobe] Where idTipRobe = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idTipRobe);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public int unesiRobu(String naziv, String kod, int idTipRobe) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into dbo.Roba (idTipRobe, Naziv, Kod) Values (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, naziv);
            stm.setString(2, kod);
            stm.setInt(3, idTipRobe);
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
    public int obrisiRobu(int idRoba) {
        int succ = 0;
        try{
        String querry = "Delete From [dbo].[Roba] Where idRobe = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idRoba);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public List<Integer> dohvatiSvuRobu() {
        List<Integer> svaRoba = new LinkedList<>();
        try{
            String querry = "Select idRobe From dbo.Roba ";
            PreparedStatement stm = con.prepareStatement(querry);
            ResultSet rs = stm.executeQuery();
         
            while(rs.next()){
                svaRoba.add(rs.getInt("idRobe"));
            }
            
            if(svaRoba.isEmpty())
                svaRoba = null;
        
        } catch (SQLException ex) {
            svaRoba = null;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return svaRoba;
    }

    @Override
    public int zaposleniRadiUMagacinu(int idZaposleni, int idMagacin) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into dbo.RadiUMagacinu (idZaposlenog, idMagacina) Values (?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, idZaposleni);
            stm.setInt(2, idMagacin);
            
            int rowsInserted = stm.executeUpdate();
         
            if (rowsInserted == 0) 
                throw new SQLException("Insert failed!");
        
            rtnValue = idZaposleni;
            
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtnValue;
    }

    @Override
    public int zaposleniNeRadiUMagacinu(int idZaposleni) {
        int succ = 0;
        try{
        String querry = "Delete From [dbo].[RadiUMagacinu] Where idZaposlenog = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idZaposleni);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public int zaposleniZaduzujeOpremu(int idZaposlenogKojiZaduzuje, int idMagacin, int idRoba, Date datumZaduzenja, String napomena) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into dbo.ZaduzenaOprema"
                    + " (idZaposlenog, idMagacina, idOpreme, DatumZaduzenja, Napomena) Values (?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, idZaposlenogKojiZaduzuje);
            stm.setInt(2, idMagacin);
            stm.setInt(3, idRoba);
            stm.setDate(4, datumZaduzenja);
            stm.setString(5, napomena);
            
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
    public int zaposleniRazduzujeOpremu(int idZaduzenjaOpreme, Date datumRazduzenja) {
        int succ = 0;
        try {
            String querry = "Update [dbo].ZaduzenaOprema Set DatumRazduzenja = ? Where idZaduzeneOpreme = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setDate(1, datumRazduzenja);
            stm.setInt(2, idZaduzenjaOpreme);
            int rowsUpdated = stm.executeUpdate();
         
            if (rowsUpdated == 0)
                throw new SQLException("Update failed!");
            
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;  
    }

    @Override
    public int unesiNormuUgradnogDela(String naziv, BigDecimal cenaIzrade, BigDecimal jedinicnaPlataRadnika) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into dbo.NormaUgradnihDelova (Naziv, CenaIzrade, JedinicnaPlata) Values (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, naziv);
            stm.setBigDecimal(2, cenaIzrade);
            stm.setBigDecimal(3, jedinicnaPlataRadnika);
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
    public int obrisiNormuUgradnogDela(int idNormaUgradnogDela) {
        int succ = 0;
        try{
        String querry = "Delete From [dbo].[NormaUgradnihDelova] Where idNUD = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idNormaUgradnogDela);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public BigDecimal dohvatiJedinicnuPlatuRadnikaNormeUgradnogDela(int idNR) {
      BigDecimal brojJedinica = new BigDecimal(-1);
        try{
            String querry = "Select JedinicnaPlata From [dbo].[NormaUgradnihDelova] Where idNUD = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idNR);
            ResultSet rs = stm.executeQuery();
         
            if(rs.next()){
                brojJedinica = rs.getBigDecimal(1);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brojJedinica;
    }

    @Override
    public int unesiPotrebanMaterijalPoBrojuJedinica(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela, int brojJedinica) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into dbo.PotrebanMaterijal (idRobe, idNUD, BrojJedinica) Values (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, idRobaKojaJePotrosniMaterijal);
            stm.setInt(2, idNormaUgradnogDela);
            stm.setInt(3, brojJedinica);
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
    public int unesiPotrebanMaterijalPoKolicini(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela, BigDecimal kolicina) {
        int rtnValue = -1;
        try {
            String querry = "Insert Into dbo.PotrebanMaterijal (idRobe, idNUD, Kolicina) Values (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, idRobaKojaJePotrosniMaterijal);
            stm.setInt(2, idNormaUgradnogDela);
            stm.setBigDecimal(3, kolicina);
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
    public int obrisiPotrebanMaterijal(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela) {
        int succ = 0;
        try{
        String querry = "Delete From [dbo].[PotrebanMaterijal] Where idRobe = ? And idNUD = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idRobaKojaJePotrosniMaterijal);
            stm.setInt(2, idNormaUgradnogDela);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
    }

    @Override
    public int unesiPosao(int idNormaUgradnogDela, int idSprat, Date datumPocetka) {
		int rtnValue = -1;
        try {
           // String querry = "Insert Into dbo.PotrebanMaterijal (idRobe, idNUD, Kolicina) Values (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, idNormaUgradnogDela);
            stm.setInt(2, idSprat);
            stm.setDate(3, datumPocetka);
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
    public int obrisiPosao(int idPosao) {
		int succ = 0;
        try{
        //String querry = "Delete From [dbo].[PotrebanMaterijal] Where idRobe = ? And idNUD = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idPosao);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
	}

    @Override
    public int izmeniDatumPocetkaZaPosao(int idPosao, Date datumPocetka) {
		int succ = 0;
        try {
          //  String querry = "Update [dbo].ZaduzenaOprema Set DatumRazduzenja = ? Where idZaduzeneOpreme = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setDate(1, datumPocetka);
            stm.setInt(2, idPosao);
            int rowsUpdated = stm.executeUpdate();
         
            if (rowsUpdated == 0)
                throw new SQLException("Update failed!");
            
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
	}

    @Override
    public int zavrsiPosao(int idPosao, Date datumKraja) {
		int succ = 0;
        try {
          //  String querry = "Update [dbo].ZaduzenaOprema Set DatumRazduzenja = ? Where idZaduzeneOpreme = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setDate(1, datumKraja);
            stm.setInt(2, idPosao);
            int rowsUpdated = stm.executeUpdate();
         
            if (rowsUpdated == 0)
                throw new SQLException("Update failed!");
            
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
	}

    @Override
    public int zaposleniRadiNaPoslu(int idZaposleni, int idPosao, Date datumPocetka) {
		int rtnValue = -1;
        try {
           // String querry = "Insert Into dbo.PotrebanMaterijal (idRobe, idNUD, Kolicina) Values (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(querry, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, idZaposleni);
            stm.setInt(2, idPosao);
            stm.setDate(3, datumPocetka);
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
    public int zaposleniJeZavrsioSaRadomNaPoslu(int idZaposleniNaPoslu, Date datumKraja) {
		int succ = 0;
        try {
          //  String querry = "Update [dbo].ZaduzenaOprema Set DatumRazduzenja = ? Where idZaduzeneOpreme = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setDate(1, datumKraja);
            stm.setInt(2, idZaposleniNaPoslu);
            int rowsUpdated = stm.executeUpdate();
         
            if (rowsUpdated == 0)
                throw new SQLException("Update failed!");
            
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
	}

    @Override
    public int izmeniDatumPocetkaRadaZaposlenogNaPoslu(int idZaposleniNaPoslu, Date datumPocetkaNovo) {
		int succ = 0;
        try {
          //  String querry = "Update [dbo].ZaduzenaOprema Set DatumRazduzenja = ? Where idZaduzeneOpreme = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setDate(1, datumKraja);
            stm.setInt(2, idZaposleniNaPoslu);
            int rowsUpdated = stm.executeUpdate();
         
            if (rowsUpdated == 0)
                throw new SQLException("Update failed!");
            
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
	}

    @Override
    public int izmeniDatumKrajaRadaZaposlenogNaPoslu(int idZaposleniNaPoslu, Date datumKrajaNovo) {
		int succ = 0;
        try {
          //  String querry = "Update [dbo].ZaduzenaOprema Set DatumRazduzenja = ? Where idZaduzeneOpreme = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setDate(1, datumKrajaNovo);
            stm.setInt(2, idZaposleniNaPoslu);
            int rowsUpdated = stm.executeUpdate();
         
            if (rowsUpdated == 0)
                throw new SQLException("Update failed!");
            
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
	}

    @Override
    public int zaposleniDobijaOcenu(int idZaposleniNaPoslu, int ocena) {
		int rtnValue = -1;
        try {
           // String querry = "Insert Into dbo.PotrebanMaterijal (idRobe, idNUD, Kolicina) Values (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idZaposleniNaPoslu);
            stm.setInt(2, ocena);
            int rowsInserted = stm.executeUpdate();
         
            if (rowsInserted == 0) 
                throw new SQLException("Insert failed!");
        
            rtnValue = idZaposleniNaPoslu;
         
        } catch (SQLException ex) {
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtnValue;
	}

    public int obrisiOcenuZaposlenom(int idZaposleniNaPoslu) {
		int succ = 0;
        try{
        //String querry = "Delete From [dbo].[PotrebanMaterijal] Where idRobe = ? And idNUD = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, idZaposleniNaPoslu);
            int rowsDeleted = stm.executeUpdate();
         
            if (rowsDeleted == 0) 
                succ = 1;
        
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
	}

    @Override
    public int izmeniOcenuZaZaposlenogNaPoslu(int idZaposleniNaPoslu, int ocenaNovo) {
		int succ = 0;
        try {
          //  String querry = "Update [dbo].ZaduzenaOprema Set DatumRazduzenja = ? Where idZaduzeneOpreme = ?";
            PreparedStatement stm = con.prepareStatement(querry);
            stm.setInt(1, ocenaNovo);
            stm.setInt(2, idZaposleniNaPoslu);
            int rowsUpdated = stm.executeUpdate();
         
            if (rowsUpdated == 0)
                throw new SQLException("Update failed!");
            
        } catch (SQLException ex) {
            succ = 1;
            Logger.getLogger(mn120354.class.getName()).log(Level.SEVERE, null, ex);
        }
        return succ;
	}
    
}
