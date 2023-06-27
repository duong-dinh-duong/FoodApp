package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodapp.Adaptor.CartListAdaptor;
import com.example.foodapp.Helper.Managemetcart;
import com.example.foodapp.Intenface.ChangeNumberItemsListener;
import com.example.foodapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private Managemetcart managemetcart;
    TextView totalFeeTxt, taxTxt, deliveryTxt,totalTxt,emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        managemetcart = new Managemetcart(this);
        initView();
        intiList();
        CalculateCart();
        bottomNavigation();

    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,MainActivity.class));
            }
        });
    }

    private void initView() {
        recyclerViewList =findViewById(R.id.recyclerView);
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        deliveryTxt =findViewById(R.id.deliveryTxt);
        taxTxt =findViewById(R.id.taxTxt);
        totalTxt =findViewById(R.id.totalTxt);
        emptyTxt =findViewById(R.id.emptyTxt);
        scrollView =findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);
    }
    private void intiList()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdaptor(managemetcart.getListCart(),this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(managemetcart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void CalculateCart(){
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managemetcart.getTotalFee()*percentTax)*100)/100;
        double total=Math.round((managemetcart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal=Math.round((managemetcart.getTotalFee())*100)/100;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);

    }

}