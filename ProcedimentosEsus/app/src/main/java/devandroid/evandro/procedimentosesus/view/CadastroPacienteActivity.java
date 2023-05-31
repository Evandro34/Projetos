package devandroid.evandro.procedimentosesus.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import devandroid.evandro.procedimentosesus.R;
import devandroid.evandro.procedimentosesus.controller.EnderecoController;
import devandroid.evandro.procedimentosesus.controller.PacienteController;
import devandroid.evandro.procedimentosesus.model.Endereco;
import devandroid.evandro.procedimentosesus.model.Paciente;

public class CadastroPacienteActivity extends AppCompatActivity {

    private EditText edt_cpf, edt_nome, edt_data_nascimento, edt_endereco, edt_Numero;
    private RadioGroup rg_sexo, rg_cor;
    private TextView txt_cidade, txt_estado, txt_cep;
    private RadioButton rb_masculino, rb_feminino, rb_branco, rb_preto, rb_pardo;
    private Spinner sp_logradouro, sp_Bairro;
    private Button btn_salvar_dados;

    private String sCpf;
    private String sNome;
    private String sDn;
    private String sSexo;
    private boolean bSexo;
    private String sCor;
    private boolean bCor;
    private String sLougradouro;
    private String sEndereco;
    private String sNumero;
    private String sBairro;
    private String sCidade;
    private String sEstado;
    private String sCep;

    private PacienteController pacienteController;
    private EnderecoController enderecoController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_paciente);

        pacienteController = new PacienteController(this);
        enderecoController = new EnderecoController(this);

        iniciaComponente();
        cliqueBotao();
    }


    private void iniciaComponente() {
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("CADASTRO DADOS PACIENTE");
        text_toolbar.setTextSize(18);


        edt_cpf = findViewById(R.id.edt_cpf_endereco);
        edt_nome = findViewById(R.id.edt_nome_endereco);
        edt_data_nascimento = findViewById(R.id.edt_data_nascimento_endereco);
        edt_endereco = findViewById(R.id.edt_endereco);
        edt_Numero = findViewById(R.id.edt_Numero);

        rg_sexo = findViewById(R.id.rg_sexo);
        rg_cor = findViewById(R.id.rg_cor);

        txt_cidade = findViewById(R.id.txt_cidade);
        txt_estado = findViewById(R.id.txt_estado);
        txt_cep = findViewById(R.id.txt_cep);

        rb_branco = findViewById(R.id.rb_branco);
        rb_pardo = findViewById(R.id.rb_pardo);
        rb_preto = findViewById(R.id.rb_preto);
        rb_masculino = findViewById(R.id.rb_masculino);
        rb_feminino = findViewById(R.id.rb_feminino);

        sp_logradouro = findViewById(R.id.sp_logradouro);
        sp_Bairro = findViewById(R.id.sp_Bairro);

        btn_salvar_dados = findViewById(R.id.btn_salvar_dados);

        rg_sexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                bSexo = false;
                boolean masculino = R.id.rb_masculino == i;
                boolean feminino = R.id.rb_feminino == i;

                if (masculino) {
                    sSexo = "masculino";
                    bSexo = true;
                } else if (feminino) {
                    sSexo = "feminino";
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
                    sCor = "branco";
                    bCor = true;
                }
                if (preto) {
                    sCor = "preto";
                    bCor = true;
                }
                if (pardo) {
                    sCor = "pardo";
                    bCor = true;
                }
            }
        });


        Spinner();
    }


    private void Spinner() {
        String logradouro[] = {"SELECIONE UM LOGRADOURO",
                "Aeroporto",
                "Alameda",
                "Área",
                "Avenida",
                "Campo",
                "Chácara",
                "Colônia",
                "Condomínio",
                "Conjunto",
                "Distrito",
                "Esplanada",
                "Estação",
                "Estrada",
                "Favela",
                "Fazenda",
                "Feira",
                "Jardim",
                "Ladeira",
                "Lago",
                "Lagoa",
                "Largo",
                "Loteamento",
                "Morro",
                "Núcleo",
                "Parque",
                "Passarela",
                "Pátio",
                "Praça",
                "Quadra",
                "Recanto",
                "Residencial",
                "Rodovia",
                "Rua",
                "Setor",
                "Sítio",
                "Travessa",
                "Trecho",
                "Trevo",
                "Vale",
                "Vereda",
                "Via",
                "Viaduto",
                "Viela",
                "Vila"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, logradouro);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_logradouro.setAdapter(spinnerArrayAdapter);


        String Bairro[] = {"SELECIONE UM BAIRRO", "CALAFATE", "VISTA ALEGRE", "BURACO", "MALICIA", "GERALDO LARA", "BELVEDERE", "CHACARA","ESTIVA","CENTRO","TIJUCO"};
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Bairro);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_Bairro.setAdapter(spinnerArrayAdapter1);


    }

    private void cliqueBotao() {


        btn_salvar_dados.setOnClickListener(view -> {

            if (validaDados()) {

                Paciente paciente = new Paciente();
                Endereco endereco = new Endereco();
                paciente.setCpf(edt_cpf.getText().toString());
                paciente.setNome(edt_nome.getText().toString());
                paciente.setData_nascimento(edt_data_nascimento.getText().toString());
                paciente.setSexo(sSexo);
                paciente.setCor(sCor);
                endereco.setFkCpfEndereco(edt_cpf.getText().toString());
                endereco.setLogradouro(sLougradouro);
                endereco.setEndereco(edt_endereco.getText().toString());
                endereco.setNumero(edt_Numero.getText().toString());
                endereco.setBairro(sBairro);
                endereco.setCidade(txt_cidade.getText().toString());
                endereco.setEstado(txt_estado.getText().toString());
                endereco.setCep(txt_cep.getText().toString());
                pacienteController.salvarPaciente(paciente);
                enderecoController.salvarEndereco(endereco);

                Intent intent = new Intent(this, CadastroProcedimentosActivity.class);
                startActivity(intent);

            }


        });
    }

    private boolean validaDados() {


        boolean retorno = true;
        if (TextUtils.isEmpty(edt_cpf.getText().toString())) {
            edt_cpf.setError("*");
            edt_cpf.requestFocus();
            retorno = false;
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
        if (sp_logradouro.getSelectedItemPosition() == 0) {
            retorno = false;
        } else {
            sLougradouro = sp_logradouro.getSelectedItem().toString();
        }
        if (TextUtils.isEmpty(edt_endereco.getText().toString())) {
            edt_endereco.setError("*");
            edt_endereco.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(edt_Numero.getText().toString())) {
            edt_Numero.setError("*");
            edt_Numero.requestFocus();
            retorno = false;
        }

        if (sp_Bairro.getSelectedItemPosition() == 0) {
            retorno = false;
        } else {
            sBairro = sp_Bairro.getSelectedItem().toString();
        }

        return retorno;
    }
}