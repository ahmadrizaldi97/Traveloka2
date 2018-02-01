package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.example.ahmadrizaldi.traveloka.model.ReservasiHotel;
import com.example.ahmadrizaldi.traveloka.model.UserList;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadBeforeYouPay_Hotel extends AppCompatActivity {

    private TextView kupon, tukarPoin, JumlahPoin, TotalHargaPoin, namaHotel, hargaHotelKaliBanyak, kodeUnikHotel, potonganHargaAtauPoin,
            totalBayar, poinYangAkandiPeroleh;
    private Button lanjutKeTransfer;

    private String emailUser;
    private int poinUser;

    private int potongharga, poinawal, totalpembayaran, poinTerpakai = 0;

    DatabaseReference dataUser, reservHotel, rekening;
    private String nomorRekening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_before_you_pay__hotel);

        kupon = (TextView)findViewById(R.id.textView701as);
        tukarPoin = (TextView)findViewById(R.id.textView72daio);
        JumlahPoin = (TextView)findViewById(R.id.textView71aaaop);
        TotalHargaPoin = (TextView)findViewById(R.id.textView73ba);
        namaHotel = (TextView)findViewById(R.id.textView60990);
        hargaHotelKaliBanyak = (TextView)findViewById(R.id.textView62aa12);
        kodeUnikHotel = (TextView)findViewById(R.id.textView638);
        potonganHargaAtauPoin = (TextView)findViewById(R.id.textView62sia);
        totalBayar = (TextView)findViewById(R.id.textView62ssa);
        poinYangAkandiPeroleh = (TextView)findViewById(R.id.textView64aaaaa);
        lanjutKeTransfer = (Button)findViewById(R.id.ketransfer_hotel);

        dataUser = FirebaseDatabase.getInstance().getReference("User");
        rekening = FirebaseDatabase.getInstance().getReference("Rekening");
        reservHotel = FirebaseDatabase.getInstance().getReference("Reservasi Hotel");

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        emailUser = sharedPref.getString("username","").toString();
        poinUser = Integer.parseInt(sharedPref.getString("poin","").toString());
        JumlahPoin.setText(String.valueOf(poinUser));
        TotalHargaPoin.setText("Rp. " + String.valueOf(poinUser) + "00");

        final Bundle bs = getIntent().getExtras();

//
        namaHotel.setText(bs.getString("namaHotel") + " " + bs.getString("namaroom")  +" (" + bs.getString("jumlahKamar") +") Kmr " + " ("+ bs.getString("durasi") +") Mlm");
        hargaHotelKaliBanyak.setText("Rp. " + bs.getString("harga"));
        kodeUnikHotel.setText("Rp. " + bs.getString("kodepembayaran"));
        hargaHotelKaliBanyak.setText("Rp. " + bs.getString("hargaKeseluruhan"));

        totalBayar.setText("Rp. " + bs.getString("total"));
        totalpembayaran = Integer.parseInt(bs.getString("total"));

        poinYangAkandiPeroleh.setText("Selesaikan transaksi dan akun (" + emailUser + ") akan mendapatkan " + bs.getCharSequence("poin")+ " Poin");

        rekening.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Rekening rek = dataSnapshot.getValue(Rekening.class);

                if (rek.getNamaRekening().equals(bs.getCharSequence("bank").toString().toLowerCase())){
                    nomorRekening = rek.getNomor();
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

        dataUser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserList userList = dataSnapshot.getValue(UserList.class);
                poinUser = Integer.parseInt(userList.getPoin().toString());
                poinawal = Integer.parseInt(userList.getPoin().toString());
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

        lanjutKeTransfer.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ReadBeforeYouPay_Hotel.this, TransferHotel.class);
                Bundle oo = new Bundle();
                Bundle ba = getIntent().getExtras();
                oo.putString("ID", ba.getCharSequence("ID").toString());
                oo.putString("IDRoom", ba.getString("IDRoom").toString());
                oo.putString("ID_Transaksi", ba.getCharSequence("ID_Transaksi").toString());
                oo.putString("bank", ba.getCharSequence("bank").toString());
                oo.putString("namaroom", ba.getCharSequence("namaroom").toString());
                oo.putString("harga", ba.getCharSequence("harga").toString());
                oo.putString("namaPemesan", ba.getCharSequence("namaPemesan").toString());
                oo.putString("kebikakan",ba.getCharSequence("kebikakan").toString());
                oo.putString(("namaHotel"), ba.getCharSequence("namaHotel").toString());
                oo.putString("kodepembayaran", ba.getCharSequence("kodepembayaran").toString());
                oo.putString(("poinTerpakai"), String.valueOf(poinTerpakai));
                oo.putString("total",String.valueOf(totalpembayaran));
                oo.putString(("poin"), ba.getCharSequence("poin").toString());
                oo.putString("durasi", ba.getString("durasi").toString());
                oo.putString("jumlahKamar", ba.getString("durasi").toString());

                i.putExtras(oo);

                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                final String email = sharedPref.getString("username","").toString();

                String dataPemesan = "";
                dataPemesan = ba.getString("namaPemesan").toString() + "*" + ba.getString("noHp") + "^" + ba.getString("email");

                String _bookingDate = "";
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MINUTE, 30);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm");
                _bookingDate = dateFormat.format(c.getTime());

                TambahReservasiHotel(ba.getString("ID_Transaksi").toString(), email, _bookingDate, ba.getCharSequence("ID").toString(),
                        ba.getString("IDRoom").toString(), nomorRekening, String.valueOf(totalpembayaran),
                        ba.getString("kodepembayaran").toString(), String.valueOf(poinTerpakai), ba.getCharSequence("poin").toString(),
                        ba.getString("permintaanKhusus").toString(), ba.getString("durasi").toString(), ba.getString("jumlahKamar").toString(),
                        ba.getCharSequence("namaHotel").toString(), bs.getString("namaroom"), dataPemesan, bs.getString("tanggalCheckIn"), bs.getString("checkOut"));

//                System.out.println(bs.getString("tanggalCheckIn") + " Tanggal");

                startActivityForResult(i, 912);
            }
        });


        tukarPoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (poinUser < 2000){

                    pesan();
                }else{

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReadBeforeYouPay_Hotel.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_poin, null);
                    final EditText poinygdigunakan = (EditText) mView.findViewById(R.id.jumlahpoinyangakandipakai);
                    Button butt = (Button) mView.findViewById(R.id.gunakanpoin);
                    InputFilterMinMax filter = new InputFilterMinMax("0", String.valueOf(poinUser)) {
                    };

                    poinygdigunakan.setFilters(new InputFilter[]{filter});

                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    butt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            potongharga = Integer.parseInt(poinygdigunakan.getText().toString() + "00");
                            poinTerpakai = Integer.parseInt(poinygdigunakan.getText().toString());

                            if (potongharga == 0) {
                                potonganHargaAtauPoin.setText("Rp. 0");
                                JumlahPoin.setText(poinawal + " Poin");
                                TotalHargaPoin.setText("Rp. " + String.valueOf(poinawal) + "00");
                            } else {
                                potonganHargaAtauPoin.setText("- Rp. " + poinygdigunakan.getText().toString() + "00");
                                JumlahPoin.setText(poinawal - Integer.parseInt(poinygdigunakan.getText().toString()) + " Poin");
                            }

                            TotalHargaPoin.setText("Rp. " + String.valueOf(poinawal - Integer.parseInt(poinygdigunakan.getText().toString())) + "00");

                            Bundle bs = getIntent().getExtras();

                            totalpembayaran = (Integer.parseInt(bs.getString("total")) + Integer.parseInt(bs.getString("kodepembayaran"))) - potongharga;
                            totalBayar.setText("Rp. " + totalpembayaran);
                            dialog.dismiss();
                        }
                    });

                }

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 912){
            if (resultCode == RESULT_OK){

                finish();

            }
        }

    }

    private void potongPoin(final String emailll, final String pot) {

        dataUser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserList ul = dataSnapshot.getValue(UserList.class);

                if (ul.getEmail().equals(emailll)) {
                    update_user(dataSnapshot.getKey(), emailll, String.valueOf(Integer.parseInt(ul.getPoin().toString()) - Integer.parseInt(pot)));
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
    }

    private void update_user(String key, String emai, String poi){
        UserList us = new UserList(emai, poi);
        dataUser.child(key).setValue(us);
    }

    private void TambahReservasiHotel(String idReservasi, String email, String bookingdate, String idHotel, String idRoom, String bank, String
            total, String kodePembayaran, String poinTerpakai, String poinPending, String permintaanKhusus, String durasiss, String jumlahKamarss,
                                      String namaHotela, String namaRoomaa, String dataPemesana, String checIn, String checkOut){
        ReservasiHotel res = new ReservasiHotel(idReservasi, email, bookingdate, idHotel, idRoom, bank, total, kodePembayaran,
                poinTerpakai, poinPending, permintaanKhusus, "Belum Bayar", durasiss, jumlahKamarss, namaHotela, namaRoomaa, dataPemesana, "---", "---", checIn, checkOut);

        reservHotel.child(idReservasi).setValue(res);

        potongPoin(email, String.valueOf(poinTerpakai));

    }

    private void pesan(){
        Toast.makeText(this, "Untuk menukarkan poin, minimum poin yang harus dimiliki adalah 2000", Toast.LENGTH_SHORT).show();
    }
}
