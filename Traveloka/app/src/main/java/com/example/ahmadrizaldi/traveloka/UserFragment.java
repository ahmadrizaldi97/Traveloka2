package com.example.ahmadrizaldi.traveloka;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    String emailT = " ";
    String poinn = " ";

    public UserFragment() {
        // Required empty public constructor
    }

    public void setData(String email, String poin){
        this.emailT = email;
        this.poinn = poin;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        TextView email = (TextView)view.findViewById(R.id.US_email);
        TextView poin = (TextView)view.findViewById(R.id.US_poinUser);
        Button exita = (Button)view.findViewById(R.id.US_Exit);

        email.setText(emailT);
        poin.setText("    " + poinn + " poin");

        exita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), exit.class);
                getActivity().finish();
                startActivity(intent);


            }
        });



        return view;



    }

}
