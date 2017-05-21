--Public int unesiGradiliste(String naziv, Date datumOsnivanja)
--@return idGradiliste ili -1 u slucaju greske

Declare @Naziv varchar(100),
		@DatumOsnivanja datetime

Set		@Naziv = 'Gradiliste 3'
Set		@DatumOsnivanja = '2017-08-25'

Insert Into [dbo].[Gradiliste] (Naziv, DatumOsnivanja) Values (@Naziv, @DatumOsnivanja)
Select @@Identity

Select * From Gradiliste

--public int obrisiGradiliste(int idGradiliste)
--@return 0-operacija uspesna 1-operacija neuspesna

Declare @idGradiliste int
Delete From [dbo].[Gradiliste] Where idGradilista = @idGradiliste

--public List<Integer> dohvatiSvaGradilista()
--@return lista primarnih kljuceva svih gradilista ili null u slucaju greske ili ukoliko je lista prazna 

Select idGradilista From dbo.Gradiliste  

--public int unesiObjekat(String naziv, int idGradiliste) 
--@return idObjekat ili -1 u slucaju greske 

Declare @Naziv varchar(100),
		@idGradilista int

Set		@Naziv = 'Stambena zgrada 6'
Set		@idGradilista = 3

Insert Into [dbo].[Objekat] (Naziv, idGradilista) Values (@Naziv, @idGradilista) 
Select @@Identity

Select * From [Objekat]

--public int obrisiObjekat(int idObjekat);
--@return 0-operacija uspesna 1-operacija neuspesna
     
Declare @idObjekta int
Delete From [dbo].[Objekat] Where idObjekta = @idObjekta

--public int unesiSprat(int brSprata, int idObjekat); 
--@return idSprat ili -1 u slucaju greske
  
select * from [Sprat]

Insert Into Sprat (idObjekta, BrojSprata) Values (5, 3)

--@return 0-operacija uspesna 1-operacija neuspesna
--public int obrisiSprat(int idSprat); 

Declare @idSprata int
Delete From [dbo].[Sprat] Where idSprata = @idSprata

--@return idZaposleni ili -1 u slucaju greske
--public int unesiZaposlenog(String ime, String prezime, String jmbg, String pol, String ziroRacun, String email, String brojTelefona); 

Declare @ime varchar(100),
		@prezime varchar(100),
		@jmbg varchar(13),
		@pol varchar(1),
		@ziroRacun varchar(20),
		@eMail varchar(100),
		@brojTelefona varchar(20)

Set @ime = 'Ime3'
Set	@prezime = 'Prezime3'
Set	@jmbg = '12356322383121'
Set	@pol = 'M'
Set	@ziroRacun = '370-123612-13'
Set	@eMail = 'ime_prezime@gmail.com'
Set	@brojTelefona = '064/26714162'

Insert Into Zaposleni (Ime, Prezime, JMBG, Pol, ZiroRacun, EMail, BrojTelefona)
Values (@ime, @prezime, @jmbg, @pol, @ziroRacun, @eMail, @brojTelefona)
Select @@Identity

select * from Zaposleni

--@return 0-operacija uspesna 1-operacija neuspesna   
--public int obrisiZaposlenog(int idZaposleni) 

Declare @idZaposlenog int
Delete From [dbo].[Zaposleni] Where idZaposlenog = @idZaposlenog

--@return ukupanIsplacenIznos ili -1 ukoliko zaposleni ne postoji
--public BigDecimal dohvatiUkupanIsplacenIznosZaZaposlenog(int idZaposleni)

Declare @idZaposlenog int
Set @idZaposlenog = 6

Select UkupnaZarada From [dbo].[Zaposleni] Where idZaposlenog = @idZaposlenog

--@return prosecnaOcena ili -1 ukoliko zaposleni ne postoji
--public BigDecimal dohvatiProsecnuOcenuZaZaposlenog(int idZaposleni)

Declare @idZaposlenog int
Set @idZaposlenog = 6

Select ProsecnaOcena From [dbo].[Zaposleni] Where idZaposlenog = @idZaposlenog    
	

--@return brojTrenutnoZaduzeneOpreme ili -1 ukoliko zaposleni ne postoji
--public int dohvatiBrojTrenutnoZaduzeneOpremeZaZaposlenog(int idZaposleni)

Declare @idZaposlenog int
Set @idZaposlenog = 6

Select ZaduzenaOprema From [dbo].[Zaposleni] Where idZaposlenog = @idZaposlenog 

--@return lista primarnih kljuceva svih zaposlenih ili null u slucaju greske ili ukoliko nema zaposlenih
--public List<Integer> dohvatiSveZaposlene();
    
Select idZaposlenog From [dbo].[Zaposleni]

--@return idMagacin -1 u slucaju greske
--public int unesiMagacin(int idSef, BigDecimal plata, int idGradiliste) 

Declare @idSef int,
		@plata decimal(10,3),
		@idGradiliste int

Set @idSef = 8
Set	@plata = 700
Set	@idGradiliste = 1

Insert Into Magacin (idSefa, idGradilista, Plata)
Values (@idSef, @idGradiliste, @plata)
Select @@Identity

select * from zaposleni
select * from Magacin

--@return 0-operacija uspesna 1-operacija neuspesna
--public int obrisiMagacin(int idMagacin)

Declare @idMagacina int
Delete From [dbo].[Magacin] Where idMagacina = 5

--@return 0-operacija uspesna 1-operacija neuspesna
--public int izmeniSefaZaMagacin(int idMagacin, int idSefNovo)

Declare @idSefNovo int,
		@idMagacin int   

Set		@idSefNovo = 2
Set		@idMagacin = 5

Update [dbo].[Magacin] Set idSefa = @idSefNovo Where idMagacina = @idMagacin

--@return 0-operacija uspesna 1-operacija neuspesna
--public int izmeniPlatuZaMagacin(int idMagacin, BigDecimal plataNovo);

Declare @plataNovo int,
		@idMagacin int   

Set		@plataNovo = 400
Set		@idMagacin = 5

Update [dbo].[Magacin] Set Plata = @plataNovo Where idMagacina = @idMagacin


--@return 0-operacija uspesna 1-operacija neuspesna
--public int isplatiPlateZaposlenimaUSvimMagacinima()

Insert Into RadiUMagacinu
Select  5, 8

Select * From Magacin
Select * From RadiUMagacinu
Select * From Zaposleni

Declare @idMagacina int
Exec isplataPlatePoMagacinima @idMagacina

--@return 0-operacija uspesna 1-operacija neuspesna
--public int isplatiPlateZaposlenimaUMagacinu(int idMagacin)

Declare @idMagacina int
Set @idMagacina = 1
Exec isplataPlatePoMagacinima @idMagacina
    
--@return idRobaUMagacinu ili -1 u slucaju greske
--public int unesiRobuUMagacinPoKolicini(int idRoba, int idMagacin, BigDecimal kolicina)

DECLARE	@idRobe int,
		@idMagacina int,
		@Kolicina decimal(10, 3), 
		@return_value int

Exec @return_value = uzimanjeRobePoKolicini @idRobe, @idMagacina, @Kolicina
Select @return_value
    
--@return idRobaUMagacinu ili -1 u slucaju greske
--public int unesiRobuUMagacinPoBrojuJedinica(int idRoba, int idMagacin, int brojJedinica) 

DECLARE	@idRobe int,
		@idMagacina int,
		@BrojJedinica int, 
		@return_value int

Exec @return_value = unosRobePoJedinici @idRobe, @idMagacina, @BrojJedinica
Select @return_value

--@return kolicinaRobeUzetaIzMagacina ili -1 u slucaju greske
--public BigDecimal uzmiRobuIzMagacinaPoKolicini(int idRoba, int idMagacin, BigDecimal kolicina)
    
DECLARE	@idRobe int,
		@idMagacina int,
		@Kolicina decimal(10, 3), 
		@return_value decimal(10, 3)

Exec @return_value = uzimanjeRobePoKolicini @idRobe, @idMagacina, @BrojJedinica
Select @return_value	
 
--@return kolicinaRobeUzetaIzMagacina ili -1 u slucaju greske
--public int uzmiRobuIzMagacinaPoBrojuJedinica(int idRoba, int idMagacin, int brojJedinca)

DECLARE	@idRobe int,
		@idMagacina int,
		@BrojJedinica int, 
		@return_value int

Exec @return_value = uzimanjeRobePoJedinicama @idRobe, @idMagacina, @BrojJedinica
Select @return_value

--@return kolicinaRobeUMagacinu, -1 ukoliko je roba definisana u broju jedinica ili je u pitanju druga greska
--public abstract BigDecimal pogledajKolicinuRobeUMagacinu(int idRoba, int idMagacin)
     
DECLARE	@idRobe int,
		@idMagacina int

		set @idMagacina = 2
		set @idRobe = 1

Select Kolicina From [dbo].[RobaPoKolicini] Where idRobe = @idRobe And idMagacina = @idMagacina

--@return brojJedinicaRobeUMagacinu, -1 ukoliko je roba definisana kolicinom ili je u pitanju druga greska
--public abstract int pogledajBrojJedinicaRobeUMagacinu(int idRoba, int idMagacin)
  
DECLARE	@idRobe int,
		@idMagacina int

		set @idMagacina = 1
		set @idRobe = 13

Select BrojJedinica From [dbo].[RobaPoJedinici] Where idRobe = @idRobe And idMagacina = @idMagacina

--@return idTipRobe ili -1 u slucaju greske
--public int unesiTipRobe(String naziv)
    
Declare @NazivTipa int
Insert Into dbo.TipRobe (Naziv) Values (@NazivTipa)
	
	
--@return 0-operacija uspesna 1-operacija neuspesna
--public int obrisiTipRobe(int idTipRobe); 
    
Declare @idTipRobe int
Delete From [dbo].[TipRobe] Where idTipRobe = @idTipRobe

--@return idRoba ili -1 u slucaju greske
--public int unesiRobu(String naziv, String kod, int idTipRobe); 
  
Declare @Naziv int,
		@Kod varchar(4),
		@idTipRobe
Insert Into dbo.Roba (idTipRobe, Naziv, Kod) Values (@idTipRobe, @Naziv, @Kod)
  
--@return 0-operacija uspesna 1-operacija neuspesna
--public int obrisiRobu(int idRoba); 
 
Declare @idRobe int
Delete From [dbo].[Roba] Where idRobe = @idRobe
    
--@return lista primarnih kljuceva sve robe ili null u slucaju greske ili ukoliko nema robe
--public List<Integer> dohvatiSvuRobu()

Select idRobe From dbo.Roba  
    
--@return argument idZaposleni ili -1 u slucaju greske
--public int zaposleniRadiUMagacinu(int idZaposleni, int idMagacin)

Declare @idZaposlenog int,	
		@idMagacina int

Insert Into dbo.RadiUMagacinu (idZaposlenog, idMagacina)
Values (@idZaposlenog, @idMagacina)

--@return 0-operacija uspesna 1-operacija neuspesna
--public int zaposleniNeRadiUMagacinu(int idZaposleni)
 
Declare @idZaposlenog int
Set @idZaposlenog = 11
Delete From [dbo].[RadiUMagacinu] Where idZaposlenog = @idZaposlenog
    
--@return idZaduzenjaOpreme ili -1 u slucaju greske
--public int zaposleniZaduzujeOpremu(int idZaposlenogKojiZaduzuje, int idMagacin, int idRoba, Date datumZaduzenja, String napomena)

Declare @idMagacina int,
		@idOpreme int,
		@idZaposlenog int,
		@DatumZaduzenja datetime,
		@Napomena varchar(100) 

Set @idMagacina = 1
Set	@idOpreme = 13
Set	@idZaposlenog = 2
Set	@DatumZaduzenja = '20181003'
Set	@Napomena = 'Napomena3'

Insert Into dbo.ZaduzenaOprema (idMagacina, idOpreme, idZaposlenog, DatumZaduzenja, Napomena) 
Values (@idMagacina, @idOpreme, @idZaposlenog, @DatumZaduzenja, @Napomena)

Select @@Identity

--@return 0-operacija uspesna 1-operacija neuspesna
--public int zaposleniRazduzujeOpremu(int idZaduzenjaOpreme, Date datumRazduzenja); 

Declare @idZaduzeneOpreme int,
		@datumRazduzenja datetime   

Set		@idZaduzeneOpreme = 5
Set		@datumRazduzenja = '20181003'

Update [dbo].ZaduzenaOprema Set DatumRazduzenja = @datumRazduzenja Where idZaduzeneOpreme = @idZaduzeneOpreme

--@return idNormaUgradnogDela ili -1 u slucaju greske
--public int unesiNormuUgradnogDela(String naziv, BigDecimal cenaIzrade, BigDecimal jedinicnaPlataRadnika)
   
Declare @Naziv varchar(100),
		@CenaIzrade decimal(10, 3),
		@JedinicnaPlata decimal(10, 3)

Set @Naziv = 'Ugradni Deo 1'
Set	@CenaIzrade = 800
Set	@JedinicnaPlata = 50

Insert Into dbo.NormaUgradnihDelova (Naziv, CenaIzrade, JedinicnaPlata) 
Values (@Naziv, @CenaIzrade, @JedinicnaPlata)

Select @@Identity

Select * From dbo.NormaUgradnihDelova

--@return 0-operacija uspesna 1-operacija neuspesna
--public int obrisiNormuUgradnogDela(int idNormaUgradnogDela)
   
Declare @idNUD int
Set @idNUD = 3
Delete From [dbo].[NormaUgradnihDelova] Where idNUD = @idNUD   
    
--@return jenicnaPlataRadnikaNormeRada ili -1 u slucaju greske
--public BigDecimal dohvatiJedinicnuPlatuRadnikaNormeUgradnogDela(int idNR)

Declare @idNUD int
Set @idNUD = 1
Select JedinicnaPlata From [dbo].[NormaUgradnihDelova] Where idNUD = @idNUD   

--@return idPotrebanMaterijal ili -1 u slucaju greske
--public int unesiPotrebanMaterijalPoBrojuJedinica(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela, int brojJedinica);
 
Declare @idRobe int,
		@idNUD int,
		@BrojJedinica int

Set @idRobe = 1
Set	@idNUD = 1
Set	@BrojJedinica = 50

Insert Into dbo.PotrebanMaterijal (idRobe, idNUD, BrojJedinica) 
Values (@idRobe, @idNUD, @BrojJedinica)

Select * From dbo.PotrebanMaterijal  
  
--@return idPotrebanMaterijal ili -1 u slucaju greske
--public int unesiPotrebanMaterijalPoKolicini(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela, BigDecimal kolicina);
 
Declare @idRobe int,
		@idNUD int,
		@Kolicina int

Set @idRobe = 1
Set	@idNUD = 1
Set	@Kolicina = 100

Insert Into dbo.PotrebanMaterijal (idRobe, idNUD, Kolicina) 
Values (@idRobe, @idNUD, @Kolicina)

Select * From dbo.PotrebanMaterijal 
 
--@return 0-operacija uspesna 1-operacija neuspesna
--public int obrisiPotrebanMaterijal(int idRobaKojaJePotrosniMaterijal, int idNormaUgradnogDela);
    
Declare @idRobe int,
		@idNUD int

Set @idRobe = 1
Set @idNUD = 1

Delete From [dbo].[PotrebanMaterijal] Where idRobe = @idRobe And idNUD = @idNUD  

--@return idPosao ili -1 u slucaju greske
--public int unesiPosao(int idNormaUgradnogDela, int idSprat, Date datumPocetka)


    /**
     * 
     * @param idPosao
     * @return 0-operacija uspesna 1-operacija neuspesna
     */
    public abstract int obrisiPosao(int idPosao);
    /**
     * 
     * @param idPosao
     * @param datumPocetka
     * @param datumKraja
     * @return 0-operacija uspesna 1-operacija neuspesna
     */
    public abstract int izmeniDatumPocetkaZaPosao(int idPosao, Date datumPocetka);
    /**
     * Postavlja status posla na zavrsen i postavlja datum kraja posla.
     * @param idPosao
     * @param status
     * @return 0-operacija uspesna 1-operacija neuspesna
     */
    public abstract int zavrsiPosao(int idPosao, Date datumKraja);
    
    /**
     * 
     * @param idZaposleni
     * @param idPosao
     * @return idZaposleniNaPoslu ili -1 u slucaju greske
     */
    public abstract int zaposleniRadiNaPoslu(int idZaposleni, int idPosao, Date datumPocetka);
    /**
     * Ne brise informaciju o tome na kojem poslu je radio zaposleni, vec samo postavlja datum kraja.
     * @param idZaposleniNaPoslu
     * @return 0-operacija uspesna 1-operacija neuspesna
     */
    public abstract int zaposleniJeZavrsioSaRadomNaPoslu(int idZaposleniNaPoslu, Date datumKraja); 
    /**
     * 
     * @param idZaposleniNaPoslu
     * @param datumPocetka
     * @return 0-operacija uspesna 1-operacija neuspesna
     */
    public abstract int izmeniDatumPocetkaRadaZaposlenogNaPoslu(int idZaposleniNaPoslu, Date datumPocetkaNovo);
    /**
     * 
     * @param idZaposleniNaPoslu
     * @param datumKrajaNovo
     * @return 0-operacija uspesna 1-operacija neuspesna
     */
    public abstract int izmeniDatumKrajaRadaZaposlenogNaPoslu(int idZaposleniNaPoslu, Date datumKrajaNovo);
    
    /**
     * 
     * @param idZaposleniNaPoslu
     * @param ocena
     * @return idZaposleniNaPoslu ili -1 u slucaju greske
     */
    public abstract int zaposleniDobijaOcenu(int idZaposleniNaPoslu, int ocena); 
    /**
     * 
     * @param idNormaUgradnogDela
     * @return 0-operacija uspesna 1-operacija neuspesna
     */
    public abstract int obrisiOcenuZaposlenom(int idZaposleniNaPoslu);
    /**
     * Menja samo ocenu.
     * @param idZaposleniNaPoslu
     * @param ocena
     * @return 0-operacija uspesna 1-operacija neuspesna
     */
    public abstract int izmeniOcenuZaZaposlenogNaPoslu(int idZaposleniNaPoslu, int ocenaNovo);