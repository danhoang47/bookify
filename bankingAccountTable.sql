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
