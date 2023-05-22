package devandroid.evandro.meuprocedimentos.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.meuprocedimentos.R;
import devandroid.evandro.meuprocedimentos.adapter.TurnoManhaAdapter;
import devandroid.evandro.meuprocedimentos.model.TurnoProcedimentos;


public class ManhaFragment extends Fragment {

    private RecyclerView rv_turno_manha;
    private TurnoManhaAdapter  cardapioAdapter;

    private final List<TurnoProcedimentos> produtoList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manha, container, false);
        iniciaComponente(view);
        carregaLista();
        configRv();
        return view;
    }

    private void iniciaComponente(View view){
        rv_turno_manha = view.findViewById(R.id.rv_turno_manha);
    }

    private void configRv(){
        rv_turno_manha.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_turno_manha.setHasFixedSize(true);
        cardapioAdapter = new TurnoManhaAdapter(produtoList);
        rv_turno_manha.setAdapter(cardapioAdapter);
    }

    private void carregaLista() {

        TurnoProcedimentos produto1 = new TurnoProcedimentos();
        produto1.setData("17/05/2023");
        produto1.setTurno("manha");
        produto1.setCpf("087992");
        produto1.setData_nascimento("09/05/1985");
        produto1.setLocal("usb");
        produto1.setProcedimentos("curativo");


        produtoList.add(produto1);


        produtoList.add(produto1);


    }

}