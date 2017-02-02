package com.yvesis.flyers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yvesis.flyers.core.models.FlyerItem;
import com.yvesis.flyers.core.models.FlyerShop;
import com.yvesis.flyers.core.net.Downloader;
import com.yvesis.flyers.core.net.FlyerContentDownloader;
import com.yvesis.flyers.core.net.FlyersDownloader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listview;
    private ShopFlyerItemsAdapter flyerItemAdapter;
    private RecyclerView.LayoutManager lvLayout;
    private ArrayAdapter<String> adapter;
    private List<String> items;
    private FlyerShop shop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        listview = (RecyclerView) findViewById(R.id.listview);
        listview.setHasFixedSize(true);
        lvLayout = new LinearLayoutManager(this);
        listview.setLayoutManager(lvLayout);
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
        //flyerItemAdapter = new ShopFlyerItemsAdapter(null,android.R.layout.simple_list_item_1);
        //listview.setAdapter(flyerItemAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabRefresh);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FlyersDownloader downloader = new FlyersDownloader("http://www.maxi.ca/fr_CA");
                downloader.getContent("flyers.pageview.banner@MAXI.storenum@8705.week@current.html", new Downloader.IDownloaderCallback() {
                    @Override
                    public void onContentDownloaded(String htmlContent) {

                    }

                    @Override
                    public void onContentParsed(String htmlContent, Object shopFlyer) {

                        shop = (FlyerShop)shopFlyer;
                        setTitle("Flyer : " +shop.getShopName());
                        updateList();
                    }

                    @Override
                    public void oneDownloadFailed(String htmlContent) {
                        Toast.makeText(getApplicationContext(),"Download failed!", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }

    private  void updateList(){
       /* items.clear();
        adapter.clear();

        for(FlyerCategory fc : shop){

            for(FlyerItem fi : fc){
                items.add(String.format("%s\n%s", fi.getName(), fi.getPrice()));
            }
        }*/

        flyerItemAdapter = new ShopFlyerItemsAdapter(shop);
        listview.setAdapter(flyerItemAdapter);

        flyerItemAdapter.notifyDataSetChanged();
    }



}
class ShopFlyerItemsAdapter extends RecyclerView.Adapter<ShopFlyerItemsAdapter.FlyerItemViewHolder>{

    private FlyerShop shop;


    public ShopFlyerItemsAdapter(FlyerShop shop) {
        this.shop = shop;

    }

    @Override
    public FlyerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flyer_item,parent, false);
        return new FlyerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FlyerItemViewHolder flyerItemViewHolder, int position) {

        FlyerItem fItem = shop.getItem(position);

        flyerItemViewHolder.categoryText.setText(shop.getItemCategory(position));
        flyerItemViewHolder.descriptionText.setText(fItem.getName());
        flyerItemViewHolder.priceText.setText(fItem.getPrice());
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
                    }

                }

                @Override
                public void oneDownloadFailed(String htmlContent) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
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
