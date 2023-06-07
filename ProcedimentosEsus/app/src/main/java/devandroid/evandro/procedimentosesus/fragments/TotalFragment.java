package devandroid.evandro.procedimentosesus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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


        buscarTotal(AppUtil.getDataAtualFormatoAmericanoParaDB(AppUtil.getDataAtual()));

        return view;
    }


    private void buscarTotal(String data) {

        tv_total_pa.setText(String.valueOf(consultaController.totalProcedimentosEsusData(data, AppUtil.PRESSAO_ARTERIAL)));
        tv_total_temperatura.setText(String.valueOf(consultaController.totalProcedimentosEsusData(data, AppUtil.TEMPERATURA)));
        tv_total_curativo.setText(String.valueOf(consultaController.totalProcedimentosEsusData(data, AppUtil.CURATIVO)));
        tv_total_glicemia.setText(String.valueOf(consultaController.totalProcedimentosEsusData(data, AppUtil.GLICEMIA)));
        tv_total_altura.setText(String.valueOf(consultaController.totalProcedimentosEsusData(data, AppUtil.ALTURA)));
        tv_total_peso.setText(String.valueOf(consultaController.totalProcedimentosEsusData(data, AppUtil.PESO)));

    }


}