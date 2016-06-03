package pe.dwkim.hapduk_terminal.Service;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by dwkim on 16. 5. 25..
 */
public class HapdukTerminalClient {
    private static HapdukTerminalService CLIENT;
    private static final String API_URL = "http://52.79.127.75/";

    private HapdukTerminalClient(){}

    static {
        setupClient();
    }

    public static HapdukTerminalService get(){
        return CLIENT;
    }

    private static void setupClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CLIENT = retrofit.create(HapdukTerminalService.class);
    }
}
