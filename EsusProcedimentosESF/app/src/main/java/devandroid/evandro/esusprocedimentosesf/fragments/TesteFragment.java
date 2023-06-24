package devandroid.evandro.esusprocedimentosesf.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.adapter.TesteAdapter;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.api.CriaPDF;
import devandroid.evandro.esusprocedimentosesf.controller.ConsultaController;
import devandroid.evandro.esusprocedimentosesf.model.Consulta;


public class TesteFragment extends Fragment {

    private List<Consulta> consultaList;


    private ConsultaController consultaController;

    private TesteAdapter manhaAdapter;
    private String dataAtual;
    private RecyclerView rv_teste;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teste, container, false);

        consultaController = new ConsultaController(getContext());

        rv_teste = view.findViewById(R.id.rv_teste);


        dataAtual = AppUtil.getDataAtualFormatoAmericanoParaDB(AppUtil.getDataAtual());

        configRecyclerView();

        return view;
    }


    private void configRecyclerView() {


        rv_teste.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_teste.setHasFixedSize(true);
        getPesquisaCpfPorData(dataAtual).clear();
        consultaList = getPesquisaCpfPorData(dataAtual);
        manhaAdapter = new TesteAdapter(consultaList);
        rv_teste.setAdapter(manhaAdapter);


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

        Bitmap bitmapRecycler = fotoDaRecyclerView(rv_teste) ;
        File pasta = new File(Environment.getExternalStorageDirectory()+"/Documents");
        CriaPDF criaPDF = new CriaPDF(pasta,getContext());
        String  criando = criaPDF.salvarPdf(bitmapRecycler,"lista");

        if(criando.equals("sucesso")){
            Toast.makeText(getContext(), " criar o arquivo  pdf", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),criando, Toast.LENGTH_LONG).show();
        }
    }


}