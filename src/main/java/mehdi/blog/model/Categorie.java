package mehdi.blog.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie;  // Modified field name

    private String title;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Categorie(int idCategorie, String title, List<Post> posts) {
        super();
        this.idCategorie = idCategorie;
        this.title = title;
        this.posts = posts;
    }

    public Categorie() {

    }
}
