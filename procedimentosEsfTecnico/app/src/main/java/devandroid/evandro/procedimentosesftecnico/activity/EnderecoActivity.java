package devandroid.evandro.procedimentosesftecnico.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import devandroid.evandro.procedimentosesftecnico.R;
import devandroid.evandro.procedimentosesftecnico.dao.EnderecoDAO;
import devandroid.evandro.procedimentosesftecnico.dao.PacienteDAO;
import devandroid.evandro.procedimentosesftecnico.model.Endereco;
import devandroid.evandro.procedimentosesftecnico.model.Paciente;

public class EnderecoActivity extends AppCompatActivity {

    EditText edt_cpf, edt_nome, edt_data_nascimento, edt_endereco, edt_Numero;
    RadioGroup rg_sexo, rg_cor;
    TextView txt_cidade, txt_estado, txt_cep;
    RadioButton rb_masculino, rb_feminino, rb_branco, rb_preto, rb_pardo;
    Spinner sp_logradouro, sp_Bairro;
    Button btn_salvar_dados;

    private Paciente paciente;
    private Endereco endereco;
    private PacienteDAO pacienteDao;
    private EnderecoDAO enderecoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);

        iniciaComponente();
        cliqueBotao();

    }

    private void iniciaComponente() {


        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("CADASTRO DADOS PACIENTE");
        text_toolbar.setTextSize(18);

        pacienteDao = new PacienteDAO(this);
        enderecoDAO = new EnderecoDAO(this);
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


        Spinner();
    }


    private void Spinner() {
        String logradouro[] = {"SELECIONE UM LOGRADOURO", "RUA", "AVENIDA", "RODOVIA"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, logradouro);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item); // The drop down view
        sp_logradouro.setAdapter(spinnerArrayAdapter);


        String Bairro[] = {"SELECIONE UM BAIRRO", "CALAFATE", "VISTA ALEGRE", "BURACO", "MALICIA", "GERALDO LARA", "BELVEDERE", "CHACARA"};
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Bairro);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item); // The drop down view
        sp_Bairro.setAdapter(spinnerArrayAdapter1);


    }

    private void cliqueBotao(){




        btn_salvar_dados.setOnClickListener(view -> {




            //Toast.makeText(this,"Selecionou "+cpf+" "+nome+" " +dn ,Toast.LENGTH_LONG).show();

            String cpf = edt_cpf.getText().toString();
            String nome = edt_nome.getText().toString();
            String dn = edt_data_nascimento.getText().toString();
            String sexo = "Masculino";
            String cor ="branca";
            String lougradouro = sp_logradouro.getSelectedItem().toString();
            String enderec = edt_endereco.getText().toString();
            String numero = edt_Numero.getText().toString();
            String bairro = sp_Bairro.getSelectedItem().toString();
            String cidade = txt_cidade.getText().toString();
            String estado = txt_estado.getText().toString();
            String cep = txt_cep.getText().toString();
            paciente = new Paciente();

            endereco = new Endereco();


            paciente.setCpf(cpf);
            paciente.setNome(nome);
            paciente.setData_nascimento(dn);
            paciente.setSexo(sexo);
            paciente.setCor(cor);

            pacienteDao.salvarPaciente(paciente);

            endereco.setFkCpfEndereco(cpf);
            endereco.setLogradouro(lougradouro);
            endereco.setEndereco(enderec);
            endereco.setNumero(numero);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setEstado(estado);
            endereco.setCep(cep);

            enderecoDAO.salvarEndereco(endereco);




/*
            if(sp_logradouro.getSelectedItemPosition()>0){

                if(sp_Bairro.getSelectedItemPosition()>0){
                    String bairro = sp_Bairro.getSelectedItem().toString();
                    Toast.makeText(this, "você selecionou "+ bairro ,Toast.LENGTH_LONG).show();
                }else{

                    Toast.makeText(this,"Selecione um bairro" ,Toast.LENGTH_LONG).show();

                }
            }else{

                Toast.makeText(this,"Selecione um Logadouro" ,Toast.LENGTH_LONG).show();

            }*/
        });
    }
}