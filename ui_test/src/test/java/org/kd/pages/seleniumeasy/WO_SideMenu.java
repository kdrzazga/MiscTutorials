package org.kd.pages.seleniumeasy;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.$$x;

public class WO_SideMenu {

    public void selectOption(String option) {
        Optional<SelenideElement> optionToClick =
                $$x("//li[@style = 'display: list-item;']")
                        .filter(Condition.exactText(option))
                        .stream()
                        .findFirst();

        if (optionToClick.isPresent()) {
            SelenideElement optionLink = optionToClick.get().find("a");
            optionLink.shouldBe(Condition.visible, Duration.ofMillis(1000));
            optionLink.click();
            System.out.println(option + " clicked");
        } else System.err.println(option + "was not found");

    }
}
