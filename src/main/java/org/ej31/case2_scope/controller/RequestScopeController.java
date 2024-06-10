package org.ej31.case2_scope.controller;

import org.ej31.case2_scope.services.PrototypeScopeService;
import org.ej31.case2_scope.services.RequestScopeService;
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
