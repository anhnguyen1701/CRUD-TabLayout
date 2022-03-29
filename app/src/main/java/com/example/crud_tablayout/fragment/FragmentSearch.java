package com.example.crud_tablayout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_tablayout.MainActivity;
import com.example.crud_tablayout.R;
import com.example.crud_tablayout.adapter.SearchAdapter;
import com.example.crud_tablayout.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private SearchAdapter adapter;
    private SearchView search;
    private RecyclerView recyclerView;
    private List<Cat> mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.reViewSearch);
        adapter = new SearchAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
        });
    }

    private void filter(String s) {
        List<Cat> filterList = new ArrayList<>();
        for (Cat i : mList) {
            if (i.getName().toLowerCase().contains(s.toLowerCase())) {
                filterList.add(i);
            }
        }

        if (filterList.isEmpty()) {
            Toast.makeText(getContext(), "khong co du lieu", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setListCat(filterList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mList = ((MainActivity) getActivity()).list;
    }
}
