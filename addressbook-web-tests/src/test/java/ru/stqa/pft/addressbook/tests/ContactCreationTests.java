package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Test17").withLastName("Test2").withAddress("123456, test test").withPhoneMobile("+71111111111").withEmail("test@test.com").withGroup("[none]");
    app.contact().create(contact,true);
    app.goTo().homePageForCreationContact();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
