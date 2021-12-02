
public class User {
private String UserName;
public synchronized String getUserName() {
	return UserName;
}
private double CurrentBalance;

 public User(String UserName, double CurrentBalance) {
	 this.UserName = UserName;
	 this.CurrentBalance = CurrentBalance;
 }



public synchronized double getCurrentBalance() {
	return CurrentBalance;
}
public synchronized void setCurrentBalance(double currentBalance) {
	CurrentBalance = currentBalance;
}


}
