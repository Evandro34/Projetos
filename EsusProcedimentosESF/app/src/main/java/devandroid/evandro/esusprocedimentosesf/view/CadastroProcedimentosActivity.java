package devandroid.evandro.esusprocedimentosesf.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.controller.ConsultaController;
import devandroid.evandro.esusprocedimentosesf.controller.PessoaController;

import devandroid.evandro.esusprocedimentosesf.model.Consulta;
import devandroid.evandro.esusprocedimentosesf.model.Pessoa;

public class CadastroProcedimentosActivity extends AppCompatActivity {

    private TextInputEditText et_cpf, tv_data_atual, tv_nome, tv_data_nascimento, tv_sexo;
    private RadioGroup rg_turno;
    private PessoaController pessoaController;
    private ConsultaController consultaController;
    private RadioButton rb_manha, rb_tarde, rb_noite;
    private Spinner sp_local;
    private ImageView ib_voltar;

    int idPaciente = 0;
    private String sTurno, sLocal;
    private boolean bTurno;
    private CheckBox cb_pressao_arterial, cb_glicemia,
            cb_nebulizacao, cb_altura, cb_Peso, cb_Temperatura,
            cb_curativo, cb_r_de_pontos,
            cb_visita, cb_covid,
            cb_hep_c,
            cb_hiv,
            cb_dengue,
            cb_hep_b,
            cb_sifilis;
    private Button btn_cadastrar_paciente;
    private List<String> procedimentoPessoa ;
    private List<Pessoa> dados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_procedimentos);

        pessoaController = new PessoaController(this);
        consultaController = new ConsultaController(this);


        iniciaComponente();
        recuperDadosPaciente();
        cliqueBotao();

    }

    private void recuperDadosPaciente() {
        Bundle bundle = getIntent().getExtras();

        idPaciente = bundle.getInt("id");
        dados.add(pessoaController.getBuscaPessoaId(idPaciente));

        for (Pessoa pessoa1 : dados) {
            tv_nome.setText(pessoa1.getNome());
            tv_data_nascimento.setText(pessoa1.getData_nascimento());
            tv_sexo.setText(pessoa1.getSexo());
            et_cpf.setText(pessoa1.getCpf());
        }
    }

    private void iniciaComponente() {
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("CADASTRO PROCEDIMENTOS");
        text_toolbar.setTextSize(18);


        tv_nome = findViewById(R.id.tv_nome);
        tv_data_atual = findViewById(R.id.tv_data_atual);
        tv_data_atual.setText(AppUtil.getDataAtual());
        tv_data_nascimento = findViewById(R.id.tv_data_nascimento);
        tv_sexo = findViewById(R.id.tv_sexo);

        rg_turno = findViewById(R.id.rg_turno);

        rb_manha = findViewById(R.id.rb_manha);
        rb_tarde = findViewById(R.id.rb_tarde);
        rb_noite = findViewById(R.id.rb_noite);

        et_cpf = findViewById(R.id.et_cpf);

        sp_local = findViewById(R.id.sp_local);

        cb_pressao_arterial = findViewById(R.id.cb_pressao_arterial);
        cb_glicemia = findViewById(R.id.cb_glicemia);
        cb_nebulizacao = findViewById(R.id.cb_nebulizacao);
        cb_altura = findViewById(R.id.cb_altura);
        cb_Peso = findViewById(R.id.cb_Peso);
        cb_Temperatura = findViewById(R.id.cb_Temperatura);
        cb_curativo = findViewById(R.id.cb_curativo);
        cb_r_de_pontos = findViewById(R.id.cb_r_de_pontos);
        cb_visita = findViewById(R.id.cb_visita);
        cb_covid = findViewById(R.id.cb_covid);
        cb_hep_c = findViewById(R.id.cb_hep_c);
        cb_hiv = findViewById(R.id.cb_hiv);
        cb_dengue = findViewById(R.id.cb_dengue);
        cb_hep_b = findViewById(R.id.cb_hep_b);
        cb_sifilis = findViewById(R.id.cb_sifilis);
        ib_voltar = findViewById(R.id.ib_voltar);

        rg_turno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                bTurno = false;
                boolean manha = R.id.rb_manha == i;
                boolean tarde = R.id.rb_tarde == i;
                boolean noite = R.id.rb_noite == i;

                if (manha) {
                    sTurno = AppUtil.MANHA;
                    bTurno = true;
                }
                if (tarde) {
                    sTurno = AppUtil.TARDE;
                    bTurno = true;
                }
                if (noite) {
                    sTurno = AppUtil.NOITE;
                    bTurno = true;
                }
            }
        });


        btn_cadastrar_paciente = findViewById(R.id.btn_cadastrar_paciente);

        Spinner();
        validaCheckbox1();


    }
    private void cliqueBotao() {

        ib_voltar.setOnClickListener(view -> {
            Intent intent = new Intent(this,ListarPacienteActivity.class);
            startActivity(intent);
            finish();
        });

        btn_cadastrar_paciente.setOnClickListener(view -> {
            Toast.makeText(this,""+validaDados(),Toast.LENGTH_LONG).show();


            if (validaDados()) {


                for (String procedimentosDia : procedimentoPessoa) {

                    Log.i(AppUtil.LOG_APP,"Procedimentos"+procedimentosDia);
                    Consulta procedimentos = new Consulta();

                    procedimentos.setFkidPessoaConsulta(idPaciente);
                    procedimentos.setData(AppUtil.getDataAtualFormatoAmericanoParaDB(tv_data_atual.getText().toString()));
                    procedimentos.setTurno(sTurno);
                    procedimentos.setLocal(sp_local.getSelectedItem().toString());
                    procedimentos.setProcedimentos(procedimentosDia);
                    consultaController.salvarConsulta(procedimentos);
                }
                Intent intent = new Intent(this, ListarProcedimentosActivity.class);
                startActivity(intent);


            }


        });
    }

    private boolean validaDados() {
        boolean retorno = true;


        if (!bTurno) {
            retorno = false;
        }

        if (sp_local.getSelectedItemPosition() == 0) {
            retorno = false;
        }

        if (!validaCheckbox1()) {
            retorno = false;
        }


        return retorno;

    }

    private void Spinner() {
        String local[] = {"SELECIONE LOCAL DE ATENDIMENTO", "01-UBS", "02-Unidade Móvel", "03-Rua", "04-Domicilio", "05-Escola/Creche", "06-Outros", "07-Polo(Academia Da Saúde)", "08-Intituição/ Abrigo", "09-Unidade prisional ou congêneres", "10-Unidade socioeducativa"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, local);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_local.setAdapter(spinnerArrayAdapter);

    }

    private boolean validaCheckbox1() {
        procedimentoPessoa = new ArrayList<>();

        boolean selecionado = false;

        if (cb_pressao_arterial.isChecked()) {
            procedimentoPessoa.add(AppUtil.PRESSAO_ARTERIAL);
        } else {
            procedimentoPessoa.remove(AppUtil.PRESSAO_ARTERIAL);
        }
        if (cb_glicemia.isChecked()) {
            procedimentoPessoa.add(AppUtil.GLICEMIA);
        } else {
            procedimentoPessoa.remove(AppUtil.GLICEMIA);
        }
        if (cb_nebulizacao.isChecked()) {
            procedimentoPessoa.add(AppUtil.NEBULIZACAO);
        } else {
            procedimentoPessoa.remove(AppUtil.NEBULIZACAO);
        }
        if (cb_altura.isChecked()) {
            procedimentoPessoa.add(AppUtil.ALTURA);
        } else {
            procedimentoPessoa.remove(AppUtil.ALTURA);
        }
        if (cb_Peso.isChecked()) {
            procedimentoPessoa.add(AppUtil.PESO);
        } else {
            procedimentoPessoa.remove(AppUtil.PESO);
        }
        if (cb_Temperatura.isChecked()) {
            procedimentoPessoa.add(AppUtil.TEMPERATURA);
        } else {
            procedimentoPessoa.remove(AppUtil.TEMPERATURA);
        }
        if (cb_curativo.isChecked()) {
            procedimentoPessoa.add(AppUtil.CURATIVO);
        } else {
            procedimentoPessoa.remove(AppUtil.CURATIVO);
        }
        if (cb_r_de_pontos.isChecked()) {
            procedimentoPessoa.add(AppUtil.RETIRADA_PONTO);
        } else {
            procedimentoPessoa.remove(AppUtil.RETIRADA_PONTO);
        }
        if (cb_visita.isChecked()) {
            procedimentoPessoa.add(AppUtil.VISITA);
        } else {
            procedimentoPessoa.remove(AppUtil.VISITA);
        }
        if (cb_covid.isChecked()) {
            procedimentoPessoa.add(AppUtil.COVID);
        } else {
            procedimentoPessoa.remove(AppUtil.COVID);
        }
        if (cb_hep_c.isChecked()) {
            procedimentoPessoa.add(AppUtil.HEPATITE_C);
        } else {
            procedimentoPessoa.remove(AppUtil.HEPATITE_C);
        }
        if (cb_hiv.isChecked()) {
            procedimentoPessoa.add(AppUtil.HIV);
        } else {
            procedimentoPessoa.remove(AppUtil.HIV);
        }
        if (cb_dengue.isChecked()) {
            procedimentoPessoa.add(AppUtil.DENGUE);
        } else {
            procedimentoPessoa.remove(AppUtil.DENGUE);
        }
        if (cb_hep_b.isChecked()) {
            procedimentoPessoa.add(AppUtil.HEPETITE_B);
        } else {
            procedimentoPessoa.remove(AppUtil.HEPETITE_B);
        }
        if (cb_sifilis.isChecked()) {
            procedimentoPessoa.add(AppUtil.SIFILIS);
        } else {
            procedimentoPessoa.remove(AppUtil.SIFILIS);
        }

        if (procedimentoPessoa.size() > 0) {
            selecionado = true;
        } else {
            selecionado = false;
        }
        return selecionado;
    }

}