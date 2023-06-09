package devandroid.evandro.procedimentosesus.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import devandroid.evandro.procedimentosesus.R;
import devandroid.evandro.procedimentosesus.api.AppUtil;
import devandroid.evandro.procedimentosesus.controller.PacienteController;

public class RelatorioPabActivity extends AppCompatActivity {

    private static EditText ed_data_inicial_pab, ed_data_final_pab;
    private ImageView iv_filtrar;
    private  ImageView iv_data_inicial_pab ;
    private ImageView iv_data_final_pab;
    private TextView tv_total_pa, tv_total_glicemia, tv_covid, tv_total_visita, tv_total_Nebulizacao, tv_total_r_pontos, tv_HIV, tv_hep_c, tv_hep_b, tv_sifilis, tv_dengue;

    String dataInicial;

    String dataFinal;
    private PacienteController pacienteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_pab);



        pacienteController = new PacienteController(this);
        iniciaComponentes();



        iv_data_inicial_pab.setOnClickListener(view -> {
            DialogFragment dialogFragment = new RelatorioPabActivity.DatePicker();
            dialogFragment.show(getSupportFragmentManager(),"DataInicial");

        });
        iv_data_final_pab.setOnClickListener(view -> {
            DialogFragment dialogFragment = new RelatorioPabActivity.DatePicker();
            dialogFragment.show(getSupportFragmentManager(),"DataFinal");
        });


        iv_filtrar.setOnClickListener(view -> {

            if (!TextUtils.isEmpty(ed_data_inicial_pab.getText().toString()) && !TextUtils.isEmpty(ed_data_final_pab.getText().toString())) {

                dataInicial = AppUtil.getDataAtualFormatoAmericanoParaDB(ed_data_inicial_pab.getText().toString());
                dataFinal = AppUtil.getDataAtualFormatoAmericanoParaDB(ed_data_final_pab.getText().toString());
                totalMensalPab(dataInicial,dataFinal);
            } else {
                Toast.makeText(this, "Selecione um intervalo de datas validos", Toast.LENGTH_LONG).show();
            }

        });

    }

    private void iniciaComponentes() {
        ed_data_inicial_pab = findViewById(R.id.ed_data_inicial_pab);
        ed_data_final_pab = findViewById(R.id.ed_data_final_pab);
        iv_data_final_pab = findViewById(R.id.iv_data_final_pab);
        iv_data_inicial_pab = findViewById(R.id.iv_data_inicial_pab);

        iv_filtrar = findViewById(R.id.iv_filtrar_pab);

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
    public static class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            Calendar calendar = Calendar.getInstance();
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            int mes = calendar.get(Calendar.MONTH);
            int ano = calendar.get(Calendar.YEAR);

            DatePickerDialog DatePickerTradicional = new DatePickerDialog(getActivity(), AlertDialog.THEME_TRADITIONAL, this, ano, mes, dia);
            DatePickerDialog DatePickerHoloLight = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, ano, mes, dia);
            DatePickerDialog DatePickerHoldDark = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK, this, ano, mes, dia);
            DatePickerDialog DatePickerDefaultLight = new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, ano, mes, dia);
            DatePickerDialog DatePickerDefaultDark = new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK, this, ano, mes, dia);
            return DatePickerHoldDark;
        }

        @Override
        public void onDateSet(android.widget.DatePicker datePicker, int iAno, int iMes, int iDia) {

            String dia, mes, ano;

            dia = String.valueOf(iDia);
            mes = String.valueOf(((iMes) + 1));
            ano = String.valueOf(iAno);


            dia = (Calendar.DAY_OF_MONTH < 10) ? "0" + dia : dia;

            dia = (dia.length() > 2) ? dia.substring(1, 3) : dia;

            int mesAtual = (Calendar.MONTH) + 1;

            mes = (mesAtual <= 9) ? "0" + mes : mes;

            mes = (mes.length() > 2) ? mes.substring(1, 3) : mes;


            String  dataAtual = dia + "-" + mes + "-" + ano;

            String tagClicada= getTag();

            if(tagClicada.equals("DataInicial")){

                ed_data_inicial_pab.setText(dataAtual);

            }else if(tagClicada.equals("DataFinal")){
                ed_data_final_pab.setText(dataAtual);
            }

        }
    }
}