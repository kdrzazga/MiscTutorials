package org.kd.testcase.seleniumeasy;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.kd.pages.seleniumeasy.PO_DualListBox;
import org.kd.testcase.BaseTest;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

public class TestSeleniumEasy extends BaseTest {

    @Severity(SeverityLevel.NORMAL)
    @Story("JIRA-1")
    @Description("")
    @Test
    public void testFooterNavigation() {
        PO_DualListBox po_dualListBox = new PO_DualListBox();
        po_dualListBox.navigate();
        clickSeleniumTutorials(po_dualListBox);
        $("title").shouldHave(attribute("text", "Selenium Tutorials | Selenium Easy"));
    }

    @Step("Click Selenium Tutorials")
    private void clickSeleniumTutorials(PO_DualListBox po_dualListBox) {
        po_dualListBox.clickTutorialsMenuOption("Selenium Tutorials");
    }

}
