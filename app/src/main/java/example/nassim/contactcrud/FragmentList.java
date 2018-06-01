package example.nassim.contactcrud;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

import example.nassim.contactcrud.Model.Contact;

public class FragmentList extends Fragment {

    // COMMUNICATION WITH ACTIVITY
    OnContactClickListener mCallback;

    public interface OnContactClickListener{
        void OnContactClick (Contact c);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mCallback = (OnContactClickListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }
    // END COMMUNICATION WITH ACTIVITY

    private ListView contactListView;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        contactListView = view.findViewById(R.id.ContactListView);
        searchView = view.findViewById(R.id.ContactSearch);

        // Retrieve contacts
        final List<Contact> list = Contact.find (Contact.class, null, null);

        final ArrayAdapter<Contact> adapter = new ArrayAdapter <> (view.getContext(),
                                                        android.R.layout.simple_list_item_2,
                                                        android.R.id.text1,
                                                        list);
        contactListView.setAdapter(adapter);


        // Handle click on ListView
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCallback.OnContactClick(list.get(i));
            }
        });

        // Search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return view;
    }
}
