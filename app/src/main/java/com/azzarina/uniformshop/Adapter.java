package com.azzarina.uniformshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Product> products;

    public Adapter (Context ctx, List<Product> products){
        this.inflater = LayoutInflater.from(ctx);
        this.products = products;
        }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind the data
        holder.productTitle.setText(products.get(position).getTitle());
        holder.productPrice.setText(products.get(position).getPrice());
        holder.productSize.setText(products.get(position).getSize());
        Picasso.get().load(products.get(position).getCoverImage()).into(holder.productCoverImage);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView productTitle, productPrice, productSize;
        ImageView productCoverImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productTitle = itemView.findViewById(R.id.productTitle);
            productPrice = itemView.findViewById(R.id.productPrice);
            productSize = itemView.findViewById(R.id.productSize);
            productCoverImage = itemView.findViewById(R.id.coverImage);

        }
    }
}
