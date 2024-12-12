# GameManagement

A Spring Boot application designed to manage game sessions, player participations, and game outcomes. This project provides RESTful APIs for creating, updating, retrieving, and deleting game data. It also supports player participation tracking, score recording, and player statistics updates via REST communication with the Player Management Service.

## Features

### Game Management:
- Create game sessions with attributes such as game type, maximum score, and session date.
- Host players can create, update, or delete game sessions.

### Player Participation:
- Record player participation, including game ID, player ID, score, and victory status.
- Track game history and maintain performance records.

### Real-Time Game Events:
- Update player statistics (score, level, total wins) after game completion.
- Maintain a game history log with maximum scores and winning details.

### RESTful APIs:
- Seamless integration with Player Management Service for retrieving and updating player data.

## Technologies Used

- **Spring Boot:**
  - Spring Web (REST APIs)
  - Spring Data JPA (Database access)
  - Spring Security (future integration)

- **MySQL:** Database for storing game, participation, and player data.
- **Java 21:** Ensures modern features and performance.
- **Maven:** Dependency and build management.

## Installation

### Clone the Repository:
```bash
git clone https://github.com/anitunga/game-management.git
cd game-management
```

### Configure the Database Connection in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shared_db
spring.datasource.username=alain
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
```

## API Documentation

Refer to the complete API documentation for available endpoints, request/response formats, and expected behaviors.

For additional features, contributions, or detailed explanations, please refer to the full project documentation.



# Game Management Service Documentation

## Table of Contents
1. [Overview](#overview)
2. [API Endpoints](#api-endpoints)
3. [Business Logic](#business-logic)
4. [Database Schema](#database-schema)
5. [System Flow](#system-flow)
6. [Technical Implementation](#technical-implementation)

## Overview

The Game Management Service handles game sessions, player participations, and game outcomes in the gaming platform. It works in conjunction with the Player Management Service to maintain player statistics and progression.

### Key Features
- Game session management
- Player participation tracking
- Score and victory recording
- Integration with Player Service
- Support for different game types

## API Endpoints

### Game Management

#### Create Game
```http
POST /api/games
Content-Type: application/json
{
  "date": "2024-03-20T15:00:00",
  "gameType": "MULTIPLAYER",
  "maxScore": 100,
  "hostId": 1
}
```

**Response** (200 OK):
```json
{
  "id": 1,
  "date": "2024-03-20T15:00:00",
  "gameType": "MULTIPLAYER",
  "maxScore": 100,
  "hostId": 1,
  "participations": []
}
```

#### Get Game
```http
GET /api/games/{id}
```

**Response** (200 OK):
```json
{
  "id": 1,
  "date": "2024-03-20T15:00:00",
  "gameType": "MULTIPLAYER",
  "maxScore": 100,
  "hostId": 1,
  "participations": [
    {
      "id": 1,
      "playerId": 2,
      "score": 85,
      "victory": false
    }
  ]
}
```

#### Update Game
```http
PUT /api/games/{id}
Content-Type: application/json
{
  "date": "2024-03-20T16:00:00",
  "gameType": "MULTIPLAYER",
  "maxScore": 150,
  "hostId": 1
}
```

**Response** (200 OK)

#### Delete Game
```http
DELETE /api/games/{id}
```

**Response** (200 OK)

### Participation Management

#### Register Participation
```http
POST /api/participations
Content-Type: application/json
{
  "gameId": 1,
  "playerId": 2,
  "score": 85,
  "victory": false
}
```

**Response** (200 OK):
```json
{
  "id": 1,
  "gameId": 1,
  "playerId": 2,
  "score": 85,
  "victory": false
}
```

## Business Logic

### Game Management
- Supports two game types:
  - **SINGLEPLAYER**
  - **MULTIPLAYER**
- Each game has:
  - Host player
  - Maximum score
  - Date/time
  - List of participations
- Games can be updated or deleted by the host

### Participation System
- Records player performance in games
- Tracks individual scores
- Determines victory status
- Updates player statistics via Player Service

### Cross-Service Communication
- Automatically updates player statistics on game completion
- Uses WebClient for asynchronous service communication
- Handles communication failures gracefully

## Database Schema

### Game Table
```sql
CREATE TABLE game (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  date DATETIME NOT NULL,
  game_type VARCHAR(20) NOT NULL,
  max_score INT NOT NULL,
  host_id BIGINT NOT NULL
);
```

### Participation Table
```sql
CREATE TABLE participation (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  game_id BIGINT NOT NULL,
  player_id BIGINT NOT NULL,
  score INT NOT NULL,
  victory BOOLEAN NOT NULL,
  FOREIGN KEY (game_id) REFERENCES game(id)
);
```

### Schema Design Rationale
1. **Game Table**
   - Simple structure for game session data
   - Game type as enum string for flexibility
   - Host ID for ownership tracking
   - Maximum score for victory determination

2. **Participation Table**
   - Links players to games
   - Records individual performance
   - Enables game history tracking
   - Foreign key ensures data integrity

## System Flow

### Game Creation Flow
1. Client sends `GameDTO` with session details
2. System validates host player exists
3. Creates new game session
4. Returns game information

### Participation Flow
1. Player joins game session
2. System records participation
3. Tracks score and victory status
4. Updates player statistics via Player Service

### Game Completion Flow
1. Final scores recorded
2. Victory status determined
3. Player statistics updated
4. Game session marked complete

## Technical Implementation

### Key Components

#### Entity Layer
```java
@Entity
@Data
public class Game {
    @Id @GeneratedValue
    private Long id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private GameType gameType;
    private int maxScore;
    private Long hostId;
    @OneToMany(mappedBy = "game")
    private List<Participation> participations;
}

@Entity
@Data
public class Participation {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private Game game;
    private Long playerId;
    private int score;
    private boolean victory;
}
```

#### DTO Layer
```java
@Data
public class GameDTO {
    private Date date;
    private GameType gameType;
    private int maxScore;
    private Long hostId;
}

@Data
public class ParticipationDTO {
    private Long gameId;
    private Long playerId;
    private int score;
    private boolean victory;
}
```

#### Repository Layer
```java
public interface GameRepository extends JpaRepository<Game, Long> {}
public interface ParticipationRepository extends JpaRepository<Participation, Long> {}
```

### Design Patterns
- **DTO Pattern:** For data transfer
- **Repository Pattern:** For data access
- **Service Layer:** For business logic
- **WebClient:** For service communication

### Error Handling
- Runtime exceptions for business rule violations
- HTTP status codes for API responses
- Async error handling for service communication

---

### Contact
For questions or feedback, please contact:
```info
Email: alain.nitunga@helb-prigogine.be

