
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatabaseHelper {
	private static Connection connection;
	
	private static Object lockObject = new Object();
	
	private static void initConnection(){
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("appuser");
		dataSource.setPassword("albastru");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("forumdatabase");
		
		try {
			connection = (Connection) dataSource.getConnection();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Topic> getTopics(){
		synchronized (lockObject) {		
			List<Topic> topics = new ArrayList<>();
			initConnection();
			try {
				Statement stmt = (Statement) connection.createStatement();
				ResultSet rs;
							
				rs = stmt.executeQuery("SELECT * FROM forumdatabase.topics");
				
				while (rs.next()) {
					Topic newTopic = new Topic();
					newTopic.setId(rs.getInt("TOPIC_ID"));
		            newTopic.setTitle(rs.getString("TOPIC_TITLE"));
		            newTopic.setDescription(rs.getString("TOPIC_DESCRIPTION"));
		            topics.add(newTopic);
		        }
				
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				closeConnection();
			}
			
			return topics;
		}
	}
	
	public static List<Comment> getTopicComments(int topicID){
		synchronized (lockObject) {		
			List<Comment> comments = new ArrayList<>();
			initConnection();
			try {
				Statement stmt = (Statement) connection.createStatement();
				ResultSet rs;
							
				rs = stmt.executeQuery("SELECT * FROM forumdatabase.comments WHERE TOPIC_ID = " + topicID);
				
				while (rs.next()) {
					Comment comment = new Comment();
					comment.setCommentID(rs.getInt("COMMENT_ID"));
		            comment.setCommentText(rs.getString("COMMENT_TEXT"));
		            comments.add(comment);
		        }
				
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				closeConnection();
			}
			
			return comments;
		}
	}

	public static void updateTopic(Topic topic){
		synchronized (lockObject) {		
			initConnection();
			try {
				Statement stmt = (Statement) connection.createStatement();
				ResultSet rs;
							
				rs = stmt.executeQuery("SELECT * FROM forumdatabase.topics WHERE TOPIC_ID = " + topic.getId());
								
				while (rs.next()) {					
					topic.setTitle(rs.getString("TOPIC_TITLE"));
		            topic.setDescription(rs.getString("TOPIC_DESCRIPTION"));
		        }
				
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				closeConnection();
			}
		}
	}

	public static void storeNewComment(Topic topic){
		synchronized (lockObject) {		
			initConnection();
			try {
				Statement stmt = (Statement) connection.createStatement();
				ResultSet rs;
							
				stmt.executeUpdate(
						String.format("INSERT INTO forumdatabase.comments(COMMENT_TEXT, TOPIC_ID) VALUES('%s','%d');",
								topic.getNewComment().getCommentText(),topic.getId()));
								
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				closeConnection();
			}
		}
	}

	public static void storeNewTopic(BaseTopic topic){
		synchronized (lockObject) {		
			initConnection();
			try {
				Statement stmt = (Statement) connection.createStatement();
				ResultSet rs;
							
				stmt.executeUpdate(
						String.format("INSERT INTO topics(TOPIC_TITLE, TOPIC_DESCRIPTION) VALUES('%s','%s');",
							topic.title, topic.description));
								
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				closeConnection();
			}
		}

	}
}
