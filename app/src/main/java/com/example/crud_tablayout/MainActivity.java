package com.example.crud_tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.crud_tablayout.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<Cat> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}