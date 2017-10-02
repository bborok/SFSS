INSERT INTO
  User (sfuId, accountCode, preferredCampus, email, name, phoneNumber, role, studentNumber)
VALUES
  #Admins
  ('admin1', NULL, NULL, 'michael@hotmail.com', 'Micheal', 555456789, 'ADMIN', NULL ),
  ('admin2', NULL, NULL , 'jason@hotmail.com', 'Jason', 5559876543, 'ADMIN', NULL ),
  # Supervisors
  ('supervison', NULL, 'BURNABY', 'jason@sfu.ca', 'Jason', 5551236789, 'SUPERVISOR', NULL ),
  #Team Leaders
  ('teamLeader1', NULL , 'BURNABY', 'nick@hotmail.com', 'Nick', 5559871234, 'TEAM_LEADER', NULL ),
  ('teamLeader2', NULL , 'VANCOUVER', 'sarah@hotmail.com', 'Sarah', 5559841327, 'TEAM_LEADER', NULL ),
  ('teamLeader3', NULL , 'SURREY', 'lisa@hotmail.com', 'Lisa', 5558375406, 'TEAM_LEADER', NULL ),
  #Team Members
  ('user1', NULL , 'BURNABY', 'bob@hotmail.com', 'Bob', 5558375406, 'TEAM_MEMBER', 2598798711 ),
  ('user2', NULL , 'BURNABY', 'ted@hotmail.com', 'Ted', 5558375406, 'TEAM_MEMBER', 555123321),
  ('user3', NULL , 'VANCOUVER', 'bill@hotmail.com', 'Bill', 5558375406, 'TEAM_MEMBER', 555123123),
  ('user4', NULL , 'SURREY', 'albert@hotmail.com', 'Albert', 5558375406, 'TEAM_MEMBER', 555765879),
  ('user5', NULL , 'SURREY', 'max@hotmail.com', 'Max', 5558375406, 'TEAM_MEMBER', 555718579);
