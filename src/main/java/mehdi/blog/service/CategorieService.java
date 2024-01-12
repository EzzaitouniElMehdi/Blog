package mehdi.blog.service;


import java.util.List;

import org.springframework.stereotype.Service;

import mehdi.blog.model.Categorie;
import mehdi.blog.repository.CategorieRepository;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(int id) {
        return categorieRepository.findById(id).orElse(null);
    }

    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie updateCategorie(int id, Categorie updatedCategorie) {
    	Categorie existingCategorie = getCategorieById(id);

        // Mise à jour des attributs de la réclamation existante avec les attributs de la réclamation mise à jour
        existingCategorie.setTitle(updatedCategorie.getTitle());
        existingCategorie.setPosts(updatedCategorie.getPosts());
        // Vous pouvez mettre à jour d'autres attributs selon vos besoins

        return categorieRepository.save(existingCategorie);
    }

    public void deleteCategorie(int id) {
    	Categorie existingCategorie = getCategorieById(id);
    	categorieRepository.delete(existingCategorie);
    }


}
