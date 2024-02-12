/**
 * ThemeResponse.java
 * Represents the response received from the remote API when fetching themes.
 */

package quiz.app.project.dias.dias.model.theme;

import java.util.List;

public class ThemeResponse {

    private List<Themes> themes;

    /**
     * Constructor for ThemeResponse class.
     *
     * @param themes List of themes received in the API response.
     */
    public ThemeResponse(List<Themes> themes) {
        this.themes = themes;
    }

    /**
     * Gets the list of themes from the API response.
     *
     * @return List of themes.
     */
    public List<Themes> getThemes() {
        return themes;
    }
}