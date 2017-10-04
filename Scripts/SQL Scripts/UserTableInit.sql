INSERT INTO
  User (SFU_ID, preferredCampus, email, name, phoneNumber, role, StdNum)
VALUES
  #Admins
  ('admin1',  NULL, 'michael@hotmail.com', 'Micheal', 555456789, 'ADMIN', NULL ),
  ('admin2',  NULL , 'jason@hotmail.com', 'Jason', 5559876543, 'ADMIN', NULL ),
  # Supervisors
  ('supervisor', 'BURNABY', 'jason@sfu.ca', 'Jason', 5551236789, 'SUPERVISOR', NULL ),
  #Team Leaders
  ('teamLeader1',  'BURNABY', 'nick@hotmail.com', 'Nick', 5559871234, 'TEAM_LEADER', NULL ),
  ('teamLeader2',  'VANCOUVER', 'sarah@hotmail.com', 'Sarah', 5559841327, 'TEAM_LEADER', NULL ),
  ('teamLeader3',  'SURREY', 'lisa@hotmail.com', 'Lisa', 5558375406, 'TEAM_LEADER', NULL ),
  #Team Members
  ('user1',  'BURNABY', 'bob@hotmail.com', 'Bob', 5558375406, 'MEMBER', 2598798711 ),
  ('user2',  'BURNABY', 'ted@hotmail.com', 'Ted', 5558375406, 'MEMBER', 555123321),
  ('user3',  'VANCOUVER', 'bill@hotmail.com', 'Bill', 5558375406, 'MEMBER', 555123123),
  ('user4',  'SURREY', 'albert@hotmail.com', 'Albert', 5558375406, 'MEMBER', 555765879),
  ('user5',  'SURREY', 'max@hotmail.com', 'Max', 5558375406, 'MEMBER', 555718579);
