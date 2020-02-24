package uem.dam.examen17_18.rvRe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uem.dam.examen17_18.Data.Cd;
import uem.dam.examen17_18.R;

public class CdsAdapter extends RecyclerView.Adapter<CdsAdapter.CdVH> implements View.OnClickListener {


    private ArrayList<Cd> lista;
    View.OnClickListener listener;

    public CdsAdapter(ArrayList <Cd> lista){
        this.lista = lista;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }


    public static class CdVH extends RecyclerView.ViewHolder{

        TextView tvTitulo, tvAutor;

        public CdVH(View v) {
            super(v);

            tvTitulo = v.findViewById(R.id.tvTitulo);
            tvAutor = v.findViewById(R.id.tvAutor);
        }

        public void bindCd(Cd cd){
            tvTitulo.setText(cd.getTitle());
            tvAutor.setText(cd.getArtist());
        }
    }


    @NonNull
    @Override
    public CdVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cd, parent, false);

        v.setOnClickListener(listener);
        return new CdVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CdVH holder, int position) {

        holder.bindCd(lista.get(position));//posicion de la lista
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public  void setlistener(View.OnClickListener listener){
        this.listener= listener;
    }

}
