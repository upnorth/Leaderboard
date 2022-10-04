public interface LeaderboardActions {
    int startGame(StartGameRequest startGameRequest);
    Game getGame(int gameId);
}
