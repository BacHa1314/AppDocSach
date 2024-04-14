package com.example.appdoctruyen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen.adapter.adapterTruyen;
import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class nguoidungFragment extends Fragment {
    RecyclerView listView;


    ArrayList<Truyen> TruyenArrayList;
    //
    ArrayList<Truyen> arrayList;

    adapterTruyen adaptertruyen;
    databasedoctruyen databaseDocTruyen;

    public nguoidungFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = getView().findViewById(R.id.listviewtatcatruyen);

        //ActionBar();
        if (MainActivity.check_admin == 2){
            initList();

        }else{

            Toast.makeText(getContext(),"Bạn không có quyền xem",Toast.LENGTH_SHORT).show();
            Log.e("Đăng bài: ","Bạn không có quyền");
        }
        // initList();


    }


    private void filter(String text){


        arrayList.clear();

        ArrayList<Truyen> filteredList = new ArrayList<>();

        for(Truyen item : TruyenArrayList){
            if (item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

                arrayList.add(item);
            }
        }
        adaptertruyen.filterList(filteredList);
    }

    public void initList(){
        TruyenArrayList = new ArrayList<>();
        //
        arrayList = new ArrayList<>();
        databaseDocTruyen = new databasedoctruyen(getContext());

        Cursor cursor1 = databaseDocTruyen.getData();
        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            arrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));


        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        listView.setLayoutManager(linearLayoutManager);

        adaptertruyen=new adapterTruyen(getContext(),arrayList);
        listView.setAdapter(adaptertruyen);

        cursor1.moveToFirst();
        cursor1.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tatcatruyen, container, false);
    }

}
