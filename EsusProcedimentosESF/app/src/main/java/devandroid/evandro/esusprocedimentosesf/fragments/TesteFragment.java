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
import devandroid.evandro.esusprocedimentosesf.adapter.TesteAdapter;
import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
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


}