import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BaseTopic {
	public BaseTopic(){
		
	}
	
	protected int id;
	
	protected String title;

	protected String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String saveTopic(){
		DatabaseHelper.storeNewTopic(this);
		return "/index.xhtml?faces-redirect=true";
	}
	
}
