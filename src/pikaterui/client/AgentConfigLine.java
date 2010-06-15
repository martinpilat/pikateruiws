package pikaterui.client;

import java.util.ArrayList;

import pikaterui.shared.Option;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

public class AgentConfigLine extends Composite {
	
	HorizontalPanel hp = new HorizontalPanel();
	ListBox agentSelect = new ListBox();
	Label agentName = new Label();
	TextBox agentParamsEdit = new TextBox();
	Label agentParams = new Label();
	Button edit = new Button("save");
	Button delete = new Button("delete");
	
	private final GreetingServiceAsync greetingService = GWT
	.create(GreetingService.class);
	
	final static int AGENT_NAME_INDEX = 0;
	final static int AGENT_PARAMS_INDEX = 1;
	
	public AgentConfigLine(ArrayList<String> agentsList) {
		this();
		
		for (String s: agentsList) {
			agentSelect.addItem(s);
		}
	}
	
	class AgentParamsHandler implements ClickHandler {

		AgentConfigLine myLine;
		
		public AgentParamsHandler(AgentConfigLine acl) {
			myLine = acl;
		}
		
		public void onClick(ClickEvent event) {
			
			String aName = myLine.agentSelect.getItemText(myLine.agentSelect.getSelectedIndex());
			
			greetingService.getAgentOptions(aName, new AsyncCallback<Option[]>() {
				
				@Override
				public void onSuccess(Option[] result) {
					String title = "";
					for(Option o : result) {
						title += o.getSynopsis() + "--" +o.getDescription() + "\n";
					}
					myLine.agentParamsEdit.setTitle(title);
				}
				
				@Override
				public void onFailure(Throwable caught) {
				}
			});
			
			

			
		}
		
		
	}
	
	public AgentConfigLine() {
		agentSelect.setWidth("100px");
		agentName.setWidth("100px");
		agentParamsEdit.setWidth("200px");
		agentParams.setWidth("200px");
		
		hp.add(agentSelect);
		hp.add(agentParamsEdit);
		hp.add(edit);
		hp.add(delete);
		
		edit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Button source = (Button)event.getSource();
				
				if (source.getText().equals("edit")) {
					hp.remove(agentName);
					hp.insert(agentSelect, AGENT_NAME_INDEX);
					hp.remove(agentParams);
					hp.insert(agentParamsEdit, AGENT_PARAMS_INDEX);
					source.setText("save");
				}
				else if (source.getText().equals("save")) {
					agentName.setText(agentSelect.getItemText(agentSelect.getSelectedIndex()));
					agentParams.setText(agentParamsEdit.getText());
					hp.remove(AGENT_NAME_INDEX);
					hp.insert(agentName, AGENT_NAME_INDEX);
					hp.remove(AGENT_PARAMS_INDEX);
					hp.insert(agentParams, AGENT_PARAMS_INDEX);
					source.setText("edit");
				}
				RootLayoutPanel.get().forceLayout();
			}
		});	
		
		delete.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				AgentConfigLine.this.removeFromParent();
			}
		});
		
		agentParamsEdit.addClickHandler(new AgentParamsHandler(this));
		
		initWidget(hp);
	}
	
	public String getConfigLine() {
		return agentName.getText() + " " + agentParams.getText();
	}

}
