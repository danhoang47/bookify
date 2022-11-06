use bookify

--
create view SearchAdvance as
	select ht.hotel_id, ht.hotel_name, ht.hoteltype_id, ht.background_image, ht.country, ht.city, ht.district, ht.address, 
	AVG(rt.price) as average_price,
	(select isnull((select AVG(rv.communication_point + rv.accuracy_point + rv.location_point + rv.value_point)/4 
	from review as rv where rv.hotel_id=ht.hotel_id), 0)) as rating,
	AVG(rt.number_of_guests) as number_of_guest
	from Hotel as ht, Room as rm, RoomType as rt
	where ht.hotel_id=rm.hotel_id and rm.room_type_id=rt.id 
	group by ht.hotel_id, ht.hotel_name, ht.city, ht.district, ht.address, ht.country, ht.hoteltype_id, ht.background_image

-- 
select * from SearchAdvance where hotel_id in (
	select distinct hotel_id from room where room_id not in (
		select room_id from bookingDateRange where check_in >= '2022-11-07' and check_out<='2022-11-12')
	)

--
create or alter procedure proc_getAllHotelBasicInfor
@userId varchar(50)
as
begin
	select 
		bab.*, 
			(select count(*)
				from Bookmark as bm 
				where bm.hotel_id = bab.hotel_id and bm.user_id = @userId
			) as 'isBookmarked' 
	from getAllHotelBasicInfo as bab
end
