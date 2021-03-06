package com.yvesis.flyers;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yvesis.flyers.core.models.FlyerItem;
import com.yvesis.flyers.core.models.FlyerShop;
import com.yvesis.flyers.core.net.Downloader;
import com.yvesis.flyers.core.net.FlyerContentDownloader;
import com.yvesis.flyers.core.storage.FileManager;

public class ShopFlyerItemsAdapter extends RecyclerView.Adapter<ShopFlyerItemsAdapter.FlyerItemViewHolder>{

    private FlyerShop shop;
    FileManager fileManager;
    Context context;

    public ShopFlyerItemsAdapter(Context context, FlyerShop shop) {
        this.shop = shop;
        this.context = context;
        fileManager = FileManager.getInstance(context);

    }

    @Override
    public FlyerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flyer_item,parent, false);
        return new FlyerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FlyerItemViewHolder flyerItemViewHolder, int position) {

        final FlyerItem fItem = shop.getItem(position);

        flyerItemViewHolder.categoryText.setText(shop.getItemCategory(position));
        flyerItemViewHolder.descriptionText.setText(fItem.getName());
        flyerItemViewHolder.priceText.setText(fItem.getPrice());
        if(fItem.saved && fileManager.fileExists(fItem.getId())){
            byte[] bytes = null;
            Log.i("file","exists");
            if((bytes = fileManager.readAllBytes(fItem.getId())).length >0)
            {
               // fileManager.readAllBytes(fItem.getId(),bytes);
                Log.i("read", String.valueOf(bytes.length));
                flyerItemViewHolder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0, bytes.length));
            }
        }else {
            Log.i("file","not exists");
            FlyerContentDownloader contentDownloader = new FlyerContentDownloader(fItem.getImageUrl());
            try
            {
                contentDownloader.getContent(fItem.getImageUrl(), new Downloader.IDownloaderCallback() {
                    @Override
                    public void onContentDownloaded(String htmlContent) {

                    }

                    @Override
                    public void onContentParsed(String htmlContent, Object content) {
                        if( content != null){
                            byte[] bytes = (byte[]) content;
                            flyerItemViewHolder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0, bytes.length));
                            fileManager.writeAllBytes(fItem.getId(),bytes);
                            Log.i("write", String.valueOf(bytes.length));
                            fItem.saved = true;
                        }

                    }

                    @Override
                    public void oneDownloadFailed(String htmlContent) {

                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }




        //if(position % 2 == 0)
         //   holder.itemView.setBackgroundResource(R.color.colorBlue1);
        /*else
            holder.itemView.setBackgroundResource(R.color.colorBlue2);*/
    }

    @Override
    public int getItemCount() {
        return shop.getItemsCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class FlyerItemViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView categoryText;
        private TextView descriptionText;
        private TextView priceText;
        private ImageView imageView;

        public FlyerItemViewHolder(View itemView) {
            super(itemView);
            cardView =(CardView) itemView.findViewById(R.id.item_cardview);
            categoryText = (TextView)itemView.findViewById(R.id.categoryText);
            descriptionText = (TextView)itemView.findViewById(R.id.item_description);
            priceText = (TextView)itemView.findViewById(R.id.item_price);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }
}
