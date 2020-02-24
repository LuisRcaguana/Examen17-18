package uem.dam.examen17_18.RetrofitUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import uem.dam.examen17_18.Data.Cd;
import uem.dam.examen17_18.Data.Country;

public interface ApiCdsService {

    public static final String BASE_URL = "http://10.0.2.2host:3000/";

    @GET("cds")
    Call<ArrayList<Cd>> obtenerCds();

    @GET("cds")
    Call<ArrayList<Cd>> obtenerCdsPorTitulo(@Query("title") String titulo);

    @GET("countries/(id_country)")
    Call<Country> obtenePais(@Path("id_country")String pais);

}
