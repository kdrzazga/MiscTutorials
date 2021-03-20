package org.kd.testcase;

import com.codeborne.selenide.Configuration;

public abstract class BaseTest {

    static {
        Configuration.startMaximized = true;
    }
}
