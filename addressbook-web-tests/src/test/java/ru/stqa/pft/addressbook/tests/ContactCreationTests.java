package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
   // int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Test17", "Test2", "123456, test test", "+71111111111", "test@test.com", "test1");
    app.getContactHelper().createContact(contact,true);
    app.getNavigationHelper().gotoHomePage();
    // int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

   /* int max = 0;
    for (ContactData c : after){
      if (c.getId() > max){
        max = c.getId();
      }
    }*/

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
