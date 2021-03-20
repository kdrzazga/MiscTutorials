package org.kd.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public abstract class BasePage {

    protected final String url;

    public abstract void navigate();
}
