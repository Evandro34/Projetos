package devandroid.evandro.esusprocedimentosesf.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.adapter.PessoaAdapter;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.controller.PessoaController;
import devandroid.evandro.esusprocedimentosesf.model.Pessoa;

public class ListarPacienteActivity extends AppCompatActivity {


    private TextInputEditText iet_cpf_cns, iet_nome;
    private PessoaAdapter pessoaAdapter;
    private List<Pessoa> consultaList;
    private PessoaController pessoaController;
    private static TextInputEditText iet_data_nascimento;
    private ImageView iv_data_nascimento;
    private ImageButton ib_add;
    private Button btn_pesquisar;
    private RecyclerView rv_paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_paciente);
        pessoaController = new PessoaController(this);

        iniciaComponente();

        acoesdoClique();


    }

    private void acoesdoClique() {
        iv_data_nascimento.setOnClickListener(view -> {
            DialogFragment dialogFragment = new DatePicker();
            dialogFragment.show(getSupportFragmentManager(), "DataInicial");
        });


        btn_pesquisar.setOnClickListener(view -> {
            Pessoa pessoa = new Pessoa();
            if (!iet_cpf_cns.getText().toString().isEmpty() && pessoaController.verificaTotalDeRegistroLocalizado(iet_cpf_cns.getText().toString())>0){

                consultaList = getCpf(iet_cpf_cns.getText().toString());
                configRecyclerView();
            } else if (!iet_nome.getText().toString().isEmpty() && !iet_data_nascimento.getText().toString().isEmpty()  && pessoaController.verificaTotalDeRegistroLocalizado(iet_nome.getText().toString(),iet_data_nascimento.getText().toString())>0) {
                consultaList = getCpf(iet_nome.getText().toString(), iet_data_nascimento.getText().toString());
                configRecyclerView();
            } else {
                Toast.makeText(this,"Pessoa Nao Encontrada Adicione no Icone vermelho ",Toast.LENGTH_LONG).show();
                ib_add.setActivated(true);
                ib_add.setVisibility(View.VISIBLE);
                ib_add.setOnClickListener(view1 -> {
                    Intent intent = new Intent(this, CadastroPacienteActivity.class);
                    startActivity(intent);
                });

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
        ib_add = findViewById(R.id.ib_add);
        ib_add.setActivated(false);
        ib_add.setVisibility(View.INVISIBLE);
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


            String tagClicada = getTag();


            if (tagClicada.equals("DataInicial")) {
                iet_data_nascimento.setText(AppUtil.getDataPicker(iAno, iMes, iDia));
            }


        }
    }

    private void configRecyclerView() {

        rv_paciente.setLayoutManager(new LinearLayoutManager(this));
        rv_paciente.setHasFixedSize(true);
        pessoaAdapter = new PessoaAdapter(consultaList);
        rv_paciente.setAdapter(pessoaAdapter);


    }

    private List<Pessoa> getCpf(String cpf) {

        List<Pessoa> consultas = new ArrayList<>();

        consultas.add(pessoaController.getBuscaPessoaCpf(cpf));

        return consultas;
    }

    private List<Pessoa> getCpf(String nome, String data) {

        List<Pessoa> consultas = new ArrayList<>();

        consultas.add(pessoaController.getBuscaPessoaNomeData(nome, data));

        return consultas;
    }
}