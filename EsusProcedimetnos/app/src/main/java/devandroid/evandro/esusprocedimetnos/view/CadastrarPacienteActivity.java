package devandroid.evandro.esusprocedimetnos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import devandroid.evandro.esusprocedimetnos.R;

public class CadastrarPacienteActivity extends AppCompatActivity {

    private EditText et_cpf, et_nome, et_data_nascimento, et_endereco, et_Numero;
    private TextView tv_cidade, tv_estado, tv_cep;
    private RadioGroup rg_sexo, rg_cor;
    private Button btn_salvar_dados;
    private Spinner sp_logradouro, sp_Bairro;
    private RadioButton rb_masculino, rb_feminino, rb_preto, rb_pardo, rb_branco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_paciente);

        iniciaComponente();
    }

    private void iniciaComponente() {
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("CADASTRO PACIENTE");
        text_toolbar.setTextSize(18);


        et_cpf = findViewById(R.id.et_cpf);
        et_nome = findViewById(R.id.et_nome);
        et_data_nascimento = findViewById(R.id.et_data_nascimento);
        et_endereco = findViewById(R.id.et_endereco);
        et_Numero = findViewById(R.id.et_Numero);

        tv_cidade = findViewById(R.id.tv_cidade);
        tv_estado = findViewById(R.id.tv_estado);
        tv_cep = findViewById(R.id.tv_cep);

        rg_sexo = findViewById(R.id.rg_sexo);
        rg_cor = findViewById(R.id.rg_cor);

        btn_salvar_dados = findViewById(R.id.btn_salvar_dados);

        sp_logradouro = findViewById(R.id.sp_logradouro);
        sp_Bairro = findViewById(R.id.sp_Bairro);

        rb_masculino = findViewById(R.id.rb_masculino);
        rb_feminino = findViewById(R.id.rb_feminino);
        rb_preto = findViewById(R.id.rb_preto);
        rb_pardo = findViewById(R.id.rb_pardo);
        rb_branco = findViewById(R.id.rb_branco);


    }
}