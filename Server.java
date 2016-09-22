import java.util.ArrayList;
public class Server {
	private ArrayList<ArrayList<User>> server = new ArrayList<ArrayList<User>>(3);
	public Server() {
		for(int i = 0; i < server.size(); i++) {
			server.add(new ArrayList<User>(10));
		}
	}
	public void getList() {
		
	}
}