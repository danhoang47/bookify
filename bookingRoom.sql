use bookify

select * from Booking

create or alter procedure proc_bookingRoom
@bookingId varchar(50),
@userId varchar(50),
@checkin date,
@checkout date,
@adult int,
@child int,
@infants int,
@pets int,
@roomId varchar(50)
as
begin
	-- Booking status
	-- 0: pending
	-- 1: accepted
	-- 2: rejected

	insert into Booking values
		(
			@bookingId, @userId, @checkin, @checkout, 
			@adult, @child, @infants, @pets, 
			@roomId, 0, getDate() 
		)
end

-- Transaction histories
create table Transact (
	id int primary key identity,
	user_id varchar(50),
	createdAt datetime,
	amount int,
	type int,
	foreign key(user_id) references userDetail(user_id)
)

select * from Booking

-- trigger to insert into Transact table
-- if Booking was rejected -> Transact.type = 2 -> Money back to User Banking Account 
-- if Booking was accepted -> Transact.type = 1 -> Money deposits to HotelOwner BankingAccount 
-- if Booking was pending -> Transact.type = 0 -> Money transfer from Banking Account, But not deposit to HotelOwner banking Account

create or alter trigger tggr_afterBookingCreatedOnTransact
on Booking
after insert, update
as
begin

	declare @type int = (select status from inserted)
	declare @money int = (
							select datediff(day, check_in, check_out) * RoomType.price as amount
							from 
							inserted join Room on inserted.room_id = Room.room_id
							join RoomType on Room.room_type_id = RoomType.id
						)
	declare @userId varchar(50) = (select user_id from inserted)
	declare @HotelOwnerId varchar(50) = (select Hotel.user_id from 
											inserted join Room on inserted.room_id = Room.room_id 
											join Hotel on Room.hotel_id = Hotel.hotel_id
											)

	if (@type = 0)
		begin
			insert into Transact values(@userId, getDate(), @money, @type)
		end
	else if @type = 1
		begin
			insert into Transact values(@HotelOwnerId, getDate(), @money, @type)
		end
	else 
		begin
			insert into Transact values(@userId, getDate(), @money, @type)
		end
end

-- Trigger to deposit/withdraw money
create or alter trigger tggr_afterTransactOnBankingAccount
on Transact
after insert, update
as
begin
	declare @type int = (select type from inserted)
	declare @money int = (select amount from inserted)
	declare @userId varchar(50) = (select user_id from inserted)
	declare @bankNumber varchar(100) = (select banking_account_id from userDetail where user_id = @userId)

	if (@type = 1) or (@type = 2)
		begin
			update BankingAccount 
			set amount = amount + @money
			where BankingAccount.id = @bankNumber
		end
	else if (@type = 0) 
		begin
			update BankingAccount 
			set amount = amount - @money
			where BankingAccount.id = @bankNumber
		end
end