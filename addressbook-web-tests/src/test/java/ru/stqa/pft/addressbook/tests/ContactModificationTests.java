package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Test1", "Test2", "123456, test test", "+7(111)111-11-11", "test@test.com", "test1"), true);
      app.getNavigationHelper().gotoHomePage();
    }
  }
  @Test
  public void testContactModification(){
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = 0;
    ContactData contact = new ContactData(before.get(index).getId(), "Test7", "Test2", "123456, test test", "+71111111111", "test@test.com", null);
    app.getContactHelper().modifyContact(index, contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
