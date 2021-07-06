package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/stjt.png");
    ContactData contact = new ContactData()
            .withFirstName("Test1").withLastName("Test2").withAddress("123456, test test")
            .withPhoneMobile("+71111111111").withEmail("test@test.com").withGroup("[none]").withPhoto(photo);
    app.contact().create(contact, true);
    app.goTo().homePageForCreationContact();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    contact = app.contact().contactMergePhones(contact);
    contact = app.contact().contactMergeEmail(contact);
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
