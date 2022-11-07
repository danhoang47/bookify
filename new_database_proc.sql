use bookify

create or alter procedure proc_updateRoomType
@id varchar(50),
@price int,
@numberOfBed int,
@numberOfBathroom int,
@isPrivateBathroom bit,
@numberOfGuests int,
@numberOfRoom int
as
begin
	update RoomType
	set 
		price = @price,
		bed_number = @numberOfBed,
		bathroom_number = @numberOfBathroom,
		is_private_bathroom = @isPrivateBathroom,
		number_of_guests = @numberOfGuests,
		number_of_room = @numberOfRoom
	where id = @id
end

select * from Bookmark where user_id = '9ad6286d-d08d-4397-8704-b8a1aff07309'
select getdate()

select * from Hotel

insert into Bookmark 
	values(
		'73c8fb67-a9b7-4363-8a8f-d36701d1d9af', 
		'9ad6286d-d08d-4397-8704-b8a1aff07309', 
		'0e496299-ba26-4270-8ba9-f642c6843a62', 
		getdate()
	)

create or alter proc proc_getAllBookmarkedHotel
@user_id varchar(50)
as
begin
	select distinct *
	from 
		Hotel join Bookmark 
			on Hotel.hotel_id = Bookmark.hotel_id
	where Bookmark.user_id = @user_id
end

proc_getAllBookmarkedHotel @user_id = '9ad6286d-d08d-4397-8704-b8a1aff07309'

create or alter procedure proc_updateHotel
@hotelId varchar(50),
@hotelTypeId varchar(50),
@hotelName nvarchar(50),
@backgroundImage varchar(150),
@description nvarchar(300),
@country nvarchar(100),
@district nvarchar(100),
@city nvarchar(100),
@address nvarchar(100),
@isAllowPet bit,
@isHasCamera bit,
@closing varchar(5),
@opening varchar(5),
@checkin varchar(5),
@checkout varchar(5)
as
begin
	update Hotel
	set 
		hoteltype_id = @hotelTypeId,
		hotel_name = @hotelName,
		background_image = @backgroundImage,
		description = @description,
		country = @country,
		district = @district,
		city = @city,
		address = @address,
		isAllowPet = @isAllowPet,
		isHasCamera = @isHasCamera,
		closing = @closing,
		opening = @opening,
		checkin = @checkin,
		checkout = @checkout
	where hotel_id = @hotelId
end


create or alter procedure proc_getAllMergedDayBooking
@check_in varchar(50), @check_out varchar(50), @hotelId varchar(50)  as
begin
	select r.room_id, dr.check_in, dr.check_out from Room as r, dateRangeBookingMerged as dr
	where dr.room_id=r.room_id and
	r.room_id not in
	(select room_id from dateRangeBookingMerged where 
	((check_in>=@check_in and check_out<=@check_out) 
	or (check_in>@check_in and check_out<@check_out) 
	or (check_in<@check_in and check_out>@check_out))
	and hotel_id=@hotelId) and r.hotel_id=@hotelId
end

proc_getAllMergedDayBooking @check_in='2022-11-05', @check_out='2022-11-06', @hotelId='f98320c3-235a-4cb7-a0a8-eda132b0e545'

create or alter procedure proc_testProc 
as
begin
	select 'This is test proc'
end

SELECT @@SERVERNAME
SELECT @@servicename
