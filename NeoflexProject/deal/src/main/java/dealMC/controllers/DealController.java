package dealMC.controllers;


import dealMC.BusinessLogicInterfaces.DealBusinessInterface;

import dealMC.dto.*;

import dealMC.models.*;
import dealMC.repoInterfaces.AppManagerInterface;
import dealMC.repoInterfaces.ClientManagerInterface;
import dealMC.repoInterfaces.CreditManagerInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static dealMC.enums.AppStatus.PREAPPROVAL;


@RestController
@Api(value = "DealController", description = "Model service")
public class DealController {

    static Logger log = Logger.getLogger(DealController.class.getName());

    @Autowired
    AppManagerInterface appManagerInterface;

    @Autowired
    CreditManagerInterface creditManagerInterface;

    @Autowired
    ClientManagerInterface clientManagerInterface;

    @Autowired
    DealBusinessInterface dealBusinessInterface;

    @ApiOperation(value = "get LoanOfferDTO list ", response = List.class)
    @PostMapping(value = "/deal/application")
    public List<LinkedHashMap> appl(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("Got loanapplicationRequestDTO: " + loanApplicationRequestDTO);
        Client client = new Client(null, loanApplicationRequestDTO.getFirstName(), loanApplicationRequestDTO.getLastName(), loanApplicationRequestDTO.getMiddleName(),
                loanApplicationRequestDTO.getEmail(), loanApplicationRequestDTO.getBirthdayDate(),
                null, null, null, null, null,
                loanApplicationRequestDTO.getPassportSeries(), loanApplicationRequestDTO.getPassportNumber(), null,
                null, null, null, null, null, null);
        Application application = new Application();
        application.setClient(client);
        client.setApplication(application);
        String UUID = application.getUUID();
        log.info("Saved application " + application);
        appManagerInterface.save(application);
        log.info("Saved client " + client);
        clientManagerInterface.save(client);
        Optional<Application> optApp = appManagerInterface.getIdByUUID(UUID);
        Application getApp = optApp.get();
        List<LinkedHashMap> loanOfferDTOList = dealBusinessInterface.getConveyorOffers(getApp, loanApplicationRequestDTO);
        log.info("Sended offers request for conveyor");
        log.info("Returned offer list" + loanOfferDTOList);
        return loanOfferDTOList;
    }


    @ApiOperation(value = "save offer ")
    @PutMapping("/deal/offer")
    public void offers(@RequestBody LoanOfferDTO loanOfferDTO) {
        log.info("Got LoanOfferDTO" + loanOfferDTO);
        Long appId = loanOfferDTO.getApplicationID();
        Optional<Application> application = appManagerInterface.findById(appId);
        Application realApp = application.get();
        realApp.setAppStatus(PREAPPROVAL);
        realApp.getStatusHistoryElements().add(new StatusHistoryElement(PREAPPROVAL));
        realApp.setAppliedOffer(new LoanOfferDtoEntity(loanOfferDTO.getRequestedAmount(), loanOfferDTO.getTerm(),
                loanOfferDTO.getMonthlyPayment(), loanOfferDTO.getRate(), loanOfferDTO.getIsInsuranceEnabled(), loanOfferDTO.getIsSalaryClient(), realApp));
        log.info("Saved application" + realApp);
        appManagerInterface.save(realApp);
    }


    @PutMapping("/deal/calculate/{applicationId}")
    @ApiOperation(value = "save credit info ")
    public void calc(@RequestBody ScoringDataDTO scoringDataDTO, @PathVariable(value = "applicationId") Long id) {
        Optional<Application> optionalApplication = appManagerInterface.findById(id);
        Application application = optionalApplication.get();
        log.info("Got ScoringDataDto and application" + scoringDataDTO + "  " + application);
        scoringDataDTO = dealBusinessInterface.setScoringData(scoringDataDTO, application);
        log.info("Set scoringdata info" + scoringDataDTO);
        Credit credit = dealBusinessInterface.setCredit(application, scoringDataDTO);
        log.info("Set credit info" + credit);
        credit.setApplication(application);
        application.setCredit(credit);
        creditManagerInterface.save(credit);
        log.info("Saved credit" + credit);
        appManagerInterface.save(application);
        log.info("Saved appliction" + application);
    }
}
