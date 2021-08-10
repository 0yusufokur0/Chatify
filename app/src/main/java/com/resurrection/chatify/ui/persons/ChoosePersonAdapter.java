package com.resurrection.chatify.ui.persons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resurrection.chatify.R;
import com.resurrection.chatify.data.db.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;

public class ChoosePersonAdapter extends RecyclerView.Adapter<ChoosePersonAdapter.ChoosePersonHolder> {

    private List<PersonEntity> personEntities = new ArrayList<>();
    private OnItemClickListener listener;
    private OnItemLongClickListener onItemLongClickListener;

    @NonNull
    @Override
    public ChoosePersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);


        return new ChoosePersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoosePersonHolder holder, int position) {
        PersonEntity currentPersonEntity = personEntities.get(position);


        holder.fullName.setText(currentPersonEntity.getName());
    }

    @Override
    public int getItemCount() {
        return personEntities.size();
    }

    public void setChoosePerson(List<PersonEntity> personEntities) {
        this.personEntities = personEntities;
        notifyDataSetChanged();
    }

    class ChoosePersonHolder extends RecyclerView.ViewHolder {
        private TextView fullName, lastMessage;
        private CheckBox checkBox;


        public ChoosePersonHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            lastMessage = itemView.findViewById(R.id.lastMessage);
            checkBox = itemView.findViewById(R.id.checkBox);

        }

    }

    public interface OnItemClickListener {
        void onItemClick(PersonEntity personEntity);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(PersonEntity personEntity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;

    }


}