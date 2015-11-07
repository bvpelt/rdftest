package nl.bsoft.test.rdf.rdftest;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

import java.io.*;

/**
 * Tutorial 9 - demonstrate graph operations
 */
public class Tutorial09 extends Object {

	static final String inputFileName1 = "vc-db-3.rdf";
	static final String inputFileName2 = "vc-db-4.rdf";

	public static void main(String args[]) {
		// create an empty model
		Model model1 = ModelFactory.createDefaultModel();
		Model model2 = ModelFactory.createDefaultModel();

		// use the class loader to find the input file
		InputStream in1 = FileManager.get().open(inputFileName1);
		if (in1 == null) {
			throw new IllegalArgumentException("File: " + inputFileName1 + " not found");
		}
		InputStream in2 = FileManager.get().open(inputFileName2);
		if (in2 == null) {
			throw new IllegalArgumentException("File: " + inputFileName2 + " not found");
		}

		// read the RDF/XML files
		model1.read(in1, "");
		model2.read(in2, "");

		// merge the graphs
		Model model = model1.union(model2);

		// print the graph as RDF/XML
		model.write(System.out, "RDF/XML-ABBREV");

		System.out.println();

		String format = "RDF/XML-ABBREV";
		String fileName = "tutorial09.rdf";
		writeFile(model, fileName, format);
		
		format = "TURTLE";
		fileName = "tutorial09.turtle";
		writeFile(model, fileName, format);

		format = "TTL";
		fileName = "tutorial09.ttl";
		writeFile(model, fileName, format);

		format = "N-TRIPLES";
		fileName = "tutorial09.ntriples";
		writeFile(model, fileName, format);

		format = "RDF/XML";
		fileName = "tutorial09.rdfxml";
		writeFile(model, fileName, format);

		format = "N3";
		fileName = "tutorial09.n3";
		writeFile(model, fileName, format);

		format = "JSON-LD";
		fileName = "tutorial09.json";
		writeFile(model, fileName, format);

		format = "RDF/JSON";
		fileName = "tutorial09.rdfjson";
		writeFile(model, fileName, format);

	}

	private static void writeFile(final Model model, final String fileName, final String format) {
		
		FileWriter out = null;
		
		try {
			out = new FileWriter(fileName);
			model.write(out, format);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException closeException) {
				// ignore
			}
		}
	}
}