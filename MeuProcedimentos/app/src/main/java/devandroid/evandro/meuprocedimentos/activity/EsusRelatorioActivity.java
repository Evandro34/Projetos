package devandroid.evandro.meuprocedimentos.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import devandroid.evandro.meuprocedimentos.fragment.ManhaFragment;
import devandroid.evandro.meuprocedimentos.R;
import devandroid.evandro.meuprocedimentos.fragment.NoiteFragment;
import devandroid.evandro.meuprocedimentos.fragment.TardeFragment;
import devandroid.evandro.meuprocedimentos.adapter.ViewPagerAdapter;

public class EsusRelatorioActivity extends AppCompatActivity {
    private TabLayout tab_layout;
    private ViewPager view_pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esus_relatorio);

        iniciaComponentes();

        configTabsLayout();

    }

    private void configTabsLayout(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new ManhaFragment(), "Manhã");
        viewPagerAdapter.addFragment(new TardeFragment(), "Tarde");
        viewPagerAdapter.addFragment(new NoiteFragment(), "Noite");

        view_pager.setAdapter(viewPagerAdapter);

        tab_layout.setElevation(0);
        tab_layout.setupWithViewPager(view_pager);
    }

    private void iniciaComponentes(){
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Procedimentos");

        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);
    }



}