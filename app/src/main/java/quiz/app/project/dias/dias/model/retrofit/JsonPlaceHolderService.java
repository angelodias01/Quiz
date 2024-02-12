/**
 * JsonPlaceHolderService.java
 * This interface defines the API service using Retrofit to interact with the remote server
 * and retrieve data related to themes.
 */

package quiz.app.project.dias.dias.model.retrofit;

import java.util.List;

import quiz.app.project.dias.dias.model.achievements.Achievements;
import quiz.app.project.dias.dias.model.questions.Questions;
import quiz.app.project.dias.dias.model.theme.Themes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderService {

        /**
         * Retrieves a list of themes from the remote server using a GET request.
         *
         * @return Call object representing the API call to get themes.
         */
        @GET("/themes")
        Call<List<Themes>> getThemes();

        /**
         * Retrieves a single theme by its ID from the remote server using a GET request.
         *
         * @param themeId The ID of the theme to retrieve.
         * @return Call object representing the API call to get a single theme.
         */
        @GET("/themes/{themeId}")
        Call<Themes> getThemeById(@Path("themeId") int themeId);

        /**
         * Retrieves a list of questions from the remote server using a GET request.
         *
         * @return Call object representing the API call to get questions.
         */
        @GET("/questions")
        Call<List<Questions>> getQuestions();

        /**
         * Retrieves a single question by its ID from the remote server using a GET request.
         *
         * @param questionId The ID of the question to retrieve.
         * @return Call object representing the API call to get a single question.
         */
        @GET("/questions/{questionId}")
        Call<Questions> getQuestionById(@Path("questionId") int questionId);

        /**
         * Retrieves a list of achievements from the remote server using a GET request.
         *
         * @return Call object representing the API call to get achievements.
         */
        @GET("/achievements")
        Call<List<Achievements>> getAchievements();

        /**
         * Retrieves a single achievement by its ID from the remote server using a GET request.
         *
         * @param achievementId The ID of the achievement to retrieve.
         * @return Call object representing the API call to get a single achievement.
         */
        @GET("/achievements/{achievementId}")
        Call<Achievements> getAchievementById(@Path("achievementId") int achievementId);
    }
