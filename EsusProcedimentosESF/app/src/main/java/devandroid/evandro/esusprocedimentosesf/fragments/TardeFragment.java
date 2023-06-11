package devandroid.evandro.esusprocedimentosesf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.R;
import devandroid.evandro.esusprocedimentosesf.adapter.TardeAdapter;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.controller.ConsultaController;
import devandroid.evandro.esusprocedimentosesf.model.Consulta;

public class TardeFragment extends Fragment {
    private List<Consulta> consultaList;

    private ConsultaController consultaController;

    private TardeAdapter tardeAdapter;
    private String dataAtual;
    private RecyclerView rv_tarde;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarde, container, false);
        consultaController = new ConsultaController(getContext());

        rv_tarde = view.findViewById(R.id.rv_tarde);
        dataAtual = AppUtil.getDataAtualFormatoAmericanoParaDB(AppUtil.getDataAtual());

        configRecyclerView();

        return view;
    }

    private void configRecyclerView() {


        rv_tarde.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_tarde.setHasFixedSize(true);
        getPesquisaCpfPorData(dataAtual).clear();
        consultaList = getPesquisaCpfPorData(dataAtual);
        tardeAdapter = new TardeAdapter(consultaList);
        rv_tarde.setAdapter(tardeAdapter);


    }


    private List<Consulta> getPesquisaCpfPorData(String data) {

        List<Consulta> consultas = new ArrayList<>();

        List<Integer> id = new ArrayList<>();

        for (Consulta consulta : consultaController.getTodoCpfDaDataAtual(data, AppUtil.TARDE)
        ) {

            id.add(consulta.getFkidPessoaConsulta());

        }

        for (int i = 0; i < id.size(); i++) {

            consultas.add(consultaController.getTodosProcedimentoPorPaciente(id.get(i), AppUtil.TARDE));

        }

        return consultas;

    }

}