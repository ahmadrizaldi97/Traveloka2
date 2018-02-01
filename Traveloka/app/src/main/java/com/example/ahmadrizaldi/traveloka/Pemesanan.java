package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.Reservasi;
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

import java.io.FileNotFoundException;
import java.io.IOException;

public class Pemesanan extends AppCompatActivity {

    private Button uploadimage;
    private TextView nomorpesanan, judul, deskripsi;
    private Uri imgUri;
    private DatabaseReference mDatabaseRef, rekening;
    private StorageReference mStorageRef;

    public static final String FB_STORAGE_PATH = "image/";
    public static final String SB_DATABASE_PATH = "image";
    public static final int REQUEST_CODE = 1234;

    private Bundle baa = new Bundle();


    private String Idreservasi ,Flights_id, Kelas, BookingDate, Total_, PembayaranKe, Status, Url_, email_, poin_, potong_p, jumlahPenump, kodepemb, resched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        nomorpesanan = (TextView)findViewById(R.id.textView20);
        uploadimage = (Button)findViewById(R.id.uploadgambar);
        judul = (TextView)findViewById(R.id.textView19tt);
        deskripsi = (TextView)findViewById(R.id.deskripsidikit);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Reservasi");
        mStorageRef = FirebaseStorage.getInstance().getReference("gambarPemesanan");
        rekening = FirebaseDatabase.getInstance().getReference("Rekening");

        Bundle bs = getIntent().getExtras();

        if (bs.getCharSequence("STATUS").toString().equals("Konfirmasi")){

            Intent as = new Intent(Pemesanan.this, ETicket.class);
            Bundle op = new Bundle();

            op.putString("jamBerangkat", bs.getString("jamBerangkat"));
            op.putString("jamTiba", bs.getString("jamTiba"));
            op.putString("tanggalBerangkat", bs.getString("Tanggal"));
            op.putString("darimana", bs.getString("darimana"));
            op.putString("kemana", bs.getString("kemana"));
            op.putString("FLIGHT_ID_02", bs.getString("FLIGHT_ID_02"));
            op.putString("namaMaskapai", bs.getString("namaMaskapai"));
            op.putString("ID_RESERVASI", bs.getCharSequence("ID_RESERVASI").toString());
            op.putString("FLIGHT_ID", bs.getCharSequence("FLIGHT_ID").toString());
            op.putString("KELAS", bs.getCharSequence("KELAS").toString());
            op.putString("BOOKINGDATE", bs.getCharSequence("BOOKINGDATE").toString());
            op.putString("TOTAL", bs.getCharSequence("TOTAL").toString());
            op.putString("PEMBAYARANKE", bs.getCharSequence("PEMBAYARANKE").toString());
            op.putString("STATUS", bs.getCharSequence("STATUS").toString());
            op.putString("URL", bs.getCharSequence("URL").toString());
            op.putString("PNR", bs.getCharSequence("PNR").toString());
            op.putString("idBaru", bs.getCharSequence("idBaru").toString());
            as.putExtras(op);

            startActivity(as);
            finish();
        }else if (bs.getCharSequence("STATUS").toString().equals("Belum Bayar")){

            Intent as = new Intent(Pemesanan.this, Transfer.class);
            Bundle b = new Bundle();

            b.putString("idreservasi_", bs.getString("ID_RESERVASI").toString());

            as.putExtras(b);

            startActivityForResult(as, 404);

        }else if(bs.getString("STATUS").toString().equals("Belum Bayar R")){


            Intent as = new Intent(Pemesanan.this, PilihBankTransferR.class);

            baa.putString("ID_RESERVASI", bs.getString("ID_RESERVASI"));

            baa.putString("TOTAL", bs.getString("TOTAL"));
            as.putExtras(baa);
            System.out.println(bs.getString("TOTAL"));
            startActivity(as);
            finish();

        }else{

        }

        if (!bs.getCharSequence("URL").equals("---")){
            judul.setText("MEMVERIVIKASI PEMBAYARAN ANDA");
            deskripsi.setText("Kami sedang memverivikasi pembayaran Anda melalui Transfer. Proses ini dapat berlangsung hingga 20 menit");
            uploadimage.setVisibility(View.INVISIBLE);
            finish();
        }

        nomorpesanan.setText("Id Reservasi Traveloka " + bs.getCharSequence("ID_RESERVASI").toString());

        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), REQUEST_CODE);

            }
        });

    }

    @SuppressWarnings("VisibleForTests") // hati hati
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            imgUri = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setTitle("Upload Gambar");
                dialog.show();

                StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getImageExt(imgUri));

                ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Upload Sukses",Toast.LENGTH_SHORT).show();

                        imageuploadd imgupload = new imageuploadd(nomorpesanan.getText().toString(), taskSnapshot.getDownloadUrl().toString());

                        String uploadID = mDatabaseRef.push().getKey();

                        Bundle bq = getIntent().getExtras();
                        Idreservasi = bq.getCharSequence("ID_RESERVASI").toString();
                        Flights_id = bq.getCharSequence("FLIGHT_ID").toString();
                        Kelas = bq.getCharSequence("KELAS").toString();
                        BookingDate = bq.getCharSequence("BOOKINGDATE").toString();

                        Total_ = bq.getCharSequence("TOTAL").toString();
                        PembayaranKe = bq.getCharSequence("PEMBAYARANKE").toString();
                        Status = bq.getCharSequence("STATUS").toString();
                        Url_ = taskSnapshot.getDownloadUrl().toString();
                        email_ = bq.getCharSequence("EMAIL").toString();
                        poin_ = bq.getCharSequence("POIN").toString();
                        potong_p = bq.getCharSequence("POIN_P").toString();
                        jumlahPenump = bq.getCharSequence("jumlahPenumpang").toString();
                        kodepemb = bq.getCharSequence("kodePembayaran").toString();
                        resched = bq.getCharSequence("reschedule").toString();

                         update_resv(Idreservasi, Flights_id, Kelas, BookingDate, Total_, PembayaranKe, "Bayar*", Url_, "---", email_, poin_, potong_p, jumlahPenump, kodepemb, resched, "");

                        judul.setText("MEMVERIVIKASI PEMBAYARAN ANDA");
                        deskripsi.setText("Kami sedang memverivikasi pembayaran Anda melalui Transfer. Proses ini dapat berlangsung hingga 20 menit");

                        uploadimage.setVisibility(View.INVISIBLE);
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        })

                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                dialog.setMessage("Upload : " + (int)progress+"%");
                            }
                        });


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "Silahkan pilih gambar",Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 404){
            if (resultCode == RESULT_OK){
                Intent intent_ = new Intent();
                setResult(Activity.RESULT_OK, intent_);
                finish();
            }

        }
    }

    private String getImageExt(Uri imgUri) {

        ContentResolver contentresolver =  getContentResolver();
        MimeTypeMap mim = MimeTypeMap.getSingleton();
        return mim.getExtensionFromMimeType(contentresolver.getType(imgUri));

    }

    private void update_resv(String _idreservasi, String _flights_id, String _kelas, String _bookingDate, String _total, String _pembayaranKe, String _status, String _Url_, String BookingID, String emailllll, String poinnnnn, String potong, String jumlahPenumpang, String kodePembayaran, String reschs, String dsad){

        Reservasi rev = new Reservasi(_idreservasi, _flights_id, _kelas, _bookingDate, _total, _pembayaranKe, _status, _Url_, BookingID, emailllll, poinnnnn, potong, jumlahPenumpang, kodePembayaran, reschs, dsad);
        mDatabaseRef.child(_idreservasi).setValue(rev);


    }
}
