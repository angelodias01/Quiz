/*
 * ThemeResponse.java
 * This class represents the response format for theme themes from a remote API.
 */

package quiz.app.project.dias.dias.model.theme;

import java.util.List;

/*
 * ThemeResponse class contains a list of Theme objects retrieved from the API.
 */
public class ThemeResponse {

    /*
     * The 'themes' field contains a list of Theme objects.
     */
    public List<Theme> themes;

    /*
     * Constructor for the ThemeResponse class.
     * Parameters:
     *   - themes: List of Theme objects retrieved from the API.
     */
    public ThemeResponse(List<Theme> themes) {
        this.themes = themes;
    }

    /*
     * Getter method for the 'themes' field.
     * Returns the list of Theme objects.
     */
    public List<Theme> getThemes() {
        return themes;
    }
}
