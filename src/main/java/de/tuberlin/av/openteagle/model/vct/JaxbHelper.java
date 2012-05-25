package de.tuberlin.av.openteagle.model.vct;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.tools.ant.filters.StringInputStream;

import de.tuberlin.av.openteagle.model.vct.jaxb.VctInstance;

public class JaxbHelper {

	public static de.tuberlin.av.openteagle.model.vct.jaxb.List createList(String inputXml) {
		StringInputStream inputStream = new StringInputStream(inputXml);
		de.tuberlin.av.openteagle.model.vct.jaxb.List list = null;
		
		try {
			JAXBContext context = JAXBContext.newInstance(de.tuberlin.av.openteagle.model.vct.jaxb.List.class);
			Unmarshaller um = context.createUnmarshaller();
			list = (de.tuberlin.av.openteagle.model.vct.jaxb.List) um.unmarshal(inputStream);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public static String VctInstanceToString(VctInstance requestedVCT) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		try {
			JAXBContext context = JAXBContext.newInstance(VctInstance.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(requestedVCT, bout);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		
		return bout.toString();
	}

}
