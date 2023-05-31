package devandroid.evandro.procedimentosesus.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.procedimentosesus.R;
import devandroid.evandro.procedimentosesus.api.AppUtil;
import devandroid.evandro.procedimentosesus.controller.ConsultaController;
import devandroid.evandro.procedimentosesus.fragments.ManhaFragment;
import devandroid.evandro.procedimentosesus.fragments.NoiteFragment;
import devandroid.evandro.procedimentosesus.fragments.TardeFragment;
import devandroid.evandro.procedimentosesus.model.Consulta;

public class CadastroProcedimentosActivity extends AppCompatActivity {

    private TextView tv_data_atual, tv_nome, tv_data_nascimento, tv_sexo;
    private RadioGroup rg_turno;
    private RadioButton rb_manha, rb_tarde, rb_noite;
    private EditText et_cpf;
    private Spinner sp_local;

    private String sTurno, sLocal;
    private boolean bTurno;

    private CheckBox cb_pressao_arterial, cb_glicemia, cb_nebulizacao, cb_altura, cb_Peso, cb_Temperatura, cb_curativo, cb_r_de_pontos, cb_visita, cb_covid, cb_hep_c, cb_hiv, cb_dengue, cb_hep_b, cb_sifilis;
    private Button btn_busca_paciente, btn_cadastrar_paciente;

    private ConsultaController consultaController;

    private List<String> proc;
    private List<String> proc1 = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_procedimentos);

        consultaController = new ConsultaController(this);

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
                    sTurno = "manha";
                    bTurno = true;
                }
                if (tarde) {
                    sTurno = "tarde";
                    bTurno = true;
                }
                if (noite) {
                    sTurno = "noite";
                    bTurno = true;
                }
            }
        });

        btn_busca_paciente = findViewById(R.id.btn_buscar_paciente);
        btn_cadastrar_paciente = findViewById(R.id.btn_cadastrar_paciente);

        Spinner();
        validaCheckbox1();


    }

    private void cliqueBotao() {
        btn_busca_paciente.setOnClickListener(view -> {
            Intent intent = new Intent(this, CadastroPacienteActivity.class);
            startActivity(intent);
        });
        btn_cadastrar_paciente.setOnClickListener(view -> {

            
            if (validaDados()) {

                Log.i("TESTE", "tamanho " + proc1.size());
                for (String procedimentos : proc1) {

                    Consulta consulta = new Consulta();
                    consulta.setCnsPaciente(et_cpf.getText().toString());
                    consulta.setData(tv_data_atual.getText().toString());
                    consulta.setTurno(sTurno);
                    consulta.setLocal(sLocal);
                    consulta.setProcedimentos(procedimentos);
                    consultaController.salvarConsulta(consulta);

                }
                Intent intent = new Intent(this, ListarProcedimentoActivity.class);startActivity(intent);



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
        } else {
            sLocal = sp_local.getSelectedItem().toString();
        }

        if (TextUtils.isEmpty(et_cpf.getText().toString())) {
            et_cpf.setError("*");
            et_cpf.requestFocus();
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

    private boolean validaCheckbox() {

        proc = new ArrayList<>();
        boolean selecionado = false;

        cb_pressao_arterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                Log.i("TESTE", " selecionado" + selecionou);
                if (selecionou) {
                    proc.add("Pressao Arterial");
                } else {
                    proc.remove("Pressao Arterial");
                }
            }
        });
        cb_glicemia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Glicemia");
                } else {
                    proc.remove("Glicemia");
                }
            }
        });
        cb_nebulizacao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Nebulizacao");
                } else {
                    proc.remove("Nebulizacao");
                }
            }
        });
        cb_altura.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Altura");
                } else {
                    proc.remove("Altura");
                }
            }
        });
        cb_Peso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Peso");
                } else {
                    proc.remove("Peso");
                }
            }
        });
        cb_Temperatura.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Temperatura");
                } else {
                    proc.remove("Tempertatura");
                }
            }
        });
        cb_curativo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Curativo");
                } else {
                    proc.remove("Curativo");
                }
            }
        });
        cb_r_de_pontos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Retirada de Pontos");
                } else {
                    proc.remove("Reetirada de Pontos");
                }
            }
        });
        cb_visita.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Visita");
                } else {
                    proc.remove("Visita");
                }
            }
        });
        cb_pressao_arterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Pressao Arterial");
                } else {
                    proc.remove("Pressao Arterial");
                }
            }
        });
        cb_covid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Covid");
                } else {
                    proc.remove("Covid");
                }
            }
        });
        cb_hep_c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Hepatite c");
                } else {
                    proc.remove("Hepatite c");
                }
            }
        });
        cb_hiv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("HIV");
                } else {
                    proc.remove("HIV");
                }
            }
        });
        cb_dengue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("dengue");
                } else {
                    proc.remove("dengue");
                }
            }
        });
        cb_hep_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Hepatite b");
                } else {
                    proc.remove("Hepatite b");
                }
            }
        });
        cb_sifilis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selecionou) {
                if (selecionou) {
                    proc.add("Sifilis");
                } else {
                    proc.remove("Sifilis");
                }
            }
        });

        Log.i("TESTE", "tamanho " + proc.size());

        if (proc.size() == 0) {
            selecionado = false;
        } else {
            selecionado = true;
        }
        return selecionado;
    }

    private boolean validaCheckbox1() {

        boolean selecionado = false;

        if (cb_pressao_arterial.isChecked()) {
            proc1.add("Pressao Arterial");
        } else {
            proc1.remove("Pressao Arterial");
        }
        if (cb_glicemia.isChecked()) {
            proc1.add("Glicemia");
        } else {
            proc1.remove("Glicemia");
        }
        if (cb_nebulizacao.isChecked()) {
            proc1.add("Nebulizacao");
        } else {
            proc1.remove("Nebulizacao");
        }
        if (cb_altura.isChecked()) {
            proc1.add("Altura");
        } else {
            proc1.remove("Altura");
        }
        if (cb_Peso.isChecked()) {
            proc1.add("Peso");
        } else {
            proc1.remove("Peso");
        }
        if (cb_Temperatura.isChecked()) {
            proc1.add("Temperatura");
        } else {
            proc1.remove("Temperatura");
        }
        if (cb_curativo.isChecked()) {
            proc1.add("Curativo");
        } else {
            proc1.remove("Curativo");
        }
        if (cb_r_de_pontos.isChecked()) {
            proc1.add("Retirada De Ponto");
        } else {
            proc1.remove("Retirada De Ponto");
        }
        if (cb_visita.isChecked()) {
            proc1.add("Visita");
        } else {
            proc1.remove("Visita");
        }
        if (cb_covid.isChecked()) {
            proc1.add("Covid");
        } else {
            proc1.remove("Covid");
        }
        if (cb_hep_c.isChecked()) {
            proc1.add("Hepatite C");
        } else {
            proc1.remove("Hepatite C");
        }
        if (cb_hiv.isChecked()) {
            proc1.add("HIV");
        } else {
            proc1.remove("HIV");
        }
        if (cb_dengue.isChecked()) {
            proc1.add("Dengue");
        } else {
            proc1.remove("Dengue");
        }
        if (cb_hep_b.isChecked()) {
            proc1.add("Hepatite B");
        } else {
            proc1.remove("Hepatite B");
        }
        if (cb_sifilis.isChecked()) {
            proc1.add("Sifilis");
        } else {
            proc1.remove("Sifilis");
        }


        if (proc1.size() > 0) {
            selecionado = true;
        } else {
            selecionado = false;
        }
        return selecionado;
    }
}