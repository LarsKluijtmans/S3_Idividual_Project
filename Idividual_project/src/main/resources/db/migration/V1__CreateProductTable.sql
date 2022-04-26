CREATE TABLE  genre(
    id   int     NOT NULL AUTO_INCREMENT,
    genre char(200) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product(
    id   int NOT NULL AUTO_INCREMENT,
    title char(50) NOT NULL,
    sub_title varchar(50),
    series varchar(30),
    year int,
    price double,
    condition_ enum('TRASH','VERY_BAD','BAD','NOT_BAD','OK','GOOD','GREAT','EXCELLENT'),
    description varchar(500),
    genre int,
    sold boolean,
    product_type enum('GAME', 'GAMES'),
    PRIMARY KEY (id),
    FOREIGN KEY (genre) references genre(id)
);

CREATE TABLE images(
    id   int     NOT NULL AUTO_INCREMENT,
    image_url char(200) NOT NULL,
    product_Id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_Id) references product(id)
);

INSERT INTO genre (`genre`) VALUES ('BOARD_GAME');
INSERT INTO genre (`genre`) VALUES ('CARD_GAME');
INSERT INTO genre (`genre`) VALUES ('CASINO_GAME');
INSERT INTO genre (`genre`) VALUES ('DIGITAL_COLLECTIBLE_CARD_GAME');
INSERT INTO genre (`genre`) VALUES ('GACHA_GAME');
INSERT INTO genre (`genre`) VALUES ('HORROR_GAME');
INSERT INTO genre (`genre`) VALUES ('IDLE_GAME');
INSERT INTO genre (`genre`) VALUES ('LOGIC_GAME');
INSERT INTO genre (`genre`) VALUES ('PARTY_GAME');
INSERT INTO genre (`genre`) VALUES ('PHOTOGRAPHY_GAME');
INSERT INTO genre (`genre`) VALUES ('PROGRAMMING_GAME');
INSERT INTO genre (`genre`) VALUES ('SOCIAL_DEDUCTION_GAME');
INSERT INTO genre (`genre`) VALUES ('TRIVIA_GAME');
INSERT INTO genre (`genre`) VALUES ('TYPING_GAME');
INSERT INTO genre (`genre`) VALUES ('ADVERGAME');
INSERT INTO genre (`genre`) VALUES ('ART_GAME');
INSERT INTO genre (`genre`) VALUES ('CASUAL_GAME');
INSERT INTO genre (`genre`) VALUES ('CHRISTIAN_GAME');
INSERT INTO genre (`genre`) VALUES ('EDUCATIONAL_GAME');
INSERT INTO genre (`genre`) VALUES ('ESPORTS');
INSERT INTO genre (`genre`) VALUES ('EXERGAME');
INSERT INTO genre (`genre`) VALUES ('PERSONALIZED_GAME');
INSERT INTO genre (`genre`) VALUES ('SERIOUS_GAME');
INSERT INTO genre (`genre`) VALUES ('ACTION');
INSERT INTO genre (`genre`) VALUES ('PLATFORM_GAMES');
INSERT INTO genre (`genre`) VALUES ('SHOOTER_GAMES');
INSERT INTO genre (`genre`) VALUES ('FIGHTING_GAMES');
INSERT INTO genre (`genre`) VALUES ('BEAT_EM_UP_GAME');
INSERT INTO genre (`genre`) VALUES ('STEALTH_GAME');
INSERT INTO genre (`genre`) VALUES ('SURVIVAL_GAMES');
INSERT INTO genre (`genre`) VALUES ('RHYTHM_GAMES');
INSERT INTO genre (`genre`) VALUES ('BATTLE_ROYAL_GAMES');
INSERT INTO genre (`genre`) VALUES ('ACTION_ADVENTURE');
INSERT INTO genre (`genre`) VALUES ('SURVIVAL_HORROR');
INSERT INTO genre (`genre`) VALUES ('METROPOLITAN');
INSERT INTO genre (`genre`) VALUES ('ADVENTURE');
INSERT INTO genre (`genre`) VALUES ('TEXT_ADVENTURE');
INSERT INTO genre (`genre`) VALUES ('GRAPHIC_ADVENTURES');
INSERT INTO genre (`genre`) VALUES ('VISUAL_NOVELS');
INSERT INTO genre (`genre`) VALUES ('INTERACTIVE_MOVIE');
INSERT INTO genre (`genre`) VALUES ('REAL_TIME_3D_ADVENTURE');
INSERT INTO genre (`genre`) VALUES ('PUZZLE');
INSERT INTO genre (`genre`) VALUES ('BREAKOUT_CLONE_GAME');
INSERT INTO genre (`genre`) VALUES ('LOGICAL_GAME');
INSERT INTO genre (`genre`) VALUES ('PHYSICS_GAME');
INSERT INTO genre (`genre`) VALUES ('CODING_GAME');
INSERT INTO genre (`genre`) VALUES ('TRIAL_AND_ERROR_EXPLORATION');
INSERT INTO genre (`genre`) VALUES ('HIDDEN_OBJECT_GAME');
INSERT INTO genre (`genre`) VALUES ('REVEAL_THE_PICTURE_GAME');
INSERT INTO genre (`genre`) VALUES ('TITLE_MATCHING_GAME');
INSERT INTO genre (`genre`) VALUES ('TRADITIONAL_PUZZLE_GAME');
INSERT INTO genre (`genre`) VALUES ('ROLE_PLAYING');
INSERT INTO genre (`genre`) VALUES ('ACTION_RPG');
INSERT INTO genre (`genre`) VALUES ('MMORPG');
INSERT INTO genre (`genre`) VALUES ('ROGUELIKE');
INSERT INTO genre (`genre`) VALUES ('TACTICAL_RPG');
INSERT INTO genre (`genre`) VALUES ('Sandbox_RPG');
INSERT INTO genre (`genre`) VALUES ('FIRST_PERSON_PARTY_BASED_RPG');
INSERT INTO genre (`genre`) VALUES ('JRPG');
INSERT INTO genre (`genre`) VALUES ('MONSTER_TAMER');
INSERT INTO genre (`genre`) VALUES ('SIMULATION');
INSERT INTO genre (`genre`) VALUES ('CONSTRUCTION_AND_MANAGEMENT_SIMULATION');
INSERT INTO genre (`genre`) VALUES ('LIFE_SIMULATION');
INSERT INTO genre (`genre`) VALUES ('4X_GAME');
INSERT INTO genre (`genre`) VALUES ('ARTILLERY_GAME');
INSERT INTO genre (`genre`) VALUES ('AUTO_BATTLER_(AUTO_CHESS)');
INSERT INTO genre (`genre`) VALUES ('MULTIPLAYER_ONLINE_BATTLE_ARENA_(MOBA)');
INSERT INTO genre (`genre`) VALUES ('REAL_TIME_STRATEGY_(RTS)');
INSERT INTO genre (`genre`) VALUES ('REAL_TIME_TACTICS_(RTT)');
INSERT INTO genre (`genre`) VALUES ('TOWER_DEFENSE');
INSERT INTO genre (`genre`) VALUES ('TURN_BASED_STRATEGY_(TBS)');
INSERT INTO genre (`genre`) VALUES ('TURN_BASED_TACTICS_(TBT)');
INSERT INTO genre (`genre`) VALUES ('WARGAME');
INSERT INTO genre (`genre`) VALUES ('GRAND_STRATEGY_WAR_GAME');
INSERT INTO genre (`genre`) VALUES ('RACING');
INSERT INTO genre (`genre`) VALUES ('SPORTS_GAME');
INSERT INTO genre (`genre`) VALUES ('COMPETITIVE');
INSERT INTO genre (`genre`) VALUES ('SPORT_BASED_FIGHTING');
INSERT INTO genre (`genre`) VALUES ('MMO');