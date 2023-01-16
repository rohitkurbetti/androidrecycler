package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.adapter.Adapter;
import com.example.myapplication.datamodel.ItemDataModel;
import com.example.myapplication.persistence.DbManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private static List<ItemDataModel> data;
    ItemDataModel itemDatamodel;
    // list of Expensesname
    String[] Expensesname = {"Rent","Coffee","Lunch","Dinner","Transport","Other","Grocery"};
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    Button submitExpenses;
    DbManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DbManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_expenses);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        submitExpenses = findViewById(R.id.submitexpenses);
        data = new ArrayList<>();

        submitExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = "item"+new Random().nextInt(1000);
                ItemDataModel idm = new ItemDataModel(Expensesname.length,item);
                data.add(idm);
                adapter.notifyDataSetChanged();
            }
        });


        // generate ArrayList and store in data model
        for(int i =0;i<Expensesname.length;i++){
            itemDatamodel = new ItemDataModel(
                    i,
                    Expensesname[i]
            );
            data.add(itemDatamodel);
        }
        // call Adapter class by passing ArrayList data
        adapter = new Adapter(data);
        // set adapter to recyclerview
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}