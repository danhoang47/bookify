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

proc_getAllHotelBasicInfor @userId = null