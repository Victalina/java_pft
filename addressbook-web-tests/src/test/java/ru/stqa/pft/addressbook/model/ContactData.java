package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String phoneMobile;
  private final String email;
  private final String group;

  public ContactData(String firstName, String lastName, String address, String phoneMobile, String email, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneMobile = phoneMobile;
    this.email = email;
    this.group = group;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneMobile() {
    return phoneMobile;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
