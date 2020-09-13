package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Test1", "Test2", "123456, test test", "+7(111)111-11-11", "test@test.com", "test1"),true);
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }
}
