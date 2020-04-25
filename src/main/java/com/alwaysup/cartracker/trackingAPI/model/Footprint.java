package com.alwaysup.cartracker.trackingAPI.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
public class Footprint {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
	private int xcoord, ycoord;
	private Date timestamp;
	public Footprint(int xcoord, int ycoord) {
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.timestamp = new Date();
	}
	public Footprint() {
		this.xcoord = 0;
		this.ycoord = 0;
		this.timestamp = new Date();
	}
	public int getXcoord() {
		return xcoord;
	}
	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}
	public int getYcoord() {
		return ycoord;
	}
	public void setYcoord(int ycoord) {
		this.ycoord = ycoord;
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
