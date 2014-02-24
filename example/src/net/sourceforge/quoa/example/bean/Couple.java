package net.sourceforge.quoa.example.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import net.sourceforge.beanoa.bean.Visitable;
import net.sourceforge.beanoa.bean.Visitor;

@Embeddable
public final class Couple<C,S> implements Serializable, Visitable<Couple<C,S>>{
	private static final long serialVersionUID = -6478528276227767241L;

	private C   code;
	@Transient private S content;
	
	public Couple(){
		
	}
	
	public Couple(C code, S content){
		setCode(code);
		setContent(content);
	}
	
	public C getCode() {
		return code;
	}

	public void setCode(C code) {
		this.code = code;
	}

	public S getContent() {
		return content;
	}

	public void setContent(S content) {
		this.content = content;
	}

	@Override
	public <R> R accept(Visitor<Couple<C,S>, R> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return "Couple [code=" + code + ", content=" + content + "]";
	}

}
