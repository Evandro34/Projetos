package devandroid.evandro.esusprocedimentosesf.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import devandroid.evandro.esusprocedimentosesf.R;


public class TotalFragment extends Fragment {

     public TotalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_total, container, false);

        return view;
    }
}