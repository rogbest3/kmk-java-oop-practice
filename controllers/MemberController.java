package com.bitcamp.controllers;
import javax.swing.JOptionPane;
import com.bitcamp.domains.MemberBean;
import com.bitcamp.services.MemberService;

import sun.print.resources.serviceui;
public class MemberController {

	public static void main(String[] args) {
		MemberBean member = null;
		MemberService service = new MemberService();
		MemberBean[] members = null;
		String temp = "";
		String[] arr = null;
		
		while(true) {
			
			switch (JOptionPane.showInputDialog("0.종료\n"
					+ "1.회원가입\n"
					+ "2.마이페이지\n"
					+ "3.비번 수정\n"
					+ "4.회원 탈퇴\n"
					+ "5.아이디 체크\n"
					+ "6.로그인\n"
					+ "01.회원 목록\n"
					+ "02.아이디 검색\n"
					+ "03.이름 검색\n"
					+ "04.회원수")) {
			case "0" :
				JOptionPane.showMessageDialog(null, "종료");
				return;

			case "1" :
				arr = JOptionPane.showInputDialog("이름, 아이디, 비번, 주민번호, 혈액형, 키, 몸무게").split(",");
				member = new MemberBean();
				member.setName(arr[0]);
				member.setId(arr[1]);
				member.setPw(arr[2]);
				member.setSsn(arr[3]);
				member.setBlood(arr[4]);
				member.setHeight(Double.parseDouble(arr[5]));
				member.setWeight(Double.parseDouble(arr[6]));
				JOptionPane.showMessageDialog(null, service.join(member));
			/**		
				for(int i=0; i<4; i++) {
					member = new MemberBean();
					member.setName("aa" + i);
					member.setId("aa" + i);
					member.setPw("123" + i);
					member.setSsn("123456-" + i);
					member.setBlood("A");
					member.setHeight(178.5);
					member.setWeight(65.0);
					JOptionPane.showMessageDialog(null, service.join(member));
				}
				member = new MemberBean();
				member.setName("aa2");
				member.setId("aa4");
				member.setPw("1234");
				member.setSsn("123456-4");
				member.setBlood("A");
				member.setHeight(178.5);
				member.setWeight(65.0);
				JOptionPane.showMessageDialog(null, service.join(member));
			*/
				break;
			case "2" :
				JOptionPane.showMessageDialog(null, service.getMyPage(member));
				break;
			case "3" :
				arr = JOptionPane.showInputDialog("아이디, 기존 비번, 신규 비번").split(",");
				member = new MemberBean();
				member.setId(arr[0]);
				member.setPw(arr[1] + "," + arr[2]);
				
				JOptionPane.showMessageDialog(null, service.changePassword(member));
				break;
			case "4" :
				arr = JOptionPane.showInputDialog("아이디, 비번").split(",");
				member = new MemberBean();
				member.setId(arr[0]);
				member.setPw(arr[1]);
				JOptionPane.showMessageDialog(null, service.withdrawal(member));
				break;
			case "5" :
				JOptionPane.showMessageDialog(null, service.exitId(JOptionPane.showInputDialog("아이디")));
				break;
			case "6" :
				arr = JOptionPane.showInputDialog("아이디, 비번").split(",");
				member = new MemberBean();
				member.setId(arr[0]);
				member.setPw(arr[1]);
				JOptionPane.showMessageDialog(null, service.login(member));
				break;
			case "01" :
				JOptionPane.showMessageDialog(null, service.list());
				break;
			case "02" :
				JOptionPane.showMessageDialog(null, service.findById(JOptionPane.showInputDialog("아이디")));
				break;
			case "03" :
				member = new MemberBean();
				members = new MemberBean[10];
				
				member.setName(JOptionPane.showInputDialog("이름"));
				members = service.findByName(member);
				System.out.println(service.sameNameCount());
				temp = "";
				for(int i=0; i<service.sameNameCount(); i++) {
					temp += String.format(members[i] + "\n");
				}
				
				JOptionPane.showMessageDialog(null, temp);
				break;
			case "04" :
				JOptionPane.showMessageDialog(null, String.format("회원수는 %d명입니다.",service.countAll()));
				break;
				
			}
		}
	}

}
