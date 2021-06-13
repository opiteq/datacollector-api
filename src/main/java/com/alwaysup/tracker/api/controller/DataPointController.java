package com.alwaysup.tracker.api.controller;

import com.alwaysup.tracker.api.model.dto.DataPointDTO;
import com.alwaysup.tracker.api.service.DataPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/datapoint")
public class DataPointController {

    @Autowired
    private DataPointService dataPointService;

    @GetMapping(value = "/{imei}")
    public List<DataPointDTO> getDataPointsByDeviceAndDataType(
            @PathVariable("imei") String imei,
            @RequestParam(value = "dataType", required = false) String dataType,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        if (dataType == null) {
            List<DataPointDTO> dataPoints = dataPointService
                    .getAllDataPointsByDevice(imei, page, size);
            return dataPoints;
        }
        List<DataPointDTO> dataPoints = dataPointService
                .getAllDataPointsByDeviceAndDataType(imei, dataType, page,
                        size);
        return dataPoints;
    }

    @PostMapping
    public ResponseEntity<DataPointDTO> addDataPoint(
            @RequestBody DataPointDTO deviceDto) {
        DataPointDTO createdDataPointDto =
                dataPointService.addDataPointFromDTO(deviceDto);
        if (createdDataPointDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{imei}")
                    .buildAndExpand(
                            createdDataPointDto.getDeviceDto().getImei())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdDataPointDto);
        }
    }

    @PostMapping("/batchUpload")
    public ResponseEntity<Object> addBatchDataPoints(
            @RequestBody List<DataPointDTO> dpDtos) {
        if (dataPointService.addBatchDataPointsFromDTO(dpDtos)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping
    public ResponseEntity<Object> removeDevice(
            @RequestBody DataPointDTO dataPointDto) {
        if (dataPointService.removeDataPointFromDTO(dataPointDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}