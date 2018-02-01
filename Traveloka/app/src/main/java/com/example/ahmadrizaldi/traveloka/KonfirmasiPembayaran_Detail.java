package com.example.ahmadrizaldi.traveloka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.example.ahmadrizaldi.traveloka.model.Reschedule;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.example.ahmadrizaldi.traveloka.model.UserList;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.ahmadrizaldi.traveloka.model.passangerlist;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class KonfirmasiPembayaran_Detail extends AppCompatActivity {


    private TextView idTransaksi, tanggal_book, Flightid, Total, namaPenumpang, darimana, kemana, hargaTiket, kodepmb;
    private ImageView bukti, logomaskapai;
    private Button konfirmasi_reservasi;
    private DatabaseReference update_reservasi, passangerlists, loadImg, user, reschedule, dataReservasi, dataPenerbangan;

    private String Idreservasi ,Flights_id000, Kelas, BookingDate, Total_, PembayaranKe, Status, Url, emails, poins, potong_p, jumlahPenump, kodePemb;
    private String getUrl, hargaT, kelas;
    String idBaruss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran__detail);


        idTransaksi = (TextView)findViewById(R.id.textView33);
        tanggal_book = (TextView)findViewById(R.id.textView40);
        Flightid = (TextView)findViewById(R.id.flightid_konf);
        Total = (TextView)findViewById(R.id.total_konf);
        namaPenumpang = (TextView)findViewById(R.id.textView39);
        darimana = (TextView)findViewById(R.id.textView36);
        kemana = (TextView)findViewById(R.id.textView36aaaaaadadiauj);
        hargaTiket = (TextView)findViewById(R.id.textView42);
        kodepmb = (TextView)findViewById(R.id.kodepmb);

        bukti = (ImageView)findViewById(R.id.buktiPembayaran);
        logomaskapai = (ImageView)findViewById(R.id.imageView5);

        konfirmasi_reservasi = (Button)findViewById(R.id.konfirmasi_book);

        dataPenerbangan = FirebaseDatabase.getInstance().getReference("Detail Flights");
        reschedule = FirebaseDatabase.getInstance().getReference("Reschedule");
        update_reservasi = FirebaseDatabase.getInstance().getReference("Reservasi");
        passangerlists = FirebaseDatabase.getInstance().getReference("Detail Reservasi");
        loadImg = FirebaseDatabase.getInstance().getReference("Reservasi");
        user = FirebaseDatabase.getInstance().getReference("User");
        dataReservasi = FirebaseDatabase.getInstance().getReference("Detail Reservasi");

        Bundle b = getIntent().getExtras();

        idBaruss = b.getCharSequence("idbaru").toString();
        Idreservasi = b.getCharSequence("ID_RESERVASI").toString();
        Flights_id000 = b.getCharSequence("FLIGHT_ID").toString();
        BookingDate = b.getCharSequence("BOOKINGDATE").toString();
        Kelas = b.getCharSequence("KELAS").toString();
        kelas = b.getCharSequence("KELAS").toString();
        Total_ = b.getCharSequence("TOTAL").toString();
        PembayaranKe = b.getCharSequence("PEMBAYARANKE").toString();
        Url = b.getCharSequence("URL").toString();
        emails = b.getCharSequence("EMAIL").toString();
        poins = b.getCharSequence("POIN").toString();
        potong_p = b.getCharSequence("POIN_P").toString();
        jumlahPenump = b.getCharSequence("jumlahPenumpang").toString();
        kodePemb = b.getCharSequence("kodePembayaran").toString();
        kodepmb.setText("Rp. " + b.getString("kodePembayaran"));


        if (idBaruss.equals("")){
            Idreservasi = b.getCharSequence("ID_RESERVASI").toString();
        }else{
            Idreservasi = b.getCharSequence("idbaru").toString();
        }

        int index;
        index = Flights_id000.indexOf("*") + 1;
        String namaMaskapai = Flights_id000.substring(index);

        if (namaMaskapai.equals("Lion Air")){
            logomaskapai.setImageResource(R.drawable.lionairlogo);
        }else if (namaMaskapai.equals("Garuda Indonesia")){
            logomaskapai.setImageResource(R.drawable.garudalogo);
        }else if (namaMaskapai.equals("Sriwijaya Air")){
            logomaskapai.setImageResource(R.drawable.sriwijawalogo);
        }else if (namaMaskapai.equals("Wings Air")){
            logomaskapai.setImageResource(R.drawable.wingslogo);
        }else if (namaMaskapai.equals("Citilink")){
            logomaskapai.setImageResource(R.drawable.citilink);
        }else if (namaMaskapai.equals("Air Asia")){
            logomaskapai.setImageResource(R.drawable.airasia);
        }else if (namaMaskapai.equals("Trigana Air")){
            logomaskapai.setImageResource(R.drawable.triganalogo);
        }

        dataPenerbangan.orderByChild("id").equalTo(Flights_id000).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailDataPenerbangan det = dataSnapshot.getValue(DetailDataPenerbangan.class);
                Flightid.setText(det.getFlights_id());

                int index;
                index = det.getFrom().indexOf("*");
                darimana.setText(det.getFrom().substring(0, index));

                index = det.getTo().indexOf("*");
                kemana.setText(det.getTo().substring(0, index));

                if (kelas.equals("Economy Class")){
                    hargaT = det.getHarga_E();
                }else if (kelas.equals("Business Class")){
                    hargaT = det.getHarga_B();
                }else if(kelas.equals("First Class")){
                    hargaT = det.getHarga_F();
                }

                int hargaTiketa = Integer.valueOf(hargaT) *Integer.parseInt(jumlahPenump);
                hargaTiket.setText("Rp. " + String.valueOf(hargaTiketa));


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

        idTransaksi.setText(Idreservasi);
        tanggal_book.setText(BookingDate);
        Total.setText("Rp. " + Total_);

        if (b.getCharSequence("konfirmasi").equals("iya")){
            konfirmasi_reservasi.setVisibility(View.INVISIBLE);
        }

        konfirmasi_reservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                konfirmasi_reservasi.setVisibility(View.INVISIBLE);

                String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                StringBuilder salt = new StringBuilder();
                Random rnd = new Random();
                while (salt.length() < 6) { // length of the random string.
                    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                    salt.append(SALTCHARS.charAt(index));
                }
                String saltStr = salt.toString();

                if (idBaruss.equals("")){
                    update_resv(Idreservasi, Flights_id000, Kelas, BookingDate, Total_, PembayaranKe, "Konfirmasi", Url, saltStr, emails, poins, potong_p, jumlahPenump, kodePemb, "0", "");

                }else{
                    update_resv(idBaruss, Flights_id000, Kelas, BookingDate, Total_, PembayaranKe, "Konfirmasi", Url, saltStr, emails, poins, potong_p, jumlahPenump, kodePemb, "0", "");

                }

                Bundle bk = getIntent().getExtras();

                if (idBaruss.equals("")){
                    Idreservasi = bk.getCharSequence("ID_RESERVASI").toString();
                }else{
                    String idAwal = bk.getCharSequence("ID_RESERVASI").toString();
                    Idreservasi = bk.getCharSequence("idbaru").toString();

                    reschedule.orderByChild("id_Reservasi").equalTo(idAwal).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            final Reschedule sd = dataSnapshot.getValue(Reschedule.class);

                            final String daftarPenumpang = sd.getDaftarPenumpang().toString();

                            dataReservasi.orderByChild("id_Reservasi").equalTo(sd.getId_Reservasi()).addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    passangerlist ts = dataSnapshot.getValue(passangerlist.class);




                                    String uuu = sd.getDaftarPenumpang();
                                    String uang = ts.getNama();
                                    String sPlit[] =uang.split("\\.");
                                    String sPlit2[] =uuu.split("\\.");
//
                        for (int i=0; i< sPlit.length; i++){
                            for (int c=0; c< sPlit2.length; c++){
                                if (sPlit[i].equals(sPlit2[c])){
                                    System.out.println(sPlit[i] + sPlit2[c] + "========= Kosongkan");
                                }else{

                                    uang = uang.replace(sPlit2[c], "");

                                }
                            }
                        }

                                    if (sPlit2.length == sPlit.length){

                                        hapusReservasi(sd.getId_Reservasi());

                                    }

                                    if (uang.indexOf(".") ==0){
                                        uang = uang.substring(1);
                                    }

                                    System.out.println(sd.getDaftarPenumpang() + "========= Hasilnya");
                                    System.out.println(ts.getNama() + "========= Hasilnya");
                                    System.out.println(uang + "========= Hasilnya");

                                    updateDataku(sd.getId_Reservasi(), uang);

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

                passangerlists.orderByChild("id_Reservasi").equalTo(Idreservasi).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        passangerlist pa = dataSnapshot.getValue(passangerlist.class);

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

                user.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        UserList newPost = dataSnapshot.getValue(UserList.class);
                        if (newPost.getEmail().equals(emails)){

                            int poinlama = Integer.parseInt(newPost.getPoin().toString());
                            int poinbaru = poinlama + Integer.parseInt(poins);

                            update_user(dataSnapshot.getKey() ,emails, String.valueOf(poinbaru));
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
        });



        passangerlists.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                passangerlist newPost = dataSnapshot.getValue(passangerlist.class);



                if (newPost.getId_Reservasi().equals(Idreservasi)){
                    String sa = newPost.getNama().toString();
                    String sPlit[] =sa.split("\\.");

                    int banyak = sPlit.length;
                    if (String.valueOf(banyak).toString().equals("1")){
                        namaPenumpang.setText(sPlit[0]);

                    }else if (String.valueOf(banyak).toString().equals("2")){
                        namaPenumpang.setText(sPlit[0] + "\n" + sPlit[1]);

                    }else if (String.valueOf(banyak).toString().equals("3")){
                        namaPenumpang.setText(sPlit[0] + "\n" + sPlit[1] + "\n" + sPlit[2]);

                    }else if (String.valueOf(banyak).toString().equals("4")){

                        namaPenumpang.setText(sPlit[0] + "\n" + sPlit[1] + "\n" + sPlit[2] + "\n" + sPlit[3]);
                    }else{
                        namaPenumpang.setText(sPlit[0]);
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

        loadImg.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reservasi newPost = dataSnapshot.getValue(Reservasi.class);
                if (newPost.getIdreservasi().equals(Idreservasi)){
                    //namaPenumpang.setText("1. " + newPost.getNama().toString());
                    getUrl = newPost.getUrl().toString();

                }

                loadImageFromUrl(getUrl);
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

    private void hapusReservasi(String idBooking){
        update_reservasi.child(idBooking).removeValue();
    }

    private void update_resv(String _idreservasi, String _flights_id, String _kelas, String _bookingDate, String _total, String _pembayaranKe, String _status, String _Url_, String BookingID, String emaill, String poinn, String potong, String jumlahPenumpang, String kodePembayaran, String ressss, String baruu){

        Reservasi rev = new Reservasi(_idreservasi, _flights_id, _kelas, _bookingDate, _total, _pembayaranKe, _status, _Url_, BookingID, emaill, poinn, potong, jumlahPenumpang, kodePembayaran, ressss, baruu);
        update_reservasi.child(_idreservasi).setValue(rev);
        Toast.makeText(this, "Konfirmasi Sukses", Toast.LENGTH_SHORT).show();

        konfirmasi_reservasi.setVisibility(View.INVISIBLE);

//        Intent keKonfirmasiPembayaran = new Intent(Transfer.this, KonfirmasiPembayaran.class);
//        startActivity(keKonfirmasiPembayaran);

    }

    private void updateDataku(String idReservasi, String daftarPenumpang){
        DatabaseReference datall;
        datall = FirebaseDatabase.getInstance().getReference("Detail Reservasi");

        passangerlist daaaa = new passangerlist(idReservasi, daftarPenumpang);

        datall.child(idReservasi).setValue(daaaa);
    }

    private void updatePassanger(String id_Reservasi, String namaPenum){
        passangerlist pas = new passangerlist(id_Reservasi, namaPenum);
        passangerlists.child(id_Reservasi).setValue(pas);
    }

    private void update_user(String key, String emai, String poi){
        UserList us = new UserList(emai, poi);
        user.child(key).setValue(us);
    }

    private void loadImageFromUrl(String uri){
        Picasso.with(this).load(uri).placeholder(R.drawable.travelokalogo)
                .error(R.drawable.travelokalogo)
                .into(bukti, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

        }
}

