package devandroid.evandro.procedimentosesus.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.procedimentosesus.R;
import devandroid.evandro.procedimentosesus.adapater.ManhaAdapter;
import devandroid.evandro.procedimentosesus.adapater.NoiteAdapter;
import devandroid.evandro.procedimentosesus.api.AppUtil;
import devandroid.evandro.procedimentosesus.api.CriaPDF;
import devandroid.evandro.procedimentosesus.controller.ConsultaController;
import devandroid.evandro.procedimentosesus.model.Consulta;


public class ManhaFragment extends Fragment {

    private List<Consulta> consultaList;

    private ConsultaController consultaController;

    private ManhaAdapter manhaAdapter;
    private String dataAtual;
    private RecyclerView rv_manha;
    private ImageButton ib_pdf;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_manha, container, false);


        consultaController = new ConsultaController(getContext());

        rv_manha = view.findViewById(R.id.rv_manha);


        try {
            dataAtual = AppUtil.getDataAtualFormatoAmericanoParaDB(AppUtil.getDataAtual());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        configRecyclerView();


        return view;
    }



    private void configRecyclerView() {

        rv_manha.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_manha.setHasFixedSize(true);
        getCpf(dataAtual).clear();
        consultaList=getCpf(dataAtual);
        manhaAdapter = new ManhaAdapter(consultaList);
        rv_manha.setAdapter(manhaAdapter);


    }


    private List<Consulta> getCpf(String data) {

        List<Consulta> consultas = new ArrayList<>() ;

        List<String> cpf = new ArrayList<>();

        for (Consulta consulta : consultaController.getCpf(data,"manha")
        ) {
            cpf.add(consulta.getCnsPaciente());
        }

        for (int i=0;i< cpf.size();i++){

            consultas.add(consultaController.getTODOS(cpf.get(i),"manha"));
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

        Bitmap bitmapRecycler = fotoDaRecyclerView(rv_manha) ;
        File pasta = new File(Environment.getExternalStorageDirectory()+"/Documents");
        CriaPDF criaPDF = new CriaPDF(pasta,getContext());
        String  criando = criaPDF.salvarPNG(bitmapRecycler,"lista");

        if(criando.equals("sucesso")){
            Toast.makeText(getContext(), " criar o arquivo  pdf", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),criando, Toast.LENGTH_LONG).show();
        }
    }
}