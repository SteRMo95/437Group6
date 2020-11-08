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
	public static JTextArea queryInput;
	
    public static void main(String...args) {
    	
    	//Set everything up, initialize
    	
    	//Create JFrames
    	JFrame overallJFrame = new JFrame("Movies Database Queries");
    	overallJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//Create and setup TextAreas
    	final JTextArea resultsArea = new JTextArea(20,40);
    	resultsArea.setEditable(false);
    	resultsArea.setText("Query Results Here");
    	
    	final JTextArea exceptionDisplay = new JTextArea(4,3);
    	exceptionDisplay.setEditable(false);
    	exceptionDisplay.setText("Any Exceptions Here");
    	
    	final JTextArea queryInput = new JTextArea(1,40);
    	
    	//Put TextAreas in created Scrollpanes, setup scrollpanes
    	final JScrollPane resultsScrollPane = new JScrollPane(resultsArea);
    	final JScrollPane exceptionScrollPane = new JScrollPane(exceptionDisplay);
    	
    	
    	resultsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	exceptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	
    	//Create buttons
    	JButton readButton = new JButton("Read");
    	JButton queryExecuteButton = new JButton("Arbitrary Query");
    	
    	//Create and setup Jpanel
    	JPanel guiPanel = new JPanel();
    	guiPanel.add(readButton);
    	JPanel resultsPanel = new JPanel();
    	JPanel exceptionPanel = new JPanel();
    	JPanel queryPanel = new JPanel();
    	queryPanel.add(queryInput);
    	queryPanel.add(queryExecuteButton);
    	
    	//Split panes
    	JSplitPane resultsSplitPane = new JSplitPane(SwingConstants.HORIZONTAL, resultsScrollPane, exceptionScrollPane);
    	JSplitPane topOverallSplitPane = new JSplitPane(SwingConstants.VERTICAL, guiPanel, resultsSplitPane);
    	JSplitPane overallPane = new JSplitPane(SwingConstants.HORIZONTAL, topOverallSplitPane, queryPanel);
    	
    	//Add components to panels
    	resultsPanel.add(overallPane);
    	
    	resultsArea.setText("Query Results Here");

    	overallJFrame.add(resultsPanel);
    	overallJFrame.setSize(1000,500);
    	overallJFrame.setVisible(true);
    	
    	
    	maxDisplayed = 50;
    	
    	//
        readButton.addActionListener(new ActionListener() {
        	
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
		            resultsArea.setText(results);
		            System.out.print(results);
		            
		        }
		    	
		    	catch(Exception exception){
		    		errorDisplay(exception, exceptionDisplay);
		    	}
		    	
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

