package com.example.ahmadrizaldi.traveloka;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.adapter.ViewPagerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelDetail extends AppCompatActivity{

    private TextView kolam, namaHotel, deskripsi_hotel, hotel_yangdibawah, alamatH, lokasiHotelDetail, hargaPerMalam;
    private Button pilihKamar;
    private FragmentStatePagerAdapter adapter;

    private ImageView pagers, g1, g2, g3, g4, g5, b1, b2, b3, b4,b5;
    private TextView z1,z2,z3,z4,z5,z6,z7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_detail);


        pilihKamar = (Button) findViewById(R.id.pilihkamar__);

        namaHotel = (TextView) findViewById(R.id.textView84);
        deskripsi_hotel = (TextView) findViewById(R.id.deskripsihotel);
        hotel_yangdibawah = (TextView) findViewById(R.id.textView85);
        lokasiHotelDetail = (TextView)findViewById(R.id.lokasiHotelDetail);
        alamatH = (TextView) findViewById(R.id.textView852);

        hargaPerMalam = (TextView)findViewById(R.id.hargaPerMalam);
        pagers = (ImageView)findViewById(R.id.view_pager);

        final Bundle b = getIntent().getExtras();

        g1 = (ImageView)findViewById(R.id.gt1);
        g2 = (ImageView)findViewById(R.id.gt2);
        g3 = (ImageView)findViewById(R.id.gt3);
        g4 = (ImageView)findViewById(R.id.gt4);
        g5 = (ImageView)findViewById(R.id.gt5);

        b1 = (ImageView)findViewById(R.id.bintang1_);
        b2 = (ImageView)findViewById(R.id.bintang2_);
        b3 = (ImageView)findViewById(R.id.bintang3_);
        b4 = (ImageView)findViewById(R.id.bintang4_);
        b5 = (ImageView)findViewById(R.id.bintang5_);

        z1 = (TextView)findViewById(R.id.z1);
        z2 = (TextView)findViewById(R.id.z2);
        z3 = (TextView)findViewById(R.id.z3);
        z4 = (TextView)findViewById(R.id.z4);
        z5 = (TextView)findViewById(R.id.z5);
        z6 = (TextView)findViewById(R.id.z6);
        z7 = (TextView)findViewById(R.id.z7);


        hargaPerMalam.setText("Rp. " + b.getString("mulaiHarga"));

        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(b.getString("gm1").toString(), pagers);
            }
        });

        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(b.getString("gm2").toString(), pagers);
            }
        });

        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(b.getString("gm3").toString(), pagers);
            }
        });

        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(b.getString("gm4").toString(), pagers);
            }
        });

        g5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(b.getString("gm5").toString(), pagers);
            }
        });


        String fasilitas = b.getString("fasilitasUtama");

        if (fasilitas.indexOf("wifi") < 0){
            z1.setVisibility(View.GONE);
        }

        if (fasilitas.indexOf("kolam") < 0){
            z2.setVisibility(View.GONE);
        }

        if (fasilitas.indexOf("restoran") < 0){
            z3.setVisibility(View.GONE);
        }

        if (fasilitas.indexOf("lift") < 0){
            z4.setVisibility(View.GONE);
        }

        if (fasilitas.indexOf("kebugaran") < 0){
            z5.setVisibility(View.GONE);
        }

        if (fasilitas.indexOf("rapat") < 0){
            z6.setVisibility(View.GONE);
        }

        if (fasilitas.indexOf("antar") < 0){
            z7.setVisibility(View.GONE);
        }


//        reputasi
        if (b.getString("reputasi").equals("1")){
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b3.setVisibility(View.INVISIBLE);
            b4.setVisibility(View.INVISIBLE);
            b5.setVisibility(View.INVISIBLE);

        }else if (b.getString("reputasi").equals("2")){
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.INVISIBLE);
            b4.setVisibility(View.INVISIBLE);
            b5.setVisibility(View.INVISIBLE);

        }else if (b.getString("reputasi").equals("3")){
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);
            b4.setVisibility(View.INVISIBLE);
            b5.setVisibility(View.INVISIBLE);
        }else if (b.getString("reputasi").equals("4")){
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);
            b4.setVisibility(View.VISIBLE);
            b5.setVisibility(View.INVISIBLE);
        }else{
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);
            b4.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
        }

        lokasiHotelDetail.setText(b.getString("lokasi"));

        loadImageFromUrl(b.getString("gm1").toString(), g1);
            loadImageFromUrl(b.getString("gm1").toString(), pagers);

            loadImageFromUrl(b.getString("gm2").toString(), g2);
            loadImageFromUrl(b.getString("gm3").toString(), g3);

            loadImageFromUrl(b.getString("gm4").toString(), g4);
            loadImageFromUrl(b.getString("gm5").toString(), g5);


        FragmentMap homeFragment = new FragmentMap();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content2, homeFragment);
        fragmentTransaction.commit();

        Bundle sa = getIntent().getExtras();
        homeFragment.setNamahotel(sa.getCharSequence("namaHotel").toString());
        homeFragment.setLat(sa.getDouble("lat"));
        homeFragment.setLang(sa.getDouble("lang"));

        namaHotel.setText(sa.getString("namaHotel"));
        hotel_yangdibawah.setText(sa.getString("namaHotel"));
        alamatH.setText(sa.getString("alamatH"));
        deskripsi_hotel.setText(sa.getString("deskripsi_h"));


        pilihKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HotelDetail.this, PilihRoomHotel.class);


                Bundle b = new Bundle();
                Bundle ba = getIntent().getExtras();

                b.putString("id_hotel", ba.getString("id_hotel").toString());
                b.putString("durasi", ba.getString("durasi").toString());
                b.putString("tanggalCheckIn", ba.getString("tanggalCheckIn"));
                b.putString("jumlahKamar", ba.getString("jumlahKamar").toString());
                b.putString("kebikakan",ba.getString("kebikakan").toString());
                b.putString("namaHotel", ba.getString("namaHotel").toString());
                b.putString("deskripsi_h", ba.getString("deskripsi_h").toString());
                b.putString("alamatH", ba.getString("alamatH").toString());

                i.putExtras(b);

                startActivity(i);
            }
        });

    }

    private void pesan(){
        Toast.makeText(this, "Suksess", Toast.LENGTH_SHORT).show();
    }

    public boolean googleServicesAvailable(){
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS){
            return true;
        }else if(api.isUserResolvableError(isAvailable)){
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        }
        else{
            Toast.makeText(this, "Tidak dapat menghubungkan ke Google Play Services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }






    private void loadImageFromUrl(String uri, ImageView gambar){

        if (!uri.isEmpty()) {

            Picasso.with(HotelDetail.this).load(uri).placeholder(R.drawable.travelokalogo)
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
