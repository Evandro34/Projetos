package devandroid.evandro.esusprocedimetnos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import devandroid.evandro.esusprocedimetnos.R;

public class CadastrarProcedimentosActivity extends AppCompatActivity {

    private Button btn_busca_paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_procedimentos);
    }

    private void iniciaComponente(){
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("CADASTRO PROCEDIMENTOS");
        text_toolbar.setTextSize(18);
        btn_busca_paciente= findViewById(R.id.btn_buscar_paciente);


    }

    private void cliqueBotao(){
        btn_busca_paciente.setOnClickListener(view -> {
            Intent intent = new Intent(this, CadastrarPacienteActivity.class);
            startActivity(intent);
        });
    }
}