-- apartment

INSERT INTO rentye.apartment (id, acceptance_to_use_date, acquisition_date, city, country, date_sold, description,
                              flat_number, house_number, land_mortgage_register_number, notarial_act_number,
                              active, street, usable_area, zip, depreciation_id)
VALUES (1, null, null, null, null, null, 'Appt 1 desc', null, null, null, null, true, null, null, null, null);

INSERT INTO rentye.apartment (id, acceptance_to_use_date, acquisition_date, city, country, date_sold, description,
                              flat_number, house_number, land_mortgage_register_number, notarial_act_number,
                              active, street, usable_area, zip, depreciation_id)
VALUES (2, null, null, null, null, null, 'Appt 2 desc', null, null, null, null, true, null, null, null, null);

-- contractor_type

INSERT INTO rentye.contractor_type (id, description)
VALUES (1, 'Administracja');

INSERT INTO rentye.contractor_type (id, description)
VALUES (2, 'Energia');

INSERT INTO rentye.contractor_type (id, description)
VALUES (3, 'Gaz');

INSERT INTO rentye.contractor_type (id, description)
VALUES (4, 'Telekomunikacja');

INSERT INTO rentye.contractor_type (id, description)
VALUES (5, 'Ubezpieczenia');

-- transaction_party

INSERT INTO rentye.transaction_party (id, city, country, email, first_name, flat_number, description, house_number,
                                      phone_number, second_name, street, surname, zip)
VALUES (1, null, null, null, null, null, 'Lokal Serwis', null, null, null, null, null, null);

INSERT INTO rentye.transaction_party (id, city, country, email, first_name, flat_number, description, house_number,
                                      phone_number, second_name, street, surname, zip)
VALUES (2, null, null, null, null, null, 'PGNiG', null, null, null, null, null, null);

INSERT INTO rentye.transaction_party (id, city, country, email, first_name, flat_number, description, house_number,
                                      phone_number, second_name, street, surname, zip)
VALUES (3, null, null, null, null, null, 'Innogy', null, null, null, null, null, null);

INSERT INTO rentye.transaction_party (id, city, country, email, first_name, flat_number, description, house_number,
                                      phone_number, second_name, street, surname, zip)
VALUES (4, null, null, null, null, null, 'Orange', null, null, null, null, null, null);

INSERT INTO rentye.transaction_party (id, city, country, email, first_name, flat_number, description, house_number,
                                      phone_number, second_name, street, surname, zip)
VALUES (5, null, null, null, null, null, 'UPC', null, null, null, null, null, null);

INSERT INTO rentye.transaction_party (id, city, country, email, first_name, flat_number, description, house_number,
                                      phone_number, second_name, street, surname, zip)
VALUES (6, null, null, null, null, null, 'Allianz', null, null, null, null, null, null);

-- contractor

INSERT INTO rentye.contractor (id, contact_person, contractor_type_id, transaction_party_id)
VALUES (1, null, 1, 1);

INSERT INTO rentye.contractor (id, contact_person, contractor_type_id, transaction_party_id)
VALUES (2, null, 3, 2);

INSERT INTO rentye.contractor (id, contact_person, contractor_type_id, transaction_party_id)
VALUES (3, null, 2, 3);

INSERT INTO rentye.contractor (id, contact_person, contractor_type_id, transaction_party_id)
VALUES (4, null, 4, 4);

INSERT INTO rentye.contractor (id, contact_person, contractor_type_id, transaction_party_id)
VALUES (5, null, 4, 5);

INSERT INTO rentye.contractor (id, contact_person, contractor_type_id, transaction_party_id)
VALUES (6, null, 5, 6);