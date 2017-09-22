package com.divaga.calculadoraredes;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static String[] octetos = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        ContentAdapter contentA = new ContentAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(contentA);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.modelo_lugares, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.imageview_cover);
            name = (TextView) itemView.findViewById(R.id.textview_cover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();

                    switch (getAdapterPosition()){
                        case 0:{
                            Intent intent = new Intent(context, ConvertDtoBActivity.class);
                            context.startActivity(intent);
                            break;
                        }
                        case 1:{
                            Intent intent = new Intent(context, ConvertBtoDActivity.class);
                            context.startActivity(intent);
                            break;
                        }case 2:{
                            Intent intent = new Intent(context, GetClassActivity.class);
                            context.startActivity(intent);
                            break;
                        }case 3:{
                            Intent intent = new Intent(context, AboutActivity.class);
                            context.startActivity(intent);
                            break;
                        }
                    }
                }
            });
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 4;
        private final String[] mPlaces;
        private final Drawable[] mPlaceAvators;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.titulos);

            TypedArray a = resources.obtainTypedArray(R.array.imagenes);
            mPlaceAvators = new Drawable[a.length()];
            for (int i = 0; i < mPlaceAvators.length; i++) {
                mPlaceAvators[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);
            holder.name.setText(mPlaces[position % mPlaces.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
