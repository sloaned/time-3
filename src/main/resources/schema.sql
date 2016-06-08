CREATE TABLE User (
  id CHAR(36),
  firstName VARCHAR(30) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  username VARCHAR(30) NOT NULL,
  password VARCHAR(30) NOT NULL,
  email VARCHAR(50),
  active BIT NOT NULL,
  role VARCHAR(20) NOT NULL,
  createdOn VARCHAR(24) NOT NULL,
  birthday VARCHAR(10) DEFAULT NULL,
  accountLocked BIT NOT NULL,
  failedLoginAttempts INTEGER NOT NULL,
  PRIMARY KEY (id)
);
