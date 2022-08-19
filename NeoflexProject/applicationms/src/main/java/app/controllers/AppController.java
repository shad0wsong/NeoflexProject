package app.controllers;

import app.dto.LoanApplicationRequestDTO;
import app.dto.LoanOfferDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

@RestController
@Validated
public class AppController {

    static Logger log = Logger.getLogger(AppController.class.getName());

    @Autowired
    RestTemplate restTemplate;

    @Value("${offer}")
    String offer;

    @Value("${app}")
    String app;

    @PostMapping("/application")
    @ApiOperation(value = "get offers", response = List.class)
    public List<LinkedHashMap> getOffers(@RequestBody @Valid LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("Got LoanApplicationRequestDTO");
        List<LinkedHashMap> offers = restTemplate.postForObject(app, loanApplicationRequestDTO, List.class);
        log.info("Sended offers");
        return offers;
    }

    @PostMapping("/application/offer")
    @ApiOperation(value = "save offer")
    public void saveOffers(@RequestBody LoanOfferDTO loanOfferDTO) {
        log.info("Got LoanOfferDTO");
        restTemplate.put(offer, loanOfferDTO);
        log.info("sended LoanOfferDTO");

    }
}
