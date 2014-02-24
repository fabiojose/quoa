package net.sourceforge.quoa.example;

import net.sourceforge.quoa.example.bean.Person;

public final class FamilyTree {

	public void doTree(){
		
		final Person _mariamem = new Person();
		_mariamem.setName("Maria Madalena Escoton de Moraes");
		
		final Person _joserm = new Person();
		_joserm.setName("José Rodrigues de Moraes");
		
		final Person _fabiojm = new Person();
		_fabiojm.setName("Fábio José de Moraes");
		_fabiojm.setMother(_mariamem);
		_fabiojm.setFather(_joserm);
		
		final Person _fabianaem = new Person();
		_fabianaem.setName("Fábiana Escoton de Moraes");
		_fabianaem.setMother(_mariamem);
		_fabianaem.setFather(_joserm);
		_fabianaem.getSibling().add(_fabiojm);
		_fabiojm.getSibling().add(_fabianaem);
		
		final Person _ricardom = new Person();
		_ricardom.setName("Ricardo Machado");
		
		final Person _vittoriaem = new Person();
		_vittoriaem.setName("Vittória Escoton Machado");
		_vittoriaem.setMother(_fabianaem);
		_vittoriaem.setFather(_ricardom);
	}

}
