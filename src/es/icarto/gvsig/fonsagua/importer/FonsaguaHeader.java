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
	Set<String> s2 = new HashSet<String>(2);
	s2.add("lat");
	s2.add("x");
	h2.setRuleNames(s2);

	HeaderField h3 = new HeaderField("y");
	h3.setNotDefinedField(false);
	Set<String> s3 = new HashSet<String>(4);
	s3.add("lng");
	s3.add("y");
	s3.add("lon");
	s3.add("long");
	h3.setRuleNames(s3);

	List<HeaderField> l = new ArrayList<HeaderField>(3);
	Collections.addAll(l, h1, h2, h3);
	return l;
    }

}
