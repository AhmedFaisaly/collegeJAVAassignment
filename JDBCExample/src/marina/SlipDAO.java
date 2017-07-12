/*
 * SlipDAO.java
 *
 * Created on July 2, 2005, 3:08 PM
 */

package marina;

import java.util.*;
import java.sql.*;

import sql.CoreDAOImpl;
import sql.CreateException;
import sql.CoreDAO;
import sql.DAOSysException;
import sql.NoSuchEntityException;

/**
 *	Data access object for Slip data.  This class bridges the
 *	object to non-object datastore layer.
 * @author Reg
 */
public class SlipDAO extends CoreDAOImpl	{
	/**
	 * Creates a new instance of SlipDAO
	 */
	public SlipDAO() { this(CoreDAO.DRIVER_NAME, CoreDAO.URL, CoreDAO.USER, CoreDAO.PASSWORD);		}

	/**
	 *	Parameterized constructor.  When extending this class the
	 *	derived class must invoke one of this classes constructors
	 *	for proper initialization.
	 *	@param	driver	Database driver.
	 *	@param	url		Database URL.
	 *	@param	user		Database user name.
	 *	@param	password	Database password for access.
	 *	@param	stm		SQL Statement for this intantiation.
	 *	@throws	SQLException when a SQL error has occured.
	 */
	public SlipDAO(String drivername,
						String url,
						String user,
						String password)	{
		super(drivername, url, user, password);
	}

	
	/* ACCESSORS	-----------------------------------------------	*/

	
	/* MUTATORS	--------------------------------------------------	*/


	
	/* BEHAVIOR	--------------------------------------------------------	*/
	/**
	 * Called by create() to insert entity state into the data store for a new entity.
	 *	@param	data	Persistence data model for the entity.
	 *	@throws	DAOSysException
	 */
	public void dbInsert(Object data)	throws DAOSysException {
		dbInsert(data, INSERT_STATEMENT);
	}
	
	/**
	 * Called by create() to insert entity state into the data store for a new entity.
	 *	@param	data	Persistence data model for the entity.
	 *	@param	insertStm	Data store statement for inserting into the data store.
	 *	@throws	DAOSysException
	 */
	public void dbInsert(Object data, String insertStm) throws DAOSysException		{
		SlipModel model = (SlipModel) data;
		PreparedStatement preparedStm = null;
		Connection connection = null;

		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(insertStm);
			preparedStm.setString(1, model.getNumber());
			preparedStm.setDouble(2, model.getLength());
			preparedStm.setInt(3, model.getWidth());
			preparedStm.setString(4, (model.getBoatPrimarykey() == null) ? null : model.getBoatPrimarykey().getRegistrationNo());
			preparedStm.executeUpdate();

		}	catch (SQLException sex)	{
			throw new DAOSysException("Error adding Slip <" + model.getNumber() + "> " + sex.getMessage());

		}	finally	{
			try	{
				releaseAll(null, preparedStm, connection);
			} catch (Exception ex)	{
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}
	}

	/**
	 * Called by findByPrimaryKey() to retrieve an entity by the primary key.
	 *	@param	primarykey The primary key for an entity.
	 *	@return	The persistence model for the entity, else null entity data is not found.
	 *	@throws	DAOSysException, NoSuchEntityException
	 */
	public Object dbSelectByPrimaryKey(Object primarykey)
				throws DAOSysException, NoSuchEntityException {
		if (_debug)	System.out.println("CDAO:dbSelectByPrimaryKey(" + primarykey + ")");
		return dbSelectByPrimaryKey(primarykey, new SlipModel());
	}
	
	/**
	 * Called by findByPrimaryKey() to retrieve an entity by the primary key.
	 *	@param	primarykey The primary key for an entity.
	 *	@param	model	The persistence model to be populated.
	 *	@return	The persistence model for the entity, else null entity data is not found.
	 *	@throws	DAOSysException, NoSuchEntityException
	 */
	public Object dbSelectByPrimaryKey(Object primarykey, Object model)
				throws DAOSysException, NoSuchEntityException	{
		if (_debug)	System.out.println("CDAO:dbSelectByPrimaryKey(key, model)");
		return dbSelectByPrimaryKey(primarykey, SELECT_STATEMENT, model);
	}
		

	/**
	 * Called by findByPrimaryKey() to retrieve an entity by the primary key.
	 *	@param	primarykey The primary key for an entity.
	 *	@param	selectStm	Data store statement to get the entity.
	 *	@return	The persistence model for the entity, else null entity data is not found.
	 *	@throws	DAOSysException, NoSuchEntityException
	 */
	public Object dbSelectByPrimaryKey(Object primarykey, String selectStm, Object model)
				throws DAOSysException, NoSuchEntityException	{
		if (_debug)	System.out.println("CDAO:dbSelectByPrimaryKey(key, stm, model)");
		SlipPK pk = (SlipPK) primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		boolean result = false;
		SlipModel persistenceModel = (SlipModel) model;

		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			preparedStm.setString(1, pk.getNumber());
			rs = preparedStm.executeQuery();

			result = rs.next();
			if (result)	{
				persistenceModel.setPrimarykey(new SlipPK(rs.getString(1)));
				persistenceModel.setLength(rs.getDouble(2));
				persistenceModel.setWidth(rs.getInt(3));
				persistenceModel.setBoatPrimarykey(new BoatPK(rs.getString(4)));

			}	else	{
				throw new NoSuchEntityException("Slip for <"
						+ primarykey + "> not found in the database.");
			}

		}	catch (SQLException sex)	{
			throw new DAOSysException(
				"dbSelectByPrimaryKey() SQL Exception\n"
				+ sex.getMessage());

		}	finally	{
			try	{
				releaseAll(rs, preparedStm, connection);
			} catch (Exception ex)	{
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}

		return persistenceModel;

	}

	/**
	 * Called by findAll() to find all entities in the data store.
	 *	@return	A collection of primary keys representing all of the entities.
	 *	@throws	SQLException
	 */
	public Collection dbSelectAll()	throws DAOSysException {
		return dbSelectAll(SELECT_DISTINCT_STATEMENT);
	}
	
	/**
	 * Called by findAll() to find all entities in the data store.
	 *	@return	A collection of primary keys representing all of the entities.
	 *	@throws	SQLException
	 */
	public Collection dbSelectAll(String selectStm)	throws DAOSysException {
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		ArrayList<SlipPK> list = null;
		if (selectStm == null)	selectStm = SELECT_DISTINCT_STATEMENT;

		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			rs = preparedStm.executeQuery();

			list = new ArrayList<SlipPK>();
			while (rs.next())	{
				list.add(new SlipPK(rs.getString(1)));
			}

		}	catch (SQLException sex)	{
			throw new DAOSysException(
						"dbSelectAll() SQL Exception\n"
						+ sex.getMessage());
		}	finally	{
			try	{
				releaseAll(rs, preparedStm, connection);
			} catch (Exception ex)	{
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}

		return list;
	}


	/**
	 * Called by update() to update state for an entity in the database.
	 *	@param	data	Persistence model for the entity.
	 *	@throws	DAOSysException
	 */
	public void dbUpdate(Object data)	throws DAOSysException	{
		dbUpdate(data, UPDATE_STATEMENT);
	}

	/**
	 * Called by update() to update state for an entity in the database.
	 *	@param	data	Persistence model for the entity.
	 *	@param	updateStm	Data store update statement.
	 *	@throws	DAOSysException
	 */
	public void dbUpdate(Object data, String updateStm)	throws DAOSysException {
		SlipModel model = (SlipModel) data;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		if (updateStm == null)	updateStm = UPDATE_STATEMENT;

		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(updateStm);

			/*	Grab values from persistent fields to store in database	*/
			preparedStm.setDouble(1, model.getLength());
			preparedStm.setInt(2, model.getWidth());
			preparedStm.setString(3,
					  ((model.getBoatPrimarykey() == null)
								? null : model.getBoatPrimarykey().getRegistrationNo()));
			preparedStm.setString(4, model.getNumber());

			int rowCount = preparedStm.executeUpdate();
			if (rowCount == 0)	{
 				throw new DAOSysException(
 					"Failed to store state for Slip <"
 					+ model.getNumber() + ">");
 			}

		}	catch (SQLException sex)	{
			sex.printStackTrace(System.out);
			throw new DAOSysException(
					"dbUpdate() SQL Exception <"
					+ sex.getMessage() + ">");

		}	finally	{
			try	{
				releaseAll(null, preparedStm, connection);
			} catch (Exception ex)	{
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}
	}

	/**
	 * Called by remove() to remove the state for an entity from the data store.
	 *	@param	primarykey	The primary key of the entity to be removed.
	 *	@throws	DAOSysException
	 */
	public int dbRemove(Object primarykey)	throws DAOSysException	{
		return dbRemove(primarykey, DELETE_STATEMENT);
	}
	
	/**
	 * Called by remove() to remove the state for a Slip entity from the database.
	 *	@param	primarykey	The primary key of the Slip entity
	 *	to be removed from the data store.
	 *	@param	deleteStm	Statement to remove entity data from the data store.
	 *	@throws	DAOSysException
	 */
	public int dbRemove(Object primarykey, String deleteStm)	throws DAOSysException	{
		SlipPK pk = (SlipPK) primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		int result = 0;
		if (deleteStm == null)	deleteStm = DELETE_STATEMENT;
		
		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(deleteStm);
			preparedStm.setString(1, pk.getNumber());
			result = preparedStm.executeUpdate();

			if (result == 0)	{
				throw new SQLException(
						"Failed to remove Slip <"
						+ pk.toString() + ">.");
			}

		}	catch (SQLException sex)	{
			throw new DAOSysException(
					"dbRemove() SQL Exception <" + pk.toString() + "> " + sex.getMessage());

		}	finally	{
			try	{
				releaseAll(null, preparedStm, connection);
			} catch (SQLException sqlex)	{
				throw new DAOSysException(sqlex.toString());
			}
		}

		return result;
	}


	/**
	 * Called by getTotalEntities().
	 *	@return	The total number of entities in the data store.
	 *	@throws	DAOSysException
	 */
	public int dbCountTotalEntities()	throws DAOSysException	{
		String selectStm = SELECT_DISTINCT_STATEMENT;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		int count = 0;

		try	{
			connection = connectToDB();
			/*	Request a resultset that is scrollable to easily count rows	*/
			preparedStm = connection.prepareStatement(
										selectStm,
										ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
			rs = preparedStm.executeQuery();

			/*	Go to the last row and get its row number							*/
			rs.last();
			count = rs.getRow();

		}	catch (SQLException sex)	{
			throw new DAOSysException(
						"dbCountTotalSlips() SQL Exception\n"
						+ sex.getMessage());

		}	finally	{
			try	{
				releaseAll(rs, preparedStm, connection);
			} catch	(SQLException sqlex)	{
				throw new DAOSysException(sqlex.toString());
			}
		}

		return count;
	}
	
	/**
	 * Called by store() to update state for an entity in the database.
	 *	@param	data	Persistence model for the entity.
	 *	@param	storeStm	Statement to store the data in the data store.
	 *	@throws	DAOSysException
	 */
	public void dbStore(Object data, String storeStm)	throws DAOSysException {
		dbUpdate(data, storeStm);
	}

	/**
	 * Called by store() to update state for an entity in the database.
	 *	@param	data	Persistence model for the entity.
	 *	@throws	DAOSysException
	 */
	public void dbStore(Object data)	throws DAOSysException {
		dbUpdate(data);
	}

	
	/* ATTRIBUTES	-----------------------------------------------	*/
	private final static String SELECT_DISTINCT_STATEMENT = "SELECT DISTINCT "
								+ "number "
								+ "FROM "
								+ "Slip";
	
	private final static String INSERT_STATEMENT = "INSERT INTO "
										+ "Slip"
										+ " VALUES "
										+ "( ?, ?, ?, ? )";

	private final static String SELECT_STATEMENT = "SELECT "
								+ " number "
								+ ", length "
								+ ", width "
								+ ", boatRegistrationNo "
								+ " FROM Slip "
								+ " WHERE number = ?";

	private final static String UPDATE_STATEMENT = "UPDATE "
								+ " Slip "
								+ " SET "
								+ " length = ?, "
								+ " width = ?, "
								+ " boatRegistrationNo = ? "
								+ " where number = ?";
	private final static String DELETE_STATEMENT = "DELETE FROM "
								+ "Slip"
								+ " WHERE number = ?";
	
	private final static boolean _debug = true;
	
}	/*	End of Class:	SlipDAO.java				*/
