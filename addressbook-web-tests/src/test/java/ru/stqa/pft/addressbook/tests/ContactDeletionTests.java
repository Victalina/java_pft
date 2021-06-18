package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData("Test1", "Test2", "123456, test test", "+7(111)111-11-11", "test@test.com", "test1"), true);
      app.goTo().homePageForCreationContact();
    }
  }
  @Test
  public void testContactDeletion(){
    List<ContactData> before = app.contact().list();
    int index = 0;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
