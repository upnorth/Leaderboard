import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {
    String homeTeamName = "Norge";
    String awayTeamName = "Sverige";

    @Test
    void shouldAddNewGameWithZeroScoresWhenStartingNewGame() {
        Leaderboard lb = new Leaderboard();

        int gameId = lb.startGame(homeTeamName, awayTeamName);

        Game game = lb.getGame(gameId);
        assertEquals(homeTeamName, game.getHomeTeamName());
        assertEquals(awayTeamName, game.getAwayTeamName());
        assertEquals(0, game.getHomeTeamScore());
        assertEquals(0, game.getAwayTeamScore());
    }

    @Test
    void shouldRemoveGameFromLeaderboardWhenFinished() {
        Leaderboard lb = new Leaderboard();
        int gameIdFinish = lb.startGame(homeTeamName, awayTeamName);
        int gameIdActive = lb.startGame("Home", "Away");

        lb.finishGame(gameIdFinish);

        Game finished = lb.getGame(gameIdFinish);
        Game active = lb.getGame(gameIdActive);
        assertNull(finished);
        assertNotNull(active);
    }

    @Test
    void shouldGiveExistingGameNewScoresWhenUpdated() {
        // TODO: Internal score logic of Game class should be done in separate test
        Leaderboard lb = new Leaderboard();
        int gameId = lb.startGame(homeTeamName, awayTeamName);
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

    @Test
    void shouldGiveASortedTextSummaryOfActiveGamesWhenRequested() {
        // --- Setup ---
        String team1 = homeTeamName;
        String team2 = awayTeamName;
        String team3 = "Granada";
        String team4 = "Spain";
        String team5 = "France";
        String team6 = "Germany";

        Leaderboard lb = new Leaderboard();
        int game1Id = lb.startGame(team1, team2);
        int game2Id = lb.startGame(team3, team4);
        int game3Id = lb.startGame(team5, team6);

        // First presented (shared highest total score, added last)
        int team3finalScore = 4;
        int team4finalScore = 1;
        lb.updateScore(new UpdateScoreRequest(game2Id, team3finalScore, team4finalScore));

        // Second presented (shared highest total score, added first)
        int team1finalScore = 2;
        int team2finalScore = 3;
        lb.updateScore(new UpdateScoreRequest(game1Id, team1finalScore, team2finalScore));

        // Last presented (lowest total score, added last)
        int team5finalScore = 0;
        int team6finalScore = 2;
        lb.updateScore(new UpdateScoreRequest(game3Id, team5finalScore, team6finalScore));

        // --- Run ---
        String summary = lb.getSummary();

        // --- Verify ---
        // Skipping string builder here to get better readability in the assertion:
        String expectedSummary =
                "1. " + team3 + " " + team3finalScore + " - " + team4 + " " + team4finalScore + "\n" +
                "2. " + team1 + " " + team1finalScore + " - " + team2 + " " + team2finalScore + "\n" +
                "3. " + team5 + " " + team5finalScore + " - " + team6 + " " + team6finalScore;

        assertEquals(expectedSummary, summary);
    }

    @Test
    void shouldGiveASortedListSummaryOfActiveGamesWhenRequested() {
        // TODO: A lot of code duplication between the summary tests
        // --- Setup ---
        String team1 = homeTeamName;
        String team2 = awayTeamName;
        String team3 = "Granada";
        String team4 = "Spain";
        String team5 = "France";
        String team6 = "Germany";

        Leaderboard lb = new Leaderboard();
        int game1Id = lb.startGame(team1, team2);
        int game2Id = lb.startGame(team3, team4);
        int game3Id = lb.startGame(team5, team6);

        // First presented (shared highest total score, added last)
        int team3finalScore = 4;
        int team4finalScore = 1;
        lb.updateScore(new UpdateScoreRequest(game2Id, team3finalScore, team4finalScore));

        // Second presented (shared highest total score, added first)
        int team1finalScore = 2;
        int team2finalScore = 3;
        lb.updateScore(new UpdateScoreRequest(game1Id, team1finalScore, team2finalScore));

        // Last presented (lowest total score, added last)
        int team5finalScore = 0;
        int team6finalScore = 2;
        lb.updateScore(new UpdateScoreRequest(game3Id, team5finalScore, team6finalScore));

        // --- Run ---
        List<Game> summary = lb.getSummaryData();

        // --- Verify ---
        List<Game> expectedSummary = new ArrayList<>();
        expectedSummary.add(lb.getGame(game2Id));
        expectedSummary.add(lb.getGame(game1Id));
        expectedSummary.add(lb.getGame(game3Id));

        assertEquals(expectedSummary, summary);
    }
}