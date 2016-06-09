CREATE TABLE User (
  id CHAR(36),
  firstName VARCHAR(30) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  username VARCHAR(30) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(50) NOT NULL,
  active BIT NOT NULL,
  role VARCHAR(20) NOT NULL,
  createdOn VARCHAR(24) NOT NULL,
  accountLocked BIT NOT NULL,
  failedLoginAttempts INTEGER NOT NULL,
  loginToken VARCHAR(30),
  PRIMARY KEY (id)
);

CREATE TABLE Timeclock (
    userId CHAR(36) NOT NULL,
    clockInTime INTEGER NOT NULL,
    clockOutTime INTEGER,
);
