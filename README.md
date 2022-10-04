# Leaderboard

Library that provides in-memory tracking of scores for one or more concurrent live sporting events between two teams

## Assumptions

* OK to use somewhat outdated versions/tools. With no specific technical demands, I'm using Java 8 with Maven and Junit simply due to it being the latest Java tech stack I'm familiar with.
* With that in mind, no particular deliverable or script is provided for specific technical use-cases for evaluation. 

## API

### startGame()

Adds new game to the leaderboard with an initial score of 0 - 0.

Takes a request object with the team names:
```
StartGameRequest {
 String homeTeamName
 String awayTeamName
}
```

### finishGame()

Finishes game by removing it from the Leaderboard. Takes game id.