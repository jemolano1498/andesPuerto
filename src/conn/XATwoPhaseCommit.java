package conn;

/*
A simple 2 phase XA demo. Both the branches talk to different RMS
Need 2 java enabled 8.1.6 databases to run this demo.
  -> start-1
  -> start-2
  -> Do some DML on 1
  -> Do some DML on 2
  -> end 1
  -> end 2
  -> prepare-1
  -> prepare-2
  -> commit-1
  -> commit-2
Please change the URL2 before running this.
 */

//You need to import the java.sql package to use JDBC
import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;
import oracle.jdbc.driver.*;
import oracle.jdbc.pool.*;
import oracle.jdbc.xa.OracleXid;
import oracle.jdbc.xa.OracleXAException;
import oracle.jdbc.xa.client.*;
import vos.factura;

import javax.transaction.xa.*;
import javax.transaction.UserTransaction;

public class XATwoPhaseCommit 
{
	public static void main(String [ ] args) throws SQLException
	{
		runRF15("1","Exportador2");
	}
	
	public XATwoPhaseCommit()
	{
		
	}
	static Xid createXid(int bids) throws XAException
	{
		byte[] gid = new byte[1]; gid[0]= (byte) 9;
		byte[] bid = new byte[1]; bid[0]= (byte) bids;
		byte[] gtrid = new byte[64];
		byte[] bqual = new byte[64];
		System.arraycopy (gid, 0, gtrid, 0, 1);
		System.arraycopy (bid, 0, bqual, 0, 1);
		Xid xid = new OracleXid(0x1234, gtrid, bqual);
		return xid;
	}

	@SuppressWarnings("unused")
	private static void doSomeWork1 (Connection conn)
			throws SQLException
	{
		// Create a Statement
		Statement stmt = conn.createStatement ();

		int cnt = stmt.executeUpdate ("insert into my_table values (4321)");

		System.out.println("No of rows Affected " + cnt);

		stmt.close();
		stmt = null;
	}

	public static void runRF15(String idExportador, String idNombre) throws SQLException
	{
		try
		{
			String URL1 = "jdbc:oracle:thin:@(description=(address="
					+ "(host=fn3.oracle.virtual.uniandes.edu.co)"
					+ "(protocol=tcp)(port=1521))"
					+ "(connect_data=(sid=prod)))";
			String URL2 = "jdbc:oracle:thin:@(description=(address="
					+ "(host=fn3.oracle.virtual.uniandes.edu.co)"
					+ "(protocol=tcp)(port=1521))"
					+ "(connect_data=(sid=prod)))";
			String URL3 = "jdbc:oracle:thin:@(description=(address="
					+ "(host=fn3.oracle.virtual.uniandes.edu.co)"
					+ "(protocol=tcp)(port=1521))"
					+ "(connect_data=(sid=prod)))";
			DriverManager.registerDriver(new OracleDriver());

			// You can put a database name after the @ sign in the connection URL.
			Connection conna =
					DriverManager.getConnection (URL1, "ISIS2304B071610", "7BwKvhvuJPR6ejUu");

			// Prepare a statement to create the table
			Statement stmta = conna.createStatement ();

			Connection connb =
					DriverManager.getConnection (URL2, "ISIS2304B211610", "xeh9t4CapThef7eh");

			// Prepare a statement to create the table
			Statement stmtb = connb.createStatement ();

			Connection connc = 
					DriverManager.getConnection (URL3, "ISIS2304B331610", "JmQspTgegtuP5Hu7");

			Statement stmtc = connb.createStatement ();

			// Create a XADataSource instance
			OracleXADataSource oxds1 = new OracleXADataSource();
			oxds1.setURL("jdbc:oracle:thin:@(description=(address=(host=fn3.oracle.virtual.uniandes.edu.co)"
					+ "(protocol=tcp)(port=1521))"
					+ "(connect_data=(sid=prod)))");
			oxds1.setUser("ISIS2304B071610");
			oxds1.setPassword("7BwKvhvuJPR6ejUu");

			OracleXADataSource oxds2 = new OracleXADataSource();

			oxds2.setURL("jdbc:oracle:thin:@(description=(address=(host=fn3.oracle.virtual.uniandes.edu.co)"
					+ "(protocol=tcp)(port=1521))"
					+ "(connect_data=(sid=prod)))");
			oxds2.setUser("ISIS2304B211610");
			oxds2.setPassword("xeh9t4CapThef7eh");

			OracleXADataSource oxds3 = new OracleXADataSource();

			oxds3.setURL("jdbc:oracle:thin:@(description=(address=(host=fn3.oracle.virtual.uniandes.edu.co)"
					+ "(protocol=tcp)(port=1521))"
					+ "(connect_data=(sid=prod)))");
			oxds3.setUser("ISIS2304B331610");
			oxds3.setPassword("JmQspTgegtuP5Hu7");

			// Get a XA connection to the underlying data source
			XAConnection pc1  = oxds1.getXAConnection();

			// We can use the same data source 
			XAConnection pc2  = oxds2.getXAConnection();

			XAConnection pc3 = oxds3.getXAConnection();

			// Get the Physical Connections
			Connection conn1 = pc1.getConnection();
			Connection conn2 = pc2.getConnection();
			Connection conn3 = pc3.getConnection();

			// Get the XA Resources
			XAResource oxar1 = pc1.getXAResource();
			XAResource oxar2 = pc2.getXAResource();
			XAResource oxar3 = pc3.getXAResource();

			// Create the Xids With the Same Global Ids
			Xid xid1 = createXid(1);
			Xid xid2 = createXid(2);
			Xid xid3 = createXid(3);

			// Start the Resources
			oxar1.start (xid1, XAResource.TMNOFLAGS);
			oxar2.start (xid2, XAResource.TMNOFLAGS);
			oxar3.start(xid3, XAResource.TMNOFLAGS);

			// CALCULAR BONO
			Statement stmt1 = conn1.createStatement ();
			Statement stmt2 = conn2.createStatement ();
			Statement stmt3 = conn3.createStatement ();

			ResultSet rset = stmt1.executeQuery ("Select f.* from factura f "
					+ "join exportador e on f.IDEMPRESA = e.IDEMPRESA where "
					+ "e.IDEMPRESA = 1");

			ResultSet rset2 = stmt2.executeQuery ("SELECT F.ID ID_FACTURA, "
					+ "F.ID_BUQUE ID_BUQUE,"
					+ "F.ID_CARGA ID_CARGA,F.DIAS_EN_PUERTO DIAS_EN_PUERTO,"
					+ "F.COSTO COSTO,F.ID_OPERADOR ID_OPERADOR,"
					+ "F.ID_EXPORTADOR ID_EXPORTADOR "
					+ "FROM EXPORTADOR E JOIN FACTURA F ON "
					+ "E.ID = F.ID_EXPORTADOR WHERE ID_PUERTO = '1'"
					+  " AND E.ID = '2' AND NOMBRE = 'Exportador2'");

			ResultSet rset3 = stmt3.executeQuery ("SELECT f.*, e.id as id_exportador "
					+ "FROM EXPORTADOR e, FACTURA f, BUQUE_DE_EXPORTADOR b WHERE e.ID = "
					+ "b.ID_EXPORTADOR and b.ID_BUQUE = f.ID_BUQUE_USADO");

			int idE = Integer.parseInt(idExportador);
			
			while (rset.next() && rset2.next() && rset3.next())
			{
				int costo1 = rset.getInt("TOTALPRECIO");
				int costo2 = Integer.parseInt(rset2.getString(5));
				int costo3 = rset3.getInt("COSTO_TOTAL");

				double total = 0;
				double descuento = 0;

				if(costo1 != 0 && !rset2.getString(5).equals("") && costo3 == 0)
				{
					total = costo1 + costo2;
					descuento = 300/total;
					System.out.println("Costo total facturado: " + (total-descuento));
					Statement s1 = conn1.createStatement ();
					Statement s2 = conn2.createStatement();
					s1.executeUpdate ("UPDATE FACTURA SET TOTALPRECIO = " +
							(costo1 - descuento) + " WHERE IDFACTURA = " + idE);
					s2.executeUpdate ("update factura set COSTO = " +
							(costo2 - descuento) + " where "
							+ "ID_EXPORTADOR = '" + idExportador + 
							"' and (select nombre from "
							+ "exportador where id = '1') = '" + idNombre + "'");
				}

				if(costo1 != 0 && costo3 != 0 && rset2.getString(5).equals(""))
				{
					total = costo1 + costo3;
					descuento = 300/total;
					System.out.println("Costo total facturado: " + (total-descuento));
					Statement s1 = conn1.createStatement ();
					Statement s3 = conn3.createStatement ();
					s1.executeUpdate ("UPDATE FACTURA SET TOTALPRECIO = " +
							(costo1 - descuento) + " WHERE IDFACTURA = " + idE);
					s3.executeUpdate ("update factura set COSTO_TOTAL = " +
							(costo3 - descuento) + " where "
							+ "ID_EXPORTADOR = " + idE);
				}

				if(!rset2.getString(5).equals("") && costo3 != 0 && costo1 == 0)
				{
					total = costo2 + costo3;
					descuento = 300/total;
					System.out.println("Costo total facturado: " + (total-descuento));
					Statement s2 = conn2.createStatement();
					Statement s3 = conn3.createStatement();
					s2.executeUpdate ("update factura set COSTO = " +
							(costo2 - descuento) + " where "
							+ "ID_EXPORTADOR = '" + idExportador + 
							"' and (select nombre from "
							+ "exportador where id = '1') = '" + idNombre + "'");
					s3.executeUpdate ("update factura set COSTO_TOTAL = " +
							(costo3 - descuento) + " where "
							+ "ID_EXPORTADOR = " + idE);
				}

				if(costo1 != 0 && !rset2.getString(5).equals("") && costo3 != 0)
				{
					total = costo1 + costo2 + costo3;
					descuento = 500/total;
					System.out.println("Costo total facturado: " + (total-descuento));
					Statement s1 = conn1.createStatement();
					Statement s2 = conn2.createStatement();
					Statement s3 = conn3.createStatement();
					s1.executeUpdate ("UPDATE FACTURA SET TOTALPRECIO = " +
							(costo1 - descuento) + " WHERE IDFACTURA = " + idE);
					s2.executeUpdate ("update factura set COSTO = " +
							(costo2 - descuento) + " where "
							+ "ID_EXPORTADOR = '" + idExportador + 
							"' and (select nombre from "
							+ "exportador where id = '1') = '" + idNombre + "'");
					s3.executeUpdate ("update factura set COSTO_TOTAL = " +
							(costo3 - descuento) + " where "
							+ "ID = " + idE);
				}
			}	
			rset.close();
			rset = null;
			rset2.close();
			rset2 = null;

			stmt1.close();
			stmt1 = null;
			stmt2.close();
			stmt2 = null;

			// END both the branches -- THIS IS MUST
			oxar1.end(xid1, XAResource.TMSUCCESS);
			oxar2.end(xid2, XAResource.TMSUCCESS);
			oxar3.end(xid3, XAResource.TMSUCCESS);

			// Prepare the RMs
			int prp1 =  oxar1.prepare (xid1);
			int prp2 =  oxar2.prepare (xid2);
			int prp3 =  oxar3.prepare (xid3);

			boolean do_commit = true;

			if (!((prp1 == XAResource.XA_OK) || (prp1 == XAResource.XA_RDONLY)))
				do_commit = false;

			if (!((prp2 == XAResource.XA_OK) || (prp2 == XAResource.XA_RDONLY)))
				do_commit = false;

			if (!((prp3 == XAResource.XA_OK) || (prp3 == XAResource.XA_RDONLY)))
				do_commit = false;

			System.out.println("do_commit is " + do_commit);
			System.out.println("Is oxar1 same as oxar2 ? " + oxar1.isSameRM(oxar2));
			System.out.println("Is oxar1 same as oxar3 ? " + oxar1.isSameRM(oxar3));
			System.out.println("Is oxar2 same as oxar3 ? " + oxar2.isSameRM(oxar3));

			if (prp1 == XAResource.XA_OK)
				if (do_commit)
					oxar1.commit (xid1, false);
				else
					oxar1.rollback (xid1);

			if (prp2 == XAResource.XA_OK)
				if (do_commit)
					oxar2.commit (xid2, false);
				else
					oxar2.rollback (xid2);

			if (prp3 == XAResource.XA_OK)
				if (do_commit)
					oxar3.commit (xid3, false);
				else
					oxar3.rollback (xid3);

			// Close connections
			conn1.close();
			conn1 = null;
			conn2.close();
			conn2 = null;
			conn3.close();
			conn3 = null;

			pc1.close();
			pc1 = null;
			pc2.close();
			pc2 = null;
			pc3.close();
			pc3 = null;

			stmta.close();
			stmta = null;
			stmtb.close();
			stmtb = null;
			stmtc.close();
			stmtc = null;

			conna.close();
			conna = null;
			connb.close();
			connb = null;
			connc.close();
			connc = null;

		} catch (SQLException sqe)
		{
			sqe.printStackTrace();
		} catch (XAException xae)
		{
			if (xae instanceof OracleXAException) {
				System.out.println("XA Error is " +
						((OracleXAException)xae).getXAError());
				System.out.println("SQL Error is " +
						((OracleXAException)xae).getOracleError());
			}
		}
	}
}