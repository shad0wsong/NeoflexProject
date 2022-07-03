package dealms.controllers;


import dealms.businesslogicinterfaces.AppGeneratorInt;
import dealms.businesslogicinterfaces.ClientInt;
import dealms.businesslogicinterfaces.CreditInt;
import dealms.businesslogicinterfaces.DealBusinessInterface;

import dealms.exceptions.AppNotFoundExc;
import dealms.dto.*;

import dealms.models.*;
import dealms.repoInterfaces.AppManagerInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
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
        Application getApp=clientInt.setAppAndClientAndSave(client, application);
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
        appGeneratorInt.setAndSaveApp(loanOfferDTO);
        log.info("Saved application");
    }


    @PutMapping("/deal/calculate/{applicationId}")
    @ApiOperation(value = "save credit info ")
    public void calc(@RequestBody ScoringDataDTO scoringDataDTO, @PathVariable(value = "applicationId") Long id) throws AppNotFoundExc {
        Application application=appGeneratorInt.getAppByID(id);
        log.info("Got ScoringDataDto and application" + scoringDataDTO + "  " + application);
        scoringDataDTO = dealBusinessInterface.setScoringData(scoringDataDTO, application);
        log.info("Set scoringdata info" + scoringDataDTO);
        Credit credit = dealBusinessInterface.setCredit(scoringDataDTO);
        log.info("Set credit info" + credit);
        creditInt.setCreditAndAppAndSave(credit, application);

        log.info("Saved credit" + credit);
        log.info("Saved appliction" + application);
    }

}
