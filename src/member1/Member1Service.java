package member1;

import java.util.ArrayList;
import java.util.Scanner;

public class Member1Service {
	private Member1Dao dao;

	public static String loginId = null;

	public Member1Service() {
		dao = new Member1Dao();
	}

	// id중복체크
	public boolean checkId(String id) {
		return dao.select(id) == null;
	}

	// 로그인
	public void login(Scanner sc) {
		System.out.println(" -----------------------");
		System.out.println("|          로그인        |");
		System.out.println(" -----------------------");

		System.out.print("| 아이디  ► ");
		String id = sc.next();
		System.out.print("| 비밀번호 ► ");
		String pwd = sc.next();
//		System.out.println();

		Member1 vo = dao.select(id);
		if (vo == null) {
			System.out.println("✖︎ 존재하지 않는 아이디입니다 ✖︎");
//			System.out.println();
		} else {
			if (pwd.equals(vo.getPwd())) {
				System.out.println("● 로그인에 성공했습니다 ●");
//				System.out.println();
				loginId = vo.getId();
			} else {
				System.out.println("✖︎ 비밀번호가 일치하지 않습니다 ✖︎");
			}
		}
	}

	// 로그아웃
	public void logout() {
		System.out.println("● 로그아웃 완료 ●");
//		System.out.println();
		if (loginId == null) {
			System.out.println("==[로그인을 먼저 하세요]==");
			return;
		} else {
			loginId = null;
		}
	}

	// 가입
	public void join(Scanner sc) {
		boolean flag = false;
		String id = null;

		System.out.println("-------------");
		System.out.println("    회원가입    ");
		System.out.println("-------------");

		while (!flag) {
			System.out.print("아이디: ");
			id = sc.next();
			flag = checkId(id);
			if (!flag) {
				System.out.println("==[이미 존재하는 ID입니다. 다른 ID를 입력해주세요]==");
			}

		}
		System.out.print("비밀번호: ");
		String pwd = sc.next();
		System.out.print("이름: ");
		String name = sc.next();
		System.out.print("이메일: ");
		String email = sc.next();
		System.out.print("전화번호: ");
		String tel = sc.next();
		dao.insert(new Member1(id, pwd, name, email, tel));
	}

	// 내정보확인
	public void printMyInfo() {
		System.out.println("-------------");
		System.out.println("   내정보확인    ");
		System.out.println("-------------");

		if (loginId == null) {
			System.out.println("==[로그인을 먼저 하십시오]==");
			return;
		} else {
			Member1 vo = dao.select(loginId);
			System.out.println(vo);
		}
	}

	// 내정보수정
	public void editMyInfo(Scanner sc) {
		System.out.println("-------------");
		System.out.println("   내정보수정    ");
		System.out.println("-------------");

		if (loginId == null) {
			System.out.println("==[로그인을 먼저 하십시오]==");
			return;
		}
		System.out.print("새로운 비밀번호: ");
		String pwd = sc.next();
		System.out.print("새로운 이름: ");
		String name = sc.next();
		System.out.print("새로운 이메일: ");
		String email = sc.next();
		System.out.print("새로운 전화번호: ");
		String tel = sc.next();
		dao.update(new Member1(loginId, pwd, name, email, tel));
	}

	// 탈퇴
	public void out() {
		if (loginId == null) {
			System.out.println("==[로그인을 먼저 하십시오]==");
			return;
		}
		dao.delete(loginId);
		loginId = null;
		System.out.println("-------------");
		System.out.println("   탈퇴 완료    ");
		System.out.println("-------------");
	}

	// (관리자) 회원 삭제
	public void adminDelete(Scanner sc) {

		System.out.print("삭제할 회원ID: ");
		String id = sc.next();
		Member1 vo = dao.select(id);
		if (vo == null) {
			System.out.println("==[입력하신 ID로 검색된 회원이 없습니다]==");
		} else {
			dao.delete(id);
			System.out.println("-------------");
			System.out.println("  회원 삭제 완료  ");
			System.out.println("-------------");
		}
	}

	// (관리자)전체회원목록 출력
	public void printAll() {
		System.out.println("-------------");
		System.out.println("  전체 회원 목록  ");
		System.out.println("-------------");
		ArrayList<Member1> list = dao.selectAll();
		for (Member1 vo : list) {
			System.out.println(vo);
		}
	}

	// (관리자)회원정보 검색
	public void printMemberInfo(Scanner sc) {
		System.out.println("-------------");
		System.out.println("  회원 정보 검색  ");
		System.out.println("-------------");

		System.out.print("검색할 회원ID: ");
		String id = sc.next();
		Member1 vo = dao.select(id);
		if (vo == null) {
			System.out.println("==[입력하신 ID로 검색된 회원이 없습니다]==");
		} else {
			System.out.println(vo);
		}
	}
}
