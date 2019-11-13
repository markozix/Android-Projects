package repository.web.service;

import java.util.List;

import repository.web.model.RasporedApiModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RasporedService {

    @GET("raspored/json.php")
    public Call<List<RasporedApiModel>> getRasporedi();
}
