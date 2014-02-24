package net.sourceforge.quoa.example.bean;

import java.util.Set;

public interface ICustomer {
	
	Set<Phone> getPhones();
	void setPhones(Set<Phone> phones);
	
	Set<Address> getAddresses();
	void setAddresses(Set<Address> addresses);
	
}
