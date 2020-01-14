package com.example.bequiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    public RecyclerView.Adapter profileAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView note;

    ArrayList<Profile> profiles;

    private int count;

    private String day;
    private int fromHour;
    private int fromMinute;
    private int toHour;
    private int toMinute;

    AudioManager audioManager;
    Date now = new Date();
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
    SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
    SimpleDateFormat minuteFormat = new SimpleDateFormat("M");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        note = findViewById(R.id.note);

        audioManager = (AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);

        FloatingActionButton newProfileFBA = findViewById(R.id.newProfileFBA);

        profiles = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        profileAdapter = new ProfileAdapter(profiles);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(profileAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                count--;
                profiles.remove(viewHolder.getAdapterPosition());
                profileAdapter.notifyDataSetChanged();
            }
        };

        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        newProfileFBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickHoursDF pickHoursDF = new PickHoursDF();
                pickHoursDF.show(getSupportFragmentManager(), "PickHoursFragment");
                note.setText(R.string.note_);
            }
        });

        //modifyRinger();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean restrictedInterval(int fromHour, int fromMinute, int toHour, int toMinute, String Day) {
        int currentHour = Integer.parseInt(hourFormat.format(now));
        int currentMinute = Integer.parseInt(minuteFormat.format(now));
        String currentDay = dayFormat.format(now);

        if (currentDay.equals(Day)) {
            if (currentHour > fromHour) {
                if (currentHour < toHour) {
                    return true;
                } else if (currentHour == toHour && currentMinute <= toMinute) {
                    return true;
                } else {
                    return false;
                }
            }
            else if(currentHour == fromHour && currentMinute >= fromMinute){
                return true;
            }
            else{
                return false;
            }
        }

        return false;
    }

    public void modifyRinger(){
        for(int i = 0; i<count; i++){
            if(restrictedInterval(profiles.get(i).getFromHour(), profiles.get(i).getFromMinute(), profiles.get(i).getToHour(), profiles.get(i).getToMinute(), profiles.get(i).getDay())){
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            }
        }
    }

    public void creareProfil(){
        count++;
        String titlu = "Profile " + String.valueOf(profiles.size() + 1);
        String descriere = "Day: " + day + "\nFrom: " + fromHour + ":" + fromMinute + "   To: " + toHour + ":" + toMinute;

        profileAdapter = new ProfileAdapter(profiles);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(profileAdapter);
        profiles.add(new Profile(day, fromHour, fromMinute, toHour, toMinute, R.drawable.ic_timer_profile, titlu, descriere));
    }

    public void setDay(String day){
        this.day = day;
    }
    public void setFromHour(int fromHour){
        this.fromHour = fromHour;
    }
    public void setFromMinute(int fromMinute) {
        this.fromMinute = fromMinute;
    }
    public void setToHour(int toHour) { this.toHour = toHour; }
    public void setToMinute(int toMinute) {
        this.toMinute = toMinute;
    }
}
