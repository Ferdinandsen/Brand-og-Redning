package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BEError {

    private String error;
    private String course;
    private String outoforder;
    private String urgent;
    private String induetime;
    private String wash;

    public BEError(String error, String course, String outoforder, 
            String urgent, String induetime, String wash) {

        this.error = error;
        this.course = course;
        this.outoforder = outoforder;
        this.urgent = urgent;
        this.induetime = induetime;
        this.wash = wash;
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

    /**
     * @return the outoforder
     */
    public String getOutoforder() {
        return outoforder;
    }

    /**
     * @param outoforder the outoforder to set
     */
    public void setOutoforder(String outoforder) {
        this.outoforder = outoforder;
    }

    /**
     * @return the urgent
     */
    public String getUrgent() {
        return urgent;
    }

    /**
     * @param urgent the urgent to set
     */
    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    /**
     * @return the induetime
     */
    public String getInduetime() {
        return induetime;
    }

    /**
     * @param induetime the induetime to set
     */
    public void setInduetime(String induetime) {
        this.induetime = induetime;
    }

    /**
     * @return the wash
     */
    public String getWash() {
        return wash;
    }

    /**
     * @param wash the wash to set
     */
    public void setWash(String wash) {
        this.wash = wash;
    }
}
