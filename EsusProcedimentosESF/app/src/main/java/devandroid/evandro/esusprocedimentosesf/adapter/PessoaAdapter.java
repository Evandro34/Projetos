package devandroid.evandro.esusprocedimentosesf.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.model.Pessoa;
import devandroid.evandro.esusprocedimentosesf.view.CadastroProcedimentosActivity;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {

    List<Pessoa> consultasList;

    int id =0;
    public PessoaAdapter(List<Pessoa> consultasList) {
        this.consultasList = consultasList;
    }

    @NonNull
    @Override
    public PessoaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dado_pessoa, parent, false);
        return new PessoaAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaAdapter.MyViewHolder holder, int position) {
        Pessoa pessoa = consultasList.get(position);
        id =pessoa.getIdPessoa();
        holder.iet_id.setText(String.valueOf(pessoa.getIdPessoa()));
        holder.iet_cpf.setText(pessoa.getCpf());
        holder.iet_nome.setText(pessoa.getNome());
        holder.iet_sexo.setText(pessoa.getSexo());
        holder.iet_data_nascimento.setText(pessoa.getData_nascimento());


        holder.btn_adicionar.setOnClickListener(this::onClick);


    }

    private void onClick(View view) {
        Intent intent = new Intent(view.getContext(), CadastroProcedimentosActivity.class);
        intent.putExtra("id",id);
        startActivity(view.getContext(), intent, null);

    }

    @Override
    public int getItemCount() {
        return consultasList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextInputEditText iet_id, iet_cpf, iet_nome, iet_data_nascimento, iet_sexo;
        Button btn_adicionar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iet_id = itemView.findViewById(R.id.iet_id);
            iet_cpf = itemView.findViewById(R.id.iet_cpf);
            iet_nome = itemView.findViewById(R.id.iet_nome);
            iet_data_nascimento = itemView.findViewById(R.id.iet_data_nascimento);
            iet_sexo = itemView.findViewById(R.id.iet_sexo);
            btn_adicionar = itemView.findViewById(R.id.btn_cadastrar);


        }


    }


}
