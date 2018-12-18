package com.beans.coder.recyclerpdfcreater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Context context;
    private List<Item> items;
    private static ArrayList<CardView> cardViewArrayList = new ArrayList<>();
    private OnItemClickListener mListener;

    public RecyclerViewAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item,viewGroup,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        Item item = items.get(i);
        holder.textViewName.setText(item.getName());
        holder.imageView.setImageResource(item.getImageId());
        addCardView(holder.cardView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    private static void addCardView(CardView cardView)
    {
        cardViewArrayList.add(cardView);
    }

    public static ArrayList<CardView> getCardViewList()
    {
        return cardViewArrayList;
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
                View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener{

        public TextView textViewName;
        public ImageView imageView;
        public CardView cardView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.imgView);
            cardView = itemView.findViewById(R.id.card_view_root);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem CreatePDF = menu.add(Menu.NONE,1,1,"CreatePDF");

            CreatePDF.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mListener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1:
                            mListener.onCreatePDFClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }
    public interface OnItemClickListener{
       void onCreatePDFClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
