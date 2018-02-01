package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PilihBankTransferR extends AppCompatActivity {

    private TextView bca,mandiri,bri,bni;
    private DatabaseReference dataReservasi, rekening;
    private String nomorRekening = "";
    private String _idreservasi, _flights_id, _kelas, _bookingDate, _total, _pembayaranKe, _status, _Url_, BookingID,
            emaill, poinn, potong, jumlahPenumpang, kodePembayaran, ressss, da, idReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_bank_transfer_r);

        bca = (TextView)findViewById(R.id.bcaaaR);
        mandiri = (TextView)findViewById(R.id.mandiriaR);
        bri = (TextView)findViewById(R.id.briaaaR);
        bni = (TextView)findViewById(R.id.bnis111R);


        dataReservasi = FirebaseDatabase.getInstance().getReference("Reservasi");
        rekening = FirebaseDatabase.getInstance().getReference("Rekening");


        dataReservasi.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reservasi res = dataSnapshot.getValue(Reservasi.class);

                Bundle bs = getIntent().getExtras();
                if (res.getIdreservasi().toString().trim().equals(bs.getString("ID_RESERVASI"))){
                    _idreservasi = bs.getString("ID_RESERVASI");
                    _flights_id = res.getFlights_id();
                    _kelas = res.getKelas();
                    _bookingDate = res.getBookingDate();
                    _total = bs.getString("TOTAL");
                    _status = res.getStatus();
                    _Url_ = res.getUrl();
                    BookingID = res.getPNR();
                    emaill = res.getEmail();
                    poinn = res.getPoinPending();
                    potong = res.getPotongPoin();
                    jumlahPenumpang = res.getJumlahPenumpang();
                    kodePembayaran = res.getKodePembayaran();
                    ressss = res.getReschedule();
                    da = res.getIDBaru();

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

        bca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bs = getIntent().getExtras();
                Intent i = new Intent(PilihBankTransferR.this, Transfer.class);
                Bundle b = new Bundle();
                b.putString("idreservasi_", bs.getString("ID_RESERVASI"));
                b.putString("BANK", "BCA");
                b.putString("TOTAL", bs.getString("TOTAL"));
                i.putExtras(b);

                rekening.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Rekening rek = dataSnapshot.getValue(Rekening.class);

                        if (rek.getNamaRekening().equals("bca")) {

                            update_resv(_idreservasi, _flights_id, _kelas, _bookingDate, _total, rek.getNomor(), "Belum Bayar", _Url_,
                        BookingID, emaill, poinn, potong, jumlahPenumpang, kodePembayaran, ressss, da);

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


                startActivity(i);
                finish();
            }
        });

        mandiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PilihBankTransferR.this, Transfer.class);
                Bundle bs = getIntent().getExtras();
                Bundle b = new Bundle();
                b.putString("BANK", "MANDIRI");
                b.putString("idreservasi_", bs.getString("ID_RESERVASI"));
                b.putString("TOTAL", bs.getString("TOTAL"));
                i.putExtras(b);

                rekening.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Rekening rek = dataSnapshot.getValue(Rekening.class);

                        if (rek.getNamaRekening().equals("mandiri")) {

                            update_resv(_idreservasi, _flights_id, _kelas, _bookingDate, _total, rek.getNomor(), "Belum Bayar", _Url_,
                                    BookingID, emaill, poinn, potong, jumlahPenumpang, kodePembayaran, ressss, da);

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


                startActivity(i);
                finish();
            }
        });

        bri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PilihBankTransferR.this, Transfer.class);
                Bundle bs = getIntent().getExtras();
                Bundle b = new Bundle();
                b.putString("BANK", "BRI");
                b.putString("idreservasi_", bs.getString("ID_RESERVASI"));
                b.putString("TOTAL", bs.getString("TOTAL"));
                i.putExtras(b);

                rekening.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Rekening rek = dataSnapshot.getValue(Rekening.class);

                        if (rek.getNamaRekening().equals("bri")) {

                            update_resv(_idreservasi, _flights_id, _kelas, _bookingDate, _total, rek.getNomor(), "Belum Bayar", _Url_,
                                    BookingID, emaill, poinn, potong, jumlahPenumpang, kodePembayaran, ressss, da);

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

                startActivity(i);
                finish();
            }
        });

        bni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PilihBankTransferR.this, Transfer.class);
                Bundle bs = getIntent().getExtras();
                Bundle b = new Bundle();
                b.putString("BANK", "BNI");
                b.putString("idreservasi_", bs.getString("ID_RESERVASI"));
                b.putString("TOTAL", bs.getString("TOTAL"));
                i.putExtras(b);

                rekening.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Rekening rek = dataSnapshot.getValue(Rekening.class);

                        if (rek.getNamaRekening().equals("bni")) {

                            update_resv(_idreservasi, _flights_id, _kelas, _bookingDate, _total, rek.getNomor(), "Belum Bayar", _Url_,
                                    BookingID, emaill, poinn, potong, jumlahPenumpang, kodePembayaran, ressss, da);

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


                startActivity(i);
                finish();
            }
        });

    }



    private void update_resv(String _idreservasi, String _flights_id, String _kelas, String _bookingDate, String _total, String _pembayaranKe, String _status, String _Url_, String BookingID, String emaill, String poinn, String potong, String jumlahPenumpang,
                             String kodePembayaran, String ressss, String da){

        Reservasi rev = new Reservasi(_idreservasi, _flights_id, _kelas, _bookingDate, _total, _pembayaranKe, _status, _Url_, BookingID, emaill, poinn, potong, jumlahPenumpang, kodePembayaran, ressss, da);
        dataReservasi.child(_idreservasi).setValue(rev);
        Toast.makeText(this, "Konfirmasi Sukses", Toast.LENGTH_SHORT).show();


//        Intent keKonfirmasiPembayaran = new Intent(Transfer.this, KonfirmasiPembayaran.class);
//        startActivity(keKonfirmasiPembayaran);

    }
}
