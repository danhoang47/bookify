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

proc_getHotelIdFromOwnerId @ownerId = 'ca8c99e4-a955-4439-baaf-dc02c6aacf5e'
