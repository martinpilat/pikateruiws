/**
 * WS_GUIPort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WS_GUI;

public interface WS_GUIPort extends java.rmi.Remote {
    public void setProblem(java.lang.String[] agentDescriptions, java.lang.String[] fileNames) throws java.rmi.RemoteException;
    public WS_GUI.Option[] getOptions(java.lang.String agentName) throws java.rmi.RemoteException;
    public java.lang.String[] getAgents() throws java.rmi.RemoteException;
    public void getResults() throws java.rmi.RemoteException;
}
