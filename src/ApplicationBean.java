import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.Statement;

@ManagedBean
@ViewScoped
public class ApplicationBean {

	public ApplicationBean(){
		
	}
	
	private List<Topic> topics = new ArrayList<Topic>();
	
	public List<Topic> getTopics() {
		topics = DatabaseHelper.getTopics();
		return topics;
	}

	/*public void setTopics(List<Topic> topics) {		
		this.topics = topics;
	}*/
	
	public String seeDetails(int topicID){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("topicID", topicID);
		return "/viewTopic.xhtml?faces-redirect=true";
	}
}
