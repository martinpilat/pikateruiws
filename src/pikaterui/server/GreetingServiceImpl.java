package pikaterui.server;

import java.rmi.RemoteException;

import pikaterui.client.GreetingService;
import pikaterui.shared.FieldVerifier;

import SetProblem.SetProblemPortProxy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String[] agents, String[] files) throws IllegalArgumentException {
				
		SetProblemPortProxy sp = new SetProblemPortProxy();
		
		try {
			sp.setProblem(agents, files);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		return "Hello, !<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
}
