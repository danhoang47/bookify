use bookify
 

create or alter proc proc_getAllBooking
@hotelId varchar(50)
as
begin
	
end

alter table Hotel
add signAt date

select * from getHotel where hotel_id='df28f4c5-8907-4bcf-a9b7-3849cb860815'

select * from userDetail
select * from Hotel

create view getHotel as
SELECT        
	hotel_id, user_id, hoteltype_id, hotel_name, background_image, is_verified, description, country, district, city, address, isAllowPet, isHasCamera, checkin, checkout, closing, opening, signAt,
(SELECT        ISNULL
((SELECT        AVG(communication_point + accuracy_point + location_point + value_point) / 4 AS Expr1
    FROM            dbo.Review AS rv
    WHERE        (hotel_id = ht.hotel_id)), 0) AS Expr1) AS rating
FROM            dbo.Hotel AS ht


-- proc get hotel id from ownerId
create or alter proc proc_getHotelIdFromOwnerId
@ownerId varchar(50)
as
begin
	select Hotel.hotel_id 
	from Hotel
	where Hotel.user_id = @ownerId
end

-- proc get all booking today
create or alter proc proc_getAllTodayPendingBooking
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		datediff(day, Booking.bookAt, getDate()) = 0
	and Room.hotel_id = @hotelId
	and Booking.status = 0
end

-- proc get all booked booking today
create or alter proc proc_getAllTodayBookedBooking
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		datediff(day, getdate(), Booking.check_out) > 0
	and Room.hotel_id = @hotelId
	and Booking.status = 1
end

-- proc get all checkout today
create or alter proc proc_getAllTodayCheckout
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		datediff(day, getdate(), Booking.check_out) = 0
	and Room.hotel_id = @hotelId
	and Booking.status = 1
end

select * from Hotel

proc_getAllTodayPendingBooking @hotelId = 'ae257b6b-43d4-4621-91f1-b331c6d4dea9'
proc_getAllTodayBookedBooking @hotelId = 'ae257b6b-43d4-4621-91f1-b331c6d4dea9'

--------------------------------------------------
-- proc get all booking
create or alter proc proc_getAllPendingBooking
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		Room.hotel_id = @hotelId
	and Booking.status = 0
end

-- proc get all checkout
create or alter proc proc_getAllCheckout
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		datediff(day, Booking.check_out, getdate()) >= 0
	and Room.hotel_id = @hotelId
	and Booking.status = 1
end

-- proc get all incoming booking
create or alter proc proc_getAllIncomingBooking
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		datediff(day, getdate(), Booking.check_in) > 0
	and Room.hotel_id = @hotelId
	and Booking.status = 1
end

