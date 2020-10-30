// Download Java Driver: http://search.maven.org/#artifactdetails|org.neo4j.driver|neo4j-java-driver|1.0.0|jar

import javax.swing.*;

import org.neo4j.driver.v1.*;
import static org.neo4j.driver.v1.Values.parameters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;


public class BlankSandbox {
	
	/*
	THIS INFO IS TO ACCESS A SPECIFIC CLOUD DATABASE AND IS SUBJECT TO CHANGE UPON EXPIRATION/USING YOUR OWN CLOUD SANDBOX
	REPLACE VIA TAB UNDER NEO4J SANDBOX IN PROJECT LIST
	SELECT JAVA FROM LANGUAGES
	COPY AND PASTE FIRST 2 LINES UNDER MAIN()
	 */
	//BEGIN DB INFO
	public static Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();
	public static Driver driver = GraphDatabase.driver("bolt://100.25.200.249:32884",AuthTokens.basic("neo4j","exits-girls-runout"),noSSL); // <password>
	//END DB INFO
	
	public static JLabel l;
	
    public static void main(String...args) {
    	
    	JFrame a = new JFrame("Basic Example");
    	a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JButton b = new JButton("Get Results");
    	JPanel p = new JPanel(); 
    	final JLabel l = new JLabel();
    	l.setText("Query Results Here");
    	b.setBounds(40,90,85,20);
    	p.add(l);
    	p.add(b);
    	a.add(p);
    	a.setSize(300,300);
    	a.setVisible(true);
    	
        b.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				String results = "";			
		    	try (Session session = driver.session()) {
		            String cypherQuery =
		                "MATCH (n) " + 
		                "RETURN id(n) AS id";
		            StatementResult result = session.run(cypherQuery, parameters());
		            while (result.hasNext()) {
		                results += result.next().get("id") + " ";
		            }
		        }
		    	l.setText(results);
		    	System.out.println(results);
			}
        });
       
    }
 
    
}

