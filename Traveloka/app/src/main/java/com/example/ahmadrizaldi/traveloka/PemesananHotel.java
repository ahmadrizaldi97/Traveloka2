package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.ReservasiHotel;
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

public class PemesananHotel extends AppCompatActivity {

    private TextView p1, p2, p3, p4, p5, p6, p7;
    private Button button;


    private DatabaseReference bank;
    private String namaRekening = "";

    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;

    public static final String FB_STORAGE_PATH = "image/";
    public static final String SB_DATABASE_PATH = "image";
    public static final int REQUEST_CODE = 1234;

    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan_hotel);

        p1 = (TextView)findViewById(R.id.p1);
        p2 = (TextView)findViewById(R.id.p2);
        p3 = (TextView)findViewById(R.id.p3);
        p4 = (TextView)findViewById(R.id.p4);
        p5 = (TextView)findViewById(R.id.p5);
        p6 = (TextView)findViewById(R.id.p6);
        p7 = (TextView)findViewById(R.id.p7);

        button = (Button)findViewById(R.id.pbutton);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Reservasi Hotel");
        mStorageRef = FirebaseStorage.getInstance().getReference("gambarPemesanan");
        bank = FirebaseDatabase.getInstance().getReference("Rekening");

        final Bundle bs = getIntent().getExtras();

        p1.setText("No. Pemesanan " + bs.getString("ID_Transaksi").toString());

        bank.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Rekening rek = dataSnapshot.getValue(Rekening.class);

                if (bs.getString("bank").equals(rek.getNomor().toString())){
                    namaRekening = rek.getNamaRekening().toString();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (bs.getString("status").equals("Bayar")){

            p6.setText("Memverifikasi pembayaran anda");
            p7.setText("Silahkan upload bukti pembayaran anda untuk memperceoat proses verifikasi");
            button.setText("Upload Bukti Pembayaran");

        }

        if (bs.getString("status").equals("Bayar*")){
            p6.setText("MEMVERIVIKASI PEMBAYARAN ANDA");
            p7.setText("Kami sedang memverivikasi pembayaran Anda melalui Transfer. Proses ini dapat berlangsung hingga 20 menit");

            button.setVisibility(View.INVISIBLE);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bs.getString("status").equals("Belum Bayar")){
                    Intent ia = new Intent(PemesananHotel.this, TransferHotel.class);
                    Bundle b = new Bundle();
                    b.putString("total", bs.getString("total"));
                    b.putString("bank", bs.getString("bank"));
                    b.putString("ID_Transaksi", bs.getString("ID_Transaksi"));
                    ia.putExtras(b);

                    startActivity(ia);

                }


                if (bs.getString("status").equals("Bayar")){

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), REQUEST_CODE);
                }


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


                        Bundle bq = getIntent().getExtras();

                                String Url_ = taskSnapshot.getDownloadUrl().toString();

                        updateReservasiHotel(bq.getString("ID_Transaksi"), bq.getString("email"), bq.getString("tanggalCheckIn"),
                                bq.getString("id_hotel"), bq.getString("idRoom"), bq.getString("bank"),
                                bq.getString("total") , bq.getString("kodePembayaran"), bq.getString("poinTerpakai"),
                                bq.getString("poinPending"), bq.getString("permintaan"), bq.getString("durasi"),
                                bq.getString("jumlahKamar") , bq.getString("namaHotel"), bq.getString("namaRoom"),
                                bq.getString("dataPemesan"), "Bayar*", Url_ , bq.getString("tanggalCheckIn2"), bq.getString("checkOut"));



                        p6.setText("MEMVERIVIKASI PEMBAYARAN ANDA");
                        p7.setText("Kami sedang memverivikasi pembayaran Anda melalui Transfer. Proses ini dapat berlangsung hingga 20 menit");

                        button.setVisibility(View.INVISIBLE);
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

    private void updateReservasiHotel(String idReservasi, String email, String bookingdate, String idHotel, String idRoom, String bank, String
            total, String kodePembayaran, String poinTerpakai, String poinPending, String permintaanKhusus, String durasiss, String jumlahKamarss,
                                      String namaHotela, String namaRoomaa, String dataPemesana, String Statuss, String urla, String tanggalCheckIn, String checkOut){
        ReservasiHotel res = new ReservasiHotel(idReservasi, email, bookingdate, idHotel, idRoom, bank, total, kodePembayaran,
                poinTerpakai, poinPending, permintaanKhusus, Statuss, durasiss, jumlahKamarss, namaHotela, namaRoomaa, dataPemesana, "---", urla, tanggalCheckIn, checkOut);

        mDatabaseRef.child(idReservasi).setValue(res);
    }

    private String getImageExt(Uri imgUri) {

        ContentResolver contentresolver =  getContentResolver();
        MimeTypeMap mim = MimeTypeMap.getSingleton();
        return mim.getExtensionFromMimeType(contentresolver.getType(imgUri));

    }
}
