package uem.dam.examen17_18;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import uem.dam.examen17_18.Data.Cd;
import uem.dam.examen17_18.Data.Country;
import uem.dam.examen17_18.RetrofitUtil.ApiCdsService;
import uem.dam.examen17_18.RetrofitUtil.RetrofitClient;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CDActivity extends AppCompatActivity {


    TextView tvtitu;
    TextView tvAut;
    TextView tvAnio;
    TextView tvPreci;
    TextView tvCom;


    RetrofitClient api;

    ImageView ivBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd);


        tvtitu = findViewById(R.id.tv1titulo);
        tvAut = findViewById(R.id.tvAutor);
        tvAnio = findViewById(R.id.tvAño);
        tvPreci = findViewById(R.id.tvPrevcio);
        tvCom = findViewById(R.id.tvCompañia);


        ivBan = findViewById(R.id.ivBandera);

        String titulo = getIntent().getStringExtra(MainActivity.CLAVE_TITULO);

        Retrofit r = RetrofitClient.getClient(ApiCdsService.BASE_URL);
        ApiCdsService ars = r.create(ApiCdsService.class);

        Call<ArrayList<Cd>> call =  ars.obtenerCdsPorTitulo(titulo);


        call.enqueue(new Callback<ArrayList<Cd>>() {
            @Override
            public void onResponse(Call<ArrayList<Cd>> call, Response<ArrayList<Cd>> response) {

                if(response.isSuccessful()){
                    ArrayList<Cd> Cds = response.body();
                    Cd cd = Cds.get(0);

                    tvtitu.setText(cd.getTitle());
                    tvAut.setText(cd.getArtist());
                    tvCom.setText(cd.getCompany());
                    tvAnio.setText(cd.getYear());
                    tvPreci.setText(cd.getPrice());
                    String pais = cd.getCountry();

                    obtenerPais(pais);

                }else{
                    Log.e("onResponse", "onbetnerCdPorTItulo: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Cd>> call, Throwable t) {
                Log.e("onResponse", "onbetnerCdPorTItulo: " + t.getMessage());

            }
        });





    }

    private void obtenerPais(String pais) {

        Retrofit r = RetrofitClient.getClient(ApiCdsService.BASE_URL);
        ApiCdsService ars = r.create(ApiCdsService.class);


        Call<Country> call = ars.obtenePais(pais);

        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                if(response.isSuccessful()){
                    Country country = response.body();
                    String flag = country.getFlag();


                    switch (flag){
                        case"australia":
                            ivBan.setImageResource(R.drawable.australia);
                        break;
                        case"denmark":
                            ivBan.setImageResource(R.drawable.denmark);
                            break;
                        case"itlay":
                            ivBan.setImageResource(R.drawable.italy);
                            break;
                        case"spain":
                            ivBan.setImageResource(R.drawable.spain);
                            break;
                        case"uk":
                            ivBan.setImageResource(R.drawable.uk);
                            break;
                        case"usa":
                            ivBan.setImageResource(R.drawable.usa);
                            break;



                    }

                }else {
                    Log.e("onResponse", "onbetnerPais " + response.code());

                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.e("onResponse", "onbetnerPais: " + t.getMessage());

            }
        });


    }
}
