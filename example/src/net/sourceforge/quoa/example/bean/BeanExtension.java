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
import javax.persistence.SequenceGenerator;

import net.sourceforge.beanoa.bean.Visitable;
import net.sourceforge.beanoa.bean.Visitor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "BEANEXTENSION_SEQ", sequenceName = "BEANEXTENSION_SEQ", initialValue = 1, allocationSize = 1)
public abstract class BeanExtension<T> implements Serializable, Visitable<BeanExtension<T>> {
	private static final long serialVersionUID = -4935950682076061833L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BEANEXTENSION_SEQ")
	private long id;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "code", column = @Column(name = "NAME_CODE")),
		@AttributeOverride(name = "content", column = @Column(name = "NAME"))
	})
	private Couple<String, String> name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Couple<String, String> getName() {
		return name;
	}

	public void setName(Couple<String, String> name) {
		this.name = name;
	}

	@Override
	public <R> R accept(final Visitor<BeanExtension<T>, R> visitor) {
		return visitor.visit(this);
	}

	public abstract T getValue();
	public abstract void setValue(T value);
	
}
