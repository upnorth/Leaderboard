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

    public void finishGame(int gameId) {
        // TODO: Error handling for no matching game
        games.remove(gameId);
    }

    public void updateScore(UpdateScoreRequest request) {
        // TODO: Error handling for no matching game
        Game game = games.get(request.getGameId());
        game.updateScore(request.getUpdatedHomeTeamScore(), request.getUpdatedAwayTeamScore());
    }
}
