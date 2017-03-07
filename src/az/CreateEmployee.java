package az;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CreateEmployee {
   public static void main( String[ ] args ) {
	   
	   
//	      https://www.tutorialspoint.com/jpa/jpa_criteria_api.htm	   
	   
	      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "eclipselink_jpa" );
	      EntityManager entitymanager = emfactory.createEntityManager( );

	      delete(entitymanager);
	      create(entitymanager);
	      update(entitymanager);

	      scalarandAggregateFunctions(entitymanager);
	      entitymanager.close( );
	      emfactory.close( );		
	      
   }
   public static void create(EntityManager entitymanager){
	      entitymanager.getTransaction( ).begin( );

	      Employee employee = new Employee( ); 
	      employee.setEid( 1201 );
	      employee.setEname( "Gopal" );
	      employee.setSalary( 40000 );
	      employee.setDeg( "Technical Manager" );
	      
	      entitymanager.persist( employee );
	      entitymanager.getTransaction( ).commit( );
 
	}
   public static void update(EntityManager entitymanager){
	      entitymanager.getTransaction( ).begin( );
	      Employee employee = entitymanager.find( Employee.class, 1201 );
	      employee.setSalary( 46000 );
	      entitymanager.getTransaction( ).commit( );

	}   
   public static void delete(EntityManager entitymanager){
	      entitymanager.getTransaction( ).begin( );
	      Employee employee = entitymanager.find( Employee.class, 1201 );
	      entitymanager.remove( employee );
	      entitymanager.getTransaction( ).commit( );
	}   
   public static void scalarandAggregateFunctions(EntityManager entitymanager) {
		Query query = entitymanager
				.createQuery("Select UPPER(e.ename) from Employee e");
		List<String> list = query.getResultList();

		for (String e : list) {
			System.out.println("Employee NAME :" + e);
		}

		// Aggregate function
		Query query1 = entitymanager
				.createQuery("Select MAX(e.salary) from Employee e");
		Double result = (Double) query1.getSingleResult();
		System.out.println("Max Employee Salary :" + result);
	}
}

	