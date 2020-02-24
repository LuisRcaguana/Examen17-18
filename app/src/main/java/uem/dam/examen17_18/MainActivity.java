package uem.dam.examen17_18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uem.dam.examen17_18.Data.Cd;
import uem.dam.examen17_18.RetrofitUtil.ApiCdsService;
import uem.dam.examen17_18.RetrofitUtil.RetrofitClient;
import uem.dam.examen17_18.rvRe.CdsAdapter;

public class MainActivity extends AppCompatActivity {


    public static final String CLAVE_TITULO = "TITULO";
    RecyclerView rv;
    CdsAdapter cdA;
    LinearLayoutManager llm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit r = RetrofitClient.getClient(ApiCdsService.BASE_URL);
        ApiCdsService ars = r.create(ApiCdsService.class);
        Call<ArrayList<Cd>> call = ars.obtenerCds();//aqui pasamos un arrayL



        call.enqueue(new Callback<ArrayList<Cd>>() {
            @Override
            public void onResponse(Call<ArrayList<Cd>> call, Response<ArrayList<Cd>> response) {
                if(response.isSuccessful()){
                    ArrayList<Cd> listaCds = response.body();
                    configurarMetodo(listaCds);
                }
                Log.e("onResponse", "error;" + response.code());



            }

            @Override
            public void onFailure(Call<ArrayList<Cd>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "error: "+ t.getMessage(),
                        Toast.LENGTH_LONG).show();
                Log.e("onFailure", "error: " + t.getMessage());


            }
        });
    }

    private void configurarMetodo(final ArrayList<Cd> listaCds) {
        rv = findViewById(R.id.cve);
        cdA = new CdsAdapter(listaCds);
        cdA.setlistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, CDActivity.class);
                i.putExtra(CLAVE_TITULO, listaCds.get(rv.getChildAdapterPosition(v)).getTitle());
                startActivity(i);
            }
        });
        llm = new LinearLayoutManager(this);

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(cdA);
    }
}
