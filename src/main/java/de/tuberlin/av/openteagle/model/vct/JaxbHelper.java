package de.tuberlin.av.openteagle.model.vct;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.tools.ant.filters.StringInputStream;

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

}
