import java.sql.*;
import java.util.Scanner;

public class Demo {





	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/harman","root","");
			Statement statement = connection.createStatement();

			System.out.println("assingnment table structure is as ");
			System.out.println("Id    Name    Age   Address");

		
			System.out.println();
			System.out.println();
			System.out.println("For inserting data in assignment1 table enter value '1' ");
			System.out.println("For deleting data in assignment1 table enter value '2'");
			System.out.println("For Searching the data in assignment1 table enter value '3'");

			Scanner cin = new Scanner(System.in);
			int value = cin.nextInt();

			String val = "n";
			do {
				if(val.equals("y"))
				{
					System.out.println("enter value as 1,2,3 for particular operations ");
					value = cin.nextInt();
				}
				if(value == 1)
				{
					val = insertingFunction(connection);
				}
				else if(value == 2)
				{
					val = deletingFunction(connection, statement);
				}
				else if(value == 3)
				{
					val = searchingFunction(connection, statement);
				}
				else
				{
					return;
				}

			}
			while(val.equals("y"));


			statement.close();
			connection.close();


		}
		catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static String insertingFunction(Connection connection) throws Exception
	{

		System.out.println("enter values of id, name, address and age");
		Scanner cin = new Scanner(System.in);

		String arr[] = new String[4];
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = cin.nextLine();
		}
		int id = Integer.parseInt(arr[0]);
		int age = Integer.parseInt(arr[3]);
		String name = arr[1];
		String address = arr[2];

		String sqlCond = "insert into assignment1 " + " (Id, Name, Address, Age)" + " values (?, ?, ?, ?)";

		PreparedStatement preparedStmt = connection.prepareStatement(sqlCond);
		preparedStmt.setInt(1, id);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, address);
		preparedStmt.setInt(4, age);

		preparedStmt.executeUpdate();
		System.out.println();
		System.out.println();
		System.out.println("table updated successfully");

		System.out.println("if you want to do some more operation enter 'y' ");
		String val = cin.nextLine();

		return val;

	}

	public static String searchingFunction(Connection connection,Statement statement) throws Exception
	{
		try {
			System.out.println("enter the condition for searching after where ");
			Scanner cin = new Scanner(System.in);
			String condition = cin.nextLine();
			ResultSet resultSet = statement.executeQuery("select * from assignment1 where " + condition);
			System.out.println("table after executing the query is ");
			while(resultSet.next()  == true)
			{

				int id = resultSet.getInt("Id");
				String name = resultSet.getString("Name");
				String add = resultSet.getString("Address");
				String age = resultSet.getString("Age");


				System.out.println(id + " " + name + " " + add + " " + age);



			}

			System.out.println();
			System.out.println();
			
			System.out.println();
			System.out.println("if you want to do some more operation enter 'y' ");
			String val = cin.nextLine();

			return val;
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("if you want to do some more operation enter 'y' ");
			Scanner cin = new Scanner(System.in);
			String val = cin.nextLine();

			return val;
		}
	}

	public static String deletingFunction(Connection connection,Statement statement) throws Exception {
		try {
			System.out.println("enter the condition after where ");
			Scanner cin = new Scanner(System.in);
			String condition = cin.nextLine();

			PreparedStatement preparedStmt = connection.prepareStatement("Delete from assignment1 where " + condition);
			preparedStmt.execute();
			System.out.println("table after executing the query is ");
			ResultSet resultSet = statement.executeQuery("Select * from assignment1");
			while(resultSet.next()  == true)
			{

				int id = resultSet.getInt("Id");
				String name = resultSet.getString("Name");
				String add = resultSet.getString("Address");
				String age = resultSet.getString("Age");


				System.out.println(id + " " + name + " " + add + " " + age);



			}

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("if you want to do some more operation enter 'y' ");
			String val = cin.nextLine();

			return val;
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("if you want to do some more operation enter 'y' ");
			Scanner cin = new Scanner(System.in);

			String val = cin.nextLine();

			return val;
		}
	}

}
