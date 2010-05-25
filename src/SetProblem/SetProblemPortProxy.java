package SetProblem;

public class SetProblemPortProxy implements SetProblem.SetProblemPort {
  private String _endpoint = null;
  private SetProblem.SetProblemPort setProblemPort = null;
  
  public SetProblemPortProxy() {
    _initSetProblemPortProxy();
  }
  
  public SetProblemPortProxy(String endpoint) {
    _endpoint = endpoint;
    _initSetProblemPortProxy();
  }
  
  private void _initSetProblemPortProxy() {
    try {
      setProblemPort = (new SetProblem.SetProblemServiceLocator()).getSetProblemPort();
      if (setProblemPort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)setProblemPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)setProblemPort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (setProblemPort != null)
      ((javax.xml.rpc.Stub)setProblemPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public SetProblem.SetProblemPort getSetProblemPort() {
    if (setProblemPort == null)
      _initSetProblemPortProxy();
    return setProblemPort;
  }
  
  public void setProblem(java.lang.String[] agents, java.lang.String[] file_names) throws java.rmi.RemoteException{
    if (setProblemPort == null)
      _initSetProblemPortProxy();
    setProblemPort.setProblem(agents, file_names);
  }
  
  
}