package aitelbhiri.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aitelbhiri.blog.model.Categorie;
import aitelbhiri.blog.service.CategorieService;

@CrossOrigin("*")
@RestController
@RequestMapping("/categories")
public class CategorieController {

    private final CategorieService categorieService;
    private static final Logger logger = LoggerFactory.getLogger(CategorieController.class);

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
    public List<Categorie> getAllCategories() {
        List<Categorie> categories = categorieService.getAllCategories();
        logger.info("Retrieved categories: {}", categories);
        return categories;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
    public Categorie getCategorieById(@PathVariable int id) {
        return categorieService.getCategorieById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
    public Categorie addCategorie(@RequestBody Categorie categorie) {
        return categorieService.addCategorie(categorie);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
    public Categorie updateCategorie(@PathVariable int id, @RequestBody Categorie categorie) {
        return categorieService.updateCategorie(id, categorie);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
    public void deleteCategorie(@PathVariable int id) {
        categorieService.deleteCategorie(id);
        logger.info("Deleted categorie with id: {}", id);
    }
}
