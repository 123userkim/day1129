package com.sist.vo;

public class PossibleReservationRoomVO {
	private int room_no;
	private int fee;
	private String room_type;
	private String room_state;
	
	public PossibleReservationRoomVO(int room_no, int fee, String room_type, String room_state) {
		super();
		this.room_no = room_no;
		this.fee = fee;
		this.room_type = room_type;
		this.room_state = room_state;
	}
	public PossibleReservationRoomVO() {
		super();
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getRoom_state() {
		return room_state;
	}
	public void setRoom_state(String room_state) {
		this.room_state = room_state;
	}
	
	
	
}
