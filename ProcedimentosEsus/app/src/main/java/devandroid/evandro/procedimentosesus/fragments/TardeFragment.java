package devandroid.evandro.procedimentosesus.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.procedimentosesus.R;
import devandroid.evandro.procedimentosesus.adapater.ManhaAdapter;
import devandroid.evandro.procedimentosesus.adapater.NoiteAdapter;
import devandroid.evandro.procedimentosesus.adapater.TardeAdapter;
import devandroid.evandro.procedimentosesus.controller.ConsultaController;
import devandroid.evandro.procedimentosesus.model.Consulta;


public class TardeFragment extends Fragment {

    private List<Consulta> consultaList;

    private ConsultaController consultaController;

    private TardeAdapter tardeAdapter;
    private RecyclerView rv_tarde;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_tarde, container, false);

        rv_tarde = view.findViewById(R.id.rv_tarde);

        consultaController = new ConsultaController(getContext());

        configRecyclerView();
        return view;
    }
    private void configRecyclerView() {


        rv_tarde.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_tarde.setHasFixedSize(true);
        getCpf("09/05/1987").clear();
        consultaList=getCpf("09/05/1987");
        tardeAdapter = new TardeAdapter(consultaList);
        rv_tarde.setAdapter(tardeAdapter);


    }


    private List<Consulta> getCpf(String data) {

        List<Consulta> consultas = new ArrayList<>() ;

        List<String> cpf = new ArrayList<>();

        for (Consulta consulta : consultaController.getCpf(data,"tarde")
        ) {
            cpf.add(consulta.getCnsPaciente());
        }

        for (int i=0;i< cpf.size();i++){

            consultas.add(consultaController.getTODOS(cpf.get(i),"tarde"));
        }

        return consultas;

    }

}