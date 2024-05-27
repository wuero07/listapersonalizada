package com.example.fruteria;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvTotal;
    private FruitAdapter fruitAdapter;
    private List<Fruit> fruits;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotal = findViewById(R.id.tvTotal);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fruits = new ArrayList<>();
        fruits.add(new Fruit("manzana", R.drawable.manzana));
        fruits.add(new Fruit("Banana", R.drawable.banana));
        fruits.add(new Fruit("uva", R.drawable.uva));
        fruits.add(new Fruit("sandia", R.drawable.sandia));
        fruits.add(new Fruit("naranja", R.drawable.naranja));

        fruitAdapter = new FruitAdapter(fruits, new FruitAdapter.OnQuantityChangedListener() {
            @Override
            public void onQuantityChanged() {
                updateTotal();
            }
        });
        recyclerView.setAdapter(fruitAdapter);

        updateTotal();
    }

    private void updateTotal() {
        int total = 0;
        for (Fruit fruit : fruits) {
            total += fruit.getQuantity();
        }
        tvTotal.setText("Total: " + total);
    }
}

