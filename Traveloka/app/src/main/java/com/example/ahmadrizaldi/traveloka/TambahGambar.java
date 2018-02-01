package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class TambahGambar extends AppCompatActivity {

    private ImageView im1, im2, im3, im4, im5;
    private Button tambah;

    private Uri imgUri1,imgUri2,imgUri3,imgUri4,imgUri5;

    public static final int REQUEST_CODE = 1;
    public static final int REQUEST_CODE1 = 2;
    public static final int REQUEST_CODE2 = 3;
    public static final int REQUEST_CODE3 = 4;
    public static final int REQUEST_CODE4 = 5;



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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_tambah_gambar);

        im1 = (ImageView)findViewById(R.id.gambar1);
        im2 = (ImageView)findViewById(R.id.gambar2);
        im3 = (ImageView)findViewById(R.id.gambar3);
        im4 = (ImageView)findViewById(R.id.gambar4);
        im5 = (ImageView)findViewById(R.id.gambar5);

        tambah = (Button)findViewById(R.id.tambahGambar);

        Bundle i = getIntent().getExtras();

        ////////////////////
//        if (i.getCharSequence("imageUri1") != null){
//            imgUri1 = Uri.parse(i.getCharSequence("imageUri1").toString());
//            im1.setImageURI(imgUri1);
//        }
//
//        if (i.getCharSequence("imageUri2") != null){
//            imgUri2 = Uri.parse(i.getCharSequence("imageUri2").toString());
//            im2.setImageURI(imgUri2);
//        }
//
//        if (i.getCharSequence("imageUri5") != null){
//            imgUri5 = Uri.parse(i.getCharSequence("imageUri5").toString());
//            im5.setImageURI(imgUri5);
//        }
        ////////////////////


        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_ = new Intent();

                Bundle b = new Bundle();

                    intent_.putExtra("imageUri1", selectedImage1.toString());



                setResult(RESULT_OK, intent_);


                TambahGambar.this.setResult(Activity.RESULT_OK, intent_);

                finish();
            }
        });

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 100);

//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), REQUEST_CODE);
            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), REQUEST_CODE1);
            }
        });

        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), REQUEST_CODE2);
            }
        });

        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), REQUEST_CODE3);
            }
        });

        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), REQUEST_CODE4);
            }
        });




        tambah = (Button)findViewById(R.id.tambahGambar);
    }

    @SuppressWarnings("VisibleForTests") // hati hati
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SELECT_PHOTO1:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahGambar.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage1 = data.getData();

//                    System.out.println("ini gambarnya " + selectedImage1.toString());
                }
                break;

            case SELECT_PHOTO2:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(TambahGambar.this,"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    selectedImage2 = data.getData();
                }
        }


//
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
//            imgUri1 = data.getData();
////            ias
//
//                try {
//                    Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri1);
//                    im1.setImageBitmap(bm);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//        }else if (requestCode == REQUEST_CODE1 && resultCode == RESULT_OK && data != null && data.getData() != null){
//            imgUri2 = data.getData();
////            ias
//
//            try {
//                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri2);
//                im2.setImageBitmap(bm);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }else if (requestCode == REQUEST_CODE2 && resultCode == RESULT_OK && data != null && data.getData() != null){
//            imgUri3 = data.getData();
////            ias
//
//            try {
//                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri3);
//                im3.setImageBitmap(bm);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }else if (requestCode == REQUEST_CODE3 && resultCode == RESULT_OK && data != null && data.getData() != null){
//            imgUri4 = data.getData();
////            ias
//
//            try {
//                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri4);
//                im4.setImageBitmap(bm);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }else if (requestCode == REQUEST_CODE4 && resultCode == RESULT_OK && data != null && data.getData() != null){
//            imgUri5 = data.getData();
////            ias
//
//            try {
//                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri5);
//                im5.setImageBitmap(bm);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        else{
//            Toast.makeText(this, "Silahkan pilih gambar",Toast.LENGTH_SHORT).show();
//        }
    }

    private String getImageExt(Uri imgUri) {

        ContentResolver contentresolver =  getContentResolver();
        MimeTypeMap mim = MimeTypeMap.getSingleton();
        return mim.getExtensionFromMimeType(contentresolver.getType(imgUri));

    }
}
