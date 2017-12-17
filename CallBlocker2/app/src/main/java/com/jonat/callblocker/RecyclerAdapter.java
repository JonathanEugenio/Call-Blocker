package com.jonat.callblocker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jonat.callblocker.database.dao.TelefoneDAO;
import com.jonat.callblocker.database.entities.Telefone;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<TelefoneModel> telefoneModels = new ArrayList<>();
    private RecyclerAdapter adapter;

    public RecyclerAdapter(Context mContex, ArrayList<TelefoneModel> telefoneModels) {
        this.mContext = mContex;
        this.telefoneModels = telefoneModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        adapter = this;
        final CardView cardView = viewHolder.cardView;
        TextView textView = viewHolder.textItem;

        textView.setText(telefoneModels.get(position).getNumero());

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                cardView.setBackgroundColor(Color.RED);

                AlertDialog.Builder builder =  new AlertDialog.Builder(mContext);
                builder.setTitle(R.string.title);
                builder.setMessage(R.string.msg);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TelefoneDAO.getInstance(mContext).delete(telefoneModels.get(position).getNumero());
                        telefoneModels.remove(telefoneModels.get(position));

                        cardView.setBackgroundColor(Color.parseColor("#6232fd"));

                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        cardView.setBackgroundColor(Color.parseColor("#6232fd"));
                    }
                });

                AlertDialog dialog = builder.create();

                dialog.show();

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return telefoneModels != null ? telefoneModels.size() : 0;
    }
}
