package es.icarto.gvsig.fonsagua.importer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.icarto.gvsig.importer.Header;
import es.icarto.gvsig.importer.HeaderField;

public class FonsaguaHeader {

    public Header getHeader() {
	Header header = new Header();
	header.setFromRules(createHeader());
	return header;
    }

    private List<HeaderField> createHeader() {
	HeaderField h1 = new HeaderField("id");
	h1.setNotDefinedField(false);
	Set<String> s1 = new HashSet<String>(3);
	s1.add("name");
	s1.add("id");
	s1.add("nombre");
	h1.setRuleNames(s1);

	HeaderField h2 = new HeaderField("x");
	h2.setNotDefinedField(false);
	Set<String> s2 = new HashSet<String>(3);
	s2.add("lat");
	s2.add("x");
	s2.add("easting");
	h2.setRuleNames(s2);

	HeaderField h3 = new HeaderField("y");
	h3.setNotDefinedField(false);
	Set<String> s3 = new HashSet<String>(5);
	s3.add("lng");
	s3.add("y");
	s3.add("lon");
	s3.add("long");
	s3.add("northing");
	h3.setRuleNames(s3);
	
	HeaderField h4 = new HeaderField("altura");
	h4.setNotDefinedField(false);
	Set<String> s4 = new HashSet<String>(3);
	s4.add("elev");
	s4.add("elevacion");
	s4.add("altura");
	h4.setRuleNames(s4);

	List<HeaderField> l = new ArrayList<HeaderField>(4);
	Collections.addAll(l, h1, h2, h3, h4);
	return l;
    }

}
