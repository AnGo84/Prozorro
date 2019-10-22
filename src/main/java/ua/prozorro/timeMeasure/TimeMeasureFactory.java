package ua.prozorro.timeMeasure;

import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.model.DataType;

public class TimeMeasureFactory {
    public static TimeMeasureInterface getTimeMeasurer(PropertyFields propertyFields) {
        if (propertyFields.getSearchDateType().equals(DataType.TENDERS)) {
            return new TenderTimeMeasureImp(propertyFields);

        } else if (propertyFields.getSearchDateType().equals(DataType.CONTRACTS)) {
            return new ContractTimeMeasureImp(propertyFields);
        } else if (propertyFields.getSearchDateType().equals(DataType.PLANS)) {
            return new PlanTimeMeasureImp(propertyFields);
        } else if (propertyFields.getSearchDateType().equals(DataType.NBU_RATES)) {
            return new NBURatesTimeMeasureImp(propertyFields);
        }
        return null;
    }
}
