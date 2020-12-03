package com.codeup.demo.Controllers;

import com.codeup.demo.models.Post;
import com.codeup.demo.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.codeup.demo.repos.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model model){
        model.addAttribute("posts",postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable long id, Model model) {

        model.addAttribute("post", postDao.getOne(id));

        return "posts/show";
    }


    @GetMapping("/posts/create")
    public String viewCreate() {
        return "posts/new";
    }

//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String create(@RequestParam Map<String, String> reqParam) {
//        Post post = new Post(reqParam.get("title"), reqParam.get("description") );
//        postDao.save(post);
//        return "redirect:/posts/"+post.getId();
//    }

    @GetMapping("posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    @ResponseBody
    public String createPost(
            @PathVariable long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String desc
    ){
        Post post = postDao.getOne(id);
        post.setTitle(title);
        post.setBody(desc);
        postDao.save(post);
        return "redirect:/post/ " + post.getId();
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("")
    @ResponseBody
    public String allPosts() {
        return "all posts";
    }

    @GetMapping("/posts/show")
    @ResponseBody
    public String singlePosts() {
        return "single posts";
    }

}
