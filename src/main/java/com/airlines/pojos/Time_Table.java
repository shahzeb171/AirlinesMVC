package com.airlines.pojos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Time_Table {
@Id
int sno;
String fid;
String departure_ccode;
String arrival_ccode;
int price;
String arrival_time;
String departure_time;
public int getSno() {
	return sno;
}
public void setSno(int sno) {
	this.sno = sno;
}
public String getFid() {
	return fid;
}
public void setFid(String fid) {
	this.fid = fid;
}
public String getDeparture_ccode() {
	return departure_ccode;
}
public void setDeparture_ccode(String departure_ccode) {
	this.departure_ccode = departure_ccode;
}
public String getArrival_ccode() {
	return arrival_ccode;
}
public void setArrival_ccode(String arrival_ccode) {
	this.arrival_ccode = arrival_ccode;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getArrival_time() {
	return arrival_time;
}
public void setArrival_time(String arrival_time) {
	this.arrival_time = arrival_time;
}
public String getDeparture_time() {
	return departure_time;
}
public void setDeparture_time(String departure_time) {
	this.departure_time = departure_time;
}
@Override
public String toString() {
	return "Time_Table [sno=" + sno + ", fid=" + fid + ", departure_ccode=" + departure_ccode + ", arrival_ccode="
			+ arrival_ccode + ", price=" + price + ", arrival_time=" + arrival_time + ", departure_time="
			+ departure_time + "]";
}

}
