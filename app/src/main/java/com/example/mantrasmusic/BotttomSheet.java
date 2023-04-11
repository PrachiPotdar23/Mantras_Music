package com.example.mantrasmusic;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class BotttomSheet extends BottomSheetDialogFragment {
    static String time;
    static long getTime;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
    ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.botttom_sheet,
                container, false);

        Button set_time = v.findViewById(R.id.settime);


        set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        //here we get selected time
                        int hr_mili=1000*hourOfDay;
                        int min_mili=1000*minute;
                        getTime=hr_mili+min_mili;
                        String hour_str=""+hourOfDay;
                        time=hour_str+":00";



                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();

                Toast.makeText(getActivity(),
                                "Timer Set!", Toast.LENGTH_SHORT)
                        .show();
                dismiss();
            }



        });



        return v;
    }
    public long setTime(){
        return getTime;
    }

}
