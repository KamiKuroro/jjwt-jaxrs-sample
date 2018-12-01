package com.db;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.globalkeys.KeyConstants;
import com.globalkeys.UserConstants;

public class CRUDOps 
{
	private Connection con;
	//Initialize Database connection
	private boolean Initialize() 
	{		
	    try 
		{
	    	Class.forName("com.mysql.jdbc.Driver");  
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth_jwt_sampledb","root","root");  
	    	if (con != null) 
			{
				return true;
			}
		}				
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
			
		
		return false;
	}
	
	public Boolean isUserPresent(String username, String password) {
		try {
			if(Initialize()) {
				String checkLoginSQL = "SELECT count(*) from user_master where user_name = ? and password = ?";
				PreparedStatement checkLoginStmt = con.prepareStatement(checkLoginSQL);
				checkLoginStmt.setString(1, username);
				checkLoginStmt.setString(2, password);
				System.out.println(checkLoginStmt);
				boolean hadResults = checkLoginStmt.execute();
				int is_present = 0;
				while (hadResults) 
				{
					ResultSet rs = checkLoginStmt.getResultSet();
					while(rs.next()) {
						is_present = rs.getInt(1);
						System.out.println("user found");
						break;
					}
					if(is_present==1) {
						return true;
					}
				}
				
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
}