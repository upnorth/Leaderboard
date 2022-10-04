public interface LeaderboardActions {
    int startGame(StartGameRequest startGameRequest);
    Game getGame(int gameId);
    void finishGame(int gameId);
    void updateScore(UpdateScoreRequest request);
}
