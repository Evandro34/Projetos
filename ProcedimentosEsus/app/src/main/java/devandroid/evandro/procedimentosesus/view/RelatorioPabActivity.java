package devandroid.evandro.procedimentosesus.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;

import devandroid.evandro.procedimentosesus.R;
import devandroid.evandro.procedimentosesus.api.AppUtil;
import devandroid.evandro.procedimentosesus.controller.PacienteController;

public class RelatorioPabActivity extends AppCompatActivity {

    private EditText ed_data_inicial, ed_data_final;
    private ImageView iv_filtrar;
    private TextView tv_total_pa, tv_total_glicemia, tv_covid, tv_total_visita, tv_total_Nebulizacao, tv_total_r_pontos, tv_HIV, tv_hep_c, tv_hep_b, tv_sifilis, tv_dengue;
    String dataI,dataf,sDataI,sDataf;
    String dataInicial;
    String dataFinal;
    private PacienteController pacienteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_pab);



        pacienteController = new PacienteController(this);
        iniciaComponentes();
        iv_filtrar.setOnClickListener(view -> {


            dataI = ed_data_inicial.getText().toString();
            dataf = ed_data_final.getText().toString();


            if(!dataI.isEmpty() && !dataf.isEmpty()) {

                    dataI = ed_data_inicial.getText().toString().trim();
                    dataf = ed_data_final.getText().toString().trim();
                    sDataI =dataI.replaceAll("/", "-");
                    sDataf =dataf.replaceAll("/", "-");
                    dataInicial = AppUtil.getDataAtualFormatoAmericanoParaDB(sDataI);
                    dataFinal = AppUtil.getDataAtualFormatoAmericanoParaDB(sDataf);
                    totalMensalPab(dataInicial, dataFinal);

            }else{
                Toast.makeText(this,"Selecione um intervalo de datas validos",Toast.LENGTH_LONG).show();
            }

        });

    }

    private void iniciaComponentes() {
        ed_data_inicial = findViewById(R.id.ed_data_inicial);
        ed_data_final = findViewById(R.id.ed_data_final);

        iv_filtrar = findViewById(R.id.iv_filtrar);

        tv_total_pa = findViewById(R.id.tv_total_pa);
        tv_total_glicemia = findViewById(R.id.tv_total_glicemia);
        tv_total_visita = findViewById(R.id.tv_total_visita);
        tv_total_Nebulizacao = findViewById(R.id.tv_total_Nebulizacao);
        tv_total_r_pontos = findViewById(R.id.tv_total_r_pontos);
        tv_HIV = findViewById(R.id.tv_HIV);
        tv_hep_c = findViewById(R.id.tv_hep_c);
        tv_hep_b = findViewById(R.id.tv_hep_b);
        tv_sifilis = findViewById(R.id.tv_sifilis);
        tv_dengue = findViewById(R.id.tv_dengue);
        tv_covid = findViewById(R.id.tv_covid);
    }

    private void totalMensalPab(String dataInicial, String dataFinal) {

        tv_total_pa.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.PRESSAO_ARTERIAL)));
        tv_total_glicemia.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.GLICEMIA)));
        tv_total_visita.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.VISITA)));
        tv_total_Nebulizacao.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.NEBULIZACAO)));
        tv_total_r_pontos.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.RETIRADA_PONTO)));
        tv_HIV.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.HIV)));
        tv_hep_c.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.HEPATITE_C)));
        tv_hep_b.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.HEPETITE_B)));
        tv_sifilis.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.SIFILIS)));
        tv_dengue.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.DENGUE)));
        tv_covid.setText(String.valueOf(pacienteController.listTotalProcedimentosPab(dataInicial, dataFinal, AppUtil.COVID)));
    }
}