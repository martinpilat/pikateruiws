package pikaterui.client;

import java.util.ArrayList;

import pikaterui.shared.Option;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DisclosureEvent;
import com.google.gwt.user.client.ui.DisclosureHandler;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AgentConfigLine extends Composite {
	
	HorizontalPanel hp = new HorizontalPanel();
	ListBox agentSelect = new ListBox();
	Label agentName = new Label();
	TextBox agentParamsEdit = new TextBox();
	Label agentParams = new Label();
	VerticalPanel agentOptions = new VerticalPanel();
	HTMLPanel optionsHelp = new HTMLPanel("");
	Button edit = new Button("save");
	Button delete = new Button("delete");
	Button details = new Button("details");
	
	private final GreetingServiceAsync greetingService = GWT
	.create(GreetingService.class);
	
	final static int AGENT_NAME_INDEX = 3;
	final static int AGENT_PARAMS_INDEX = 4;
	
	public AgentConfigLine(ArrayList<String> agentsList) {
		this();
		
		for (String s: agentsList) {
			agentSelect.addItem(s);
		}
	}
		
	public AgentConfigLine() {
		agentSelect.setWidth("100px");
		agentName.setWidth("100px");
		edit.setWidth("60px");
		delete.setWidth("60px");
		agentParamsEdit.setWidth("100%");
		
		hp.setBorderWidth(0);
		hp.setSpacing(6);
		hp.add(details);
		hp.add(edit);
		hp.add(delete);
		hp.add(agentSelect);
		hp.add(agentParamsEdit);
		
		agentOptions.add(hp);
		
		details.addClickHandler(new ClickHandler() {
			
			boolean open = false;
			@Override
			public void onClick(ClickEvent event) {
				if (open) {
					open = false;
					agentOptions.remove(1);
				}
				else {
					open = true;
					
					String aName = AgentConfigLine.this.agentSelect.getItemText(AgentConfigLine.this.agentSelect.getSelectedIndex());
					
					greetingService.getAgentOptions(aName, new AsyncCallback<Option[]>() {
						
						@Override
						public void onSuccess(Option[] result) {
							String HTML = "";
							
							for(Option o : result) {
								HTML += "<code>" + o.getSynopsis() + "</code> " + o.getDescription().trim() + "<br>";
							}
							optionsHelp = new HTMLPanel(HTML);
							agentOptions.add(optionsHelp);
							RootLayoutPanel.get().forceLayout();
						}
						
						@Override
						public void onFailure(Throwable caught) {
						}
					});
					
				}
			}
		});
		
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
					hp.remove(agentParamsEdit);
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
		
		initWidget(agentOptions);
	}
	
	public String getConfigLine() {
		return agentName.getText() + " " + agentParams.getText();
	}

}
