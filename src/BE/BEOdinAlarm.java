/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

public class BEOdinAlarm {
    private String title;
    private String description;
    private String time;
    public BEOdinAlarm(String title, String description, String time){
        this.title = title;
        this.description = description;
        this.time = time;
        
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }
    public String getTimeString(){
        String[] times = time.split(":");
        return times[1];
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return description + " - Tid: " + getTime();
    }
    

}
