package devandroid.evandro.procedimentosesus.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.procedimentosesus.R;
import devandroid.evandro.procedimentosesus.adapater.BpaAdapter;
import devandroid.evandro.procedimentosesus.api.AppUtil;
import devandroid.evandro.procedimentosesus.controller.PacienteController;
import devandroid.evandro.procedimentosesus.model.Consulta;
import devandroid.evandro.procedimentosesus.model.Paciente;

public class RelatorioBPAActivity extends AppCompatActivity {

    private RecyclerView rv_bpa;
    private EditText ed_data_final, ed_data_inicial;
    private ImageView iv_filtrar;

    private List<Paciente> consultaList;
    PacienteController pacienteController;
    BpaAdapter bpaAdapter;

    String dataI, dataf, sDataI, sDataf;
    String dataInicial;
    String dataFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_bpaactivity);

        pacienteController = new PacienteController(this);
        iniciaComponente();


        iv_filtrar.setOnClickListener(view -> {

            if (!TextUtils.isEmpty(ed_data_inicial.getText().toString()) && !TextUtils.isEmpty(ed_data_final.getText().toString())) {
                dataI = ed_data_inicial.getText().toString();
                dataf = ed_data_final.getText().toString();
                dataInicial = AppUtil.getDataAtualFormatoAmericanoParaDB(dataI.replaceAll("/", "-"));
                dataFinal = AppUtil.getDataAtualFormatoAmericanoParaDB(dataf.replaceAll("/", "-"));
                configRecyclerView();
            } else {
                Toast.makeText(this, "Selecione um intervalo de datas validos", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void iniciaComponente() {
        rv_bpa=findViewById(R.id.rv_bpa);
        ed_data_final =findViewById(R.id.ed_data_final);
        ed_data_inicial =findViewById(R.id.ed_data_inicial);
        iv_filtrar =findViewById(R.id.iv_filtrar);
    }

    private void configRecyclerView() {

        rv_bpa.setLayoutManager(new LinearLayoutManager(this));
        rv_bpa.setHasFixedSize(true);
        getCpf(dataInicial, dataFinal).clear();
        consultaList = getCpf(dataInicial, dataFinal);
        //getCpf("2023-06-01", "2023-06-05").clear();
        //consultaList = getCpf("2023-06-01", "2023-06-05");
        bpaAdapter = new BpaAdapter(consultaList);
        rv_bpa.setAdapter(bpaAdapter);


    }

    private List<Paciente> getCpf(String dataInicial, String dataFinal) {

        List<Paciente> consultas = new ArrayList<>();

        List<String> cpf = new ArrayList<>();


        for (Consulta consulta : pacienteController.getCpfCurativo(dataInicial, dataFinal)
        ) {
            cpf.add(consulta.getCnsPaciente());

        }
        for (int i = 0; i < cpf.size(); i++) {

            consultas.add(pacienteController.getTodasDataCurativoPorCpf(cpf.get(i),dataInicial,dataFinal));
        }

        return consultas;
    }


}