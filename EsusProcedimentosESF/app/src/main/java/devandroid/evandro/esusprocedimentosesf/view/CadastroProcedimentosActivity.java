package devandroid.evandro.esusprocedimentosesf.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;

public class CadastroProcedimentosActivity extends AppCompatActivity {

    private TextInputEditText et_cpf, tv_data_atual, tv_nome, tv_data_nascimento, tv_sexo;
    private RadioGroup rg_turno;
    private RadioButton rb_manha, rb_tarde, rb_noite;
    private Spinner sp_local;
    private String sTurno, sLocal;
    private boolean bTurno;
    private CheckBox cb_pressao_arterial, cb_glicemia, cb_nebulizacao, cb_altura, cb_Peso, cb_Temperatura, cb_curativo, cb_r_de_pontos, cb_visita, cb_covid, cb_hep_c, cb_hiv, cb_dengue, cb_hep_b, cb_sifilis;
    private Button  btn_cadastrar_paciente;
    private List<String> procedimentosPaciente = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_procedimentos);

        iniciaComponente();
        cliqueBotao();
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

        btn_cadastrar_paciente.setOnClickListener(view -> {
            Toast.makeText(this,""+validaDados(),Toast.LENGTH_LONG).show();

/*
            if (validaDados()) {


                for (String procedimentos : procedimentosPaciente) {


                    Consulta consulta = new Consulta();
                    consulta.setCnsPaciente(et_cpf.getText().toString());
                    consulta.setData(AppUtil.getDataAtualFormatoAmericanoParaDB(tv_data_atual.getText().toString()));
                    consulta.setTurno(sTurno);
                    consulta.setLocal(sLocal);
                    consulta.setProcedimentos(procedimentos);
                    consultaController.salvarConsulta(consulta);


                }
                Intent intent = new Intent(this, ListarProcedimentoActivity.class);startActivity(intent);



            }
*/


        });
    }

    private boolean validaDados() {
        boolean retorno = true;


        if (!bTurno) {
            retorno = false;
        }

        if (sp_local.getSelectedItemPosition() == 0) {
            retorno = false;
        } else {
            sLocal = sp_local.getSelectedItem().toString();
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

        boolean selecionado = false;

        if (cb_pressao_arterial.isChecked()) {
            procedimentosPaciente.add(AppUtil.PRESSAO_ARTERIAL);
        } else {
            procedimentosPaciente.remove(AppUtil.PRESSAO_ARTERIAL);
        }
        if (cb_glicemia.isChecked()) {
            procedimentosPaciente.add(AppUtil.GLICEMIA);
        } else {
            procedimentosPaciente.remove(AppUtil.GLICEMIA);
        }
        if (cb_nebulizacao.isChecked()) {
            procedimentosPaciente.add(AppUtil.NEBULIZACAO);
        } else {
            procedimentosPaciente.remove(AppUtil.NEBULIZACAO);
        }
        if (cb_altura.isChecked()) {
            procedimentosPaciente.add(AppUtil.ALTURA);
        } else {
            procedimentosPaciente.remove(AppUtil.ALTURA);
        }
        if (cb_Peso.isChecked()) {
            procedimentosPaciente.add(AppUtil.PESO);
        } else {
            procedimentosPaciente.remove(AppUtil.PESO);
        }
        if (cb_Temperatura.isChecked()) {
            procedimentosPaciente.add(AppUtil.TEMPERATURA);
        } else {
            procedimentosPaciente.remove(AppUtil.TEMPERATURA);
        }
        if (cb_curativo.isChecked()) {
            procedimentosPaciente.add(AppUtil.CURATIVO);
        } else {
            procedimentosPaciente.remove(AppUtil.CURATIVO);
        }
        if (cb_r_de_pontos.isChecked()) {
            procedimentosPaciente.add(AppUtil.RETIRADA_PONTO);
        } else {
            procedimentosPaciente.remove(AppUtil.RETIRADA_PONTO);
        }
        if (cb_visita.isChecked()) {
            procedimentosPaciente.add(AppUtil.VISITA);
        } else {
            procedimentosPaciente.remove(AppUtil.VISITA);
        }
        if (cb_covid.isChecked()) {
            procedimentosPaciente.add(AppUtil.COVID);
        } else {
            procedimentosPaciente.remove(AppUtil.COVID);
        }
        if (cb_hep_c.isChecked()) {
            procedimentosPaciente.add(AppUtil.HEPATITE_C);
        } else {
            procedimentosPaciente.remove(AppUtil.HEPATITE_C);
        }
        if (cb_hiv.isChecked()) {
            procedimentosPaciente.add(AppUtil.HIV);
        } else {
            procedimentosPaciente.remove(AppUtil.HIV);
        }
        if (cb_dengue.isChecked()) {
            procedimentosPaciente.add(AppUtil.DENGUE);
        } else {
            procedimentosPaciente.remove(AppUtil.DENGUE);
        }
        if (cb_hep_b.isChecked()) {
            procedimentosPaciente.add(AppUtil.HEPETITE_B);
        } else {
            procedimentosPaciente.remove(AppUtil.HEPETITE_B);
        }
        if (cb_sifilis.isChecked()) {
            procedimentosPaciente.add(AppUtil.SIFILIS);
        } else {
            procedimentosPaciente.remove(AppUtil.SIFILIS);
        }


        if (procedimentosPaciente.size() > 0) {
            selecionado = true;
        } else {
            selecionado = false;
        }
        return selecionado;
    }

}