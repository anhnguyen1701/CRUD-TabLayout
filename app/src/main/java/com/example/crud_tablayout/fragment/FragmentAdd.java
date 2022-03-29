package com.example.crud_tablayout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_tablayout.MainActivity;
import com.example.crud_tablayout.R;
import com.example.crud_tablayout.adapter.CatAdapter;
import com.example.crud_tablayout.adapter.SpinnerAdapter;
import com.example.crud_tablayout.model.Cat;

public class FragmentAdd extends Fragment implements CatAdapter.CatItemListener {
    private CatAdapter adapter;
    private Spinner spinner;
    private EditText editName, editPrice, editInfor;
    private Button btnAdd, btnUpdate;
    private RecyclerView recyclerView;
    private int[] imgs = {R.drawable.meo1, R.drawable.meo2};
    private int pcurr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new CatAdapter((MainActivity) getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this::onItemClick);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(editPrice.getText().toString());
                    Cat cat = new Cat(img, editName.getText().toString(), price, editInfor.getText().toString());
                    adapter.add(cat);
                } catch (NumberFormatException e) {

                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(editPrice.getText().toString());
                    Cat cat = new Cat(img, editName.getText().toString(), price, editInfor.getText().toString());
                    adapter.update(pcurr, cat);
                    btnUpdate.setVisibility(View.INVISIBLE);
                    btnAdd.setVisibility(View.VISIBLE);
                } catch (NumberFormatException e) {

                }
            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        btnAdd.setVisibility(View.INVISIBLE);
        btnUpdate.setVisibility(View.VISIBLE);
        pcurr = position;
        Cat cat = adapter.getItem(position);
        int img = cat.getImg();
        int p = 0;
        for (int i = 0; i < imgs.length; i++) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
        spinner.setSelection(p);
        editName.setText(cat.getName());
        editPrice.setText(cat.getPrice() + "");
        editInfor.setText(cat.getInfor());
    }

    private void initView(View view) {
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(), imgs);
        spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        editName = view.findViewById(R.id.editName);
        editPrice = view.findViewById(R.id.editPrice);
        editInfor = view.findViewById(R.id.editDesc);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        recyclerView = view.findViewById(R.id.reView);

        btnUpdate.setVisibility(View.INVISIBLE);
    }
}
