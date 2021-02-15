DROP USER IF EXISTS 'tegadb'@'localhost';

CREATE USER IF NOT EXISTS 'tegadb'@'localhost'

    IDENTIFIED BY 'roadpadi_authentication';

GRANT ALL PRIVILEGES ON * TO 'tegadb'@'localhost';

FLUSH PRIVILEGES;