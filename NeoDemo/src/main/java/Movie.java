import org.neo4j.ogm.annotation.*;

//@NodeEntity informs Neo4j that this object will need to be represented by a node in the resulting graph.
//The @NodeEntity annotation is used to declare that a POJO class is an entity backed by a node in the graph database
@NodeEntity
public class Movie {
	
	private int MovieID;
	
	@Property(name = "released")
	private int releasedYear;
	
	@Property(name = "tagline")
	private String tagline;
	
	@Property(name = "title")
	private String title;
	
	public Movie(int movieID, int released, String tagline, String title) {
		MovieID = movieID;
		this.releasedYear = released;
		this.tagline = tagline;
		this.title = title;
	}
	public int getMovieID() {
		return MovieID;
	}
	public void setMovieID(int movieID) {
		MovieID = movieID;
	}
	public int getReleased() {
		return releasedYear;
	}
	public void setReleased(int released) {
		this.releasedYear = released;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}