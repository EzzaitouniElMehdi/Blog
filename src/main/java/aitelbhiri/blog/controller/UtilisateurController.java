package aitelbhiri.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import aitelbhiri.blog.service.UtilisateurService;
import aitelbhiri.blog.model.Utilisateur;

@CrossOrigin("*")
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

	private final UtilisateurService utilisateurService;
    private static final Logger logger = LoggerFactory.getLogger(CategorieController.class);

	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	@GetMapping
	public List<Utilisateur> getAllUtilisateurs() {
		return utilisateurService.getAllUtilisateurs();
	}

	@GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
	public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int id) {
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
		if (utilisateur != null) {
			return ResponseEntity.ok(utilisateur);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
	public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
		Utilisateur newUtilisateur = utilisateurService.addUtilisateur(utilisateur);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUtilisateur);
	}

	@PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
	public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
		logger.info("Received ID: " + id);
		logger.info("Received Utilisateur: " + utilisateur);

		Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur);

		if (updatedUtilisateur != null) {
			return ResponseEntity.ok(updatedUtilisateur);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
	public ResponseEntity<Void> deleteUtilisateur(@PathVariable int id) {
		utilisateurService.deleteUtilisateur(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/count")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUTHOR')")
	public long countUtilisateurs() {
		return utilisateurService.countUtilisateurs();
	}

}
