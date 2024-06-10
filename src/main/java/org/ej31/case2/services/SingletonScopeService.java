package org.ej31.case2.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class SingletonScopeService {

    @PostConstruct
    public void init() {
        System.out.println("SingletonScopeService.init - 빈이 초기화되었습니다.");
    }

    public String getSome() {
        return "get SSS!";  // 사용! (Usage)
    }

    @PreDestroy
    public void destroy() {
        System.out.println("SingletonScopeService.init - 빈이 소멸됩니다!");
    }
}
