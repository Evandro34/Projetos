package devandroid.evandro.esusprocedimentosesf.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.controller.EnderecoController;
import devandroid.evandro.esusprocedimentosesf.controller.PessoaController;
import devandroid.evandro.esusprocedimentosesf.model.Endereco;
import devandroid.evandro.esusprocedimentosesf.model.Pessoa;

public class CadastroPacienteActivity extends AppCompatActivity {

    private static TextInputEditText edt_data_nascimento;
    private TextInputEditText edt_cpf, edt_nome, edt_endereco, edt_Numero;
    private RadioGroup rg_sexo, rg_cor;
    private ImageView iv_data_nascimento;
    private Spinner sp_logradouro, sp_Bairro;

    private TextView txt_cidade, txt_estado, txt_cep;

    private RadioButton rb_masculino, rb_feminino, rb_branco, rb_preto, rb_pardo;
    private Button btn_salvar_dados;
    private String sSexo;
    private boolean bSexo;
    private String sCor;
    private boolean bCor;

    private ImageView ib_voltar;


    private PessoaController pessoaController;
    private EnderecoController enderecoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_paciente);
        iniciaComponente();
        cliqueBotao();
        pessoaController = new PessoaController(this);
        enderecoController= new EnderecoController(this);

        iv_data_nascimento.setOnClickListener(view -> {
            DialogFragment dialogFragment = new CadastroPacienteActivity.DatePicker();
            dialogFragment.show(getSupportFragmentManager(), "DataInicial");
        });

    }

    private void iniciaComponente() {
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("CADASTRO DADOS PACIENTE");
        text_toolbar.setTextSize(18);

        ib_voltar = findViewById(R.id.ib_voltar);
        edt_cpf = findViewById(R.id.edt_cpf_endereco);
        edt_nome = findViewById(R.id.edt_nome_endereco);
        edt_data_nascimento = findViewById(R.id.edt_data_nascimento_endereco);
        edt_endereco = findViewById(R.id.edt_endereco);
        edt_Numero = findViewById(R.id.edt_Numero);
        iv_data_nascimento = findViewById(R.id.iv_data_nascimento);
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


        String Bairro[] = {"SELECIONE UM BAIRRO", "CALAFATE", "VISTA ALEGRE", "BURACO", "MALICIA", "GERALDO LARA", "BELVEDERE", "CHACARA", "ESTIVA", "CENTRO", "TIJUCO"};
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Bairro);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_Bairro.setAdapter(spinnerArrayAdapter1);


    }

    private void cliqueBotao() {
        ib_voltar.setOnClickListener(view -> {
            Intent intent = new Intent(this,ListarPacienteActivity.class);
            startActivity(intent);
            finish();
        });

        btn_salvar_dados.setOnClickListener(view -> {


            if(validaDados()) {
                Pessoa paciente = new Pessoa();
                Endereco endereco = new Endereco();
                paciente.setCpf(edt_cpf.getText().toString());
                paciente.setNome(edt_nome.getText().toString());
                paciente.setData_nascimento(edt_data_nascimento.getText().toString());
                paciente.setSexo(sSexo);
                paciente.setCor(sCor);

                pessoaController.salvarPessoa(paciente);
                Log.i(AppUtil.LOG_APP, "id == >" + pessoaController.getLastPK());
                endereco.setFkidPessoaEndereco(pessoaController.getLastPK());
                endereco.setLogradouro( sp_logradouro.getSelectedItem().toString());
                Log.i(AppUtil.LOG_APP, "logradouro " + sp_logradouro.getSelectedItem().toString());
                endereco.setEndereco(edt_endereco.getText().toString());
                Log.i(AppUtil.LOG_APP, "endereco " + edt_endereco.getText().toString());
                endereco.setNumero(edt_Numero.getText().toString());
                Log.i(AppUtil.LOG_APP, "numero " + edt_Numero.getText().toString());
                endereco.setBairro(sp_Bairro.getSelectedItem().toString());
                Log.i(AppUtil.LOG_APP, "bairro " + sp_Bairro.getSelectedItem().toString());
                endereco.setCidade(txt_cidade.getText().toString());
                Log.i(AppUtil.LOG_APP, "cidade  " + txt_cidade.getText().toString());
                endereco.setEstado(txt_estado.getText().toString());
                Log.i(AppUtil.LOG_APP, "estado" + txt_estado.getText().toString());
                endereco.setCep(txt_cep.getText().toString());
                Log.i(AppUtil.LOG_APP, "cep " + txt_cep.getText().toString());
                enderecoController.salvarEndereco(endereco);

                Intent intent = new Intent(this, CadastroProcedimentosActivity.class);
                intent.putExtra("id",pessoaController.getLastPK());
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
        }

        return retorno;
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
               edt_data_nascimento.setText(AppUtil.getDataPicker(iAno,iMes,iDia));
            }


        }
    }
}