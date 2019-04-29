package org.brohede.marcus.fragmentsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String[] mountainNames = {"Matterhorn","Mont Blanc","Denali"};
    private static final String[] mountainLocations = {"Alps","Alps","Alaska"};
    private static final int[] mountainHeights ={4478,4808,6190};
    public static final List<Mountain> MOUNTAINS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        TODO: You should create an app that uses fragments and orientation

        TODO: 1 - Create a fragment (list) to hold a ListView of Mountains
        See: https://developer.android.com/training/basics/fragments/fragment-ui.html

        TODO: 2 - Create a fragment (blank) to hold a details view of Mountain
        See: https://developer.android.com/training/basics/fragments/fragment-ui.html

        TODO: 3 - Create a separate layout for landscape orientation
        See: https://developer.android.com/training/multiscreen/screensizes.html
        See: https://developer.android.com/training/multiscreen/screensizes.html#alternative-layouts

        TODO: 4 - The layout in portrait mode should only display the list fragment and when
                  pressing a list item the details fragment should replace the list fragment
                  and show all the Mountain data.

        TODO: 5 - The layout in landscape mode should display both the list fragment and the
                  details fragment. The details fragment should display details of the currently
                  selected list item.

        */
    }
}
