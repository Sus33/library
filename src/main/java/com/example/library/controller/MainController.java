package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class MainController {
    @Value("D\\mvc\\")
    private String imageUploadPath;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String main(ModelMap modelMap) {
        modelMap.addAttribute ("book", new Book ());
        modelMap.addAttribute ("user", new User ());
        return "index";
    }
    @RequestMapping(value = "/authorHome", method = RequestMethod.GET)
    public String authorHome(ModelMap modelMap) {
        modelMap.addAttribute ("book", new Book ());
        return "user";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("Book") Book book) {
        bookRepository.save (book);
        return "redirect:/authorHome";

    }

    @RequestMapping(value = "/addAuthor", method = RequestMethod.POST)
    public String addAuthor(@ModelAttribute("User") User user, @RequestParam("image") MultipartFile file) throws IOException {
        File dir = new File (imageUploadPath);
        if (!dir.exists ()) {
            dir.mkdir ();
        }
        String picName = System.currentTimeMillis () + "_" + file.getOriginalFilename ();
        File picture = new File ("D:\\picLibrary\\" + picName);
        file.transferTo (picture);
        user.setPicUrl (picName);
        userRepository.save (user);
        return "redirect:/home";

    }

    @RequestMapping(value = "/user/image", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {
        InputStream in = new FileInputStream ("D:\\picLibrary\\" + fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

}
