package devandroid.evandro.procedimentosesus.view;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

import devandroid.evandro.procedimentosesus.R;
import devandroid.evandro.procedimentosesus.adapater.ViewPagerAdapter;
import devandroid.evandro.procedimentosesus.api.CriaPDF;
import devandroid.evandro.procedimentosesus.databinding.ActivityListarProcedimentoBinding;
import devandroid.evandro.procedimentosesus.fragments.ManhaFragment;
import devandroid.evandro.procedimentosesus.fragments.NoiteFragment;
import devandroid.evandro.procedimentosesus.fragments.TardeFragment;
import devandroid.evandro.procedimentosesus.fragments.TotalFragment;

public class ListarProcedimentoActivity extends AppCompatActivity {
    private TabLayout tab_layout;

    private ImageButton ibAdd, ibPDF;

    private ViewPager view_pager;

    private ManhaFragment  manhaFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_procedimento);

        manhaFragment = new ManhaFragment();

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

        ibAdd = findViewById(R.id.ib_add);
        ibPDF = findViewById(R.id.ib_pdf);
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("PROCEDIMENTOS");
        text_toolbar.setTextSize(18);

        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);

    }

    private void cliqueBotao() {
        ibAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, CadastroProcedimentosActivity.class);
            startActivity(intent);
        });
        ibPDF.setOnClickListener(view -> {

            manhaFragment.Reclyclerview();
        });
    }


}