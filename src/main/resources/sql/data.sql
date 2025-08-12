INSERT INTO launch_sites (site_name, location) VALUES
                                                   ('Kennedy Space Center', 'Florida, USA'),
                                                   ('StarBase OML-A', 'Texas, USA'),
                                                   ('Vandenberg Air Force Base', 'California, USA'),
                                                   ('Cape Canaveral Space Force Station', 'Florida, USA');

INSERT INTO rockets (rocket_name, payload_capacity, manufacturer, image_file_name) VALUES
                                                                                       ('Falcon 9', 22800, 'SpaceX', 'rocketPlaceholder.png'),
                                                                                       ('Starship', 150000, 'SpaceX', 'rocketPlaceholder.png'),
                                                                                       ('Atlas V', 18800, 'Lockheed Martin and ULA', 'rocketPlaceholder.png'),
                                                                                       ('Delta IV Heavy', 28600, 'Boeing and ULA', 'rocketPlaceholder.png');

INSERT INTO missions (mission_name, mission_objective, launch_date, mission_type, crew_onboard, is_success, image_file_name, launch_site_id) VALUES
                                                                                                                                                 ('Starlink 1', 'Deliver payload to low-earth orbit', '2020-05-30', 'SATELLITE_DEPLOYMENT', NULL, TRUE, 'missionPlaceholder.png', 1),
                                                                                                                                                 ('Crew-1', 'Deliver astronauts to the ISS', '2021-04-23', 'CREWED_MISSION', 3, TRUE, 'missionPlaceholder.png', 1),
                                                                                                                                                 ('Mars Perseverance Rover', 'Search for signs of ancient life', '2020-07-30', 'INTERPLANETARY_EXPLORATION', NULL, TRUE, 'missionPlaceholder.png', 3),
                                                                                                                                                 ('Fourth Flight Test', 'Attempt landing on virtual tower', '2024-06-06', 'TECHNOLOGY_DEMONSTRATION', NULL, FALSE, 'missionPlaceholder.png', 2);
