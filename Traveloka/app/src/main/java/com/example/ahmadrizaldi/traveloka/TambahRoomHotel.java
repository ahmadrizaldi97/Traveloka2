package com.example.ahmadrizaldi.traveloka;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.RoomHotel_Fix;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class TambahRoomHotel extends AppCompatActivity {

    private ImageView im1, im2, im3, im4, im5, im6;
    private TextView fasilitasKamar, fasilitasKamarMandi, deskripsiKmar, tambahStok, kuragStok, stokKmar;
    private Button tambahRoom;
    private EditText namaRoom, Harga, ukuranKamar, tipeTempatTidur;

    private String ukuranKamarFinal, tipeTempatTidurFinal;
    private String fasilitasKamarFinal = ".";
    private String fasilitasKamarMandiFinal = ".";

    private static final int SELECT_PHOTO1 = 100;
    private static final int SELECT_PHOTO2 = 200;
    private static final int SELECT_PHOTO3 = 300;
    private static final int SELECT_PHOTO4 = 400;
    private static final int SELECT_PHOTO5 = 500;
    private static final int SELECT_PHOTO6 = 600;


    Uri selectedImage1 = null;
    Uri selectedImage2 = null;
    Uri selectedImage3 = null;
    Uri selectedImage4 = null;
    Uri selectedImage5 = null;
    Uri selectedImage6 = null;

    DatabaseReference Room;

    FirebaseStorage storage;
    StorageReference storageRef,imageRef;
    ProgressDialog progressDialog;
    UploadTask uploadTask;
    ImageView imageView;

    private EditText ukuranKamar_;
    private RadioGroup radioTipeTempatTidurKamar;
    private RadioButton radioTipeTempatTidurKamarButton;
    private Button simpan, simpanTipeTempattidur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_room);

        im1 = (ImageView)findViewById(R.id.gmb1);
        im2 = (ImageView)findViewById(R.id.gmb2);
        im3 = (ImageView)findViewById(R.id.gmb3);
        im4 = (ImageView)findViewById(R.id.gmb4);
        im5 = (ImageView)findViewById(R.id.gmb5);
        im6 = (ImageView)findViewById(R.id.gmb6);

        stokKmar = (TextView)findViewById(R.id.stokKamara);
        kuragStok = (TextView)findViewById(R.id.stokKamarkurang);
        tambahStok = (TextView)findViewById(R.id.stokKamartambah);
        deskripsiKmar = (TextView)findViewById(R.id.deskripsiRoom);
        fasilitasKamarMandi = (TextView)findViewById(R.id.fasilitaskamarmandi);
        fasilitasKamar = (TextView)findViewById(R.id.dkjagsfdhasgkhlkjdaaaakjhascfasz);

        namaRoom = (EditText)findViewById(R.id.namaroom);
        Harga = (EditText)findViewById(R.id.hargaroom);
        ukuranKamar = (EditText)findViewById(R.id.ukuranKamar);
        tipeTempatTidur = (EditText)findViewById(R.id.tipeTempatTidur);

        storage = FirebaseStorage.getInstance();
        //creates a storage reference
        storageRef = storage.getReference();

        tambahStok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index;
                String stokop = stokKmar.getText().toString().trim();
                index = stokop.indexOf(" ");
                stokop = stokop.substring(0, index);

                index = Integer.parseInt(stokop) + 1;
                stokKmar.setText(String.valueOf(index) + " Kamar");

            }
        });

        fasilitasKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TambahRoomHotel.this, tambahFasilitasKamar.class);
                startActivityForResult(i, 17);
            }
        });

        fasilitasKamarMandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(TambahRoomHotel.this, TambahFasilitasKamarMandi.class);
                startActivityForResult(i, 18);

            }
        });

        deskripsiKmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TambahRoomHotel.this, InputDeskripsiKamar.class);
                startActivityForResult(i, 812);
            }
        });

        kuragStok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index;
                String stokop = stokKmar.getText().toString().trim();
                index = stokop.indexOf(" ");
                stokop = stokop.substring(0, index);

                index = Integer.parseInt(stokop) - 1;

                if (index == 0 || index < 0){
                    index = 0;
                }

                stokKmar.setText(String.valueOf(index) + " Kamar");
            }
        });

        ukuranKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TambahRoomHotel.this);
                View parentView = getLayoutInflater().inflate(R.layout.dialogukurankamar, null);
                bottomSheetDialog.setContentView(parentView);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View)parentView.getParent());
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics()));
                bottomSheetDialog.show();

                ukuranKamar_ = (EditText) parentView.findViewById(R.id.editTextUkuranKamar);
                simpan = (Button)parentView.findViewById(R.id.simpanUkuran);

                simpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!ukuranKamar_.getText().toString().trim().isEmpty()){
                            ukuranKamarFinal = ukuranKamar_.getText().toString().trim() + " m2";
                            ukuranKamar.setText(ukuranKamarFinal);
                            bottomSheetDialog.dismiss();
                        }else{
                            pesanKosong();
                        }
                    }
                });

            }
        });

        tipeTempatTidur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TambahRoomHotel.this);
                View parentView = getLayoutInflater().inflate(R.layout.dialogtipetempattidur, null);
                bottomSheetDialog.setContentView(parentView);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View)parentView.getParent());
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics()));

                radioTipeTempatTidurKamar = (RadioGroup) parentView.findViewById(R.id.bedtype);
                simpanTipeTempattidur = (Button)parentView.findViewById(R.id.simpanTempatTidur);
                final RadioButton single = (RadioButton)parentView.findViewById(R.id.singleBed);
                final RadioButton doublebed = (RadioButton)parentView.findViewById(R.id.doublebed);
                final RadioButton twin = (RadioButton)parentView.findViewById(R.id.twinbed);

                bottomSheetDialog.show();

                simpanTipeTempattidur.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (single.isChecked()){
                            tipeTempatTidurFinal = single.getText().toString().trim();
                        }else if(doublebed.isChecked()){
                            tipeTempatTidurFinal = doublebed.getText().toString().trim();
                        }else{
                            tipeTempatTidurFinal = twin.getText().toString().trim();
                        }

                        tipeTempatTidur.setText(tipeTempatTidurFinal);



                        bottomSheetDialog.dismiss();

                    }
                });


            }
        });

        tambahRoom = (Button)findViewById(R.id.tambahRoom);

        Room = FirebaseDatabase.getInstance().getReference("Room Hotel");


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

        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO6);
            }
        });

        tambahRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idHo = Room.push().getKey().toString();

                Bundle bs = getIntent().getExtras();
                String idHotell = bs.getString("idHotell");

                uploadImage(selectedImage1, idHo,1, idHotell);
                uploadImage(selectedImage2, idHo,2, idHotell);
                uploadImage(selectedImage3, idHo,3, idHotell);
                uploadImage(selectedImage4, idHo,4, idHotell);
                uploadImage(selectedImage5, idHo,5, idHotell);
                uploadImage(selectedImage6, idHo,6, idHotell);

                pesanSukses();
                finish();
            }

        });

    }

    public void uploadImage(Uri selectedImagea, final String idHo, final int indeks, final String hotelIDD) {
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
                Toast.makeText(TambahRoomHotel.this, "Error in uploading!", Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();


                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                Bundle b = getIntent().getExtras();

                String idHotel = sharedPref.getString("idHotel","").toString();

//                String idHotel = b.getCharSequence("id_hotel").toString();




                int index;
                String stokKmr = stokKmar.getText().toString().trim();
                index = stokKmr.indexOf(" ");
                stokKmr = stokKmr.substring(0, index);

                if (indeks == 1) {

                    TambahRoom(hotelIDD, idHo, namaRoom.getText().toString().trim(), Harga.getText().toString().trim(),
                            downloadUrl.toString(), "", "", "", "", "", ukuranKamar.getText().toString().trim(), tipeTempatTidur.getText().toString().trim(),
                            fasilitasKamarFinal, fasilitasKamarMandiFinal,
                            deskripsiKmar.getText().toString().trim(), stokKmr);

                } else if (indeks == 2) {
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("gm2", downloadUrl.toString());

                    FirebaseDatabase.getInstance().getReference().child("Room Hotel").child(idHo).updateChildren(result);
                } else if (indeks == 3) {
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("gm3", downloadUrl.toString());

                    FirebaseDatabase.getInstance().getReference().child("Room Hotel").child(idHo).updateChildren(result);
                } else if (indeks == 4) {
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("gm4", downloadUrl.toString());

                    FirebaseDatabase.getInstance().getReference().child("Room Hotel").child(idHo).updateChildren(result);
                } else if (indeks == 5) {
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("gm5", downloadUrl.toString());

                    FirebaseDatabase.getInstance().getReference().child("Room Hotel").child(idHo).updateChildren(result);
                } else if (indeks == 6) {
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("gm6", downloadUrl.toString());

                    FirebaseDatabase.getInstance().getReference().child("Room Hotel").child(idHo).updateChildren(result);
                }
            }
        });
    }

    private void TambahRoom(String ID_Hotel,String id_room,  String namaRoom, String harga, String gm1, String gm2, String gm3,
                            String gm4, String gm5, String gm6, String ukuranKamar, String tipeTempatTidur, String fasilitasKamar,
                            String fasilitasKamarMandi, String deskripsiKamar, String stok){
        RoomHotel_Fix room = new RoomHotel_Fix(ID_Hotel, id_room, namaRoom, harga, gm1, gm2, gm3, gm4, gm5, gm6,ukuranKamar,
                tipeTempatTidur, fasilitasKamar,
                fasilitasKamarMandi,deskripsiKamar, stok );


        Room.child(id_room).setValue(room);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 812){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("deskripsi");
                deskripsiKmar.setText(result);

            }
        }

        if (requestCode == 17){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("fasilitasKamar");
                fasilitasKamar.setText("Fasilitas telah diset");
                fasilitasKamarFinal = result;
            }
        }

        if (requestCode == 18){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("fasilitasKamarMandi");
                fasilitasKamarMandi.setText("Fasilitas telah diset");
                fasilitasKamarMandiFinal = result;
            }
        }



        switch (requestCode) {
            case SELECT_PHOTO1:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahRoomHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage1 = data.getData();
                    im1.setImageURI(selectedImage1);
                }
                break;

            case SELECT_PHOTO2:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahRoomHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage2 = data.getData();
                    im2.setImageURI(selectedImage2);
                    break;
                }

            case SELECT_PHOTO3:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahRoomHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage3 = data.getData();
                    im3.setImageURI(selectedImage3);
                    break;
                }
            case SELECT_PHOTO4:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahRoomHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage4 = data.getData();
                    im4.setImageURI(selectedImage4);
                    break;
                }
            case SELECT_PHOTO5:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahRoomHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage5 = data.getData();
                    im5.setImageURI(selectedImage5);
                    break;
                }

            case SELECT_PHOTO6:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahRoomHotel.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage6 = data.getData();
                    im6.setImageURI(selectedImage6);
                    break;
                }
        }
    }

    private void pesanKosong(){
        Toast.makeText(TambahRoomHotel.this, "Harap mengisi semua data", Toast.LENGTH_SHORT).show();
    }

    private void pesanSukses(){
        Toast.makeText(TambahRoomHotel.this, "Tambah data sukses", Toast.LENGTH_SHORT).show();
    }


}
