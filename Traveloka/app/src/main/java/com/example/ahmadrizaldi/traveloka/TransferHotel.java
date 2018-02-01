package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.model.DeskripsiHotel;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.example.ahmadrizaldi.traveloka.model.ReservasiHotel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TransferHotel extends AppCompatActivity {

    private TextView bookingId, jam, keteranganJam, nomorRekening, namaRekening, totalBayar;
    private ImageView logoBank;
    private Button sayaSudahBayar;

    private DatabaseReference bank, reservHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_hotel);

        bookingId = (TextView)findViewById(R.id.bookingidTransf_h);
        jam = (TextView)findViewById(R.id.textView23_h);
        keteranganJam = (TextView)findViewById(R.id.textView24_h);
        nomorRekening = (TextView)findViewById(R.id.nomorrekening_h);
        namaRekening = (TextView)findViewById(R.id.textView30_h);
        totalBayar = (TextView)findViewById(R.id.jumlahTotals_h);

        logoBank = (ImageView) findViewById(R.id.imageView4_h);

        sayaSudahBayar = (Button)findViewById(R.id.sayasudahbayar_h);

        bank = FirebaseDatabase.getInstance().getReference("Rekening");
        reservHotel = FirebaseDatabase.getInstance().getReference("Reservasi Hotel");

        final Bundle ba = getIntent().getExtras();
        bookingId.setText("Traveloka Booking ID " + ba.getCharSequence("ID_Transaksi").toString());
        totalBayar.setText("Rp. " + ba.getString("total"));


        bank.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Rekening rek = dataSnapshot.getValue(Rekening.class);
                String bank = ba.getString("bank");

                if (rek.getNamaRekening().trim().equals(bank.trim())){
                    namaRekening.setText(rek.getAtasNama());
                    nomorRekening.setText(rek.getNomor());

                    if (rek.getNamaRekening().trim().equals("mandiri")){
                        logoBank.setImageResource(R.drawable.mandiri);
                    }else if (rek.getNamaRekening().trim().equals("bri")){
                        logoBank.setImageResource(R.drawable.bri);
                    }else if (rek.getNamaRekening().trim().equals("bca")){
                        logoBank.setImageResource(R.drawable.bca);
                    }else{
                        logoBank.setImageResource(R.drawable.bni);
                    }
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

        sayaSudahBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reservHotel.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        ReservasiHotel res = dataSnapshot.getValue(ReservasiHotel.class);

                        if (res.getIdReservasi().toString().equals(ba.getCharSequence("ID_Transaksi").toString())){
                            TambahReservasiHotel(res.getIdReservasi().toString(), res.getEmail().toString(), res.getBookingdate().toString(), res.getIdHotel().toString(),
                                    res.getIdRoom().toString(), res.getBank().toString(), res.getTotal().toString(), res.getKodePembayaran().toString(), res.getPoinTerpakai().toString(),
                                    res.getPoinPending().toString(), res.getPermintaanKhusus().toString(), res.getDurasi().toString(), res.getJumlahKamar().toString(), res.getNamaHotel().toString(),
                                    res.getNamaRoom().toString(), res.getDataPemesan().toString(), res.getTanggalCheckIn(), res.getTanggalCheckOut());
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

                Intent i = new Intent(TransferHotel.this, MainM.class);
                startActivity(i);
                finish();

            }
        });
    }

    private void TambahReservasiHotel(String idReservasi, String email, String bookingdate, String idHotel, String idRoom, String bank, String
            total, String kodePembayaran, String poinTerpakai, String poinPending, String permintaanKhusus, String durasiss, String jumlahKamarss,
                                      String namaHotela, String namaRoomaa, String dataPemesana, String tanggalCheckIn2, String checkOut){
        ReservasiHotel res = new ReservasiHotel(idReservasi, email, bookingdate, idHotel, idRoom, bank, total, kodePembayaran,
                poinTerpakai, poinPending, permintaanKhusus, "Bayar", durasiss, jumlahKamarss, namaHotela, namaRoomaa, dataPemesana, "---", "---", tanggalCheckIn2, checkOut);

        reservHotel.child(idReservasi).setValue(res);
    }

    public void onBackPressed(){
        Intent intent_ = new Intent();

        setResult(RESULT_OK, intent_);
        TransferHotel.this.setResult(Activity.RESULT_OK, intent_);

        finish();


    }

}
