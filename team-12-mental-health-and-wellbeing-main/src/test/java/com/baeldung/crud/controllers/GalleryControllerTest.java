package com.baeldung.crud.controllers;

import com.baeldung.crud.repositories.GalleryRepository;
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
class GalleryControllerTest {

    private static GalleryController galleryController;
    private static GalleryRepository galleryRepo;

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @BeforeAll
    public static void before() {
        galleryController = new GalleryController(galleryRepo);
    }

    @Test
    public void searchAllGalleryItems() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/admin/gallery");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        assert mav != null;
        Assertions.assertEquals("allGalleryItems", mav.getViewName());
    }

//    @Test
//    void deleteGalleryPhoto(@RequestParam(value="id", defaultValue="null") int i) throws Exception {
//        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/admin/delete/gallery/photo");
//        MockHttpServletResponse response = new MockHttpServletResponse();
//        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
//        ModelAndView mav = handlerAdapter.handle(request, response, handler);
//        assert mav != null;
//        Assertions.assertEquals("gallery", mav.getViewName());
//    }

    @Test
    void deleteGalleryVideo() {
    }

    @Test
    void userUploadToGallery() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/register/addThingsToGallery");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        assert mav != null;
        Assertions.assertEquals("galleryUp", mav.getViewName());
    }

    @Test
    void upAddPhotoPathComment() {
    }

    @Test
    void upAddVideoPathComment() {
    }

    @Test
    void permitPhoto() {
    }

    @Test
    void permitVideo() {
    }

    @Test
    void findGalleryPhotoByPage() {
    }

    @Test
    void findGalleryPhotoNextPage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/public/gallery/photo/next");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        assert mav != null;
        Assertions.assertEquals("gallery", mav.getViewName());
    }

    @Test
    void findGalleryPhotoPreviousPage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/public/gallery/photo/previous");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        assert mav != null;
        Assertions.assertEquals("gallery", mav.getViewName());
    }

    @Test
    void findGalleryVideoByPage() {
    }

    @Test
    void findGalleryVideoNextPage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/public/gallery/video/next");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        assert mav != null;
        Assertions.assertEquals("gallery2", mav.getViewName());
    }

    @Test
    void findGalleryVideoPreviousPage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/public/gallery/video/previous");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = Objects.requireNonNull(handlerMapping.getHandler(request)).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        assert mav != null;
        Assertions.assertEquals("gallery2", mav.getViewName());
    }
}