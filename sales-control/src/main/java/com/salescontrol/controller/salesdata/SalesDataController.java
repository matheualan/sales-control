package com.salescontrol.controller.salesdata;

import com.salescontrol.model.SalesData;
import com.salescontrol.repository.SalesDataRepository;
import com.salescontrol.service.SalesDataService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales-data")
public class SalesDataController {

    @Autowired
    private SalesDataService salesDataService;

    @Autowired
    private SalesDataRepository salesDataRepository;

    @Hidden
    @PostMapping("/populate-most-requested")
    public ResponseEntity<SalesData> populateMostRequested(@RequestBody SalesData salesData) {
        salesDataService.populateMostRequested(salesData);
        salesDataRepository.save(salesData);
        return ResponseEntity.ok(salesData);
    }

}
