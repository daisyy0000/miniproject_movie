package movie;

import java.util.ArrayList;
import java.util.Scanner;
import member1.Member1Service;
import theater.TheaterDao;
import theater.TheaterVo;

public class MovieService {
	private MovieDao dao;
	private TheaterDao tdao;

	public MovieService() {
		dao = new MovieDao();
		tdao = new TheaterDao();
	}

	public void create(Scanner sc) {
		System.out.println("======= 어드민모드: 영화 등록 =======");
		// TheaterId, title, runtime, releaseD, endD, startT, finishT
		//영화를 등록하려면 상영관이 먼저있어야되니깐 theater에서 불러와서 그게 비어있냐부터 확인하려고 한것.
		ArrayList<TheaterVo> list = tdao.selectAllTheater();
		if(list.isEmpty()) {
			System.out.println("등록된 상영관이 없습니다. 상영관 등록을 먼저 해주세요.");
		} else {
			for (TheaterVo vo : list) {
				System.out.println(vo);
			}
			System.out.print("상영 영화를 등록할 상영관의 번호를 입력해주세요.: ");
			int theaterN = sc.nextInt();
			
			System.out.print("영화 제목을 정확히 입력해주세요: ");
			sc.nextLine();
			String title = sc.nextLine();

			System.out.print("영화 러닝타임을 0h 0m 형식으로 입력해주세요.(1h 40m): ");
			String runTime = sc.nextLine();

			System.out.print("영화개봉일을 yyyy-mm-dd 형식으로 입력해주세요.: ");
			String releaseD = sc.next();

			System.out.print("영화종영일을 yyyy-mm-dd 형식으로 입력해주세요.: ");
			String endD = sc.next();
			
			System.out.print("영화상영일을 yyyy-mm-dd 형식으로 입력해주세요.: ");
			String runD = sc.next();

			System.out.print("영화 시작 시간을 00:00 형식으로 입력해주세요.(14:25): ");
			String startT = sc.next();

			System.out.print("영화 끝나는 시간을 00:00 형식으로 입력해주세요.(15:10): ");
			String finishT = sc.next();
			
			dao.insert(new MovieVo(0, Member1Service.loginId, theaterN, title, runTime, releaseD, endD, runD, startT, finishT));
//			sdao.insert(theaterId, runD, startT);
			System.out.print(title + " 영화등록 완료");
		}	
	}

	public boolean readByTheater(Scanner sc) {
		System.out.println("======= 상영관에 따른 영화 리스트 =======");
		ArrayList<TheaterVo> list = tdao.selectAllTheater();
		if(list.isEmpty()) {
			System.out.println("등록된 상영관이 없습니다. 상영관 등록을 먼저 해주세요.");
		} else {
			for (TheaterVo vo : list) {
				System.out.println(vo);
			} //서울, 경기 상영관 정보 어레이 리스트
			System.out.print("상영관을 선택해주세요(숫자입력): ");
			int theaterN = sc.nextInt();

			//ex) 서울관 입력받고 서울관에서 상영중인 영화 정보 어레이리스트
			ArrayList<MovieVo> list1 = dao.selectByTheater(theaterN);
			if(list1.isEmpty()) {
				return false;
			} else {
				for (MovieVo vo : list1) {
					System.out.println(vo);
				}
			}
		}
		//boolean타입으로해서 나중에 요긴하게 쓰일 예정
		return true;
	}
	
	public boolean readByTitle(Scanner sc) {
		System.out.println("======= 영화 제목으로 영화 정보 찾기 =======");
		System.out.print("영화 제목의 키워드를 입력해주세요.: ");
		sc.nextLine();
		String title = sc.nextLine();

		ArrayList<MovieVo> list = dao.selectByTitle(title);
		if (list.isEmpty()) {
			return false;
		} else {
			for (MovieVo vo : list) {
				System.out.println(vo);
			}
		}
		return true;
	}
	
	public void readAll() {
		System.out.println("======= 상영중인 영화 정보 보기 =======");
		ArrayList<MovieVo> list = dao.selectAll();
		if(list.isEmpty()) {
			System.out.println("현재 상영중인 영화가 없습니다.");
		} else {
			for (MovieVo vo : list) {
				System.out.println(vo);
			}
		}
	}

	public void update(Scanner sc) {
		System.out.println("======= 영화정보 수정하기 =======");
		System.out.print("영화에 할당된 번호: ");
		int movieId = sc.nextInt();

		MovieVo vo = dao.selectByNum(movieId);
		if(vo == null) {
			System.out.println("해당 번호의 영화가 없습니다.");
		} else {
			System.out.println(vo);
			System.out.print("new title: ");
			sc.nextLine();
			String title = sc.nextLine();
			dao.edit(new MovieVo(movieId, null, 0, title, null, null, null, null, null, null));
			System.out.println("영화 정보 수정 성공");
		}
	}

	public void delete(Scanner sc) {
		System.out.println("======= 영화 삭제하기 =======");
		System.out.print("삭제를 원하는 영화에 할당된 번호: ");
		int movieId = sc.nextInt();

		dao.deleteOne(movieId);
		System.out.println("영화 삭제 성공");
	}
}
