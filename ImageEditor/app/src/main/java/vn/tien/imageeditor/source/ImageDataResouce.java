package vn.tien.imageeditor.source;

import java.util.List;

public interface ImageDataResouce {
    interface OnFetchDataListener<T> {
        void onFetchDataSuccess(List<T> data);

        void onFetchDataFailure(Exception e);
    }
}
