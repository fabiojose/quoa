package net.sourceforge.quoa.example.bean;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import net.sourceforge.beanoa.bean.Visitable;
import net.sourceforge.beanoa.bean.Visitor;
import net.sourceforge.quoa.adapter.PropertiesAdapter;
import net.sourceforge.quoa.adapter.XPathAdapter;
import net.sourceforge.quoa.annotation.Argument;
import net.sourceforge.quoa.annotation.Attribute;
import net.sourceforge.quoa.annotation.InjectionMapping;
import net.sourceforge.quoa.annotation.QuoaSource;
import net.sourceforge.quoa.annotation.QuoaSources;
import net.sourceforge.quoa.annotation.Shapping;
import net.sourceforge.quoa.shaper.StringTrimShaper;

@QuoaSources(
	value = {
		@QuoaSource(adapter = XPathAdapter.class, name = "address.type",
			arguments = {
				@Argument(from = @Attribute(name = "type.code"), destination = "code")
			},
			injections = {
				@InjectionMapping(from = "description", destination = @Attribute(name = "type.content"))
			}
		),
		
		@QuoaSource(adapter = PropertiesAdapter.class, name = "address.country",
			arguments = {
				@Argument(from = @Attribute(name = "country.code"), destination = "code")
			},
			injections = {
				@InjectionMapping(from = "description", destination = @Attribute(name = "country.content"), shapping = @Shapping(shaper = StringTrimShaper.class))
			}
		),
		
		@QuoaSource(adapter = PropertiesAdapter.class, name = "address.state",
			arguments = {
				@Argument(from = @Attribute(name = "state.code"), destination = "code")
			},
			injections = {
				@InjectionMapping(from = "description", destination = @Attribute(name = "state.content"), shapping = @Shapping(shaper = StringTrimShaper.class))
			}
		),
		
		@QuoaSource(adapter = PropertiesAdapter.class, name = "address.city",
			arguments = {
				@Argument(from = @Attribute(name = "city.code"), destination = "code")
			},
			injections = {
				@InjectionMapping(from = "description", destination = @Attribute(name = "city.content"), shapping = @Shapping(shaper = StringTrimShaper.class))
			}
		)
	}
)

@Entity
@SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "ADDRESS_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Address implements Serializable, Visitable<Address> {
	private static final long serialVersionUID = 7215810926159453058L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
	private long id;
	
	/*
	 * content from a xml file configuration
	 */
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "code", column = @Column(name = "type_code"))
	})
	private Couple<Long, String> type;
	private String zip;
	
	/*
	 * content from a properties file configuration
	 */
	@ManyToOne(optional = false)
	private Country country;
	
	/*
	 * content from a properties file configuration
	 */
	@ManyToOne(optional = false)
	private State state;
	
	/*
	 * content from a properties file configuration 
	 */
	@ManyToOne(optional = false)
	private City city;
	
	private String line1;
	private String line2;
	
	public long getId(){
		return id;
	}
	public void setId(long id){
		this.id = id;
	}
	
	public Couple<Long, String> getType() {
		if(null== type){
			type = new Couple<Long, String>();
		}
		return type;
	}
	public void setType(Couple<Long, String> type) {
		this.type = type;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(final Country country) {
		this.country = country;
	}
	public State getState() {
		return state;
	}
	public void setState(final State state) {
		this.state = state;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	
	@Override
	public <R> R accept(Visitor<Address, R> visitor) {
		return visitor.visit(this);
	}
	@Override
	public String toString() {
		return "Address [type=" + type + ", zip=" + zip + ", country="
				+ country + ", state=" + state + ", city=" + city + ", line1="
				+ line1 + ", line2=" + line2 + "]";
	}
	
}
