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
import devandroid.evandro.procedimentosesus.api.AppUtil;
import devandroid.evandro.procedimentosesus.controller.ConsultaController;
import devandroid.evandro.procedimentosesus.model.Consulta;


public class ManhaFragment extends Fragment {

    private List<Consulta> consultaList;

    private ConsultaController consultaController;

    private ManhaAdapter manhaAdapter;
    private RecyclerView rv_manha;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_manha, container, false);
        rv_manha = view.findViewById(R.id.rv_manha);

        consultaController = new ConsultaController(getContext());

        configRecyclerView();
        return view;
    }



    private void configRecyclerView() {


        rv_manha.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_manha.setHasFixedSize(true);
        getCpf(AppUtil.getDataAtual()).clear();
        consultaList=getCpf(AppUtil.getDataAtual());
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

}