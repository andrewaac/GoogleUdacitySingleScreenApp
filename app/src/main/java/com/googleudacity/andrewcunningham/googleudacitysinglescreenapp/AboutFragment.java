package com.googleudacity.andrewcunningham.googleudacitysinglescreenapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andrewcunningham on 25/02/2017.
 */

public class AboutFragment extends Fragment {

    // Final Fields
    private static final String TAG = "AboutFragment";

    // Views
    CardView callButton, emailButton, mapButton;
    TextView dayOne, dayTwo, dayThree, dayFour, dayFive;
    TextView dayOneTimes, dayTwoTimes, dayThreeTimes, dayFourTimes, dayFiveTimes;


    public AboutFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_about, container, false);

        ArrayList<TextView> daysOfWeek = new ArrayList<>();
        dayOne = (TextView) mainView.findViewById(R.id.day_one);
        dayTwo = (TextView) mainView.findViewById(R.id.day_two);
        dayThree = (TextView) mainView.findViewById(R.id.day_three);
        dayFour = (TextView) mainView.findViewById(R.id.day_four);
        dayFive = (TextView) mainView.findViewById(R.id.day_five);
        daysOfWeek.add(dayOne);
        daysOfWeek.add(dayTwo);
        daysOfWeek.add(dayThree);
        daysOfWeek.add(dayFour);
        daysOfWeek.add(dayFive);
        String[] names = getResources().getStringArray(R.array.days_of_week);
        for (int i = 0; i < daysOfWeek.size(); i++) {
            daysOfWeek.get(i).setText(names[i]);
        }

        ArrayList<TextView> timesOfWeek = new ArrayList<>();
        dayOneTimes = (TextView) mainView.findViewById(R.id.day_one_times);
        dayTwoTimes = (TextView) mainView.findViewById(R.id.day_two_times);
        dayThreeTimes = (TextView) mainView.findViewById(R.id.day_three_times);
        dayFourTimes = (TextView) mainView.findViewById(R.id.day_four_times);
        dayFiveTimes = (TextView) mainView.findViewById(R.id.day_five_times);
        timesOfWeek.add(dayOneTimes);
        timesOfWeek.add(dayTwoTimes);
        timesOfWeek.add(dayThreeTimes);
        timesOfWeek.add(dayFourTimes);
        timesOfWeek.add(dayFiveTimes);
        String[] times = getResources().getStringArray(R.array.times_of_week);
        for (int i = 0; i < daysOfWeek.size(); i++) {
            timesOfWeek.get(i).setText(times[i]);
        }

        return mainView;

    }
}
