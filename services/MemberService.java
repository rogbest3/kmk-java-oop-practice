package com.bitcamp.services;
import com.bitcamp.domains.MemberBean;
/**
 * 요구사항(기능정의)
 * <사용자기능>
 * 1. 회원가입			join
 * 2. 마이페이지		getMyPage
 * 3. 비번 수정			changePassword
 * 4. 회원 탈퇴			withdrawal
 * 5. 아이디 존재 체크	exitId
 * 6. 로그인			login
 **************
 * <관리자기능>
 * 01. 회원 목록		list
 * 02. 아이디 검색		findById
 * 03. 이름 검색		findByName
 * 04. 전체 회원수		countAll
 */
public class MemberService {

	private MemberBean[] members;
	private int count;
	private int sameCount;
	public MemberService() {
		members = new MemberBean[10];
		count = 0;
		sameCount = 0;
	}
	
	public String join(MemberBean param) {
		String msg = "회원가입 완료";
		members[count] = param;
		count++;
		return msg;
	}
	
	public String getMyPage(MemberBean param) {
		
		return param.toString();
	}
	//	id, pw(기존, 신규) 입력 받아 id와 기존 pw가 맞으면 신규 pw로 변경
	public String changePassword(MemberBean param) {
		String msg = "비번 변경 실패";
		String[] arr = param.getPw().split(",");
		
		for(int i=0; i<count; i++) {
			if( param.getId().equals(members[i].getId()) 
				&& arr[0].equals(members[i].getPw()) ){
					members[i].setPw(arr[1]);
					msg = "비번 변경 완료";
					break;
				}
		}
		return msg;
	}
	// id, pw를 입력 받아 회원 탈퇴 -> 회원수를 줄이고 중간에 빈 삭제한 공간 앞으로 당기기
	public String withdrawal(MemberBean param) {
		String msg = "회원 탈퇴 실패";
		
		for(int i = 0; i<count; i++) {
			if(param.getId().equals(members[i].getId())
					&& param.getPw().equals(members[i].getPw())) {
				members[i] = new MemberBean();
				for(;i<count-1;i++) {
					members[i] = members[i+1];
				}
				count--;
				msg = "회원 탈퇴 성공";
				break;
			}
		}
		return msg;
	}

	public String exitId(String id) {
		String msg = "사용가능한 아이디입니다.";
		for(int i =0;i<count; i++) {
			if( id.equals(members[i].getId()) ) {
				msg = "이미 존재하는 아이디입니다.";
				break;
			}
		}
		
		return msg;
	}
	
	public String login(MemberBean param) {
		String msg = "로그인 실패";
		for(int i=0; i<count; i++) {
			if( param.getId().equals(members[i].getId())
					&& param.getPw().equals(members[i].getPw())) {
				msg = "로그인 성공";
				break;
			}
		}
		return msg;
	}
	public String list() {
		String list = "";
		for(int i=0; i<count; i++) {
			list += String.format(members[i].toString() + "\n");
				
		}
		return list;
	}
	
	public MemberBean findById(String id) {
		MemberBean member = new MemberBean();
		for(int i=0; i<count; i++) {
			if(id.equals(members[i].getId())) {
				member = members[i];
				break;
			}
		}
		return member;
	}
	
	public MemberBean[] findByName(MemberBean param) {
		int num = 0;
		for(int i=0;i<count; i++) {
			if(param.getName().equals(this.members[i].getName())) {
				num++;
			}
		}
		
		MemberBean[] members = new MemberBean[num];
		num = 0;
		for(int i=0; i<count; i++) {
			if(param.getName().equals(this.members[i].getName())) {
				members[num] = this.members[i]; 
				num++;
				if(members.length <= num) {
					sameCount = num;
					break;
				}
			}
			
		}
		return members;
	}
	public int sameNameCount() {
		return sameCount;
	}
	public int countAll() {
		return count;
	}
}
