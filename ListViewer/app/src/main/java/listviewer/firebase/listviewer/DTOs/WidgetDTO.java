package listviewer.firebase.listviewer.DTOs;

/**
 * Created by CHANDRASAIMOHAN on 10/11/2016.
 */

public class WidgetDTO {
    private String widgetLabel;
    private String widgetColumn;
    private String widgetType;
    private String userID;
    private String widgetReference;


    public String getWidgetLabel() {
        return widgetLabel;
    }

    public void setWidgetLabel(String widgetLabel) {
        this.widgetLabel = widgetLabel;
    }

    public String getWidgetReference() {
        return widgetReference;
    }

    public void setWidgetReference(String widgetReference) {
        this.widgetReference = widgetReference;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public String getWidgetColumn() {
        return widgetColumn;
    }

    public void setWidgetColumn(String widgetColumn) {
        this.widgetColumn = widgetColumn;
    }
}
