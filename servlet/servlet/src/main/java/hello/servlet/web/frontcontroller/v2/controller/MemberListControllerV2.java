package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.Myview;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {
    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public Myview process(HttpServletRequest request, HttpServletResponse response) throws ServerCloneException, IOException, ServletException {
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);

        return  new Myview("/WEB-INF/views/members.jsp");

    }
}
