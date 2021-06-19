package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withFirstName("Test17").withLastName("Test2").withAddress("123456, test test").withPhoneMobile("+71111111111").withEmail("test@test.com").withGroup("[none]");
    app.contact().create(contact,true);
    app.goTo().homePageForCreationContact();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
