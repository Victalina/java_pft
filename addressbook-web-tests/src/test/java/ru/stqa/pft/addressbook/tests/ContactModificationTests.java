package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData()
              .withFirstName("Test1").withLastName("Test2").withAddress("123456, test test").withPhoneMobile("+71111111111").withEmail("test@test.com").withGroup("[none]"), true);
      app.goTo().homePageForCreationContact();
    }
  }
  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int index = 0;
    ContactData contact = new ContactData().withId(before.get(index).getId())
            .withFirstName("Test17").withLastName("Test2").withAddress("123456, test test").withPhoneMobile("+71111111111").withEmail("test@test.com");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
