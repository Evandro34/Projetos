package devandroid.evandro.esusprocedimentosesf.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.controller.PessoaController;
import devandroid.evandro.esusprocedimentosesf.model.Pessoa;

public class EditarPacienteActivity extends AppCompatActivity {

    private static TextInputEditText edt_data_nascimento;
    private TextInputEditText edt_cpf, edt_cns, edt_nome;

    private RadioGroup rg_sexo, rg_cor;

    private RadioButton rb_masculino, rb_feminino, rb_branco, rb_preto, rb_pardo;

    private Button btn_atualizar;
    private String sSexo;
    private boolean bSexo;

    private String sCor, sCpf, sCns;
    private boolean bCor;
    int idPaciente = 0;
    private List<Pessoa> dados = new ArrayList<>();
    private ImageView iv_data_nascimento;

    private PessoaController pessoaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_paciente);

        pessoaController = new PessoaController(this);
        iniciaComponente();
        recuperDadosPaciente();

    }

    private void iniciaComponente() {


        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("EDITAR DADOS PACIENTE");
        text_toolbar.setTextSize(18);

        edt_cpf = findViewById(R.id.edt_cpf_endereco);
        edt_cns = findViewById(R.id.edt_cns_endereco);
        edt_nome = findViewById(R.id.edt_nome_endereco);
        edt_data_nascimento = findViewById(R.id.edt_data_nascimento_endereco);
        iv_data_nascimento = findViewById(R.id.iv_data_nascimento);
        rg_sexo = findViewById(R.id.rg_sexo);
        rg_cor = findViewById(R.id.rg_cor);

        rb_branco = findViewById(R.id.rb_branco);
        rb_pardo = findViewById(R.id.rb_pardo);
        rb_preto = findViewById(R.id.rb_preto);
        rb_masculino = findViewById(R.id.rb_masculino);
        rb_feminino = findViewById(R.id.rb_feminino);


        btn_atualizar = findViewById(R.id.btn_atualizar);


        btn_atualizar.setOnClickListener(view -> {



            if (validaDados()){
                Pessoa pessoa = new Pessoa();

                pessoa.setIdPessoa(idPaciente);
                pessoa.setCpf(sCpf);
                pessoa.setCns(sCns);
                pessoa.setNome(edt_nome.getText().toString());
                pessoa.setData_nascimento(edt_data_nascimento.getText().toString());
                pessoa.setSexo(sSexo);
                pessoa.setCor(sCor);

                pessoaController.atualizaDados(pessoa);

                Intent intent = new Intent(this,ListarPacienteActivity.class);
                startActivity(intent);
                finish();

            }
        });

        rg_sexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                bSexo = false;
                boolean masculino = R.id.rb_masculino == i;
                boolean feminino = R.id.rb_feminino == i;

                if (masculino) {
                    sSexo = AppUtil.MASCULINO;
                    bSexo = true;
                } else if (feminino) {
                    sSexo = AppUtil.FEMININO;
                    bSexo = true;
                }
            }
        });
        rg_cor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                bCor = false;
                boolean branco = R.id.rb_branco == i;
                boolean preto = R.id.rb_preto == i;
                boolean pardo = R.id.rb_pardo == i;

                if (branco) {
                    sCor = AppUtil.BRANCA;
                    bCor = true;
                }
                if (preto) {
                    sCor = AppUtil.PRETA;
                    bCor = true;
                }
                if (pardo) {
                    sCor = AppUtil.PARDA;
                    bCor = true;
                }
            }
        });


    }

    private boolean validaDados() {


        boolean retorno = true;

        if (!AppUtil.isCPF(edt_cpf.getText().toString().trim())) {
            edt_cpf.setError("*");
            edt_cpf.requestFocus();
            retorno = false;
        } else {
            sCpf = edt_cpf.getText().toString();
        }
        if (!AppUtil.isCns(edt_cns.getText().toString().trim())) {
            edt_cns.setError("*");
            edt_cns.requestFocus();
            retorno = false;
        } else {
            sCns = edt_cns.getText().toString();
        }
        if (TextUtils.isEmpty(edt_nome.getText().toString())) {
            edt_nome.setError("*");
            edt_nome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(edt_data_nascimento.getText().toString())) {
            edt_data_nascimento.setError("*");
            edt_data_nascimento.requestFocus();
            retorno = false;
        }

        if (!bSexo) {
            retorno = false;
        }

        if (!bCor) {
            retorno = false;
        }


        return retorno;
    }

    private void recuperDadosPaciente() {
        Bundle bundle = getIntent().getExtras();

        idPaciente = bundle.getInt("id");
        dados.add(pessoaController.getBuscaPessoaId(idPaciente));

        for (Pessoa pessoa1 : dados) {
            String cor = pessoa1.getCor();

            Log.i(AppUtil.LOG_APP,"COR "+ cor);
            Log.i(AppUtil.LOG_APP,"COR "+ AppUtil.BRANCA);
            edt_nome.setText(pessoa1.getNome());
            edt_data_nascimento.setText(pessoa1.getData_nascimento());
            edt_cpf.setText(pessoa1.getCpf());
            if (cor.equals(AppUtil.BRANCA)) {
                rb_branco.setChecked(true);

            } else if (cor.equals(AppUtil.PARDA)) {

                rb_pardo.setChecked(true);

            } else if (cor.equals(AppUtil.PRETA)) {

                rb_preto.setChecked(true);
            }
            if (pessoa1.getSexo().equals(AppUtil.MASCULINO)) {
                rb_masculino.setChecked(true);

            } else if (pessoa1.getSexo().equals(AppUtil.FEMININO)) {
                rb_feminino.setChecked(true);
            }
            edt_cns.setText(pessoa1.getCns());
        }
    }


}