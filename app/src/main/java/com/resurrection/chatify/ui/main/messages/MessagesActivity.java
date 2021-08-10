package com.resurrection.chatify.ui.main.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.resurrection.chatify.R;
import com.resurrection.chatify.data.db.entity.ChatEntity;
import com.resurrection.chatify.data.db.entity.MessageEntity;
import com.resurrection.chatify.ui.base.ChatViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    private RecyclerView messagesRecyclerView;
    private EditText editMessage;
    private Button send;
    private MessageAdapter messageAdapter;
    private ChatViewModel chatViewModel;

    private void init(){
        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
        editMessage = findViewById(R.id.editMessage);
        send = findViewById(R.id.send);
        messageAdapter = new MessageAdapter();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        init();
        getAndSetItems();
        long personId = getIntent().getLongExtra("personId",-1);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // burada ki is me yi personId olarak değiştireceğiz ve kontrolde person Id üzerinden Yapacağınz
                if (editMessage.getText().toString().equals("1")){
                    chatViewModel.insertMessage(new MessageEntity(idCreater(),personId,editMessage.getText().toString(),getDate(),true,true));

                }else{
                    chatViewModel.insertMessage(new MessageEntity(idCreater(),personId,editMessage.getText().toString(),getDate(),false,false));

                }
                editMessage.setText("");

            }
        });





    }
    private void getAndSetItems(){
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(MessagesActivity.this));
        messagesRecyclerView.setHasFixedSize(true);
        messagesRecyclerView.setAdapter(messageAdapter);
        chatViewModel = ViewModelProviders.of(MessagesActivity.this).get(ChatViewModel.class);
        chatViewModel.getAllMessage().observe(this, new Observer<List<MessageEntity>>() {
            @Override
            public void onChanged(List<MessageEntity> messageEntities) {
                messageAdapter.setMessages(messageEntities);
            }
        });



    }

    public static long idCreater() {
        Date nowDateTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyykkmmss");
        String date = dateFormat.format(nowDateTime);
        System.out.println(date);
        return Long.valueOf(date);
    }


    public  String getDate() {
        java.sql.Date nowDateTime = new java.sql.Date(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-kk:mm");
        String date = dateFormat.format(nowDateTime);
        System.out.println(date);
        return date;
    }
}












