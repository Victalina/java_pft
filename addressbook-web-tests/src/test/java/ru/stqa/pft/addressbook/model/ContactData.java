package ru.stqa.pft.addressbook.model;

import java.util.Objects;

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

  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
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
    return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(phoneMobile, that.phoneMobile) && Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, address, phoneMobile, email);
  }
}
