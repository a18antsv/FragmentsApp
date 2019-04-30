package org.brohede.marcus.fragmentsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MountainDetailsFragment extends Fragment {

    private static final String
            ARG_ID = "MOUNTAIN_ID",
            ARG_NAME = "MOUNTAIN_NAME",
            ARG_LOCATION = "MOUNTAIN_LOCATION",
            ARG_HEIGHT = "MOUNTAIN_HEIGHT",
            ARG_IMAGE = "MOUNTAIN_IMAGE",
            ARG_ARTICLE = "MOUNTAIN_ARTICLE";

    private int mID;
    private String mName;
    private int mHeight;
    private String mLocation;
    private String mImgURL;
    private String mArticleURL;

    private OnFragmentInteractionListener listener;

    public MountainDetailsFragment() {}

    public static MountainDetailsFragment newInstance(int id, String name, int height, String location, String imgURL, String articleURL) {
        MountainDetailsFragment fragment = new MountainDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_NAME, name);
        args.putInt(ARG_HEIGHT, height);
        args.putString(ARG_LOCATION, location);
        args.putString(ARG_IMAGE, imgURL);
        args.putString(ARG_ARTICLE, articleURL);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mID = getArguments().getInt(ARG_ID);
            mName = getArguments().getString(ARG_NAME);
            mHeight = getArguments().getInt(ARG_HEIGHT);
            mLocation = getArguments().getString(ARG_LOCATION);
            mImgURL = getArguments().getString(ARG_IMAGE);
            mArticleURL = getArguments().getString(ARG_ARTICLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mountain_details, container, false);


        return view;
    }

    public void onButtonPressed(Uri uri) {
        if(listener != null) {
            listener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
