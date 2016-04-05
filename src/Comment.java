import java.util.List;

public class Comment {
	
	public Comment(){
		
	}

	private int commentID;
	
	private String commentText;
	
	private List<Comment> replies;
	
	public Comment(String text){
		commentText = text;
	}
	
	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public List<Comment> getReplies() {
		return replies;
	}

	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}

	
}
