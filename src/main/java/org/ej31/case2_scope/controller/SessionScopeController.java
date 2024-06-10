package org.ej31.case2_scope.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.ej31.case2_scope.services.PrototypeScopeService;
import org.ej31.case2_scope.services.SessionScopeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scope/session")
public class SessionScopeController {
    private final SessionScopeService sessionScopeService;
    private final PrototypeScopeService prototypeScopeService;

    public SessionScopeController(SessionScopeService sessionScopeService, PrototypeScopeService prototypeScopeService) {
        this.sessionScopeService = sessionScopeService;
        this.prototypeScopeService = prototypeScopeService;
    }

    @PostMapping
    public String createSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true); // 세션을 가져오거나 없으면 새로 생성
        System.out.println(this.sessionScopeService.getSome());
        return "세션 생성 완료. 세션 ID: " + session.getId();
    }

    @GetMapping
    public String getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션을 가져오되, 없으면 null 반환
        if (session == null) {
            return "세션이 존재하지 않습니다.";
        }
        System.out.println(this.sessionScopeService.getSome());
        return "세션 조회 완료. 세션 ID: " + session.getId();
    }

    @DeleteMapping
    public String invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션을 가져오되, 없으면 null 반환
        if (session == null) {
            return "세션이 존재하지 않습니다.";
        }
        session.invalidate(); // 세션 무효화
        System.out.println(this.sessionScopeService.getSome());
        return "세션 무효화 완료.";
    }
}