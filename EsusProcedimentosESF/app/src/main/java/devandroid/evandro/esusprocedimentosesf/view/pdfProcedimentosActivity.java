package devandroid.evandro.esusprocedimentosesf.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.adapter.Teste2Adapter;
import devandroid.evandro.esusprocedimentosesf.adapter.TesteAdapter;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.api.CriaPDF;
import devandroid.evandro.esusprocedimentosesf.controller.ConsultaController;
import devandroid.evandro.esusprocedimentosesf.model.Consulta;

public class pdfProcedimentosActivity extends AppCompatActivity {

    private List<Consulta> consultaList;


    private ConsultaController consultaController;

    private Teste2Adapter manhaAdapter;
    private String dataAtual;
    private RecyclerView rv_pdf;

    private Button gerarPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_procedimentos);
        consultaController = new ConsultaController(this);

        rv_pdf = findViewById(R.id.rv_pdf);
        gerarPdf =findViewById(R.id.btn_gerar_pdf);

        gerarPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reclyclerview();
            }
        });


        dataAtual = AppUtil.getDataAtualFormatoAmericanoParaDB(AppUtil.getDataAtual());

        configRecyclerView();
    }

    private void configRecyclerView() {


        rv_pdf.setLayoutManager(new LinearLayoutManager(this));
        rv_pdf.setHasFixedSize(true);
        getPesquisaCpfPorData(dataAtual).clear();
        consultaList = getPesquisaCpfPorData(dataAtual);
        manhaAdapter = new Teste2Adapter(consultaList);
        rv_pdf.setAdapter(manhaAdapter);


    }

    private List<Consulta> getPesquisaCpfPorData(String data) {

        List<Consulta> consultas = new ArrayList<>();

        List<Integer> id = null;


        String[] turn = {AppUtil.MANHA, AppUtil.TARDE, AppUtil.NOITE};

        for (int J = 0; J < turn.length; J++) {


            id = new ArrayList<>();

            for (Consulta consulta : consultaController.getTodoCpfDaDataAtual(data, turn[J])
            ) {


                id.add(consulta.getFkidPessoaConsulta());

            }


            for (int i = 0; i < id.size(); i++) {


                consultas.add(consultaController.getTodosProcedimentoPorPaciente(id.get(i), turn[J]));

            }


        }


        return consultas;

    }
    public Bitmap fotoDaRecyclerView(RecyclerView view) {

        RecyclerView.Adapter adapter = view.getAdapter();

        Bitmap bitmapPronto = null;

        if (adapter != null) {

            Paint paint = new Paint();

            int tamanhoDaLista = adapter.getItemCount();
            int altura = 0;
            int alturaVolatil = 0;

            final int tamanhoMaximoDoArquivo = (int) (Runtime.getRuntime().maxMemory() / 1024);
            final int tamanhoDoCache = tamanhoMaximoDoArquivo / 8;

            LruCache<String, Bitmap> bitmapCache = new LruCache<>(tamanhoDoCache);


            for (int x = 0; x < tamanhoDaLista; x++) {

                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(x));
                adapter.onBindViewHolder(holder, x);

                holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

                holder.itemView.layout(0, 0,
                        holder.itemView.getMeasuredWidth(),
                        holder.itemView.getMeasuredHeight());

                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildLayer();


                Bitmap cacheDoBitmap = holder.itemView.getDrawingCache();

                if (cacheDoBitmap != null) {

                    bitmapCache.put(String.valueOf(x), cacheDoBitmap);
                }

                altura += holder.itemView.getMeasuredHeight();
            }

            bitmapPronto = Bitmap.createBitmap(view.getMeasuredWidth(), altura, Bitmap.Config.ARGB_8888);

            Canvas pagina = new Canvas(bitmapPronto);
            pagina.drawColor(Color.WHITE);

            for (int x = 0; x < tamanhoDaLista; x++) {
                Bitmap bitmap = bitmapCache.get(String.valueOf(x));
                pagina.drawBitmap(bitmap, 0, alturaVolatil, paint);

                alturaVolatil += bitmap.getHeight();
                bitmap.recycle();
            }

        }


        return bitmapPronto;
    }


    public void Reclyclerview(){

        Bitmap bitmapRecycler = fotoDaRecyclerView(rv_pdf) ;
        File pasta = new File(Environment.getExternalStorageDirectory()+"/Download");
        CriaPDF criaPDF = new CriaPDF(pasta,this);
        String  criando = criaPDF.salvarPdf(bitmapRecycler,"lista");

        if(criando.equals("sucesso")){
            Toast.makeText(this, " criar o arquivo  pdf", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,criando, Toast.LENGTH_LONG).show();
        }
    }

}