use bookify 

select * from Hotel where hotel_id = 'fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9'
select * from Image where hotel_id = 'fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9'
select * from RoomType

alter table Hotel
add checkout varchar(5)

alter table Room
ADD FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id);

alter table Room
add foreign key (room_type_id) references RoomType(id)

alter table RoomType
add hotel_id varchar(50)

select * from Room

insert into Room values(
	'032266c1-fa7b-42cb-aaa1-d1ac4f18ee0b', 
	'fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9',
	'032266c1-fa7b-42cb-aaa1-d1ac4f18ee0a'
)

insert into RoomType values(
	'032266c1-fa7b-42cb-aaa1-d1ac4f18ee0a',
	100,
	'Normal Bedroom',
	2,
	'Normal Bathroom',
	2,
	1,
	null,
	11,
	10,
	'fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9'
)


-- proc get amenities
create or alter procedure proc_getHotelAmenities
@hotelId varchar(50)
as
begin
	select * 
	from 
		HotelAmenities join Amenity 
		on HotelAmenities.amenity_id = Amenity.amenity_id
		join Amenity_type 
		on Amenity.type_id = Amenity_type.amenity_type_id
	where HotelAmenities.hotel_id = @hotelId
end

-- select default Amenities
create or alter procedure proc_getDefaultAmenities
as
begin
	select * 
	from Amenity join Amenity_type
	on Amenity.type_id = Amenity_type.amenity_type_id
end

-- get room type
create or alter procedure proc_getRoomType
@hotelId varchar(50)
as
begin
	select 
		RoomType.id, RoomType.price, RoomType.bed_type, 
		RoomType.bed_number, RoomType.bathroom_type, 
		RoomType.bathroom_number, RoomType.is_private_bathroom, 
		RoomType.is_private_bathroom, RoomType.number_of_guests,
		RoomType.number_of_room, Room.hotel_id, count(Room.room_id) as rooms 
	from RoomType join Room
	on RoomType.id = Room.room_type_id
	group by RoomType.id, RoomType.price, RoomType.bed_type, 
		RoomType.bed_number, RoomType.bathroom_type, 
		RoomType.bathroom_number, RoomType.is_private_bathroom, 
		RoomType.is_private_bathroom, RoomType.number_of_guests,
		RoomType.number_of_room, Room.hotel_id
	having Room.hotel_id = @hotelId
end

proc_getRoomType @hotelId = 'fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a'

-- proc update Hotel
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

select * from Hotel where hotel_id = 'fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9'

-- proc update RoomType
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

select * from Image
alter table Image
alter column image varchar(300)
proc_getHotelAmenities @hotelId = 'fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9'

--Advance filter
create view AdvancedFilter as
select ht.hotel_id, ht.hotel_name, ht.hoteltype_id, ht.background_image, ht.country, ht.city, ht.district, ht.address, AVG(rt.price) as average_price,
(select isnull((select AVG(rv.communication_point + rv.accuracy_point + rv.location_point + rv.value_point)/4 
from review as rv where rv.hotel_id=ht.hotel_id), 0)) as rating,
AVG(rt.number_of_room) as number_of_room,
AVG(rt.bed_number) as bed_number,
AVG(rt.bathroom_number) as bath_number
from Hotel as ht, Room as rm, RoomType as rt
where ht.hotel_id=rm.hotel_id and rm.room_type_id=rt.id 
group by ht.hotel_id, ht.hotel_name, ht.city, ht.district, ht.address, ht.country, ht.hoteltype_id, ht.background_image

-- basicHotelInfor

create view getAllHotelBasicInfo as
select ht.hotel_id, ht.hotel_name, ht.hoteltype_id, ht.background_image, ht.country, ht.city, ht.district, ht.address, AVG(rt.price) as average_price,
(select isnull((select AVG(rv.communication_point + rv.accuracy_point + rv.location_point + rv.value_point)/4 
from review as rv where rv.hotel_id=ht.hotel_id), 0)) as rating
from Hotel as ht, Room as rm, RoomType as rt
where ht.hotel_id=rm.hotel_id and rm.room_type_id=rt.id 
group by ht.hotel_id, ht.hotel_name, ht.city, ht.district, ht.address, ht.country, ht.hoteltype_id, ht.background_image