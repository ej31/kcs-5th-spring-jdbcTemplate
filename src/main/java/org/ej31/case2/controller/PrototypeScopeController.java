package org.ej31.case2.controller;

import org.ej31.case2.services.PrototypeScopeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 프로토타입(Prototype) 스코프
 * - 요청 시마다 새로운 빈 인스턴스가 생성됩니다.
 * - 스프링 컨테이너는 프로토타입 빈의 소멸을 관리하지 않기 때문에 @PreDestroy 애너테이션이 붙은 메서드가 자동으로 호출되지 않습니다.
 * - 프로토타입 빈을 사용하는 코드가 수동으로 소멸 작업을 처리해야 합니다.
 */
@RestController
@RequestMapping("/scope/prototype")
public class PrototypeScopeController {
    private final PrototypeScopeService prototypeScopeService;

    public PrototypeScopeController(PrototypeScopeService prototypeScopeService) {
        this.prototypeScopeService = prototypeScopeService;
    }

    @GetMapping
    public String getSession() {
        prototypeScopeService.getSome();
        return "조회 완료! ";
    }
}
