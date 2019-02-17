package com.arhiser.sample.ui.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.arhiser.sample.R;
import com.arhiser.sample.model.Contact;
import com.arhiser.sample.ui.ActivityCall;
import com.arhiser.sample.ui.BaseActivity;

public class ContactEditActivity extends BaseActivity {

    public static final String EXTRA_CONTACT = "ContactEditActivity.EXTRA_CONTACT";

    private EditText firstName;
    private EditText lastName;
    private EditText phone;

    public static void startForResult(Activity caller, Contact contact, int requestId) {
        Intent intent = new Intent(caller, ContactEditActivity.class);
        intent.putExtra(EXTRA_CONTACT, contact);
        caller.startActivityForResult(intent, requestId);
    }

    public static Contact getResult(Intent intent) {
        return intent.getParcelableExtra(EXTRA_CONTACT);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Контакт");

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        phone = findViewById(R.id.phone);

        if (savedInstanceState == null) {
            Contact contact = getIntent().getParcelableExtra(EXTRA_CONTACT);
            firstName.setText(contact.getFirstName());
            lastName.setText(contact.getLastName());
            phone.setText(contact.getPhone());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.apply) {
            Contact contact = getIntent().getParcelableExtra(EXTRA_CONTACT);
            contact.setFirstName(firstName.getText().toString());
            contact.setLastName(lastName.getText().toString());
            contact.setPhone(phone.getText().toString());
            Intent result = new Intent();
            result.putExtra(EXTRA_CONTACT, contact);
            setResult(RESULT_OK, result);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class Call extends ActivityCall<Contact> {

        public void start(Contact contact) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_CONTACT, contact);
            start(intent);
        }

        @Override
        protected Contact fromIntent(Intent intent) {
            return intent.getParcelableExtra(EXTRA_CONTACT);
        }

        @Override
        protected Class<? extends Activity> getActivityClass() {
            return ContactEditActivity.class;
        }
    }
}
