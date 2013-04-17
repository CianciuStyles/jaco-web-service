package it.uniroma1.dis.jaco.server;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import it.uniroma1.dis.jaco.model.Behavior;

public class BehaviorResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	String baseFilePath;
	String name;

	public BehaviorResource(UriInfo uriInfo, Request request, String client_id,
			String name) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.name = name;
		this.baseFilePath = System.getProperty("catalina.base")
				+ File.separator + "JacoStorage" + File.separator + client_id
				+ File.separator + "Behaviors";
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Behavior getBehavior() {
		/*
		 * Behavior behavior =
		 * StorageDao.instance.getModel(ipAddress).getBehaviors().get(name); if
		 * (behavior == null) throw new
		 * RuntimeException("Problem with GET request: Behavior with name " +
		 * name + " not found."); return behavior;
		 */
		File behaviorXmlFile = new File(baseFilePath + File.separatorChar
				+ name + ".xml");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Behavior.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Behavior behavior = (Behavior) jaxbUnmarshaller
					.unmarshal(behaviorXmlFile);

			return behavior;
		} catch (JAXBException e) {
			throw new RuntimeException(
					"Problem with GET request: Behavior with name " + name
							+ " not found.");
		}
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public Behavior getBehaviorHTML() {
		/*
		 * Behavior behavior =
		 * StorageDao.instance.getModel(ipAddress).getBehaviors().get(name); if
		 * (behavior == null) throw new
		 * RuntimeException("Problem with GET request: Behavior with name " +
		 * name + " not found."); return behavior;
		 */
		File behaviorXmlFile = new File(baseFilePath + File.separator + name
				+ ".xml");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Behavior.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Behavior behavior = (Behavior) jaxbUnmarshaller
					.unmarshal(behaviorXmlFile);

			return behavior;
		} catch (JAXBException e) {
			throw new RuntimeException(
					"Problem with GET request: Behavior with name " + name
							+ " not found.");
		}
	}

	// @POST
	// @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML,
	// MediaType.APPLICATION_JSON})
	public Response putBehavior(JAXBElement<Behavior> behavior)
			throws JAXBException, IOException, URISyntaxException {
		Behavior b = behavior.getValue();
		return putAndGetResponse(b);
	}

	private Response putAndGetResponse(Behavior behavior) throws JAXBException,
			IOException, URISyntaxException {
		/*
		 * Response res; if(
		 * StorageDao.instance.getModel(ipAddress).getBehaviors
		 * ().containsKey(behavior.getName()) ) { res =
		 * Response.noContent().build(); } else { res =
		 * Response.created(uriInfo.getAbsolutePath()).build(); }
		 * StorageDao.instance
		 * .getModel(ipAddress).getBehaviors().put(behavior.getName(),
		 * behavior); return res;
		 */

		Response res;
		File behaviorXmlFile = new File(baseFilePath + File.separator
				+ behavior.getName() + ".xml");

		if (behaviorXmlFile.exists())
			res = Response.noContent().build();
		else {
			// System.out.println(baseFilePath + File.separator +
			// behavior.getName() + ".xml");
			new File(baseFilePath).mkdirs();
			behaviorXmlFile.createNewFile();

			JAXBContext jaxbContext = JAXBContext.newInstance(Behavior.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(behavior, behaviorXmlFile);

			URI ub = new URI(uriInfo.getAbsolutePath() + "/"
					+ behavior.getName());
			res = Response.created(ub).build();
		}

		return res;
	}

	@DELETE
	public Response deleteBehavior() {
		File behaviorXmlFile = new File(baseFilePath + File.separator + name
				+ ".xml");
		if (behaviorXmlFile.delete())
			return Response.ok().build();
		else
			// throw new
			// RuntimeException("Problem with DELETE request: Behavior with name "
			// + name + " not found.");
			return Response
					.status(404)
					.entity("Problem with DELETE request: Behavior with name "
							+ name + " not found.").build();
	}

}
