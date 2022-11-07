use bookify

create table BankingAccount(
	id int primary key identity,
	bank_number varchar(100),
	amount int,
)

alter table userDetail
add banking_account_id int

alter table userDetail
add foreign key (banking_account_id) references BankingAccount(id)

insert into BankingAccount(bank_number, amount) 
values
	('asdc-dawd-2312d-dasd', 10000),
	('asdc-dawd-2312d-dase', 10000)

select * from BankingAccount
select * from userDetail

update userDetail
set banking_account_id = 1 where user_id = '9ad6286d-d08d-4397-8704-b8a1aff07309'


create or alter procedure proc_getAmount
@userId varchar(50)
as
begin
	select amount 
	from 
		userDetail join BankingAccount
		on userDetail.banking_account_id = BankingAccount.id
	where userDetail.user_id = @userId
end

proc_getAllMergedDayBooking @check_in='2022-11-24', @check_out='2022-11-29', @hotelId='f98320c3-235a-4cb7-a0a8-eda132b0e545'

select * from Booking 
join Room on Booking.room_id = Room.room_id
where Room.hotel_id = 'f98320c3-235a-4cb7-a0a8-eda132b0e545'
order by Booking.check_in

