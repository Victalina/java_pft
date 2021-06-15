package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private String id;
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String phoneMobile;
  private final String email;
  private final String group;


  public ContactData(String id, String firstName, String lastName, String address, String phoneMobile, String email, String group) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneMobile = phoneMobile;
    this.email = email;
    this.group = group;
  }

  public ContactData(String firstName, String lastName, String address, String phoneMobile, String email, String group) {
    this.id = null;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneMobile = phoneMobile;
    this.email = email;
    this.group = group;
  }

  public String getId() {
    return id;
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


  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", phoneMobile='" + phoneMobile + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(phoneMobile, that.phoneMobile) && Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, address, phoneMobile, email);
  }
}
