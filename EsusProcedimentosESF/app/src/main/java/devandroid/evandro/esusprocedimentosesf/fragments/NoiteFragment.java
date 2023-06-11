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
import devandroid.evandro.esusprocedimentosesf.adapter.NoiteAdapter;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.controller.ConsultaController;
import devandroid.evandro.esusprocedimentosesf.model.Consulta;


public class NoiteFragment extends Fragment {

    private List<Consulta> consultaList;

    private ConsultaController consultaController;

    private NoiteAdapter noiteAdapter;
    private String dataAtual;
    private RecyclerView rv_noite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_noite, container, false);

        consultaController = new ConsultaController(getContext());

        rv_noite = view.findViewById(R.id.rv_noite);

        dataAtual = AppUtil.getDataAtualFormatoAmericanoParaDB(AppUtil.getDataAtual());

        configRecyclerView();
        return view;
    }

    private void configRecyclerView() {


        rv_noite.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_noite.setHasFixedSize(true);
        getPesquisaCpfPorData(dataAtual).clear();
        consultaList = getPesquisaCpfPorData(dataAtual);
        noiteAdapter = new NoiteAdapter(consultaList);
        rv_noite.setAdapter(noiteAdapter);


    }


    private List<Consulta> getPesquisaCpfPorData(String data) {

        List<Consulta> consultas = new ArrayList<>();

        List<Integer> id = new ArrayList<>();

        for (Consulta consulta : consultaController.getTodoCpfDaDataAtual(data, AppUtil.NOITE)
        ) {

            id.add(consulta.getFkidPessoaConsulta());

        }

        for (int i = 0; i < id.size(); i++) {

            consultas.add(consultaController.getTodosProcedimentoPorPaciente(id.get(i), AppUtil.NOITE));

        }

        return consultas;

    }
}