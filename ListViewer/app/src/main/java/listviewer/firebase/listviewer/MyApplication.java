package listviewer.firebase.listviewer;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import listviewer.firebase.listviewer.DTOs.WidgetDTO;


/**
 * Created by CHANDRASAIMOHAN on 7/21/2016.
 */
public class MyApplication extends Application {
    private static MyApplication appInstance;
  //  private static DBProducts mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        appInstance = this;



    }

    public  static  MyApplication getAppInstance(){
        return  appInstance;
    }

    public static Context getMyAppContext(){
        return  appInstance.getApplicationContext();
    }

  /*  public synchronized static DBProducts getWritableDatabase() {
        if (mDatabase == null) {
            mDatabase = new DBProducts(getMyAppContext());
        }
        return mDatabase;
    }
*/
    AppCompatActivity mActivityContext;

    public Context getmActivityContext() {
        return mActivityContext;
    }

    public void setmActivityContext(AppCompatActivity mActivityContext) {
        this.mActivityContext = mActivityContext;
    }


    Map<String,String> colmnMap = new HashMap<>();

    public Map<String, String> getColmnMap() {
        return colmnMap;
    }

    public void setColmnMap(Map<String, String> colmnMap) {
        this.colmnMap = colmnMap;
    }

    String tableName="";

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    Map<String,WidgetDTO> widgetConfigMap = new HashMap<>();

    public Map<String, WidgetDTO> getWidgetConfigMap() {
        return widgetConfigMap;
    }

    public void setWidgetConfigMap(Map<String, WidgetDTO> widgetConfigMap) {
        this.widgetConfigMap = widgetConfigMap;
    }
    List<HashMap<String,String>> tableData = new ArrayList<HashMap<String, String>>();

    public List<HashMap<String, String>> getTableData() {
        return tableData;
    }

    public void setTableData(List<HashMap<String, String>> tableData) {
        this.tableData = tableData;
    }
}
