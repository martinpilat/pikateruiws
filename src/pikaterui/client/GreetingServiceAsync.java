package pikaterui.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String[] files, String[] agents,
			AsyncCallback<String> callback);
	
	void getAgents(AsyncCallback<String[]> callback);
}
