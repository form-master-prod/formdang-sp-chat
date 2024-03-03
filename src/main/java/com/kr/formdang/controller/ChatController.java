package com.kr.formdang.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    @GetMapping("/view/chat")
    public ModelAndView chatView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chat.html");
        return modelAndView;
    }

    @GetMapping("/view/room")
    public ModelAndView roomView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("room.html");
        return modelAndView;
    }
}
