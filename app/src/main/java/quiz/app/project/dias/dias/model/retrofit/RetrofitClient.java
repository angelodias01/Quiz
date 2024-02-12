/**
 * RetrofitClient.java
 * This class provides a singleton instance of the Retrofit client for making API requests.
 */

package quiz.app.project.dias.dias.model.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    /**
     * Gets the singleton instance of the Retrofit client.
     *
     * @return The Retrofit client instance.
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            // Create a logging interceptor to log HTTP request and response data
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Create an OkHttpClient with the logging interceptor
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            // Build the Retrofit instance with the base URL, OkHttpClient, and Gson converter factory
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000/api/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
