package com.lucascalderon1.combustivel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.lucascalderon1.combustivel.home.HomeActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ViewPager2 viewPager2;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;
    Button btn_avancar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_avancar = findViewById(R.id.btn_avancar);
        btn_avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        viewPager2 = findViewById(R.id.viewpager);
        int[] images = {R.drawable.veiculos, R.drawable.relatorio, R.drawable.manu, R.drawable.manutencao};
        String[] heading = {"Controle de despesas", "Relatórios", "Manutenção", "Gastos"};
        String[] desc =
                {getString(R.string.heading_1),
                getString(R.string.heading_2),
                getString(R.string.heading_3),
                getString(R.string.heading_4)};


                    viewPagerItemArrayList = new ArrayList<>();

                    for (int i =0; i < images.length ; i++) {
                        ViewPagerItem viewPagerItem = new ViewPagerItem(images[i], heading[i], desc[i]);
                        viewPagerItemArrayList.add(viewPagerItem);

                    }

                    VPAdapter vpAdapter = new VPAdapter(viewPagerItemArrayList);

                    viewPager2.setAdapter(vpAdapter);

                    viewPager2.setClipToPadding(false);
                    viewPager2.setClipChildren(false);
                    viewPager2.setOffscreenPageLimit(2);
                    viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);


    }
    }

