package com.codeup.demo.Controllers;

import com.codeup.demo.Post;
import com.codeup.demo.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.codeup.demo.repos.PostRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        List<Post> posts = new ArrayList<>(Arrays.asList(
                new Post("test1", "body1"),
                new Post("test2", "body2")
        ));

        model.addAttribute("posts", posts);
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable long id, Model model) {

        model.addAttribute("post", postDao.getOne(id));

        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreate() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String create() {
        return "create a new post";
    }

    @GetMapping("posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    @ResponseBody
    public String createPost(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String desc
    ){
        Post post = new Post(title, desc);
        Post dbPost = postDao.save(post);
        return "redirect:/post/ " + dbPost.getId();
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
