package com.example.SocialNetwork.api.mvc;


import com.example.SocialNetwork.api.PageHelper;
import com.example.SocialNetwork.api.PageRs;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/users")
public class UserMVCController {

    @Autowired
    private UserService userService;

    Long userId = 1L;

    @GetMapping
    public String getAllUsers(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            Model model) {

        Pageable pageable = PageHelper.toPageable(page, size);

        PageRs<UserRs> pageRs = userService.getAll(pageable);

        model.addAttribute("users", pageRs.items());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalItems", pageRs.totalItems());
        model.addAttribute("totalPages", pageRs.totalPages());
        model.addAttribute("hasNext", pageRs.hasNext());
        model.addAttribute("hasPrevious", pageRs.hasPrevious());
        model.addAttribute("currentUserId", userId);

        model.addAttribute("nextPage", page + 1);
        model.addAttribute("isLastPage", page >= pageRs.totalPages());
        return "users";
    }
}
