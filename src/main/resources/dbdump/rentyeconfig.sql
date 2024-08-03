-- contractor_type

INSERT INTO rentye.contractor_type (id, description) VALUES (1, 'Bank');
INSERT INTO rentye.contractor_type (id, description) VALUES (2, 'Real estate agency');
INSERT INTO rentye.contractor_type (id, description) VALUES (3, 'Notary');
INSERT INTO rentye.contractor_type (id, description) VALUES (4, 'House Community');
INSERT INTO rentye.contractor_type (id, description) VALUES (5, 'Insurer');
INSERT INTO rentye.contractor_type (id, description) VALUES (6, 'Energy supplier');
INSERT INTO rentye.contractor_type (id, description) VALUES (7, 'Gasworks');
INSERT INTO rentye.contractor_type (id, description) VALUES (8, 'Telecommunications company');
INSERT INTO rentye.contractor_type (id, description) VALUES (9, 'City Council');
INSERT INTO rentye.contractor_type (id, description) VALUES (10, 'Tax Office');
INSERT INTO rentye.contractor_type (id, description) VALUES (11, 'Renovation and construction company');
INSERT INTO rentye.contractor_type (id, description) VALUES (12, 'Handyman');

-- transaction_type

INSERT INTO rentye.transaction_type (id, default_transaction_sort, description) VALUES (1, 2, 'Purchase Price');
INSERT INTO rentye.transaction_type (id, default_transaction_sort, description) VALUES (2, 2, 'Acquisition Cost');
INSERT INTO rentye.transaction_type (id, default_transaction_sort, description) VALUES (3, 3, 'Loan');
INSERT INTO rentye.transaction_type (id, default_transaction_sort, description) VALUES (4, 3, 'Deposit');
INSERT INTO rentye.transaction_type (id, default_transaction_sort, description) VALUES (5, 1, 'Running Cost');
INSERT INTO rentye.transaction_type (id, default_transaction_sort, description) VALUES (6, 1, 'Depreciation');
INSERT INTO rentye.transaction_type (id, default_transaction_sort, description) VALUES (7, 3, 'Tax');
INSERT INTO rentye.transaction_type (id, default_transaction_sort, description) VALUES (8, 0, 'Revenue');
INSERT INTO rentye.transaction_type (id, default_transaction_sort, description) VALUES (9, 3, 'Own Rental');

-- transaction subtype

INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (1, 'PPR', 'Purchase Price', 0, 1);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (2, 'CRT', 'Credit-related Costs', null, 2);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (3, 'AGC', 'Agency commission', null, 2);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (4, 'NOF', 'Notary fees', null, 2);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (5, 'LCI', 'Loan / capital installment', null, 3);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (6, 'TED', 'Tenant\'s deposit', null, 4);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (7, 'ION', 'Interest on loans', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (8, 'DEP', 'Depreciation', null, 6);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (9, 'RFU', 'Renovation fund', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (10, 'OHC', 'Other fees payable to housing community', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (11, 'INS', 'Insurance', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (12, 'ENE', 'Energy', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (13, 'GAS', 'Gas', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (14, 'ITV', 'Internet/TV', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (15, 'WAS', 'Waste collection', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (16, 'PTX', 'Property tax', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (17, 'PLE', 'Perpetual lease', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (18, 'REN', 'Renovations / Repairs', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (19, 'REF', 'Refund of overpayment for utilities', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (20, 'OCO', 'Other costs', null, 5);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (21, 'RTX', 'Rental tax', null, 7);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (22, 'RTF', 'Rent from the tenant', null, 8);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (23, 'OUS', 'Own Use', null, 9);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (24, 'SUT', 'Settlements of utilities with the tenant', null, 8);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (25, 'SEC', 'Service charges', null, 8);
INSERT INTO rentye.transaction_subtype (id, code, description, last_index, transaction_type_id) VALUES (26, 'COM', 'Compensation', null, 8);

-- fixed asset type

INSERT INTO rentye.fixed_asset_type (id, description) VALUES (1, 'Doors');
INSERT INTO rentye.fixed_asset_type (id, description) VALUES (2, 'Floors');
INSERT INTO rentye.fixed_asset_type (id, description) VALUES (3, 'Main renovation');
INSERT INTO rentye.fixed_asset_type (id, description) VALUES (4, 'Built-in furniture');
INSERT INTO rentye.fixed_asset_type (id, description) VALUES (5, 'Furniture & equipment');
INSERT INTO rentye.fixed_asset_type (id, description) VALUES (6, 'Other services');

-- fixed asset subtype

INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (3, 'Internal doors', 1);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (4, 'External doors', 1);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (5, 'Plumbers', 6);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (6, 'Chimney sweepers', 6);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (7, 'Repairs / Losses', 6);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (8, 'Electrical connection', 6);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (9, 'Cleaning', 6);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (10, 'Debris removal', 6);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (11, 'Parquet materials', 2);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (12, 'Parquet work', 2);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (13, 'Flooring', 2);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (14, 'Roof', 3);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (15, 'Contractor\'s materials', 3);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (16, 'Heating', 3);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (17, 'Electrical equipment', 3);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (18, 'Window sills', 3);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (19, 'Tiles', 3);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (20, 'Contractor\'s work', 3);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (21, 'Sanitary bathrooms', 3);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (22, 'Electrical equipment', 3);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (23, 'Kitchen furniture', 4);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (24, 'Bathroom furniture', 4);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (25, 'Masking panels', 4);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (26, 'Sanitary Kitchen', 4);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (27, 'Wardrobes', 4);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (28, 'Meter installation', 4);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (29, 'Built-in household appliances', 5);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (30, 'Bathroom accessories', 5);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (31, 'Large household appliances', 5);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (32, 'Other equipment', 5);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (33, 'Furniture', 5);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (34, 'Lighting', 5);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (35, 'Roller blinds', 5);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (36, 'RTV', 5);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (37, 'Transport / assembly of furniture', 5);
INSERT INTO rentye.fixed_asset_subtype (id, description, fixed_asset_type_id) VALUES (38, 'Carpets', 5);