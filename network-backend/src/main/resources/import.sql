-- Parent devices
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('be-FD-10-75-6E-bA',0, null);
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('e7-F0-71-3d-f1-5a',1, null);
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('11-c0-f3-D4-aC-e0',2, null);
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('De-eD-bC-07-E1-9e',0, null);

-- Nested devices L1
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('e6-78-CC-F0-D6-2F',0, 'be-FD-10-75-6E-bA');
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('7F-03-dD-A2-7F-E5',1, 'be-FD-10-75-6E-bA');
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('e0-e0-98-f7-6B-D7',2, 'be-FD-10-75-6E-bA');


INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('B5-B7-b6-eD-c7-32',2, 'e7-F0-71-3d-f1-5a');

INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('eA-4B-ba-Ec-0A-d5',1, '11-c0-f3-D4-aC-e0');

INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('b7-EC-Bb-B5-1F-2C',0, '11-c0-f3-D4-aC-e0');

-- Nested devices L2
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('33-Da-6C-dF-23-92',0, 'b7-EC-Bb-B5-1F-2C');
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('97-b4-aD-b7-6b-75',0, 'b7-EC-Bb-B5-1F-2C');

-- Nested devices L3
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('dE-75-ae-70-C8-bF',0, '33-Da-6C-dF-23-92');

