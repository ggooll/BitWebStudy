import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kr.co.bit.bonddebt.dao.ChatDAO;
import kr.co.bit.bonddebt.vo.Chat;

@ServerEndpoint("/broadcasting")
public class BroadcastSocket {
	private static JSONParser parser = new JSONParser();
	private static JSONObject json;
	private static Map<Integer, HashMap<Integer, Session>> group = new HashMap<Integer, HashMap<Integer, Session>>();
	private static HashMap<Integer, Session> currentGroup;

	//private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public void onMessage(String data, Session session) throws IOException, ParseException {
		System.out.println(data);
		
		json = (JSONObject) parser.parse(data);
		int type = Integer.parseInt((String) json.get("type"));
		int groupNo = Integer.parseInt((String) json.get("groupNo"));
		int memberNo = Integer.parseInt((String) json.get("memberNo"));
		String memberName = (String) json.get("memberName");
		String message = (String) json.get("message");
		Chat chatVO = new Chat(groupNo, memberNo, memberName, message);
		
		if (type == 0) {
			if (!group.containsKey(groupNo)) {
				currentGroup = new HashMap<Integer, Session>();
				currentGroup.put(memberNo, session);
				group.put(groupNo, currentGroup);
			} else {
				currentGroup = group.get(groupNo);
				if (!currentGroup.containsKey(memberNo)) {
					currentGroup.put(memberNo, session);
				}
			}
			return;
		}

		currentGroup = group.get(groupNo);
		
		if (type == 1) {
			// store message to db - ok
			// get group collection - ok
			// send message - ok;
			System.out.println(data);
			(new ChatDAO()).insertChatLog(chatVO);
			StringBuffer msg = new StringBuffer();
			msg.append("{ \"name\" : \"");
			msg.append(memberName);
			msg.append("\", \"message\" : \"");
			msg.append(message);
			msg.append("\"}");
			Session temp;
			synchronized (currentGroup) {
				Set<Integer> iter = currentGroup.keySet();
				for (int i : iter) {
					temp = currentGroup.get(i);
					System.out.println(temp);
					if (!temp.equals(session)) {
						System.out.println(temp+" "+msg.toString());
						temp.getBasicRemote().sendText(msg.toString());
					}
				}
			}
			return;
		}
		
		if(type == 2) {
			System.out.println("type = 2 message receive ok");
			Session tmp = currentGroup.remove(memberNo);
			System.out.println(tmp);
			return;
		}
		
	}

	@OnOpen
	public void onOpen(Session session) {
		// Add session to the connected sessions set
		System.out.println(session);
		// clients.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		// Remove session from the connected sessions set
		//clients.remove(session);
	}
}