package BE;

/**
 *
 * @author Shadowleet
 */
public class BEError {
    private String error;
    private String course;
    
    public BEError(String error, String course) {
       
        this.error = error;
        this.course = course;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }
    
}
