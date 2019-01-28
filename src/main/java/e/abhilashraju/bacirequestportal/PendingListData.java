package e.abhilashraju.bacirequestportal;

import java.io.Serializable;

public class PendingListData implements Serializable {

    private String req_type;
    private String req_desc;
    private String acc_num;
    private boolean completed;

    public PendingListData(String req_type, String req_desc, String acc_num, boolean status) {
        this.req_type = req_type;
        this.req_desc = req_desc;
        this.acc_num = acc_num;
        this.completed = status;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompletedStatus(boolean status) {
        this.completed = status;
    }

    public String getReq_type() {
        return req_type;
    }

    public void setReq_type(String req_type) {
        this.req_type = req_type;
    }

    public String getReq_desc() {
        return req_desc;
    }

    public void setReq_desc(String req_desc) {
        this.req_desc = req_desc;
    }

    public String getAcc_num() {
        return acc_num;
    }

    public void setAcc_num(String acc_num) {
        this.acc_num = acc_num;
    }
}
