package gatewayms.controller;

import gatewayms.businessimpl.RequestServiceImpl;
import gatewayms.dto.EmailMessage;
import gatewayms.dto.LoanApplicationRequestDTO;
import gatewayms.dto.LoanOfferDTO;
import gatewayms.dto.ScoringDataDTO;
import io.swagger.annotations.ApiOperation;
import gatewayms.models.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class GateController {
    static Logger log = Logger.getLogger(GateController.class.getName());

    @Autowired
    RequestServiceImpl requestService;

    @ApiOperation(value = "get LoanOfferDTO list ", response = List.class)
    @PostMapping(value = "/gateway/application")
    public List<LinkedHashMap> appl(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("Got loanapplicationRequestDTO: " + loanApplicationRequestDTO);
        List<LinkedHashMap> offers=requestService.getDealOffers(loanApplicationRequestDTO);
        log.info("Sended offers request to application");
        log.info("Returned offer list" + offers);
        return offers;
    }

    @ApiOperation(value = "save offer ")
    @PutMapping("/gateway/offer")
    public void offers(@RequestBody LoanOfferDTO loanOfferDTO) {
        log.info("Got LoanOfferDTO" + loanOfferDTO);
        requestService.saveDealOffer(loanOfferDTO);
        log.info("Sended request to application");
    }

    @PutMapping("/gateway/calculate/{applicationId}")
    @ApiOperation(value = "save credit info ")
    public void calc(@RequestBody ScoringDataDTO scoringDataDTO, @PathVariable(value = "applicationId") Long id)  {
        requestService.calcCredit(scoringDataDTO,id);
        log.info("Sended request to deal");
    }

    @ApiOperation(value = "send docs ")
    @PostMapping(value = "/gateway/document/{applicationId}/send")
    public void sendDocs(@PathVariable(value = "applicationId") Long applicationId, @RequestBody EmailMessage emailMessage) {
        requestService.sendDocs(applicationId,emailMessage);
        log.info("Sended request to deal");
    }

    @ApiOperation(value = " request sign docs ")
    @PostMapping(value = "/gateway/document/{applicationId}/sign")
    public void rqSignDocs(@PathVariable(value = "applicationId") Long applicationId, @RequestBody EmailMessage emailMessage) {
        requestService.rqSignDocs(applicationId,emailMessage);
        log.info("Sended request to deal");
    }

    @ApiOperation(value = " sign docs ")
    @PostMapping(value = "/gateway/document/{applicationId}/code")
    public void signDocs(@PathVariable(value = "applicationId") Long applicationId, @RequestBody EmailMessage emailMessage) {
        requestService.signDocs(applicationId,emailMessage);
        log.info("Sended request to deal");
    }

    @ApiOperation(value = " get app by id ")
    @GetMapping(value = "/gateway/admin/application/{applicationId}")
    public Application getAppById(@PathVariable(value = "applicationId") Long applicationId)  {
        Application application=requestService.getAppById(applicationId);
        log.info("Returned app by id");
        return application;
    }

    @ApiOperation(value = " get all applications ")
    @GetMapping(value = "/gateway/admin/application")
    public Iterable getAllApp() {
        Iterable<Application> applications = requestService.getAllAp();
        log.info("Returned all apps");
        return applications;
    }
}
