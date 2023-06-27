package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.DoMain.FoodDomain;
import com.example.foodapp.Helper.Managemetcart;
import com.example.foodapp.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView AddToCartBtn;
    private TextView titleTxt,feeTxt,descriptionTxt,numberOrderTxt;
    private ImageView plusbtn, minusBtn,picFood;
    private FoodDomain object;
    int numBerOrder=1;
    private Managemetcart managemetcart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        managemetcart =new Managemetcart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object =(FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResoureceId =this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this).load(drawableResoureceId).into(picFood);
        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numBerOrder));

        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numBerOrder=numBerOrder+1;
                numberOrderTxt.setText(String.valueOf(numBerOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numBerOrder>1){
                    numBerOrder=numBerOrder-1;
                    numberOrderTxt.setText(String.valueOf(numBerOrder));
                }
            }
        });

        AddToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numBerOrder);
                managemetcart.insertFood(object);
            }
        });

    }

    private void initView() {
        AddToCartBtn=findViewById(R.id.AddToCartBtn);
        titleTxt =findViewById(R.id.titleTxt);
        feeTxt =findViewById(R.id.priceTxt);
        descriptionTxt =findViewById(R.id.descriptionTxt);
        numberOrderTxt =findViewById(R.id.numberOrderTxt);
        plusbtn =findViewById(R.id.plusBtn);
        minusBtn =findViewById(R.id.minusBtn);
        picFood =findViewById(R.id.picfood);
    }
}