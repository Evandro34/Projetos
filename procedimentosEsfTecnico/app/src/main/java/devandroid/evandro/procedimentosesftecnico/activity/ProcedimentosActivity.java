package devandroid.evandro.procedimentosesftecnico.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import devandroid.evandro.procedimentosesftecnico.R;
import devandroid.evandro.procedimentosesftecnico.adapter.ViewPagerAdapter;
import devandroid.evandro.procedimentosesftecnico.fragment.ManhaFragment;
import devandroid.evandro.procedimentosesftecnico.fragment.NoiteFragment;
import devandroid.evandro.procedimentosesftecnico.fragment.TardeFragment;

public class ProcedimentosActivity extends AppCompatActivity {

    private TabLayout tab_layout;

    private ImageButton ibAdd;

    private ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimentos);

        iniciaComponentes();
        configTabsLayout();
        cliqueBotao();

    }

    private void configTabsLayout() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new ManhaFragment(), "Manhã");
        viewPagerAdapter.addFragment(new TardeFragment(), "Tarde");
        viewPagerAdapter.addFragment(new NoiteFragment(), "Noite");

        view_pager.setAdapter(viewPagerAdapter);

        tab_layout.setElevation(0);
        tab_layout.setupWithViewPager(view_pager);
    }

    private void iniciaComponentes() {

        ibAdd = findViewById(R.id.ib_add);
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("PROCEDIMENTOS");
        text_toolbar.setTextSize(18);

        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);

    }

    private void cliqueBotao() {
        ibAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, CadPacienteActivity.class);
            startActivity(intent);
        });
    }
}