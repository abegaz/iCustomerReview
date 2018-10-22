create table dbo.[account] (
	account_ID int identity(1,1) primary key not null,
	username varchar(15) NOT NULL,
	password_hash varchar(513) NOT NULL,
	password_salt varchar(513) NOT NULL,
	email varchar(30) NOT NULL,
	fname varchar(15) NOT NULL,
	lname varchar(15) NOT NULL,
	address_01 varchar(25) NULL,
	address_02 varchar(25) NULL,
	city varchar(15) NULL,
	state varchar(15) NULL,
	zip varchar(10) NULL,
	phone_number varchar(12) NULL,
	review_count int default 0 not null
	) ;

create table dbo.[company] (
	company_ID int identity(1,1) primary key not null,
	cname varchar(25) not null unique,
	caddress_01 varchar(25) not null, 
	caddress_02 varchar(25) null,
	ccity varchar(15) not null, 
	cstate varchar(15) not null,
	czip varchar(10) not null,
	cphone varchar(12) not null
	);

create table dbo.[product] (
	product_ID int identity(1,1) primary key not null,
	pname varchar(25) not null,
	pmodel varchar(15) unique not null,
	company_ID int foreign key references company(company_ID) not null,
	);

create table dbo.[purchase] (
	purchase_ID int identity(1,1) primary key not null,
	pdate date not null,
	quantity int default 1 not null,
	product_ID int foreign key references product(product_ID) not null,
	account_ID int foreign key references account(account_ID) not null
	)

create table dbo.[review] (
	review_ID int identity(1,1) primary key not null,
	stars int not null,
	rev_descr varchar(500) not null,
	account_ID int foreign key references account(account_ID) not null,
	company_ID int foreign key references company(company_ID) not null,
	product_ID int foreign key references product(product_ID) not null
	);

SELECT        
FROM product INNER JOIN
        company ON product.company_ID = company.company_ID INNER JOIN
        purchase ON product.product_ID = purchase.product_ID INNER JOIN
        account ON purchase.account_ID = account.account_ID INNER JOIN
        review ON product.product_ID = review.product_ID AND company.company_ID = review.company_ID AND account.account_ID = review.account_ID