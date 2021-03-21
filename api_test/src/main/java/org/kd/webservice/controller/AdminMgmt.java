package org.kd.webservice.controller;

import org.kd.webservice.main.Webservice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminMgmt {

    @RequestMapping(path = "/")
    public String printInfo() {
        var header = "<!DOCTYPE html>\n<html>\n<body>";
        var info = "SOME INFO";
        var footer = "\n</body>\n</html>";

        return header + info + footer;
    }

    @PostMapping(path = "/stop")
    public void stop() {
        Webservice.stop();
    }

}
