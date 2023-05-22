package devandroid.evandro.cityfood.activity.autenticacao;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import devandroid.evandro.cityfood.R;
import devandroid.evandro.cityfood.adapter.ViewPagerAdapter;
import devandroid.evandro.cityfood.fragment.empresa.EmpresaFragment;
import devandroid.evandro.cityfood.fragment.usuario.UsuarioFragment;

public class CriarContaActivity extends AppCompatActivity {

    private TabLayout table_layout;
    private ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        iniciaComponentes();
        configTabsLayout();
        configCliques();
    }

    private void configCliques() {
        findViewById(R.id.ib_voltar).setOnClickListener(view -> finish());
    }

    private void configTabsLayout() {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new UsuarioFragment(), "Usuário");
        viewPagerAdapter.addFragment(new EmpresaFragment(), "Empresa");

        view_pager.setAdapter(viewPagerAdapter);

        table_layout.setElevation(0);

        table_layout.setupWithViewPager(view_pager);


    }


    private void iniciaComponentes() {

        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Cadastra-se");
        table_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);
    }


}