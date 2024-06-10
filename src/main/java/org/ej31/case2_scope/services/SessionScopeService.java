package org.ej31.case2_scope.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopeService {

    @PostConstruct
    public void init() {
        System.out.println("SessionScopeService.init - 빈이 초기화되었습니다.");
    }

    public String getSome() {
        return "get 세션 스코프 서비스 getSome() 메서드 호출 됨";  // 사용! (Usage)
    }

    @PreDestroy
    public void destroy() {
        System.out.println("SessionScopeService.init - 빈이 소멸됩니다!");
    }
}
