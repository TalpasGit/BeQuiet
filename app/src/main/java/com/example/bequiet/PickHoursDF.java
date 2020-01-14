package com.example.bequiet;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PickHoursDF extends DialogFragment  {
    private static final String TAG = "PickHours";

    private Spinner daySpinner;
    private Button doneButton;
    private Button fromButton;
    private Button toButton;
    private TextView fromText;
    private TextView toText;
    private TimePicker timePicker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.pickhours_fragment, container, false);

        fromButton = view.findViewById(R.id.from_button);
        fromText   = view.findViewById(R.id.text_from);
        toButton   = view.findViewById(R.id.to_button);
        toText     = view.findViewById(R.id.text_to);
        doneButton = view.findViewById(R.id.pick_time_button);
        timePicker = view.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
        daySpinner = view.findViewById(R.id.day_spinner);
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.days, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);


        fromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour   = timePicker.getHour();
                int minute = timePicker.getMinute();

                fromText.setText(hour + " : " + minute);
                ((MainActivity)getActivity()).setFromHour(hour);
                ((MainActivity)getActivity()).setFromMinute(minute);
            }
        });

        toButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour   = timePicker.getHour();
                int minute = timePicker.getMinute();

                toText.setText(hour + " : " + minute);
                ((MainActivity)getActivity()).setToHour(hour);
                ((MainActivity)getActivity()).setToMinute(minute);
            }
        });

       doneButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int hour   = timePicker.getHour();
               int minute = timePicker.getMinute();
               String day = daySpinner.getSelectedItem().toString();

               ((MainActivity)getActivity()).setDay(day);
               ((MainActivity)getActivity()).creareProfil();
               ((MainActivity)getActivity()).modifyRinger();
               dismiss();
           }
       });

        return view;
    }

}
