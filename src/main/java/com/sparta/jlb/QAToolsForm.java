package com.sparta.jlb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QAToolsForm {

    private WebDriver driver;

    private String qaToolsFormURL = "http://toolsqa.com/automation-practice-form/";

    private By firstNameFieldName = By.name("firstname");
    private By lastNameFieldName = By.name("lastname");
    private String sexRadioButtonID = "sex-";
    private String yearsOfExperienceID = "exp-";
    private By continentDropDownListID = By.id("continents");
    private By continentDropDownListOptionsCssSelector = By.cssSelector("#continents option");

    public QAToolsForm(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void goToQAFormPage(){
        driver.navigate().to(qaToolsFormURL);
    }

    public void inputFirstName(String firstName){
        driver.findElement(firstNameFieldName).sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        driver.findElement(lastNameFieldName).sendKeys(lastName);
    }

    public void selectSexButton(String sex){

        if (sex.toLowerCase().equals("male")){
            driver.findElement(By.id(sexRadioButtonID + 0)).click();
        }else if (sex.toLowerCase().equals("female")){
            driver.findElement(By.id(sexRadioButtonID + 1)).click();
        }else{
            System.out.println("Please select from either 'male' or 'female'");
        }
    }

    public void selectYearsOfExperience(Integer numberOfYears){

        if (numberOfYears > 0 && numberOfYears < 8){
            driver.findElement(By.id(yearsOfExperienceID + --numberOfYears)).click();
        }else{
            System.out.println("please select an amount between 1 and 7");
        }
    }


    public void selectContinent(String continentName) {
        //Create list of continents from getContinentElementsAsStringList
        List<String> continents = getContinentElementsAsStringList();

        if (continents.contains(continentName)) {
            // You need to create a Select object but call the individual element that contains
            // If you check the drop down
            Select continentOptions = new Select(driver.findElement(continentDropDownListID));
            continentOptions.selectByVisibleText(continentName);
        } else if (!continents.contains(continentName)) {
            System.out.println("Please select one of the below options");

            for (String continent : continents) {
                System.out.println(continent);
            }
        }
    }

    //Helper Methods

    private List<String> getContinentElementsAsStringList() {
        List<WebElement> continentOptions = driver.findElements(continentDropDownListOptionsCssSelector);
        List<String> continentsAsStringList = new ArrayList<>();

        for (WebElement continent : continentOptions) {
            continentsAsStringList.add(continent.getText());
        }

        return continentsAsStringList;
    }

    //Method Validators

    public boolean CheckFirstNameIsEntered(String firstName){

        boolean enteredFirstNamePresent;
        enteredFirstNamePresent = driver.findElement(firstNameFieldName).getAttribute("value").trim().equals(firstName);
        return enteredFirstNamePresent;
    }

    public boolean CheckLastNameIsEntered(String lastName){

        boolean enteredFirstNamePresent;
        enteredFirstNamePresent = driver.findElement(lastNameFieldName).getAttribute("value").trim().equals(lastName);
        return enteredFirstNamePresent;
    }

    public boolean CheckSexSelected(String sex){
        boolean selectedSexCorrect = false;
        if (sex.toLowerCase().equals("male")){
            selectedSexCorrect = driver.findElement(By.id(sexRadioButtonID + 0)).isSelected();
        }else if (sex.toLowerCase().equals("female")){
            selectedSexCorrect = driver.findElement(By.id(sexRadioButtonID + 1)).isSelected();
        }
        return selectedSexCorrect;
    }

    public boolean CheckNumberOfYearsSelected(Integer numberOfYears){
        boolean selectedCorrectly;
        selectedCorrectly = driver.findElement(By.id(yearsOfExperienceID + --numberOfYears)).isSelected();
        return selectedCorrectly;
    }

    public boolean CheckContinentValueIsSelected(String continentName){

        boolean selectedCorrectly;
        Select continentOptions = new Select(driver.findElement(continentDropDownListID));
        selectedCorrectly = continentOptions.getFirstSelectedOption().getText().equals(continentName);
        return selectedCorrectly;
    }

}
