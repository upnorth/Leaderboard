import java.util.HashMap;

public class Leaderboard implements LeaderboardActions {
    private HashMap<Integer, Game> games = new HashMap<Integer, Game>();
    private int nextGameId = 1;

    public int startGame(StartGameRequest startGameRequest) {
        Game newGame = new Game(
                startGameRequest.getHomeTeamName(),
                startGameRequest.getAwayTeamName()
        );
        int newGameId = nextGameId;
        games.put(newGameId, newGame);
        nextGameId += 1;
        return newGameId;
    }

    public Game getGame(int gameId) {
        return games.get(gameId);
    }
}
