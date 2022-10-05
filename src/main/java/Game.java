public class Game {
    private int id;
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore = 0;
    private int awayTeamScore = 0;

    public Game(int id, String homeTeamName, String awayTeamName) {
        this.id = id;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }

    public int getId() {
        return id;
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

    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }
}
