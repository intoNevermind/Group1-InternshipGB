package ru.geekbrains.androidadmin.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.geekbrains.androidadmin.R;
import ru.geekbrains.androidadmin.activities.BaseActivity;
import ru.geekbrains.androidadmin.adapters.MyRecyclerViewAdapter;
import ru.geekbrains.androidadmin.model.Keyword;
import ru.geekbrains.androidadmin.model.Person;
import ru.geekbrains.androidadmin.model.Site;
import ru.geekbrains.androidadmin.model.User;

public class BaseFragment<T> extends Fragment {

    private String LOG_TAG;

    private MyRecyclerViewAdapter rvAdapter;
    private T type;

    public static <T> BaseFragment<T> newInstance(List<T> data, T type, Context context) {
        BaseFragment<T> fragment = new BaseFragment<>();
        fragment.type = type;
        fragment.rvAdapter = new MyRecyclerViewAdapter(data, context);
        fragment.LOG_TAG = "BaseFragment " + type.getClass().getSimpleName();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);

        RecyclerView rv = view.findViewById(R.id.recycler_view);
        final FloatingActionButton fab = view.findViewById(R.id.fab_fragment);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        rv.setAdapter(rvAdapter);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE)
//                    fab.show();
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.isShown())
                    fab.hide();
                if (dy < 0 && !fab.isShown())
                    fab.show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseActivity activity = (BaseActivity) getActivity();
                String title = null;
                if (type instanceof User) {
                    activity.showUserDialog(null);
                } else if (type instanceof Site) {
                    activity.showSiteDialog(null);
                } else if (type instanceof Person) {
                    activity.showPersonDialog(null);
                } else if (type instanceof Keyword) {
                    activity.showKeywordDialog(null);
                }

//                ((BaseActivity)getActivity()).showInputDialog(title, type);
                Log.d(LOG_TAG, "onClick");
            }
        });

        return view;
    }

    public MyRecyclerViewAdapter getRvAdapter() {
        return rvAdapter;
    }
}
