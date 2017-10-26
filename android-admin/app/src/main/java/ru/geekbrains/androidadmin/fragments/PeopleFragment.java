package ru.geekbrains.androidadmin.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.androidadmin.R;
import ru.geekbrains.androidadmin.adapters.MyRecyclerViewAdapter;
import ru.geekbrains.androidadmin.model.PersonInfo;

public class PeopleFragment extends Fragment {

    private List<PersonInfo> persons = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPersons();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        RecyclerView rv = view.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        MyRecyclerViewAdapter rvAdapter = new MyRecyclerViewAdapter(persons);
        rv.setAdapter(rvAdapter);

        return view;
    }

    private void addPersons() {
        persons.add(new PersonInfo("Путин", 100));
        persons.add(new PersonInfo("Мудведев", 50));
        persons.add(new PersonInfo("Навальный", 200));
    }
}
