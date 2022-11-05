use bookify

create view getHotel as SELECT 
	hotel_id, user_id, hoteltype_id, hotel_name, 
	background_image, is_verified, description, 
	country, district, city, address, isAllowPet, 
	isHasCamera, checkin, checkout, closing, opening,
        (SELECT ISNULL
            ((SELECT AVG(communication_point + accuracy_point + location_point + value_point) / 4 AS Expr1
                    FROM      dbo.Review AS rv
					WHERE   (hotel_id = ht.hotel_id)), 0) AS Expr1) AS rating
FROM Hotel AS ht 

select * from getHotel