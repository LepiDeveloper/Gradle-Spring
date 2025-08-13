DROP TABLE IF EXISTS missions;
DROP TABLE IF EXISTS rockets;
DROP TABLE IF EXISTS launch_sites;

CREATE TABLE launch_sites (
                              launch_site_id INT AUTO_INCREMENT PRIMARY KEY,
                              site_name VARCHAR(100) NOT NULL,
                              location VARCHAR(200) NOT NULL,
                              image_file_name VARCHAR(100)
);

CREATE TABLE rockets (
                         rocket_id INT AUTO_INCREMENT PRIMARY KEY,
                         rocket_name VARCHAR(100) NOT NULL,
                         payload_capacity INT NOT NULL,
                         manufacturer VARCHAR(100),
                         image_file_name VARCHAR(100)
);

CREATE TABLE missions (
                          mission_id INT AUTO_INCREMENT PRIMARY KEY,
                          mission_name VARCHAR(100) NOT NULL,
                          mission_objective VARCHAR(255),
                          launch_date DATE,
                          mission_type VARCHAR(50),
                          crew_onboard INT,
                          is_success BOOLEAN,
                          image_file_name VARCHAR(100),
                          launch_site_id INT,
                          FOREIGN KEY (launch_site_id) REFERENCES launch_sites(launch_site_id)
);

CREATE TABLE mission_rocket (
                                mission_id INT NOT NULL,
                                rocket_id INT NOT NULL,
                                PRIMARY KEY (mission_id, rocket_id),
                                FOREIGN KEY (mission_id) REFERENCES missions(mission_id) ON DELETE CASCADE,
                                FOREIGN KEY (rocket_id) REFERENCES rockets(rocket_id) ON DELETE CASCADE
);



