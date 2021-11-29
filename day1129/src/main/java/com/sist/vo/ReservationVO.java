package com.sist.vo;

import java.util.Date;

public class ReservationVO {
	private String reservation_no;
	private String checkin;
	private String checkout;
	private String phone;
	private int total;
	private int count_guest;
	private int customer_no;
	private int room_no;
	private int eno;
	public ReservationVO(String reservation_no, String checkin,String checkout, String phone, int total, int count_guest,
			int customer_no, int room_no, int eno) {
		super();
		this.reservation_no = reservation_no;
		this.checkin = checkin;
		this.checkout = checkout;
		this.phone = phone;
		this.total = total;
		this.count_guest = count_guest;
		this.customer_no = customer_no;
		this.room_no = room_no;
		this.eno = eno;
	}
	public ReservationVO() {
		super();
	}
	public String getReservation_no() {
		return reservation_no;
	}
	public void setReservation_no(String reservation_no) {
		this.reservation_no = reservation_no;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount_guest() {
		return count_guest;
	}
	public void setCount_guest(int count_guest) {
		this.count_guest = count_guest;
	}
	public int getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(int customer_no) {
		this.customer_no = customer_no;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	
	
}
