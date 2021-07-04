package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getPhoneMobile());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {

    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void initContactCreation() {

    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value ='" + id +"']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("(//input[@value='Delete'])"));
    acceptAlert();
  }

  public void initContactModification(int i) {
    wd.findElements(By.xpath("(//img[@alt='Edit'])")).get(i).click();
  }
  public void initContactModificationById(int id) {
   wd.findElement(By.cssSelector("input[value = '" + id + "']")).findElement(By.xpath(".//../../td[8]/a/img[@alt='Edit']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    click(By.xpath("(//a[text()='home page'])"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
  /*  if(contactCache != null){
      return new Contacts(contactCache);
    } */
    Contacts contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.xpath(".//td[1]/input")).getAttribute("value"));
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      String allphones = element.findElement(By.xpath(".//td[6]")).getText();
      String allemails = element.findElement(By.xpath(".//td[5]")).getText();
      contactCache.add(new ContactData()
              .withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address)
              .withAllPhones(allphones).withAllEmails(allemails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withPhoneHome(home).withPhoneMobile(mobile).withPhoneWork(work).withAddress(address).withEmail(email).withEmail2(email2)
            .withEmail3(email3);
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String replaceNull(String input) {
    return input == null ? "" : input;
  }

  public ContactData contactMergePhones(ContactData contact){
    String mergePhones = Arrays.asList(replaceNull(contact.getPhoneHome()), replaceNull(contact.getPhoneMobile()),
            replaceNull(contact.getPhoneWork())).stream().filter((s) -> !s.equals(""))
            .map(ContactHelper::cleaned).collect(Collectors.joining("\n"));
    return contact.withAllPhones(mergePhones).withPhoneHome(null).withPhoneMobile(null).withPhoneWork(null);
  }

  public ContactData contactMergeEmail (ContactData contact){
    String mergeEmails = Arrays.asList(replaceNull(contact.getEmail()), replaceNull(contact.getEmail2()),
            replaceNull(contact.getEmail3())).stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    return contact.withAllEmails(mergeEmails).withEmail(null).withEmail2(null).withEmail3(null);
  }
}
