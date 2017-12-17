package com.jonat.callblocker;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder{

    public CardView cardView;
    public TextView textItem;

    public ViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.item);
        textItem = (TextView) itemView.findViewById(R.id.txtItem);
    }

}
