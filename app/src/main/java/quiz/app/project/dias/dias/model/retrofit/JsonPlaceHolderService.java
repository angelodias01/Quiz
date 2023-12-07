package quiz.app.project.dias.dias.model.retrofit;

import java.util.List;

import quiz.app.project.dias.dias.model.theme.Theme;
import quiz.app.project.dias.dias.model.theme.ThemeResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderService {
    @GET("Themes/getThemes.php")
    Call<List<Theme>> getThemes();

    //@PUT("appUser/update/{id}'")
    // Call<UserResponse> updateUser(@Path("id") int id, @Body User user);
}
