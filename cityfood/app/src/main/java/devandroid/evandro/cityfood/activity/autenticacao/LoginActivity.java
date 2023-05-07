package devandroid.evandro.cityfood.activity.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import devandroid.evandro.cityfood.R;

public class LoginActivity extends AppCompatActivity {

    private TextView text_criar_conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        configCliques();
        iniciaComponentes();
    }

    private void configCliques(){
        findViewById(R.id.text_criar_conta).setOnClickListener(view -> startActivity(new Intent(this,CriarContaActivity.class)));
    }

    private void iniciaComponentes(){
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Login");
    }


}