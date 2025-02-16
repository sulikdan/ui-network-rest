-- Parent devices
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('be-fd-10-75-6e-ba',0, null);
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('e7-f0-71-3d-f1-5a',1, null);
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('11-c0-f3-d4-ac-e0',2, null);
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('de-ed-bc-07-e1-9e',0, null);

-- Nested devices L1
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('e6-78-cc-f0-d6-2f',0, 'be-fd-10-75-6e-ba');
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('7f-03-dd-a2-7f-e5',1, 'be-fd-10-75-6e-ba');
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('e0-e0-98-f7-6b-d7',2, 'be-fd-10-75-6e-ba');


INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('b5-b7-b6-ed-c7-32',2, 'e7-f0-71-3d-f1-5a');

INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('ea-4b-ba-ec-0a-d5',1, '11-c0-f3-d4-ac-e0');

INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('b7-ec-bb-b5-1f-2c',0, '11-c0-f3-d4-ac-e0');

-- Nested devices L2
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('33-da-6c-df-23-92',0, 'b7-ec-bb-b5-1f-2c');
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('97-b4-ad-b7-6b-75',0, 'b7-ec-bb-b5-1f-2c');

-- Nested devices L3
INSERT INTO device (mac_address, device_type, uplink_mac_address) VALUES ('de-75-ae-70-c8-bf',0, '33-da-6c-df-23-92');

