# Getting Started

Implementation of Rock Scissors Paper game.

Backend done in Spring Boot and frontend in a simple Angular 8.

## Game Back End

### Build

Using mave you can build he project.

```$mvn clean build```

### Execution
Start the backend as an usual java application

```$ java -jar rock-game-server-0.0.1-SNAPSHOT.jar```

This file is locate in the target folder.

## Game Front End

### Build

Using Angular CLI you can build the project

```ng build-dev```

### Execution

Front end can be run by

```$ng serve```

Access to the front end can be done by

```http://localhost:880/game```

## How to play

In order to play you need first to provide a name to your player. So introduce a name and press create game button will create a new game.
Then Play Game and Reset game buttons will be enable.
Every time you press play game, a round will be generated and result will appear in table at the bottom.
When you press Reset Game, the table will be clean but the game will still be active.
If you reload the page or go to see the stats of all games, the game will considered finnished so you need to start a new game.
Going to Game-list will show a table with all the stats for all the games you had played.