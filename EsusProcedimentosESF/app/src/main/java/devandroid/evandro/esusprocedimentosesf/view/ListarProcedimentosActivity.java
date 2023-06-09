package devandroid.evandro.esusprocedimentosesf.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.adapter.ViewPagerAdapter;
import devandroid.evandro.esusprocedimentosesf.fragments.ManhaFragment;
import devandroid.evandro.esusprocedimentosesf.fragments.NoiteFragment;
import devandroid.evandro.esusprocedimentosesf.fragments.TardeFragment;
import devandroid.evandro.esusprocedimentosesf.fragments.TotalFragment;

public class ListarProcedimentosActivity extends AppCompatActivity {
    private TabLayout tab_layout;

    private ImageButton ib_Add,ib_voltar;

    private ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_procedimentos);

        iniciaComponentes();
        configTabsLayout();
        cliqueBotao();
    }

    private void configTabsLayout() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new ManhaFragment(), "Manhã");
        viewPagerAdapter.addFragment(new TardeFragment(), "Tarde");
        viewPagerAdapter.addFragment(new NoiteFragment(), "Noite");
        viewPagerAdapter.addFragment(new TotalFragment(), "Total");

        view_pager.setAdapter(viewPagerAdapter);
        tab_layout.setElevation(0);
        tab_layout.setupWithViewPager(view_pager);
    }

    private void iniciaComponentes() {

        ib_Add = findViewById(R.id.ib_add);

        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("PROCEDIMENTOS");
        text_toolbar.setTextSize(18);

        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);

    }

    private void cliqueBotao() {
        ib_Add.setOnClickListener(view -> {
            Intent intent = new Intent(this,ListarPacienteActivity.class);
            startActivity(intent);
        });

    }
}