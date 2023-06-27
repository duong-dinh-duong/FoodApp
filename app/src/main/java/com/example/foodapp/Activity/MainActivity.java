package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodapp.Adaptor.CategoryAdaptor;
import com.example.foodapp.Adaptor.PoplurarAdaptor;
import com.example.foodapp.DoMain.CategoryDomain;
import com.example.foodapp.DoMain.FoodDomain;
import com.example.foodapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategoryList();
        RecyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }
    private void recyclerViewCategoryList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById( R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryDomains =new ArrayList<>();
        categoryDomains.add(new CategoryDomain("Pizza","cat_1"));
        categoryDomains.add(new CategoryDomain("Burger","cat_2"));
        categoryDomains.add(new CategoryDomain("Hotdog","cat_3"));
        categoryDomains.add(new CategoryDomain("Drink","cat_4"));
        categoryDomains.add(new CategoryDomain("Donut","cat_5"));
        adapter =new CategoryAdaptor(categoryDomains);
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void RecyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularlist = findViewById(R.id.recyclerView2);
        recyclerViewPopularlist.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodDomain =new ArrayList<>();
        foodDomain.add(new FoodDomain("Pepperoni pizza","pop_1","slices pepperoni,mozzerella cheese,fresh oregano,ground black pepper,pizza sauce",9.76));
        foodDomain.add(new FoodDomain("Cheese Burger","pop_2","beef,Gouda Cheese,Special Sauce,Lettuce,tomato",8.79));
        foodDomain.add(new FoodDomain("Vegetable pizza","pop_3","olive oil,Vegetable oil,pitted kalamata,cherry tomatoes,fresh oregano,basil",8.79));

        adapter2 =new PoplurarAdaptor(foodDomain);
        recyclerViewPopularlist.setAdapter(adapter2);

    }

}