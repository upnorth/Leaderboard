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
}