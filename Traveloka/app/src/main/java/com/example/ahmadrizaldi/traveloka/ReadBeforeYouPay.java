package com.example.ahmadrizaldi.traveloka;

import android.app.Activity;
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

import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.example.ahmadrizaldi.traveloka.model.UserList;

import com.example.ahmadrizaldi.traveloka.model.passangerlist;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ReadBeforeYouPay extends AppCompatActivity {

    private TextView poin, hargapoin, tukarpoin, kodeunik, hargaTiket, potong_poin, totoalbayar, pesawat;
    private DatabaseReference dataUserPoin, datauser, detailReservasi, tambahReservasi, rekening, DataPenerbangan;
    private int poin_int, potongharga, totalpembayaran, Pn, poinawal, poinPindah = 0;
    private Button ketransfer;
    private TextView pesanPoin;

    private String rekening_, namaRekening, __kelas;

    private int kuotaaaa = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_before_you_pay);

        tambahReservasi = FirebaseDatabase.getInstance().getReference("Reservasi");
        datauser = FirebaseDatabase.getInstance().getReference("User");
        detailReservasi = FirebaseDatabase.getInstance().getReference("Detail Reservasi");
        rekening = FirebaseDatabase.getInstance().getReference("Rekening");
        DataPenerbangan = FirebaseDatabase.getInstance().getReference("Detail Flights");

        poin = (TextView)findViewById(R.id.textView71);
        hargapoin = (TextView)findViewById(R.id.textView73);
        tukarpoin = (TextView)findViewById(R.id.textView72);
        kodeunik = (TextView)findViewById(R.id.textView63);
        hargaTiket = (TextView)findViewById(R.id.textView62);
        potong_poin = (TextView)findViewById(R.id.textView62s);
        hargapoin = (TextView)findViewById(R.id.textView73);
        totoalbayar = (TextView)findViewById(R.id.textView62ss);
        pesawat = (TextView)findViewById(R.id.textView60);
        pesanPoin = (TextView)findViewById(R.id.textView64);
        ketransfer = (Button)findViewById(R.id.ketransfer);

        potong_poin.setText("Rp. 0");

        final Bundle bs = getIntent().getExtras();

        totalpembayaran = bs.getInt("total_harga");
        totoalbayar.setText("Rp. " + totalpembayaran);

        pesawat.setText(bs.getString("namaMaskapai").toString() + " x " + bs.getString("jumlah_penumpang"));

//        int jumlahP = Integer.parseInt(bs.getCharSequence("jumlah_penumpang").toString()) * bs.getInt("harga_tiket");
        int harg = bs.getInt("harga_tiket") * Integer.parseInt(bs.getString("jumlah_penumpang"));
        hargaTiket.setText("Rp. " + String.valueOf(harg));

        kodeunik.setText("Rp. " + String.valueOf(bs.getInt("kode_pembayaran")));

        dataUserPoin = FirebaseDatabase.getInstance().getReference("User");

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final String email = sharedPref.getString("username","").toString();

        pesanPoin.setText("Selesaikan transaksi dan akun ("+ email +") akan mendapatkan "+ bs.getString("poin")+" Poin");

        rekening.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Rekening rek = dataSnapshot.getValue(Rekening.class);

                if (rek.getNamaRekening().equals(bs.getCharSequence("BANK").toString().toLowerCase())){
                    rekening_ = rek.getNomor();
                    namaRekening = rek.getAtasNama();
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


        dataUserPoin.orderByChild("email").equalTo(email).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserList datauser = dataSnapshot.getValue(UserList.class);
                int hargaPoints = Integer.parseInt(datauser.getPoin().toString() + "00");
                poin_int = Integer.parseInt(datauser.getPoin().toString());
                if (email.equals(datauser.getEmail())){
                    poin.setText(datauser.getPoin().toString() + " Poin");
                    hargapoin.setText("Rp. " + String.valueOf(hargaPoints));
                    poinawal = Integer.parseInt(datauser.getPoin().toString());
                    Pn = Integer.parseInt(datauser.getPoin().toString());


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


        tukarpoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Pn < 2000){

                    pesan();
                }else {

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReadBeforeYouPay.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_poin, null);
                    final EditText poinygdigunakan = (EditText) mView.findViewById(R.id.jumlahpoinyangakandipakai);
                    Button butt = (Button) mView.findViewById(R.id.gunakanpoin);

                    InputFilterMinMax filter = new InputFilterMinMax("0", String.valueOf(poin_int)) {
                    };
                    poinygdigunakan.setFilters(new InputFilter[]{filter});

                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    butt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            potongharga = Integer.parseInt(poinygdigunakan.getText().toString() + "00");
                            poinPindah = Integer.parseInt(poinygdigunakan.getText().toString());
                            if (potongharga == 0) {
                                potong_poin.setText("Rp. 0");
                                poin.setText(poinawal + " Poin");
                                hargapoin.setText("Rp. " + String.valueOf(poinawal) + "00");
                            } else {
                                potong_poin.setText("- Rp. " + poinygdigunakan.getText().toString() + "00");
                                poin.setText(poinawal - Integer.parseInt(poinygdigunakan.getText().toString()) + " Poin");
                            }

//                            textView62s


                            hargapoin.setText("Rp. " + String.valueOf(poinawal - Integer.parseInt(poinygdigunakan.getText().toString())) + "00");

                            totalpembayaran = (bs.getInt("total_harga") + bs.getInt("kode_pembayaran")) - potongharga;
                            totoalbayar.setText("Rp. " + totalpembayaran);
                            dialog.dismiss();
                        }
                    });
                }

            }
        });

        ketransfer.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                Intent isas = new Intent(ReadBeforeYouPay.this, Transfer.class);
                Bundle b = new Bundle();
                b.putString("maskapai",bs.getCharSequence("namaMaskapai").toString());
                b.putString("idreservasi_", bs.getCharSequence("idreservasi_").toString());
                b.putString("FlightId", bs.getCharSequence("FlightId").toString() );
                b.putString("From", bs.getCharSequence("From").toString());
                b.putString("To", bs.getCharSequence("To").toString());
                b.putString("Tanggal", bs.getCharSequence("Tanggal").toString());
                b.putString("JamBerangkat", bs.getCharSequence("JamBerangkat").toString());
                b.putString("JamTiba", bs.getCharSequence("JamTiba").toString());
                b.putString("kelas", bs.getCharSequence("kelas").toString());
                b.putInt("potongpoin", poinPindah);
                b.putString("poin", bs.getCharSequence("poin").toString());
                b.putString("jumlah_penumpang", bs.getCharSequence("jumlah_penumpang").toString());
                b.putString("darimana", bs.getCharSequence("darimana").toString());
                b.putString("kemana", bs.getCharSequence("kemana").toString());
                b.putString("namaPenumpang", bs.getCharSequence("namaPenumpang").toString());
                b.putInt("harga_tiket", bs.getInt("harga_tiket"));
                b.putInt("kode_pembayaran",bs.getInt("kode_pembayaran"));
                b.putInt("total_harga", totalpembayaran);
                b.putString("BANK",bs.getCharSequence("BANK").toString());
                isas.putExtras(b);

                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String Email_ = sharedPref.getString("username",null);

                b.putString("rekening", rekening_);
                b.putString("namaRekening", namaRekening);

                final String _idreservasi = bs.getCharSequence("idreservasi_").toString();
                final String _flights_id = bs.getString("id").toString();
                String _kelas = bs.getCharSequence("kelas").toString();

                String _bookingDate = "";
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MINUTE, 30);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm");
                _bookingDate = dateFormat.format(c.getTime());


                String _total = String.valueOf(totalpembayaran);
                String _pembayaranKe = rekening_;
                String _status = "Belum Bayar";
                String _Url_ = "---";
                String _nama = bs.getCharSequence("namaPenumpang").toString();
                String email_ = Email_;
                String poins_ = bs.getCharSequence("poin").toString();
                String potong = String.valueOf(poinPindah);
                String jumlahPenumpang = bs.getCharSequence("jumlah_penumpang").toString();
                String kodePembayaran = String.valueOf(bs.getInt("kode_pembayaran"));
                String resccc = "0";

                tambahReservasi_method(_idreservasi, _flights_id, _kelas, _bookingDate,
                         _total, _pembayaranKe, _status, _Url_,  _nama, email_,
                         poins_, potong, jumlahPenumpang, kodePembayaran, resccc);

                tambahKuota(_flights_id, jumlahPenumpang, _kelas);


//                DataPenerbangan.addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                        DetailDataPenerbangan det = dataSnapshot.getValue(DetailDataPenerbangan.class);
//
//                        if (_flights_id.equals(det.getId())) {
//
//                            __kelas = bs.getCharSequence("kelas").toString();
//
//                            int n1, n2, n3;
//                            n2 = Integer.valueOf(bs.getCharSequence("jumlah_penumpang").toString());
//
//                            if (__kelas.equals("Economy Class")) {
//
//                                kuotaaaa = Integer.parseInt(det.getKuota_E().toString());
//                                n1 = kuotaaaa;
//                                n3 = n1 - n2;
//                                UpdateStok("kuota_E", String.valueOf(n3), _flights_id);
//
//                            } else if (__kelas.equals("Business Class")) {
//
//                                kuotaaaa = Integer.parseInt(det.getKuota_B().toString());
//                                n1 = kuotaaaa;
//                                n3 = n1 - n2;
//                                UpdateStok("kuota_B", String.valueOf(n3), _flights_id);
//
//                            } else if (__kelas.equals("First Class")) {
//
//                                kuotaaaa = Integer.parseInt(det.getKuota_F().toString());
//                                n1 = kuotaaaa;
//                                n3 = n1 - n2;
//                                UpdateStok("kuota_F", String.valueOf(n3), _flights_id);
//                            }
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

                startActivityForResult(isas, 404);
            }
        });
    }

    private void tambahKuota(final String idPenerb, final String jumlahP, final String kelass){

        DataPenerbangan.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailDataPenerbangan dat = dataSnapshot.getValue(DetailDataPenerbangan.class);

                if (idPenerb.equals(dat.getId().toString())){


                    String kelas = kelass;
                    int jumlahKuota = 0;

                    if (kelas.equals("Economy Class")){
                        jumlahKuota = Integer.parseInt(dat.getKuota_E());
                        jumlahKuota += Integer.parseInt(jumlahP);

                        DetailDataPenerbangan dataPenerbangan2 = new DetailDataPenerbangan(dat.getId(), dat.getFlights_id(),
                                dat.getFrom(), dat.getTo(), dat.getDates(), dat.getJamBerangkat(),
                                dat.getJamTiba(), String.valueOf(jumlahKuota), dat.getKuota_B(),
                                dat.getKuota_F(), dat.getHarga_E(), dat.getHarga_B(), dat.getHarga_F(), dat.getMaxReschedule());

                        DataPenerbangan.child(dat.getId()).setValue(dataPenerbangan2);

                    }else if (kelas.equals("Business Class")){

                        jumlahKuota = Integer.parseInt(dat.getKuota_E());
                        jumlahKuota += Integer.parseInt(jumlahP);

                        DetailDataPenerbangan dataPenerbangan2 = new DetailDataPenerbangan(dat.getId(), dat.getFlights_id(),
                                dat.getFrom(), dat.getTo(), dat.getDates(), dat.getJamBerangkat(),
                                dat.getJamTiba(), dat.getKuota_E(), String.valueOf(jumlahKuota),
                                dat.getKuota_F(), dat.getHarga_E(), dat.getHarga_B(), dat.getHarga_F(), dat.getMaxReschedule());

                        DataPenerbangan.child(dat.getId()).setValue(dataPenerbangan2);

                    }else if(kelas.equals("First Class")){
                        jumlahKuota = Integer.parseInt(dat.getKuota_E());
                        jumlahKuota += Integer.parseInt(jumlahP);

                        DetailDataPenerbangan dataPenerbangan2 = new DetailDataPenerbangan(dat.getId(), dat.getFlights_id(),
                                dat.getFrom(), dat.getTo(), dat.getDates(), dat.getJamBerangkat(),
                                dat.getJamTiba(), dat.getKuota_E(), dat.getKuota_B(),
                                String.valueOf(jumlahKuota), dat.getHarga_E(), dat.getHarga_B(), dat.getHarga_F(), dat.getMaxReschedule());

                        DataPenerbangan.child(dat.getId()).setValue(dataPenerbangan2);

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

    }

    private void UpdateStok(String kelas, String kuotaSisa, String ID_Penerbangan){

                HashMap<String, Object> result = new HashMap<>();
                result.put(kelas, kuotaSisa);

                FirebaseDatabase.getInstance().getReference().child("Detail Flights").child(ID_Penerbangan).updateChildren(result);
    }

    private void tambahReservasi_method(String _idreservasi, String _flights_id, String _kelas, String _bookingDate, String _total, String _pembayaranKe, String _status, String _Url_, String _nama, String email_, String poins_, String potong, String jumlahPenumpang, String kodePembayaran, String resccc){

        Reservasi rev = new Reservasi(_idreservasi, _flights_id, _kelas, _bookingDate, _total, _pembayaranKe, _status, _Url_, "---",email_, poins_, potong, jumlahPenumpang, kodePembayaran, resccc, "");
        tambahReservasi.child(_idreservasi).setValue(rev);
        Toast.makeText(this, "Terimakasih telah memesan di Traveloka", Toast.LENGTH_SHORT).show();

        passangerlist sadada = new passangerlist(_idreservasi, _nama);
        detailReservasi.child(_idreservasi).setValue(sadada);

        potongPoin(email_, potong);

//
    }

    private void potongPoin(final String emailll, final String pot) {

        datauser.orderByChild("email").equalTo(emailll).addChildEventListener(new ChildEventListener() {
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
        datauser.child(key).setValue(us);
    }

    private void pesan(){
        Toast.makeText(this, "Untuk menukarkan poin, minimum poin yang harus dimiliki adalah 2000", Toast.LENGTH_SHORT).show();
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
