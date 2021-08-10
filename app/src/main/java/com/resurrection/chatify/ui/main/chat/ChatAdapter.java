package com.resurrection.chatify.ui.main.chat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.resurrection.chatify.R;
import com.resurrection.chatify.data.ChatDatabase;
import com.resurrection.chatify.data.db.dao.ChatDao;
import com.resurrection.chatify.data.db.entity.MessageEntity;
import com.resurrection.chatify.data.db.entity.PersonEntity;
import com.resurrection.chatify.ui.base.ChatViewModel;
import com.resurrection.chatify.ui.persons.ManagePersons;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    private List<MessageEntity> messageEntities = new ArrayList<>();
    private ChatViewModel chatViewModel;
    private Context context;
    private List<PersonEntity> personEntities = new ArrayList<>();



    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        context =parent.getContext();
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        MessageEntity currentMessageEntity = messageEntities.get(position);


        PersonEntity personEntity = ChatDatabase.getInstance(context).chatDao().getPerson(currentMessageEntity.getPersonId());

        holder.fullName.setText(personEntity.getName());
        holder.lastMessage.setText(currentMessageEntity.getDate());
    }

    @Override
    public int getItemCount() {
        return messageEntities.size();
    }

    public void setMessage(List<MessageEntity> messageEntities) {
        this.messageEntities = messageEntities;
        notifyDataSetChanged();
    }
    public void setPerson(List<PersonEntity> personEntities) {
        this.personEntities = personEntities;
        notifyDataSetChanged();
    }

    class ChatHolder extends RecyclerView.ViewHolder {
        private TextView fullName, lastMessage;
        private CheckBox checkBox;


        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            lastMessage = itemView.findViewById(R.id.lastMessage);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }

}
