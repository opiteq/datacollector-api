package com.alwaysup.cartracker.trackingAPI.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.ArrayUtils;

import org.springframework.beans.factory.annotation.Autowired;

import com.alwaysup.cartracker.trackingAPI.model.Footprint;
import com.alwaysup.cartracker.trackingAPI.repository.FootprintRepository;

public class FootprintService {
    @Autowired
	private FootprintRepository footprintRepository;

    public Footprint[] getLatestFootprintOver2Days(String id){
        Footprint[] footprints = footprintRepository.findByUseridOrderByTimestampDesc(id);
        if (footprints.length>1){
            Calendar cal = Calendar.getInstance();
            cal.setTime(footprints[0].getTimestamp());
            cal.add(Calendar.DATE, -1);
            Date lastDateAllowed = cal.getTime();
            for (Footprint fp : footprints){
                if (fp.getTimestamp().before(lastDateAllowed)){
                    ArrayUtils.removeElement(footprints, fp);
                }
            }
        }
        return footprints;
    }

    public String addNewUser (String userid, float x, float y) {
		try {
            Footprint fp = new Footprint(userid, x, y);
            footprintRepository.save(fp);
            return "0";
        } catch (Exception e) {
            return "1";
        }
	}
}