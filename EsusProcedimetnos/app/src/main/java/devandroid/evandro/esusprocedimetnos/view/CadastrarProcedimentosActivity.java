package devandroid.evandro.esusprocedimetnos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import devandroid.evandro.esusprocedimetnos.R;

public class CadastrarProcedimentosActivity extends AppCompatActivity {

    private Button btn_busca_paciente, btn_cadastrar_procedimentos;

    private TextView tv_data_atual, tv_nome, tv_data_nascimento, tv_sexo;

    private RadioGroup rg_turno;

    private RadioButton rb_manha, rb_tarde, rb_noite;

    private EditText et_cpf;

    private Spinner sp_local;

    private CheckBox cb_pressao_arterial, cb_glicemia, cb_nebulizacao,
            cb_altura, cb_peso, cb_temperatura, cb_curativo,
            cb_r_de_pontos, cb_visita, cb_covid, cb_hep_c, cb_hiv,
            cb_dengue, cb_hep_b, cb_sifilis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_procedimentos);
        iniciaComponente();
        cliqueBotao();
    }

    private void iniciaComponente() {
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("CADASTRO PROCEDIMENTOS");
        text_toolbar.setTextSize(18);

        btn_busca_paciente = findViewById(R.id.btn_buscar_paciente);
        btn_cadastrar_procedimentos = findViewById(R.id.btn_cadastrar_procedimentos);

        tv_data_atual = findViewById(R.id.tv_data_atual);
        tv_nome = findViewById(R.id.tv_data_atual);
        tv_data_nascimento = findViewById(R.id.tv_data_atual);
        tv_sexo = findViewById(R.id.tv_data_atual);

        et_cpf = findViewById(R.id.et_cpf);

        sp_local = findViewById(R.id.sp_local);

        cb_pressao_arterial = findViewById(R.id.cb_pressao_arterial);
        cb_glicemia = findViewById(R.id.cb_glicemia);
        cb_nebulizacao = findViewById(R.id.cb_nebulizacao);
        cb_altura = findViewById(R.id.cb_altura);
        cb_peso = findViewById(R.id.cb_Peso);
        cb_temperatura = findViewById(R.id.cb_Temperatura);
        cb_curativo = findViewById(R.id.cb_curativo);
        cb_r_de_pontos = findViewById(R.id.cb_r_de_pontos);
        cb_visita = findViewById(R.id.cb_visita);
        cb_covid = findViewById(R.id.cb_covid);
        cb_hep_c = findViewById(R.id.cb_hep_c);
        cb_hiv = findViewById(R.id.cb_hiv);
        cb_dengue = findViewById(R.id.cb_dengue);
        cb_hep_b = findViewById(R.id.cb_hep_b);
        cb_sifilis = findViewById(R.id.cb_sifilis);


    }

    private void cliqueBotao() {
        btn_busca_paciente.setOnClickListener(view -> {
            Intent intent = new Intent(this, CadastrarPacienteActivity.class);
            startActivity(intent);
        });
    }
}