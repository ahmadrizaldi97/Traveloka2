package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.Reschedule;

import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.example.ahmadrizaldi.traveloka.model.passangerlist;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Review_ extends AppCompatActivity {

    private TextView maskapai, kelas, kode_dari, kode_ke, darimana,kemana, nama_bandara_dari, nama_bandara_ke, jam_berangkat, jam_tiba;
    private TextView barisputih_penumpang, penumpang, infobagasi, beratbagasi;
    private TextView namamaskapai_R, hargatiket, kodeunik, totalbayar, poinyangakandidapat;
    private Button lanjut;
    private ImageView logo;
    private DatabaseReference rescs, Reservasiss, penumpanglistss;
    private int harga, kodePembayaran = 0;
    private String id_UnrukReschedule, jumlahReschedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_);

        maskapai = (TextView)findViewById(R.id.textView51);
        kelas = (TextView)findViewById(R.id.textView54);
        kode_dari = (TextView)findViewById(R.id.textView50);
        kode_ke = (TextView)findViewById(R.id.textView50s);
        darimana = (TextView)findViewById(R.id.textView53);
        kemana = (TextView)findViewById(R.id.textView53s);
        nama_bandara_dari = (TextView)findViewById(R.id.textView56);
        nama_bandara_ke = (TextView)findViewById(R.id.textView56s);
        jam_berangkat = (TextView)findViewById(R.id.textView55);
        jam_tiba = (TextView)findViewById(R.id.textView55s);

        barisputih_penumpang = (TextView)findViewById(R.id.textView3sss);
        penumpang = (TextView)findViewById(R.id.textView59s);
        infobagasi = (TextView)findViewById(R.id.textView60ss);
        beratbagasi = (TextView)findViewById(R.id.beratbagasi);

        namamaskapai_R = (TextView)findViewById(R.id.textView60);
        hargatiket = (TextView)findViewById(R.id.textView62);
        kodeunik = (TextView)findViewById(R.id.textView63);
        totalbayar = (TextView)findViewById(R.id.textView62s);

        poinyangakandidapat = (TextView)findViewById(R.id.textView64);

        logo = (ImageView)findViewById(R.id.imageView6);

        lanjut = (Button)findViewById(R.id.lanjutkepembayaran);

        rescs = FirebaseDatabase.getInstance().getReference("Reschedule");
        Reservasiss = FirebaseDatabase.getInstance().getReference("Reservasi");
        penumpanglistss = FirebaseDatabase.getInstance().getReference("Detail Reservasi");

        final Bundle bs = getIntent().getExtras();
        Random rand = new Random();
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final String email = sharedPref.getString("username","").toString();


        int  n1 = rand.nextInt(999) + 1;
        int  n2 = rand.nextInt(999) + 1;
        int  n3 = rand.nextInt(999) + 1;
        int  n4 = rand.nextInt(999) + 1;

        final String angka = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + String.valueOf(n4);

        String s = bs.getCharSequence("namaPenumpang").toString();

        final String sPlit[] =s.split("\\.");
        String banyak;

//        tampil(bs.getCharSequence("harga").toString());

        if (bs.getBoolean("langsung")){
            String uangsa  = bs.getCharSequence("harga").toString();
            uangsa = uangsa.substring(uangsa.indexOf(" ") + 1);
            harga = Integer.parseInt(uangsa) * sPlit.length;
        }else{
            harga = Integer.parseInt(bs.getString("hargaku")) * Integer.parseInt(bs.getCharSequence("jumlah_penumpang").toString());
        }

        tampil(String.valueOf(harga));

        kodePembayaran = rand.nextInt(9999) + 1;

        hargatiket.setText("Rp. " +  String.valueOf(harga));
        harga += kodePembayaran;

        maskapai.setText(bs.getString("namaMaskapai") + " " + bs.getCharSequence("FlightId").toString());

        if (bs.getCharSequence("jumlahpenumpang") == null){
            namamaskapai_R.setText(bs.getString("namaMaskapai") + "(X" + bs.getCharSequence("jumlah_penumpang").toString() + ")" );
        }else{
            namamaskapai_R.setText(bs.getString("namaMaskapai") + "(X" + bs.getCharSequence("jumlah_penumpang").toString()+ ")" );
        }


        kelas.setText(bs.getCharSequence("kelas").toString());
        kode_dari.setText(bs.getCharSequence("From").toString());
        kode_ke.setText(bs.getCharSequence("To").toString());
        darimana.setText(bs.getCharSequence("darimana").toString());
        nama_bandara_dari.setText(bs.getCharSequence("darimana").toString());
        kemana.setText(bs.getCharSequence("kemana").toString());
        nama_bandara_ke.setText(bs.getCharSequence("kemana").toString());
        jam_berangkat.setText(bs.getCharSequence("JamBerangkat").toString());
        jam_tiba.setText(bs.getCharSequence("JamTiba").toString());



        infobagasi.setText(bs.getCharSequence("From").toString() + " - "+bs.getCharSequence("To").toString());



        if (bs.getBoolean("langsung")){

            lanjut.setText("Lanjutkan");

            id_UnrukReschedule = bs.getCharSequence("idReservasi").toString();

            rescs.orderByChild("id_Reservasi").equalTo(id_UnrukReschedule).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Reschedule ressdasd = dataSnapshot.getValue(Reschedule.class);
//                    jumlahReschedule = ressdasd.getReschedule();
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



            if (String.valueOf(bs.getCharSequence("jumlah_penumpang").toString()).equals("1")) {
                penumpang.setText(bs.getCharSequence("namaPenumpang").toString());
                barisputih_penumpang.setHeight(350);
            } else if (bs.getCharSequence("jumlah_penumpang").equals("2")) {
                penumpang.setText(sPlit[0] + "\n" + sPlit[1]);
                barisputih_penumpang.setHeight(450);
            } else if (bs.getCharSequence("jumlah_penumpang").equals("3")) {
                penumpang.setText(sPlit[0] + "\n" + sPlit[1] + "\n" + sPlit[2]);
                barisputih_penumpang.setHeight(550);
            } else if (bs.getCharSequence("jumlah_penumpang").equals("4")) {
                barisputih_penumpang.setHeight(650);
                penumpang.setText(sPlit[0] + "\n" + sPlit[1] + "\n" + sPlit[2] + "\n" + sPlit[3]);
            } else {
                penumpang.setText(bs.getCharSequence("namaPenumpang").toString());
            }

        }else {

            if (String.valueOf(bs.getCharSequence("jumlah_penumpang").toString()).equals("1")) {
                penumpang.setText("1." + bs.getCharSequence("namaPenumpang").toString());
                barisputih_penumpang.setHeight(350);
            } else if (bs.getCharSequence("jumlah_penumpang").equals("2")) {
                penumpang.setText("1." + sPlit[0] + "\n" + "2." + sPlit[1]);
                barisputih_penumpang.setHeight(450);
            } else if (bs.getCharSequence("jumlah_penumpang").equals("3")) {
                penumpang.setText("1." + sPlit[0] + "\n" + "2." +  sPlit[1] + "\n" + "3." + sPlit[2]);
                barisputih_penumpang.setHeight(550);
            } else if (bs.getCharSequence("jumlah_penumpang").equals("4")) {
                barisputih_penumpang.setHeight(650);
                penumpang.setText( "1." + sPlit[0] + "\n" + "2." +  sPlit[1] + "\n" + "3." +  sPlit[2] + "\n" + "4." +  sPlit[3]);
            } else {
                penumpang.setText(bs.getCharSequence("namaPenumpang").toString());
            }
        }




        String id_penerbangan = bs.getString("id");

        if (id_penerbangan.indexOf("Lion Air") > 0){

            logo.setImageResource(R.drawable.lionairlogo);
        }else if (id_penerbangan.indexOf("Garuda Indonesia") > 0){
            logo.setImageResource(R.drawable.garudalogo);
        }else if (id_penerbangan.indexOf("Sriwijaya Air") > 0){
            logo.setImageResource(R.drawable.sriwijawalogo);
        }else if (id_penerbangan.indexOf("Wings Air")  > 0){
            logo.setImageResource(R.drawable.wingslogo);
        }else if (id_penerbangan.indexOf("Citilink")  > 0){
            logo.setImageResource(R.drawable.citilink);
        }else if (id_penerbangan.indexOf("Air Asia")  > 0){
            logo.setImageResource(R.drawable.airasia);
        }else if (id_penerbangan.indexOf("Trigana Air")  > 0){
            logo.setImageResource(R.drawable.triganalogo);
        }



        kodeunik.setText("Rp. " + String.valueOf(kodePembayaran));
        totalbayar.setText("Rp. " + String.valueOf(harga));

        String pesan = "Selesaikan transaksi dan akun ( " + email + " ) akan mendapatkan " + bs.getCharSequence("poin").toString() + " Poin";
        poinyangakandidapat.setText(pesan);


        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle b = new Bundle();
                Intent keReadbeforePay;
                if (bs.getBoolean("langsung") == true){
                    keReadbeforePay = new Intent(Review_.this, MainMenu.class);
                    b.putString("idReservasi", bs.getCharSequence("idReservasi").toString());
                }else{
                    keReadbeforePay = new Intent(Review_.this, PilihBankTransfer.class);
                }

                b.putString("id", bs.getString("id").toString());
                b.putString("namaMaskapai",bs.getString("namaMaskapai"));
                b.putString("idreservasi_", angka);
                b.putString("FlightId", bs.getCharSequence("FlightId").toString() );
                b.putString("From", bs.getCharSequence("From").toString());
                b.putString("To", bs.getCharSequence("To").toString());
                b.putString("Tanggal", bs.getCharSequence("Tanggal").toString());
                b.putString("JamBerangkat", bs.getCharSequence("JamBerangkat").toString());
                b.putString("JamTiba", bs.getCharSequence("JamTiba").toString());
                b.putString("kelas", kelas.getText().toString());
                b.putString("poin", bs.getCharSequence("poin").toString());


                b.putString("darimana", bs.getCharSequence("darimana").toString());
                b.putString("kemana", bs.getCharSequence("kemana").toString());
                b.putString("namaPenumpang", bs.getCharSequence("namaPenumpang").toString());
                b.putString("harga_tiket", bs.getString("hargaku"));
                b.putInt("kode_pembayaran",kodePembayaran);
                b.putInt("total_harga", harga);
                keReadbeforePay.putExtras(b);

                if (bs.getBoolean("langsung")){
                    b.putString("jumlah_penumpang", String.valueOf(sPlit.length));
                    reschedule(bs.getCharSequence("idReservasi").toString(), angka, bs.getCharSequence("FlightId").toString(),
                            kelas.getText().toString(), String.valueOf(sPlit.length), bs.getCharSequence("namaPenumpang").toString(), "Belum Konfirmasi", "a", "---");

                    Random rand = new Random();
                    int  n1 = rand.nextInt(999) + 1;
                    int  n2 = rand.nextInt(999) + 1;
                    int  n3 = rand.nextInt(999) + 1;
                    int  n4 = rand.nextInt(999) + 1;

                    SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    String Email_ = sharedPref.getString("username",null);

                    final String angka2 = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + String.valueOf(n4);
                    reservasisekarang(bs.getCharSequence("idReservasi").toString(), bs.getCharSequence("FlightId").toString() , bs.getCharSequence("kelas").toString(),"3/11/2017", bs.getString("hargaku") , "---","Belum Konfirmasi","---" ,"---", Email_,bs.getCharSequence("poin").toString(),"0",String.valueOf(sPlit.length) ,"0", "1", angka2, bs.getCharSequence("namaPenumpang").toString());


                }else {
                    b.putString("jumlah_penumpang", bs.getCharSequence("jumlah_penumpang").toString());
                }

                keReadbeforePay.putExtras(b);
                startActivityForResult(keReadbeforePay, 404);
            }
        });

    }

   private void reservasisekarang(String idreservasi, String flights_id, String kelas, String bookingDate, String total, String pembayaranKe, String status, String Url_, String pnr, String emailsss, String poins, String PotongPoin_, String JumlahPenumpang_, String KodePembayaran_ , String resss, String IDBaru, String penumpangs){

       Reservasi oop = new Reservasi(idreservasi ,flights_id, kelas, bookingDate, total, pembayaranKe, status, Url_, pnr, emailsss, poins, PotongPoin_, JumlahPenumpang_, KodePembayaran_, resss, IDBaru);
       Reservasiss.child(IDBaru).setValue(oop);

       passangerlist pa = new passangerlist(IDBaru,penumpangs );
       penumpanglistss.child(IDBaru).setValue(pa);

   }

    private void reschedule(String Id_Reservasi, String Id_Reservasi_baru,String Flight_id,String kelass,String jumlahPenumpang,String daftarPenumpang,String Status, String idd, String idLama
    ){
        Reschedule resc = new Reschedule(Id_Reservasi, Id_Reservasi_baru, Flight_id, kelass, jumlahPenumpang, daftarPenumpang, Status, idd, idLama);
        rescs.child(Id_Reservasi).setValue(resc);
    }

    private void tampil(String pesan){
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 404){
            if (resultCode == RESULT_OK){
                Intent intent_ = new Intent();
                setResult(Activity.RESULT_OK, intent_);
                finish();
            }

        }
    }
}
