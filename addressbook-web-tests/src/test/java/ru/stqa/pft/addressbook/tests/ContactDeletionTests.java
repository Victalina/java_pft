package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Test1", "Test2", "123456, test test", "+7(111)111-11-11", "test@test.com", "test1"), true);
      app.getNavigationHelper().gotoHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(0);
    app.getContactHelper().deleteSelectedContact();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
