package net.sourceforge.quoa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sourceforge.quoa.Ownership;

public final class ListDelegator {

	public static <E> List<Ownership<E>> newInstance(final E owner){
		
		return newInstance(owner, new ArrayList<Ownership<E>>());
	}
	
	public static <E> List<Ownership<E>> newInstance(final E owner,  final List<? extends Ownership<E>> list){
		
		final List<Ownership<E>> _result = new ArrayListDelegate<E>(owner, list);
		return _result;
	}
	
	private static class ArrayListDelegate<E> extends ArrayList<Ownership<E>>{
		private static final long serialVersionUID = 798322969813671916L;

		private E owner;
		public ArrayListDelegate(E owner){
			this.owner = owner;
		}
		
		public ArrayListDelegate(E owner, Collection<? extends Ownership<E>> collection){
			this(owner);
			addAll(collection);
		}
		
		public boolean add(Ownership<E> t){
			if(null!= t){
				t.setOwner(owner);
			}
			
			return super.add(t);
		}
		
		public void add(int index, Ownership<E> t){
			if(null!= t){
				t.setOwner(owner);
			}
			
			super.add(index, t);
		}
		
		public boolean addAll(Collection<? extends Ownership<E>> collection){
			if(null!= collection){
				for(Ownership<E> _t : collection){
					_t.setOwner(owner);
				}
			}
			
			return super.addAll(collection);
		}
		
		public boolean addAll(int index, Collection<? extends Ownership<E>> collection){
			if(null!= collection){
				for(Ownership<E> _t : collection){
					_t.setOwner(owner);
				}
			}
			
			return super.addAll(index, collection);
		}
	}
}
