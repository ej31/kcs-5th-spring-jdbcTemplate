package org.ej31.case2_scope.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("application")
public class PrototypeScopeService {

    @PostConstruct
    public void init() {
        System.out.println("PrototypeScopeService.init - 빈이 초기화되었습니다.");
    }

    @Autowired
    public PrototypeScopeService() {
        System.out.println("PrototypeScopeService.init - 빈이 인스턴스화되었습니다.");
    }

    public String getSome() {
        return "get PSS!"; // 사용! (Usage)
    }

    @PreDestroy
    public void destroy() {
        System.out.println("PrototypeScopeService.init - 빈이 소멸됩니다!");
    }
}
