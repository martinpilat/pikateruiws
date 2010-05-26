/**
 * WS_GUIServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WS_GUI;

public class WS_GUIServiceLocator extends org.apache.axis.client.Service implements WS_GUI.WS_GUIService {

    public WS_GUIServiceLocator() {
    }


    public WS_GUIServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WS_GUIServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WS_GUIPort
    private java.lang.String WS_GUIPort_address = "http://localhost:8080/wsig/ws/WS_GUI";

    public java.lang.String getWS_GUIPortAddress() {
        return WS_GUIPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WS_GUIPortWSDDServiceName = "WS_GUIPort";

    public java.lang.String getWS_GUIPortWSDDServiceName() {
        return WS_GUIPortWSDDServiceName;
    }

    public void setWS_GUIPortWSDDServiceName(java.lang.String name) {
        WS_GUIPortWSDDServiceName = name;
    }

    public WS_GUI.WS_GUIPort getWS_GUIPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WS_GUIPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWS_GUIPort(endpoint);
    }

    public WS_GUI.WS_GUIPort getWS_GUIPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WS_GUI.WS_GUIBindingStub _stub = new WS_GUI.WS_GUIBindingStub(portAddress, this);
            _stub.setPortName(getWS_GUIPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWS_GUIPortEndpointAddress(java.lang.String address) {
        WS_GUIPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (WS_GUI.WS_GUIPort.class.isAssignableFrom(serviceEndpointInterface)) {
                WS_GUI.WS_GUIBindingStub _stub = new WS_GUI.WS_GUIBindingStub(new java.net.URL(WS_GUIPort_address), this);
                _stub.setPortName(getWS_GUIPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WS_GUIPort".equals(inputPortName)) {
            return getWS_GUIPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:WS_GUI", "WS_GUIService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:WS_GUI", "WS_GUIPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WS_GUIPort".equals(portName)) {
            setWS_GUIPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
