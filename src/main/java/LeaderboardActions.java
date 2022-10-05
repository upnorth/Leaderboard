public interface LeaderboardActions {
    int startGame(String homeTeamName, String awayTeamName);
    void finishGame(int gameId);
    void updateScore(UpdateScoreRequest request);
    String getSummary();
}
