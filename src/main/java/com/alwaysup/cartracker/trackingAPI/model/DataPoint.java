package com.alwaysup.cartracker.trackingAPI.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
public class DataPoint {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(AccessLevel.PACKAGE)
	private long id;
	@ManyToOne
	@JoinColumn(name="device_imei")
	private Device device;
	/**
	 * Location/Temperature/Humidity
	 * 000 -> 0 (none)
	 * 110 -> 6 (Location & Temperature)
	 */
	private int dataType;
	private float x, y;
	private float temp;
	private float humidity;
	private Date timestamp;
	public DataPoint(Builder builder) {
		this.x = builder.x;
		this.y = builder.y;
		this.temp = builder.temp;
		this.humidity = builder.humidity;
		this.device = builder.device;
		this.dataType = builder.dataType;
		this.timestamp = new Date();
	}
	public static class Builder {
		private Device device;
		private int dataType;
		private float x, y;
		private float temp;
		private float humidity;

		private Builder() {}

		public Builder setDevice(Device device) {
			this.device = device;
			return this;
		}

		public Builder setDataType(int dataType) {
			this.dataType = dataType;
			return this;
		}

		public Builder setLocation(float x, float y) {
			this.x = x;
			this.y = y;
			return this;
		}

		public Builder setTemperature(float temp) {
			this.temp = temp;
			return this;
		}

		public Builder setHumidity(float humidity) {
			this.humidity = humidity;
			return this;
		}

		public Builder setHumidity(float humidity) {
			this.humidity = humidity;
			return this;
		}
	}
}
