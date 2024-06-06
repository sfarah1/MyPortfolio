package com.baeldung.crud.controllers;

import com.baeldung.crud.entities.News;
import com.baeldung.crud.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class NewsController {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/news")
    public String showNewsList(Model model) {
        model.addAttribute("newsList", newsRepository.findAll());
        return "news";
    }

    @GetMapping("/admin/news")
    public String showAdminNewsList(Model model) {
        model.addAttribute("news", newsRepository.findAll());
        return "admin-news";
    }

    @GetMapping("/admin/news/add")
    public String showAddNews(News news) {
        return "add-news";
    }


    @PostMapping("/admin/news/add")
    public String addNews(@Valid News news, BindingResult result, Model model,@RequestParam("imageURL") MultipartFile[] files) throws Exception {
        String storePath = null;
        MultipartFile file = files[0];
        if(file.getSize() > 0) {
            storePath = storeImages(file);
        } else {
            return "add-news";
        }
        if (result.hasErrors() && !result.hasFieldErrors("imageURL")) {
            return "add-news";
        }
        news.setImageURL(storePath);

        newsRepository.save(news);
        return "redirect:/admin/news";
    }

    @GetMapping("/admin/news/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        News news = newsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid news Id:" + id));
        model.addAttribute("news", news);

        return "update-news";
    }

    @PostMapping("/admin/news/update/{id}")
    public String updateNews(@PathVariable("id") long id, @Valid News news, BindingResult result, Model model,@RequestParam("imageURL") MultipartFile[] files) throws Exception {
        String storePath = null;
        MultipartFile file = files[0];
        if(file.getSize() > 0) {
            storePath = storeImages(file);
        } else {
            news.setId(id);
            return "update-news";
        }
        if (result.hasErrors() && !result.hasFieldErrors("imageURL")) {
            news.setId(id);
            return "update-news";
        }
        news.setImageURL(storePath);

        newsRepository.save(news);

        return "redirect:/admin/news";
    }

    @GetMapping("/admin/news/delete/{id}")
    public String deleteNews(@PathVariable("id") long id, Model model) {
        News news = newsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid news Id:" + id));
        newsRepository.delete(news);

        return "redirect:/admin/news";
    }

    public String storeImages(MultipartFile file) throws Exception{
        String filePath = null;
        String storePath = null;
        filePath = "src/main/resources/static/images/news/" + file.getOriginalFilename();
        storeFiles(filePath, file);
        storePath = "/images/news/" + file.getOriginalFilename();
        return storePath;
    }

    public void storeFiles(String filePath, MultipartFile file) throws Exception{
        File file_var = new File(filePath);
        file_var.getParentFile().mkdirs();
        file_var.createNewFile();
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file_var,false));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
    }

}
