package pl.hubertkras.coronavirusTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.hubertkras.coronavirusTracker.models.LocationStats;
import pl.hubertkras.coronavirusTracker.services.CoronaVirusDataService;

import java.util.List;

@Controller
public class HomeControler {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    public HomeControler(CoronaVirusDataService coronaVirusDataService) {
        this.coronaVirusDataService = coronaVirusDataService;
    }

    @GetMapping("/")
        public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("localStats", coronaVirusDataService.getAllStats());
        model.addAttribute("totalReportedCases",totalReportedCases);
        return "home";
        }
}
