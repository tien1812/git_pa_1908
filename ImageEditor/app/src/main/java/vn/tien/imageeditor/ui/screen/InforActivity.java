package vn.tien.imageeditor.ui.screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vn.tien.imageeditor.R;

public class InforActivity extends AppCompatActivity {
    private ImageView mPicture ;
    private TextView mTextTitle ,mTextArtist ,mTextDate ;

    public static final String KEY_TITLE = "title";
    public static final String KEY_Artist = "artist";
    public static final String KEY_Date = "date";
    public static final String KEY_PICTURE = "picture";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        initView();

    }



    private void initView() {
        Intent intent =getIntent();
        String title = intent.getExtras().getString(KEY_TITLE);
        String artist = intent.getExtras().getString(KEY_Artist);
        String date = intent.getExtras().getString(KEY_Date);
        int picture = intent.getExtras().getInt(KEY_PICTURE);

        mPicture = findViewById(R.id.image_infor);
        mTextTitle = findViewById(R.id.text_title);
        mTextArtist = findViewById(R.id.text_artist);
        mTextDate = findViewById(R.id.text_date);

        mPicture.setImageResource(picture);
        mTextTitle.setText(title);
        mTextArtist.setText(artist);
        mTextDate.setText(date);
    }
}
