package org.brohede.marcus.fragmentsapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.brohede.marcus.fragmentsapp.ListViewFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MountainRecyclerViewAdapter extends RecyclerView.Adapter<MountainRecyclerViewAdapter.ViewHolder> {

    private final List<Mountain> mountains;
    private final OnListFragmentInteractionListener listener;

    public MountainRecyclerViewAdapter(List<Mountain> items, OnListFragmentInteractionListener listener) {
        this.mountains = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_mountain, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {
        holder.mountain = mountains.get(i);
        holder.viewId.setText(Integer.toString(mountains.get(i).getId()));
        holder.contentView.setText(mountains.get(i).getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onListFragmentInteraction(holder.mountain);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mountains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView viewId;
        public final TextView contentView;
        public Mountain mountain;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            viewId = (TextView) view.findViewById(R.id.id);
            contentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}
