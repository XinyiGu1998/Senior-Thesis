package edu.skidmore.cs376b.semantics;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.update.GraphStore;
import com.hp.hpl.jena.update.GraphStoreFactory;
import com.hp.hpl.jena.update.UpdateAction;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * Demonstrate programmatic use of a graph database and semantic reasoner using
 * the Apache Jena framework.
 *
 * @author readda
 *
 */
public class UseReasoner {
	/**
	 * A default file to process - in case one is not supplied on the command line
	 */
	private final static String DEFAULT_INPUT_FILE = "samples/DSRDiffInferencingTestOntology.turtle";

	/**
	 * DBPedia's SPARQL URL
	 */
	private final static String DBPEDIA_SPARQL_ENDPOINT = "http://dbpedia.org/sparql";

	/**
	 * Common semantic namespace prefixes
	 */
	private final static String STANDARD_SPARQL_PREFIXES = "prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n"
			+ "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
			+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
			+ "prefix owl: <http://www.w3.org/2002/07/owl#>\n"
			+ "prefix olwsv: <http://www.openlinksw.com/schemas/virtrdf#>\n"
			+ "prefix olwsvdf: <http://www.openlinksw.com/schemas/virtrdf-data-formats#>\n"
			+ "prefix dbpc: <http://dbpedia.org/class/>\n" + "prefix dbpr: <http://dbpedia.org/resource/>\n"
			+ "prefix dbpo: <http://dbpedia.org/ontology/>\n" + "prefix dbpp: <http://dbpedia.org/property/>\n";

	/**
	 * A query suitable for DBPedia
	 */
	private final static String BOOK_LIST_DBPEDIA = STANDARD_SPARQL_PREFIXES + "select ?s ?p\n" + "where {\n"
			+ " ?s ?p dbpc:Book\n" + "}\n" + "order by ?s\n" + "limit 20";

	/**
	 * A query suitable for DBPedia using the SERVICE clause
	 */
	private final static String FAREWELL_TO_ARMS_SERVICE_DBPEDIA = STANDARD_SPARQL_PREFIXES + "select ?p ?o\n"
			+ "where {\n" + " SERVICE <http://dbpedia.org/sparql> {\n" + "  dbpr:A_Farewell_to_Arms ?p ?o .\n" + " }\n"
			+ "}\n" + "order by ?p\n";

	/**
	 * An update statement for the local model
	 */
	private final static String DELETE_MCCOY_CAR_AND_HOME_POLICIES = STANDARD_SPARQL_PREFIXES
			+ "prefix dsr: <http://monead.com/semantic/education#>\n" + "delete data {\n"
			+ "dsr:mcCoyLH dsr:policyHeld dsr:civicPolicy .\n" + "dsr:mcCoyLH dsr:policyHeld dsr:colonialPolicy .\n"
			+ "dsr:mcCoyLH dsr:name \"Bones\" .\n" + "}";

	/**
	 * The in-memory ontology and data model (Jena-based)
	 */
	private OntModel ontModel;

	/**
	 * Create the instance
	 */
	public UseReasoner() {
	}

	/**
	 * Create a model with the reasoning level set as requested
	 *
	 * @param spec
	 *            The reasoning specification
	 * @return The empty model with the reasoner configured
	 */
	private OntModel createModel(OntModelSpec spec) {
		// TODO return an ontology model from the ModelFactory
		return null;
	}

	/**
	 * Load triples from a file into an ontology model.
	 *
	 * @param inputFile
	 *            The file of triples to read
	 * @param reasoningLevel
	 *            The reasoning level
	 */
	private void loadTriples(File inputFile, OntModelSpec reasoningLevel) {
		FileInputStream inputStream = null;

		try {
			// Open a FileInputStream to the triples file
			inputStream = new FileInputStream(inputFile);

			// Create an ontology model using the createModel helper method
			ontModel = createModel(reasoningLevel);

			// Load the triples by calling read on the ontology model
			// read(input stream, base URL (or null), Serialization (e.g. TURTLE))
			// TODO call the read method on the ontology model

		} catch (Throwable throwable) {
			System.err.println("Error reading file: " + throwable.getClass().getName() + ": " + throwable.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Throwable throwable) {
					System.err.println("Error closing input file");
					throwable.printStackTrace();
					System.exit(-1);
				}
			}
		}
	}

	/**
	 * Show the classes and member instances from the model
	 */
	private void listClassesAndInstances() {
		ExtendedIterator<OntClass> iClasses;
		OntClass clazz;
		ExtendedIterator<? extends OntResource> instances;
		OntResource resource;

		System.out.println("Classes:");

		// Call the listClasses() method on the ontology model
		// Returns a ExtendedIterator<OntClass>
		// TODO get the classes iterator from the ontology model
		iClasses = null;

		// Iterate through the classes using hasNext() and next()
		while (iClasses.hasNext()) {
			// Get the next class using the next() method on the classes iterator, returns
			// an OntClass instance
			// TODO get the next class from the iterator
			clazz = null;

			// Print the class (using its default toString representation)
			System.out.println("  " + clazz);

			// Get the member instances by calling listInstances on the OntClass instance
			// Returns an ExtendedIterator<? extends OntResource>
			// TODO get the instances of the class
			instances = null;

			// Iterate through the instances using hasNext() and next()
			while (instances.hasNext()) {
				// Get the next instance by calling next() in the instances iterator
				// Returns a Resource
				// TODO get the next instance from the instances iterator
				resource = null;

				// Print the resource (using its default toString representation)
				System.out.println("    " + resource);
			}
		}

		// Close the classes iterator - it is backed by a connection to the model
		// TODO Close the classes iterator

	}

	/**
	 * List the individuals and their statements (e.g. where they are a subject)
	 * from the model
	 */
	private void listIndividualsAndStatements() {
		String subject;
		ExtendedIterator<Individual> iIndividuals;
		StmtIterator iProperties;
		Statement statement;

		System.out.println("Individuals:");

		// Call the listIndividuals method on the ontology model
		// Returns an ExtendedIterator<Individual>
		// TODO Get the set of all individuals from the ontology model
		iIndividuals = null;

		// Iterate through the individuals using hasNext() and next()
		while (iIndividuals.hasNext()) {
			// Call next() on the iterator - returns an Individual
			// TODO Get the next individual from the individuals iterator
			Individual individual = null;

			// Get the local (without namespace) name of the individual using getLocalName()
			// TODO Get the local name for the current individual
			subject = null;

			// Get the set of associated properties from the individual using
			// listPoperties()
			// Returns a StmtIterator
			// TODO get the list of properties for the individual
			iProperties = null;

			System.out.println("  " + subject + "-->");

			// Loop through all of the properties using the iterator's hasNext() and next()
			// methods
			while (iProperties.hasNext()) {
				// Call next() on the StmtIterator
				// Returns a Statement
				// TODO get the next statement from the properties iterator
				statement = null;

				// Print the predicate and object
				// getPredicate().getLocalName() on the Statement
				System.out.print("     " + statement.getPredicate().getLocalName());

				// Use the identifyObjectNoteType helper method passing the getObject() from the
				// Statement
				System.out.println("-->" + identifyObjectNodeType(statement.getObject()));
			}

			// Close the properties iterator - it is backed by a connection to the model
			iProperties.close();
		}

		// Close the individuals iterator - it is backed by a connection to the model
		iIndividuals.close();
	}

	/**
	 * Determine the type of a node
	 *
	 * @param node
	 *            The RDF node being examined
	 * @return The value of the node and its type
	 */
	private String identifyObjectNodeType(RDFNode node) {
		String nodeType = node.isAnon() ? "A"
				: node.isLiteral() ? "L" : node.isResource() ? "R" : node.isURIResource() ? "U" : "?";

		return "(" + nodeType + ")" + node;
	}

	/**
	 * Programmatically add assertions to the model
	 */
	private void insertData() {
		// Create a new class in the model and hold a reference to it
		// http://new#Being
		// TODO create a class in the ontology model
		OntClass being = null;

		// Get a class from the model and hold a reference to it
		// http://monead.com/semantic/education#Person
		// TODO get the Person class instance from the ontology model
		OntClass person = null;

		// Make two classes equivalent using the above references
		// addEquivalentClass()
		// TODO Assert that the classes are equivalent to each other


		// Create an individual of a class and hold a reference to it
		// createIndividual(individual, class)
		// "http://monead.com/semantic/education#mrScott" as a being
		// TODO Create an individual in the ontology model
		Individual mrScott = null;

		// Create a new datatype property and hold a reference to it
		// createDatatypeProperty(propname)
		// http://monead.com/semantic/education#degree
		// TODO Create a datatype property in the ontology model
		DatatypeProperty degree = null;

		// Add a literal (datatype property and value) for an individual
		// addLiteral(property, value)
		// mrScott degree Engineering
		// TODO Add a triple for the individual using the degree property


		// Get a datatype property from the model and hold a reference to it
		// getDatatypeProperty(propname)
		// http://monead.com/semantic/education#legalName
		DatatypeProperty legalName = ontModel.getDatatypeProperty("http://monead.com/semantic/education#legalName");

		// Add a literal relationship to an individual
		// addLiteral(property, value)
		// mrScott -> legalName -> Montgomery Scott
		mrScott.addLiteral(legalName, "Montgomery Scott");

		// Get an object property from the model and hold a reference to it
		// getObjectProperty(propname)
		// http://monead.com/semantic/education#policyHeld
		// TODO Get an object property instance from the ontology model
		ObjectProperty policyHeld = null;

		// Get an individual from the model and hold a reference to it
		// getIndividual(indivname)
		// http://monead.com/semantic/education#civicPolicy
		Individual civicPolicy = ontModel.getIndividual("http://monead.com/semantic/education#civicPolicy");

		// Add an object relationship to an individual
		// addProperty(propreference, object)
		// mrScott -> policyHeld -> civicPolicy
		// TODO Add a triple for the individual using the policyHeld property and civicPolicy

	}

	/**
	 * Execute a SPARQL query against the local model and display the results
	 *
	 * @param sparql
	 *            The SPARQL query
	 */
	private void localSparqlSelect(String sparql) {
		QueryExecution qe;
		ResultSet resultSet = null;
		int numResults = 0;

		// Parse and setup the query using the QueryFactory
		// QueryFactory.create(SPARQL Query, Syntax)
		// TODO Create a Query instance using the QueryFactory - Syntax is syntaxARQ
		final Query query = null;

		// Run the select query with the QueryExecutionFactory
		// QueryExecutionFactory.create(query, ontology model)
		// TODO Create the QueryExecution instance using the query and ontology model
		qe = null;

		// Get the result set from the query execution
		// query execution's execSelect() method returns a ResultSet
		// TODO Execute the SPARQL select using the QueryExecution instance, obtaining the ResultSet
		resultSet = null;

		// Iterate through the results using hasNext() and next()
		while (resultSet.hasNext()) {
			// Call next() on the result set - returns a QuerySolution
			// TODO Get the next QuerySolution from the ResultSet
			QuerySolution solution = null;

			// Get an Iterator of the variable names from the query
			// solution.varNames() returns an Iterator<String>
			// TODO Get an iterator for the variable names (e.g. the select variables)
			Iterator<String> varNames = null;

			// Increment the numResults count
			numResults++;

			// Print out the result number
			System.out.println("Result Number: " + numResults);

			// Iterate through the selected variables using the Iterable's hasNext() and
			// next() methods.
			while (varNames.hasNext()) {
				// Use varNames.next() to get the next variable name
				// TODO Get the next variable name
				String var = null;

				// Get the node from the result for the variable
				// solution.get(var) returns an RDFNode
				// TODO Get the RDFNode for the current variable from the current solution
				RDFNode node = null;

				// Print the varable's name
				System.out.print("  " + var + ": ");

				// Check the type of property values (literal versus object)
				if (node.isLiteral()) {
					// If literal, print it
					System.out.println("(L) " + solution.getLiteral(var));
				} else if (node.isResource()) {
					// If resource, print it
					System.out.println("(R) " + solution.getResource(var));
				} else if (node.isURIResource()) {
					// If URI resource, print it as a resource
					System.out.println("(U) " + solution.getResource(var));
				} else if (node.isAnon()) {
					// If it is an anonymous node, print it as a resource
					System.out.println("(A) " + solution.getResource(var));
				} else {
					// Just use the node's toString
					System.out.println("(N) " + node);
				}
			}
		}

		// Important - free up resources used running the query by closing the query
		// execution object - close() method
		qe.close();

		System.out.println("SPARQL query processing completed: " + numResults);

	}

	/**
	 * Execute a query against a remote SPARQL endpoint and display the results
	 *
	 * @param sparql
	 *            The SPARQL query
	 * @param serviceUrl
	 *            The URL of the SPARQL service to query
	 */
	private void remoteSparqlEndpoint(String sparql, String serviceUrl) {
		QueryExecution qe;
		ResultSet resultSet = null;
		int numResults = 0;

		// Parse and setup the query
		final Query query = QueryFactory.create(sparql, Syntax.syntaxARQ);

		// Run the select query with the QueryExecutionFactory
		// QueryExecutionFactory.sparqlService(serviceURL, query)
		// TODO Create the query execution instance using the SPARQL service
		qe = null;

		// Get the result set from the query execution
		// query execution's execSelect() method returns a ResultSet
		resultSet = qe.execSelect();

		// Iterate through the selected variables using the Iterable's hasNext() and
		// next() methods.
		while (resultSet.hasNext()) {
			// Call next() on the result set - returns a QuerySolution
			QuerySolution solution = resultSet.next();

			// Get an Iterator of the variable names from the query
			// solution.varNames() returns an Iterator<String>
			Iterator<String> varNames = solution.varNames();

			// Increment the numResults count
			numResults++;

			// Print out the result number
			System.out.println("Result Number: " + numResults);

			// Iterate through the selected variables using the Iterable's hasNext() and
			// next() methods.
			while (varNames.hasNext()) {
				// Use varNames.next() to get the next variable name
				String var = varNames.next();

				// Get the node from the result for the variable
				// solution.get(var) returns an RDFNode
				RDFNode node = solution.get(var);

				// Print the varable's name
				System.out.print("  " + var + ": ");

				// Check the type of property values (literal versus object)
				if (node.isLiteral()) {
					// If literal, print it
					System.out.println("(L) " + solution.getLiteral(var));
				} else if (node.isResource()) {
					// If resource, print it
					System.out.println("(R) " + solution.getResource(var));
				} else if (node.isURIResource()) {
					// If URI resource, print it as a resource
					System.out.println("(U) " + solution.getResource(var));
				} else if (node.isAnon()) {
					// If it is an anonymous node, print it as a resource
					System.out.println("(A) " + solution.getResource(var));
				} else {
					// Just use the node's toString
					System.out.println("(N) " + node);
				}
			}
		}

		// Important - free up resources used running the query by closing the query
		// execution object - close() method
		qe.close();

		System.out.println("SPARQL query processing completed: " + numResults);

	}

	/**
	 * This expects an insert or delete SPARQL statement. The model will be updated
	 * based on the provided statement
	 *
	 * @param sparql
	 *            The SPARQL insert or delete statement
	 */
	private void updateLocalModelUsingSparql(String sparql) {
		// Associate the ontology model with a graph store (in-memory graph database)
		// GraphStoreFactory.create(ontology model) returns a GraphStore instance
		// TODO Create a GraphStore instance from the ontology model
		GraphStore graphStore = null;

		// Get the count of individuals in the model
		long originalAssertionCount = ontModel.size();

		// Apply the SPARQL statement to the graph store
		// UpdateAction.parseExecute(SPARQL statement, graph store)
		// TODO Apply the update to the graph store


		// Get the updated count of individuals in the model
		long resultingAssertionCount = ontModel.size();

		System.out.println("Local model update completed [Original Assertion Count:" + originalAssertionCount
				+ " Resulting Assertion Count:" + resultingAssertionCount + "]");
	}

	/**
	 * Create an instance of this class and call the various ontology model and
	 * SPARQL processing methods
	 *
	 * @param args
	 *            Command line arguments - not used
	 */
	public static void main(String[] args) {
		UseReasoner me = new UseReasoner();

//		me.loadTriples(new File(DEFAULT_INPUT_FILE), OntModelSpec.OWL_DL_MEM);
//		me.listClassesAndInstances();
//		me.listIndividualsAndStatements();

//		me.insertData();
//		me.listIndividualsAndStatements();

//		me.localSparqlSelect("select ?s ?p ?o where { ?s ?p ?o . }");

//		me.listIndividualsAndStatements();
//		me.updateLocalModelUsingSparql(DELETE_MCCOY_CAR_AND_HOME_POLICIES);
//		me.listIndividualsAndStatements();

//		me.remoteSparqlEndpoint(BOOK_LIST_DBPEDIA, DBPEDIA_SPARQL_ENDPOINT);
//		me.localSparqlSelect(FAREWELL_TO_ARMS_SERVICE_DBPEDIA);
	}
}
