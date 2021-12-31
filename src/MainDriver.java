

import java.util.*;

import com.memorysql.model.*;
import com.memorysql.model.constraint.*;
import com.memorysql.model.operator.EqualsOperator;
/*
 * Problem: Design an in-memory database using object-oriented principles.
 */

public class MainDriver {
public static void main(String[] args) {
   	Database database = Database.getInstance("company");
   	SQL sql = new SQL(database);
    Table employee = database.createTable("employee");
    Column id = employee.createColumn("id", Type.INTEGER);
    id.addCons(new IntegerConstraint());
    
    Column username = employee.createColumn("username", Type.STRING);
    username.addCons(new StringConstrain());
    username.addCons(new IsRequiredConstraint());
    
    Column password = employee.createColumn("password", Type.STRING);
    password.addCons(new StringConstrain());
    
    Column salary= employee.createColumn("salary", Type.INTEGER);
	sql.insert("INSERT INTO EMPLOYEE values(101,\"Deepanshu\",\"password\",1100);");
	sql.insert("INSERT INTO EMPLOYEE values(101,\"Deepanshu\",\"\");");

	database.getTable("employee").printAllRecords();
} 
}
