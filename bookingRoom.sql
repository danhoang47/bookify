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
		( @bookingId, @userId, @checkin, @checkout, @adult, @child, @infants, @pets, @roomId, 0, getDate() )
end

select * from Booking
proc_bookingRoom 
	@bookingId = '8f6c84b1-8cc9-4d71-b6a9-4fc46b2e42c9',
	@userId = '13069adc-3485-492b-8239-ba93c43d9d6e',
	@checkin = '2022-11-08', @checkout = '2022-11-10',
	@adult = 3, @child = 0, @infants = 0, @pets = 0, 
	@roomId = 'c77e2c0d-75ef-44ef-b68b-b3835ed6bab0'

-- Transaction histories
create table Transact (
	id int primary key identity,
	user_id varchar(50),
	createdAt datetime,
	amount int,
	type int,
	foreign key(user_id) references userDetail(user_id)
)

-- trigger to insert into Transact table
-- if Booking was rejected -> Transact.type = 2 -> Money back to User Banking Account 
-- if Booking was accepted -> Transact.type = 1 -> Money deposits to HotelOwner BankingAccount 
-- if Booking was pending -> Transact.type = 0 -> Money transfer from Banking Account, But not deposit to HotelOwner banking Account

create or alter trigger tggr_afterBookingCreatedOnTransact
on Booking
after insert, update
as
begin
	declare @bookingId varchar(50) = (select inserted.booking_id from inserted)
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

insert into BankingAccount
values('aafc-dawd-2312d-aaaa', 10000)

select * from BankingAccount
select * from userDetail

update userDetail
set banking_account_id = 1
where user_id = '13069adc-3485-492b-8239-ba93c43d9d6e'

select * from BankingAccount
select * from userDetail
select * from Booking

BookingDTO{
user=UserDTO{user_id=13069adc-3485-492b-8239-ba93c43d9d6e, 
roomId=c77e2c0d-75ef-44ef-b68b-b3835ed6bab0, hotelId=null, price=0, 
bookingId=8f6c84b1-8cc9-4d71-b6a9-4fc46b2e42c9, checkin=2022-11-08, 
checkout=2022-11-10, adult=3, child=0, infant=0, pet=0, status=0,

