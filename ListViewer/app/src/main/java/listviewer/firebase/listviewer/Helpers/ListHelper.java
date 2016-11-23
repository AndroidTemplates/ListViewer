package listviewer.firebase.listviewer.Helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import listviewer.firebase.listviewer.DTOs.WidgetDTO;
import listviewer.firebase.listviewer.MyApplication;
import listviewer.firebase.listviewer.interfaces.DataListener;


/**
 * Created by CHANDRASAIMOHAN on 10/29/2016.
 */

public class ListHelper {
    private final String TAG = this.getClass().getSimpleName();

    //FireBase Related Constants

    private DatabaseReference listConfigurationDataBase;
    private DatabaseReference listDummyDataReference;
    //

    Map<String,WidgetDTO> configMap;
    Map<String,Map<String,String>> dataMap;

    private DataListener dataListener = null;
    private Context appContext = null;

   public ListHelper(Context ctx, DataListener dataListener){
       this.dataListener = dataListener;
       appContext = ctx;
   }
    ProgressDialog progressDialog;
   public void fetchConfigData(){
       progressDialog = new ProgressDialog(appContext);
       progressDialog.setMessage("Fetching Configuration....");
       listConfigurationDataBase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://listgenerator-ec1fb.firebaseio.com/Configuration");
       listConfigurationDataBase.keepSynced(true);

       Log.d("TAG", "Reference::::" + listConfigurationDataBase.toString());

       listConfigurationDataBase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               // Map<String,String> columnHash = dataSnapshot.getValue(Map.class);
              /* String  jsonResponse = new Gson().toJson(dataSnapshot.getValue());
               Log.d(TAG,"ListConfigData::::"+jsonResponse);
               if(jsonResponse!=null){
                   progressDialog.dismiss();
                   dataListener.receiveResult("configSuccess");
               }*/
             /*  configMap = (Map<String,Object>) dataSnapshot.getValue();
               Log.d("ListGenerator","ConfigMap:::"+configMap);
               if(configMap!=null && !configMap.isEmpty()){
                   progressDialog.dismiss();
                   dataListener.receiveResult("configSuccess");
               }*/
               configMap = new HashMap<String, WidgetDTO>();
               Map<String, String> colMap = new HashMap<String, String>();
             /*  Log.d("ListGenerator","Children Count:::"+dataSnapshot.getChildrenCount());
               Log.d("ListGenerator","ConfigData:::"+dataSnapshot.getValue().toString());*/
               for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                   String dataKey = postSnapshot.getKey();
                   WidgetDTO valueMap = (WidgetDTO) postSnapshot.getValue(WidgetDTO.class);
                   configMap.put(dataKey,valueMap);
                   colMap.put(valueMap.getWidgetColumn(),"Default");
                   Log.d("ListHelper","DataSnapShot.....ConfigMap::::"+configMap);
               }
               Log.d("ListHelper","DataSnapShot.....ConfigMap::::"+configMap);
               MyApplication.getAppInstance().setWidgetConfigMap(configMap);
               if(colMap!=null){
                   MyApplication.getAppInstance().setColmnMap(colMap);
               }
               progressDialog.dismiss();
               dataListener.receiveResult("configSuccess");
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
   }


    public void fetchDummyData(){
        progressDialog = new ProgressDialog(appContext);
        progressDialog.setMessage("Fetching Data....");
        listDummyDataReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://listgenerator-ec1fb.firebaseio.com/DummyData");
        listDummyDataReference.keepSynced(true);
        dataMap = new HashMap<>();
        listDummyDataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Map<String,String> columnHash = dataSnapshot.getValue(Map.class);
                String  jsonResponse = new Gson().toJson(dataSnapshot.getValue());
                Log.d(TAG,"Dummy List Data::::"+jsonResponse);
                List<HashMap<String,String>>  tableData = new ArrayList<HashMap<String, String>>();
                if(jsonResponse!=null){
                    try{
                        JSONArray dummyArray = new JSONArray(jsonResponse);
                        for(int i=0;i<dummyArray.length();i++){
                            JSONObject rowData = dummyArray.getJSONObject(i);
                            HashMap<String,String> rowHash = new HashMap<String, String>();
                            Iterator<String> iter = rowData.keys();
                            while (iter.hasNext()) {
                                String key = iter.next();
                                try {
                                    String value = (String) rowData.get(key);
                                    rowHash.put(key,value);
                                } catch (JSONException e) {
                                    // Something went wrong!
                                }
                            }
                            tableData.add(rowHash);

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                    if(tableData!=null && tableData.size()>0){
                      MyApplication.getAppInstance().setTableData(tableData);
                    }
                    dataListener.receiveResult("DummySuccess");
                }
               /* dataMap = (Map<String,Object>) dataSnapshot.getValue();
                Log.d("ListGenerator","dataMap:::"+dataMap);
                if(!dataMap.isEmpty()){
                    progressDialog.dismiss();
                    dataListener.receiveResult("DummySuccess");
                }*
                List<Map<String,String>> dummyMap = null;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String dataKey = postSnapshot.getKey();
                    dummyMap = new ArrayList<Map<String, String>>();
                    dummyMap = (List<Map<String,String>>) dataSnapshot.getValue(ArrayList.class);


                }
                Log.d("ListHelper","DataSnapShot.....DataMap::::"+dummyMap);
                progressDialog.dismiss();
                dataListener.receiveResult("DummySuccess");
                */
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void parseConfigJSON(){

    }

}


/*
Config Data:
ListConfigData::::
{

"widget2":{"widgetType":"Dynamic Label","widgetColumn":"country","widgetLabel":""},
"widget1":{"widgetType":"Dynamic Label","widgetColumn":"ename","widgetLabel":""},
"widget3":{"widgetType":"Button","widgetColumn":"city","widgetLabel":""}
}

Dummy Data:
[
      {"ename":"enameDummy","country":"countryDummy","city":"cityDummy"},
      {"ename":"enameDummy","country":"countryDummy","city":"cityDummy"},
      {"ename":"enameDummy","country":"countryDummy","city":"cityDummy"},
      {"ename":"enameDummy","country":"countryDummy","city":"cityDummy"},
      {"ename":"enameDummy","country":"countryDummy","city":"cityDummy"},
      {"ename":"enameDummy","country":"countryDummy","city":"cityDummy"},
      {"ename":"enameDummy","country":"countryDummy","city":"cityDummy"},
      {"ename":"enameDummy","country":"countryDummy","city":"cityDummy"},
      {"ename":"enameDummy","country":"countryDummy","city":"cityDummy"},
      {"ename":"enameDummy","country":"countryDummy","city":"cityDummy"}
 ]
 */