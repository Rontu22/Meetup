package com.example.meetup.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meetup.ChatAdapter.ChatAdapter;
import com.example.meetup.Interests.InterestField;
import com.example.meetup.MessagesList.MessagesList;
import com.example.meetup.Models.ChatModel;
import com.example.meetup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {
    private TextView chatHeading;
    private EditText editMessage;
    private Button sendButton;
    private RecyclerView chatRecyclerView;

    private static String CHAT = "CHAT";
    private static String chat = "chat";

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    //private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // get the userid
        Intent intent = getIntent();
        String userID = intent.getStringExtra("USERID");
        chatHeading = findViewById(R.id.chatTV);
        chatHeading.setText(userID);

        // get the interest
        String INTEREST = InterestField.getINTEREST();

        long time = System.currentTimeMillis();
        String timeString = String.valueOf(time);



        // find views
        editMessage = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);

        //initialize firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("INTERESTS");


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editMessage.getText().toString();
                String messageTime = String.valueOf(System.currentTimeMillis());

                if (message == "") {
                    Toast.makeText(ChatActivity.this, "Can't send empty message", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child(INTEREST).child(firebaseUser.getUid()).child(CHAT).child(userID).child(chat).child(messageTime).setValue(message);
                    databaseReference.child(INTEREST).child(userID).child(CHAT).child(firebaseUser.getUid()).child(chat).child(messageTime).setValue(message);

                    databaseReference.child(INTEREST).child(firebaseUser.getUid()).child(CHAT).child(userID).child(chat).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            System.out.println("This is data snapshot");
                            System.out.println(snapshot);
                            MessagesList messagesList = new MessagesList();
                            messagesList.chatList.clear();

                            for (DataSnapshot ds : snapshot.getChildren()) {
                                System.out.println(ds.getValue());
                                messagesList.addChat(new ChatModel(ds.getKey().toString(), ds.getValue().toString()));

                            }
                            ChatAdapter chatAdapter = new ChatAdapter(messagesList.getChatList(), ChatActivity.this);
                            chatRecyclerView.setAdapter(chatAdapter);

                            chatRecyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));


                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


                }
            }
        });



    }
}