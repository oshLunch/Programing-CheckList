package Test01;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SendMessage extends JFrame {

	private JLabel la1, la2;
	private JTextField tf1, tf2;
	private JButton btn;
	private Container c;

	public SendMessage() {

		GridLayout g = new GridLayout(3, 2);
		la1 = new JLabel("전화번호");
		tf1 = new JTextField();
		la2 = new JLabel("메세지");
		tf2 = new JTextField();
		btn = new JButton("전송");

		c = getContentPane();
		c.setLayout(g);

		c.add(la1);
		c.add(tf1);
		c.add(la2);
		c.add(tf2);
		c.add(btn);

		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				String api_key = "NCS6EPNIHCEGKIBF";
			    String api_secret = "HCEJDD6R3MAANRV0W3JGICDBG5YICXDQ";
			    Message coolsms = new Message(api_key, api_secret);

			    // 4 params(to, from, type, text) are mandatory. must be filled
			    HashMap<String, String> params = new HashMap<String, String>();
			    params.put("to", "01022485247");
			    params.put("from", tf1.getText());
			    params.put("type", "SMS");
			    params.put("text", tf2.getText());
			    params.put("app_version", "test app 1.2"); // application name and version

			    try {
			      JSONObject obj = (JSONObject) coolsms.send(params);
			      System.out.println("메세지가 전송되었습니다");
			      System.out.println(obj.toString());
			      // 1번 : obj(문자열) -> 자바 오브젝트
			      // 2번 : error가 있는지 확인
			      // 3번 : DB insert
			    } catch (CoolsmsException e1) {
			      System.out.println(e1.getMessage());
			      System.out.println(e1.getCode());
			    }
			}
		});

		setTitle("");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SendMessage();
	}
}

