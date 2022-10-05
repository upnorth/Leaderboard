# Leaderboard

Library that provides in-memory tracking of scores for one or more concurrent live sporting events between two teams

### Assumptions

#### Functional

* Validation of input for team names and scores should be added before use in production. Names shouldn't be a problem but scores should probably be checked to not be negative at least.

#### Technical
* OK to use somewhat outdated versions/tools. With no specific technical demands, I'm using Java 8 with Maven and Junit simply due to it being the latest Java tech stack I'm familiar with.
* With that in mind, no particular deliverable or script is provided for specific technical use-cases for evaluation.
* More used to making APIs where Leaderboard would be a DI instantiated service, but since the task is designed to be as simple as possible, I just made a class
* Added getSummaryData() to provide a more API-like data format. Normally this would also be mapped to some sort of external data model that doesn't include the internal id's, but to keep it simple I simply return the internal state as is.
* See TODO comments for various known possible improvements 

### API

Basic usage requires instantiating a Leaderboard object

```
Leaderboard lb = new Leaderboard()
```

##### startGame()

Adds new game to the leaderboard with an initial score of 0 - 0.

```
lb.startGame(String homeTeamName, String awayTeamName)
```

##### finishGame()

Finishes game by removing it from the Leaderboard.

```
lb.finishGame(int gameId)
```

##### updateGame()

Updates scores for active game.

Takes a request object with game id and new scores:

```
UpdateScoreRequest {
 int gameId;
 int updatedHomeTeamScore
 int updatedAwayTeamScore
}

lb.updateGame(UpdateScoreRequest updateScoreRequest)
```

##### getSummary()

Returns a text-based indexed list summary of games.
Games are sorted after highest total game score first, and latest added second.

Example:
```
1. Granada 4 - Spain 1
2. Norge 2 - Sverige 3
3. France 0 - Germany 2
```

##### getSummaryData()

Returns a list of game data sorted in the same way as getSummary(), to enable other types of presentation.