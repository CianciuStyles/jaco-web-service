package it.uniroma1.dis.jaco.client;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import it.uniroma1.dis.jaco.model.Behavior;
import it.uniroma1.dis.jaco.model.NodeWithTransitions;
import it.uniroma1.dis.jaco.model.Transition;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Tester {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		// Create Sergio
		List<String> l1 = new LinkedList<String>();
		List<String> l2 = new LinkedList<String>();
		// List<String> l3 = new LinkedList<String>();
		l1.add("sergioNode2");
		l2.add("sergioNode1");
		// l3.add("sergioNode1");

		Transition t1 = new Transition("sergioAction1", l1);
		Transition t2 = new Transition("sergioAction2", l2);
		// Transition t3 = new Transition("sergioAction3", l2);

		List<Transition> tr1 = new LinkedList<Transition>();
		List<Transition> tr2 = new LinkedList<Transition>();
		// List<Transition> tr3 = new LinkedList<Transition>();
		tr1.add(t1);
		tr2.add(t2);
		// tr2.add(t3);
		// tr3.add(t3);

		NodeWithTransitions n1 = new NodeWithTransitions("sergioNode1", tr1);
		NodeWithTransitions n2 = new NodeWithTransitions("sergioNode2", tr2);
		// NodeWithTransitions n3 = new NodeWithTransitions("sergioNode3", tr3);

		List<NodeWithTransitions> fsm1 = new LinkedList<NodeWithTransitions>();

		fsm1.add(n1);
		fsm1.add(n2);
		// fsm1.add(n3);

		Behavior sergio = new Behavior("Sergio", "sergioNode1", fsm1);

		// Create Gianni
		List<String> gl1 = new LinkedList<String>();
		List<String> gl2 = new LinkedList<String>();
		// List<String> gl3 = new LinkedList<String>();
		gl1.add("gianniNode2");
		gl2.add("gianniNode1");
		// gl3.add("gianniNode1");

		Transition gt1 = new Transition("gianniAction1", gl1);
		Transition gt2 = new Transition("gianniAction2", gl2);
		// Transition gt3 = new Transition("gianniAction3", gl3);

		List<Transition> gtr1 = new LinkedList<Transition>();
		List<Transition> gtr2 = new LinkedList<Transition>();
		// List<Transition> gtr3 = new LinkedList<Transition>();
		gtr1.add(gt1);
		gtr2.add(gt2);
		// gtr3.add(gt3);

		NodeWithTransitions gn1 = new NodeWithTransitions("gianniNode1", gtr1);
		NodeWithTransitions gn2 = new NodeWithTransitions("gianniNode2", gtr2);
		// NodeWithTransitions gn3 = new NodeWithTransitions("gianniNode3",
		// gtr3);

		List<NodeWithTransitions> gfsm1 = new LinkedList<NodeWithTransitions>();

		gfsm1.add(gn1);
		gfsm1.add(gn2);
		// gfsm1.add(gn3);

		Behavior gianni = new Behavior("Gianni", "gianniNode1", gfsm1);

		// Create Target
		List<String> tl1 = new LinkedList<String>();
		List<String> tl2 = new LinkedList<String>();
		List<String> tl3 = new LinkedList<String>();
		List<String> tl4 = new LinkedList<String>();
		tl1.add("targetNode2");
		tl2.add("targetNode3");
		tl3.add("targetNode4");
		tl4.add("targetNode1");

		Transition tt1 = new Transition("gianniAction1", tl1);
		Transition tt2 = new Transition("sergioAction1", tl2);
		Transition tt3 = new Transition("gianniAction2", tl3);
		Transition tt4 = new Transition("sergioAction2", tl4);

		List<Transition> ttr1 = new LinkedList<Transition>();
		List<Transition> ttr2 = new LinkedList<Transition>();
		List<Transition> ttr3 = new LinkedList<Transition>();
		List<Transition> ttr4 = new LinkedList<Transition>();
		ttr1.add(tt1);
		ttr2.add(tt2);
		ttr3.add(tt3);
		ttr4.add(tt4);

		NodeWithTransitions tn1 = new NodeWithTransitions("targetNode1", ttr1);
		NodeWithTransitions tn2 = new NodeWithTransitions("targetNode2", ttr2);
		NodeWithTransitions tn3 = new NodeWithTransitions("targetNode3", ttr3);
		NodeWithTransitions tn4 = new NodeWithTransitions("targetNode4", ttr4);

		List<NodeWithTransitions> tfsm1 = new LinkedList<NodeWithTransitions>();

		tfsm1.add(tn1);
		tfsm1.add(tn2);
		tfsm1.add(tn3);
		tfsm1.add(tn4);

		Behavior target = new Behavior("Target", "targetNode1", tfsm1);

		ClientResponse response;
		String client_id = "on4laed1hm2n3vuna81f9agedh";

		// POST Sergio
		response = service.path(client_id).path("behaviors")
				// .path(npc1.getName())
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, sergio);
		// Return code should be 201 == created resource
		// System.out.println(response.getStatus());
		System.out.println(response.toString());

		// POST Gianni
		response = service.path(client_id).path("behaviors")
				// .path(npc1.getName())
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, gianni);
		// Return code should be 201 == created resource
		// System.out.println(response.getStatus());
		System.out.println(response.toString());

		// POST Target
		response = service.path(client_id).path("target")
				// .path(npc1.getName())
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, target);
		// Return code should be 201 == created resource
		// System.out.println(response.getStatus());
		// System.out.println(response.toString());
		// response =
		// service.path("behaviors").path("Stefano").delete(ClientResponse.class);
		System.out.println(response.toString());

		// Get the Behavior with ID Stefano
		// System.out.println(service.path("behaviors").path("MyEnemyMech").accept(MediaType.APPLICATION_JSON).get(String.class));

		response = service.path(client_id).path("composition")
				.accept(MediaType.TEXT_PLAIN).post(ClientResponse.class);
		System.out.println(response.toString());
		// System.out.println(service.path("auth").accept(MediaType.APPLICATION_XML).get(String.class));
		// System.out.println(service.path("auth").accept(MediaType.APPLICATION_JSON).get(String.class));

		// response = service.path("composition").post(ClientResponse.class);
		// System.out.println(response.toString());

		/*
		 * // Get the NonPlayerCharacters
		 * System.out.println(service.path("npcs")
		 * .accept(MediaType.TEXT_XML).get(String.class));
		 * 
		 * // Get JSON for application
		 * System.out.println(service.path("npcs").accept
		 * (MediaType.APPLICATION_JSON).get(String.class));
		 * 
		 * // Get XML for application
		 * System.out.println(service.path("npcs").accept
		 * (MediaType.APPLICATION_XML).get(String.class));
		 * 
		 * 
		 * 
		 * // Delete the NonPlayerCharacter with ID 1
		 * service.path("npcs/1").delete();
		 * 
		 * // Get all the NonPlayerCharacters, ID 1 should be deleted
		 * System.out.
		 * println(service.path("npcs").accept(MediaType.APPLICATION_XML
		 * ).get(String.class));
		 * 
		 * // Create a NonPlayerCharacter
		 * 
		 * Form form = new Form(); form.add("id", "4"); form.add("name",
		 * "npc4"); form.add("currentState", "moving"); response =
		 * service.path("npcs") .type(MediaType.APPLICATION_FORM_URLENCODED)
		 * .post(ClientResponse.class, form);
		 * //System.out.println("Form response: " +
		 * response.getEntity(String.class));
		 * 
		 * // Get all the NonPlayerCharacters, ID 4 should be created
		 * System.out.
		 * println(service.path("npcs").accept(MediaType.APPLICATION_XML
		 * ).get(String.class));
		 */
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:9799/jtlv").build();
		// return UriBuilder.fromUri("http://151.27.143.151:8080/jtlv").build();
	}
}
