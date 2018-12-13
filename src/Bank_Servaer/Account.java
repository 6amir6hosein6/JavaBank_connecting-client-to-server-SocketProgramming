package Bank_Servaer;

/**
 * Created by Techno Service on 5/24/2018.
 */
public class Account {
    private String fname;
    private String lname;
    private String card_number;
    private String account_number;
    private float mojodi;
    private String password;
    private boolean access;
    public Account(String fname,String lname,String cardnumber ,String accountnumber ,float mojodi ,String password , boolean aceess) {
       this.fname=fname;
       this.lname=lname;
       this.card_number=cardnumber;
       this.account_number=accountnumber;
       this.mojodi=mojodi;
       this.password=password;
       this.access=aceess;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public float getMojodi() {
        return mojodi;
    }

    public void setMojodi(float mojodi) {
        this.mojodi = mojodi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }


}
