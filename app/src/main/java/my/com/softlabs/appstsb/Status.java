package my.com.softlabs.appstsb;


public class Status {
    private String apply,stat,ent,bal;

    public Status(String apply,String stat,String ent,String bal)
    {
        this.setApply(apply);
        //this.setStart(start);
        //this.setEnd(end);
        this.setStat(stat);
        //this.setLeaveid(leaveid);
        this.setEnt(ent);
        this.setBal(ent);

    }


    public String getApply(int position) {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    /*public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }*/

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public String getBal() {
        return bal;
    }

    public void setBal(String bal) {
        this.bal = bal;
    }
}
