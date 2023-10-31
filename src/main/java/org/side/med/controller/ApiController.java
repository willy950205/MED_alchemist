package org.side.med.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.side.med.service.util.ApiServiceUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("drug")
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final ApiServiceUtil util;

    @GetMapping("/search")
    public String searchDrug() {
        log.info("ApiController.serachDrug");
        return "search Drug";
    }

    @GetMapping("/res")
    public String getKeyTest() {
        log.info("ApiController.resourceValueTest");
        return util.serachDrug().toString();
    }
}
