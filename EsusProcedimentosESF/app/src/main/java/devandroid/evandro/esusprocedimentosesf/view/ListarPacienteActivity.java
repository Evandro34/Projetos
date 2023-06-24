package devandroid.evandro.esusprocedimentosesf.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

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
    private ImageButton ib_add,ib_voltar;
    private Button btn_pesquisar;
    private SwipeableRecyclerView rv_paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_paciente);
        pessoaController = new PessoaController(this);

        iniciaComponente();

        acoesdoClique();


    }

    private void acoesdoClique() {

        ib_voltar.setOnClickListener(view -> {
            Intent intent = new Intent(this,ListarProcedimentosActivity.class);
            startActivity(intent);
            finish();
        });
        iv_data_nascimento.setOnClickListener(view -> {
            DialogFragment dialogFragment = new DatePicker();
            dialogFragment.show(getSupportFragmentManager(), "DataInicial");
        });


        btn_pesquisar.setOnClickListener(view -> {

            if (!iet_cpf_cns.getText().toString().isEmpty() && pessoaController.verificaTotalDeRegistroLocalizado(iet_cpf_cns.getText().toString())>0){
                consultaList = getCpf(iet_cpf_cns.getText().toString());
                configRecyclerView();
            } else if (!iet_nome.getText().toString().isEmpty() && !iet_data_nascimento.getText().toString().isEmpty()  && pessoaController.verificaTotalDeRegistroLocalizado(iet_nome.getText().toString(),iet_data_nascimento.getText().toString())>0) {
                consultaList = getCpf(iet_nome.getText().toString(), iet_data_nascimento.getText().toString());
                configRecyclerView();
            } else {


                FancyAlertDialog.Builder
                        .with(this)
                        .setTitle("PESSOA NÃO ENCONTRADA")
                        .setBackgroundColor(Color.parseColor("#303F9F"))  // for @ColorRes use setBackgroundColorRes(R.color.colorvalue)
                        .setMessage("REALIZAR NOVO CADASTRO ?")
                        .setNegativeBtnText("NÃO")
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  // for @ColorRes use setPositiveBtnBackgroundRes(R.color.colorvalue)
                        .setPositiveBtnText("SIM")
                        .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  // for @ColorRes use setNegativeBtnBackgroundRes(R.color.colorvalue)
                        .setAnimation(Animation.POP)
                        .isCancellable(true)
                        .setIcon(R.drawable.ic_add_32, View.VISIBLE)
                        .onPositiveClicked(View -> Alerta())
                        .onNegativeClicked(dialog -> Toast.makeText(ListarPacienteActivity.this, "Cancel", Toast.LENGTH_SHORT).show())
                        .build()
                        .show();


            }


        });
    }

    private void Alerta() {
        Intent intent = new Intent(this, CadastroPacienteActivity.class);
        startActivity(intent);
        finish();
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
        ib_voltar = findViewById(R.id.ib_voltar);
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("PESQUISAR PACIENTE");
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


        rv_paciente.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                int id =0;
                Pessoa pessoa =consultaList.get(position);
                id =pessoa.getIdPessoa();
                Intent intent = new Intent(getBaseContext(),EditarPacienteActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }

            @Override
            public void onSwipedRight(int position) {

                int id =0;
                Pessoa pessoa =consultaList.get(position);
                id =pessoa.getIdPessoa();
                Intent intent = new Intent(getBaseContext(),CadastroProcedimentosActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);


            }
        });



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