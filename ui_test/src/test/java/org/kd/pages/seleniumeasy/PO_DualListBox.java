package org.kd.pages.seleniumeasy;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.kd.pages.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.*;

public class PO_DualListBox extends BasePage {

    private final WO_SideMenu mainMenu;
    private final WO_Footer footer;

    public PO_DualListBox() {
        super("https://www.seleniumeasy.com/test/bootstrap-dual-list-box-demo.html");
        this.mainMenu = new WO_SideMenu();
        this.footer = new WO_Footer();
    }

    @Step("Enter {0} in Search Dual List")
    public void typeInSearchDualList(String entry) {
        SelenideElement searchDualList = $(By.name("SearchDualList"));
        searchDualList.clear();
        searchDualList.sendKeys(entry);
        searchDualList.pressEnter();
    }

    public ElementsCollection getStatementList() {
        return $("div[class = 'well text-right']")
                .find("ul[class = 'list-group']")
                .findAll(By.tagName("li"))
                .filter(not(empty));
    }

    public void clickMenuOption(String option){
        this.mainMenu.selectOption(option);
    }

    public void clickTutorialsMenuOption(String option){
        this.footer.clickTutorialsMenuOption(option);
    }

    @Override
    public void navigate() {
        open(this.url);
    }
}