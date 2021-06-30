package com.azzarina.uniformshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {
    LayoutInflater inflater;
    List<Product> products;
    List<Product>  productsFull;
    OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public Adapter (Context ctx, List<Product> products){
        this.inflater = LayoutInflater.from(ctx);
        this.products = products;
        productsFull = new ArrayList<>(products);
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
        /*holder.productTitle.setText(products.get(position).getTitle());*/
        /*holder.productPrice.setText("$" + (products.get(position).getPrice()));
        holder.productSize.setText("Size " + (products.get(position).getSize()));*/
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

            productTitle = itemView.findViewById(R.id.productTitle_detail);
           /* productPrice = itemView.findViewById(R.id.productPrice_detail);
            productSize = itemView.findViewById(R.id.productSize_detail);*/
            productCoverImage = itemView.findViewById(R.id.coverImage_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    @Override
    public Filter getFilter(){
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Product> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length()== 0){
                filteredList.addAll(productsFull);

            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Product product : productsFull){
                    if (product.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(product);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products.clear();
            products.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

}
