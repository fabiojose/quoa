package net.sourceforge.quoa.example.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import net.sourceforge.beanoa.bean.Visitable;
import net.sourceforge.beanoa.bean.Visitor;
import net.sourceforge.quoa.adapter.CSVAdapter;
import net.sourceforge.quoa.annotation.Argument;
import net.sourceforge.quoa.annotation.Attribute;
import net.sourceforge.quoa.annotation.InjectionMapping;
import net.sourceforge.quoa.annotation.QuoaSource;
import net.sourceforge.quoa.annotation.QuoaSources;
import net.sourceforge.quoa.annotation.Shapping;
import net.sourceforge.quoa.shaper.StringTrimShaper;

@QuoaSources({
	@QuoaSource(adapter = CSVAdapter.class, name = "being.name",
		arguments = {
			@Argument(from = @Attribute(name = "document"), destination = "document")
		},
		injections = {
			@InjectionMapping(from = "name", destination = @Attribute(name = "name"), shapping = @Shapping(shaper = StringTrimShaper.class))
		}
	)
  }
)

/*
@CollectionForeignQuerySources(sources = {
	@CollectionForeignQuerySource(collection = List.class, name = "person.addresses",
		attribute = @Attribute(name = "addresses"), type = Address.class, adapter = CSVAdapter.class,
		arguments = {
			@Argument(from = @Attribute(name = "id"), destination = "PERSON_ID")
		},
		injections = {
			@InjectionMapping(from = "type",    destination = @Attribute(name = "type.code")),
			@InjectionMapping(from = "COUNTRY", destination = @Attribute(name = "country.code")),
			@InjectionMapping(from = "STATE",   destination = @Attribute(name = "state.code")),
			@InjectionMapping(from = "CITY",    destination = @Attribute(name = "city.code")),
			@InjectionMapping(from = "LINE1",   destination = @Attribute(name = "line1")),
			@InjectionMapping(from = "LINE2",   destination = @Attribute(name = "line2"))
		}
	)
})
*/
@Entity
@SequenceGenerator(name = "BEING_SEQ", sequenceName = "BEING_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Being implements Serializable, Visitable<Being> {
	private static final long serialVersionUID = 6897589431072310858L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BEING_SEQ")
	private long id;
	
	private String name;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "BEING_ID", nullable = false)
	private Set<BeanExtension<? extends Object>> extension;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<BeanExtension<? extends Object>> getExtension() {
		if(null== extension){
			extension = new HashSet<BeanExtension<? extends Object>>();
		}
		return extension;
	}

	public void setExtension(Set<BeanExtension<? extends Object>> extension) {
		this.extension = extension;
	}

	@Override
	public <R> R accept(Visitor<Being, R> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return "Being [id=" + id + ", name=" + name + "]";
	}
/*
	private class ArrayListDelegate extends ArrayList<Address>{
		private static final long serialVersionUID = 798322969813671916L;

		public ArrayListDelegate(){
		}
		
		public ArrayListDelegate(Collection<? extends Address> collection){
			addAll(collection);
		}
		
		public boolean add(Address address){
			if(null!= address){
				address.setOwner(Being.this);
			}
			
			return super.add(address);
		}
		
		public void add(int index, Address address){
			if(null!= address){
				address.setOwner(Being.this);
			}
			
			super.add(index, address);
		}
		
		public boolean addAll(Collection<? extends Address> collection){
			if(null!= collection){
				for(Address _address : collection){
					_address.setOwner(Being.this);
				}
			}
			
			return super.addAll(collection);
		}
		
		public boolean addAll(int index, Collection<? extends Address> collection){
			if(null!= collection){
				for(Address _address : collection){
					_address.setOwner(Being.this);
				}
			}
			
			return super.addAll(index, collection);
		}
	}*/
}
