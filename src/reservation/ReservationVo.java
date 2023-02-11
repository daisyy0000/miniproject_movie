package reservation;

public class ReservationVo {
	private int ticketN;
	private int movieId;
	private String memberId;
	private int theaterN;
	private String runD; //상영일?
	private String startT; //시작시간
	private String rowN; //행
	private int seatN; //열
	
	public ReservationVo() {}
	
	public ReservationVo(int ticketN, int movieId, String memberId, int theaterN, String runD, String startT, String rowN, int seatN) {
		this.ticketN = ticketN;
		this.movieId = movieId;
		this.memberId = memberId;
		this.theaterN = theaterN;
		this.runD = runD;
		this.startT = startT;
		this.rowN = rowN;
		this.seatN = seatN;
	}

	public int getTicketN() {
		return ticketN;
	}
	public void setTicketN(int ticketN) {
		this.ticketN = ticketN;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public int getTheaterN() {
		return theaterN;
	}

	public void setTheaterN(int theaterN) {
		this.theaterN = theaterN;
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
	public String getRowN() {
		return rowN;
	}
	public void setRowN(String rowN) {
		this.rowN = rowN;
	}
	public int getSeatN() {
		return seatN;
	}
	public void setSeatN(int seatN) {
		this.seatN = seatN;
	}

	@Override
	public String toString() {
		return "========== 영화 티켓 ==========" + "\n" + "    티켓 번호: " + ticketN + "\n"
				+ "    상영관번호: " + theaterN + "\n"
				+ "    상영일: " + runD + "\n" + "    상영 시작 시간: " + startT + "\n"
				+ "    좌석: " + rowN + "열" + seatN + "번" + "\n"
				+ "=============================";
	}
	
}
