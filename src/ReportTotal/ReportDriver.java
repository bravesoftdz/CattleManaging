package ReportTotal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class ReportDriver {

   
   public ReportDriver() {
   }

  
   public static Connection connectDB(String databaseName, String userName, String password) {
      Connection jdbcConnection = null;
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         jdbcConnection = DriverManager.getConnection(databaseName,userName,password);
      }catch(Exception ex) {
         String connectMsg = "Could not connect to the database: " + ex.getMessage() + " " + ex.getLocalizedMessage();
         System.out.println(connectMsg);
      }
      return jdbcConnection;
   }

    
   public static void runReport(String databaseName, String userName, String password,String reportFile) {
	   
     runReport(databaseName, userName, password, reportFile,null);
     
   }
   public static void runReport(String databaseName, String userName, String password,String reportFile,Map<String, ?> parameterReport) {
	   
	      try{
	         JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
	         JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	         Connection jdbcConnection = connectDB(databaseName, userName, password);
	         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterReport, jdbcConnection);
	         JasperViewer.viewReport(jasperPrint, false);
	      }catch(Exception ex) {
	         String connectMsg = "Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
	         System.out.println(connectMsg);
	      }
	   }
  
   
   public static void main(String[] args) {
      if (args.length == 4) {
         String databaseName = args[0] ;
         String userName = args[1];
         String password = args[2];
         String reportFile = args[3];
         runReport(databaseName, userName, password, reportFile);
      }/*else{
         runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456","E:\\Report Backup\\Report Cow For Zayesh\\ReportCowForZayesh.jrxml");
      }*/
      return;
   }
}