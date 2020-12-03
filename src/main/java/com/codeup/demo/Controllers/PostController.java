package com.codeup.demo.Controllers;

import com.codeup.demo.models.Post;
import com.codeup.demo.models.User;
import com.codeup.demo.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.codeup.demo.repos.UserRepository;

import java.util.Map;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
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
    public String viewCreate() {
        return "posts/new";
    }

    @PostMapping("/posts/create")
    public String create(@RequestParam (name = "title") String title, @RequestParam (name = "description") String description) {
        User user = userDao.getOne(1L);
        Post post = new Post(title, description, user);
        postDao.save(post);
        return "redirect:/posts/"+ post.getId();
    }


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
