import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {
    @Test
    void shouldAddNewGameWithZeroScoresWhenStartingNewGame() {
        Leaderboard lb = new Leaderboard();
        String homeTeamName = "Norge";
        String awayTeamName = "Sverige";
        StartGameRequest request = new StartGameRequest(homeTeamName, awayTeamName);

        int gameId = lb.startGame(request);

        Game game = lb.getGame(gameId);
        assertEquals(homeTeamName, game.getHomeTeamName());
        assertEquals(awayTeamName, game.getAwayTeamName());
        assertEquals(0, game.getHomeTeamScore());
        assertEquals(0, game.getAwayTeamScore());
    }
}