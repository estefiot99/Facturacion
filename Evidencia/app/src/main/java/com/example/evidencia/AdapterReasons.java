package com.example.evidencia;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AdapterReasons extends RecyclerView.Adapter<AdapterReasons.ViewHolderReasons> {

    ArrayList<String> listReasons;
    private Context context;

    public AdapterReasons(ArrayList<String> listReasons) {
        this.listReasons = listReasons;
    }

    @Override
    public ViewHolderReasons onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = parent.getContext();


                Dialog builder = new Dialog(context);
                builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                builder.getWindow().setBackgroundDrawable(
                        new ColorDrawable(android.graphics.Color.TRANSPARENT));
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        //nothing;
                    }
                });

                ImageView imageView = new ImageView(context);
                try
                {
                    InputStream ims = context.getAssets().open("qr.png");
                    Drawable d = Drawable.createFromStream(ims, null);
                    imageView.setImageDrawable(d);
                    ims.close();
                }
                catch(IOException ex)
                {
                    return;
                }
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                builder.show();
            }
        });
        return new ViewHolderReasons(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReasons holder, int position) {
        holder.assignReasons(listReasons.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listReasons.size();
    }

    public class ViewHolderReasons extends RecyclerView.ViewHolder {

        TextView alias;

        public ViewHolderReasons(@NonNull View itemView) {
            super(itemView);
            alias = (TextView) itemView.findViewById(R.id.aliasTv);
        }

        public void assignReasons(String reason) {
            alias.setText(reason);
        }
    }
}
