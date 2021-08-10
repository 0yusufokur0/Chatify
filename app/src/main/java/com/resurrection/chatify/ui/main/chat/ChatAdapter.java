package com.resurrection.chatify.ui.main.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resurrection.chatify.R;
import com.resurrection.chatify.data.ChatDatabase;
import com.resurrection.chatify.data.db.entity.ChatEntity;
import com.resurrection.chatify.data.db.entity.PersonEntity;
import com.resurrection.chatify.ui.base.ChatViewModel;
import com.resurrection.chatify.ui.persons.ChoosePersonAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    private List<ChatEntity> chatEntities = new ArrayList<>();
    private Context context;
    private List<PersonEntity> personEntities = new ArrayList<>();
    private ChatAdapter.OnItemClickListener listener;
    private ChatAdapter.OnItemLongClickListener onItemLongClickListener;


    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        context = parent.getContext();
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        ChatEntity currentChatEntity = chatEntities.get(position);


        PersonEntity personEntity = ChatDatabase.getInstance(context).chatDao().getPerson(currentChatEntity.getPersonId());
        System.out.println("--- >"+personEntity.getId());
        holder.fullName.setText(personEntity.getName());
        holder.lastMessage.setText(currentChatEntity.getDate());
    }

    @Override
    public int getItemCount() {
        return chatEntities.size();
    }

    public void setChat(List<ChatEntity> messageEntities) {
        this.chatEntities = messageEntities;
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


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(chatEntities.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(ChatEntity chatEntity);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(ChatEntity chatEntity);
    }

    public void setOnItemClickListener(ChatAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(ChatAdapter.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;

    }

}
