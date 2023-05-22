package devandroid.evandro.meuprocedimentos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import devandroid.evandro.meuprocedimentos.R;
import devandroid.evandro.meuprocedimentos.model.TurnoProcedimentos;

public class TurnoTardeAdapter extends RecyclerView.Adapter<TurnoTardeAdapter.MyViewHolder>{

    private final List<TurnoProcedimentos> categoriaList;


    public TurnoTardeAdapter(List<TurnoProcedimentos> categoriaList) {
        this.categoriaList = categoriaList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemVIew = LayoutInflater.from(parent.getContext()).inflate(R.layout.turno, parent, false);
        return new MyViewHolder(itemVIew);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TurnoProcedimentos turnoProcedimentos = categoriaList.get(position);

        holder.txt_data.setText("DATA: "+turnoProcedimentos.getData());
        holder.txt_turno.setText("TURNO:"+turnoProcedimentos.getTurno());
        holder.txt_cpf.setText("CPF"+turnoProcedimentos.getCpf());
        holder.txt_data_nascimento_a.setText("DN:"+turnoProcedimentos.getData_nascimento());
        holder.txt_local_atendimento.setText("LOCAL:"+turnoProcedimentos.getLocal());
        holder.txt_procedimentos_realizados.setText("PROCEDIMENT0"+turnoProcedimentos.getProcedimentos());

        /*
        holder.txt_data.setText("10/05/2023");
        holder.txt_turno.setText("Manha");
        holder.txt_cpf.setText("0000000000000");
        holder.txt_local_atendimento.setText("usb");
        holder.txt_procedimentos_realizados.setText("Curativo,pa");
         */
    }

    @Override
    public int getItemCount() {
        return categoriaList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_data, txt_turno, txt_cpf, txt_data_nascimento_a, txt_local_atendimento, txt_procedimentos_realizados;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_data = itemView.findViewById(R.id.txt_data);
            txt_turno = itemView.findViewById(R.id.txt_turno);
            txt_cpf = itemView.findViewById(R.id.txt_cpf);
            txt_data_nascimento_a = itemView.findViewById(R.id.txt_data_nascimento_a);
            txt_local_atendimento = itemView.findViewById(R.id.txt_local_atendimento);
            txt_procedimentos_realizados = itemView.findViewById(R.id.txt_procedimentos_realizados);


        }
    }
}
