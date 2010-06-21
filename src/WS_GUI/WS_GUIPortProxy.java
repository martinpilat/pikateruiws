package WS_GUI;

public class WS_GUIPortProxy implements WS_GUI.WS_GUIPort {
  private String _endpoint = null;
  private WS_GUI.WS_GUIPort wS_GUIPort = null;
  
  public WS_GUIPortProxy() {
    _initWS_GUIPortProxy();
  }
  
  public WS_GUIPortProxy(String endpoint) {
    _endpoint = endpoint;
    _initWS_GUIPortProxy();
  }
  
  private void _initWS_GUIPortProxy() {
    try {
      wS_GUIPort = (new WS_GUI.WS_GUIServiceLocator()).getWS_GUIPort();
      if (wS_GUIPort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wS_GUIPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wS_GUIPort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wS_GUIPort != null)
      ((javax.xml.rpc.Stub)wS_GUIPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public WS_GUI.WS_GUIPort getWS_GUIPort() {
    if (wS_GUIPort == null)
      _initWS_GUIPortProxy();
    return wS_GUIPort;
  }
  
  public java.lang.String setProblem(java.lang.String[] agentDescriptions, java.lang.String[] fileNames) throws java.rmi.RemoteException{
    if (wS_GUIPort == null)
      _initWS_GUIPortProxy();
    return wS_GUIPort.setProblem(agentDescriptions, fileNames);
  }
  
  public WS_GUI.Option[] getOptions(java.lang.String agentName) throws java.rmi.RemoteException{
    if (wS_GUIPort == null)
      _initWS_GUIPortProxy();
    return wS_GUIPort.getOptions(agentName);
  }
  
  public java.lang.String[] getAgents() throws java.rmi.RemoteException{
    if (wS_GUIPort == null)
      _initWS_GUIPortProxy();
    return wS_GUIPort.getAgents();
  }
  
  public WS_GUI.Results[] getResults() throws java.rmi.RemoteException{
    if (wS_GUIPort == null)
      _initWS_GUIPortProxy();
    return wS_GUIPort.getResults();
  }
  
  
}