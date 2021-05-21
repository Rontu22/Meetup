package com.example.meetup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.contentcapture.DataRemovalRequest;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meetup.Adapter.UserDataAdapter;
import com.example.meetup.Interests.Interests;
import com.example.meetup.Models.Users;
import com.example.meetup.UsersList.CommonInterestUsers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView showInterest;
    private Button showAllUsers;
    private Button addMe;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    private UserDataAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;


    private String INTEREST = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // find the textview
//        showInterest = findViewById(R.id.selectInterest);
        showAllUsers = findViewById(R.id.showAllUsers);
        addMe = findViewById(R.id.add_myself);

        Interests interests = new Interests();
        Intent intent = getIntent();
        INTEREST = intent.getStringExtra("INTEREST");
//        progressDialog.setTitle("Please wait");
//        progressDialog.show();
        //showInterest.setText(INTEREST);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("INTERESTS");
////
////
//        firebaseUser = firebaseAuth.getCurrentUser();
        addMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DatabaseReference df = FirebaseDatabase.getInstance().getReference();
//                df.removeValue();
                databaseReference.child(INTEREST).child(firebaseUser.getUid()).setValue(firebaseUser.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "You have been successfully added to the database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                //databaseReference.child(INTEREST.toString()).


            }
        });





//        databaseReference.child(INTEREST).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                System.out.println("These are data snapshots  : ");
//
//                for(DataSnapshot ds: snapshot.getChildren())
//                {
//                    System.out.println(ds.getValue());
//                    ds.getValue();
//                    commonInterestUsers.addUser(new Users(ds.getValue().toString()));
//                    //list.add(new Users(ds.getValue().toString()));
//                }
//
////
//
//                recyclerView = (RecyclerView)findViewById(R.id.recyclerViewShowUsers);
//
//                adapter = new UserDataAdapter(commonInterestUsers.usersList,MainActivity.this);
//                recyclerView.setAdapter(adapter);
//
//                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                progressDialog.dismiss();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//                progressDialog.dismiss();
//
//            }
//        });

        showAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(INTEREST).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        System.out.println("These are data snapshots  : ");
                        CommonInterestUsers commonInterestUsers = new CommonInterestUsers();

                        for(DataSnapshot ds: snapshot.getChildren())
                        {
                            System.out.println(ds.getValue());
                            ds.getValue();
                            commonInterestUsers.addUser(new Users(ds.getValue().toString()));
                            //list.add(new Users(ds.getValue().toString()));
                        }

//

                        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewShowUsers);

                        adapter = new UserDataAdapter(commonInterestUsers.uniqueUsersList(),MainActivity.this);
                        recyclerView.setAdapter(adapter);

                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                        showAllUsers.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }
        });










//        // find the spinner element
//        Spinner spinner = findViewById(R.id.dropDownInterests);
//
//        List<String> interests = new ArrayList<>();
//        interests.add("Football");
//        interests.add("Cricket");
//        interests.add("Education");
//        interests.add("PUBG");
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,interests);
//
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setAdapter(dataAdapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//
//                Toast.makeText(MainActivity.this,item+" item selected",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(MainActivity.this,"Please select something...",Toast.LENGTH_LONG).show();
//
//            }
//        });



    }

//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        return super.onContextItemSelected(item);
//    }


    private List<Users> getData()
    {
        List<Users> list = new ArrayList<>();
        list.add(new Users("rontubarhoi@gmail.com"));
        list.add(new Users("one@one.ocm"));
        list.add(new Users("onelere erlejle"));

        return list;
    }
}