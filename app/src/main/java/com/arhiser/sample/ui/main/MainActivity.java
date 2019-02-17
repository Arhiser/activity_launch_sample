package com.arhiser.sample.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arhiser.sample.R;
import com.arhiser.sample.SampleApp;
import com.arhiser.sample.model.Contact;
import com.arhiser.sample.model.Repository;
import com.arhiser.sample.ui.BaseActivity;
import com.arhiser.sample.ui.contact.ContactEditActivity;

import static com.arhiser.sample.ui.contact.ContactEditActivity.EXTRA_CONTACT;

public class MainActivity extends BaseActivity {

    ListView listView;
    ContactAdapter adapter = new ContactAdapter();
    Repository repository;

    ContactEditActivity.Call createCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContactEditActivity.Call editCall = registerActivityCall(new ContactEditActivity.Call(), contact -> {
            repository.putContact(contact);
            updateList();
        });

        createCall = registerActivityCall(new ContactEditActivity.Call(), contact -> {
            repository.addContact(contact);
            updateList();
        });

        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        repository = ((SampleApp)getApplication()).getRepository();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editCall.start((Contact) adapter.getItem(i));
            }
        });
        updateList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.create) {
            Contact contact = new Contact();
            createCall.start(contact);
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateList() {
        adapter.setContacts(repository.getContacts());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
