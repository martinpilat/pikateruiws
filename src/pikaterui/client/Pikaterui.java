package pikaterui.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Pikaterui implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final Button agentsAddRow = new Button("Add agent");
		final Button filesAddRow = new Button("Add file");
		final ArrayList<String> agents = new ArrayList<String>();
		
		final Label errorLabel = new Label();
		
		final DockLayoutPanel agentsTable = new DockLayoutPanel(Unit.PX);
		final FlexTable filesTable = new FlexTable(); 
		
		filesTable.setText(0, 0, "Filename");
		filesTable.setWidget(1, 0, new TextBox());
		filesTable.setWidget(2, 0, filesAddRow);

		greetingService.getAgents(new AsyncCallback<String[]>() {

			@Override
			public void onFailure(Throwable caught) {
				errorLabel.setText(caught.getMessage());
				agentsAddRow.setText("error");
				agentsTable.add(agentsAddRow);
			}

			@Override
			public void onSuccess(String[] result) {
				for (String s : result) {
					agents.add(s);
				}
				agentsTable.addNorth(new AgentConfigLine(agents),20);
				agentsTable.addNorth(agentsAddRow,20);
			}
		});

		//final TextBox nameField = new TextBox();
		//nameField.setText("GWT User");
		
				
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		
		//RootPanel.get("agentsTableContainer").add(agentsTable);
		//RootPanel.get("filesTableContainer").add(filesTable);
		//RootPanel.get("sendButtonContainer").add(sendButton);

		sendButton.setWidth("100px");
		sendButton.setHeight("50px");
		
		StackLayoutPanel sp = new StackLayoutPanel(Unit.PX);
		sp.add(agentsTable, "Agents", 30.0);
		sp.add(filesTable, "Files", 30.0);
		
		ArrayList<String> aaa = new ArrayList<String>();
		aaa.add("agent1");
		aaa.add("agent2");
		
		DockLayoutPanel dp = new DockLayoutPanel(Unit.PX);
		dp.addNorth(new HTML("<h1>Web Services Pikater UI</h1>"), 100);		
		dp.addEast(new Label(), 100);
		dp.addWest(new Label(), 100);
		dp.addSouth(sendButton, 50);
		dp.add(sp);
		
		RootLayoutPanel rp = RootLayoutPanel.get();
		rp.add(dp);
		// Focus the cursor on the name field when the app loads
		//nameField.setFocus(true);
		//nameField.selectAll();
				
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				//if (!FieldVerifier.isValidName(textToServer)) {
				//	errorLabel.setText("Please enter at least four characters");
				//	return;
				//}

				// Then, we send the input to the server.
				
				String[] agents = new String[agentsTable.getWidgetCount() - 2];
				String[] files = new String[filesTable.getRowCount() - 2];
				
				for (int i = 1; i < filesTable.getRowCount() - 1; i++) {
					files[i-1] = ((TextBox)filesTable.getWidget(i, 0)).getText();
				}

				for (int i = 0; i < agentsTable.getWidgetCount() - 1; i++) {
					agents[i-1] = ((AgentConfigLine)agentsTable.getWidget(i)).getConfigLine();
				}
												
				sendButton.setEnabled(false);
				serverResponseLabel.setText("");
				greetingService.greetServer(agents, files,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		agentsAddRow.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ListBox lb = new ListBox();
				
				for (String s : agents) {
					lb.addItem(s);
				}

				agentsTable.insertNorth(new AgentConfigLine(agents), 20, agentsAddRow);
			}
		});
		
		filesAddRow.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				filesTable.insertRow(filesTable.getRowCount() - 1);
				filesTable.setWidget(filesTable.getRowCount() - 2, 0, new TextBox());
			}
		});
		
		//nameField.addKeyUpHandler(handler);
	}
}
