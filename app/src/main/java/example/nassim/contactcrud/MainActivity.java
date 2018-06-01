package example.nassim.contactcrud;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import example.nassim.contactcrud.Model.Contact;

public class MainActivity extends AppCompatActivity implements FragmentList.OnContactClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = this.getSupportFragmentManager();

        if(findViewById(R.id.readPortrait) != null){
            manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.fragmentAbout))
                    .show(manager.findFragmentById(R.id.fragmentList))
                    .commit();
        }

        if(findViewById(R.id.readLandscape) != null){
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.fragmentAbout))
                    .show(manager.findFragmentById(R.id.fragmentList))
                    .commit();
        }
    }

    @Override
    public void OnContactClick(Contact c) {

        FragmentAbout fragmentAbout = (FragmentAbout) getSupportFragmentManager().findFragmentById(R.id.fragmentAbout);

        if(fragmentAbout != null)
            fragmentAbout.setContact(c);

        if(findViewById(R.id.readPortrait) != null && findViewById(R.id.readLandscape) == null){
            FragmentManager manager = this.getSupportFragmentManager();
            FragmentTransaction f = manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.fragmentList))
                    .show(manager.findFragmentById(R.id.fragmentAbout));

            f.addToBackStack(null);
            f.commit();
        }
    }
}
