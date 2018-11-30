package ua.prozorro.model.pages;

import com.google.gson.annotations.SerializedName;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by AnGo on 22.10.2016.
 */
public class PageURL {

    @SerializedName("path")
    private String path;
    @SerializedName("url")
    private URL url;
    @SerializedName("offset")
    private String offset;

    public PageURL(String path, String url, String offset) {
        this.path = path;
        this.url = null;
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.offset = offset;
    }

    public PageURL(URL url) {
        this.url = url;
    }

    public PageURL() {
    }

    @Override
    public java.lang.String toString() {
        final StringBuilder sb = new StringBuilder("pages.PageURL{");
        sb.append("path= ").append(path);
        sb.append(", url= ").append(url);
        sb.append(", offset= ").append(offset);
        sb.append('}');
        return sb.toString();
    }


    public String getPath() {
        return path;
    }

    public URL getUrl() {
        return url;
    }

    public String getOffset() {
        return offset;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
