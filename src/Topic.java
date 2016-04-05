import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class Topic extends BaseTopic{

	public Topic(){
		
		/*super.title = "Random title";
		super.description = "Some text here";
		comments.add(new Comment("Comment 1"));
		comments.add(new Comment("Comment 2"));*/
	}
	
	public Topic(int id, String title, String description){
		super.id = id;
		super.title = title;
		super.description = description;
	}
	
	private Comment newComment = new Comment();
	
	public Comment getNewComment() {
		return newComment;
	}

	public void setNewComment(Comment newComment) {
		this.newComment = newComment;
	}

	private List<Comment> comments = new ArrayList<Comment>();
	
	public List<Comment> getComments() {
		comments = DatabaseHelper.getTopicComments(this.id);
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@PostConstruct
	public void init(){
		int id = (int) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("topicID");
		//get info from database for this id and populate the topic attributes
		
		//the following lines of code are for test; they should be deleted
		setId(id);
		DatabaseHelper.updateTopic(this);
	}
	
	public void saveNewComment(){
		//TODO: save new comment in database and update comments list
		if(!this.newComment.getCommentText().trim().equals(""))
			DatabaseHelper.storeNewComment(this);
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("topicID", this.id);

		//this line of code is for test; it should be deleted 
		/*Comment c = new Comment();
		c.setCommentText(newComment.getCommentText());
		comments.add(newComment);*/
		newComment = new Comment();
	}
	
}
