package quiz.app.project.dias.dias.model.achievements;

import java.util.List;

public class AchievementsResponse {
    private List<Achievements> achievements;

    /**
     * Constructor for ThemeResponse class.
     *
     * @param achievements List of themes received in the API response.
     */
    public AchievementsResponse(List<Achievements> achievements) {
        this.achievements = achievements;
    }

    /**
     * Gets the list of themes from the API response.
     *
     * @return List of themes.
     */
    public List<Achievements> getAchievements() {
        return achievements;
    }
}
