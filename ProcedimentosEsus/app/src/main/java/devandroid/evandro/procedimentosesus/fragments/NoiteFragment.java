package devandroid.evandro.procedimentosesus.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.procedimentosesus.R;
import devandroid.evandro.procedimentosesus.adapater.ManhaAdapter;
import devandroid.evandro.procedimentosesus.adapater.NoiteAdapter;
import devandroid.evandro.procedimentosesus.controller.ConsultaController;
import devandroid.evandro.procedimentosesus.model.Consulta;

public class NoiteFragment extends Fragment {


    private List<Consulta> consultaList;

    private ConsultaController consultaController;

    private NoiteAdapter noiteAdapter;
    private RecyclerView rv_noite;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noite, container, false);

        rv_noite = view.findViewById(R.id.rv_noite);

        consultaController = new ConsultaController(getContext());

        configRecyclerView();

        return view;
    }

    private void configRecyclerView() {


        rv_noite.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_noite.setHasFixedSize(true);
        getCpf("09/05/1987");
        noiteAdapter = new NoiteAdapter(consultaList);
        rv_noite.setAdapter(noiteAdapter);


    }


    private void getCpf(String data) {

        List<Consulta> consultas = new ArrayList<>();

        for (Consulta consulta : consultaController.getCpf(data)
        ) {
            consultaController.getProcedimentos(consulta.getCnsPaciente());
            Consulta consulta1 = new Consulta();
            consulta1.setData(consulta.getData());
            consulta1.setTurno(consulta.getTurno());
            consulta1.setLocal(consulta.getLocal());
            consulta1.setCnsPaciente(consulta.getCnsPaciente());
            consultas.add(consulta1);
        }
        consultaList=consultas;

    }


}