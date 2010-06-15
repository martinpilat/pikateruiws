package pikaterui.server;

import java.rmi.RemoteException;


import pikaterui.client.GreetingService;
import pikaterui.shared.Option;
import WS_GUI.WS_GUIPortProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String[] agents, String[] files) throws IllegalArgumentException {
				
		WS_GUIPortProxy sp = new WS_GUIPortProxy();
		
		try {
			sp.setProblem(agents, files);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		String ret = "";
		
		for (String s: agents) {
			ret += s + "|";
		}
		
		ret += "<br>";
		
		for (String s: files) {
			ret += s + "|";
		}
		
		
		return "Hello, !<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent + "<br>" + ret;
	}

	@Override
	public String[] getAgents() throws IllegalArgumentException{
		
		WS_GUIPortProxy sp = new WS_GUIPortProxy();
		
		try {
			
			return sp.getAgents();
		}
		catch (RemoteException e) {
			String err = new String();
			
			for (StackTraceElement s : e.getStackTrace()) {
				err += s.toString() + " <br>";
			}
			
			
			throw new IllegalArgumentException(err);
			
			
			
		}
		
	}

	@Override
	public Option[] getAgentOptions(String agent)
			throws IllegalArgumentException {
		WS_GUIPortProxy sp = new WS_GUIPortProxy();
		
		try {
			WS_GUI.Option[] options = sp.getOptions(agent);
			Option[] o = new Option[options.length]; 
			
			for (int i = 0; i < o.length; i++) {
				o[i] = new Option(options[i].getName(), options[i].getDescription(), options[i].getSynopsis(), options[i].getValue());
			}
			return o; 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
