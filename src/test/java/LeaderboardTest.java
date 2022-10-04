import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {
    String homeTeamName = "Norge";
    String awayTeamName = "Sverige";

    @Test
    void shouldAddNewGameWithZeroScoresWhenStartingNewGame() {
        Leaderboard lb = new Leaderboard();
        StartGameRequest request = new StartGameRequest(homeTeamName, awayTeamName);

        int gameId = lb.startGame(request);

        Game game = lb.getGame(gameId);
        assertEquals(homeTeamName, game.getHomeTeamName());
        assertEquals(awayTeamName, game.getAwayTeamName());
        assertEquals(0, game.getHomeTeamScore());
        assertEquals(0, game.getAwayTeamScore());
    }

    @Test
    void shouldRemoveGameFromLeaderboardWhenFinished() {
        Leaderboard lb = new Leaderboard();
        StartGameRequest request = new StartGameRequest(homeTeamName, awayTeamName);
        int gameId = lb.startGame(request);

        lb.finishGame(gameId);

        Game game = lb.getGame(gameId);
        assertNull(game);
    }

    @Test
    void shouldGiveExistingGameNewScoresWhenUpdated() {
        // TODO: Internal score logic of Game class should be done in separate test
        Leaderboard lb = new Leaderboard();
        StartGameRequest startGameRequest = new StartGameRequest(homeTeamName, awayTeamName);
        int gameId = lb.startGame(startGameRequest);
        int updatedHomeTeamScore = 3;
        int updatedAwayTeamScore = 4;
        UpdateScoreRequest updateScoreRequest = new UpdateScoreRequest(
                gameId,
                updatedHomeTeamScore,
                updatedAwayTeamScore
        );

        lb.updateScore(updateScoreRequest);

        Game game = lb.getGame(gameId);
        assertEquals(updatedHomeTeamScore, game.getHomeTeamScore());
        assertEquals(updatedAwayTeamScore, game.getAwayTeamScore());
    }
}