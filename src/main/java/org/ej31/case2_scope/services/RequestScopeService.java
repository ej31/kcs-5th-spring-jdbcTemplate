package org.ej31.case2_scope.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
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
