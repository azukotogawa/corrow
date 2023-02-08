import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
public class Test {
	private static int timer = 1;
	
	 ActionListener isClose = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			System.out.println("Hello");
		}
	      };
	
	public static void main(String[] args){
		new Test();
	}
	public Test(){
		new Timer(timer, isClose).start();
	}
}
