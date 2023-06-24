package devandroid.evandro.esusprocedimentosesf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.model.Pessoa;


public class BpaAdapter extends RecyclerView.Adapter<BpaAdapter.MyViewHolder> {

    List<Pessoa> consultasList;

    public BpaAdapter(List<Pessoa> consultasList) {
        this.consultasList = consultasList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bpa3,parent,false);
        return new MyViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       Pessoa paciente = consultasList.get(position);

       holder.tv_cpf.setText(paciente.getCpf());
       holder.tv_cns.setText(paciente.getCns());
       holder.tv_nome.setText(paciente.getNome());
       holder.tv_data_nascimento.setText(paciente.getData_nascimento());
       holder.tv_sexo.setText(paciente.getSexo());
       holder.tv_cor.setText(paciente.getCor());
       holder.tv_logradouro.setText(paciente.getEnderecoPaciente().getLogradouro());
       holder.tv_endereco.setText(paciente.getEnderecoPaciente().getEndereco());
       holder.tv_numero.setText(paciente.getEnderecoPaciente().getNumero());
       holder.tv_bairro.setText(paciente.getEnderecoPaciente().getBairro());
       holder.tv_cidade.setText(paciente.getEnderecoPaciente().getCidade());
       holder.tv_cep.setText(paciente.getEnderecoPaciente().getCep());
       holder.tv_procedimentos.setText(paciente.getConsultaPaciente().getProcedimentos());
       holder.tv_data.setText(paciente.getConsultaPaciente().getData());

    }

    @Override
    public int getItemCount() {
        return consultasList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        private TextView tv_nome,tv_cpf,tv_cns,tv_data_nascimento,tv_sexo,tv_cor,
                tv_logradouro,tv_endereco,tv_numero,tv_bairro,tv_cidade,tv_cep,tv_data,tv_procedimentos;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_cpf = itemView.findViewById(R.id.tv_cpf);
            tv_cns = itemView.findViewById(R.id.tv_cns);
            tv_nome = itemView.findViewById(R.id.tv_nome);
            tv_data = itemView.findViewById(R.id.tv_data);
            tv_sexo = itemView.findViewById(R.id.tv_sexo);
            tv_cor = itemView.findViewById(R.id.tv_cor);
            tv_data_nascimento = itemView.findViewById(R.id.tv_data_nascimento);
            tv_logradouro = itemView.findViewById(R.id.tv_logradouro);
            tv_endereco = itemView.findViewById(R.id.tv_endereco);
            tv_numero = itemView.findViewById(R.id.tv_numero);
            tv_bairro = itemView.findViewById(R.id.tv_bairro);
            tv_cidade = itemView.findViewById(R.id.tv_cidade);
            tv_cep =itemView.findViewById(R.id.tv_cep);
            tv_procedimentos =itemView.findViewById(R.id.tv_procedimentos);

        }
    }
}
