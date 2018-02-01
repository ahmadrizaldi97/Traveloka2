package com.example.ahmadrizaldi.traveloka.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.PemesananHotel;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.Voucher;
import com.example.ahmadrizaldi.traveloka.model.Hotel;
import com.example.ahmadrizaldi.traveloka.model.ReservasiHotel;
import com.example.ahmadrizaldi.traveloka.model.RoomHotel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ahmadrizaldi on 12/28/17.
 */

public class Adapter_Booking_Hotel extends RecyclerView.Adapter<Adapter_Booking_Hotel.ViewHolder> {

    private Context context;
    private ArrayList<ReservasiHotel> rvData = new ArrayList<>();
    private ArrayList<Hotel> dataH = new ArrayList<>();
    private ArrayList<RoomHotel> roomH = new ArrayList<>();

    private DatabaseReference dataHotel, dataRoom;
    Bundle b = new Bundle();

    public Adapter_Booking_Hotel(Context context, ArrayList<ReservasiHotel> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listbookinghotel, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (rvData.get(position).getStatus().equals("Konfirmasi")){
            holder.waktu.setText("VOUCHER HOTEL TELAH TERBIT");
            holder.waktu.setBackgroundColor(Color.parseColor("#00A651"));
            holder.waktu.setTextColor(Color.WHITE);
        } else if(rvData.get(position).getStatus().equals("Waktu Habis")){
            holder.waktu.setText("WAKTU PEMBAYARAN HABIS");
            holder.waktu.setBackgroundColor(Color.parseColor("#f4424b"));
            holder.waktu.setTextColor(Color.WHITE);
        }else if(rvData.get(position).getStatus().equals("Bayar*")){
            holder.waktu.setText("MEMVERIVIKASI PEMBAYARAN ANDA");
        }else if(rvData.get(position).getStatus().equals("Bayar")){
            holder.waktu.setText("UPLOAD BUKTI PEMBAYARAN ANDA");
        }

        holder.namaHotell.setText(rvData.get(position).getNamaHotel());
        holder.tanggalCheckin.setText(rvData.get(position).getTanggalCheckIn());


        String sa = rvData.get(position).getBookingdate().toString().replace("/",".");
        final String sPlit[] = sa.split("\\.");

        Calendar c = Calendar.getInstance();

        Calendar c2 = Calendar.getInstance();


        int posisiTerakhir = 0;
        String tahun = "";
        String bulan = "";

        for (int ci=0; ci< sPlit.length; ci++){
            if (sPlit[ci].indexOf(" ") >1){
                posisiTerakhir = ci;
                break;
            }

            if (ci == 0){
                tahun = sPlit[ci];
                System.out.println("tahun " + sPlit[ci]);
            }

            if (ci == 1){
                bulan = sPlit[ci];
                System.out.println("bulan " + sPlit[ci]);
            }

        }

        String datee = "";
        String jam, menit;
        int index = sPlit[posisiTerakhir].indexOf(" ");
        int index2 = sPlit[posisiTerakhir].indexOf(":");
        datee = sPlit[posisiTerakhir].substring(0, index);
        jam = sPlit[posisiTerakhir].substring(index + 1, index2);
        menit = sPlit[posisiTerakhir].substring(index2 + 1);


        int BulanFix = 0;

        if (bulan.equals("01")){
            BulanFix = Calendar.JANUARY;
        }else if (bulan.equals("02")){
            BulanFix = Calendar.FEBRUARY;
        }else if (bulan.equals("03")){
            BulanFix = Calendar.MARCH;
        }else if (bulan.equals("04")){
            BulanFix = Calendar.APRIL;
        }else if (bulan.equals("05")){
            BulanFix = Calendar.MAY;
        }else if (bulan.equals("06")){
            BulanFix = Calendar.JUNE;
        }else if (bulan.equals("07")){
            BulanFix = Calendar.JULY;
        }else if (bulan.equals("08")){
            BulanFix = Calendar.AUGUST;
        }else if (bulan.equals("09")){
            BulanFix = Calendar.SEPTEMBER;
        }else if (bulan.equals("10")){
            BulanFix = Calendar.OCTOBER;
        }else if (bulan.equals("11")){
            BulanFix = Calendar.NOVEMBER;
        }else if (bulan.equals("12")){
            BulanFix = Calendar.DECEMBER;
        }

        int jam2Fix = 0;
        jam2Fix = Integer.parseInt(jam);

        c2.set(Integer.parseInt(tahun), BulanFix, Integer.parseInt(datee), jam2Fix, Integer.parseInt(menit));



        long millis1 = c.getTimeInMillis();
        long millis2 = c2.getTimeInMillis();

        long diff = millis2 - millis1;
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);



        if (rvData.get(position).getStatus().toString().equals("Bayar*")){
            holder.waktu.setText("MEMVERIVIKASI PEMBAYARAN ANDA");
        }else if (rvData.get(position).getStatus().toString().equals("Belum Bayar")){
            holder.waktu.setText("Menunggu pembayaran : " + String.valueOf(diffMinutes) + " menit");
        }


        holder.namaHotell.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {



                Intent i = new Intent(context, Voucher.class);


                if (rvData.get(position).equals("Waktu Habis")){

                }else {

                    final ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setTitle("Upload Gambar");

                    dataHotel = FirebaseDatabase.getInstance().getReference("Hotel");

                    progressDialog.show();
                    dataHotel.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                            Hotel h = dataSnapshot.getValue(Hotel.class);

                            if (h.getID_Hotel().toString().trim().equals(rvData.get(position).getIdHotel())) {

                                b.putDouble("lat", Double.parseDouble(h.getLat().toString()));
                                b.putDouble("lang", Double.parseDouble(h.getLang().toString()));
                                b.putString("kebikakan", h.getKebijakan().toString());
                                b.putString("deskripsi_h", h.getDeskripsi().toString());
                                b.putString("alamatH", h.getAlamat().toString());
                                b.putString("gm1", h.getGM1());
                                b.putString("gm2", h.getGM2());
                                b.putString("gm3", h.getGM3());
                                b.putString("gm4", h.getGM4());
                                b.putString("gm5", h.getGM5());

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

                    progressDialog.dismiss();


//


//

                    b.putString("checkINN", rvData.get(position).getTanggalCheckIn());
                    b.putString("checkOut", rvData.get(position).getTanggalCheckOut());
                    b.putString("tanggalCheckIn2", rvData.get(position).getTanggalCheckIn().toString());
                    b.putString("id_hotel", rvData.get(position).getIdHotel());
                    b.putString("kodeVoucher", rvData.get(position).getVoucher());
                    b.putString("durasi", rvData.get(position).getDurasi().toString());
                    b.putString("tanggalCheckIn", rvData.get(position).getBookingdate());
                    b.putString("jumlahKamar", rvData.get(position).getJumlahKamar());
                    b.putString("namaHotel", rvData.get(position).getNamaHotel());
                    b.putString("namaRoom", rvData.get(position).getNamaRoom());
                    b.putString("namaPemesan", rvData.get(position).getDataPemesan());
                    b.putString("email",rvData.get(position).getEmail());
                    b.putString("idRoom", rvData.get(position).getIdRoom());
                    b.putString("kodePembayaran", rvData.get(position).getKodePembayaran());
                    b.putString("poinTerpakai", rvData.get(position).getPoinTerpakai());
                    b.putString("poinPending", rvData.get(position).getPoinPending());
                    b.putString("permintaan", rvData.get(position).getPermintaanKhusus());
                    b.putString("poinTerpakai", rvData.get(position).getPoinTerpakai());
                    b.putString("dataPemesan", rvData.get(position).getDataPemesan());
                    b.putString("url", rvData.get(position).getUrl());

//                    String idReservasi, String email, String bookingdate, String idHotel, String idRoom, String bank, String
//                    total, String kodePembayaran, String poinTerpakai, String poinPending, String permintaanKhusus,
// String durasiss, String jumlahKamarss,
//                            String namaHotela, String namaRoomaa, String dataPemesana, String Statuss

                    i.putExtras(b);

                    if (rvData.get(position).getStatus().equals("Belum Bayar")){
                        Intent ia = new Intent(context, PemesananHotel.class);
                        b.putString("total", rvData.get(position).getTotal().toString());
                        b.putString("status", rvData.get(position).getStatus());
                        b.putString("ID_Transaksi", rvData.get(position).getIdReservasi().toString());
                        b.putString("bank",rvData.get(position).getBank().toString());
                        ia.putExtras(b);
                        context.startActivity(ia);

                    }
                    else if (rvData.get(position).getStatus().equals("Bayar") || rvData.get(position).getStatus().equals("Bayar*")){
                        Intent ia = new Intent(context, PemesananHotel.class);
                        b.putString("total", rvData.get(position).getTotal().toString());
                        b.putString("status", rvData.get(position).getStatus());
                        b.putString("ID_Transaksi", rvData.get(position).getIdReservasi().toString());
                        b.putString("bank",rvData.get(position).getBank().toString());
                        ia.putExtras(b);
                        context.startActivity(ia);

                    }else if (rvData.get(position).equals("Waktu Habis")){

                    }
                    else {

                        context.startActivity(i);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tanggalCheckin, namaHotell, waktu;
        public ViewHolder(View itemView) {
            super(itemView);

            namaHotell = (TextView)itemView.findViewById(R.id.namaHotelBooking);
            tanggalCheckin = (TextView)itemView.findViewById(R.id.tanggalCheckinHotell);
            waktu = (TextView)itemView.findViewById(R.id.status_booking_Hotel);

        }
    }



    private void pesan(String pesan){
        Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show();
    }
}
