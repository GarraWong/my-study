
package com.zjpl.system.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example package. 
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

    private final static QName _RequestMessage_QNAME = new QName("http://impl.service.mdm.neusoft.com/", "requestMessage");
    private final static QName _GetDataResponse_QNAME = new QName("http://impl.service.mdm.neusoft.com/", "getDataResponse");
    private final static QName _GetData_QNAME = new QName("http://impl.service.mdm.neusoft.com/", "getData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RequestMessage }
     * 
     */
    public RequestMessage createRequestMessage() {
        return new RequestMessage();
    }

    /**
     * Create an instance of {@link GetDataResponse }
     * 
     */
    public GetDataResponse createGetDataResponse() {
        return new GetDataResponse();
    }

    /**
     * Create an instance of {@link GetData }
     * 
     */
    public GetData createGetData() {
        return new GetData();
    }

    /**
     * Create an instance of {@link ErrorMessage }
     * 
     */
    public ErrorMessage createErrorMessage() {
        return new ErrorMessage();
    }

    /**
     * Create an instance of {@link ResponseMessage }
     * 
     */
    public ResponseMessage createResponseMessage() {
        return new ResponseMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.mdm.neusoft.com/", name = "requestMessage")
    public JAXBElement<RequestMessage> createRequestMessage(RequestMessage value) {
        return new JAXBElement<RequestMessage>(_RequestMessage_QNAME, RequestMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.mdm.neusoft.com/", name = "getDataResponse")
    public JAXBElement<GetDataResponse> createGetDataResponse(GetDataResponse value) {
        return new JAXBElement<GetDataResponse>(_GetDataResponse_QNAME, GetDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.mdm.neusoft.com/", name = "getData")
    public JAXBElement<GetData> createGetData(GetData value) {
        return new JAXBElement<GetData>(_GetData_QNAME, GetData.class, null, value);
    }

}
