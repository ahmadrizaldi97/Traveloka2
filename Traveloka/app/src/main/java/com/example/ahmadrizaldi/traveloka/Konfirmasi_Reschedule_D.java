package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;
import com.example.ahmadrizaldi.traveloka.model.Reschedule;
import com.example.ahmadrizaldi.traveloka.model.Reservasi;
import com.example.ahmadrizaldi.traveloka.model.passangerlist;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class Konfirmasi_Reschedule_D extends AppCompatActivity {

    private TextView id, darimana, kemana, penumpang, tanggal, nomorPenerbangan, bandara1, bandara2;
    private ImageView gambarLogo;
    private EditText biayaAdministrasi;
    private String flightid, jumlahReschedule, IdRese, namaMaskapai,kelas, hargaTiketMaskapai, hargaTotalPlusAdministrasi, idFl;
    private Button konfirmasi;
    private DatabaseReference flightbaru, reschedule, dataReservasi, detailReservasi;

    private String bookingDate, email, flights_id, idbaru, idreservasi, jumlahResched, jumlahPenumpang, kodePembayaran, pembayaranKe, pnr, poinPending, potongPoin, status, total, url;
    private long pembayaranSebelumReschedule, jumlahPnmpng, banyakPenumpangReschedule;

    private long pembayaranFinal;
    private long hargaFlightBaruKaliBanyakPenumpang;

    private String idReservasiBaru;

    private String dataPenumpangReservasi, dataPenumpangReschedule, idReservasiLama;
    private String penerbanganLama, penerbanganBaru;

    private long yangSudahDibayar, yangHarusDibayar;

    boolean hapustidak = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi__reschedule__d);

        id = (TextView)findViewById(R.id.textView33dd);
        penumpang = (TextView)findViewById(R.id.textView3999);
        darimana  = (TextView)findViewById(R.id.textView36123);
        kemana = (TextView)findViewById(R.id.dhjsjdsdjsjdhj);
        tanggal = (TextView)findViewById(R.id.textView40);
        nomorPenerbangan =(TextView)findViewById(R.id.flightid_konf);
        biayaAdministrasi = (EditText)findViewById(R.id.biayaAdministrasi);
        bandara1 = (TextView)findViewById(R.id.bandaraaaaa1);
        bandara2 = (TextView)findViewById(R.id.bandaraaaaa2);

        gambarLogo = (ImageView)findViewById(R.id.imageView5);

        konfirmasi = (Button)findViewById(R.id.konfirmasi_reschedule);

        flightbaru = FirebaseDatabase.getInstance().getReference("Detail Flights");
        reschedule = FirebaseDatabase.getInstance().getReference("Reschedule");
        dataReservasi = FirebaseDatabase.getInstance().getReference("Reservasi");
        detailReservasi = FirebaseDatabase.getInstance().getReference("Detail Reservasi");

        final Bundle b = getIntent().getExtras();
        IdRese = b.getString("idReservasi").toString();
        flightid = b.getString("id_penerbangan").toString();
        int index = b.getString("id_penerbangan").indexOf("*") + 1;
        namaMaskapai = b.getString("id_penerbangan").substring(index);
        nomorPenerbangan.setText(b.getString("flightId").toString());

//        maskapai.setText(op.getString("namaMaskapai") + " " + op.getString("FLIGHT_ID_02"));

        if (namaMaskapai.equals("Lion Air")){
            gambarLogo.setImageResource(R.drawable.lionairlogo);
        }else if (namaMaskapai.equals("Garuda Indonesia")){
            gambarLogo.setImageResource(R.drawable.garudalogo);
        }else if (namaMaskapai.equals("Sriwijaya Air")){
            gambarLogo.setImageResource(R.drawable.sriwijawalogo);
        }else if (namaMaskapai.equals("Wings Air")){
            gambarLogo.setImageResource(R.drawable.wingslogo);
        }else if (namaMaskapai.equals("Citilink")){
            gambarLogo.setImageResource(R.drawable.citilink);
        }else if (namaMaskapai.equals("Air Asia")){
            gambarLogo.setImageResource(R.drawable.airasia);
        }else if (namaMaskapai.equals("Trigana Air")){
            gambarLogo.setImageResource(R.drawable.triganalogo);
        }


        reschedule.orderByChild("id_Reservasi").equalTo(b.getString("idReservasi").toString()) .addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reschedule ers = dataSnapshot.getValue(Reschedule.class);

                if (ers.getStatus().toString().equals("Konfirmasi")){
                    konfirmasi.setVisibility(View.INVISIBLE);
                }

                idFl = ers.getId();
                flightid = ers.getFlight_id();
                String jumlahPenump = ers.getJumlahPenumpang().toString();
                idReservasiBaru = ers.getId_baru();

                dataPenumpangReschedule = ers.getDaftarPenumpang().toString();

                String sa = ers.getDaftarPenumpang().toString();

                final String sPlit[] =sa.split("\\.");

                jumlahPnmpng = sPlit.length;

                if (jumlahPenump.equals("1")){
                    penumpang.setText(sPlit[0].toString());
                }else if (jumlahPenump.equals("2")){
                    penumpang.setText(" 1. " + sPlit[0].toString() + "\n 2. " + sPlit[1].toString());
                }else if (jumlahPenump.equals("3")){
                    penumpang.setText(" 1. " + sPlit[0].toString() + "\n 2. " + sPlit[1].toString() + "\n 3. " + sPlit[2].toString());
                }else if (jumlahPenump.equals("4")){
                    penumpang.setText(" 1. " + sPlit[0].toString() + "\n 2. " + sPlit[1].toString() + "\n 3. " + sPlit[2].toString() + "\n 4. " + sPlit[3].toString());
                }else if (jumlahPenump.equals("5")){
                    penumpang.setText(" 1. " + sPlit[0].toString() + "\n 2. " + sPlit[1].toString() + "\n 3. " + sPlit[2].toString() + "\n 5. " + sPlit[3].toString() + "\n 5. " + sPlit[4].toString());
                }

                id.setText(ers.getId_Reservasi().toString());

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

        detailReservasi.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                passangerlist pas = dataSnapshot.getValue(passangerlist.class);

                if (pas.getId_Reservasi().equals(b.getString("idReservasi"))){
                    dataPenumpangReservasi = pas.getNama();
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



        dataReservasi.orderByChild("idreservasi").equalTo(IdRese).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reservasi res = dataSnapshot.getValue(Reservasi.class);

                if (res.getIdreservasi().equals(IdRese)){
                    bookingDate = res.getBookingDate();
                    email = res.getEmail();
                    flights_id = res.getFlights_id();
                    idbaru= res.getIDBaru();
                    idreservasi = res.getIdreservasi();
                    jumlahPenumpang = res.getJumlahPenumpang();
                    kodePembayaran = res.getKodePembayaran();
                    pembayaranKe = res.getPembayaranKe();
                    pnr = res.getPNR();
                    poinPending = res.getPoinPending();
                    potongPoin = res.getPotongPoin();
                    status = res.getStatus();
                    total = res.getTotal();
                    url = res.getUrl();
                    jumlahResched =  res.getReschedule().toString();

                    int jumlahss = Integer.parseInt(jumlahResched) + 1;
                    jumlahResched = String.valueOf(jumlahss);


                    banyakPenumpangReschedule = Integer.valueOf(jumlahPenumpang) -jumlahPnmpng;
                    pembayaranSebelumReschedule = Integer.valueOf(total) - Integer.valueOf(kodePembayaran);

                    yangSudahDibayar = (pembayaranSebelumReschedule / Integer.valueOf(jumlahPenumpang)) * banyakPenumpangReschedule;


                    if (Integer.valueOf(jumlahPenumpang) -jumlahPnmpng == 0){
                        hapustidak = true;

                    }

//                    pembayaranSebelumReschedule = pembayaranSebelumReschedule
//                    pembayaranFinal = pembayaranSebelumReschedule / banyakPenumpangReschedule;

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

        dataReservasi.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reservasi res = dataSnapshot.getValue(Reservasi.class);

                if (res.getIdreservasi().toString().equals(b.getString("idLama"))){
                    yangSudahDibayar = pembayaranSebelumReschedule / Integer.parseInt(res.getJumlahPenumpang());
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

        flightbaru.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailDataPenerbangan dat = dataSnapshot.getValue(DetailDataPenerbangan.class);

                kelas = b.getString("kelas").toString();

                if (dat.getId().toString().equals(idFl)){

                    tanggal.setText(dat.getDates().toString());
                    int index, index2;
                    index = dat.getFrom().toString().indexOf("*");
                    darimana.setText(dat.getFrom().toString().substring(0, index));

                    index2 = dat.getFrom().toString().indexOf("^") + 1;
                    bandara1.setText(dat.getFrom().toString().substring(index2));

                    index = dat.getTo().toString().indexOf("*");
                    kemana.setText(dat.getTo().toString().substring(0, index));

                    index2 = dat.getTo().toString().indexOf("^") + 1;
                    bandara2.setText(dat.getTo().toString().substring(index2));


                    if (kelas.equals("Economy Class")){
                        hargaTiketMaskapai = dat.getHarga_E();
                        hargaFlightBaruKaliBanyakPenumpang = Integer.valueOf(dat.getHarga_E());
                    }else if (kelas.equals("Business Class")){
                        hargaTiketMaskapai = dat.getHarga_B();
                        hargaFlightBaruKaliBanyakPenumpang = Integer.valueOf(dat.getHarga_B());
                    }else if(kelas.equals("First Class")){
                        hargaTiketMaskapai = dat.getHarga_F();
                        hargaFlightBaruKaliBanyakPenumpang = Integer.valueOf(dat.getHarga_F());
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


        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {



                if (!biayaAdministrasi.getText().toString().trim().isEmpty()){
                    pembayaranFinal += Long.valueOf(biayaAdministrasi.getText().toString().trim());

                    System.out.println(idFl + "Dadasdasdasdasdasd");
                    hargaFlightBaruKaliBanyakPenumpang *=  jumlahPnmpng;
                    hargaFlightBaruKaliBanyakPenumpang += Long.valueOf(biayaAdministrasi.getText().toString().trim());


                    System.out.println(yangSudahDibayar + " Sudah Bayar" );
                    System.out.println(hargaFlightBaruKaliBanyakPenumpang + " Harus Bayar" );

                    if (yangSudahDibayar < hargaFlightBaruKaliBanyakPenumpang){
                        System.out.println(yangSudahDibayar + " " + hargaFlightBaruKaliBanyakPenumpang + " bayar dulu");
                        hargaFlightBaruKaliBanyakPenumpang -= yangSudahDibayar;

                        System.out.println(" dadjhgas " + idReservasiBaru);

                        dataReservasi.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Reservasi res = dataSnapshot.getValue(Reservasi.class);

                                if (res.getIdreservasi().toString().trim().equals(idReservasiBaru)){
//
                                    Random rand = new Random();
                                    int  angka2 = rand.nextInt(999) + 1;

                                    hargaFlightBaruKaliBanyakPenumpang += Integer.valueOf(angka2);


                                    update_resv(idReservasiBaru, res.getFlights_id(), res.getKelas(), res.getBookingDate(), String.valueOf(hargaFlightBaruKaliBanyakPenumpang),
                                            res.getPembayaranKe(), "Belum Bayar R", res.getUrl(), "---", res.getEmail(), res.getPoinPending(),
                                            res.getPotongPoin(), res.getJumlahPenumpang(), String.valueOf(angka2), res.getBookingDate(),
                                            res.getIDBaru(), "---");

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

                        reschedule.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Reschedule res = dataSnapshot.getValue(Reschedule.class);

                                if (res.getId_Reservasi().toString().equals(IdRese)){

                                    reschedule_update(res.getId_Reservasi(), String.valueOf(hargaFlightBaruKaliBanyakPenumpang), res.getFlight_id(), res.getKelas(),
                                            res.getJumlahPenumpang(), res.getDaftarPenumpang(), "Konfirmasi", res.getId(), res.getId_baru()  );
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

                        final String reschedule[] = dataPenumpangReschedule.split("\\.");
                        final String reservasi[] = dataPenumpangReservasi.split("\\.");

                        String namaBaru = "";
                        for (int i=0; i< reservasi.length; i++){
                            for (int j=0; j< reschedule.length; j++){
                                if (reservasi[i].equals(reschedule[j])){
                                    System.out.println(reservasi[i] + " dan " + reschedule[j] + " sama");
                                    reservasi[i] = "";
                                }
                            }
                        }

                        for (int i=0; i< reservasi.length; i++){
                            if (!reservasi[i].isEmpty()){

                                namaBaru += reservasi[i] + ".";
                            }

                        }

                        updatePenumpang( IdRese,namaBaru);


                    }else{
                        System.out.println(yangSudahDibayar + " " + hargaFlightBaruKaliBanyakPenumpang + " buat eTicket");

                        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                        StringBuilder salt = new StringBuilder();
                        Random rnd = new Random();
                        while (salt.length() < 6) { // length of the random string.
                            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                            salt.append(SALTCHARS.charAt(index));
                        }

                        final String PNR = salt.toString();

                        String jumlahReschedule = jumlahResched;

                        Calendar c = Calendar.getInstance();
                        c.add(Calendar.MINUTE, 30);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm");
                        final String bookingDate = dateFormat.format(c.getTime());
                        ;
//                        idReservasiBaru

                        dataReservasi.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Reservasi res = dataSnapshot.getValue(Reservasi.class);

                                if (res.getIdreservasi().toString().trim().equals(idReservasiBaru)){

//
                                        update_resv(idReservasiBaru , idFl, res.getKelas(), bookingDate, String.valueOf(hargaFlightBaruKaliBanyakPenumpang),
                                               res.getPembayaranKe(), "Konfirmasi", "---", PNR, res.getEmail(),  res.getPoinPending(), res.getPotongPoin(), res.getJumlahPenumpang()
                                        , res.getKodePembayaran(), jumlahResched, "", idreservasi);

                                    System.out.println(dataPenumpangReschedule + " " + dataPenumpangReservasi);

                                    final String reschedule[] = dataPenumpangReschedule.split("\\.");
                                    final String reservasi[] = dataPenumpangReservasi.split("\\.");

                                    String namaBaru = "";
                                    for (int i=0; i< reservasi.length; i++){
                                        for (int j=0; j< reschedule.length; j++){
                                            if (reservasi[i].equals(reschedule[j])){
                                                System.out.println(reservasi[i] + " dan " + reschedule[j] + " sama");
                                                reservasi[i] = "";
                                            }
                                        }
                                    }

                                    for (int i=0; i< reservasi.length; i++){
                                        if (!reservasi[i].isEmpty()){

                                            namaBaru += reservasi[i] + ".";
                                        }

                                    }

                                    updatePenumpang( IdRese,namaBaru);
                                    System.out.println("Hasil " + namaBaru);

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

                        reschedule.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Reschedule res = dataSnapshot.getValue(Reschedule.class);

                                if (res.getId_Reservasi().toString().equals(IdRese)){

                                    reschedule_update(res.getId_Reservasi(), String.valueOf(hargaFlightBaruKaliBanyakPenumpang), res.getFlight_id(), res.getKelas(),
                                            res.getJumlahPenumpang(), res.getDaftarPenumpang(), "Konfirmasi", res.getId(), res.getId_baru()  );
                                }

                                System.out.println(IdRese + " hahahahahha");
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



                }else{
                    pesanKosong();
                }

                konfirmasi.setVisibility(View.INVISIBLE);

            }
        });


    }

    private void update(String id, String Flights_id, String From, String To, String Dates, String JamBerangkat, String JamTiba ,
                                   String Kuota_E, String Kuota_B, String Kuota_F,
                                   String Harga_E, String Harga_B, String Harga_F, String maxsssssss){

        //private String Flights_id, From, To, Dates, JamBerangkat, JamTiba , Kuota_E, Kuota_B, Kuota_F, Harga_E, Harga_B, Harga_F;

        DetailDataPenerbangan dat = new  DetailDataPenerbangan(id, Flights_id, From, To, Dates, JamBerangkat, JamTiba, Kuota_E, Kuota_B, Kuota_F,
                Harga_E, Harga_B, Harga_F, maxsssssss);

//        flightbaru

    }



    private void update_resv(String _idreservasi, String _flights_id, String _kelas, String _bookingDate, String _total, String _pembayaranKe, String _status, String _Url_, String BookingID, String emaill, String poinn, String potong, String jumlahPenumpang,
                             String kodePembayaran, String ressss, String da, String idReservas){

        Reservasi rev = new Reservasi(_idreservasi, _flights_id, _kelas, _bookingDate, _total, _pembayaranKe, _status, _Url_, BookingID, emaill, poinn, potong, jumlahPenumpang, kodePembayaran, ressss, da);
        dataReservasi.child(_idreservasi).setValue(rev);
        Toast.makeText(this, "Konfirmasi Sukses", Toast.LENGTH_SHORT).show();


    }

    private void updatePenumpang(String idReserv, String nama){
        passangerlist pas = new passangerlist(idReserv, nama);
        detailReservasi.child(idReserv).setValue(pas);

    }

    private void reschedule_update(String Id_Reservasi, String TotalBayar,String Flight_id,String kelass,String jumlahPenumpang,String daftarPenumpang,String Status, String idd, String idBaru){
        Reschedule resc = new Reschedule(Id_Reservasi, TotalBayar, Flight_id, kelass, jumlahPenumpang, daftarPenumpang, Status, idd, idBaru);
        reschedule.child(Id_Reservasi).setValue(resc);
    }

    private void pesanKosong(){
        Toast.makeText(Konfirmasi_Reschedule_D.this, "Biaya administrasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
    }
}
