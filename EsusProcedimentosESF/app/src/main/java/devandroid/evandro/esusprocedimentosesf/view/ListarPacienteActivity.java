package devandroid.evandro.esusprocedimentosesf.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;

public class ListarPacienteActivity extends AppCompatActivity {


    private TextInputEditText iet_cpf_cns, iet_nome;
    private static TextInputEditText iet_data_nascimento;
    private ImageView iv_data_nascimento;
    private Button btn_pesquisar;
    private RecyclerView rv_paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_paciente);

        iniciaComponente();

        iv_data_nascimento.setOnClickListener(view -> {
            DialogFragment dialogFragment = new ListarPacienteActivity.DatePicker();
            dialogFragment.show(getSupportFragmentManager(),"DataInicial");
        });

        btn_pesquisar.setOnClickListener(view -> {
           if(iet_cpf_cns.getText().toString().equals("11111111111")){
               Intent intent = new Intent(this,CadastroProcedimentosActivity.class);
               startActivity(intent);
           }else{
               Intent intent = new Intent(this,CadastroPacienteActivity.class);
               startActivity(intent);
           }
        });


    }

    private void iniciaComponente() {
        iet_cpf_cns = findViewById(R.id.iet_cpf_cns);
        iet_nome = findViewById(R.id.iet_nome);
        iet_data_nascimento = findViewById(R.id.iet_data_nascimento);
        iv_data_nascimento = findViewById(R.id.iv_data_nascimento);
        btn_pesquisar = findViewById(R.id.btn_pesquisar);
        rv_paciente = findViewById(R.id.rv_paciente);
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


            String tagClicada= getTag();


            if(tagClicada.equals("DataInicial")) {
                iet_data_nascimento.setText(AppUtil.getDataPicker(iAno,iMes,iDia));
            }


        }
    }
}