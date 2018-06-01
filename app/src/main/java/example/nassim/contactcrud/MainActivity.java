package example.nassim.contactcrud;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import example.nassim.contactcrud.Model.Contact;

public class MainActivity extends AppCompatActivity implements FragmentList.OnContactClickListener{

    FloatingActionButton toCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toCreate = findViewById(R.id.btnToCreate);
        toCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CreateContact.class));
            }
        });
    }

    @Override
    public void OnContactClick(Contact c) {

        FragmentAbout fragmentAbout = (FragmentAbout) getSupportFragmentManager().findFragmentById(R.id.fragmentAbout);

        if(fragmentAbout != null)
            fragmentAbout.setContact(c);

        else{
            FragmentAbout newFrag = new FragmentAbout();
            newFrag.setContact(c);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragmentList, newFrag);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
