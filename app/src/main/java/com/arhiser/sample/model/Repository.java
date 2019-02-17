package com.arhiser.sample.model;

import java.util.ArrayList;
import java.util.List;

public class Repository  {

    private ArrayList<Contact> contacts = new ArrayList<>();

    public Repository() {
        contacts.add(new Contact(1, "Vasiliy", "Pupkin", "+300682345543"));
        contacts.add(new Contact(2, "Petr", "Alexeev", "+300685564566"));
        contacts.add(new Contact(3, "Gennadiy", "Sidorov", "+300689567575"));
        contacts.add(new Contact(4, "Izya", "Rabinovitch", "+300682456745"));
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }

    public void putContact(Contact contact) {
        int i = 0;
        for (; i < contacts.size(); i++) {
            if (contact.getId() == contacts.get(i).getId()) {
                break;
            }
        }
        if (i < contacts.size()) {
            contacts.remove(i);
            contacts.add(i, contact);
        } else {
            addContact(contact);
        }
    }

    public void addContact(Contact contact) {
        contact.setId(contacts.size() + 1);
        contacts.add(contact);
    }
}
