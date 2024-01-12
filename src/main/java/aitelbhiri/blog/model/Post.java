package aitelbhiri.blog.model;



import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "post")
public class Post {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;
   
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, name="create_date", updatable = false)
    private Date createDate;
	
	@Column(name = "image_url")
	private String imageUrl;
    
    @ManyToOne(fetch = FetchType.EAGER) // Ensure eager loading
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)	
	@JsonManagedReference
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "categorie_id")
	private Categorie categorie;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getImageUrl() {
	    return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
	    this.imageUrl = imageUrl;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}



	public Post(int id, String title, String body, Date createDate, String imageUrl ,Utilisateur utilisateur,
			List<Comment> comments, Categorie categorie) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.createDate = createDate;
	    this.imageUrl = imageUrl;
		this.utilisateur = utilisateur;
		this.comments = comments;
		this.categorie = categorie;

	}

	public Post() {
		
	}
	
	

}