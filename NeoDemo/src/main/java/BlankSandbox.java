// Download Java Driver: http://search.maven.org/#artifactdetails|org.neo4j.driver|neo4j-java-driver|1.0.0|jar

import javax.swing.*;

import org.neo4j.driver.v1.*;
import static org.neo4j.driver.v1.Values.parameters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Collections;
import java.util.List;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;


public class BlankSandbox {
	
	/*
	THIS INFO IS TO ACCESS A SPECIFIC CLOUD DATABASE AND IS SUBJECT TO CHANGE UPON EXPIRATION/USING YOUR OWN CLOUD SANDBOX
	REPLACE VIA DROPDOWN, CONNECT VIA DRIVERS TAB UNDER NEO4J SANDBOX IN PROJECT LIST
	SELECT JAVA FROM LANGUAGES
	COPY AND PASTE FIRST 2 LINES UNDER MAIN()
	 */
	//BEGIN DB INFO
    public static Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();
    public static Driver driver = GraphDatabase.driver("bolt://52.201.184.95:33664",AuthTokens.basic("neo4j","dockings-module-services"),noSSL); // <password>
	//END DB INFO
	   
    public static int maxDisplayed;
    
	public static JTextArea l;
	public static JTextArea exceptionDisplay;
	
    public static void main(String...args) {
    	
    	
    	//Set everything up, initialize
    	
    	//Create JFrames
    	JFrame jframeCreate = new JFrame("Basic Example");
    	jframeCreate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//Create TextAreas
    	
    	final JTextArea l = new JTextArea(20,40);
    	final JTextArea exceptionDisplay = new JTextArea(10,20);
    	
    	//Put TextAreas in Scrollpanes
    	
    	
    	JButton b = new JButton("Get Results");
    	JPanel p = new JPanel(); 
    	
    	l.setEditable(false);
    	
    	final JScrollPane resultsScrollPlane = new JScrollPane(l);
    	resultsScrollPlane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	l.setText("Query Results Here");
    	//b.setBounds(40,90,85,20);
    	p.add(resultsScrollPlane);
    	p.add(exceptionDisplay);
    	p.add(b);
    	jframeCreate.add(p);
    	jframeCreate.setSize(1000,1000);
    	jframeCreate.setVisible(true);
    	
    	
    	maxDisplayed = 50;
    	
    	//
        b.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList resultList = new ArrayList();
				String results = "";	
				int numDisplayed = 0;
		    	try (Session session = driver.session()) {
		            String cypherQuery = "Match (m:Movie) RETURN m.title";
		            StatementResult result = session.run(cypherQuery, parameters());
	            	
		            //While results remain, add them to a list
		            while (result.hasNext()) {
		                resultList.add( result.next().get("m.title") + "");
		                System.out.println(results);
		            }
		            
		            //Sort the list
		            Collections.sort(resultList);
		            
		            //for each in the list, add it to the result string
		            for (Object title : resultList) {
		            	results += title.toString() + "\r\n";
		            }
		          
		            //display the result string
		            l.setText(results);
		            System.out.print(results);
		            
		        }
		    	
		    	/*
		    	catch(Exception exception){
		    		errorDisplay(exception, l);
		    	}
		    	*/
			}
        });
       
    }
    
    private static void errorDisplay(Exception exception, JTextArea textarea) {
    	
    	String results = "";
    	results += "Error retrieving\r\n";
		results += exception.toString();
		textarea.setText(results);
		System.out.println(results);
		
    }
    
}

