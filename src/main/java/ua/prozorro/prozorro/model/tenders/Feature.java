package ua.prozorro.prozorro.model.tenders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feature {
    private String code;
    private String featureOf;
    private String relatedItem;
    private String title;
    private String description;
    @SerializedName("title_en")
    private String  titleEn;
    @SerializedName("description_en")
    private String  descriptionEn;

    @SerializedName("enum")
    //@Expose
    private List<FeatureEnum> featureEnums = null;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFeatureOf() {
        return featureOf;
    }

    public void setFeatureOf(String featureOf) {
        this.featureOf = featureOf;
    }

    public String getRelatedItem() {
        return relatedItem;
    }

    public void setRelatedItem(String relatedItem) {
        this.relatedItem = relatedItem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FeatureEnum> getFeatureEnums() {
        return featureEnums;
    }

    public void setFeatureEnums(List<FeatureEnum> featureEnums) {
        this.featureEnums = featureEnums;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Feature{");
        sb.append("code='").append(code).append('\'');
        sb.append(", featureOf='").append(featureOf).append('\'');
        sb.append(", relatedItem='").append(relatedItem).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", titleEn='").append(titleEn).append('\'');
        sb.append(", descriptionEn='").append(descriptionEn).append('\'');
        sb.append(", featureEnums=").append(featureEnums);
        sb.append('}');
        return sb.toString();
    }
}
