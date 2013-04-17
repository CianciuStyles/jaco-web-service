package it.uniroma1.dis.jaco.server;

import it.uniroma1.dis.jaco.model.CompositionResponse;
import it.uniroma1.dis.jaco.model.ComputedComposition;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Path("/{client_id}/composition")
public class CompositionResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	String baseFilePath = System.getProperty("catalina.base") + File.separator
			+ "JacoStorage";

	@POST
	public Response requestComposition(@PathParam("client_id") String client_id) {
		Queue.instance.getModel().add(client_id);

		Response res = Response.ok().build();
		return res;
	}

	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON })
	public Response getComposition(@PathParam("client_id") String client_id) {
		File compositionXmlFile = new File(baseFilePath + File.separator
				+ client_id + File.separator + "Composition" + File.separator
				+ "Composition.xml");
		Response res = null;

		// System.out.println("Does the file exist? " +
		// compositionXmlFile.exists());
		if (compositionXmlFile.exists()) {
			ComputedComposition cc = null;
			// Read the composition file and send it to the client
			synchronized (compositionXmlFile) {
				try {
					JAXBContext jaxbContext = JAXBContext
							.newInstance(ComputedComposition.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext
							.createUnmarshaller();
					cc = (ComputedComposition) jaxbUnmarshaller
							.unmarshal(compositionXmlFile);
				} catch (JAXBException e) {
					e.printStackTrace();
					// throw new
					// RuntimeException("Problem with GET request: Behavior with name "
					// + name + " not found.");
				}
			}
			System.out.println("Did we have a composition? " + !(cc == null));

			CompositionResponse cr = new CompositionResponse(cc);
			res = Response.ok().entity(cr).build();
		} else {
			// Return the number in the queue
			String queueNumber = null;

			Object[] queue = Queue.instance.getModel().toArray();
			int queueSize = queue.length;

			for (int i = 0; i < queueSize; i++) {
				String currentRequest = (String) queue[i];
				System.out.println("Is " + currentRequest + " equal to "
						+ client_id + " ?");
				if (currentRequest.equals(client_id)) {
					queueNumber = "" + (i + 1);
					break;
				}
			}

			CompositionResponse cr = new CompositionResponse(queueNumber);
			res = Response.ok().entity(cr).build();
		}

		return res;
	}
}
