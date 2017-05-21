-- ===================================================================
-- ISPLATA PLATE PO MAGACINIMA
-- Description:
--		@idMagacina za koji magacin treba da se isplati plata svim
--		zaposlenim u datom magacinu, ako je NULL onda se isplacuje
--		plata svim zaposlenima u svim magacinima
-- ===================================================================
Create Procedure isplataPlatePoMagacinima
@idMagacina As int
As

Create Table [dbo].[#PlatePoMagacinima]
(	
	idZaposlenog int,
	Plata int
)

Insert Into [dbo].[#PlatePoMagacinima] 
	Select z.idZaposlenog, m.Plata From [dbo].[Magacin] m
	Join [dbo].[Zaposleni] z On z.idZaposlenog = m.idSefa 
	Where m.idMagacina = @idMagacina Or @idMagacina Is NULL
	Union All
	Select z.idZaposlenog, m.Plata From [dbo].[Zaposleni] z
	Join [dbo].[RadiUMagacinu] rum On rum.idZaposlenog = z.idZaposlenog
	Join [dbo].[Magacin] m On m.idMagacina = rum.idMagacina
	Where m.idMagacina = @idMagacina Or @idMagacina Is NULL


Update [dbo].[Zaposleni]
Set UkupnaZarada = UkupnaZarada + ppm.Plata
From [dbo].[Zaposleni] z1
Join [dbo].[#PlatePoMagacinima] ppm On z1.idZaposlenog = ppm.idZaposlenog

Drop Table [dbo].[#PlatePoMagacinima]
Go
-- ===================================================================
-- UNOS ROBE U MAGACINE PO JEDINICAMA
-- Description:
--		U @idMagacina se unosi robe @idRobe.
--		Ako ne postoji do tada uneta roba ona se ubacuje
--		a ako postoji samo se doda @BrojJedinica na trenutno 
--		stanje robe @idRobe u magacinu @idMagacina
-- ===================================================================

Create Procedure unosRobePoJedinicama
	@idRobe As int,
	@idMagacina As int,
	@BrojJedinica As int
AS
	If((Select Count(*) From [dbo].[RobaPoJedinici] 
		Where idMagacina = @idMagacina And idRobe = @idRobe) = 0)
	Begin
		Insert Into [dbo].[RobaPoJedinici] (idRobe, idMagacina, BrojJedinica)
		Values (@idRobe, @idMagacina, @BrojJedinica)
	End
	Else
	Begin
		Update [dbo].[RobaPoJedinici]
		Set BrojJedinica = BrojJedinica + @BrojJedinica
		Where idMagacina = @idMagacina And idRobe = @idRobe
	End

	Declare @rtn int 

	Select @rtn = idRobeUMagacinu From [dbo].[RobaPoJedinici] Where idMagacina = @idMagacina And idRobe = @idRobe
	
	return @rtn
Go
-- ===================================================================
-- UNOS ROBE U MAGACINE PO KOLICINI
-- Description:
--		U @idMagacina se unosi robe @idRobe.
--		Ako ne postoji do tada uneta roba ona se ubacuje
--		a ako postoji samo se doda @Kolicina na trenutno 
--		stanje robe @idRobe u magacinu @idMagacina
-- ===================================================================

Create Procedure unosRobePoKolicini
	@idRobe As int,
	@idMagacina As int,
	@Kolicina As decimal(10, 3)
AS
	If((Select Count(*) From [dbo].[RobaPoKolicini] 
		Where idMagacina = @idMagacina And idRobe = @idRobe) = 0)
	Begin
		Insert Into [dbo].[RobaPoKolicini] (idRobe, idMagacina, Kolicina)
		Values (@idRobe, @idMagacina, @Kolicina)
	End
	Else
	Begin
		Update [dbo].[RobaPoKolicini]
		Set Kolicina = Kolicina + @Kolicina
		Where idMagacina = @idMagacina And idRobe = @idRobe
	End

	Declare @rtn int 

	Select @rtn = idRobeUMagacinu From [dbo].[RobaPoKolicini] Where idMagacina = @idMagacina And idRobe = @idRobe
	
	return @rtn
Go
-- ===================================================================
-- UZIMANJE ROBE IZ MAGACINA PO KOLICINI
-- Description:
--		Iz @idMagacina se uzima roba @idRobe.
--		Ako postoji roba u dovoljnoj kolicini @Kolicina 
--		ona se uzima, u suprotnom se uzima onoliko robe 
--		koliko je ostalo u magacinu
-- ===================================================================

Create Procedure uzimanjeRobePoKolicini
	@idRobe As int,
	@idMagacina As int,
	@Kolicina As decimal(10, 3)
AS
	Declare @rtn decimal(10, 3) 

	If((Select Count(*) From [dbo].[RobaPoKolicini] 
		Where idMagacina = @idMagacina And idRobe = @idRobe) <> 0)
	Begin
		Select @rtn = Case When Kolicina >= @Kolicina Then @Kolicina Else Kolicina End
		From [dbo].[RobaPoKolicini] Where idMagacina = @idMagacina And idRobe = @idRobe

		Update [dbo].[RobaPoKolicini]
		Set Kolicina = Kolicina - @rtn
		Where idMagacina = @idMagacina And idRobe = @idRobe
	End
	
	return @rtn
Go
-- ===================================================================
-- UZIMANJE ROBE IZ MAGACINA PO JEDINICI
-- Description:
--		Iz @idMagacina se uzima roba @idRobe.
--		Ako postoji roba u jedinicama @BrojJedinica
--		ona se uzima, u suprotnom se uzima onoliko robe 
--		koliko je ostalo u magacinu
-- ===================================================================

Create Procedure uzimanjeRobePoJedinicama
	@idRobe As int,
	@idMagacina As int,
	@BrojJedinica As int
AS
	Declare @rtn int

	If((Select Count(*) From [dbo].[RobaPoJedinici] 
		Where idMagacina = @idMagacina And idRobe = @idRobe) <> 0)
	Begin
		Select @rtn = Case When BrojJedinica >= @BrojJedinica Then @BrojJedinica Else BrojJedinica End
		From [dbo].[RobaPoJedinici] Where idMagacina = @idMagacina And idRobe = @idRobe

		Update [dbo].[RobaPoJedinici]
		Set BrojJedinica = BrojJedinica - @rtn
		Where idMagacina = @idMagacina And idRobe = @idRobe
	End
	
	return @rtn
Go