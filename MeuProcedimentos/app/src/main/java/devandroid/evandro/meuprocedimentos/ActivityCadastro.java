package devandroid.evandro.meuprocedimentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ActivityCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        conectarTelaXmlComJava();
    }
    private void conectarTelaXmlComJava(){
        findViewById(R.id.btn_buscar_paciente).setOnClickListener(v ->
                startActivity(new Intent(this, CadastroUsuarioActivity.class)));

    }
}