package it.uniroma1.dis.jaco.server;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import it.uniroma1.dis.jaco.model.Behavior;

@Path("/{client_id}/target")
public class TargetResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	String baseFilePath = System.getProperty("catalina.base") + File.separator
			+ "JacoStorage";

	@GET
	@Produces(MediaType.TEXT_XML)
	public Behavior getTargetBrowser(@PathParam("client_id") String client_id) {
		File targetXmlFile = new File(baseFilePath + File.separator + client_id
				+ File.separator + "Target" + File.separator + "Target.xml");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Behavior.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Behavior target = (Behavior) jaxbUnmarshaller
					.unmarshal(targetXmlFile);

			return target;
		} catch (JAXBException e) {
			throw new RuntimeException(
					"Problem with GET request: Target not found.");
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Behavior getTarget(@PathParam("client_id") String client_id)
			throws JAXBException {
		File targetXmlFile = new File(baseFilePath + File.separator + client_id
				+ File.separator + "Target" + File.separatorChar + "Target.xml");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Behavior.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Behavior target = (Behavior) jaxbUnmarshaller
					.unmarshal(targetXmlFile);

			return target;
		} catch (JAXBException e) {
			throw new RuntimeException(
					"Problem with GET request: Target not found.");
		}
	}

	@DELETE
	public Response clearTarget(@PathParam("client_id") String client_id) {
		File targetXmlFile = new File(baseFilePath + File.separator + client_id
				+ File.separator + "Target" + File.separator + "Target.xml");
		if (targetXmlFile.delete())
			return Response.ok().build();
		else
			return Response.status(404)
					.entity("Problem with DELETE request: Target not found.")
					.build();
		// throw new
		// RuntimeException("Problem with DELETE request: Target not found.");
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML,
			MediaType.APPLICATION_JSON })
	public Response putBehavior(JAXBElement<Behavior> target,
			@PathParam("client_id") String client_id) throws JAXBException,
			IOException {
		Behavior t = target.getValue();
		return putAndGetResponse(t, client_id);
	}

	private Response putAndGetResponse(Behavior target, String client_id)
			throws IOException {
		Response res;
		String baseDir = baseFilePath + File.separator + client_id
				+ File.separator + "Target";

		// System.out.println(baseDir + File.separator + "Target.xml");
		File targetXmlFile = new File(baseDir + File.separator + "Target.xml");

		if (!targetXmlFile.exists()) {
			new File(baseDir).mkdirs();
			targetXmlFile.createNewFile();
		}

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Behavior.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(target, targetXmlFile);

			res = Response.created(uriInfo.getAbsolutePath()).build();
			return res;
		} catch (JAXBException e) {
			throw new RuntimeException(
					"Problem with POST request: Impossible to create file on server.");
		}
	}
}
