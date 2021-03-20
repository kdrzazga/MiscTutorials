package org.kd.pages.seleniumeasy;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$x;

public class WO_Footer {

    private ElementsCollection getTutorialElements() {
        return $$x("//footer/div[@class='container']/div")
                .get(0)
                .$$(By.xpath("ul/li"));
    }

    private ElementsCollection getPopularPosts() {
        return $$x("//footer/div[@class='container']/div")
                .get(1)
                .$$(By.xpath("ul/li"));
    }

    public void clickTutorialsMenuOption(String option) {
        getTutorialElements()
                .filter(Condition.exactText(option))
                .first()
                .$("a")
                .click();
    }

    public void clickPopularPostsOption(String option) {
        getPopularPosts()
                .filter(Condition.exactText(option))
                .first()
                .$("a")
                .click();
    }
}
