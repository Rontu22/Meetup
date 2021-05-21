package com.example.meetup.Interests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meetup.Activity.MainActivity;
import com.example.meetup.R;

public class InterestsListActivity extends AppCompatActivity {
    private Spinner interestsSpinner;
    private Button letsGoButton;
    private String INTEREST = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests_list);

        // take the spinner
        interestsSpinner = findViewById(R.id.interestsList);

        // find the button
        letsGoButton = findViewById(R.id.lets_go_button);

        Interests interests = new Interests();


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,interests.getInterests());

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        interestsSpinner.setAdapter(dataAdapter);

        interestsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                INTEREST = item;
                Toast.makeText(InterestsListActivity.this,item+" item selected",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(InterestsListActivity.this,"Please select something...",Toast.LENGTH_LONG).show();

            }
        });

        letsGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(INTEREST !="")
                {
                    goToHome(INTEREST);
                    Toast.makeText(InterestsListActivity.this, "Please choose any interest : "+INTEREST, Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(InterestsListActivity.this, "Please choose any interest", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void goToHome(String interest) {
        Intent intent = new Intent(InterestsListActivity.this,MainActivity.class);
        intent.putExtra("INTEREST",INTEREST);
        startActivity(intent);
    }
}