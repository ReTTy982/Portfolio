package edu.pwr.pl.Lab06.app.installation;

import edu.pwr.pl.Lab06.app.installation.DTO.InstallationCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/installation")
@Controller
public class InstallationController {
    private final InstallationService installationService;

    @Autowired
    public InstallationController(InstallationService installationService){
        this.installationService = installationService;
    }


    @PostMapping("/new")
    public void createInstallation(@RequestBody InstallationCreateRequest request){installationService.createInstallation(request);}
}
