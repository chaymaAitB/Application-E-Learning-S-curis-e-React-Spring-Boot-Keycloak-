package com.example.elearning_app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    private final List<Map<String, Object>> courses = new ArrayList<>();

    public CourseController() {
        // Cours de test
        courses.add(Map.of(
                "id", 1,
                "title", "Introduction au Réseau",
                "author", "Prof A"
        ));
        courses.add(Map.of(
                "id", 2,
                "title", "Sécurité des Systèmes",
                "author", "Prof B"
        ));
    }

    // GET /courses - accessible à tous les utilisateurs connectés
    @GetMapping("/courses")
    public List<Map<String, Object>> getCourses() {
        return courses;
    }

    @PostMapping("/admin/courses")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> addCourse(@RequestBody Map<String, Object> course) {

        // Générer un ID simple
        course.put("id", courses.size() + 1);

        courses.add(course);

        return course;
    }


    // GET /me - renvoie les infos utilisateur
    @GetMapping("/me")
    public Map<String, Object> getMe(Authentication authentication) {
        Map<String, Object> result = new HashMap<>();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();

            result.put("username", jwt.getClaimAsString("preferred_username"));
            result.put("email", jwt.getClaimAsString("email"));

            // Rôles
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            List<String> roles = new ArrayList<>();
            if (realmAccess != null && realmAccess.containsKey("roles")) {
                roles = (List<String>) realmAccess.get("roles");
            }
            roles.removeIf(role ->
                    role.equals("offline_access") ||
                            role.equals("uma_authorization") ||
                            role.startsWith("default-roles")
            );
            result.put("roles", roles);
        } else {
            result.put("error", "Non authentifié");
        }

        return result;
    }

    // Endpoint public pour test
    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello World (public)";
    }

    // Endpoint admin test
    @GetMapping("/admin/test")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminTest() {
        return "Admin access OK";
    }

    // Endpoint student test
    @GetMapping("/student/test")
    @PreAuthorize("hasRole('STUDENT')")
    public String studentTest() {
        return "Student access OK";
    }
}