package devandroid.evandro.meuprocedimentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_cad_procedimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectarTelaXmlComJava();
    }

    private void conectarTelaXmlComJava(){
        findViewById(R.id.btn_cad_procedimento).setOnClickListener(v ->
                startActivity(new Intent(this, ActivityCadastro.class)));

    }


}