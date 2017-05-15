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


Create Trigger postojiSefMagacina
On dbo.Magacin
for insert, update
As
Begin
	Declare @cursor Cursor

	Declare @idSefa int
		
		Set @cursor = Cursor For
		Select @idSefa From inserted

	Open @cursor

	Fetch Next From @cursor Into @idSefa
	
	While @@Fetch_Status = 0
	Begin
		
		If((Select Count(*) From dbo.Magacin Where idSefa = @idSefa) <> 1)
		Begin
			Print('Sef sa id-em ' + Convert(varchar(10), @idSefa) + ' je vec zaposlen u drugom magacinu!')
			Rollback Transaction
		End

		Fetch Next From @cursor Into @idSefa
	End
End
Go
