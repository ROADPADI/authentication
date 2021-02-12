DROP USER IF EXISTS 'root'@'localhost';

CREATE USER IF NOT EXISTS 'root'@'localhost'

    IDENTIFIED BY 'roadpadi_authentication';

GRANT ALL PRIVILEGES ON * TO 'root'@'localhost';

FLUSH PRIVILEGES;