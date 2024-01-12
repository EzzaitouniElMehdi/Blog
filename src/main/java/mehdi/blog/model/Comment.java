package mehdi.blog.model;
import jakarta.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

	@Temporal(TemporalType.DATE)
	@Column(nullable=true, name="create_date", updatable = false)
    private Date createDate;

	@ManyToOne
    @JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;
    
	@ManyToOne
    @JoinColumn(name = "post_id")
	@JsonBackReference
	private Post post;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment(int id, String body, Date createDate, Utilisateur utilisateur, Post post) {
		super();
		this.id = id;
		this.body = body;
		this.createDate = createDate;
		this.utilisateur = utilisateur;
		this.post = post;
	}
	
	public Comment() {
		
	}


}