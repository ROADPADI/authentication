DROP USER IF EXISTS 'root'@'localhost';

CREATE USER IF NOT EXISTS 'root'@'localhost'

    IDENTIFIED BY 'roadpadi_authentication';

GRANT ALL PRIVILEGES ON root.* TO 'root'@'localhost';

FLUSH PRIVILEGES;