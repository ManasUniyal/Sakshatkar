package Main;

import Utilities.SqlQueryExecuter;

public class Main {

	public static SqlQueryExecuter SQLQueryExecuter;

	public static void main(String args[]) {

		SQLQueryExecuter = new SqlQueryExecuter("root","root","jdbc:mysql://localhost/sakshatkar");

		new Server().start();

	}

}