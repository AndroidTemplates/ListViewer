package listviewer.firebase.listviewer;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import listviewer.firebase.listviewer.login.FireBaseRegistrationFragment;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActivityContext();
        initToolBar();
        launchRegistrationFragment();
    }

    private void setActivityContext(){
        MyApplication.getAppInstance().setmActivityContext(MainActivity.this);
    }


    public void initToolBar(){
        mToolBar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LayoutInflater mInflater= LayoutInflater.from(getApplicationContext());
        View mCustomView = mInflater.inflate(R.layout.toolbar_custom_view, null);
        mToolBar.addView(mCustomView);
        // display the first navigation drawer view on app launch

    }

    private  void launchRegistrationFragment(){
        Fragment fireBaseRegistrationFragment = new FireBaseRegistrationFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.parentlayout, fireBaseRegistrationFragment)
                .commit();
    }

}
