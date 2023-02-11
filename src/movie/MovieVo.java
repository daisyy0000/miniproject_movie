package movie;

//TODO: 연관관계 매핑 해보기!!
//연관관계 매핑 !-> FK를 가지고있는 테이블이 주인이고, 양쪽에서 연관관계 메소드를 정의해주어야한다.  (양방향)
//그래야 FK를 통해 자동으로 값이 연관 관계를 형성한다.

public class MovieVo {
	private int movieId;
	private String managing;
	private int theaterN;
	private String title;
	private String runTime;
	private String releaseD; //개봉일
	private String endD; //종영일
	private String runD; //상영일
	private String startT;
	private String finishT;
	
	MovieVo () {}

	public MovieVo(int movieId, String managing, int theaterN, String title, String runTime, String releaseD, String endD, String runD, String startT,
			String finishT) {
		this.movieId = movieId;
		this.managing = managing;
		this.theaterN = theaterN;
//		this.theaterId = theaterId;
		this.title = title;
		this.runTime = runTime;
		this.releaseD = releaseD;
		this.endD = endD;
		this.runD = runD;
		this.startT = startT;
		this.finishT = finishT;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public String getManaging() {
		return managing;
	}
	
	public void setManaging(String managing) {
		this.managing = managing;
	}
	
	public int getTheaterN() {
		return theaterN;
	}

	public void setTheaterN(int theaterN) {
		this.theaterN = theaterN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public String getReleaseD() {
		return releaseD;
	}

	public void setReleaseD(String releaseD) {
		this.releaseD = releaseD;
	}

	public String getEndD() {
		return endD;
	}

	public void setEndD(String endD) {
		this.endD = endD;
	}
	
	public String getRunD() {
		return runD;
	}

	public void setRunD(String runD) {
		this.runD = runD;
	}

	public String getStartT() {
		return startT;
	}

	public void setStartT(String startT) {
		this.startT = startT;
	}

	public String getFinishT() {
		return finishT;
	}

	public void setFinishT(String finishT) {
		this.finishT = finishT;
	}

	@Override
	public String toString() {
		return "영화번호:" + movieId + "| 영화제목:" + title + "| 러닝타임:" + runTime
				+ "| 개봉일:" + releaseD + "| 종영일:" + endD + "| 상영일:" + runD + "| 시작시간:" + startT + "| 끝나는시간:" + finishT
				+ "|";
	}
}
