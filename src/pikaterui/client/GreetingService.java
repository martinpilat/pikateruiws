package pikaterui.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pikaterui.shared.Option;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String[] files, String[] agents) throws IllegalArgumentException;
	String[] getAgents() throws IllegalArgumentException;
	Option[] getAgentOptions(String agent) throws IllegalArgumentException;
}
