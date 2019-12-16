package vn.tien.imageeditor.data;

public class Picture {
    private int image_uri;
    private String artist;
    private String title;
    private String date;

    public Picture(String title, String artist ,String date,int image_uri) {
        this.image_uri = image_uri;
        this.artist = artist;
        this.title = title;
        this.date = date;
    }

    public Picture() {
    }

    public int getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(int image_uri) {
        this.image_uri = image_uri;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
