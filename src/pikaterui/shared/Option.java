/**
 * Option.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pikaterui.shared;

public class Option  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String description;

    private java.lang.String synopsis;

    private java.lang.String value;

    public Option() {
    }

    public Option(
           java.lang.String name,
           java.lang.String description,
           java.lang.String synopsis,
           java.lang.String value) {
           this.name = name;
           this.description = description;
           this.synopsis = synopsis;
           this.value = value;
    }


    /**
     * Gets the name value for this Option.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Option.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this Option.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Option.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the synopsis value for this Option.
     * 
     * @return synopsis
     */
    public java.lang.String getSynopsis() {
        return synopsis;
    }


    /**
     * Sets the synopsis value for this Option.
     * 
     * @param synopsis
     */
    public void setSynopsis(java.lang.String synopsis) {
        this.synopsis = synopsis;
    }


    /**
     * Gets the value value for this Option.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this Option.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }
}
