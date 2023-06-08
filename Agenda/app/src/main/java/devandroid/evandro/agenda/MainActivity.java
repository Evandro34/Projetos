package devandroid.evandro.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.agenda.DAO.DAO;
import devandroid.evandro.agenda.adapter.RecyclerViewAdapter;
import devandroid.evandro.agenda.objetos.Pessoa;

public class MainActivity extends AppCompatActivity {

    private EditText et_nome, et_sexo, et_idade;
    private Switch swSexo;
    private Button bt_salvar;
    private Context context;
    private RecyclerView recyclerView;
    LinearLayout linearLayout;
    RecyclerView.Adapter recylerViewadapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        iniciaComponente();

        buscaNoBanco();

        bt_salvar.setOnClickListener(view -> {


            if (!(et_nome.getText().toString().equals("") || et_idade.getText().toString().equals(""))) {

                String sexo;

                if (swSexo.isChecked()) {
                    sexo = "F";
                } else {
                    sexo = "M";
                }

                DAO dao = new DAO(getApplicationContext());

                Pessoa pessoa = new Pessoa();


                pessoa.setNome(et_nome.getText().toString());
                pessoa.setSexo(sexo);
                pessoa.setIdade(Integer.valueOf(et_idade.getText().toString()));

                dao.inserePessoa(pessoa);
                dao.close();

                limpaFormulario();

                buscaNoBanco();


            } else {
                Toast.makeText(this, "Por favor preencha todos os campos", Toast.LENGTH_LONG).show();
            }

        });

    }

    private void buscaNoBanco() {
        DAO dao2 = new DAO(this);

        List<Pessoa> pessoas = dao2.buscarPessoa();
        List<String> nomes = new ArrayList<String>();
        List<String> idades = new ArrayList<String>();


        String[] dados_nomes = new String[]{};
        String[] dados_idades = new String[]{};

        for (Pessoa nomeBuscado : pessoas) {
            nomes.add(nomeBuscado.getNome());
            idades.add(String.valueOf(nomeBuscado.getIdade()));
        }


        dados_nomes = nomes.toArray(new String[0]);
        dados_idades = idades.toArray(new String[0]);

        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recylerViewadapter = new RecyclerViewAdapter(context, dados_nomes, dados_idades);
        recyclerView.setAdapter(recylerViewadapter);

    }


    private void iniciaComponente() {

        et_nome = findViewById(R.id.et_nome);
        et_idade = findViewById(R.id.et_idade);
        swSexo = findViewById(R.id.sw_sexo);
        bt_salvar = findViewById(R.id.bt_salvar);
        recyclerView = findViewById(R.id.lv_pessoas);
    }

    private void limpaFormulario() {
        et_nome.setText("");
        et_nome.requestFocus();
        et_idade.setText("");
        swSexo.setChecked(false);
    }
}