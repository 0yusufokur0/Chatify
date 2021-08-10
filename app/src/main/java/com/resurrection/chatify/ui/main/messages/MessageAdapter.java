package com.resurrection.chatify.ui.main.messages;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.resurrection.chatify.R;
import com.resurrection.chatify.data.db.entity.MessageEntity;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {
    private List<MessageEntity> messageEntities = new ArrayList<>();
    MessageEntity messageEntity;


    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);

        return new MessageHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        messageEntity = messageEntities.get(position);
        holder.messageLeft.setVisibility(View.GONE);
        holder.messageTxtLeft.setVisibility(View.GONE);
        holder.messageDateLeft.setVisibility(View.GONE);

        holder.messageRight.setVisibility(View.GONE);
        holder.messageTxtRight.setVisibility(View.GONE);
        holder.messageDateRight.setVisibility(View.GONE);


        if (messageEntity.isMe()) {

            holder.messageLeft.setVisibility(View.VISIBLE);
            holder.messageTxtLeft.setVisibility(View.VISIBLE);
            holder.messageDateLeft.setVisibility(View.VISIBLE);

            holder.messageTxtLeft.setText(messageEntity.getMessages());
            holder.messageDateLeft.setText(messageEntity.getDate());
        } else {

            holder.messageRight.setVisibility(View.VISIBLE);
            holder.messageTxtRight.setVisibility(View.VISIBLE);
            holder.messageDateRight.setVisibility(View.VISIBLE);

            holder.messageTxtRight.setText(messageEntity.getMessages());
            holder.messageDateRight.setText(messageEntity.getDate());
        }


    }

    @Override
    public int getItemCount() {
        return messageEntities.size();
    }

    public void setMessages(List<MessageEntity> messageEntities) {
        this.messageEntities = messageEntities;
        notifyDataSetChanged();
    }

    class MessageHolder extends RecyclerView.ViewHolder {
        TextView messageTxtRight, messageDateRight, messageTxtLeft, messageDateLeft;
        ConstraintLayout messageRight, messageLeft;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            messageTxtRight = itemView.findViewById(R.id.messageTxtRight);
            messageDateRight = itemView.findViewById(R.id.messageDateRight);
            messageTxtLeft = itemView.findViewById(R.id.messageTxtLeft);
            messageDateLeft = itemView.findViewById(R.id.messageDateLeft);
            messageRight = itemView.findViewById(R.id.messageRight);
            messageLeft = itemView.findViewById(R.id.messageLeft);

        }
    }
}

