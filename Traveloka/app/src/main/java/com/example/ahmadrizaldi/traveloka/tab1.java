package com.example.ahmadrizaldi.traveloka;

/**
 * Created by ahmadrizaldi on 10/22/17.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class tab1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menunggu_konfirmasi, container, false);
        return rootView;
    }

}
