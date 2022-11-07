use bookify

-- id: 
-- userId: 
-- hotelId: 
-- sourceId: 
-- notifyType: 
-- notifyDate:
-- hotelName: 
-- actorName: 
-- isRead:

-- 
create table Notification(
	notify_id int identity primary key,
	user_id varchar(50),
	source_id varchar(50),
	notify_type int,
	notify_date datetime,
	is_read bit
)

select * from Notification

-- trigger adding notif when host recevie booking
-- type 0
create or alter trigger tggr_getNotifWhileBooking
on Booking
after insert
as
begin
	declare @source_id varchar(50) = (select booking_id from inserted)
	declare @user_id varchar(50) = (select Hotel.user_id 
									from 
										inserted join Room on inserted.room_id = Room.room_id
										join Hotel on Room.hotel_id = Hotel.hotel_id
									)

	insert into Notification 
	values(@user_id, @source_id, 0, getdate(), 0)
end

-- trigger adding notif when user get confirm booking
-- type 3, 4, 5, 6
create or alter trigger tggr_getNotifWhileConfirmBooking
on Booking
after update
as
begin
	declare @source_id varchar(50) = (select booking_id from inserted)
	declare @user_id varchar(50) = (select user_id from inserted)
	declare @owner_id varchar(50) = (select Hotel.user_id 
									from 
										inserted join Room on inserted.room_id = Room.room_id
										join Hotel on Room.hotel_id = Hotel.hotel_id
									)
	declare @status int = (select status from inserted) 

	if @status = 1
	begin
		insert into Notification 
		values(@user_id, @source_id, 3, getdate(), 0)
		update Notification
		set notify_type = 5
		where user_id = @owner_id and source_id = @source_id
	end
	else
	begin
		insert into Notification 
		values(@user_id, @source_id, 4, getdate(), 0)
		update Notification
		set notify_type = 6
		where user_id = @owner_id and source_id = @source_id
	end


end

alter table Review
add content varchar(300)

-- trigger adding notif when host get review
-- type 2
create or alter trigger tggr_getNotifWhileReview
on Review
after insert
as
begin
	declare @source_id varchar(50) = (select review_id from inserted)
	declare @owner_id varchar(50) = (select Hotel.user_id 
										from 
											inserted join Hotel on inserted.hotel_id = Hotel.hotel_id
									)

	insert into Notification 
	values(@owner_id, @source_id, 2, getdate(), 0)
end

-- procedure get all notification
-- UPDATED
create or alter proc proc_getAllNotification
@userId varchar(50),
@sourceId varchar(50) = null
as
begin
	select joinedNotifs.notify_id, joinedNotifs.user_id, joinedNotifs.source_id,
			joinedNotifs.notify_date, joinedNotifs.notify_type, joinedNotifs.is_read,
			joinedNotifs.actor_id, joinedNotifs.hotel_id, Hotel.hotel_name, 
			userDetail.username as actorName, userDetail.avatar as actorAvatar from
	(
		-- Booking
		select 
			Notification.notify_id, Notification.user_id, Notification.source_id,
			Notification.notify_date, Notification.notify_type, Notification.is_read,
			Booking.user_id as actor_id, Room.hotel_id
		from 
			Notification join Booking on Notification.source_id = Booking.booking_id
			join Room on Booking.room_id = Room.room_id
			where Notification.notify_type in (0, 3, 4, 5, 6) 
		union
		--- Review
		select 
			Notification.notify_id, Notification.user_id, Notification.source_id,
			Notification.notify_date, Notification.notify_type, Notification.is_read,
			Review.user_id as actor_id, Review.hotel_id
		from 
			Notification join Review on Notification.source_id = Review.review_id
			where Notification.notify_type = 2
		union
		--- Report
		select
			Notification.notify_id, Notification.user_id, Notification.source_id,
			Notification.notify_date, Notification.notify_type, Notification.is_read,
			Report.user_id as actor_id, Report.hotel_id
		from 
			Notification join Report on Notification.source_id = Report.report_id
			where Notification.notify_type = 7
	) as joinedNotifs join Hotel on joinedNotifs.hotel_id = Hotel.hotel_id
	join userDetail on joinedNotifs.actor_id = userDetail.user_id
	where joinedNotifs.user_id = @userId and joinedNotifs.source_id = ISNULL(@sourceId, joinedNotifs.source_id)
	order by notify_date desc
end

proc_getAllNotification @userId = '13069adc-3485-492b-8239-ba93c43d9d6e', @sourceId = 'af2a30da-6bb4-4b6d-8a4e-6324cafad135'

select * from Notification
select * from userDetail

select * from BankingAccount 

select * from Transact where user_id = 'ca8c99e4-a955-4439-baaf-dc02c6aacf5e'

