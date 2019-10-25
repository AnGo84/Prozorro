package ua.prozorro.entity;

public class EventResultData {

    private String id;
    private boolean hasResult;
    private String eventResult;
    private Object resultObject;

    public EventResultData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasResult() {
        return hasResult;
    }

    public void setHasResult(boolean hasResult) {
        this.hasResult = hasResult;
    }

    public String getEventResult() {
        return eventResult;
    }

    public void setEventResult(String eventResult) {
        this.eventResult = eventResult;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventResultData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", hasResult=").append(hasResult);
        sb.append(", comparisonResult='").append(eventResult).append('\'');
        sb.append(", resultObject=").append(resultObject);
        sb.append('}');
        return sb.toString();
    }
}
