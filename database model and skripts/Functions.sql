Create Function [dbo].[MoguceZaduziti] (@idRobe int)
	Returns int
	As
	Begin
		Declare @idAlat int,
				@idHTZ int,
				@moguce bit
		
		Select @idAlat = idTipRobe From [dbo].[TipRobe] tr Where tr.Naziv = 'alat' 
		Select @idHTZ = idTipRobe From [dbo].[TipRobe] tr Where tr.Naziv = 'HTZ' 
		
		Select @moguce = Case When Exists (Select * From Roba r Join TipRobe tr on tr.idTipRobe = r.Tip
		Where @idRobe = r.idRobe And  r.Tip in (@idAlat, @idHTZ)) Then 1 Else 0 End

		Return @moguce
		
	End
Go
