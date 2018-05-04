package my.com.softlabs.appstsb;

public class Config {
    //URL to our login.php file
    public static final String LOGIN_URL = "http://192.168.1.201:8080/appSTSB/login.php";
    public static final String URL_GET_STAFF = "http://192.168.1.201:8080/appSTSB/getStaff.php?staff_id=";
    public static final String URL_LEAVE_APP = "http://192.168.1.201:8080/appSTSB/leaveApplication.php?";
    //public static final String LOGIN_URL = "http://localhost:8080/appSTSB/login.php";
    //public static final String URL_GET_STAFF = "http://localhost:8080/appSTSB/getStaff.php?staff_id=";
    //public static final String URL_LEAVE_APP = "http://localhost:8080/appSTSB/leaveApplication.php?staff_id=";
    //public static final String LOGIN_URL = "http://10.0.2.2/appSTSB/login.php";
    //public static final String URL_GET_STAFF = "http://10.0.2.2/appSTSB/getStaff.php?staff_id=";
    public static final String URL_LEAVE_STAT = "http://192.168.1.201:8080/appSTSB/status.php?staff_id=";
    public static final String URL_LEAVE_STAT_APPROVE = "http://192.168.1.201:8080/appSTSB/approveStatus.php?dept=";
    public static final String URL_LEAVE_STAT_DETAIL = "http://192.168.1.201:8080/appSTSB/statusDetail.php?leave_id=";
    public static final String URL_LEAVE_APPROVE = "http://192.168.1.201:8080/appSTSB/leaveApprove.php?dept=";

    //public static final String LOGIN_URL = "http://192.168.43.26:8080/appSTSB/login.php";
    //public static final String URL_GET_STAFF = "http://192.168.43.26:8080/appSTSB/getStaff.php?staff_id=";
    //public static final String URL_LEAVE_APP = "http://192.168.43.26:8080/appSTSB/leaveApplication.php?";
    //public static final String URL_LEAVE_STAT = "http://192.168.43.26:8080/appSTSB/status.php?staff_id=";
    //public static final String URL_LEAVE_STAT_DETAIL = "http://192.168.43.26:8080/appSTSB/statusDetail.php?leave_id=";
    public static final String URL_LOGIN_CONT = "http://192.168.1.201:8080/appSTSB/login_add_cont.php?staff_id=";

    //Keys for staff_id and password as defined in our $_POST['key'] in login.php
    public static final String KEY_STAFFID = "staff_id";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_STAFF_NAME = "staff_name";
    public static final String KEY_IC_NO = "ic_no";
    public static final String KEY_CONFIRM_YEAR = "confirmation_year";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_LEAVE_ID = "leave_id";
    public static final String KEY_TODAY = "apply_date";
    public static final String KEY_START = "leave_start_date";
    public static final String KEY_END = "leave_end_date";
    public static final String KEY_TOTAL = "total_day_leave";
    public static final String KEY_REASON = "reason";
    public static final String KEY_REMARKS = "remarks";


    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_STAFF_ID = "staff_id";
    public static final String TAG_STAFF_NAME = "staff_name";
    public static final String TAG_IC_NO = "ic_no";
    public static final String TAG_CONFIRM_YEAR = "confirmation_year";
    public static final String TAG_GENDER = "gender";
    public static final String TAG_DESIG = "designation";

    public static final String TAG_LEAVE_ID = "leave_id";
    public static final String TAG_TODAY = "apply_date";
    public static final String TAG_START = "leave_start_date";
    public static final String TAG_END = "leave_end_date";
    public static final String TAG_TOTAL = "total_day_leave";
    public static final String TAG_REASON = "reason";
    public static final String TAG_REMARKS = "remarks";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the staff_id of current logged in user
    public static final String STAFFID_SHARED_PREF = "staff_id";
    public static final String DEPT_SHARED_PREF = "dept";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    //staff id to pass with intent
    public static final String STAFF_ID = "staff_id";
}
