Create Database Sports;
Use Sports;

Select * from sports.grounds order by 1 desc; 
Select * from sports.sports order by 1 desc;
Select * from sports.amenities order by 1 desc;
Select * from sports.ground_image order by 1 desc;
Select * from sports.available_time order by 1 desc;

INSERT INTO `sports` (`sport_name`, `description`, `price`, `created_at`, `updated_at`, `is_disable`)
VALUES
('Soccer', 'A team sport played with a spherical ball.', 50.00, NOW(), NOW(), 0),
('Basketball', 'A sport where players try to score by shooting a ball through a hoop.', 45.00, NOW(), NOW(), 0),
('Tennis', 'A racket sport that can be played individually against a single opponent.', 55.00, NOW(), NOW(), 0),
('Volleyball', 'A team sport in which two teams of six players are separated by a net.', 40.00, NOW(), NOW(), 0),
('Cricket', 'A bat-and-ball game played between two teams of eleven players on a field.', 60.00, NOW(), NOW(), 0),
('Baseball', 'A bat-and-ball game played between two teams of nine players.', 50.00, NOW(), NOW(), 0),
('Rugby', 'A team sport that involves running with the ball and tackling opponents.', 70.00, NOW(), NOW(), 0),
('Badminton', 'A racquet sport played with shuttlecocks and a net.', 35.00, NOW(), NOW(), 0),
('Hockey', 'A sport played on ice or field where players use sticks to hit a puck or ball.', 65.00, NOW(), NOW(), 0),
('Golf', 'A club-and-ball sport where players try to hit the ball into a series of holes on a course.', 75.00, NOW(), NOW(), 0);


INSERT INTO `amenities` (`name`, `description`, `created_at`, `updated_at`, `is_disable`)
VALUES
('Restroom', 'A facility providing restroom services.', NOW(), NOW(), 0),
('Parking', 'A designated area for vehicle parking.', NOW(), NOW(), 0),
('Lighting', 'Proper lighting for evening events.', NOW(), NOW(), 0),
('Seating', 'Seating arrangements for spectators.', NOW(), NOW(), 0),
('Water Fountain', 'A source for drinking water.', NOW(), NOW(), 0),
('First Aid Kit', 'Kit for providing first aid.', NOW(), NOW(), 0),
('Wi-Fi', 'Wireless internet access.', NOW(), NOW(), 0),
('Concession Stand', 'Food and drink stand for guests.', NOW(), NOW(), 0),
('Locker Rooms', 'Rooms for changing and storing personal items.', NOW(), NOW(), 0),
('Scoreboard', 'Electronic board displaying game scores.', NOW(), NOW(), 0);
