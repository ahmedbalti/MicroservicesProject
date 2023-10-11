package tn.esprit.microservicesnihed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.microservicesnihed.entity.Claim;

import tn.esprit.microservicesnihed.repository.ClaimRepository;
import tn.esprit.microservicesnihed.service.IResponseService;
import tn.esprit.microservicesnihed.service.IClaimService;

import java.util.List;

@RestController
@RequestMapping("/api/Claim/")
public class ClaimController {

    @Autowired(required = false)
    private IClaimService iClaimService;

    @Autowired(required = false)
    private ClaimRepository claimRepository;

    @Autowired(required = false)
    private IResponseService iResponseService;
    //simple user can create new claim
    @PostMapping("creat")
    public ResponseEntity createClaim(@RequestBody Claim claim){


        iClaimService.createClaim(claim);
        return new ResponseEntity<>("claim sended successefully", HttpStatus.OK);
    }

    // admin user can access all claims, even they was archived by simple users
    @GetMapping("allClaims")
    public ResponseEntity<List<Claim>> getAllClaims(){



        return new ResponseEntity<>(iClaimService.getAllClaims(),HttpStatus.OK);
    }

    @PutMapping("update/{idClaim}")
    public ResponseEntity updateClaim(@RequestBody Claim claim, @PathVariable("idClaim")Long idClaim){

            iClaimService.updateClaim(claim, idClaim);
            return new ResponseEntity<>("claim updated successefully", HttpStatus.OK);


    }






}
