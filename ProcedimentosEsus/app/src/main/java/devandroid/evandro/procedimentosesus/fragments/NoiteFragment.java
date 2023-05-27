package devandroid.evandro.procedimentosesus.fragments;

import android.content.Intent;
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
import devandroid.evandro.procedimentosesus.model.Consulta;

public class NoiteFragment extends Fragment {


    private List<Consulta> consultaList = new ArrayList<>();

    private NoiteAdapter noiteAdapter;
    private RecyclerView rv_noite;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_noite, container, false);

       rv_noite = view.findViewById(R.id.rv_noite);
       carregaLista();
       configRecyclerView();

       return  view;
    }
    private void configRecyclerView(){



       rv_noite.setLayoutManager(new LinearLayoutManager(getContext()));
       rv_noite.setHasFixedSize(true);

       noiteAdapter= new NoiteAdapter(consultaList);
       rv_noite.setAdapter(noiteAdapter);





    }

    private void carregaLista(){

        Consulta produto1 = new Consulta();
        produto1.setData("02/05/2020");
        produto1.setTurno("NOITE");
        produto1.setCnsPaciente("11111111111111");
       // produto1.setData_nascimento("09/06/2024");
        produto1.setLocal("1-USB");
        produto1.setProcedimentos("CURATIVO-222222\nGlicemia - 11111111\nPA - 3333333");

        Consulta produto2 = new Consulta();
        produto2.setData("02/05/2020");
        produto2.setTurno("NOITE");
        produto2.setCnsPaciente("22222222222222");
       // produto2.setData_nascimento("09/06/2024");
        produto2.setLocal("1-USB");
        produto2.setProcedimentos("CURATIVO-222222\nGlicemia - 11111111\nPA - 3333333");


        Consulta produto3 = new Consulta();
        produto3.setData("02/05/2020");
        produto3.setTurno("NOITE");
        produto3.setCnsPaciente("22222222222222");
        //produto3.setData_nascimento("09/06/2024");
        produto3.setLocal("1-USB");
        produto3.setProcedimentos("CURATIVO-222222\nGlicemia - 11111111\nPA - 3333333");


        consultaList.add(produto1);
        consultaList.add(produto2);
        consultaList.add(produto3);
    }





}