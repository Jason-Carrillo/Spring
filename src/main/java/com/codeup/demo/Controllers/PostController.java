package com.codeup.demo.Controllers;

import com.codeup.demo.Services.EmailService;
import com.codeup.demo.models.Post;
import com.codeup.demo.models.User;
import com.codeup.demo.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.codeup.demo.repos.UserRepository;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String index(Model model){
        model.addAttribute("posts",postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable(name= "id") long id,  Model model ) {

        model.addAttribute("post", postDao.getOne(id));

        return "posts/show";
    }



    @GetMapping("/posts/create")
    public String viewCreate(Model viewModel) {
        viewModel.addAttribute("post", new Post());
        return "posts/new";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post postToSave) {
        User user = userDao.getOne(1L);
        postToSave.setOwner(user);
        Post postDB = postDao.save(postToSave);
        emailService.prepareAndSend(postDB, "post created", "this is the body");
        return "redirect:/posts/";
    }


    @GetMapping("posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post postToEdit){
        User user = userDao.getOne(1L);
        postToEdit.setOwner(user);
        postDao.save(postToEdit);
        return "redirect:/posts/{id}";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/show")
    @ResponseBody
    public String singlePosts() {
        return "single posts";
    }

}
