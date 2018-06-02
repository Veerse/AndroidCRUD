package example.nassim.contactcrud;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import example.nassim.contactcrud.Model.Contact;

public class CreateContact extends AppCompatActivity implements View.OnClickListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageButton btnCreateContact;
    private ImageButton btnTakePic;
    private ImageButton btnReset;

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
        btnReset = findViewById(R.id.btnReset);
        btnTakePic = findViewById(R.id.btnTakePic);

        btnCreateContact.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnTakePic.setOnClickListener(this);

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

        if(view.getId() == R.id.btnReset){
            resetFields();
        }

        if(view.getId() == R.id.btnTakePic){
            takePicture();
        }
    }

    private void resetFields () {
        editTextLastName.setText("");
        editTextFirstName.setText("");
        editTextNumber.setText("");
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
            Toast.makeText(this, "Phone number required !", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void takePicture () {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //avatar.setImageBitmap(imageBitmap);
        }
    }
}
