package com.example.ahmadrizaldi.traveloka.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadrizaldi.traveloka.InsertData;
import com.example.ahmadrizaldi.traveloka.InsertData2;
import com.example.ahmadrizaldi.traveloka.InsertData3;
import com.example.ahmadrizaldi.traveloka.InsertData4;
import com.example.ahmadrizaldi.traveloka.R;
import com.example.ahmadrizaldi.traveloka.Review_;
import com.example.ahmadrizaldi.traveloka.Review_R;
import com.example.ahmadrizaldi.traveloka.list.datapenerbangan;
import com.example.ahmadrizaldi.traveloka.model.DetailDataPenerbangan;

import java.util.ArrayList;


/**
 * Created by ahmadrizaldi on 10/12/17.
 */

public class adapter_penerbangantersedia extends RecyclerView.Adapter<adapter_penerbangantersedia.ViewHolder> {

    private Context context;
    private ArrayList<DetailDataPenerbangan> rvData;
    private String jumlah_p, kelas;
    private boolean langsung = false;
    String poin, darimana, kemana = "";
    String passanger, passAwal, idReservasi;
    String namaMaskapai;
    String hargaTiket;

    public adapter_penerbangantersedia(Context context, ArrayList<DetailDataPenerbangan> rvData, String jumlah_pa, String kelass, String darimana_, String kemana_) {
        this.context = context;
        this.rvData = rvData;
        this.jumlah_p = jumlah_pa;
        this.kelas = kelass;
        this.darimana = darimana_;
        this.kemana = kemana_;
    }

    public adapter_penerbangantersedia(Context context, ArrayList<DetailDataPenerbangan> rvData, Boolean langsung, String kelass, String darimana_, String kemana_, String passanger, String pasawal, String idreservasi__, String jumlahPenum) {
        this.context = context;
        this.rvData = rvData;
        this.langsung = langsung;
        this.kelas = kelass;
        this.darimana = darimana_;
        this.kemana = kemana_;
        this.passanger = passanger;
        this.passAwal = pasawal;
        this.idReservasi = idreservasi__;
        this.jumlah_p = jumlahPenum;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listpenerbangantersedia, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.maskapai.setText("Garuda Indonesia");
        holder.logomaskapai.setImageResource(R.drawable.garudalogo);

        int indeks;

        indeks = rvData.get(position).getId().toString().indexOf("*") + 1;

        namaMaskapai = rvData.get(position).getId().substring(indeks);
        holder.maskapai.setText(namaMaskapai);



        if (namaMaskapai.equals("Lion Air")){
            holder.logomaskapai.setImageResource(R.drawable.lionairlogo);
        }else if (namaMaskapai.equals("Garuda Indonesia")){
            holder.logomaskapai.setImageResource(R.drawable.garudalogo);
        }else if (namaMaskapai.equals("Sriwijaya Air")){
            holder.logomaskapai.setImageResource(R.drawable.sriwijawalogo);
        }else if (namaMaskapai.equals("Wings Air")){
            holder.logomaskapai.setImageResource(R.drawable.wingslogo);
        }else if (namaMaskapai.equals("Citilink")){
            holder.logomaskapai.setImageResource(R.drawable.citilink);
        }else if (namaMaskapai.equals("Air Asia")){
            holder.logomaskapai.setImageResource(R.drawable.airasia);
        }else if (namaMaskapai.equals("Trigana Air")){
            holder.logomaskapai.setImageResource(R.drawable.triganalogo);
        }

        String uang = "";


        if (kelas.equals("Economy Class")){


            holder.harga.setText("Rp " +  rvData.get(position).getHarga_E());


            uang = rvData.get(position).getHarga_E().toString();
        }else if (kelas.equals("Business Class")){
            holder.harga.setText("Rp " +  rvData.get(position).getHarga_B());
            uang = rvData.get(position).getHarga_B().toString();
        }else if(kelas.equals("First Class")){
            holder.harga.setText("Rp " +  rvData.get(position).getHarga_F());
            uang = rvData.get(position).getHarga_F().toString();
        }




        int panjang = uang.length();

        if (panjang == 6){
            holder.poin.setText("Dapatkan " + uang.substring(0,2) + " Poin");
            poin = uang.substring(0,2);
        }else if (panjang == 7){
            holder.poin.setText("Dapatkan " + uang.substring(0,3) + " Poin");
            poin = uang.substring(0,3);
        }else if (panjang == 8){
            holder.poin.setText("Dapatkan " + uang.substring(0,4) + " Poin");
            poin = uang.substring(0,4);
        }else if (panjang == 9){
            holder.poin.setText("Dapatkan " + uang.substring(0,5) + " Poin");
            poin = uang.substring(0,5);
        }else if (panjang == 10){
            holder.poin.setText("Dapatkan " + uang.substring(0,6) + " Poin");
            poin = uang.substring(0,6);
        }



        holder.waktu.setText(rvData.get(position).getJamBerangkat().toString() + " - " + rvData.get(position).getJamTiba().toString());

        holder.maskapai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent op = new Intent(context, c());

                int index1;
                index1 = rvData.get(position).getFrom().toString().indexOf("*");

                String from = rvData.get(position).getFrom().toString().substring(0, index1);

                String to = rvData.get(position).getTo().toString().substring(0, index1);

                Bundle b = new Bundle();
                b.putString("id",  rvData.get(position).getId().toString()  );
                b.putString("FlightId", rvData.get(position).getFlights_id().toString());
                b.putString("From", from);
                b.putString("To", to);
                b.putString("darimana", darimana);
                b.putString("kemana", kemana);
                b.putString("Tanggal", rvData.get(position).getDates().toString());
                b.putString("JamBerangkat", rvData.get(position).getJamBerangkat().toString());
                b.putString("JamTiba", rvData.get(position).getJamTiba().toString());
                b.putString("kelas", kelas);
                b.putString("harga",holder.harga.getText().toString());



                if (langsung){
                    int index = holder.harga.getText().toString().indexOf(" ") + 1;
                    String harga = holder.harga.getText().toString().substring(index);
                    b.putString("hargaTiket", harga);
                    b.putString("hargaku", holder.harga.getText().toString());
                    b.putString("idReservasi", idReservasi);
                    b.putString("namaPenumpang", passanger);
                    b.putString("passangers_awal", passAwal);
                    b.putBoolean("langsung", true);
                    b.putString("jumlah_penumpang", jumlah_p);

                }else {
                    b.putString("jumlah_penumpang", jumlah_p);
                }


                b.putString("poin", poin);



                op.putExtras(b);


                System.out.println(passanger);
                context.startActivity(op);
                ((Activity)context).finish();

            }
        });

    }

    private Class c(){

        Class c = null;

        if (String.valueOf(jumlah_p).equals("1")){
            c = InsertData.class;
        }else if (String.valueOf(jumlah_p).equals("2")){
            c = InsertData2.class;
        }else if (String.valueOf(jumlah_p).equals("3")){
            c = InsertData3.class;
        }else if (String.valueOf(jumlah_p).equals("4")){
            c = InsertData4.class;
        }else{
            c = InsertData.class;
        }

        if (langsung == true){
            c = Review_R.class;
        }

        return c;
    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView maskapai, harga, waktu, poin;
        public ImageView logomaskapai;
        public ViewHolder(View itemView) {
            super(itemView);
            maskapai = (TextView)this.itemView.findViewById(R.id.namamaskapaiy);
            logomaskapai = (ImageView)itemView.findViewById(R.id.logomaskapai);
            harga = (TextView)itemView.findViewById(R.id.harga);
            waktu = (TextView)itemView.findViewById(R.id.waktu);
            poin = (TextView)itemView.findViewById(R.id.poinsss);
        }
    }


}
