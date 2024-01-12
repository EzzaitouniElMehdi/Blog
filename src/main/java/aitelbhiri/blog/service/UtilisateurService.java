package aitelbhiri.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import aitelbhiri.blog.model.Utilisateur;
import aitelbhiri.blog.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	private final UtilisateurRepository utilisateurRepository;

	public UtilisateurService(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

	// Méthode pour ajouter un nouvel utilisateur
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}

	// Méthode pour récupérer un utilisateur par son ID
	public Utilisateur getUtilisateurById(int id) {
		return utilisateurRepository.findById(id).orElse(null);
	}

	// Méthode pour récupérer tous les utilisateur
	public List<Utilisateur> getAllUtilisateurs() {
		return utilisateurRepository.findAll();
	}

	// Méthode pour mettre à jour les informations d'un utilisateur
	public Utilisateur updateUtilisateur(int id, Utilisateur updatedUtilisateur) {
		Utilisateur existingUtilisateur = utilisateurRepository.findById(id).orElse(null);
		if (existingUtilisateur != null) {
			// Mettez à jour les attributs de existingutilisateur avec les attributs de
			// updatedutilisateur
			existingUtilisateur.setUsername(updatedUtilisateur.getUsername());
			existingUtilisateur.setNom(updatedUtilisateur.getNom());
			existingUtilisateur.setPrenom(updatedUtilisateur.getPrenom());
			existingUtilisateur.setEmail(updatedUtilisateur.getEmail());
			existingUtilisateur.setPassword(updatedUtilisateur.getPassword());
			existingUtilisateur.setRoles(updatedUtilisateur.getRoles());

			return utilisateurRepository.save(existingUtilisateur);
		}
		return null;
	}

	// Méthode pour supprimer un utilisateur par son ID
	public void deleteUtilisateur(int id) {
		utilisateurRepository.deleteById(id);
	}

	public long countUtilisateurs() {
		return utilisateurRepository.count();
	}

}
