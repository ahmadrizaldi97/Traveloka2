package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Review_R extends AppCompatActivity {

    private TextView maskapai, kelas, kode_dari, kode_ke, darimana,kemana, nama_bandara_dari, nama_bandara_ke, jam_berangkat, jam_tiba;
    private TextView barisputih_penumpang, penumpang, infobagasi, beratbagasi;
    private TextView namamaskapai_R, hargatiket, kodeunik, totalbayar, poinyangakandidapat;
    private ImageView logo;
    private Button lanjut;

    String namaPenumpang2, namaPennumpangAwal, namaMaskapai;

    private DatabaseReference reschedule, tambahReservasi, DetailReservasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review__r);

        maskapai = (TextView)findViewById(R.id.textView51R);
        kelas = (TextView)findViewById(R.id.textView54R);
        kode_dari = (TextView)findViewById(R.id.textView50R);
        kode_ke = (TextView)findViewById(R.id.textView50sR);
        darimana = (TextView)findViewById(R.id.textView53R);
        kemana = (TextView)findViewById(R.id.textView53sR);
        nama_bandara_dari = (TextView)findViewById(R.id.textView56R);
        nama_bandara_ke = (TextView)findViewById(R.id.textView56sR);
        jam_berangkat = (TextView)findViewById(R.id.textView55R);
        jam_tiba = (TextView)findViewById(R.id.textView55sR);

        barisputih_penumpang = (TextView)findViewById(R.id.textView3sssR);
        penumpang = (TextView)findViewById(R.id.textView59sR);
        infobagasi = (TextView)findViewById(R.id.textView60ssR);
        beratbagasi = (TextView)findViewById(R.id.beratbagasiR);

//        namamaskapai_R = (TextView)findViewById(R.id.textView60R);
//        hargatiket = (TextView)findViewById(R.id.textView62R);
//        kodeunik = (TextView)findViewById(R.id.textView63R);
//        totalbayar = (TextView)findViewById(R.id.textView62sR);

        poinyangakandidapat = (TextView)findViewById(R.id.textView64R);

        logo = (ImageView)findViewById(R.id.imageView6R);

        lanjut = (Button)findViewById(R.id.lanjutkepembayaranR);

        reschedule = FirebaseDatabase.getInstance().getReference("Reschedule");
        tambahReservasi = FirebaseDatabase.getInstance().getReference("Reservasi");
        DetailReservasi = FirebaseDatabase.getInstance().getReference("Detail Reservasi");

        Random rand = new Random();
        int  n1 = rand.nextInt(999) + 1;
        int  n2 = rand.nextInt(999) + 1;
        int  n3 = rand.nextInt(999) + 1;
        int  n4 = rand.nextInt(999) + 1;

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final String Email_ = sharedPref.getString("username",null);

        final String angka2 = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + String.valueOf(n4);

        final Bundle bs = getIntent().getExtras();
//        b.putString("id",  rvData.get(position).getId().toString()  );
//        b.putString("FlightId", rvData.get(position).getFlights_id().toString());
//        b.putString("From", from);
//        b.putString("To", to);
//        b.putString("darimana", darimana);
//        b.putString("kemana", kemana);
//        b.putString("Tanggal", rvData.get(position).getDates().toString());
//        b.putString("JamBerangkat", rvData.get(position).getJamBerangkat().toString());
//        b.putString("JamTiba", rvData.get(position).getJamTiba().toString());
//        b.putString("kelas", kelas);
//        b.putString("harga",holder.harga.getText().toString());
//            b.putString("hargaku", holder.harga.getText().toString());
//            b.putString("idReservasi", idReservasi);
//            b.putString("namaPenumpang", passanger);
//            b.putString("passangers_awal", passAwal);
//            b.putBoolean("langsung", true);
//            b.putString("jumlah_penumpang", jumlah_p);

        poinyangakandidapat.setText("Selesaikan transaksi dan akun ("+  Email_ +") akan mendapatkan 100 Poin");

        String darimanaa = bs.getString("darimana");
        String kemanaa = bs.getString("kemana");
        String kodebandaraa, namaBandaraa;

        int index, index2;
        index = darimanaa.indexOf("^") + 1;
        index2 = darimanaa.indexOf("*");

        kodebandaraa = darimanaa.substring(0, index2);
        namaBandaraa = darimanaa.substring(index2 +1, index -1);
        darimana.setText(namaBandaraa);
        nama_bandara_dari.setText(darimanaa.substring(index));
        kode_dari.setText(kodebandaraa);

        index = kemanaa.indexOf("^") + 1;
        index2 = kemanaa.indexOf("*");

        kodebandaraa = kemanaa.substring(0, index2);
        namaBandaraa = kemanaa.substring(index2 +1, index -1);
        kemana.setText(namaBandaraa);
        nama_bandara_ke.setText(kemanaa.substring(index));
        kode_ke.setText(kodebandaraa);

        index = bs.getString("id").toString().indexOf("*") +1;
        maskapai.setText(bs.getString("id").toString().substring(index) + " " + bs.getString("FlightId").toString());


        kelas.setText(bs.getString("kelas"));


//        int hargaT = Integer.parseInt(bs.getString("hargaTiket").toString()) * Integer.parseInt(bs.getString("jumlah_penumpang").toString());
//        hargatiket.setText("Rp. " + String.valueOf(hargaT));
//
//        int  n7 = rand.nextInt(999) + 1;
//
//        kodeunik.setText(String.valueOf("Rp. " +  n7));
//        int TotalBayar = hargaT + Integer.valueOf(n7);
//        totalbayar.setText("Rp. " + String.valueOf(TotalBayar));

        namaPenumpang2 = bs.getString("namaPenumpang");
        namaPennumpangAwal = bs.getString("passangers_awal");

        namaMaskapai = bs.getString("id").toString().substring(index);
        if (namaMaskapai.equals("Lion Air")){
            logo.setImageResource(R.drawable.lionairlogo);
        }else if (namaMaskapai.equals("Garuda Indonesia")){
            logo.setImageResource(R.drawable.garudalogo);
        }else if (namaMaskapai.equals("Sriwijaya Air")){
            logo.setImageResource(R.drawable.sriwijawalogo);
        }else if (namaMaskapai.equals("Wings Air")){
            logo.setImageResource(R.drawable.wingslogo);
        }else if (namaMaskapai.equals("Citilink")){
            logo.setImageResource(R.drawable.citilink);
        }else if (namaMaskapai.equals("Air Asia")){
            logo.setImageResource(R.drawable.airasia);
        }else if (namaMaskapai.equals("Trigana Air")){
            logo.setImageResource(R.drawable.triganalogo);
        }

        String sPlit[] =namaPenumpang2.split("\\.");

        int banyak = sPlit.length;
        if (String.valueOf(banyak).toString().equals("1")){
            penumpang.setText(sPlit[0]);

        }else if (String.valueOf(banyak).toString().equals("2")){
            penumpang.setText(sPlit[0] + "\n" + sPlit[1]);

        }else if (String.valueOf(banyak).toString().equals("3")){
            penumpang.setText(sPlit[0] + "\n" + sPlit[1] + "\n" + sPlit[2]);

        }else if (String.valueOf(banyak).toString().equals("4")){

            penumpang.setText(sPlit[0] + "\n" + sPlit[1] + "\n" + sPlit[2] + "\n" + sPlit[3]);
        }else{
            penumpang.setText(sPlit[0]);
        }

        lanjut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Review_R.this, MainM.class);
                Bundle b = new Bundle();


                Calendar c = Calendar.getInstance();
                c.add(Calendar.MINUTE, 30);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm");
                String _bookingDate = dateFormat.format(c.getTime());


                reschedule(bs.getString("idReservasi"), "---", bs.getString("FlightId"), bs.getString("kelas"),
                        bs.getString("jumlah_penumpang"), bs.getString("namaPenumpang"), "Menunggu Konfirmasi", bs.getString("id"), angka2);
//                startActivity(i);

                tambahReservasi_method(angka2, bs.getString("id"), bs.getString("kelas"), _bookingDate, "0",  "0", "Menunggu Konfirmasi", "0", bs.getString("namaPenumpang")
                , Email_, "0", "0", bs.getString("jumlah_penumpang"), "0", "0" );

                finish();

            }
        });

    }

    private void tambahReservasi_method(String _idreservasi, String _flights_id, String _kelas, String _bookingDate, String _total, String _pembayaranKe,
                                        String _status, String _Url_, String _nama, String email_, String poins_, String potong, String jumlahPenumpang,
                                        String kodePembayaran, String resccc){

        Reservasi rev = new Reservasi(_idreservasi, _flights_id, _kelas, _bookingDate, _total, _pembayaranKe, _status, _Url_, "---",email_, poins_, potong, jumlahPenumpang, kodePembayaran, resccc, "");
        tambahReservasi.child(_idreservasi).setValue(rev);
//        Toast.makeText(this, "Terimakasih telah memesan di Traveloka", Toast.LENGTH_SHORT).show();
//
        passangerlist sadada = new passangerlist(_idreservasi, _nama);
        DetailReservasi.child(_idreservasi).setValue(sadada);
//
//        potongPoin(email_, potong);

//
        Toast.makeText(Review_R.this, "Thanks", Toast.LENGTH_SHORT).show();
    }

    private void reschedule(String Id_Reservasi, String TotalBayar,String Flight_id,String kelass,String jumlahPenumpang,String daftarPenumpang,String Status, String idd, String idBaru){
        Reschedule resc = new Reschedule(Id_Reservasi, TotalBayar, Flight_id, kelass, jumlahPenumpang, daftarPenumpang, Status, idd, idBaru);
        reschedule.child(Id_Reservasi).setValue(resc);
    }
}
