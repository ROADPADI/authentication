DROP USER IF EXISTS root@'localhost';

CREATE USER IF NOT EXISTS 'root'@'localhost'

    IDENTIFIED BY 'Thethingdonefinallywork4321@';

GRANT ALL PRIVILEGES ON * TO 'root'@'localhost';

FLUSH PRIVILEGES;