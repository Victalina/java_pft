package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

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
  public void testContactDeletion(){
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
