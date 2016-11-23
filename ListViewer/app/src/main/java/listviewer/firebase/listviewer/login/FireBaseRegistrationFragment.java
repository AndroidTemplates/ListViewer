package listviewer.firebase.listviewer.login;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import listviewer.firebase.listviewer.Fragments.ListGeneratorFragment;
import listviewer.firebase.listviewer.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FireBaseRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FireBaseRegistrationFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View fireBaseRegistrationView = null;
    EditText email,password,name;
    Button register;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    TextView login;
    int mContainerId = -1;
    private DatabaseReference mDataBaseReference;
    public FireBaseRegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FireBaseRegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FireBaseRegistrationFragment newInstance(String param1, String param2) {
        FireBaseRegistrationFragment fragment = new FireBaseRegistrationFragment();
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
        initFireBaseAuth();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fireBaseRegistrationView =  inflater.inflate(R.layout.fragment_fire_base_registration, container, false);
        mContainerId = container.getId();
        initToolBar();
        initView(fireBaseRegistrationView);
        registerListener();
        return  fireBaseRegistrationView;
    }


    private  void initFireBaseAuth(){
        mDataBaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        firebaseAuth = FirebaseAuth.getInstance();
    }
    private  void initView(View v){
        progressDialog = new ProgressDialog(getActivity());
        email = (EditText) v.findViewById(R.id.email_ed);
          password = (EditText)   v.findViewById(R.id.password_ed);
        register = (Button) v.findViewById(R.id.register);
        login = (TextView) v.findViewById(R.id.login_here);
        name = (EditText) v.findViewById(R.id.name_ed);
    }

    private  void registerListener(){
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                register();
                break;
            case R.id.login_here:
                launchLogin();
                break;
        }
    }

private void launchLogin(){
    Fragment fireBaseLoginFragment = new FireBaseLoginFragment();
    getActivity().getSupportFragmentManager().beginTransaction()
            .replace(mContainerId, fireBaseLoginFragment)
          //  .addToBackStack(null)
            .commit();
}

   /* private void launchProfileSetUp(){
        Fragment profileWithImageFragment = new ProfileWithImageFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(mContainerId, profileWithImageFragment)
             //   .addToBackStack(null)
                .commit();
    }*/
    private  void register(){
        String emailId = email.getText().toString();
        String passwordForEmail  = password.getText().toString();
        final String nameForRegistration = name.getText().toString();
        if(!TextUtils.isEmpty(emailId) && !TextUtils.isEmpty(passwordForEmail) && !TextUtils.isEmpty(nameForRegistration)){
            progressDialog.setMessage("Registering user....");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(emailId,passwordForEmail).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        String userID =  firebaseAuth.getCurrentUser().getUid();
                        DatabaseReference currentUserDBReference =    mDataBaseReference.child(userID);
                        currentUserDBReference.child("name").setValue(nameForRegistration);
                        currentUserDBReference.child("profileImg").setValue("abc");

                        //launchProfileSetUp();
                        launchListGenerator();

                        Toast.makeText(getActivity(),"Registered Successfully",Toast.LENGTH_LONG).show();
                    }else{

                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),"Failed to Register"+ task.getException().toString(),Toast.LENGTH_LONG).show();
                    }
                }

            });
        }else{
            Toast.makeText(getActivity(),"Please enter Email password to Register",Toast.LENGTH_LONG).show();
        }


    }

    private void launchListGenerator(){
        Fragment listGeneratorFragment = new ListGeneratorFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.parentlayout, listGeneratorFragment)
                .commit();
    }


    Button create_table_btn_tool_bar;
    TextView toolBarTitle;
    Toolbar mToolBar;
    private  void initToolBar(){
        mToolBar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        create_table_btn_tool_bar = (Button)mToolBar.findViewById(R.id.create_table) ;
        create_table_btn_tool_bar.setVisibility(View.GONE);
        toolBarTitle = (TextView)mToolBar.findViewById(R.id.title);
        toolBarTitle.setText("Registration");
    }
}
