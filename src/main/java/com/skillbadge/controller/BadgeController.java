package com.skillbadge.controller;

import com.skillbadge.service.BadgeService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    private final BadgeService badgeService;

    //@Autowired
    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    // ✅ Assign Coding Badges
    @PostMapping("/assign/coding")
    public String assignCodingBadges() {
        badgeService.assignCodingBadges();
        return "Coding badges assigned.";
    }

    // ✅ Assign Sports Badges
    @PostMapping("/assign/sports")
    public String assignSportBadges() {
        badgeService.assignSportBadges();
        return "Sports badges assigned.";
    }

    // ✅ Assign Academic Badges (logic to be added)
    @PostMapping("/assign/academic")
    public String assignAcademicBadges() {
        badgeService.assignAcademicBadges();
        return "Academic badges assigned.";
    }

    @PostMapping("/assign/editing")
    public String assignEditingBadges() {
        badgeService.assignEditingBadges();
        return "Editing badges assigned.";
    }

    @PostMapping("/assign/speaking")
    public String assignSpeakingBadges() {
        badgeService.assignSpeakingBadges();
        return "Speaking badges assigned.";
    }

    @PostMapping("/assign/teaching")
    public String assignTeachingBadges() {
        badgeService.assignTeachingBadges();
        return "Teaching badges assigned.";
    }
}
