package org.ej31.case2_scope.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeService {

    @PostConstruct
    public void init() {
        System.out.println("RequestScopeService.init - 빈이 초기화되었습니다.");
    }


    public String getSome() {
        return "get RSS!";  // 사용! (Usage)
    }

    @PreDestroy
    public void destroy() {
        System.out.println("RequestScopeService.init - 빈이 소멸됩니다!");
    }
}
