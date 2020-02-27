package ua.prozorro.urlreader;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FilterData {
    private String FILTER_DATE_PATTERN_DD_MM_YYYY = "dd.MM.yyyy";

    private Date dateFrom;
    private Date dateTill;
    private boolean withContent;
    private boolean readOnly;

    public FilterData(Date dateFrom, Date dateTill, boolean withContent, boolean readOnly) {
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
        this.withContent = withContent;
        this.readOnly = readOnly;
    }

    public String getDateFormat() {
        return FILTER_DATE_PATTERN_DD_MM_YYYY;
    }
}
