package com.example.offerclient2.controller;



import com.example.offerclient2.OfferRepository.IOfferRepository;
import com.example.offerclient2.OfferService.IOfferService;
import com.example.offerclient2.OfferService.QRCodeServiceImpl1;
import com.example.offerclient2.entity.Offer.Destination;
import com.example.offerclient2.entity.Offer.Offer;
import com.example.offerclient2.exception.UpdateOfferException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin("*")
public class OfferRestController {
//
//    public User authorisation(){
//        String token = request.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        }
//        return userRepository.findByUserName(jwtUtils.getUserNameFromJwtToken(token)).get();
//    }
//    @Autowired
//    private IUserService iUserService;
    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired(required = false)
    private HttpServletResponse response;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    JwtUtils jwtUtils;



//    @Autowired
//    private IAlertService iAlertService;

    @Autowired
    private IOfferService offerService;
    @Autowired
    private IOfferRepository offerRepository;




    //    http://localhost:8080/espritmobility/api/offers/getOffers
    @GetMapping("/getOffers")
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    //    http://localhost:8080/espritmobility/api/offers/4
    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable Integer id) {
        return offerService.getOfferById(id);
    }


    //    http://localhost:8080/espritmobility/api/offers/createOffer
    @PostMapping("/createOffer")
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody Offer offer) {
        offer.setOfferCreationDate(LocalDateTime.now());
        Offer createdOffer = offerService.createOffer(offer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    //    http://localhost:8080/espritmobility/api/offers/10
    @PutMapping("/{id}")
    public Offer updateOffer(@PathVariable Integer id, @Valid @RequestBody Offer offerDetails) {
        return offerService.updateOffer(id, offerDetails);
    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<Offer> updateEmployee(@PathVariable(value = "id") int id,
                                                @RequestBody Offer employeeDetails) throws UpdateOfferException {
        Offer offer = offerService.findById(id)
                .orElseThrow(() -> new UpdateOfferException("Offer not found for this id :: " + id));

        offer.setTitle(employeeDetails.getTitle());
        offer.setImage(employeeDetails.getImage());
        offer.setDateOffre(employeeDetails.getDateOffre());
        offer.setOfferCreationDate(employeeDetails.getOfferCreationDate());
        offer.setNbreCandidats(employeeDetails.getNbreCandidats());
        offer.setProfil(employeeDetails.getProfil());
        offer.setDestination(employeeDetails.getDestination());
        offer.setDuration(employeeDetails.getDuration());
        offer.setConditions(employeeDetails.getConditions());
        offer.setAdvantages(employeeDetails.getAdvantages()) ;

        final Offer updatedEmployee = offerService.save(offer);
        return ResponseEntity.ok(updatedEmployee);
    }

    //    http://localhost:8080/espritmobility/api/offers/10
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    //    http://localhost:8080/espritmobility/api/offers/search?title=offre mobilité etudiant Stutgart
    @GetMapping("/search")
    public ResponseEntity<List<Offer>> searchOffers(@RequestParam(value = "title") String title) {
        List<Offer> offers = offerService.getOffersByTitle(title);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    //    http://localhost:8080/espritmobility/api/offers/tri/date
    @GetMapping("/tri/date")
    public ResponseEntity<List<Offer>> trierParDate() {
        List<Offer> offers = offerService.trierParDate();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    //    http://localhost:8080/espritmobility/api/offers/9/archive
    @PostMapping("/{id}/archive")
    public void archiveCandidature(@PathVariable Integer id) {
        offerService.archiveOffer(id);
    }

    //    http://localhost:8080/espritmobility/api/offers/export/excel
//    @GetMapping("/export/excel")
//    public void exportOffersToExcel(HttpServletResponse response) {
//        offerService.exportOffersToExcel(response);
//    }

    //    http://localhost:8080/espritmobility/api/offers/pagination?pageNumber=0&pageSize=10&sortBy=idOffre
    @GetMapping("/pagination")
    public ResponseEntity<Page<Offer>> paginationOffers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Offer> result = offerService.paginationOffers(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(result);
    }

    //    http://localhost:8080/espritmobility/api/offers/dateOffer/2023-01-01
    @GetMapping("/dateOffer/{date}")
    public List<Offer> getOffresDateSuperieur(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return offerService.getOffresDateSuperieur(date);
    }

    //    http://localhost:8080/espritmobility/api/offers/statistiques
    @GetMapping("/statistiques")
    public Map<Destination, Long> getNombreOffresParDestination() {
        return offerService.getNombreOffresParDestination();
    }

    //    http://localhost:8080/espritmobility/api/offers/pdf
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePDF() throws DocumentException, IOException {
        List<Offer> offres = offerService.getAllOffers();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();


        Image logo = Image.getInstance("/personal/SandBox/International_Mobility_Management_Plateform/angularTemplate/src/assets/frontassets/img/logo1.png");
        logo.setAlignment(Element.ALIGN_CENTER);
        logo.scaleToFit(200f, 200f);
        document.add(logo);

        // Ajouter un titre
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.RED);
        Paragraph titre = new Paragraph("Liste Des Offres", font);
        titre.setAlignment(Element.ALIGN_CENTER);
        document.add(titre);


        // Ajouter un tableau
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        // Ajouter des en-têtes de colonnes
        PdfPCell cell = new PdfPCell(new Phrase("Title"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Conditions"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Date de l'offre"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombre de candidats"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Destination"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        // Ajouter les données
        for (Offer offre : offres) {
            table.addCell(offre.getTitle());
            table.addCell(offre.getConditions());
            table.addCell(offre.getDateOffre().toString());
            table.addCell(offre.getNbreCandidats().toString());
            table.addCell(offre.getDestination().toString());
        }
        document.add(table);
        document.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename("offres1.pdf").build());
        headers.setContentLength(baos.size());
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    //    http://localhost:8080/espritmobility/api/offers/enseignant
    @GetMapping("/enseignant")
    public List<Offer> getOffresEnseignant() {
        return offerService.getOffresEnseignant();
    }

    //    http://localhost:8080/espritmobility/api/offers/etudiant
    @GetMapping("/etudiant")
    public List<Offer> getOffresEtudiant() {
        return offerService.getOffresEtudiant();
    }

//    @GetMapping("/allOffers")
//    public ResponseEntity<List<Offer>> getAllOffers1(){
//
//        String token = request.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        }
//        User user = userRepository.findByUserName(jwtUtils.getUserNameFromJwtToken(token)).get();
//        //if(!user.getRoles().contains(ERole.ROLE_ADMIN))
//        //return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
//        return new ResponseEntity<>(offerService.getAllOffers1(),HttpStatus.OK);
//    }


//    @PostMapping("/ajouterOffer")
//    public ResponseEntity ajouterOffer (@RequestBody Offer offer){
//        User user= authorisation();
//        if(user == null)
//            return new ResponseEntity<>("you should be connected",HttpStatus.FORBIDDEN);
//        offerService.createOffer2(offer, user.getId());
//        return new ResponseEntity<>("offer registred successefully", HttpStatus.OK);
//    }



//    @GetMapping("/myOffers")
//    public ResponseEntity<List<Offer>> getAllMyOffers(){
//        User user= authorisation();
//        if(user == null)
//            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
//        return new ResponseEntity<>(offerService.getOfferByUser(user.getId()),HttpStatus.OK);
//    }

//    @GetMapping("/Useroffers")
//    public ResponseEntity<List<Offer>> getUserOffers(){
//        User user= authorisation();
//        if(user == null)
//            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
//        return new ResponseEntity<>(offerService.getAllOffers(),HttpStatus.OK);
//    }

    @Autowired
    private QRCodeServiceImpl1 qrCodeService;

//    @PostMapping("/ajouterQR")
//    public QRCode1 createQRCode(@RequestBody String link) throws Exception {
//        return qrCodeService.createQRCode(link);
//    }

    @GetMapping(value = "/qr/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCodeImage(@PathVariable("id") Long id) throws IOException {
        byte[] image = qrCodeService.getQRCodeImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

//    @GetMapping("/videochat2")
//    public ModelAndView videochat2() {
//        return new ModelAndView("videochat2");
//    }
//

}