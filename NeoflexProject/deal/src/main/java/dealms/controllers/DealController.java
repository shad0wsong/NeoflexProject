package dealms.controllers;


import dealms.businesslogicinterfaces.AppGeneratorInt;
import dealms.businesslogicinterfaces.ClientInt;
import dealms.businesslogicinterfaces.CreditInt;
import dealms.businesslogicinterfaces.DealBusinessInterface;

import dealms.enums.EmailTheme;
import dealms.exceptions.AppNotFoundExc;
import dealms.dto.*;

import dealms.models.*;
import dealms.repoInterfaces.AppManagerInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;


@RestController
@Api(value = "DealController", description = "Model service")
public class DealController {

    static Logger log = Logger.getLogger(DealController.class.getName());


    @Autowired
    CreditInt creditInt;

    @Autowired
    DealBusinessInterface dealBusinessInterface;

    @Autowired
    ClientInt clientInt;

    @Autowired
    AppGeneratorInt appGeneratorInt;


    @ApiOperation(value = "get LoanOfferDTO list ", response = List.class)
    @PostMapping(value = "/deal/application")
    public List<LinkedHashMap> appl(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) throws AppNotFoundExc {
        log.info("Got loanapplicationRequestDTO: " + loanApplicationRequestDTO);
        Client client = clientInt.setClient(loanApplicationRequestDTO);
        Application application = new Application();
        Application getApp = clientInt.setAppAndClientAndSave(client, application);
        log.info("Saved application " + application);
        log.info("Saved client " + client);
        List<LinkedHashMap> loanOfferDTOList = dealBusinessInterface.getConveyorOffers(getApp, loanApplicationRequestDTO);
        log.info("Sended offers request for conveyor");
        log.info("Returned offer list" + loanOfferDTOList);
        return loanOfferDTOList;
    }


    @ApiOperation(value = "save offer ")
    @PutMapping("/deal/offer")
    public void offers(@RequestBody LoanOfferDTO loanOfferDTO) throws AppNotFoundExc {
        log.info("Got LoanOfferDTO" + loanOfferDTO);
        Application application = appGeneratorInt.setAndSaveApp(loanOfferDTO);
        dealBusinessInterface.createDocs(application.getAppId());
        log.info("Sended request to Kafka");
        log.info("Saved application");
    }


    @PutMapping("/deal/calculate/{applicationId}")
    @ApiOperation(value = "save credit info ")
    public void calc(@RequestBody ScoringDataDTO scoringDataDTO, @PathVariable(value = "applicationId") Long id) throws AppNotFoundExc {
        Application application = appGeneratorInt.getAppByID(id);
        log.info("Got ScoringDataDto and application" + scoringDataDTO + "  " + application);
        scoringDataDTO = dealBusinessInterface.setScoringData(scoringDataDTO, application);
        log.info("Set scoringdata info" + scoringDataDTO);
        Credit credit = dealBusinessInterface.setCredit(scoringDataDTO);
        log.info("Set credit info" + credit);
        creditInt.setCreditAndAppAndSave(credit, application);
        log.info("Saved credit" + credit);
        log.info("Saved appliction" + application);
    }

    @ApiOperation(value = "send docs ")
    @PostMapping(value = "/deal/document/{applicationId}/send")
    public void sendDocs(@PathVariable(value = "applicationId") Long applicationId, @RequestBody EmailMessage emailMessage) {
        dealBusinessInterface.sendDocs(emailMessage, applicationId);
        log.info("Sended request to Kafka");

    }

    @ApiOperation(value = " request sign docs ")
    @PostMapping(value = "/deal/document/{applicationId}/sign")
    public void rqSignDocs(@PathVariable(value = "applicationId") Long applicationId, @RequestBody EmailMessage emailMessage) {
        dealBusinessInterface.rqSignDocs(applicationId, emailMessage);
        log.info("Sended request to Kafka");
    }

    @ApiOperation(value = " sign docs ")
    @PostMapping(value = "/deal/document/{applicationId}/code")
    public void signDocs(@PathVariable(value = "applicationId") Long applicationId, @RequestBody EmailMessage emailMessage) {
        dealBusinessInterface.signDocs(applicationId, emailMessage);
        log.info("Sended request to Kafka");
    }

    @ApiOperation(value = " get app by id ")
    @GetMapping(value = "/deal/admin/application/{applicationId}")
    public Application getAppById(@PathVariable(value = "applicationId") Long applicationId) throws AppNotFoundExc {
        Application application=appGeneratorInt.getAppByID(applicationId);
        log.info("Returned app by id");
        return application;
    }

    @ApiOperation(value = " get all applications ")
    @GetMapping(value = "/deal/admin/application")
    public Iterable getAllApp() {
        Iterable<Application> applications = appGeneratorInt.getAllApp();
        log.info("Returned all apps");
        return applications;
    }
}
