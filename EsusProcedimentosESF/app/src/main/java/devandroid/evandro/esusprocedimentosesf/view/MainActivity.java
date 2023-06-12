package devandroid.evandro.esusprocedimentosesf.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.view.ListarProcedimentosActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_cad_procedimento,btn_relatorio_pab,btn_relatorio_bpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciaComponente();
        cliqueBotao();
    }

    private void iniciaComponente(){
        btn_cad_procedimento = findViewById(R.id.btn_cad_procedimento);
        btn_relatorio_bpa = findViewById(R.id.btn_relatorio_bpa);
        btn_relatorio_pab = findViewById(R.id.btn_relatorio_pab);

    }
    private void cliqueBotao(){
        btn_cad_procedimento.setOnClickListener(view -> {
            Intent intent = new Intent(this, ListarProcedimentosActivity.class);
            startActivity(intent);
        });
        btn_relatorio_bpa.setOnClickListener(view -> {
           Intent intent = new Intent(this, RelatorioBpaActivity.class);
           startActivity(intent);
        });
        btn_relatorio_pab.setOnClickListener(view -> {
            Intent intent = new Intent(this, RelatorioPabActivity.class);
            startActivity(intent);
        });
    }
}