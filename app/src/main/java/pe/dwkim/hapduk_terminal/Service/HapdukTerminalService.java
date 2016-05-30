package pe.dwkim.hapduk_terminal.Service;

import java.util.List;

import pe.dwkim.hapduk_terminal.Model.Destination;
import pe.dwkim.hapduk_terminal.Model.Route;
import pe.dwkim.hapduk_terminal.Model.StopInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dwkim on 16. 5. 25..
 */
public interface HapdukTerminalService {

    @GET("/api/get_intercity_destinations")
    Call<List<Destination>> getIntercityDestinations();

    @GET("/api/get_route/{destinationId}")
    Call<List<StopInfo>> getRoute(@Path("destinationId") int destinationId);
}
