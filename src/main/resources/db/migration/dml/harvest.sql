insert into garden (id, info, name)
values (1,'My first garden','zahrada1');

insert into harvest (id, name, garden_id) values (1 ,'Leto 2020', 1);

insert into seeder_position(id, positionx, positiony, positionz) values (1,10,10,50);


insert into seeder(id, name, position_id)values (1, 'Sazenice mrkve', 1);
insert into planted_crop_position(id, positionx, positiony, positionz) values (1,100,200,50);

insert into crop (id, ideal_soil_humidity, info, name, spacing, seeder_id) VALUES (1, 80, 'Mrkev obecná', 'Mrkev', 10, 1);
insert into planted_crop(id, planted, crop_id, position_id, harvest_id, seeder_id) values (1, true, 1,1,1,1);