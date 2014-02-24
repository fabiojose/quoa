package net.sourceforge.quoa.adapter;

import java.util.HashSet;
import java.util.Set;

public final class Configuration {

	private Set<Parameter> parameters;
	Configuration(){
		parameters = new HashSet<Parameter>();
	}
	
	Set<Parameter> parameters(){
		return parameters;
	}
	
	void addParameter(final Parameter parameter){
		parameters.add(parameter);
	}
	
	public static final class Parameter {
		private String name;
		private Class<?> type;
		
		public Parameter(){
			
		}
		
		public Parameter(final String name, final Class<?> type){
			setName(name);
			setType(type);
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Class<?> getType() {
			return type;
		}
		public void setType(Class<?> type) {
			this.type = type;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Parameter other = (Parameter) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
	}
}
