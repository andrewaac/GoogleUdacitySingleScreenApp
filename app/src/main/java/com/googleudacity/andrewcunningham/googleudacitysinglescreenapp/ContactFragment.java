package com.googleudacity.andrewcunningham.googleudacitysinglescreenapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by andrewcunningham on 25/02/2017.
 */

public class ContactFragment extends Fragment implements View.OnClickListener {

    // Final Fields
    private static final String TAG = "AboutFragment";
    private static final int REQUEST_PHONE = 1;

    // Views
    CardView callButton, emailButton, mapButton;

    public ContactFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_contact, container, false);

        // Bind views
        callButton = (CardView) mainView.findViewById(R.id.call_button);
        emailButton = (CardView) mainView.findViewById(R.id.email_button);
        mapButton = (CardView) mainView.findViewById(R.id.map_button);

        // Set OnClickListeners
        callButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);
        mapButton.setOnClickListener(this);

        return mainView;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_button:
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE);
                    return;
                }
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(getString(R.string.tel_uri_code) + getString(R.string.phone_number)));
                startActivity(callIntent);
                break;
            case R.id.email_button:
                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.setType("text/plain");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.email_address));
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
                startActivity(Intent.createChooser(mailIntent, getString(R.string.email_chooser_title)));
                break;
            case R.id.map_button:
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setData(Uri.parse(getString(R.string.fake_location)));
                startActivity(mapIntent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PHONE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(getString(R.string.tel_uri_code) + getString(R.string.phone_number)));
                startActivity(callIntent);
            } else {
                Toast.makeText(getContext(), R.string.permissions_toast, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
