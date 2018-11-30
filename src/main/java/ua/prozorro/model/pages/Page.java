package ua.prozorro.model.pages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by AnGo on 22.10.2016.
 */
public class Page {

    @SerializedName("path")
    //@Expose
    private String path;
    @SerializedName("uri")
    //@Expose
    private String uri;
    @SerializedName("offset")
    //@Expose
    private String offset;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Page{");
        sb.append("path='").append(path).append('\'');
        sb.append(", uri='").append(uri).append('\'');
        sb.append(", offset='").append(offset).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
