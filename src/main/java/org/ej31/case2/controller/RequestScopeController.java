package org.ej31.case2.controller;

import org.ej31.case2.services.PrototypeScopeService;
import org.ej31.case2.services.RequestScopeService;
import org.ej31.case2.services.SingletonScopeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scope/request")
public class RequestScopeController {
    private final RequestScopeService requestScopeService;
    private final PrototypeScopeService prototypeScopeService;

    public RequestScopeController(RequestScopeService requestScopeService, PrototypeScopeService prototypeScopeService) {
        this.requestScopeService = requestScopeService;
        this.prototypeScopeService = prototypeScopeService;
    }

    @GetMapping
    public String getSession() {
        requestScopeService.getSome();
        prototypeScopeService.getSome();
        return "조회 완료! ";
    }
}
