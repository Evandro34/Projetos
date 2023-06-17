package devandroid.evandro.esusprocedimentosesf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.model.Consulta;

public class TesteAdapter extends RecyclerView.Adapter<TesteAdapter.MyViewHolder> {

    private List<Consulta> consultaList ;

    public TesteAdapter(List<Consulta> consultaList) {
        this.consultaList = consultaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_procedimentos2,parent,false);
        return new MyViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Consulta consulta =consultaList.get(position);

            String dataFormatoBrasileiro = AppUtil.getDataAtualFormatoBrasileiro(consulta.getData());
            holder.tv_data.setText(dataFormatoBrasileiro);
            holder.tv_turno.setText(consulta.getTurno());
            holder.tv_cpf.setText(consulta.getCpf());
            holder.tv_data_nascimento.setText(consulta.getData_nascimento());
            holder.tv_sexo.setText(consulta.getSexo());
            holder.tv_local.setText(consulta.getLocal());
            holder.tv_procedimentos.setText(consulta.getProcedimentos());





    }

    @Override
    public int getItemCount() {
        return consultaList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextInputEditText tv_data,tv_turno,tv_cpf,
                tv_data_nascimento,tv_sexo,tv_local,tv_procedimentos;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_data =itemView.findViewById(R.id.tv_data);
            tv_turno =itemView.findViewById(R.id.tv_turno);
            tv_cpf =itemView.findViewById(R.id.tv_cpf);
            tv_data_nascimento =itemView.findViewById(R.id.tv_data_nascimento);
            tv_sexo =itemView.findViewById(R.id.tv_sexo);
            tv_local =itemView.findViewById(R.id.tv_local);
            tv_procedimentos=itemView.findViewById(R.id.tv_procedimentos);
        }
    }
}
