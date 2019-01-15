package ua.prozorro.task;

import javafx.concurrent.Task;
import org.hibernate.SessionFactory;
import ua.prozorro.properties.PropertyFields;
import ua.prozorro.prozorro.ParsingResultData;
import ua.prozorro.prozorro.model.DataType;

public class TaskFactory {

    public static Task taskForParseAndSave(SessionFactory sessionFactory, PropertyFields propertyFields, ParsingResultData resultData) {
        if (propertyFields.getSearchDateType().equals(DataType.TENDERS)) {
            return new TenderParserTask(sessionFactory, propertyFields, resultData);

        } else if (propertyFields.getSearchDateType().equals(DataType.CONTRACTS)) {
            return null;
        } else if (propertyFields.getSearchDateType().equals(DataType.PLANS)) {
            return null;
        }
        return null;
    }

    public static Task taskForCalculationParsingTimeForPeriod(PropertyFields propertyFields) {
        if (propertyFields.getSearchDateType().equals(DataType.TENDERS)) {
            return new TenderParseTimeTask(propertyFields);

        } else if (propertyFields.getSearchDateType().equals(DataType.CONTRACTS)) {
            return null;
        } else if (propertyFields.getSearchDateType().equals(DataType.PLANS)) {
            return new PlanParseTimeTask(propertyFields);
        }
        return null;
    }
}
