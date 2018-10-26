package ua.prozorro.model.pages;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by AnGo on 22.10.2016.
 */
public class Page {
    private String path;
    private URL url;
    private String offset;

    public Page(String path, String url, String offset) {
        this.path = path;
        this.url = null;
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.offset = offset;
    }

    public Page(URL url) {
        this.url = url;
    }

    public Page() {
    }

    @Override
    public java.lang.String toString() {
        final StringBuilder sb = new StringBuilder("pages.Page{");
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
