package com.example.ahmadrizaldi.traveloka;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.DeskripsiHotel;
import com.example.ahmadrizaldi.traveloka.model.Hotel;
import com.example.ahmadrizaldi.traveloka.model.imageuploadd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TambahHotel extends AppCompatActivity {

    private TextView tambahGambar, deskripsi_HHHHH, fasilitasUtama;
    private ImageView im1,im2,im3,im4,im5;
    private EditText alamatHotel, lokasiHotel;

    private Button simpan;
    private String lok;

    private DatabaseReference mDatabaseRef, hotelDatabase;

    private String deskripsi = " ";

    private static final int SELECT_PHOTO1 = 100;
    private static final int SELECT_PHOTO2 = 200;
    private static final int SELECT_PHOTO3 = 300;
    private static final int SELECT_PHOTO4 = 400;
    private static final int SELECT_PHOTO5 = 500;


    Uri selectedImage1 = null;
    Uri selectedImage2 = null;
    Uri selectedImage3 = null;
    Uri selectedImage4 = null;
    Uri selectedImage5 = null;



    FirebaseStorage storage;
    StorageReference storageRef,imageRef;
    ProgressDialog progressDialog;
    UploadTask uploadTask;
    ImageView imageView;

    FragmentMap2 homeFragment = new FragmentMap2();

    String fasilitasFinal = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_tambah_hotel);

        tambahGambar = (TextView)findViewById(R.id.tambahGmbar_);
        lokasiHotel = (EditText)findViewById(R.id.lokasiHtl);

        im1 = (ImageView)findViewById(R.id.im1);
        im2 = (ImageView)findViewById(R.id.im6);
        im3 = (ImageView)findViewById(R.id.im3);
        im4 = (ImageView)findViewById(R.id.im4);
        im5 = (ImageView)findViewById(R.id.im5);

        lokasiHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TambahHotel.this, Lokasi.class);
                startActivityForResult(i, 13);
            }
        });

        fasilitasUtama = (TextView)findViewById(R.id.fasilitasUtamaHotelTambah);

        storage = FirebaseStorage.getInstance();
        //creates a storage reference
        storageRef = storage.getReference();


        alamatHotel = (EditText)findViewById(R.id.alamatHotel);

        deskripsi_HHHHH = (TextView)findViewById(R.id.deskripsi_HHHHH);

        simpan = (Button)findViewById(R.id.simpanHotel);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Reservasi");
        hotelDatabase = FirebaseDatabase.getInstance().getReference("Hotel");


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content3, homeFragment);
        fragmentTransaction.commit();

        deskripsi_HHHHH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TambahHotel.this, DeskripsiHotel.class);

                Bundle b = new Bundle();
                b.putString("deskripsi", deskripsi);
                i.putExtras(b);
                startActivityForResult(i, 789);
            }
        });


        fasilitasUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TambahHotel.this, FasilitasUtamaHotel.class);
                startActivityForResult(i, 89);
            }
        });

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO1);
            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO2);
            }
        });

        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO3);
            }
        });

        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO4);
            }
        });

        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO5);
            }
        });


        simpan.setOnClickListener(new View.OnClickListener() {


            @Override
            @SuppressWarnings("VisibleForTests")
            public void onClick(View view) {


                String idHo = hotelDatabase.push().getKey().toString();

                uploadImage(selectedImage1, idHo,1);
                uploadImage(selectedImage2, idHo,2);
                uploadImage(selectedImage3, idHo,3);
                uploadImage(selectedImage4, idHo,4);
                uploadImage(selectedImage5, idHo,5);

                System.out.println("Urlnya " + selectedImage1.toString());

                pesan("Tambah Hotel Sukses");

                simpan.setVisibility(View.GONE);

            }
        });



        tambahGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_ = new Intent(TambahHotel.this, TambahGambar.class);

                startActivityForResult(intent_,909);


            }
        });
    }


    public void uploadImage(Uri selectedImagea, final String idHo, final int indeks) {
        imageRef = storageRef.child("images/" + selectedImagea.getLastPathSegment());
        //creating and showing progress dialog


        //starting upload
        uploadTask = imageRef.putFile(selectedImagea);
        // Observe state change events such as progress, pause, and resume

        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                //sets and increments value of progressbar
            }
        });

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TambahHotel.this, "Error in uploading!", Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();


                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String namaHotel = sharedPref.getString("namaHotel","");
                String pemilik = sharedPref.getString("idHotel","");
                String reputasi = sharedPref.getString("reputasi","").toString();


                if (indeks ==1){
                    tambahHotel(idHo, namaHotel, reputasi,lokasiHotel.getText().toString().trim(),fasilitasFinal,deskripsi_HHHHH.getText().toString().trim(), "---",
                            homeFragment.getLat(), homeFragment.getLang(), downloadUrl.toString(),"","","","",
                            alamatHotel.getText().toString().trim(), pemilik);

                }else if (indeks ==2){
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("gm2", downloadUrl.toString());

                    FirebaseDatabase.getInstance().getReference().child("Hotel").child(idHo).updateChildren(result);
                }else if (indeks ==3){
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("gm3", downloadUrl.toString());

                    FirebaseDatabase.getInstance().getReference().child("Hotel").child(idHo).updateChildren(result);
                }else if (indeks ==4){
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("gm4", downloadUrl.toString());

                    FirebaseDatabase.getInstance().getReference().child("Hotel").child(idHo).updateChildren(result);
                }else if (indeks ==5){
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("gm5", downloadUrl.toString());

                    FirebaseDatabase.getInstance().getReference().child("Hotel").child(idHo).updateChildren(result);
                }


            }
        });

    }



    private void tambahHotel(String ID_Hotel, String NamaHotel, String Reputasi, String Lokasi, String Fasilitas, String Deskripsi, String Kebijakan,
    double lat, double lang, String g1, String g2 , String g3, String g4, String g5, String alamat, String pemilik){


        Hotel hot = new Hotel(ID_Hotel , NamaHotel, Reputasi, Lokasi, Fasilitas, Deskripsi, Kebijakan, lat, lang, g1, g2, g3, g4, g5, alamat, pemilik);

        hotelDatabase.child(ID_Hotel).setValue(hot);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SELECT_PHOTO1:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage1 = data.getData();
                    im1.setImageURI(selectedImage1);
                }
                break;

            case SELECT_PHOTO2:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage2 = data.getData();
                    im2.setImageURI(selectedImage2);
                    break;
                }

            case SELECT_PHOTO3:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage3 = data.getData();
                    im3.setImageURI(selectedImage3);
                    break;
                }
            case SELECT_PHOTO4:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage4 = data.getData();
                    im4.setImageURI(selectedImage4);
                    break;
                }
            case SELECT_PHOTO5:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage5 = data.getData();
                    im5.setImageURI(selectedImage5);
                    break;
                }
        }

        if (requestCode == 789) {
            if (resultCode == RESULT_OK) {
                deskripsi = data.getStringExtra("deskripsi");
                deskripsi_HHHHH.setText(data.getStringExtra("deskripsi"));
            }
        }

        if (requestCode == 89) {
            if (resultCode == RESULT_OK) {
                fasilitasFinal = data.getStringExtra("fasilitasUtamaHotel");
                fasilitasUtama.setText("Fasilitas Telah di set");
            }
        }

        if (requestCode == 13){
            if (resultCode == RESULT_OK){
                lok = data.getStringExtra("nama");
                lokasiHotel.setText(lok);
                Toast.makeText(this, lok,Toast.LENGTH_SHORT).show();
            }
        }
    }

        private void pesan(String pesan) {

            Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();

        }

}
