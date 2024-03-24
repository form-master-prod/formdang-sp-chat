package com.kr.formdang.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ViewController {

    @GetMapping("/view/socket/chat/{channel}")
    public ModelAndView viewSocketChat(@PathVariable(value = "channel") String channel) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/socket/chat.html");
        modelAndView.addObject("channel", channel);
        return modelAndView;
    }

    @GetMapping("/view/socket/room")
    public ModelAndView viewSocketRoom() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/socket/room.html");
        return modelAndView;
    }
}
