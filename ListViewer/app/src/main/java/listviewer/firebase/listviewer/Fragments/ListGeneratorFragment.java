package listviewer.firebase.listviewer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import listviewer.firebase.listviewer.Adapters.ListGeneratorAdapter;
import listviewer.firebase.listviewer.DividerItemDecoration;
import listviewer.firebase.listviewer.Helpers.ListHelper;
import listviewer.firebase.listviewer.MyApplication;
import listviewer.firebase.listviewer.R;
import listviewer.firebase.listviewer.interfaces.DataListener;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListGeneratorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListGeneratorFragment extends Fragment implements DataListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View generatorView = null;
    View view1,view2;
    String view1Type = "TextView";
    String view2Type = "EditText";
    private RecyclerView simpleType1RecyclerView;
    private ListGeneratorAdapter listGeneratorAdapter;
    Toolbar mToolBar;

    public ListGeneratorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListGeneratorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListGeneratorFragment newInstance(String param1, String param2) {
        ListGeneratorFragment fragment = new ListGeneratorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        generatorView =  inflater.inflate(R.layout.fragment_list_generator, container, false);
      //  initView(generatorView);
        initToolBar();
        simpleType1RecyclerView = (RecyclerView)generatorView.findViewById(R.id.list_generator_r_view);

        simpleType1RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        simpleType1RecyclerView.addItemDecoration(itemDecoration);
        simpleType1RecyclerView.setItemAnimator(new DefaultItemAnimator());


        new ListHelper(MyApplication.getAppInstance().getmActivityContext(),ListGeneratorFragment.this).fetchConfigData();
        return  generatorView;
    }

   /* private void initView(View view) {
        if (view1Type.equalsIgnoreCase("TextView")) {
            view1 = (View) view.findViewById(R.id.view1);
            TextView tv1 = (TextView)view1;
            tv1.setText("TEXXXXXXXTTTTTTTTTTTT");
        }
        view2 = (View) view.findViewById(R.id.view2);
        EditText etv1 = (EditText) view1;
        etv1.setText("TEXXXXXXXTTTTTTTTTTTT");
    }*/
      //  view2 = (View)view.findViewById(R.id.view2);




    @Override
    public void receiveResult(String result) {
        Log.d("ListGenerator",result);
        if(result.equalsIgnoreCase("configSuccess")){
            new ListHelper(MyApplication.getAppInstance().getmActivityContext(),ListGeneratorFragment.this).fetchDummyData();
        }else if(result.equalsIgnoreCase("DummySuccess")){
            Log.d("ListGenerator","DummySuccess");
            setAdapter();
        }else{
            Log.d("ListGenerator","Fail");
        }
    }

    private  void setAdapter(){
        if(!MyApplication.getAppInstance().getColmnMap().isEmpty() && MyApplication.getAppInstance().getColmnMap().size()>0 && MyApplication.getAppInstance().getTableData().size()>0 ) {
            listGeneratorAdapter = new ListGeneratorAdapter(MyApplication.getAppInstance().getmActivityContext(), MyApplication.getAppInstance().getWidgetConfigMap(),MyApplication.getAppInstance().getTableData());
            simpleType1RecyclerView.setAdapter(listGeneratorAdapter);
        }
    }
    Button create_table_btn_tool_bar;
    TextView toolBarTitle;
    private  void initToolBar(){
        mToolBar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        create_table_btn_tool_bar = (Button)mToolBar.findViewById(R.id.create_table) ;
        create_table_btn_tool_bar.setVisibility(View.INVISIBLE);
        toolBarTitle = (TextView)mToolBar.findViewById(R.id.title);
        toolBarTitle.setText("Generated List");
    }
}
