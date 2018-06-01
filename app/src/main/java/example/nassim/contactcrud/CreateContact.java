package example.nassim.contactcrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import example.nassim.contactcrud.Model.Contact;

public class CreateContact extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnCreateContact;

    private EditText editTextLastName;
    private EditText editTextFirstName;
    private EditText editTextNumber;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_contact);

        editTextLastName = findViewById(R.id.EditTextLastName);
        editTextFirstName = findViewById(R.id.EditTextFirstName);
        editTextNumber = findViewById(R.id.EditTextNumber);

        btnCreateContact = findViewById(R.id.btnCreateContact);
        btnCreateContact.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnCreateContact)
            if(fieldsFilled()){
                new Contact(editTextFirstName.getText().toString(),
                            editTextLastName.getText().toString(),
                            Integer.parseInt(editTextNumber.getText().toString())).save();
                // Back to main after creating a contact
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
    }

    private boolean fieldsFilled (){
        if(editTextFirstName.getText().toString().length() < 1) {
            Toast.makeText(this, "First name required !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(editTextLastName.getText().toString().length() < 1) {
            Toast.makeText(this, "Last name required !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(editTextNumber.getText().toString().length() < 1) {
            Toast.makeText(this, "Age required !", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
