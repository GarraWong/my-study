
package com.zjpl.system.webservice;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * OSB Service
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MdmServiceImplService", targetNamespace = "http://impl.service.mdm.neusoft.com/", wsdlLocation = "http://10.253.13.16:7003/mdmwebservice/ps/getMdmList?wsdl")
public class MdmServiceImplService
    extends Service
{

    private final static URL MDMSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException MDMSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName MDMSERVICEIMPLSERVICE_QNAME = new QName("http://impl.service.mdm.neusoft.com/", "MdmServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://10.253.13.16:7003/mdmwebservice/ps/getMdmList?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MDMSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        MDMSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public MdmServiceImplService() {
        super(__getWsdlLocation(), MDMSERVICEIMPLSERVICE_QNAME);
    }

    public MdmServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MDMSERVICEIMPLSERVICE_QNAME, features);
    }

    public MdmServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, MDMSERVICEIMPLSERVICE_QNAME);
    }

    public MdmServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MDMSERVICEIMPLSERVICE_QNAME, features);
    }

    public MdmServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MdmServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MdmService
     */
    @WebEndpoint(name = "MdmServiceImplPort")
    public MdmService getMdmServiceImplPort() {
        return super.getPort(new QName("http://impl.service.mdm.neusoft.com/", "MdmServiceImplPort"), MdmService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MdmService
     */
    @WebEndpoint(name = "MdmServiceImplPort")
    public MdmService getMdmServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://impl.service.mdm.neusoft.com/", "MdmServiceImplPort"), MdmService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MDMSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw MDMSERVICEIMPLSERVICE_EXCEPTION;
        }
        return MDMSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}