public class UpdateScoreRequest {
    private int gameId;
    private int updatedHomeTeamScore;
    private int updatedAwayTeamScore;

    public UpdateScoreRequest(int gameId, int updatedHomeTeamScore, int updatedAwayTeamScore) {
        this.gameId = gameId;
        this.updatedHomeTeamScore = updatedHomeTeamScore;
        this.updatedAwayTeamScore = updatedAwayTeamScore;
    }

    public int getGameId() {
        return gameId;
    }

    public int getUpdatedHomeTeamScore() {
        return updatedHomeTeamScore;
    }

    public int getUpdatedAwayTeamScore() {
        return updatedAwayTeamScore;
    }
}
