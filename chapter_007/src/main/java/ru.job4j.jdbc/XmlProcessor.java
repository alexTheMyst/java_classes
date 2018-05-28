package ru.job4j.jdbc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Contains XML parse and transformation logic.
 *
 * @author alexey
 * @version 28.05.18
 */
public class XmlProcessor {
    /**
     * First XML file name.
     */
    private static final String FIRST_XML_FILE_NAME = "1.xml";
    /**
     * Second XML file name.
     */
    private static final String SECOND_XML_FILE_NAME = "2.xml";
    /**
     * First XSLT file name.
     */
    private static final String FIRST_XSLT_FILE_NAME = "xslt.xml";
    /**
     * Second XSLT file name.
     */
    private static final String SECOND_XSLT_FILE_NAME = "xslt_2.xml";
    /**
     * JAXB context instance.
     */
    private JAXBContext entryContainerContext;
    /**
     * JAXB marshaller instance.
     */
    private Marshaller firstMarshaller;
    /**
     * Path where files are stored.
     */
    private String path;

    /**
     * Constructor.
     *
     * @param path path to files
     * @throws JAXBException JAXB exeption
     */
    public XmlProcessor(String path) throws JAXBException {
        this.path = path;
        this.entryContainerContext = JAXBContext.newInstance(EntryContainer.class);
        this.firstMarshaller = entryContainerContext.createMarshaller();
        this.firstMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    /**
     * Creates first XML file.
     *
     * @param entryContainer container with data
     * @return the file
     * @throws JAXBException
     */
    public File createFirstXml(EntryContainer entryContainer) throws JAXBException {
        File resultFile = new File(this.path + FIRST_XML_FILE_NAME);
        this.firstMarshaller.marshal(entryContainer, resultFile);
        return resultFile;
    }

    /**
     * Creates second XML file.
     *
     * @param fileToTransform file to transform
     * @return the file
     * @throws TransformerException
     */
    public File createSecondXmlWithXslt(File fileToTransform) throws TransformerException {
        Transformer transformer = getTransformer(this.path + FIRST_XSLT_FILE_NAME);
        File resultFile = new File(this.path + SECOND_XML_FILE_NAME);
        Source xml = new StreamSource(fileToTransform);
        transformer.transform(xml, new StreamResult(resultFile));
        return resultFile;
    }

    /**
     * Creates result XML as a string.
     *
     * @param fileToTransform file to transform
     * @return result XML as a string
     * @throws TransformerException transformer exception
     */
    public String getTransformedString(File fileToTransform) throws TransformerException {
        Transformer transformer = getTransformer(this.path + SECOND_XSLT_FILE_NAME);
        Source xmlSource = new StreamSource(fileToTransform);
        StringWriter stringWriter = new StringWriter();
        transformer.transform(xmlSource, new StreamResult(stringWriter));
        return stringWriter.toString();
    }

    /**
     * Extract result from the XML string.
     *
     * @param stringToTransform string to process
     * @return result as long
     * @throws JAXBException JAXB exception
     */
    public long getResult(String stringToTransform) throws JAXBException {
        JAXBContext sumContext = JAXBContext.newInstance(SumResult.class);
        SumResult unmarshalledResult = (SumResult) sumContext.createUnmarshaller().unmarshal(new StringReader(stringToTransform));

        return unmarshalledResult.getResult();
    }

    /**
     * Creates JAXB transformer.
     *
     * @param pathToTemplate path to XSLT file
     * @return transformer instance
     * @throws TransformerConfigurationException transformer exception
     */
    private Transformer getTransformer(String pathToTemplate) throws TransformerConfigurationException {
        File xsltSylesheet = new File(pathToTemplate);
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(xsltSylesheet);
        return factory.newTransformer(xslt);
    }
}
