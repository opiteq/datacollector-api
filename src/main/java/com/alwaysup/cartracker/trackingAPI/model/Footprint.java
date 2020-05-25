package com.alwaysup.cartracker.trackingAPI.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
public class Footprint {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="id_generator")
	@SequenceGenerator(name="id_generator", sequenceName="FOOTPRINT_ID_SEQ")
    private int id;
	private String userid;
	private float xcoord, ycoord;
	private Date timestamp;
	public Footprint(String userid, int xcoord, int ycoord) {
		this.userid = userid;
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.timestamp = new Date();
	}
	public Footprint() {
		this.xcoord = 0;
		this.ycoord = 0;
		this.userid = "";
		this.timestamp = new Date();
	}
	public float getXcoord() {
		return xcoord;
	}
	public void setXcoord(float xcoord) {
		this.xcoord = xcoord;
	}
	public float getYcoord() {
		return ycoord;
	}
	public void setYcoord(float ycoord) {
		this.ycoord = ycoord;
	}
	public String getuserid() {
		return userid;
	}
	public void setuserid(String userid) {
		this.userid = userid;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	@Override
    public String toString() {
        return "Footprint \n\t[id:" + this.id + "\n\tCoordinates: (X:" + this.xcoord + ", Y:" + this.ycoord + ")\n\ttimestamp:" + this.timestamp
                + "\t\n]";
    }
}
