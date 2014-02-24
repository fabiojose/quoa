package net.sourceforge.quoa.example;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import net.sourceforge.quoa.example.bean.BeanExtensionString;
import net.sourceforge.quoa.example.bean.Country;
import net.sourceforge.quoa.example.bean.Couple;
import net.sourceforge.quoa.example.bean.BeanExtension;
import net.sourceforge.quoa.example.bean.BeanExtensionDate;
import net.sourceforge.quoa.example.bean.Person;
import net.sourceforge.quoa.example.bean.Phone;
import net.sourceforge.quoa.example.bean.Price;
import net.sourceforge.quoa.example.bean.PriceProduct;
import net.sourceforge.quoa.example.bean.PriceService;
import net.sourceforge.quoa.example.bean.Product;
import net.sourceforge.quoa.example.bean.Service;
import net.sourceforge.quoa.example.bean.Student;
import net.sourceforge.quoa.util.Commons;

public final class JPAExample {

	private static final String UNIT = "QUOA";
	public static void main(String... args) {
		
		final EntityManagerFactory _factory = Persistence.createEntityManagerFactory(UNIT);
		final EntityManager _emanager = _factory.createEntityManager();
		
		try{
			
			_emanager.getTransaction().begin();
			
			final BeanExtension<Date> _administration = new BeanExtensionDate();
			_administration.setValue(Commons.newDate(2010, 1, 1));
			_administration.setName(new Couple<String, String>("START", "ADMINISTRATION START"));
			
			final BeanExtension<String> _bith = new BeanExtensionString();
			_bith.setValue("BELO HORIZONTE");
			_bith.setName(new Couple<String, String>("BIRTH_CITY", "Bith: City"));
			
			final Person _president = new Person();
			_president.setName("DILMA ROUSSEFF");
			_president.getExtension().add(_administration);
			_president.getExtension().add(_bith);
			
			_emanager.persist(_president);
			
			final Country _country = new Country();
			_country.setMonogram("br");
			_country.setName("Brasil");
			_country.setCreation(Commons.newDate(1822, 9, 7));
			_country.setPresident(_president);
			
			_emanager.persist(_country);
			
			final Student _student = new Student();
			_student.setAcademic("002200300696");
			_student.setName("JOSEPH MALÁIS");
			_student.setAlias("JOHN");
			_student.setBith(Commons.newDate(1983, 2, 10));
			
			_emanager.persist(_student);
			
			final Person _father = new Person();
			_father.setName("FATHER OF JOHN");
			_father.setBith(Commons.newDate(1970, 10, 10));
			
			_emanager.persist(_father);
			
			_student.setFather(_father);
			
			final Person _mother = new Person();
			_mother.setName("MOTHER OF JOHN");
			_mother.setBith(Commons.newDate(1972, 10, 22));
			
			_emanager.persist(_mother);
			
			_student.setMother(_mother);
			
			final Phone _phone = new Phone();
			_phone.setNote("JUST AT NIGHT");
			_phone.setNumber("55 66 8888 9999");
			
			_student.setPhone(_phone);
			
			final Product _product = new Product();
			_product.setCode("0444TT/ff");
			_product.setCreation(new Date());
			_product.setName("ARM PROCESSOR 1.4GHz");
			_product.setParts(1);
			
			_emanager.persist(_product);
			
			final Price _price = new PriceProduct();
			_price.setSelling(_product);
			_price.setValue(250.0D);
			_price.setName("NEW PRICE - OVER 5%");
			_price.setCreation(new Date());
			_price.setDate(Commons.newDate(2012, 5, 1));
			
			_emanager.persist(_price);
			
			final Service _service = new Service();
			_service.setCode("SERV/7774");
			_service.setName("CEL PROCESSOR FIX");
			
			_emanager.persist(_service);
			
			final Price _sprice = new PriceService();
			_sprice.setSelling(_service);
			_sprice.setValue(55.78D);
			_sprice.setName("NEW SERVICE PRICE - OVER 10%");
			
			_emanager.persist(_sprice);
			
			//FAMILY TREE
			final Person _mariamem = new Person();
			_mariamem.setName("Maria Madalena Escoton de Moraes");
			_mariamem.getSex().setCode("FEMALE");
			_emanager.persist(_mariamem);
			
			final Person _joserm = new Person();
			_joserm.setName("José Rodrigues de Moraes");
			_joserm.getSex().setCode("MALE");
			_emanager.persist(_joserm);
			
			_mariamem.setWedlock(_joserm);
			
			final Person _fabiojm = new Person();
			_fabiojm.setName("Fábio José de Moraes");
			_fabiojm.getSex().setCode("MALE");
			_fabiojm.setMother(_mariamem);
			_fabiojm.setFather(_joserm);
			_emanager.persist(_fabiojm);
			
			final Person _fabianaem = new Person();
			_fabianaem.setName("Fabiana Escoton de Moraes");
			_fabianaem.getSex().setCode("FEMALE");
			_fabianaem.setMother(_mariamem);
			_fabianaem.setFather(_joserm);
			_fabianaem.getSibling().add(_fabiojm);
			_fabiojm.getSibling().add(_fabianaem);
			_emanager.persist(_fabianaem);
			
			final Person _ricardom = new Person();
			_ricardom.setName("Ricardo Machado");
			_ricardom.getSex().setCode("MALE");
			_emanager.persist(_ricardom);
			
			final Person _vittoriaem = new Person();
			_vittoriaem.setName("Vittória Escoton Machado");
			_vittoriaem.getSex().setCode("FEMALE");
			_vittoriaem.setMother(_fabianaem);
			_vittoriaem.setFather(_ricardom);
			_emanager.persist(_vittoriaem);
			
			//FAMILY TREE
			/*
			final Human _person = new Human();
			_person.setIdentification("29961088822");
			_person.setAge(29);
			
			final Address _address = new Address();
			_address.setLine1("line11");
			_address.setLine2("line22");
			_address.setZip("zip");
			
			_address.getType().setCode(666L);
			_address.getCountry().setCode("br");
			_address.getState().setCode("sp");
			_address.getCity().setCode("355160");
			
			_person.setHome(_address);

			_emanager.persist(_person);
			_emanager.persist(_address);
			
			final Phone _phone = new Phone();
			_phone.setNumber("5255-6666");
			_phone.setNode("TALK TO JOANA");
			
			final School _school = new School();
			_school.setAddress(_address);
			_school.setName("ESCOLA POLITECNICA");
			_school.getPhones().add(_phone);
			
			_emanager.persist(_school);
			
			final Student _student = new Student();
			_student.setAge(25);
			_student.setIdentification("888-9999-6666");
			_student.setHome(_address);
			
			_emanager.persist(_student);
			
			final Study _study = new Study();
			_study.setSchool(_school);
			_study.setYear(2010);
			
			_student.setStuding(_study);
			
			final Customer _customer = new Customer();
			_customer.setAge(39);
			_customer.setIdentification("777-77-8888");
			final Address _home = new Address();
			_home.setLine1("R. HEITOR PENTEADO, 999");
			_home.setZip("01543-000");
			_home.getCountry().setCode("br");
			_home.getState().setCode("sp");
			_home.getCity().setCode("355030");
			_home.getType().setCode(1L);
			
			_customer.setHome(_home);
			_customer.getAddresses().add(_address);
			
			_emanager.persist(_customer);
			
			final Company _company = new Company();
			_company.setAddress(_home);
			_company.setBrand("MOOVE TERMOLAR");
			_company.setFoundation(new Date());
			_company.setIdentification("77788-99898888-IO");
			
			_emanager.persist(_company);
			
			final Work _work = new Work();
			_work.setAdmission(new Date());
			_work.setCompany(_company);
			_work.setRemuneration(900D);
			_work.setWorker(_student);
			
			_emanager.persist(_work);
			
			//----QUERY
			*/
			final Query _qperson = _emanager.createQuery("from Being");
			System.out.println(_qperson.getResultList());
			
			//----COMMIT
			_emanager.getTransaction().commit();
			
		}finally{
			_emanager.close();
			_factory.close();
		}

	}

}
