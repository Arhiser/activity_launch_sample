package com.arhiser.sample.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arhiser.sample.R;
import com.arhiser.sample.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private List<Contact> contacts = new ArrayList<>();

    public void setContacts(List<Contact> newContacts) {
        contacts.clear();
        contacts.addAll(newContacts);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contacts.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false);
        }
        Contact contact = contacts.get(i);
        ((TextView)view.findViewById(R.id.name)).setText(contact.getFirstName() + " " + contact.getLastName());
        ((TextView)view.findViewById(R.id.phone)).setText(contact.getPhone());
        return view;
    }
}
