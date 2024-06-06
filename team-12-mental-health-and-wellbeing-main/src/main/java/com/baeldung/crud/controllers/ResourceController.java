package com.baeldung.crud.controllers;

import com.baeldung.crud.form.AddVideoForm;
import com.baeldung.crud.form.ScaleForm;
import com.baeldung.crud.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

@Controller
public class ResourceController {

    private final VideoRepository videoRepo;

    @Autowired
    public ResourceController(VideoRepository pRepo) {
        videoRepo = pRepo;
    }

    @RequestMapping(path = "/")
    public String rootRedirect() {
        return "aboutUs"; // since aboutUs.html is our home page
    }

    @RequestMapping("public/resource")
    public ModelAndView resource() {
        ModelAndView mav = new ModelAndView();
    /* although this will return all the resource videos to the resource page, I use thymeleaf in the front-end
       to limit only the latest 5 videos can be displayed in resource.html */
        mav.addObject("videos",videoRepo.findAllVideos());
        mav.setViewName("resource");
        return mav;
    }

// a place for admin to upload video resources and something associated with them(cover image, title)
    @RequestMapping(path = "admin/resource")
    public ModelAndView uploadVideo() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("uploadVideo");
        return mav;
    }

// a place for admin to manage video resources like delete them or recommend them to either scaleA or scaleB or reset
    @RequestMapping(path="admin/manage/videos", method = RequestMethod.GET)
    public ModelAndView searchAllVideo() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("videos",videoRepo.findAllVideos());
        mav.setViewName("allVideos");
        return mav;
    }

// upload and add cover image, video, title in to the database at the same time
    @RequestMapping(path="admin/upload/video", method = RequestMethod.POST)
    // parameter "videoFile" should be same to the name of "input" tag
    public ModelAndView UpAddVideo(@RequestParam("videoFile") MultipartFile[] files, AddVideoForm addVideoForm) throws Exception {
        ModelAndView mav = new ModelAndView();
        String filePath = null;
        String storePath = null;  // video path that need to be store
        String storePathC = null;  // cover image path that need to be store
        for(int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
        // get the suffix of the file to check their type
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        // generally, "video" tag of html5 support type mp4 and ogg (and also webm, I forgot adding this type before the deadline of this project)
            if(suffix.equals("mp4")||(suffix.equals("MP4"))||(suffix.equals("ogg"))||(suffix.equals("OGG"))||(suffix.equals("webm"))) {
            // this is the destination where uploaded video file can be stored
                filePath = "src/main/resources/static/mp4/resourceMp4/" + file.getOriginalFilename();
                store(filePath, file);
            // this is the String that should be stored in the database, and can be used as the source in 'video' tag of html
                storePath = "/mp4/resourceMp4/" + file.getOriginalFilename();
            } else {
            /* I've already restricted the type of video and image uploaded in the front-end by using "accept"
               attribute in the "input" tag, so there is no need to check the suffix of image (if a file uploaded
               is not video types above, it must be the image type restricted in the front-end) */
                filePath = "src/main/resources/static/img/resourceImg/" + file.getOriginalFilename();
                store(filePath, file);
                storePathC = "/img/resourceImg/" + file.getOriginalFilename();
            }
        }
    // addVideoForm only contain the video's title inputted by the admin
        if (videoRepo.addVideoByString(storePath, storePathC, addVideoForm)) {
            System.out.println("added video ");
            mav.addObject("videos", videoRepo.findAllVideos());
            mav.setViewName("allVideos");
        }else{
            mav.setViewName("index");
        }
        return mav;
    }
    // extract from above, used to write the uploaded file in the specific location of the project
    public void store(String filePath, MultipartFile file) throws Exception{
    // BufferedOutputStream enhance the efficiency of FileOutputStream by create a buffer in memory (I/O in buffer is faster)
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
    }

// delete a resource item in the database according to it's id
    @RequestMapping(path="admin/delete/video", method = RequestMethod.GET)
    public ModelAndView deleteResourceVideo(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        System.out.println("deleted video path");
        mav.addObject(videoRepo.deleteVideoPathById(idInt));
        mav.addObject("videos", videoRepo.findAllVideos());
        mav.setViewName("allVideos");
        return mav;
    }

// jump to a page that only contain one resource item
    @RequestMapping(path="public/SearchVideo", method = RequestMethod.GET)
    public ModelAndView searchVideo(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(videoRepo.findVideoById(idInt));
        mav.setViewName("resourceSingleVideo");
        return mav;
    }

// recommend video to user with different mental health situation
    @RequestMapping(path="admin/recommend/scaleA", method = RequestMethod.GET)
    public ModelAndView recommendScaleA(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        System.out.println("recommend video to user with good mental health");
        mav.addObject(videoRepo.changeVideoForScaleAById(idInt));
        mav.addObject("videos", videoRepo.findAllVideos());
        mav.setViewName("allVideos");
        return mav;
    }
    @RequestMapping(path="admin/recommend/scaleB", method = RequestMethod.GET)
    public ModelAndView recommendScaleB(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        System.out.println("recommend video to user with bad mental health");
        mav.addObject(videoRepo.changeVideoForScaleBById(idInt));
        mav.addObject("videos", videoRepo.findAllVideos());
        mav.setViewName("allVideos");
        return mav;
    }
    // reset flag (cancel recommendation)
    @RequestMapping(path="admin/cancel/recommend", method = RequestMethod.GET)
    public ModelAndView cancelRecommend(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        System.out.println("reset recommendation");
        mav.addObject(videoRepo.resetVideoFlagById(idInt));
        mav.addObject("videos", videoRepo.findAllVideos());
        mav.setViewName("allVideos");
        return mav;
    }

/* for questionnaire in the resource.html that can be used to know users' mental health scale so that
   admin can recommend their with different video resources (although there are only two kind of recommendation
   videos: scaleA for user with EXCELLING and THRIVING scale and scaleB for user with UNSETTLED, STRUGGLING and IN CRISIS)*/
    @RequestMapping(path="public/scale", method = RequestMethod.GET)
    public ModelAndView questionnaire(ScaleForm scaleForm, Model model, BindingResult br){
        ModelAndView mav = new ModelAndView();
        String scale = null; // this can be displayed in the webpage to let user know their own mental health scale
    // the value of each waring sign is determined in the front-end ("input" tag)
        int a = scaleForm.getInsomnia();
        int b = scaleForm.getStress();
        int c = scaleForm.getAnorexic();
        int d = scaleForm.getIsolation();
        int e = scaleForm.getSuicidal(); // value is larger than the other 4 because this is more dangerous
        int sum = a + b + c + d + e;
        mav.addObject("videos",videoRepo.findAllVideos());
        if (br.hasErrors()) {
            mav.setViewName("resource");
        } else {
            if (sum >= 3) {
                if (sum == 3) {
                    scale = "UNSETTLED";
                } else if (sum <= 5){
                    scale = "STRUGGLING";
                } else {
                    scale = "IN CRISIS";
                }
                model.addAttribute("MHscale", scale);
                mav.addObject("videosB",videoRepo.findAllScaleBVideos());
                mav.setViewName("scaleB");
            } else {
                if (sum == 0) {
                    scale = "EXCELLING";
                } else {
                    scale = "THRIVING";
                }
                model.addAttribute("MHscale", scale);
                mav.addObject("videosA",videoRepo.findAllScaleAVideos());
                mav.setViewName("scaleA");
            }
        }
        return mav;
    }

}
