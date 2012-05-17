//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.18 at 01:45:20 AM CEST 
//


package de.tuberlin.av.openteagle.model.vct.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.tuberlin.av.openteagle.model.vct.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Shared_QNAME = new QName("", "shared");
    private final static QName _CommonName_QNAME = new QName("", "commonName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.tuberlin.av.openteagle.model.vct.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Vct }
     * 
     */
    public Vct createVct() {
        return new Vct();
    }

    /**
     * Create an instance of {@link Description }
     * 
     */
    public Description createDescription() {
        return new Description();
    }

    /**
     * Create an instance of {@link HasBookings }
     * 
     */
    public HasBookings createHasBookings() {
        return new HasBookings();
    }

    /**
     * Create an instance of {@link ProvidesResources }
     * 
     */
    public ProvidesResources createProvidesResources() {
        return new ProvidesResources();
    }

    /**
     * Create an instance of {@link ResourceInstance }
     * 
     */
    public ResourceInstance createResourceInstance() {
        return new ResourceInstance();
    }

    /**
     * Create an instance of {@link State }
     * 
     */
    public State createState() {
        return new State();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link List }
     * 
     */
    public List createList() {
        return new List();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "shared")
    public JAXBElement<Boolean> createShared(Boolean value) {
        return new JAXBElement<Boolean>(_Shared_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "commonName")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createCommonName(String value) {
        return new JAXBElement<String>(_CommonName_QNAME, String.class, null, value);
    }

}