package com.example.ahmadrizaldi.traveloka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RoomDetail extends AppCompatActivity {

    private ImageView header, gm1, gm2, gm3, gm4, gm5;
    private TextView namaHotel, hargaHotel, ukuranKamar, tipeTempatTidur, fasilitasKamar,
            fasilitasKamarMandi, deskripsiKamarDetail, poin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_detail);

        header = (ImageView)findViewById(R.id.view_pager1);
        gm1 = (ImageView)findViewById(R.id.r1);
        gm2 = (ImageView) findViewById(R.id.r2);
        gm3 = (ImageView)findViewById(R.id.r3);
        gm4 = (ImageView)findViewById(R.id.r4);
        gm5 = (ImageView)findViewById(R.id.r5);

        poin = (TextView)findViewById(R.id.poinYangAkanDidapat);
        namaHotel = (TextView)findViewById(R.id.namaHotelDet);
        hargaHotel = (TextView)findViewById(R.id.hargaRoomDet);
        ukuranKamar = (TextView)findViewById(R.id.textView131);
        tipeTempatTidur = (TextView)findViewById(R.id.tipeTempatTidurDetail);
        fasilitasKamar = (TextView)findViewById(R.id.fasilitasKamarDetail);
        fasilitasKamarMandi = (TextView)findViewById(R.id.fasilitasKamarMandiDetail);
        deskripsiKamarDetail = (TextView)findViewById(R.id.deskripsiKamarDetail);

        final Bundle bs = getIntent().getExtras();

        loadImageFromUrl(bs.getString("gm1"), header);
        loadImageFromUrl(bs.getString("gm1"), gm1);
        loadImageFromUrl(bs.getString("gm2"), gm2);
        loadImageFromUrl(bs.getString("gm3"), gm3);
        loadImageFromUrl(bs.getString("gm4"), gm4);
        loadImageFromUrl(bs.getString("gm5"), gm5);

        gm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(bs.getString("gm1"), header);
            }
        });

        gm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(bs.getString("gm2"), header);
            }
        });

        gm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(bs.getString("gm3"), header);
            }
        });

        gm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(bs.getString("gm4"), header);
            }
        });

        gm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(bs.getString("gm5"), header);
            }
        });



        poin.setText(bs.getString("poin") + " poin");
        namaHotel.setText(bs.getString("namaHotel").toString());
        hargaHotel.setText("Rp. " + bs.getString("harga").toString());
        ukuranKamar.setText(bs.getString("ukuranKamar").toString());
        tipeTempatTidur.setText(bs.getString("tipeTempatTidur").toString());

        String hasil = "";

        String sa = bs.getString("fasilitasKamar").toString();
        String sPlit[] =sa.split("\\.");

//        fasilitasKamar.setHeight(sPlit.length * 10);

        for (int i=0; i< sPlit.length; i++){
            hasil +=  " • " + sPlit[i] + " \n ";
        }

        fasilitasKamar.setText(hasil);

        String hasil2 = "";
        String sa2 = bs.getString("deskripsiKamarMandi").toString();
        String sPlit2[] =sa2.split("\\.");


//        fasilitasKamarMandi.setHeight(sPlit2.length * 10);
        for (int i=0; i< sPlit2.length; i++){
            hasil2 +=  " • " + sPlit2[i] + " \n ";
        }

        fasilitasKamarMandi.setText(hasil2);

        deskripsiKamarDetail.setText(bs.getString("deskripsiKamar"));



//        b.putString("deskripsiKamar", rvData.get(position).getDeskripsiKamar());
//        b.putString("fasilitasKamar", rvData.get(position).getFasilitasKamar());
//        b.putString("deskripsiKamarMandi", rvData.get(position).getFasilitasKamarMandi());
//        b.putString("gm1", rvData.get(position).getGm1());
//        b.putString("gm2", rvData.get(position).getGm2());
//        b.putString("gm3", rvData.get(position).getGm3());
//        b.putString("gm4", rvData.get(position).getGm4());
//        b.putString("gm5", rvData.get(position).getGm5());
//        b.putString("stok", rvData.get(position).getStok());
//        b.putString("tipeTempatTidur", rvData.get(position).getTipeTempatTidur());
//        b.putString("ukuranKamar", rvData.get(position).getUkuranKamar());
//        b.putString("ID", rvData.get(position).getIDHotel());
//        b.putString("IDRoom", rvData.get(position).getID_Room());
//        b.putString("namaroom", rvData.get(position).getNamaRoom());
//        b.putString("harga", rvData.get(position).getHarga());
//        b.putString("durasi", baa.getCharSequence("durasi").toString());
//        b.putString("tanggalCheckIn", baa.getString("tanggalCheckIn"));
//        b.putString("jumlahKamar", baa.getCharSequence("jumlahKamar").toString());
//        b.putString("kebikakan", baa.getCharSequence("kebikakan").toString());
//        b.putString(("namaHotel"), baa.getCharSequence("namaHotel").toString());
    }

    private void loadImageFromUrl(String uri, ImageView gambar){

        if (!uri.isEmpty()) {

            Picasso.with(RoomDetail.this).load(uri).placeholder(R.drawable.travelokalogo)
                    .error(R.drawable.travelokalogo)
                    .into(gambar, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });
        }

    }
}
