create table if not exists crossfire.user_roles (
  role_name varchar(100) primary key
  , description text not null
  , created_by varchar(100) not null
  , created timestamp default current_timestamp
  , modified_by varchar(100) not null
  , modified timestamp default current_timestamp
);
--;;
create table if not exists crossfire.retailers (
  id serial primary key
  , business_name varchar(200) not null
  , abn char(11)
  , acn char(9)
  , created TIMESTAMP default current_timestamp
  , created_by varchar(100) not null
  , modified TIMESTAMP default current_timestamp 
  , modified_by varchar(100) not null
 );
--;;
create unique index if not exists cf_retailers_uidx
  on crossfire.retailers(business_name, abn);
--;;
create table if not exists crossfire.billing_addresses (
 id serial primary key
 , retailer_id integer references crossfire.retailers(id)
 , street1 varchar(200) not null
 , street2 varchar(200)
 , city varchar(200) not null
 , state varchar(40) not null
 , postcode varchar(10) not null
 , country varchar(100) default 'Australia'
 , valid_from date default current_date
 , valid_to date 
 , created timestamp default current_timestamp
 , created_by varchar(100) not null
 , modified timestamp default current_timestamp
 , modified_by varchar(100) not null
);
--;;
create unique index if not exists cf_billing_addresses_uidx 
  on crossfire.billing_addresses(id, retailer_id);
--;;
create table if not exists crossfire.shipping_addresses (
   id serial primary key
 , retailer_id integer references retailers(id)
 , street1 varchar(200) not null
 , street2 varchar(200)
 , city varchar(200) not null
 , state varchar(40) not null
 , postcode varchar(10) not null
 , country varchar(100) default 'Australia'
 , valid_from date default current_date
 , valid_to date
 , created timestamp default current_timestamp
 , created_by varchar(100) not null
 , modified timestamp default current_timestamp
 , modified_by varchar(100) not null
 );
--;;
create unique index if not exists cf_shipping_addresses_uidx
  on crossfire.shipping_addresses(id, retailer_id);
--;;
create table if not exists crossfire.users (
   user_role varchar(100) references crossfire.user_roles(role_name)
 , retail_id integer references crossfire.retailers(id)
 , email varchar(200) primary key
 , password varchar(100)
 , password_hint varchar(200)
 , first_name varchar(100)
 , last_name varchar(100)
 , business_phone varchar(10)
 , mobile_phone varchar(10)
 , fax varchar(10)
 , valid_from date default current_date
 , valid_to date
 , created timestamp default current_timestamp
 , created_by varchar(100) not null
 , modified timestamp default current_timestamp
 , modified_by varchar(100) not null
 );
--;; 
create unique index if not exists cf_users_uidx
  on crossfire.users(retail_id, email);
