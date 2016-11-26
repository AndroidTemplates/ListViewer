package listviewer.firebase.listviewer.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import listviewer.firebase.listviewer.DTOs.WidgetDTO;
import listviewer.firebase.listviewer.R;


/**
 * Created by CHANDRASAIMOHAN on 8/20/2016.
 */
public class ListGeneratorAdapter extends RecyclerView.Adapter <ListGeneratorAdapter.SimpleType1ViewHolder> {


    private  LayoutInflater inflator;
    Map<String,String> simpleDTOTypeMap = Collections.EMPTY_MAP;
    List<String> simpleDTOTypeList = Collections.EMPTY_LIST;
    Context context;
    Map<String,WidgetDTO>  listConfiguration = new HashMap<>();
    List<HashMap<String,String>> tableData = new ArrayList<HashMap<String, String>>();
    SimpleType1ViewHolder simpleType1ViewHolder;

    public ListGeneratorAdapter(Context context, Map<String,WidgetDTO>  listConfiguration, List<HashMap<String,String>> tableData ){
        inflator = LayoutInflater.from(context);
        this.context = context;
        this.listConfiguration = listConfiguration;
        this.tableData = tableData;
       // initListConfig();
        /*simpleDTOTypeMap = data;
        simpleDTOTypeList = new ArrayList<String>(simpleDTOTypeMap.keySet());*/
    }

    @Override
    public SimpleType1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = inflator.inflate(R.layout.list_generator_row_type1,parent,false);
        Log.d("ListGeneratorAdapter","onCreateViewHolder");
         simpleType1ViewHolder = new SimpleType1ViewHolder(view);
        return simpleType1ViewHolder;
    }

    @Override
    public void onBindViewHolder(SimpleType1ViewHolder  holder, int position) {
        //String title = simpleDTOTypeList.get(position);
     //   holder.title.setText("ABC");
        HashMap<String,String>  rowDataMap = tableData.get(position);

     //   holder.title_icon.setTag(position);
        if(listConfiguration.containsKey("widget1")) {
            WidgetDTO widgetProperties1 = listConfiguration.get("widget1");
            populateDataForRow1(holder.row1Layout, widgetProperties1, 1, rowDataMap);
        }
        if(listConfiguration.containsKey("widget2")) {
            WidgetDTO widgetProperties2 = listConfiguration.get("widget2");
            populateDataForRow1(holder.row1Layout, widgetProperties2, 2, rowDataMap);
        }

        if(listConfiguration.containsKey("widget3")) {
            WidgetDTO widgetProperties3 = listConfiguration.get("widget3");
            populateDataForRow2(holder.row2Layout,widgetProperties3,3,rowDataMap);
        }
        if(listConfiguration.containsKey("widget4")) {
            WidgetDTO widgetProperties4 = listConfiguration.get("widget4");
            populateDataForRow2(holder.row2Layout, widgetProperties4, 4,rowDataMap);
        }

        if(listConfiguration.containsKey("widget5")) {
            WidgetDTO widgetProperties5 = listConfiguration.get("widget5");
            populateDataForRow3(holder.row3Layout,widgetProperties5,5,rowDataMap);
        }
        if(listConfiguration.containsKey("widget6")) {
            WidgetDTO widgetProperties6 = listConfiguration.get("widget6");
            populateDataForRow3(holder.row3Layout, widgetProperties6, 6,rowDataMap);
        }

        if(listConfiguration.containsKey("widget7")) {
            WidgetDTO widgetProperties7 = listConfiguration.get("widget7");
            populateDataForRow4(holder.row4Layout,widgetProperties7,7,rowDataMap);
        }
        if(listConfiguration.containsKey("widget8")) {
            WidgetDTO widgetProperties8 = listConfiguration.get("widget8");
            populateDataForRow4(holder.row4Layout,widgetProperties8,8,rowDataMap);
        }

        if(listConfiguration.containsKey("imageIcon")){
            WidgetDTO widgetProperties8 = listConfiguration.get("imageIcon");
            String imageURL = widgetProperties8.getWidgetColumn();
            Log.d("ImageURL","ImageURL::::::"+rowDataMap.get(imageURL));
         //   simpleType1ViewHolder.setProfilePic(rowDataMap.get(imageURL),context);

            try {
                Glide.with(context).load(rowDataMap.get(imageURL))
                        //.override(200, 200)
                        //.fitCenter()
                        .centerCrop()
                        .into(holder.title_icon);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
    private void populateImage(WidgetDTO widgetDTO){

    }
    private void populateDataForRow1(LinearLayout row1,WidgetDTO widgetDTO,int position, HashMap<String,String>  rowDataMap){
        if(widgetDTO.getWidgetType().equalsIgnoreCase("Static Label")){
                TextView tv = (TextView)row1.findViewById(position);
                tv.setTypeface(null, Typeface.NORMAL);
            if (Build.VERSION.SDK_INT < 23) {
                tv.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Medium);
            } else {
                tv.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Medium);
            }
                tv.setText(widgetDTO.getWidgetLabel());
        }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Dynamic Label")){
            TextView tv = (TextView)row1.findViewById(position);
            tv.setTypeface(null, Typeface.NORMAL);
            if (Build.VERSION.SDK_INT < 23) {
                tv.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Medium);
            } else {
                tv.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Medium);
            }
            tv.setText(rowDataMap.get(widgetDTO.getWidgetColumn()));
        }
        else if(widgetDTO.getWidgetType().equalsIgnoreCase("Button")){
            Button tv = (Button)row1.findViewById(position);
            tv.setText(widgetDTO.getWidgetLabel());
        } else if(widgetDTO.getWidgetType().equalsIgnoreCase("Rating Bar")){
            RatingBar ratingBar = (RatingBar)row1.findViewById(position);
            if(!TextUtils.isEmpty(rowDataMap.get(widgetDTO.getWidgetColumn()))){
                if(isNumeric(rowDataMap.get(widgetDTO.getWidgetColumn()))){
                    double rating = Double.parseDouble(rowDataMap.get(widgetDTO.getWidgetColumn()));
                    ratingBar.setRating((float)rating);
                }
            }

        }
    }

    private void populateDataForRow2(LinearLayout row2,WidgetDTO widgetDTO,int position, HashMap<String,String>  rowDataMap){
        if(widgetDTO.getWidgetType().equalsIgnoreCase("Static Label")){
                TextView tv = (TextView)row2.findViewById(position);
                tv.setText(widgetDTO.getWidgetLabel());
            if (Build.VERSION.SDK_INT < 23) {
                tv.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Medium);
            } else {
                tv.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Medium);
            }
        }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Dynamic Label")){
            TextView tv = (TextView)row2.findViewById(position);
            tv.setText(rowDataMap.get(widgetDTO.getWidgetColumn()));
            if (Build.VERSION.SDK_INT < 23) {
                tv.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Medium);
            } else {
                tv.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Medium);
            }
        }
        else if(widgetDTO.getWidgetType().equalsIgnoreCase("Button")){
            Button tv = (Button)row2.findViewById(position);
            tv.setText(widgetDTO.getWidgetLabel());
        }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Rating Bar")){
            RatingBar ratingBar = (RatingBar)row2.findViewById(position);
            if(!TextUtils.isEmpty(rowDataMap.get(widgetDTO.getWidgetColumn()))){
                if(isNumeric(rowDataMap.get(widgetDTO.getWidgetColumn()))){
                    double rating = Double.parseDouble(rowDataMap.get(widgetDTO.getWidgetColumn()));
                    ratingBar.setRating((float)rating);
                }
            }

        }
    }

    private void populateDataForRow4(LinearLayout row4,WidgetDTO widgetDTO,int position, HashMap<String,String>  rowDataMap){
        if(widgetDTO.getWidgetType().equalsIgnoreCase("Static Label")){
                TextView tv = (TextView)row4.findViewById(position);
                tv.setText(widgetDTO.getWidgetLabel());
            if (Build.VERSION.SDK_INT < 23) {
                tv.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Medium);
            } else {
                tv.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Medium);
            }
        }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Dynamic Label")){
            TextView tv = (TextView)row4.findViewById(position);
            tv.setText(rowDataMap.get(widgetDTO.getWidgetColumn()));
            if (Build.VERSION.SDK_INT < 23) {
                tv.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Medium);
            } else {
                tv.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Medium);
            }
        }
        else if(widgetDTO.getWidgetType().equalsIgnoreCase("Button")){
            Button tv = (Button)row4.findViewById(position);
            tv.setText(widgetDTO.getWidgetLabel());
        }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Rating Bar")){
            RatingBar ratingBar = (RatingBar)row4.findViewById(position);
            if(!TextUtils.isEmpty(rowDataMap.get(widgetDTO.getWidgetColumn()))){
                if(isNumeric(rowDataMap.get(widgetDTO.getWidgetColumn()))){
                    double rating = Double.parseDouble(rowDataMap.get(widgetDTO.getWidgetColumn()));
                    ratingBar.setRating((float)rating);
                }
            }

        }
    }

    private void populateDataForRow3(LinearLayout row3,WidgetDTO widgetDTO,int position, HashMap<String,String>  rowDataMap){
        if(widgetDTO.getWidgetType().equalsIgnoreCase("Static Label")){
                TextView tv = (TextView)row3.findViewById(position);
                tv.setText(widgetDTO.getWidgetLabel());
            if (Build.VERSION.SDK_INT < 23) {
                tv.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Medium);
            } else {
                tv.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Medium);
            }
        }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Dynamic Label")){
            TextView tv = (TextView)row3.findViewById(position);
            tv.setText(rowDataMap.get(widgetDTO.getWidgetColumn()));
            if (Build.VERSION.SDK_INT < 23) {
                tv.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Medium);
            } else {
                tv.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Medium);
            }
        }
        else if(widgetDTO.getWidgetType().equalsIgnoreCase("Button")){
            Button tv = (Button)row3.findViewById(position);
            tv.setText(widgetDTO.getWidgetLabel());
        }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Rating Bar")){
            RatingBar ratingBar = (RatingBar)row3.findViewById(position);
            if(!TextUtils.isEmpty(rowDataMap.get(widgetDTO.getWidgetColumn()))){
                if(isNumeric(rowDataMap.get(widgetDTO.getWidgetColumn()))){
                    double rating = Double.parseDouble(rowDataMap.get(widgetDTO.getWidgetColumn()));
                    ratingBar.setRating((float)rating);
                }
            }

        }
    }
    private void delete(int position){
        simpleDTOTypeList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return tableData.size();
    }

    class SimpleType1ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{
        TextView title;
        ImageView title_icon;
        LinearLayout rootLayout;
        LinearLayout row1Layout,row2Layout,row3Layout,row4Layout;
        Context ctx;
        public SimpleType1ViewHolder(View itemView ){
            super(itemView);
            Log.d("ListGeneratorAdapter","In SimpleType1ViewHolder");
            title_icon = (ImageButton)itemView.findViewById(R.id.imageButton);
            row1Layout = (LinearLayout)itemView.findViewById(R.id.row1_layout);
            row2Layout = (LinearLayout)itemView.findViewById(R.id.row2_layout);
            row3Layout = (LinearLayout)itemView.findViewById(R.id.row3_layout);
            row4Layout = (LinearLayout)itemView.findViewById(R.id.row4_layout);


          /*  TextView tv = new TextView(context);
            tv.setId(R.id.view1_id);
            tv.setText("View1..");
            row1Layout.addView(tv);
            Button et = new Button(context);
            et.setId(R.id.view2_id);
            et.setText("My EditText........");
            row1Layout.addView(et);

            TextView tv2 = new TextView(context);
            tv2.setId(R.id.view3_id);
            tv2.setText("View1..");
            row2Layout.addView(tv2);
            Button et2 = new Button(context);
            et2.setId(R.id.view4_id);
            et2.setText("MyButton");
            row2Layout.addView(et2);*/
            if(listConfiguration.containsKey("widget1")) {
                WidgetDTO widgetProperties1 = listConfiguration.get("widget1");
                initViewsForRow1(row1Layout,widgetProperties1,1);
            }
            if(listConfiguration.containsKey("widget2")) {
                WidgetDTO widgetProperties2 = listConfiguration.get("widget2");
                initViewsForRow1(row1Layout,widgetProperties2,2);
            }
         if(listConfiguration.containsKey("widget3")) {
             WidgetDTO widgetProperties3 = listConfiguration.get("widget3");
             initViewsForRow2(row2Layout,widgetProperties3,3);
         }
            if(listConfiguration.containsKey("widget4")) {
                WidgetDTO widgetProperties4 = listConfiguration.get("widget4");
                initViewsForRow2(row2Layout, widgetProperties4, 4);
            }

            if(listConfiguration.containsKey("widget5")) {
                WidgetDTO widgetProperties5 = listConfiguration.get("widget5");
                initViewsForRow3(row3Layout,widgetProperties5,5);
            }
            if(listConfiguration.containsKey("widget6")) {
                WidgetDTO widgetProperties6 = listConfiguration.get("widget6");
                initViewsForRow3(row3Layout, widgetProperties6, 6);
            }

            if(listConfiguration.containsKey("widget7")) {
                WidgetDTO widgetProperties7 = listConfiguration.get("widget7");
                initViewsForRow4(row4Layout,widgetProperties7,7);
            }
            if(listConfiguration.containsKey("widget8")) {
                WidgetDTO widgetProperties8 = listConfiguration.get("widget8");
                initViewsForRow4(row4Layout, widgetProperties8, 8);
            }



        }


        public  void setProfilePic(String profileURL,Context ctx){
            this.ctx = ctx;
            try {
                /*URI uri = new URI(profileURL.toString());
                Log.d("ListGenerator","URI:::::"+uri);*/
                //Log.d("Adapter","profileURL:::::"+ Html.fromHtml(profileURL));
               // simpleType1ViewHolder.title_icon.setTag(profileURL);
                Glide.with(ctx).load(profileURL)
                        //.override(200, 200)
                        //.fitCenter()
                        .centerCrop()
                        .into(simpleType1ViewHolder.title_icon);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        private void initViewsForRow1(LinearLayout row1,WidgetDTO widgetDTO,int position){
           if(widgetDTO.getWidgetType().equalsIgnoreCase("Static Label")){
               TextView tv = new TextView(context);
               tv.setId(position);
               setWeight(tv);
               row1.addView(tv);
              // tv.setText("ABC");

           }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Dynamic Label")){
               TextView tv = new TextView(context);
               tv.setId(position);
               setWeight(tv);
               row1.addView(tv);
           //    tv.setText("ABC");
           }
           else if(widgetDTO.getWidgetType().equalsIgnoreCase("Button")){
               Button tv = new Button(context);
               tv.setId(position);
               setWeight(tv);
               row1.addView(tv);
            //   tv.setText("Button");

           }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Rating Bar")){
               RatingBar ratingBar = new RatingBar(context, null, android.R.attr.ratingBarStyleSmall);
               ratingBar.setId(position);
            //   row1.addView(ratingBar);

               LinearLayout ratingBarLayout = new LinearLayout(context);
               ratingBarLayout.addView(ratingBar);
               setWeightForViewGroup(ratingBarLayout);
               row1.addView(ratingBarLayout);
           }

            /*else if(widgetDTO.getWidgetType().equalsIgnoreCase("StaticLabel")){

           }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Rating Bar")){

           }*/
        }

        private void initViewsForRow2(LinearLayout row2,WidgetDTO widgetDTO,int position){
            if(widgetDTO.getWidgetType().equalsIgnoreCase("Static Label")){
                TextView tv = new TextView(context);
                tv.setId(position);
                setWeight(tv);
                row2.addView(tv);
             //   tv.setText("ABC");

            }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Dynamic Label")){
                TextView tv = new TextView(context);
                tv.setId(position);
                setWeight(tv);
                row2.addView(tv);
              //  tv.setText("ABC");
            }
            else if(widgetDTO.getWidgetType().equalsIgnoreCase("Button")){
                Button tv = new Button(context);
                tv.setId(position);
                setWeight(tv);
                row2.addView(tv);
             //   tv.setText("Button");

            }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Rating Bar")){
                RatingBar ratingBar = new RatingBar(context, null, android.R.attr.ratingBarStyleSmall);
                ratingBar.setId(position);
               // row2.addView(ratingBar);

                LinearLayout ratingBarLayout = new LinearLayout(context);
                ratingBarLayout.addView(ratingBar);
                setWeightForViewGroup(ratingBarLayout);
                row2.addView(ratingBarLayout);
            }
        }


        private void initViewsForRow3(LinearLayout row3,WidgetDTO widgetDTO,int position){
            if(widgetDTO.getWidgetType().equalsIgnoreCase("Static Label")){
                TextView tv = new TextView(context);
                tv.setId(position);
                setWeight(tv);
                row3.addView(tv);
              //  tv.setText("ABC");

            }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Dynamic Label")){
                TextView tv = new TextView(context);
                tv.setId(position);
                setWeight(tv);
                row3.addView(tv);
              //  tv.setText("ABC");
            }
            else if(widgetDTO.getWidgetType().equalsIgnoreCase("Button")){
                Button tv = new Button(context);
                tv.setId(position);
                setWeight(tv);
                row3.addView(tv);
              // tv.setText("Button");

            }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Rating Bar")){
                RatingBar ratingBar = new RatingBar(context, null, android.R.attr.ratingBarStyleSmall);
                ratingBar.setId(position);
              //  row3.addView(ratingBar);

                LinearLayout ratingBarLayout = new LinearLayout(context);
                ratingBarLayout.addView(ratingBar);
                setWeightForViewGroup(ratingBarLayout);
                row3.addView(ratingBarLayout);
            }
        }


        private void initViewsForRow4(LinearLayout row4,WidgetDTO widgetDTO,int position){
            if(widgetDTO.getWidgetType().equalsIgnoreCase("Static Label")){
                TextView tv = new TextView(context);
                tv.setId(position);
                setWeight(tv);
                row4.addView(tv);
             //   tv.setText("ABC");

            }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Dynamic Label")){
                TextView tv = new TextView(context);
                tv.setId(position);
                setWeight(tv);
                row4.addView(tv);
             //   tv.setText("ABC");
            }
            else if(widgetDTO.getWidgetType().equalsIgnoreCase("Button")){
                Button tv = new Button(context);
                tv.setId(position);
                setWeight(tv);
                row4.addView(tv);
         //       tv.setText("Button");

            }else if(widgetDTO.getWidgetType().equalsIgnoreCase("Rating Bar")){
                RatingBar ratingBar = new RatingBar(context, null, android.R.attr.ratingBarStyleSmall);
                ratingBar.setId(position);
                //row4.addView(ratingBar);

                LinearLayout ratingBarLayout = new LinearLayout(context);
                ratingBarLayout.addView(ratingBar);
                setWeightForViewGroup(ratingBarLayout);
                row4.addView(ratingBarLayout);

            }
        }


        /*@Override
        public void onClick(View v) {
            rootLayout.setSelected(false);
            switch (v.getId()){
                case R.id.simple_type1_title:
                    delete(getAdapterPosition());
                    break;
                case  R.id.root_layout:
                     rootLayout.setSelected(true);
                    break;
            }


        }*/
    }

    private void initListConfig(){
        listConfiguration.put("widget1",getWidgetInfo(1,"TextView"));
        listConfiguration.put("widget2",getWidgetInfo(2,"Button"));
        listConfiguration.put("widget3",getWidgetInfo(3,"TextView"));
        listConfiguration.put("widget4",getWidgetInfo(4,"Button"));


        listConfiguration.put("widget5",getWidgetInfo(5,"TextView"));
        listConfiguration.put("widget6",getWidgetInfo(6,"Button"));
        listConfiguration.put("widget7",getWidgetInfo(7,"Button"));
        listConfiguration.put("widget8",getWidgetInfo(8,"TextView"));
    }

    private WidgetDTO getWidgetInfo(int position,String widgetType){
        WidgetDTO  widgetDTO = new WidgetDTO();
        widgetDTO.setWidgetLabel("Name"+position);
        widgetDTO.setWidgetType(widgetType);
        return  widgetDTO;
    }

    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    private void setWeight(TextView tv){
        tv.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
    }

    private void setWeightForViewGroup(ViewGroup tv){
        tv.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
    }
}
