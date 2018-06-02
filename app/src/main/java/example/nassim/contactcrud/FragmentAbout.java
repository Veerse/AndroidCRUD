package example.nassim.contactcrud;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import example.nassim.contactcrud.Model.Contact;

public class FragmentAbout extends Fragment {

    private EditText editFirstName;
    private EditText editLastName;
    private EditText editNumber;

    private ImageButton btnEdit;
    private ImageButton btnDelete;

    private Contact c;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        editFirstName = view.findViewById(R.id.EditContactFirstName);
        editLastName = view.findViewById(R.id.EditContactLastName);
        editNumber = view.findViewById(R.id.EditContactNumber);

        btnEdit = view.findViewById(R.id.btnEdit);
        btnDelete = view.findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.delete();
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.setFirstName(editFirstName.getText().toString());
                c.setLastName(editLastName.getText().toString());
                c.setNumber(Integer.parseInt(editNumber.getText().toString()));
                //c.update();
                Contact.update (c);
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

        return view;
    }

    public void setContact (Contact c) {
        this.c = c;
        if(this.editLastName != null && this.editFirstName != null && this.editNumber != null){
            this.editFirstName.setText(c.getFirstName());
            this.editLastName.setText(c.getLastName());
            this.editNumber.setText(c.getNumber().toString());
        }
    }

}
