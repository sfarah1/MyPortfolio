package com.baeldung.crud.controllers;

import com.baeldung.crud.repositories.VideoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Objects;

@WebAppConfiguration
@SpringBootTest
class ResourceControllerTest {

    private static ResourceController resourceController;
    private static VideoRepository videoRepo;

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @BeforeAll
    public static void before() {
        resourceController = new ResourceController(videoRepo);
    }

    @Test
    void rootRedirect() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        assert mav != null;
        Assertions.assertEquals("resource", mav.getViewName());
    }

    @Test
    void resource() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/public/resource");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        assert mav != null;
        Assertions.assertEquals("resource", mav.getViewName());
    }

    @Test
    void uploadVideo() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/admin/resource");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        assert mav != null;
        Assertions.assertEquals("uploadVideo", mav.getViewName());
    }

    @Test
    void searchAllVideo() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/admin/manage/videos");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        assert mav != null;
        Assertions.assertEquals("allVideos", mav.getViewName());
    }

    @Test
    void upAddVideo() {
    }

    @Test
    void store() {
    }

    @Test
    void deleteResourceVideo() throws Exception {
//        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/admin/delete/video");
//        MockHttpServletResponse response = new MockHttpServletResponse();
//        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
//        ModelAndView mav = handlerAdapter.handle(request, response, handler);
//        assert mav != null;
//        Assertions.assertEquals("allVideos", mav.getViewName());
    }

    @Test
    void searchVideo() {
    }

    @Test
    void recommendScaleA() {
    }

    @Test
    void recommendScaleB() {
    }

    @Test
    void cancelRecommend() {
    }

    @Test
    void questionnaire() {
    }
}