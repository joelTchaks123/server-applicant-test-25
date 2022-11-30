/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

-- //////////////  task 1

--Create 2 ONLINE driver with map car
insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (9, now(), false, 'ONLINE',
'driver09pw', 'driver09', 3);

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (10, now(), false, 'ONLINE',
'driver10pw', 'driver10', 4);

-- Create 1 Electric Cars
insert into car (CAR_ID , CAR_STATUT , CONVERTIBLE , DATE_CREATED , DELETED , ENGINE_TYPE , LICENSE_PLATE , RATING, SEAT_COUNT , MANUFACTURERDO_ID  )
values (1, 'FREE', false, now(), false,  'ELECTRIC', 'HHK7431', 4.1, 4, null);

-- Create 1 Gas Cars
insert into car (CAR_ID , CAR_STATUT , CONVERTIBLE , DATE_CREATED , DELETED , ENGINE_TYPE , LICENSE_PLATE , RATING, SEAT_COUNT , MANUFACTURERDO_ID  )
values (2, 'FREE', false, now(), false,  'GAS', 'HHK7474', 4.5, 5, null);

-- Create 2 Mapped Cars
insert into car (CAR_ID , CAR_STATUT , CONVERTIBLE , DATE_CREATED , DELETED , ENGINE_TYPE , LICENSE_PLATE , RATING, SEAT_COUNT , MANUFACTURERDO_ID  )
values (3, 'BUSY', false, now(), false,  'ELECTRIC', 'HHK7410', 4.1, 4, null);

insert into car (CAR_ID , CAR_STATUT , CONVERTIBLE , DATE_CREATED , DELETED , ENGINE_TYPE , LICENSE_PLATE , RATING, SEAT_COUNT , MANUFACTURERDO_ID  )
values (4, 'BUSY', false, now(), false,  'ELECTRIC', 'HHK7465', 4.1, 4, null);


