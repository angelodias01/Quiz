/**
 * JsonPlaceHolderService.java
 * This interface defines the API service using Retrofit to interact with the remote server
 * and retrieve data related to themes.
 */

package quiz.app.project.dias.dias.model.retrofit;

import java.util.List;
import quiz.app.project.dias.dias.model.theme.Theme;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderService {

    /**
     * Retrieves a list of themes from the remote server using a GET request.
     *
     * @return Call object representing the API call to get themes.
     */
    @GET("Themes/getThemes.php")
    Call<List<Theme>> getThemes();

    //@PUT("appUser/update/{id}'")
    // Call<UserResponse> updateUser(@Path("id") int id, @Body User user);
}
