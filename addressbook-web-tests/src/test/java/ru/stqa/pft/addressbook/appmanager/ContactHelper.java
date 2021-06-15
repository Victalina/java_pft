package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

  public void selectContact(int i) {
    wd.findElements(By.name("selected[]")).get(i).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("(//input[@value='Delete'])"));
    acceptAlert();
  }

  public void initContactModification(int i) {
    wd.findElements(By.xpath("(//img[@alt='Edit'])")).get(i).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    click(By.xpath("(//a[text()='home page'])"));
  }

  public void createContact(ContactData contact, boolean creation1) {
    initContactCreation();
    fillContactForm(contact, creation1);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    // List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']/td[2]"));
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.xpath(".//td[1]/input")).getAttribute("value"));
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      String phoneMobile = element.findElement(By.xpath(".//td[6]")).getText();;
      String email = element.findElement(By.xpath(".//td[5]")).getText();
      ContactData contact = new ContactData(id, firstName, lastName, address, phoneMobile, email, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
