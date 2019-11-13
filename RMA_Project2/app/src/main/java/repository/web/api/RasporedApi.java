package repository.web.api;

import java.util.List;

import repository.web.model.RasporedApiModel;
import repository.web.service.RasporedService;
import repository.web.service.ServiceGenerator;
import retrofit2.Call;

public class RasporedApi {

    private RasporedService rasporedService;

    public RasporedApi(){
        rasporedService = ServiceGenerator.createService(RasporedService.class);
    }

    public Call<List<RasporedApiModel>> getRasporedi() {
        return rasporedService.getRasporedi();
    }


}
