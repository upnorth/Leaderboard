public class Game {
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore = 0;
    private int awayTeamScore = 0;

    public Game(String homeTeamName, String awayTeamName) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void updateScore(int updatedHomeTeamScore, int updatedAwayTeamScore) {
        // TODO: Validation of new scores (no negative numbers)
        homeTeamScore = updatedHomeTeamScore;
        awayTeamScore = updatedAwayTeamScore;
    }
}
