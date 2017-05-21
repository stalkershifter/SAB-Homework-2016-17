Create Trigger azuriranjeBrojaObjekata
On dbo.Objekat
For update, insert, delete
As
Begin
	
	Declare @cursor Cursor

	Declare @idGradilista int,
			@operation int

	Declare @rowInserted int,
			@rowDeleted int

	Select @rowInserted = Count(*) From Inserted
	Select @rowDeleted = Count(*) From Deleted

	 --If(@rowInserted <> 0) 
		--Set @cursor = Cursor For
		--Select idGradilista From inserted
	 --IF(@rowDeleted <> 0) 
		--Set @cursor = Cursor For
		--Select idGradilista From deleted
		
		Set @cursor = Cursor For
		(Select 0, idGradilista From inserted
		Union All
		Select 1, idGradilista From deleted)

	Open @cursor

	Fetch Next From @cursor Into @operation, @idGradilista
	
	while @@Fetch_Status = 0
	Begin
		
		Update dbo.Gradiliste
		Set BrojObjekata = BrojObjekata + Case When @operation = 0 Then 1 Else -1 End
		Where idGradilista = @idGradilista

		Fetch Next From @cursor Into @operation, @idGradilista
	End 

	close @cursor
	deallocate @cursor
End
Go

Create Trigger azuriranjeBrojaSpratova
On dbo.Sprat
For update, insert, delete
As
Begin
	
	Declare @cursor Cursor

	Declare @idObjekta int,
			@operation int
		
		Set @cursor = Cursor For
		(Select 0, idObjekta From inserted
		Union All
		Select 1, idObjekta From deleted)

	Open @cursor

	Fetch Next From @cursor Into @operation, @idObjekta
	
	while @@Fetch_Status = 0
	Begin
		
		Update dbo.Objekat
		Set BrojSpratova = BrojSpratova + Case When @operation = 0 Then 1 Else -1 End
		Where idObjekta = @idObjekta

		Fetch Next From @cursor Into @operation, @idObjekta
	End 

	close @cursor
	deallocate @cursor
End
Go

Create Trigger proveraBrojaSprata
On dbo.Sprat
for insert, delete
As
Begin
	
	Declare @cursor Cursor

	Declare @idObjekta int,
			@BrojSprata int,
			@inc int,
			@TrenutnoSpratova int
		
		Set @cursor = Cursor For
		(Select idObjekta, BrojSprata, 1 From inserted
		Union All
		Select idObjekta, BrojSprata, 0 From deleted)

	Open @cursor

	Fetch Next From @cursor 
	Into @idObjekta, @BrojSprata, @inc
	
	while @@Fetch_Status = 0
	Begin
		
		Select @TrenutnoSpratova = Count(*) - @inc From dbo.Sprat Where idObjekta = @idObjekta
		
		If(@TrenutnoSpratova <> @BrojSprata)
			Begin
				If(@inc = 1)
					Print('Ne moze se izgraditi ' + Convert(varchar(10), @BrojSprata) + '. sprat!')
				else
					Print('Ne moze se srusiti ' + Convert(varchar(10), @BrojSprata) + '. sprat!')
				Rollback Transaction
			End

		Fetch Next From @cursor 
		Into @idObjekta, @BrojSprata, @inc
	End 

	close @cursor
	deallocate @cursor
End
Go

Create Trigger SefVecRadiUMagacina
On dbo.Magacin
for insert, update
As
Begin
	Declare @cursor Cursor

	Declare @idSefa int
		
		Set @cursor = Cursor For
		Select idSefa From inserted

	Open @cursor

	Fetch Next From @cursor Into @idSefa
	
	While @@Fetch_Status = 0
	Begin
		
		If((Select Count (*) From dbo.RadiUMagacinu Where idZaposlenog = @idSefa) = 1)
		Begin
			Print('Sef sa id-em ' + Convert(varchar(10), @idSefa) + ' je vec zaposlen u drugom magacinu!')
			Rollback Transaction
			Break
		End

		Fetch Next From @cursor Into @idSefa
	End
End
Go

Create Trigger ZaposleniJeSefUMagacinu
On [dbo].RadiUMagacinu
for Insert, Update
As
Begin
	Declare @cursor Cursor

	Declare @idZaposlenog int
		
		Set @cursor = Cursor For
		Select idZaposlenog From inserted

	Open @cursor

	Fetch Next From @cursor Into @idZaposlenog
	
	While @@Fetch_Status = 0
	Begin
		
		If((Select Count (*) From dbo.Magacin Where idSefa = @idZaposlenog) = 1)
		Begin
			Print('Zaposleni sa id-em ' + Convert(varchar(10), @idZaposlenog) + ' je sef u magacinu!')
			Rollback Transaction
			Break
		End

		Fetch Next From @cursor Into @idZaposlenog
	End
End
Go

Create Trigger mozeZaduziziOpremu
On [dbo].[ZaduzenaOprema]
For Insert
As
Begin
	
	Declare @HTZ int,
			@alat int

	Select @HTZ = idTipRobe From TipRobe Where Naziv Like 'HTZ'
	Select @alat = idTipRobe From TipRobe Where Naziv Like 'alat'

	Declare @cursor Cursor

	Declare @idZaduzeneOpreme int,
			@idMagacina int,
			@idOpreme int
		
		Set @cursor = Cursor For
		Select idZaduzeneOpreme, idMagacina, idOpreme From inserted

	Open @cursor

	Fetch Next From @cursor Into @idZaduzeneOpreme, @idMagacina, @idOpreme
	
	While @@Fetch_Status = 0
	Begin
		
		If((Select Count(*) From ZaduzenaOprema zo
		Join Roba r On r.idRobe = zo.idOpreme
		Where idZaduzeneOpreme = @idZaduzeneOpreme And r.idTipRobe In (@HTZ, @alat)) = 0)
		Begin
			Rollback Transaction
			Break
		End
		Else
		Begin
			Exec uzimanjeRobePoJedinicama @idOpreme, @idMagacina, 1
		End

		Fetch Next From @cursor Into @idZaduzeneOpreme, @idMagacina, @idOpreme
	End
End
Go

Create Trigger razduzujeOpremu
On [dbo].[ZaduzenaOprema]
For Update
As
Begin
	
	Declare @cursor Cursor

	Declare @datumRazduzenja datetime,
			@idMagacina int,
			@idOpreme int
		
		Set @cursor = Cursor For
		Select datumRazduzenja, idMagacina, idOpreme From deleted

	Open @cursor

	Fetch Next From @cursor Into @datumRazduzenja, @idMagacina, @idOpreme
	Print(@datumRazduzenja)
	While @@Fetch_Status = 0
	Begin
		If(@datumRazduzenja IS NOT NULL)
		Begin
			Rollback Transaction
			Break
		End
		Else
		Begin
			Exec unosRobePoJedinicama @idOpreme, @idMagacina, 1
		End

		Fetch Next From @cursor Into @datumRazduzenja, @idMagacina, @idOpreme
	End
End
Go