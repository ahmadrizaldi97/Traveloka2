package com.example.ahmadrizaldi.traveloka;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.example.ahmadrizaldi.traveloka.model.UserList;
import com.example.ahmadrizaldi.traveloka.model.imageuploadd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class CobaCoba extends AppCompatActivity {

    Button button;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba_coba);

        button = (Button)findViewById(R.id.btnViewShow);

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                String myDate = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

                Calendar c = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();

//                cal1.set(2006, Calendar.DECEMBER, 30);

                c2.set(2017, Calendar.DECEMBER, 30, 04, 20);

                long millis1 = c.getTimeInMillis();
                long millis2 = c2.getTimeInMillis();

                long diff = millis2 - millis1;
                long diffSeconds = diff / 1000;
                long diffMinutes = diff / (60 * 1000);

                System.out.println("In minutes: " + diffMinutes + " minutes.");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy hh:mm aa");
                myDate = dateFormat.format(c.getTime());


                System.out.println(myDate+ " haha");
//                progressDialog = new ProgressDialog(CobaCoba.this,R.style.Custom);
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                progressDialog.setMessage("Logging in. Please wait.");
//                progressDialog.show();

            }
        });

    }


}
