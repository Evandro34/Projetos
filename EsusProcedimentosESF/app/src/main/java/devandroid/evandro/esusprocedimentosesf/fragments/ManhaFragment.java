package devandroid.evandro.esusprocedimentosesf.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.adapter.ManhaAdapter;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.controller.ConsultaController;
import devandroid.evandro.esusprocedimentosesf.model.Consulta;


public class ManhaFragment extends Fragment {

    private List<Consulta> consultaList;



    private ConsultaController consultaController;

    private ManhaAdapter manhaAdapter;
    private String dataAtual;
    private RecyclerView rv_manha;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manha, container, false);

        consultaController = new ConsultaController(getContext());

        rv_manha = view.findViewById(R.id.rv_manha);


        dataAtual = AppUtil.getDataAtualFormatoAmericanoParaDB(AppUtil.getDataAtual());

        configRecyclerView();

        return view;
    }


    private void configRecyclerView() {


        rv_manha.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_manha.setHasFixedSize(true);
        getPesquisaCpfPorData(dataAtual).clear();
        consultaList = getPesquisaCpfPorData(dataAtual);
        manhaAdapter = new ManhaAdapter(consultaList);
        rv_manha.setAdapter(manhaAdapter);


    }


    private List<Consulta> getPesquisaCpfPorData(String data) {

        List<Consulta> consultas = new ArrayList<>();

        List<Integer> id = new ArrayList<>();

        int x=0;
        for (Consulta consulta : consultaController.getTodoCpfDaDataAtual(data, AppUtil.MANHA)
        ) {

            id.add(consulta.getFkidPessoaConsulta());


        }


        for (int i = 0; i < id.size(); i++) {


            consultas.add(consultaController.getTodosProcedimentoPorPaciente(id.get(i), AppUtil.MANHA));

        }

        return consultas;

    }


}