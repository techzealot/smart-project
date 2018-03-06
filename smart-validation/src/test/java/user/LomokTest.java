package user;

public class LomokTest {
public static void main(String[] args) {
	User u=new User();
	u.setId(9);
	System.out.println(u.toString());
	System.out.println(u.hashCode());
}
}
