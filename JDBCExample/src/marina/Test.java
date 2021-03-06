/*
 * Test.java
 *
 * Created on June 27, 2005, 1:04 PM
 */
package marina;
import marina.*;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Calendar;

import java.sql.*;
import sql.*;


/**
 *
 * @author Reg
 */
public class Test {
	/** Creates a new instance of Test */
	public Test() {}



	/**
	 *
	 */
	public static void main(String args[])		{
		Customer c1 = null;
		Boat b1a = null;
		Boat b1b = null;
		Slip s1a = null;
		Slip s1b = null;
		Lease l1a = null;
		Lease l1b = null;
		
		Customer c2 = null;
		Boat b2a = null;
		Boat b2b = null;
		Lease l2a = null;
		Lease l2b = null;

		Customer c3 = null;
		Boat b3a = null;
		Boat b3b = null;
		Lease l3a = null;
		Lease l3b = null;
		
		
		Customer c4 = null;
		Boat b4a = null;
		Boat b4b = null;

		try	{
//			removeCustomer("N12");
//			removeCustomer("N34");
//			removeCustomer("N56");
//			removeCustomer("N78");
//			removeCustomer("N910");

			//			System.exit(1);

			/* CUSTOMER 1				*/
//			c1 = searchForCustomer("N12");
//			s1a = Slip.findByPrimarykey(new SlipPK("S1"));
//			s1b = Slip.findByPrimarykey(new SlipPK("S2"));
			if (c1 == null)	{
				try	{
					c1 = addCustomer("N12", "Eleanor", "Atlanta", "123-4567");
					b1a = addBoat("MO3456", 28, "Tartan", 2002, c1);
//					b1b = addBoat("MO3457", 25, "Tartan", 2004, c1);
					s1a = addSlip("S1", 30.0, 15);
//					s1b = Slip.create("S2", 25.0, 15);

				} catch (Exception ex)	{
					ex.printStackTrace(System.out);
				}
			}

//			listBoats(c1); 

			l1a = addLease(c1, b1a, s1a);
			
//			l1b = addLease(c1, b1b, s1b);
			listBoats(c1); 

//			/* CUSTOMER 2				*/
//			c2 = searchForCustomer("N34");
//			if (c2 == null)	{
//				c2 = addCustomer("N34", "Smithy", "Boston", "867-1111");
//				b2a = addBoat("MO3456-N34", 28, "Tartan", 2002, c2);
//				b2b = addBoat("MO3457-N34", 25, "Tartan", 2004, c2);
//			}
//			listBoats(c2);
//			
//			/* CUSTOMER 3				*/
//			c3 = searchForCustomer("N56");
//			if (c3 == null)	{
//				c3 = addCustomer("N56", "Joseph", "Atlanta", "867-1111");
//				b3a = addBoat("MO3456-N56", 28, "Tartan", 2002, c3);
//				b3b = addBoat("MO3457-N56", 25, "Tartan", 2004, c3);
//			}
//			listBoats(c3);
//			
//			/* CUSTOMER 4				*/
//			c4 = searchForCustomer("N78");
//			if (c4 == null)	{
//				c4 = addCustomer("N78", "Jocky", "Horses", "867-1111");
//			}
//			listBoats(c4);
//		
//
//			Collection<Customer> customers = Customer.findAll();
//			Iterator citr = customers.iterator();
//			System.out.println("All Customers <" + customers.size() + ">");
//			while (citr.hasNext())	{
//				System.out.println("\tCustomer:" + citr.next());
//			}
			
//			Collection boats = Boat.findAll();
//			Iterator bitr = boats.iterator();
//			System.out.println("All Boats <" + boats.size() + ">");
//			while (bitr.hasNext())	{
//				System.out.println("\tBoat:" + (Boat) bitr.next());
//			}
			

		} catch (Exception ex)	{
			System.out.println("TEST:Processing Error <" + ex.toString() + ">");
			ex.printStackTrace(System.out);
		}

		
//		addCustomerBoatSlip("N1234", "Eleanor", "Atlanta", "123-4567",
//									"MO3456", 28, "Tartan", 2002,
//									1, 12, 20
//								);
//		addCustomerBoatSlip("CU55595", "Joe", "Boston", "890-1234",
//									"MO34567", 36, "Doran", 2006,
//									2, 15, 40);
	}

	public static Slip addSlip(String number, double length, int width)	{
		System.out.println("TEST: ========== addSlip:" + number + ", " + length + ", " + width);
		Slip s = null;
		try	{
//			s = new Slip(number, length, width);
			s = Slip.create(number, length, width, null);

		} catch (Exception ex)	{
			ex.printStackTrace();
		}

		System.out.println("TEST: ========== addSlip: ---------- complete for slip #" + number);
		return s;
	}

	/**
	 *	Test adding a customer
	 */
	public static Customer addCustomer(String custno, String name, String addr, String phone)	{
		System.out.println("TEST: ========== addCustomer:" + custno);

		Customer c = null;
		try	{
			c = Customer.create(custno, name, addr, phone);
		} catch (Exception ex)	{
			System.out.println("Test: Fatal SQL Error <" + ex.toString() + ">");
		}

		System.out.println("TEST: ========== addCustomer: ----- complete for customer #" + custno);
		return c;
	}

	/**
	 *	Test Adding a Boat
	 */
	public static Boat addBoat(String regno, double len, String manu, int year, Customer c)	{
		System.out.println("TEST: ========== addBoat:" + regno);
		Boat b = null;
		try	{
			b = Boat.create(regno, len, manu, year, c);

//			System.out.println("\tBoats owned by Customer:" + c.toString(", "));
//			Iterator itrB = c.boats();
//			while (itrB.hasNext())	{
//				System.out.println("\t\tBoat:" + itrB.next().toString());
//			}

		} catch (Exception ex)	{
			System.out.println("Test: Fatal SQL Error <" + ex.toString() + ">");
		}
		
		
		System.out.println("TEST: ========== addBoat: ---------- complete for boat #" + regno);
		return b;
	}

	
	public static Lease addLease(Customer customer, Boat boat, Slip slip)	{
		System.out.println("TEST: ========== addLease:"
				  + "\n\tCustomer:" + customer.getPrimaryKey()
				  + "\n\tBoat:" + boat.getPrimaryKey()
				  + "\n\tSlip:" + slip.getPrimarykey());
	
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.set(2005, Calendar.AUGUST, 28);
		java.util.Date d = aCalendar.getTime();
		java.sql.Date date1 = new java.sql.Date(d.getTime());
		Lease lease = null;
		
		try	{
			/*	assign the Boat to the Slip, lease							*/
			boat.assignToSlip(slip);
			slip.leaseAnnually(customer, date1, true);
			lease = slip.getLease();
		} catch (Exception ex)	{
			ex.printStackTrace(System.out);
		}

		System.out.println("TEST: ========== addLease: ---------- complete for lease #" + lease.getNumber());
		return lease;
	}
	
	
	public static Collection<Boat> listBoats(Customer c)	{
		System.out.println("========= Listing boats");
		Collection<Boat> list = null;

		try	{
			list = Boat.findByCustomer(c);
			Iterator itr = list.iterator();
			System.out.println("Boats for customer:" + c);
			while (itr.hasNext())	{
				System.out.println("\tBoat:" + itr.next());
			}
		} catch (FinderException fex)	{
			System.out.println("Can't find boats for customer <" + c + ">");
		}

		return list;
	}
	
	/**
	 *	Search for a customer.
	 *	@param	custno	The customer number of the customer to search for.
	 *	@return	The customer object if found, else a null
	 */
	public static Customer searchForCustomer(String custno)	{
		System.out.println("TEST: ========== search:" + custno);
		Customer c = null;

		try	{
			c = Customer.findByPrimarykey(new CustomerPK(custno));
			System.out.println("TEST:\tFound Customer " + custno + ":" + c);

		} catch (Exception ex)	{
			System.out.println("*** Test: Can't find customer <" + ex.toString() + ">");
		}
		
		return c;
	}

	/**
	 *	Search for a customer.
	 *	@param	custno	The customer number of the customer to search for.
	 *	@return	The customer object if found, else a null
	 */
	public static Collection<Boat> findBoatsForCustomer(Customer customer)	{
		System.out.println("TEST: ========== findBoatsForCustomer(" + customer + ")");
		Collection<Boat> boats = new ArrayList<Boat>();
		try	{
			boats = Boat.findByCustomer(customer);
			Iterator itr = boats.iterator();
			System.out.println("TEST:  Boats for customer:" + customer);
			while (itr.hasNext())	{
				Boat b = (Boat) itr.next();
				System.out.println("\tBoat:" + b);
			}
			System.out.println("TEST:  --- End of list");
		} catch (Exception ex)	{
			ex.printStackTrace(System.out);
		}
		
		return boats;
	}

	
	
	
	/**
	 *	Remove a customer and all their boats from the system.
	 *	@param	custno	The customer number for the customer to be removed.
	 */
	public static Customer	removeCustomer(String custno)	{
		System.out.println("TEST: ========== removeCustomer:" + custno);

		Customer c = null;
		int rc = 0;
		try	{
			c = searchForCustomer(custno);
			System.out.println("\tCustomer found:" + c);
			
			if (c != null)	{
				Collection boats = Boat.findByCustomer(c);
				Iterator itr = boats.iterator();
				System.out.println("Boats for customer:" + c);
				while (itr.hasNext())	{
					Boat b = (Boat) itr.next();
					System.out.println("\tRemoving Boat:" + b);
					b.remove();
				}

					/*	Remove the customer.													*/
					c = c.remove();
					System.out.println("c1.remove(): count=" + rc);
			
			}

		} catch (Exception ex)	{
			System.out.println("*** Test: Error removing boat <" + ex.toString() + ">");
			ex.printStackTrace();
		}


		return c;
	}

	
	/**
	 *	Test removing a boat from the data store.
	 *	@param	regno	The registration number of the boat to be removed.
	 */
	public static Boat removeBoat(String regno)	{
		System.out.println("TEST: ========== removeBoat:" + regno);
		Boat b = null;

		int rc = 0;
		try	{
			/* Remove some entities from the database.						*/
			b = Boat.findByPrimarykey(new BoatPK(regno));
			Boat rm = b.remove();
//			rc = Boat.removeByPrimarykey(new BoatPK(regno));
			if (rm != null)	System.out.println("Boat.removed():" + rm);

		} catch (Exception ex)	{
			System.out.println("*** Test: Error removing boat <" + ex.toString() + ">");
			ex.printStackTrace();
		}

		return b;
	}
	
	
	/**
	 *	Test adding a slip to a boat
	 */
	public static void addCustomerBoatSlip(
			  String custno, String name, String addr, String phone,
			  String regno, double len, String manu, int year,
			  String slipno, int sw, int sl)	{
		System.out.println("TEST: ========== addCustomerBoatSlip:" + custno);

		Customer c = addCustomer(custno, name, addr, phone);
		Boat b = addBoat(regno, len, manu, year, c);

		Slip s = null;
		Lease l = null;
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.set(2005, Calendar.AUGUST, 28);
		java.util.Date d = aCalendar.getTime();
		java.sql.Date date1 = new java.sql.Date(d.getTime());
		
		try	{
			s = new Slip(slipno, sw, sl);

			/*	assign the Boat to the Slip, lease							*/
			b.assignToSlip(s);
			s.leaseAnnually(c, date1, true);
			l = s.getLease();

			System.out.println("Boats owned by Customer:" + c.toString(", "));
			Iterator itrB = c.boats();
			while (itrB.hasNext())	{
				System.out.println("\tBoat:" + itrB.next().toString());
			}

			System.out.println("Boat " + s.getBoat().getRegistrationNo());
			System.out.println(" is assigned to Slip no " + b.getSlip().getNumber());

			System.out.println(c.getName() + " has leased slips:");
			Iterator itrL = c.leases();
			while (itrL.hasNext())	{
				System.out.println("\tLease:" + itrL.next().toString());
			}

			System.out.print("Slip number " + s.getNumber() + " is leased to ");
			System.out.println(s.getLease().getCustomer().getName());
			System.out.println("The lease amount is " + s.getLease().getAmount());

//			System.out.println("Starting with slip 1 navigating to slip "
//						+ s1.getBoat().getCustomer().getLease().getSlip().getNumber()
//					);

		} catch (Exception ex)	{
			System.out.println("Test: Fatal SQL Error <" + ex.toString() + ">");
			ex.printStackTrace();
		}
	}

	
}	/* End of CLASS:	Test.java			*/
