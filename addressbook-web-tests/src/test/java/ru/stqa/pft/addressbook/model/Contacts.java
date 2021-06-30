package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Contacts extends ForwardingSet<ContactData>{

  private Set<ContactData> delegate;

  public Contacts(Contacts contacts){
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }
  public Contacts(){
    this.delegate = new HashSet<ContactData>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }
  public Contacts without(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
