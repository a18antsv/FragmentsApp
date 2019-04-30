package org.brohede.marcus.fragmentsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ListViewFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column_count";

    private int columnCount = 1;
    private OnListFragmentInteractionListener listener;
    private RecyclerView recyclerView;

    public ListViewFragment() {}

    @SuppressWarnings("unused")
    public static ListViewFragment newInstance(int columnCount) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mountain_list, container, false);
        if(view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if(columnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
            }
            recyclerView.setAdapter(new MountainRecyclerViewAdapter(MainActivity.MOUNTAINS, listener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnListFragmentInteractionListener) {
            listener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void setAdapter(List<Mountain> list) {
        recyclerView.setAdapter(new MountainRecyclerViewAdapter(list, listener));
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Mountain m);
    }
}