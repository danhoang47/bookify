use bookify

alter table Notification
add user_id varchar(50)

alter table Booking
add bookAt date

insert into Booking values 
	('05626770-b285-4326-80f9-8e6b5162d886', '123', '2022-11-07',	'2022-11-13',	2, 0,	0,	0,	'2ecb71cc-d224-4e15-a245-87b45b1c1628',	0,	'2022-11-05'),
	('4c7ed5f9-3f98-4392-a7d3-5651d7ab178b', '123', '2022-11-18',	'2022-11-20',	2, 0,	0,	0,	'2706c071-76ea-4645-9741-03b166bad469',	0,	'2022-11-01')


select * from (
	select datediff(day, getdate(), Booking.check_out) as diff from Booking 
	) as checkOutDateDiff
where checkOutDateDiff.diff = 1
