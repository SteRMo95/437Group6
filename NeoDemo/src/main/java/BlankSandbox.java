// Download Java Driver: http://search.maven.org/#artifactdetails|org.neo4j.driver|neo4j-java-driver|1.0.0|jar

import javax.swing.*;


import org.neo4j.driver.v1.*;
import static org.neo4j.driver.v1.Values.parameters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
    

	public static JTextArea exceptionDisplay;
	public static JTextArea queryInput;

	
	public static JTextField createNodeType;
	public static JTextField createNodeKey;
	public static JTextField createNodeValue;
	public static JTextField createNodeReturn;
	
	public static JTextField readNodeType;
	public static JTextField readNodeKey;
	public static JTextField readNodeValue;
	public static JTextField readNodeReturn;
	
	public static JTextField updateNodeType;
	public static JTextField updateNodeKey;
	public static JTextField updateNodeValue;
	public static JTextField updateNodeReturn;
	
	public static JTextField destroyNodeType;
	public static JTextField destroyNodeKey;
	public static JTextField destroyNodeValue;
	public static JTextField destroyNodeReturn;
	
    public static void main(String...args) {
    	
    	//Set everything up, initialize 
    	final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	
    	//Create overall application frame
    	JFrame overallJFrame = new JFrame("Movies Database Queries");
    	overallJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	overallJFrame.setLocationByPlatform(true);
    
    	
    	//Create buttons
    	JButton createButton = new JButton("Create Node");
    	JButton readButton = new JButton("Read Node");
    	JButton updateButton = new JButton("Update Node");
    	JButton destroyButton = new JButton("Destroy Node");
    	
    	JButton queryExecuteButton = new JButton("Arbitrary Query");   
    	
    	
    	//Create and setup TextAreas
    	final JTextArea resultsArea = new JTextArea(20,40);
    	resultsArea.setEditable(false);
    	resultsArea.setText("Query Results Here");
    	
    	final JTextArea exceptionDisplay = new JTextArea(4,3);
    	exceptionDisplay.setEditable(false);
    	exceptionDisplay.setText("Any Exceptions Here");
    	
    	//(Inputs for queries)
    	final JTextArea queryInput = new JTextArea(1,40);
    	
    	final JTextField createNodeType = new JTextField(1);
    	final JTextField createNodeKey = new JTextField(1);
    	final JTextField createNodeValue = new JTextField(1);
    	final JTextField createNodeReturn = new JTextField(1);
    	
    	final JTextField readNodeType = new JTextField(1);
    	final JTextField readNodeKey = new JTextField(1);
    	final JTextField readNodeValue = new JTextField(1);
    	final JTextField readNodeReturn = new JTextField(1);

    	final JTextField updateNodeType = new JTextField(1);
    	final JTextField updateNodeKey = new JTextField(1);
    	final JTextField updateNodeValue = new JTextField(1);
    	final JTextField updateNodeReturn = new JTextField(1);
    	
    	final JTextField destroyNodeType = new JTextField(1);
    	final JTextField destroyNodeKey = new JTextField(1);
    	final JTextField destroyNodeValue = new JTextField(1);
    	final JTextField destroyNodeReturn = new JTextField(1);
    	
    	
    	
    	//Put TextAreas in created panes, setup panes
    	final JScrollPane resultsScrollPane = new JScrollPane(resultsArea);
    	final JScrollPane exceptionScrollPane = new JScrollPane(exceptionDisplay);
    	
    	
    	resultsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	exceptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	
    	
    	//Create and setup Jpanel
    	JPanel queryPanel = new JPanel();
    	queryPanel.add(queryInput);
    	queryPanel.add(queryExecuteButton);   
   
    	JPanel guiPanel = new JPanel();
    	JPanel createPanel = new JPanel();
    	createPanel.add(new JLabel ("Node Type"));
    	createPanel.add(createNodeType);
    	createPanel.add(new JLabel ("Node Key"));
    	createPanel.add(createNodeKey);
    	createPanel.add(new JLabel ("Match Value"));
    	createPanel.add(createNodeValue);
    	createPanel.add(new JLabel ("Return Value"));
    	createPanel.add(createNodeReturn);
    	for (int i = 0; i < 12; i++) {
    		createPanel.add(new JLabel ("                                                               "));
    	}
    	createPanel.add(createButton);
    	
    	JPanel readPanel = new JPanel();
    	readPanel.add(new JLabel ("Node Type"));
    	readPanel.add(readNodeType);
    	readPanel.add(new JLabel ("Node Key"));
    	readPanel.add(readNodeKey);
    	readPanel.add(new JLabel ("Match Value"));
    	readPanel.add(readNodeValue);
    	readPanel.add(new JLabel ("Return Value"));
    	readPanel.add(readNodeReturn);
    	for (int i = 0; i < 12; i++) {
    		readPanel.add(new JLabel ("                                                               "));
    	}
    	readPanel.add(readButton);
    	
    	JPanel updatePanel = new JPanel();
    	updatePanel.add(new JLabel ("Node Type"));
    	updatePanel.add(updateNodeType);
    	updatePanel.add(new JLabel ("Node Key"));
    	updatePanel.add(updateNodeKey);
    	updatePanel.add(new JLabel ("Match Value"));
    	updatePanel.add(updateNodeValue);
    	updatePanel.add(new JLabel ("Return Value"));
    	updatePanel.add(updateNodeReturn);
    	for (int i = 0; i < 12; i++) {
    		updatePanel.add(new JLabel ("                                                               "));
    	}
    	updatePanel.add(updateButton);
    	
    	JPanel destroyPanel = new JPanel();
    	destroyPanel.add(new JLabel ("Node Type"));
    	destroyPanel.add(destroyNodeType);
    	destroyPanel.add(new JLabel ("Node Key"));
    	destroyPanel.add(destroyNodeKey);
    	destroyPanel.add(new JLabel ("Match Value"));
    	destroyPanel.add(destroyNodeValue);
    	destroyPanel.add(new JLabel ("Return Value"));
    	destroyPanel.add(destroyNodeReturn);
    	for (int i = 0; i < 12; i++) {
    		destroyPanel.add(new JLabel ("                                                               "));
    	}
    	destroyPanel.add(destroyButton);
    	
    	JPanel resultsPanel = new JPanel();
    	JPanel exceptionPanel = new JPanel();
    	
    	//Setup BoxLayout for panels
    	BoxLayout boxlayoutCreate = new BoxLayout(createPanel, BoxLayout.Y_AXIS); 
    	createPanel.setLayout(boxlayoutCreate); 
    	createPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	BoxLayout boxlayoutRead = new BoxLayout(readPanel, BoxLayout.Y_AXIS); 
    	readPanel.setLayout(boxlayoutRead); 
    	readPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	BoxLayout boxlayoutUpdate = new BoxLayout(updatePanel, BoxLayout.Y_AXIS); 
    	updatePanel.setLayout(boxlayoutUpdate); 
    	updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	BoxLayout boxlayoutDestroy = new BoxLayout(destroyPanel, BoxLayout.Y_AXIS); 
    	destroyPanel.setLayout(boxlayoutDestroy); 
    	destroyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	
    	//Set up tabbed pane for gui
    	JTabbedPane guiPane = new JTabbedPane();
    	guiPane.addTab("CREATE", createPanel);
    	guiPane.addTab("READ", readPanel);
    	guiPane.addTab("UPDATE", updatePanel);
    	guiPane.addTab("DESTROY", destroyPanel);
    	
    	
    	//Split panes
    	JSplitPane resultsSplitPane = new JSplitPane(SwingConstants.HORIZONTAL, resultsScrollPane, exceptionScrollPane);
    	JSplitPane topOverallSplitPane = new JSplitPane(SwingConstants.VERTICAL, guiPane, resultsSplitPane);
    	JSplitPane overallPane = new JSplitPane(SwingConstants.HORIZONTAL, topOverallSplitPane, queryPanel);
    	
    	
    	//Add components to panels
    	resultsPanel.add(overallPane);
    	
    	resultsArea.setText("Query Results Here");

    	overallJFrame.add(resultsPanel);
    	overallJFrame.setSize(1000,600);
    	overallJFrame.setVisible(true);
    	
    	
    	maxDisplayed = 50;
    	
    	//SET UP THE LISTENERS AND THEIR ASSOCIATED QUERIES
        createButton.addActionListener(new ActionListener() {
        	
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
		            
		            Date date = new Date();
		            date = new Date();
		            exceptionDisplay.setText("Completed CREATE successfully (" + formatter.format(date) + ")");
		            
		        }
		    	
		    	catch(Exception exception){
		    		errorDisplay(exception, exceptionDisplay);
		    	}
		    	
			}
        });
    	
        readButton.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList resultList = new ArrayList();
				String results = "";	
				int numDisplayed = 0;
		    	try (Session session = driver.session()) {
		            String cypherQuery = "Match (n";
		            if (readNodeType.getText().isBlank()) {
		            	cypherQuery += " {";
		            }
		            else {
		            	cypherQuery += ":" + readNodeType.getText() + " {";
		            }
		            if (!readNodeKey.getText().isBlank()) {
		            	cypherQuery += "" + readNodeKey.getText() + ":" + readNodeValue.getText() + "})";
		            }
		            else {
		            	cypherQuery += "})";
		            }
		            
		            cypherQuery += " RETURN ";
		            cypherQuery += "n." + readNodeReturn.getText();
		            
		            System.out.println("Running query: " + cypherQuery);
		            
		            StatementResult result = session.run(cypherQuery, parameters());
	            	
		            //While results remain, add them to a list
		            while (result.hasNext()) {
		                resultList.add( result.next().values()  + "");
		                //System.out.println(results);
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
		            
		            Date date = new Date();
		            date = new Date();
		            exceptionDisplay.setText("Completed READ successfully (" + formatter.format(date) + ")");
		            
		        }
		    	
		    	catch(Exception exception){
		    		errorDisplay(exception, exceptionDisplay);
		    	}
		    	
			}
        });
        
        updateButton.addActionListener(new ActionListener() {
        	
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
		            
		            Date date = new Date();
		            date = new Date();
		            exceptionDisplay.setText("Completed UPDATE successfully (" + formatter.format(date) + ")");
		        }
		    	
		    	catch(Exception exception){
		    		errorDisplay(exception, exceptionDisplay);
		    	}
		    	
			}
        });
        
        destroyButton.addActionListener(new ActionListener() {
        	
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
		            
		            Date date = new Date();
		            date = new Date();
		            exceptionDisplay.setText("Completed DESTROY successfully (" + formatter.format(date) + ")");
		            
		        }
		    	
		    	catch(Exception exception){
		    		errorDisplay(exception, exceptionDisplay);
		    	}
		    	
			}
        });
        
        queryExecuteButton.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList resultList = new ArrayList();
				String results = "";	
				int numDisplayed = 0;
				exceptionDisplay.setText("");
		    	try (Session session = driver.session()) {
		            String cypherQuery = queryInput.getText();
		            StatementResult result = session.run(cypherQuery, parameters());
	            	
		            //While results remain, add them to a list
		            while (result.hasNext()) {
		                resultList.add( result.next().values() + "");
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
		            
		            Date date = new Date();
		            date = new Date();
		            exceptionDisplay.setText("Completed successfully (" + formatter.format(date) + ")");
		            
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

