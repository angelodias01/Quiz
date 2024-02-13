/**
 * ThemeResponse.java
 * Represents the response received from the remote API when fetching themes.
 */

package quiz.app.project.dias.dias.model.questions;

import java.util.List;

import quiz.app.project.dias.dias.model.theme.Themes;

public class QuestionResponse {

    private List<Questions> questions;

    /**
     * Constructor for ThemeResponse class.
     *
     * @param questions List of themes received in the API response.
     */
    public QuestionResponse(List<Questions> questions) {
        this.questions = questions;
    }

    /**
     * Gets the list of themes from the API response.
     *
     * @return List of themes.
     */
    public List<Questions> getQuestions() {
        return questions;
    }
}