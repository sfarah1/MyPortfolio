package com.baeldung.crud.controllers;

import com.baeldung.crud.DTO.GalleryPagination;
import com.baeldung.crud.form.AddGalleryPhotoForm;
import com.baeldung.crud.form.AddGalleryPhotoMessageForm;
import com.baeldung.crud.form.AddGalleryVideoForm;
import com.baeldung.crud.form.AddGalleryVideoMessageForm;
import com.baeldung.crud.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

@Controller
public class GalleryController {

    private final GalleryRepository galleryRepo;

    @Autowired
    public GalleryController(GalleryRepository pRepo) {
        galleryRepo = pRepo;
    }

// a place that display all gallery items and allow admin to operate them
    @RequestMapping(path = "admin/gallery")
    public ModelAndView searchAllGalleryItems() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("g_photo", galleryRepo.findAllPhotoComments());
        mav.addObject("g_video", galleryRepo.findAllVideoComments());
        mav.addObject("g_pmessage", galleryRepo.findAllPhotoMessage());
        mav.addObject("g_vmessage", galleryRepo.findAllVideoMessage());
        mav.setViewName("allGalleryItems");
        return mav;
    }

// delete gallery items
    @RequestMapping(path="admin/delete/gallery/photo", method = RequestMethod.GET)
    public ModelAndView deleteGalleryPhoto(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        System.out.println("deleted photo and comment");
        mav.addObject(galleryRepo.deletePhotoById(idInt));
    // just for admin to check whether update is successful in the gallery, so don't need the pagination function
        mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
        mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
        mav.setViewName("galleryOperateSuccess");
        return mav;
    }
    @RequestMapping(path="admin/delete/gallery/video", method = RequestMethod.GET)
    public ModelAndView deleteGalleryVideo(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        System.out.println("deleted video and comment");
        mav.addObject(galleryRepo.deleteGalleryVideoById(idInt));
    // just for admin to check whether update is successful in the gallery, so don't need the pagination function
        mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
        mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
        mav.setViewName("galleryOperateSuccess");
        return mav;
    }

// a place for user to upload new gallery items
    @RequestMapping(path = "register/addThingsToGallery", method = RequestMethod.GET)
    public ModelAndView userUploadToGallery() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("galleryUp");
        return mav;
    }

// upload photo and store photo's path, comment, date and owner
    @RequestMapping(path="register/upAddPhoto", method = RequestMethod.POST)
    public ModelAndView UpAddPhotoPathComment(@RequestParam("photoFile") MultipartFile file, AddGalleryPhotoForm addGalleryPhotoForm) throws Exception {
    // this is the destination where uploaded image file can be stored
        String filePath = "src/main/resources/static/img/galleryImg/" + file.getOriginalFilename();
    // this is the String that should be stored in the database, and can be used as the source in 'image' tag of html
        String storePath = "/img/galleryImg/" + file.getOriginalFilename();
    // BufferedOutputStream enhance the efficiency of FileOutputStream by create a buffer in memory (I/O in buffer is faster)
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes()); // begin to store
        outputStream.flush();
        outputStream.close();
        ModelAndView mav = new ModelAndView();
    // addGalleryPhotoForm contain the 'comment' and 'owner', which are submitted by users
        if (galleryRepo.addPhotoByString(storePath, addGalleryPhotoForm)) {
            System.out.println("added photo path");
            mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
            mav.setViewName("galleryUpSuccess");
        }else{
            mav.setViewName("aboutUs");
        }
        return mav;
    }

// upload video and store video's path, comment, date and owner
    @RequestMapping(path="register/upAddVideo", method = RequestMethod.POST)
    public ModelAndView UpAddVideoPathComment(@RequestParam("videoFile") MultipartFile file, AddGalleryVideoForm addGalleryVideoForm) throws Exception {
    // this is the destination where uploaded video file can be stored
        String filePath = "src/main/resources/static/mp4/galleryMp4/" + file.getOriginalFilename();
    // this is the String that should be stored in the database, and can be used as the source in 'video' tag of html
        String storePath = "/mp4/galleryMp4/" + file.getOriginalFilename();
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        ModelAndView mav = new ModelAndView();
    // addGalleryVideoForm contain the 'comment' and 'owner', which are submitted by users
        if (galleryRepo.addVideoByString(storePath, addGalleryVideoForm)) {
            System.out.println("added video path");
            mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
            mav.setViewName("galleryUpSuccess");
        }else{
            mav.setViewName("aboutUs");
        }
        return mav;
    }

// admin give permission to the item uploaded by the user to make it visible in the gallery
    @RequestMapping(path="admin/permit/photo", method = RequestMethod.GET)
    public ModelAndView permitPhoto(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        System.out.println("permit photo and comment");
        mav.addObject(galleryRepo.changePhotoPublicStatusById(idInt));
    // just for admin to check whether update is successful in the gallery, so don't need the pagination function
        mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
        mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
        mav.setViewName("galleryOperateSuccess");
        return mav;
    }
    @RequestMapping(path="admin/permit/video", method = RequestMethod.GET)
    public ModelAndView permitVideo(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        System.out.println("permit video and comment");
        mav.addObject(galleryRepo.changeVideoPublicStatusById(idInt));
    // just for admin to check whether update is successful in the gallery, so don't need the pagination function
        mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
        mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
        mav.setViewName("galleryOperateSuccess");
        return mav;
    }

// find single gallery items, used when user wants to check the comments(messages left by others) of the gallery items
    @RequestMapping(path="public/SearchPhoto", method = RequestMethod.GET)
    public ModelAndView searchPhoto(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(galleryRepo.findPhotoById(idInt));
    // display all message left by other user to this gallery item
        mav.addObject("g_pmessage", galleryRepo.findAllPhotoMessagesByPhotoId(idInt));
        mav.setViewName("gallerySinglePhoto");
        return mav;
    }
    @RequestMapping(path="public/SearchGalleryVideo", method = RequestMethod.GET)
    public ModelAndView searchGalleryVideo(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(galleryRepo.findGalleryVideoById(idInt));
        mav.addObject("g_vmessage", galleryRepo.findAllVideoMessagesByPhotoId(idInt));
        mav.setViewName("gallerySingleVideo");
        return mav;
    }

// pagination function. this is the part that face to the public users
    GalleryPagination page1 = new GalleryPagination(1,6,1); // for photo
    GalleryPagination page2 = new GalleryPagination(1,6,1); // for video
    @GetMapping("public/gallery")
    // the first page
    public ModelAndView findGalleryPhotoByPage(@RequestParam(defaultValue = "1") int pageNum,
                                       /* max rows of each page can only be modified in the below line
                                       (the default parameters in page1, page2 above just to prevent error, don't need to be change,
                                       once you change the pageRow value below and re-run the server, please at first execute
                                       this method (click the 'gallery' in the navigation bar, or photo/video convert button in the gallery page)
                                          the defaultValue of pageRow shouldn't be 1 ! */
                                               @RequestParam(defaultValue = "6") int pageRow) {
        ModelAndView mav = new ModelAndView();
    // assign the default value of pageNum and pageRow to page1 that can also be used in next and previous page function
        page1.setPageNum(pageNum);
        page1.setPageRow(pageRow);
    // compute the max page number and assign it to page1
        int max = maxPhotoPage(pageRow);
        page1.setPageTotal(max);
        System.out.println(pageNum + " " + pageRow);
        mav.addObject("g_photo", galleryRepo.findAllPublicPhotoByPage(pageNum, pageRow));
        mav.addObject("page", page1); // show current page situation in the webpage
        mav.setViewName("gallery");
        return mav;
    }

    @GetMapping("public/galleryNextPhoto")
    public ModelAndView findGalleryPhotoNextPage() {
        ModelAndView mav = new ModelAndView();
        int pageRow = page1.getPageRow();
        int pageNum = page1.getPageNum();
        int max = maxPhotoPage(pageRow);
        if (pageNum < max) { // if not the last page
            pageNum++; // go to next page
        } // else remain in this last page
        return getPhotoPagination(mav, pageRow, pageNum, max);
    }

    @GetMapping("public/galleryPreviousPhoto")
    public ModelAndView findGalleryPhotoPreviousPage() {
        ModelAndView mav = new ModelAndView();
        int pageRow = page1.getPageRow();
        int pageNum = page1.getPageNum();
        if (pageNum > 1) { // if not the first page
            pageNum--; // go to previous page
        } // else remain in the first page
        int max = maxPhotoPage(pageRow);
        return getPhotoPagination(mav, pageRow, pageNum, max);
    }

    // extract from next and previous photo page methods above
    private ModelAndView getPhotoPagination(ModelAndView mav, int pageRow, int pageNum, int max) {
        page1.setPageNum(pageNum); // change the current page
    /* assign the max page number to page1 in the next and previous page methods just because
       if I don't do this, it will get wrong when admin re-run the server and directly click next and
       previous page button without execute the findGalleryPhotoByPage() method above to reset the values in page1 */
        page1.setPageTotal(max);
        System.out.println(pageNum + " " + pageRow);
        mav.addObject("g_photo", galleryRepo.findAllPublicPhotoByPage(pageNum, pageRow));
        mav.addObject("page", page1); // show current page situation in the webpage
        mav.setViewName("gallery");
        return mav;
    }
    // extract from above methods
    public int maxPhotoPage(int pageRow) {
        int photoNum = galleryRepo.countAllPublicPhoto();
        int max = 0;
        // performance is different according to whether photoNum is the multiple of pageRow
        if (photoNum % pageRow != 0) {
            max = photoNum / pageRow + 1;
        } else {
            max = photoNum / pageRow;
        }
        return max;
    }

// video pagination, similar to above
    @GetMapping("public/galleryVideo")
    public ModelAndView findGalleryVideoByPage(@RequestParam(defaultValue = "1") int pageNum,
                                       /* max rows of each page can only be modified in the below line
                                       (the default parameters in page1 above just to prevent error, don't need to be change,
                                       once you change the pageRow value below and re-run the server, please at first execute
                                       this method (click the 'gallery' in the navigation bar)
                                          the defaultValue of pageRow shouldn't be 1 ! */
                                               @RequestParam(defaultValue = "6") int pageRow) {
        ModelAndView mav = new ModelAndView();
    // assign the default value of pageNum and pageRow to page2 that can also be used in next and previous page function
        page2.setPageNum(pageNum);
        page2.setPageRow(pageRow);
    // compute the max page number and assign it to page2
        int max = maxVideoPage(pageRow);
        page2.setPageTotal(max);
        mav.addObject("g_video", galleryRepo.findAllPublicVideoByPage(pageNum, pageRow));
        mav.addObject("page", page2); // show current page situation in the webpage
        mav.setViewName("gallery2");
        System.out.println(page2.getPageNum() + " " + page2.getPageRow());
        return mav;
    }
    @GetMapping("public/galleryVideoNext")
    public ModelAndView findGalleryVideoNextPage() {
        ModelAndView mav = new ModelAndView();
        int pageRow = page2.getPageRow();
        int pageNum = page2.getPageNum();
        int max = maxVideoPage(pageRow);
        if (pageNum < max) { // if not the last page
            pageNum++; // go to next page
        } // else remain in the last page
        return getVideoPagination(mav, pageRow, pageNum, max);
    }

    @GetMapping("public/galleryVideoPrevious")
    public ModelAndView findGalleryVideoPreviousPage() {
        ModelAndView mav = new ModelAndView();
        int pageRow = page2.getPageRow();
        int pageNum = page2.getPageNum();
        if (pageNum > 1) { // if not the first page
            pageNum--; // go to previous page
        } // else remain in the first page
        int max = maxVideoPage(pageRow);
        return getVideoPagination(mav, pageRow, pageNum, max);
    }

    // extract from next and previous video page methods above
    private ModelAndView getVideoPagination(ModelAndView mav, int pageRow, int pageNum, int max) {
        page2.setPageNum(pageNum); // change the current page
    /* assign the max page number to page2 in the next and previous page methods just because
       if I don't do this, it will get wrong when admin re-run the server and directly click next and
       previous page button without execute the findGalleryVideoByPage() method above to reset the values in page2 */
        page2.setPageTotal(max);
        System.out.println(pageNum + " " + pageRow);
        mav.addObject("g_video", galleryRepo.findAllPublicVideoByPage(pageNum, pageRow));
        mav.addObject("page", page2); // show current page situation in the webpage
        mav.setViewName("gallery2");
        return mav;
    }
    // extract from above methods
    public int maxVideoPage(int pageRow) {
        int videoNum = galleryRepo.countAllPublicVideo();
        int max = 0;
        // performance is different according to whether videoNum is the multiple of pageRow
        if (videoNum % pageRow != 0) {
            max = videoNum / pageRow + 1;
        } else {
            max = videoNum / pageRow;
        }
        return max;
    }

// "give a like" function for gallery items
    @RequestMapping(path="public/liked/gallery/photo", method = RequestMethod.GET)
    public ModelAndView likedPhoto(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(galleryRepo.likedPhotoById(idInt)); // make the "liked" value of a gallery item in the webpage plus 1
        mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
        mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
        mav.setViewName("galleryOperateSuccess");
        return mav;
    }
    @RequestMapping(path="public/liked/gallery/video", method = RequestMethod.GET)
    public ModelAndView likedGalleryVideo(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(galleryRepo.likedGalleryVideoById(idInt));
        mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
        mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
        mav.setViewName("galleryOperateSuccess");
        return mav;
    }

// make comments function, allow public visitors to leave message to each gallery item
    @RequestMapping(path="public/leavePhotoMessage", method = RequestMethod.POST)
    public ModelAndView leavePhotoMessage(AddGalleryPhotoMessageForm addGalleryPhotoMessageForm, BindingResult br) {
        ModelAndView mav = new ModelAndView();
        if (br.hasErrors()) {
            mav.setViewName("aboutUs");
        } else {
            if (galleryRepo.addPhotoMessage(addGalleryPhotoMessageForm)) {
                mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
                mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
                mav.setViewName("galleryOperateSuccess");
            } else {
                mav.setViewName("aboutUs");
            }
        }
        return mav;
    }
    @RequestMapping(path="public/leaveVideoMessage", method = RequestMethod.POST)
    public ModelAndView leaveVideoMessage(AddGalleryVideoMessageForm addGalleryVideoMessageForm, BindingResult br) {
        ModelAndView mav = new ModelAndView();
        if (br.hasErrors()) {
            mav.setViewName("aboutUs");
        } else {
            if (galleryRepo.addVideoMessage(addGalleryVideoMessageForm)) {
                mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
                mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
                mav.setViewName("galleryOperateSuccess");
            } else {
                mav.setViewName("aboutUs");
            }
        }
        return mav;
    }

// delete comments
    @RequestMapping(path="admin/delete/gallery/photoMessage", method = RequestMethod.GET)
    public ModelAndView deleteGalleryPhotoMessage(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(galleryRepo.deletePhotoMessageById(idInt));
        mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
        mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
        mav.addObject("g_pmessage", galleryRepo.findAllPhotoMessage());
        mav.addObject("g_vmessage", galleryRepo.findAllVideoMessage());
        mav.setViewName("allGalleryItems");
        return mav;
    }
    @RequestMapping(path="admin/delete/gallery/videoMessage", method = RequestMethod.GET)
    public ModelAndView deleteGalleryVideoMessage(@RequestParam(value="id", defaultValue="null") int idInt) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(galleryRepo.deleteVideoMessageById(idInt));
        mav.addObject("g_photo", galleryRepo.findAllPublicPhotoComments());
        mav.addObject("g_video", galleryRepo.findAllPublicVideoComments());
        mav.addObject("g_pmessage", galleryRepo.findAllPhotoMessage());
        mav.addObject("g_vmessage", galleryRepo.findAllVideoMessage());
        mav.setViewName("allGalleryItems");
        return mav;
    }

}
