package devandroid.evandro.esusprocedimentosesf.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.adapter.BpaAdapter;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.controller.PessoaController;
import devandroid.evandro.esusprocedimentosesf.model.Consulta;
import devandroid.evandro.esusprocedimentosesf.model.Pessoa;

public class RelatorioBpaActivity extends AppCompatActivity {

    private RecyclerView rv_bpa;
    private static EditText ed_data_final, ed_data_inicial;
    private ImageView iv_filtrar, iv_data_inicial, iv_data_final;

    private List<Pessoa> consultaList;
    PessoaController pacienteController;
    BpaAdapter bpaAdapter;
    String dataInicial;
    String dataFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_bpa);


        pacienteController = new PessoaController(this);

        iniciaComponente();


        iv_data_inicial.setOnClickListener(view -> {
            DialogFragment dialogFragment = new DatePicker();
            dialogFragment.show(getSupportFragmentManager(),"DataInicial");
        });
        iv_data_final.setOnClickListener(view -> {
            DialogFragment dialogFragment = new DatePicker();
            dialogFragment.show(getSupportFragmentManager(),"DataFinal");
        });

        iv_filtrar.setOnClickListener(view -> {


            if (!TextUtils.isEmpty(ed_data_inicial.getText().toString()) && !TextUtils.isEmpty(ed_data_final.getText().toString())) {
                dataInicial = AppUtil.getDataAtualFormatoAmericanoParaDB(ed_data_inicial.getText().toString());
                dataFinal = AppUtil.getDataAtualFormatoAmericanoParaDB(ed_data_final.getText().toString());
                configRecyclerView();
            } else {
                Toast.makeText(this, "Selecione um intervalo de datas validos", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void iniciaComponente() {
        rv_bpa = findViewById(R.id.rv_bpa);
        ed_data_final = findViewById(R.id.ed_data_final);
        ed_data_inicial = findViewById(R.id.ed_data_inicial);
        iv_data_final = findViewById(R.id.iv_data_final);
        iv_data_inicial = findViewById(R.id.iv_data_inicial);
        iv_filtrar = findViewById(R.id.iv_filtrar);
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

    private List<Pessoa> getCpf(String dataInicial, String dataFinal) {

        List<Pessoa> consultas = new ArrayList<>();

        List<Integer> cpf = new ArrayList<>();


        for (Consulta consulta : pacienteController.getCpfCurativo(dataInicial, dataFinal)
        ) {
            cpf.add(consulta.getFkidPessoaConsulta());

        }
        for (int i = 0; i < cpf.size(); i++) {

            consultas.add(pacienteController.getTodasDataCurativoPorCpf(cpf.get(i), dataInicial, dataFinal));
        }

        return consultas;
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

                ed_data_inicial.setText(dataAtual);

            }else if(tagClicada.equals("DataFinal")){
                ed_data_final.setText(dataAtual);
            }

        }
    }
}