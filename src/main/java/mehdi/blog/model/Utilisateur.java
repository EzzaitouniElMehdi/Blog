package mehdi.blog.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "utilisateur")
public class Utilisateur {

	/* Début de la déclaration des attributs */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(name = "username", nullable = false, unique = true, length= 100)
    private String username;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false , length= 100)
    private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_utilisateur"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Comment> comments;
    

	/* Fin de la declaration des attributs */

	/* Debut des getters et setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	/* Fin des getters et setters */

	/* Debut du constructeur */

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

	public Utilisateur(int id, String username, String nom, String prenom, String email, 
			String password, List<Post> posts, List<Comment> comments) {
		super();
		this.id = id;
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.posts = posts;
		this.comments = comments;
	}

	public Utilisateur(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}




	public Utilisateur() {
		// Empty constructor (required by JPA)
	}
	/* Fin du constructeur */

	public Utilisateur(int id, String username, String nom, String prenom, String email, String password,
	        Comment comment, Post post) {
	    this.id = id;
	    this.username = username;
	    this.nom = nom;
	    this.prenom = prenom;
	    this.email = email;
	    this.password = password;
	    this.comments = new ArrayList<>(); // Initialize the comments list
	    this.posts = new ArrayList<>();    // Initialize the posts list
	    this.comments.add(comment);         // Add the provided comment to the list
	    this.posts.add(post);               // Add the provided post to the list
	}



}
