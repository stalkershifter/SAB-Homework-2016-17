-- ==============================================================
-- GRADILISTE
-- ==============================================================
Insert Into dbo.Gradiliste (Naziv, DatumOsnivanja)
Select 'Gradiliste 1', '20170101'
Union All
Select 'Gradiliste 2', '20170202'
Union All
Select 'Gradiliste 3', '20170303'
Union All
Select 'Gradiliste 4', '20170404'
Go

Select * From dbo.Gradiliste
Go

-- ==============================================================
-- OBJEKAT
-- ==============================================================
Insert Into dbo.Objekat (idGradilista, Naziv)
Select 3, 'Objekat 1'
Union All
Select 2, 'Objekat 2'
Union All
Select 2, 'Objekat 3'
Union All
Select 1, 'Objekat 4'
Go

Select * From dbo.Objekat
Go

-- ==============================================================
-- SPRAT
-- ==============================================================
Insert Into dbo.Sprat (idObjekta, BrojSprata)
Select 1, 0 Union All Select 1, 1 Union All Select 1, 2 Union All Select 1, 3 Union All
Select 2, 0 Union All Select 2, 1 Union All
Select 3, 0 Union All Select 3, 1 Union All Select 3, 2 Union All Select 3, 3 Union All
Select 3, 4 Union All Select 3, 5 Union All Select 3, 6
Go

--DBCC CHECKIDENT('dbo.Sprat', RESEED, 0)
--delete dbo.Sprat

Select * From dbo.Sprat
Go

-- ==============================================================
-- ZAPOSLENI
-- ==============================================================
INSERT INTO [dbo].[Zaposleni]
           ([Ime], [Prezime], [JMBG], [Pol], [ZiroRacun], [EMail], [BrojTelefona])
Select 'Ime1', 'Prezime1', '0123456789001', 'M', '000-00000-01', 'mail1@hotmail.com', '0600000001' Union All
Select 'Ime2', 'Prezime2', '0123456789002', 'Z', '000-00000-02', 'mail2@hotmail.com', '0600000002' Union All
Select 'Ime3', 'Prezime3', '0123456789003', 'M', '000-00000-03', 'mail3@hotmail.com', '0600000003' Union All
Select 'Ime4', 'Prezime4', '0123456789004', 'Z', '000-00000-04', 'mail4@hotmail.com', '0600000004' Union All
Select 'Ime5', 'Prezime5', '0123456789005', 'M', '000-00000-05', 'mail5@hotmail.com', '0600000005' Union All
Select 'Ime6', 'Prezime6', '0123456789006', 'Z', '000-00000-06', 'mail6@hotmail.com', '0600000006' Union All
Select 'Ime7', 'Prezime7', '0123456789007', 'M', '000-00000-07', 'mail7@hotmail.com', '0600000007' Union All
Select 'Ime8', 'Prezime8', '0123456789008', 'Z', '000-00000-08', 'mail8@hotmail.com', '0600000008' Union All
Select 'Ime9', 'Prezime9', '0123456789009', 'M', '000-00000-09', 'mail9@hotmail.com', '0600000009' Union All
Select 'Ime10', 'Prezime10', '0123456789010', 'Z', '000-00000-10', 'mail10@hotmail.com', '0600000010' Union All
Select 'Ime11', 'Prezime11', '0123456789011', 'M', '000-00000-11', 'mail11@hotmail.com', '0600000011' 
Go

Select * From dbo.Zaposleni
Go

-- ==============================================================
-- MAGACIN
-- ==============================================================

Insert Into dbo.Magacin (idSefa, idGradilista, Plata)
Select 1, 1, 500 Union All
Select 3, 2, 600 Union All
Select 8, 3, 700 
Go

Select * From dbo.Magacin
Go

Select z.Ime + ' ' + z.Prezime As FullName, g.Naziv From Magacin m
Join Gradiliste g On g.idGradilista = m.idGradilista
Join Zaposleni z On z.idZaposlenog = m.idSefa
Go

-- ==============================================================
-- RADI U MAGACINU
-- ==============================================================
Insert Into dbo.RadiUMagacinu (idMagacina, idZaposlenog)
Select 1, 2 Union All
Select 2, 4 Union All
Select 2, 5 Union All
Select 2, 6 Union All
Select 2, 7 Union All
Select 3, 9 Union All
Select 3, 10
Go

Select * From dbo.RadiUMagacinu
Go

Select z.Ime + ' ' + z.Prezime + ' radi u ' + g.Naziv As Description From Magacin m
Join Gradiliste g On g.idGradilista = m.idGradilista
Join RadiUMagacinu rum On rum.idMagacina = m.idMagacina
Join Zaposleni z On z.idZaposlenog = rum.idZaposlenog
Go

-- ==============================================================
-- TIP ROBE
-- ==============================================================
Insert Into dbo.TipRobe (Naziv) 
Select 'HTZ'  Union All
Select 'alat' Union All
Select 'materijal'
Go

Select * From dbo.TipRobe
Go

--DBCC CHECKIDENT('dbo.TipRobe', RESEED, 0)
--delete dbo.TipRobe

-- ==============================================================
-- TIP ROBE
-- ==============================================================
Insert Into dbo.Roba (Naziv, Kod, idTipRobe) 
Select 'Pesak', '0001', 3 Union All       
Select 'Cigla', '0002', 3 Union All       
Select 'Cement', '0003', 3 Union All      
Select 'Keramicka plocica', '0004', 3 Union All       
Select 'Crep', '0005', 3 Union All      
Select 'Armatura', '0006', 3 Union All      
Select 'Busilica', '0007', 2 Union All      
Select 'Cekic', '0008', 2 Union All      
Select 'Elektricni odvijac', '0009', 2 Union All       
Select 'Kruzna testera', '0010', 2 Union All       
Select 'Rukavice', '0011', 1 Union All       
Select 'Naocare', '0012', 1 Union All       
Select 'Cipele', '0013', 1 Union All
Select 'Stitnik za kolena', '0014', 1 Union All
Select 'Kaciga', '0015', 1 
Go

Select * From dbo.Roba
Go

-- ==============================================================
-- ROBA PO JEDINICAMA
-- ==============================================================

Exec unosRobePoJedinicama 2, 1, 1000 
Go 
Exec unosRobePoJedinicama 2, 1, 1000 
Go
Exec unosRobePoJedinicama 2, 2, 1000 
Go
Exec unosRobePoJedinicama 5, 1, 1000 
Go 
Exec unosRobePoJedinicama 5, 2, 2000 
Go
Exec unosRobePoJedinicama 5, 3, 1000 
Go
Exec unosRobePoJedinicama 8, 1, 20	 
Go 
Exec unosRobePoJedinicama 8, 3, 40	 
Go
Exec unosRobePoJedinicama 8, 1, 30	 
Go
Exec unosRobePoJedinicama 15, 1, 40	 
Go 
Exec unosRobePoJedinicama 13, 1, 100 
Go
Exec unosRobePoJedinicama 12, 1, 50	 
Go

Select * From [dbo].[RobaPoJedinici]
Go

--Create Database GradjevinskaFirma

--DBCC CHECKIDENT('dbo.[RobaPoJedinici]', RESEED, 0)
--delete dbo.[RobaPoJedinici]

-- ==============================================================
-- ROBA PO KOLICINI
-- ==============================================================
Exec unosRobePoKolicini 1, 1, 2000 
Go
Exec unosRobePoKolicini 3, 2, 1000 
Go
Exec unosRobePoKolicini 1, 1, 3000 
Go
Exec unosRobePoKolicini 1, 2, 1000 
Go

Select * From [dbo].[RobaPoKolicini]
Go

-- ==============================================================
-- ZADUZENA OPREMA
-- ==============================================================
Insert Into dbo.ZaduzenaOprema (idMagacina, idOpreme, idZaposlenog, DatumZaduzenja, Napomena) 
Select 3, 8, 10, GetDate(), 'Napomena1' Union All
Select 3, 8, 10, GetDate(), 'Napomena2' Union All
Select 1, 13, 2, GetDate(), 'Napomena3' Union All
Select 1, 13, 2, GetDate(), 'Napomena4' 
Go

Select * From [dbo].[ZaduzenaOprema]
Go
