

import java.util.*;

import com.memorysql.model.*;
import com.memorysql.model.constraint.*;
import com.memorysql.model.operator.EqualsOperator;

public class MainDriver {
public static void main(String[] args) {
	Database database = Database.getInstance("Company");  
	
    Table employee = database.createTable("Employee");
    Column id = employee.createColumn("id", Type.INTEGER);
    id.addCons(new IntegerConstraint());
    
    Column name = employee.createColumn("name", Type.STRING);
    name.addCons(new StringConstrain());
    
	Map <String,Object> record1 = new HashMap<String, Object>();
    record1.put("id", 101);
    record1.put("name", "Deepanshu");
	employee.insert(record1);

	
	Map <String,Object> record2 = new HashMap<String, Object>();

	record2.put("id", 102);
	record2.put("name", "Deepanshu");
	employee.insert(record2);
		
	Map <String,Object> record3 = new HashMap<String, Object>();

	record3.put("id", 103);
	record3.put("name", "Mahesh");

	employee.insert(record3);
	
	// SELECT * FROM EMPLOYEE WHERE NAME = DEEPANSHU
	System.out.println(database.Query("Employee","Deepanshu", "name", "*", new EqualsOperator()));
} 
}
