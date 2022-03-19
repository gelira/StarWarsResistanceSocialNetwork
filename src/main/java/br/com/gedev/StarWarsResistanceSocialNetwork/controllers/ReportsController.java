package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.business.ReportsBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.ReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
public class ReportsController {
    private final ReportsBusiness reportsBusiness;

    @GetMapping
    public ReportDTO generateReport() {
        return reportsBusiness.generateReport();
    }
}
