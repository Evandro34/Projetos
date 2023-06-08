package devandroid.evandro.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import devandroid.evandro.agenda.DAO.DAO;
import devandroid.evandro.agenda.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    //private String[] nomes;
    private List<String> nomes = new ArrayList<>();
    private String[] idades;
    private Context context;
    private View viewOncreate;
    private ViewHolder viewHolderLocal;


    public RecyclerViewAdapter(Context context, String[] nomes, String[] idades) {
        this.context = context;
        this.nomes.addAll(Arrays.asList(nomes));
        this.idades = idades;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        viewOncreate = LayoutInflater.from(context).inflate(R.layout.recyclerview_itens,parent,false);
        viewHolderLocal= new ViewHolder(viewOncreate);

        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tv_nome.setText(nomes.get(position));
        holder.tv_idade.setText(idades[position]);

        holder.icone.setOnClickListener(view -> {
            DAO dao = new DAO(context);
            dao.apagaPessoa(nomes.get(position));
            nomes.remove(position);
            //NOTIFICA AO RECYCLERVIEW QUE REMOVEU;
            notifyItemRemoved(position);
            //NOTIFICA E ATUALIZA  O RECYCLERVIEW QUE REMOVEU;
            notifyItemRangeRemoved(position,nomes.size());
        });



    }

    @Override
    public int getItemCount() {
        return nomes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_nome, tv_idade;
        public ImageView icone;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_nome = itemView.findViewById(R.id.tv_nome);
            tv_idade = itemView.findViewById(R.id.tv_idade);
            icone = itemView.findViewById(R.id.iv_icone);


        }
    }
}
