package devandroid.evandro.meuprocedimentos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import devandroid.evandro.meuprocedimentos.R;
import devandroid.evandro.meuprocedimentos.activity.ActivityCadastro;
import devandroid.evandro.meuprocedimentos.activity.EsusRelatorioActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_cad_procedimento,btn_relatorio_esus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectarTelaXmlComJava();
    }

    private void conectarTelaXmlComJava(){
        findViewById(R.id.btn_cad_procedimento).setOnClickListener(v ->
                startActivity(new Intent(this, ActivityCadastro.class)));
        findViewById(R.id.btn_relatorio_esus).setOnClickListener(v ->
                startActivity(new Intent(this, EsusRelatorioActivity.class)));

    }


}