package vn.tien.imageeditor.constant;

import androidx.annotation.StringDef;

@StringDef({
        ImageEntity.IMAGES,
        ImageEntity.URL,
})
public @interface ImageEntity {
    String IMAGES = "images";
    String URL =" url" ;
}
