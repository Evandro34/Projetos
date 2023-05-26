package devandroid.evandro.esusprocedimetnos.adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import devandroid.evandro.esusprocedimetnos.R;
import devandroid.evandro.esusprocedimetnos.model.Consulta;

public class AdapterManha extends RecyclerView.Adapter<AdapterManha.ViewHolder> {


    private List<Consulta>consultaList ;

    public AdapterManha(List<Consulta> consultaList) {
        this.consultaList = consultaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_procedimentos,parent,false);
        return new ViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Consulta consulta = consultaList.get(position);

        holder.txt_data.setText(consulta.getData());
        holder.txt_turno.setText(consulta.getTurno());
        holder.txt_cpf.setText(consulta.getCnsPaciente());
        holder.txt_data_nascimento.setText(consulta.getData_nascimento());
        holder.txt_local.setText(consulta.getLocal());
        holder.txt_procedimento.setText(consulta.getProcedimentos());

    }

    @Override
    public int getItemCount() {
        return consultaList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        TextView txt_data,txt_turno,txt_cpf,txt_data_nascimento,txt_local,txt_procedimento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            txt_data = itemView.findViewById(R.id.txt_data);
            txt_turno = itemView.findViewById(R.id.txt_turno);
            txt_cpf = itemView.findViewById(R.id.txt_cpf);
            txt_data_nascimento = itemView.findViewById(R.id. txt_data_nascimento);
            txt_local = itemView.findViewById(R.id.txt_local);
            txt_procedimento = itemView.findViewById(R.id.txt_procedimentos);
        }
    }
}
