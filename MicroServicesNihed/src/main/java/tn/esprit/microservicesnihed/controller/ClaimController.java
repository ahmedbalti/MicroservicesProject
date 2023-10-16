package tn.esprit.microservicesnihed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.microservicesnihed.entity.Claim;

import tn.esprit.microservicesnihed.entity.Response;
import tn.esprit.microservicesnihed.repository.ClaimRepository;
import tn.esprit.microservicesnihed.repository.ResponseRepository;
import tn.esprit.microservicesnihed.service.IResponseService;
import tn.esprit.microservicesnihed.service.IClaimService;

import java.util.List;

@RestController
@RequestMapping("/api/Claim/")
@CrossOrigin("http://localhost:4200/")
public class ClaimController {

    @Autowired(required = false)
    private IClaimService iClaimService;

    @Autowired(required = false)
    private ClaimRepository claimRepository;

    @Autowired(required = false)
    private ResponseRepository responseRepository;

    @Autowired(required = false)
    private IResponseService iResponseService;
    //simple user can create new claim
    @PostMapping("creat")

    public ResponseEntity createClaim(@RequestBody Claim claim){


        iClaimService.createClaim(claim);
        return new ResponseEntity<>("claim sended successefully", HttpStatus.OK);
    }


    @GetMapping("allClaims")
    public ResponseEntity<List<Claim>> getAllClaims(){

        return new ResponseEntity<>(iClaimService.getAllClaims(),HttpStatus.OK);
    }

    @PutMapping("update/{idClaim}")
    public ResponseEntity updateClaim(@RequestBody Claim claim, @PathVariable("idClaim")Long idClaim){

        iClaimService.updateClaim(claim, idClaim);
        return new ResponseEntity<>("claim updated successefully", HttpStatus.OK);
    }

    //user admin respond to specific claim
    @PostMapping("responseclaim/{idClaim}")
    public ResponseEntity respondClaim(@RequestBody Response response, @PathVariable("idClaim")Long idClaim){

            iResponseService.addResponse(response,idClaim);
            return new ResponseEntity<>("response add successefully", HttpStatus.OK);

    }
    @GetMapping("allresponses")
    public ResponseEntity<List<Response>> getAllResponses(){
        return new ResponseEntity<>(responseRepository.findAll(),HttpStatus.OK);
    }

    @PutMapping("/block/{idClaim}")
    public ResponseEntity blockClaim(@PathVariable("idClaim")Long idClaim){

        iClaimService.blockClaim(idClaim);
        return new ResponseEntity<>("claim blocked successefully", HttpStatus.OK);
    }


    @DeleteMapping("delete/{idClaim}")
    public ResponseEntity deleteClaim(@PathVariable("idClaim") Long idClaim) {
        iClaimService.deleteClaim(idClaim);
        return new ResponseEntity<>("Claim deleted successfully", HttpStatus.OK);
    }





}
