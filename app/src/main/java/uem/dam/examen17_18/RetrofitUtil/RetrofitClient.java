package uem.dam.examen17_18.RetrofitUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static Retrofit retrofit = null;//libreria k nos permite consumir el web service

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())//cambiar aqui si es de JSon
                    .build();
        }
        return retrofit;
    }
}
