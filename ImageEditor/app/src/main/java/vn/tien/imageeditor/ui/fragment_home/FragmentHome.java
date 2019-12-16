package vn.tien.imageeditor.ui.fragment_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.tien.imageeditor.R;
import vn.tien.imageeditor.data.Picture;

public class FragmentHome extends Fragment {
    private RecyclerView mRecyclerView;
    List<Picture> mPictures;
    PictureAdapter pictureAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = view.findViewById(R.id.rv_image);
        pictureAdapter = new PictureAdapter(mPictures, getContext());
        mRecyclerView.setAdapter(pictureAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        createList();


    }

    private void createList() {
        mPictures = new ArrayList<>();
        mPictures.add(new Picture("áo mưa", "Tien", "25/6/2018", R.drawable.aomua));
        mPictures.add(new Picture("báo cáo", "Tien", "25/6/2018", R.drawable.baocao));
        mPictures.add(new Picture("can thiệp", "Tien", "25/6/2018", R.drawable.canthiep));
        mPictures.add(new Picture("cát tường", "Tien", "25/6/2018", R.drawable.cattuong));
        mPictures.add(new Picture("chiếu tre", "Tien", "25/6/2018", R.drawable.chieutre));
        mPictures.add(new Picture("đánh lửa", "Tien", "25/6/2018", R.drawable.danhlua));
        mPictures.add(new Picture("đàn ông", "Tien", "25/6/2018", R.drawable.danong));
        mPictures.add(new Picture("kiếm chuyện", "Tien", "25/6/2018", R.drawable.kiemchuyen));
        mPictures.add(new Picture("lan can", "Tien", "25/6/2018", R.drawable.lancan));
        mPictures.add(new Picture("ma sát", "Tien", "25/6/2018", R.drawable.masat));
        mPictures.add(new Picture("nam bán cầu", "Tien", "25/6/2018", R.drawable.nambancau));
        mPictures.add(new Picture("oto", "Tien", "25/6/2018", R.drawable.oto));
        mPictures.add(new Picture("quy hàng", "Tien", "25/6/2018", R.drawable.quyhang));
    }

    public void beginSearch(String newText) {
        final List<Picture> filteredList =filter(mPictures,newText);
        pictureAdapter.setSearchResult(filteredList);
    }

    private List<Picture> filter(List<Picture> models, String query) {
        query = query.toLowerCase();
        final List<Picture> filteredList = new ArrayList<>();
        for (Picture picture : models) {
            final String text = picture.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredList.add(picture);
            }
        }
        return filteredList;
    }
}
