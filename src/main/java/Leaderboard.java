import java.util.ArrayList;
import java.util.List;

public class Leaderboard implements LeaderboardActions {
    private List<Game> games = new ArrayList<>();
    private int nextGameId = 1;

    public int startGame(String homeTeamName, String awayTeamName) {
        Game newGame = new Game(nextGameId, homeTeamName, awayTeamName);
        games.add(newGame);
        int addedGameId = nextGameId;
        nextGameId += 1;
        return addedGameId;
    }

    // Only used for internal tests
    Game getGame(int gameId) {
        for (Game game : games) {
            if(game.getId() == gameId) {
                return game;
            }
        }
        return null;
    }

    public void finishGame(int gameId) {
        games.removeIf((Game g) -> g.getId() == gameId);
    }

    public void updateScore(UpdateScoreRequest request) {
        for (Game game : games) {
            if(game.getId() == request.getGameId()) {
                game.updateScore(request.getUpdatedHomeTeamScore(), request.getUpdatedAwayTeamScore());
            }
        }
    }

    void sortGamesByTotalScoresAndRegistration() {
        games.sort((Game g1, Game g2) -> {
            // Multiplying .compare() result with -1 to reverse to higher first.
            // TODO: Find or create more proper sortByHighestFirst(a, b) helper
            if(g1.getTotalScore() != g2.getTotalScore()) {
                return -1 * Integer.compare(g1.getTotalScore(), g2.getTotalScore());
            }
            return -1 * Integer.compare(g1.getId(), g2.getId());
        });
    }

    public String getSummary() {
        sortGamesByTotalScoresAndRegistration();

        StringBuilder sb = new StringBuilder();
        int gameIndex = 1;
        for (Game g: games) {
            if(gameIndex > 1) sb.append("\n");
            sb.append(gameIndex).append(". ").append(g.getHomeTeamName()).append(" ").append(g.getHomeTeamScore())
                    .append(" - ").append(g.getAwayTeamName()).append(" ").append(g.getAwayTeamScore());
            gameIndex += 1;
        }

        return sb.toString();
    }

    @Override
    public List<Game> getSummaryData() {
        sortGamesByTotalScoresAndRegistration();
        return games;
    }
}
