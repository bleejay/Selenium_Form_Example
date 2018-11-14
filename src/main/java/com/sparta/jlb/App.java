package com.sparta.jlb;


import org.openqa.selenium.chrome.ChromeDriver;

public class App
{
    public static void main( String[] args ){

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\TECH-W87\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver chromeDriver = new ChromeDriver();
        QAToolsForm qaToolsForm = new QAToolsForm(chromeDriver);
        qaToolsForm.goToQAFormPage();
        qaToolsForm.inputFirstName("Jason");
        System.out.println("check first name = " + qaToolsForm.CheckFirstNameIsEntered("Jason"));
        qaToolsForm.inputLastName("Blee");
        System.out.println("check last name = " + qaToolsForm.CheckLastNameIsEntered("Blee"));
        qaToolsForm.selectSexButton("male");
        System.out.println("selected sex is correct = " + qaToolsForm.CheckSexSelected("male"));
        qaToolsForm.selectYearsOfExperience(6);
        System.out.println("selected years is correct = " + qaToolsForm.CheckNumberOfYearsSelected(6));
        qaToolsForm.selectContinent("Europe");
        System.out.println("check selected continent = " + qaToolsForm.CheckContinentValueIsSelected("Europe"));

    }
}
