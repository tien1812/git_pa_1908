package vn.tien.imageeditor.ui.fragment_home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.tien.imageeditor.R;
import vn.tien.imageeditor.data.Picture;
import vn.tien.imageeditor.ui.screen.InforActivity;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    private List<Picture> pictures;
    private Context context;


    public static final String KEY_TITLE = "title";
    public static final String KEY_Artist = "artist";
    public static final String KEY_Date = "date";
    public static final String KEY_PICTURE = "picture";

    public PictureAdapter(List<Picture> pictures, Context context) {
        this.pictures = pictures;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mImageView.setImageResource(pictures.get(position).getImage_uri());
        holder.mItemPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InforActivity.class);

                intent.putExtra(KEY_TITLE, pictures.get(position).getTitle());
                intent.putExtra(KEY_Artist, pictures.get(position).getArtist());
                intent.putExtra(KEY_Date, pictures.get(position).getDate());
                intent.putExtra(KEY_PICTURE, pictures.get(position).getImage_uri());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public void setSearchResult(List<Picture> result) {
        pictures = result;
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private LinearLayout mItemPicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_item);
            mItemPicture = itemView.findViewById(R.id.item_picture);

        }



    }
}
