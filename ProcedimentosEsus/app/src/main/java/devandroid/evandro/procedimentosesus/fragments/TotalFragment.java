package devandroid.evandro.procedimentosesus.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;

import devandroid.evandro.procedimentosesus.R;
import devandroid.evandro.procedimentosesus.api.AppUtil;
import devandroid.evandro.procedimentosesus.controller.ConsultaController;


public class TotalFragment extends Fragment {

    private ConsultaController consultaController;

    private TextView tv_total_pa,tv_total_temperatura,tv_total_curativo,
            tv_total_altura,tv_total_glicemia,tv_total_peso;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_total, container, false);

        consultaController = new ConsultaController(getActivity());


        tv_total_pa = view.findViewById(R.id.tv_total_pa);
        tv_total_glicemia = view.findViewById(R.id.tv_total_glicemia);
        tv_total_temperatura = view.findViewById(R.id.tv_total_temperatura);
        tv_total_curativo = view.findViewById(R.id.tv_total_curativo_simples);
        tv_total_peso = view.findViewById(R.id.tv_total_peso);
        tv_total_altura = view.findViewById(R.id.tv_total_altura);


        try {
            buscarTotal(AppUtil.getDataAtualFormatoAmericanoParaDB(AppUtil.getDataAtual()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return view;
    }

    private void buscarTotal(String data){

       tv_total_pa.setText(""+consultaController.totalPa(data));
       tv_total_curativo.setText(""+consultaController.totalCurativo(data));
       tv_total_peso.setText(""+consultaController.totalPeso(data));
       tv_total_temperatura.setText(""+consultaController.totalTemperatura(data));
       tv_total_altura.setText(""+consultaController.totalAltura(data));
       tv_total_glicemia.setText(""+consultaController.totalGlicemia(data));

    }

}