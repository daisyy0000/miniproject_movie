//package movie;
//
//import java.util.Scanner;
//
//public class Menu {
//	private MovieService mvService;
//	
//	public Menu() {
//		mvService = new MovieService();
//	}
//	
//	public void mvrun(Scanner sc) {
//		boolean flag = true;
//		int m = 0;
//		while(flag) {
//			System.out.println("1.영화 등록 2.상영관으로 찾기 3.영화 제목으로 찾기 4.영화 리스트 출력"
//					+ " 5.영화 정보 수정 6.영화삭제 7.종료");
//			m = sc.nextInt();
//			switch(m) {
//			case 1:
//				mvService.create(sc);
//				System.out.println();
//				break;
//			case 2:
//				mvService.readByTheater(sc);
//				System.out.println();
//				break;
//			case 3:
//				mvService.readByTitle(sc);
//				System.out.println();
//				break;
//			case 4:
//				mvService.readAll(sc);
//				System.out.println();
//				break;
//			case 5:
//				mvService.readAll(sc);
//				mvService.update(sc);
//				System.out.println();
//				break;
//			case 6:
//				mvService.readAll(sc);
//				mvService.delete(sc);
//				System.out.println();
//				break;
//			case 7:
//				flag = false;
//				break;
//			}
//		}
//	}
//}
