package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData()
              .withFirstName("Test1").withLastName("Test2").withAddress("123456, test test").withPhoneMobile("+71111111111").withEmail("test@test.com").withGroup("[none]"), true);
      app.goTo().homePageForCreationContact();
    }
  }
  @Test
  public void testContactModification(){
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstName("Test17").withLastName("Test2").withAddress("123456, test test").withPhoneMobile("+71111111111").withEmail("test@test.com");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
