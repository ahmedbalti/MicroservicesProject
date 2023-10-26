package com.esprit.tn.candidacy_microservice.Controller;

import com.esprit.tn.candidacy_microservice.Entities.Candidacy.Candidacy;
import com.esprit.tn.candidacy_microservice.Entities.Candidacy.DomainCandidacy;
import com.esprit.tn.candidacy_microservice.Entities.Candidacy.QRCode;
import com.esprit.tn.candidacy_microservice.Entities.Candidacy.StatusCandidacy;
import com.esprit.tn.candidacy_microservice.Repository.Candidacy.ICandidacyRepository;
import com.esprit.tn.candidacy_microservice.Service.CandidacyServices.CandidacyServiceImpl;
import com.esprit.tn.candidacy_microservice.Service.CandidacyServices.ICandidacyService;
import com.esprit.tn.candidacy_microservice.Service.CandidacyServices.QRCodeServiceImpl;
import com.esprit.tn.candidacy_microservice.exception.UpdateCandidacyException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidacy")
@CrossOrigin("*")

public class CandidacyRestController {

    /*public User authorisation(){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return userRepository.findByUserName(jwtUtils.getUserNameFromJwtToken(token)).get();
    }*/

    @Autowired
    private QRCodeServiceImpl qrCodeService;

    /*@Autowired
    private IUserService iUserService;*/

    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired(required = false)
    private HttpServletResponse response;

    /*@Autowired
    private UserRepository userRepository;*/

    /*@Autowired
    JwtUtils jwtUtils;*/

    @Autowired
    private ICandidacyService candidacyService;
    @Autowired
    private ICandidacyRepository candidacyRepository;

    @Autowired
    private CandidacyServiceImpl candidacyServiceImpl;



    //http://localhost:8081/espritmobility/api/candidacy/getCandidacy1
    @GetMapping("/getCandidacy1")
    public List<Candidacy> getAllCandidacy1() {
        return candidacyService.getAllCandidacy();
    }

    //http://localhost:8081/espritmobility/api/candidacy/getCandidacy
    /*@GetMapping("/getCandidacy")
    /public List<Candidacy> getAllCandidacy() {
        return candidacyService.getAllCandidacy();
    }*/


    @GetMapping("/modifier/{id}")
    public Candidacy getCandidacyById(@PathVariable Integer id) {
        return candidacyService.getCandidacyById(id);
    }


    //http://localhost:8081/espritmobility/api/candidacy/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Candidacy> getCandidatureById(@PathVariable Integer idCandidacy) {
        Optional<Candidacy> candidatureOptional =candidacyRepository.findById(idCandidacy);
        if (candidatureOptional.isPresent()) {
            Candidacy candidature = candidatureOptional.get();
            return ResponseEntity.ok(candidature);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //http://localhost:8081/espritmobility/api/candidacy/create
    @PostMapping("/create")
    public ResponseEntity<Candidacy> createCandidacy(@Valid @RequestBody Candidacy candidacy) {
        Candidacy createdCandidacy = candidacyService.createCandidacy(candidacy);
        return new ResponseEntity<>(createdCandidacy, HttpStatus.CREATED);
    }


    //http://localhost:8081/espritmobility/api/candidacy/{id}
    @PutMapping("/{id}")
    public Candidacy updateCandidacy(@PathVariable Integer id, @Valid @RequestBody Candidacy candidacyDetails) {
        return candidacyService.updateCandidacy(id, candidacyDetails);
    }


    //http://localhost:8081/espritmobility/api/candidacy/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCandidacy(@PathVariable Integer id) {
        candidacyService.deleteCandidacy(id);
        return ResponseEntity.noContent().build();
    }


    //http://localhost:8081/espritmobility/api/candidacy/search?firstName=ikram
    @GetMapping("/search")
    public ResponseEntity<List<Candidacy>> searchCandidacy(@RequestParam(value = "firstName") String firstName) {
        List<Candidacy> candidacy = candidacyService.getCandidacyByNom(firstName);
        return new ResponseEntity<>(candidacy, HttpStatus.OK);
    }



    //http://localhost:8081/espritmobility/api/candidacy/tri/date
    @GetMapping("/tri/date")
    public ResponseEntity<List<Candidacy>> trierParDate() {
        List<Candidacy> candidacy = candidacyService.trierParDate();
        return new ResponseEntity<>(candidacy, HttpStatus.OK);
    }

    //http://localhost:8081/espritmobility/api/candidacy/{id}/archive
    @PostMapping("/{id}/archive")
    public void archiveCandidature(@PathVariable Integer id) {
        candidacyService.archiveCandidature(id);
    }


    //http://localhost:8081/espritmobility/api/candidacy/pagination?pageNumber=0&pageSize=10&sortBy=idCandidacy
    @GetMapping("/pagination")
    public ResponseEntity<Page<Candidacy>> getAllCandidatures(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Candidacy> result = candidacyService.getAllCandidatures(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(result);
    }


    //http://localhost:8081/espritmobility/api/candidacy/statistiques
    @GetMapping("/statistiques")
    public Map<DomainCandidacy, Long> getNombreCandidaturesParDomaine() {
        return candidacyService.getNombreCandidaturesParDomaine();
    }


    //Selection Candidature Entudiant (ESPRIT)
    //http://localhost:8081/espritmobility/api/candidacy/update/{idCandidacy}/status
    @PutMapping("/update/status/{idCandidacy}")
    public Candidacy updateCandidacyStatus(@PathVariable("idCandidacy") Integer idCandidacy) {
        return candidacyService.updateCandidacyStatus(idCandidacy);
    }

    //http://localhost:8081/espritmobility/api/candidacy/pending
    @GetMapping("/pending")
    public List<Candidacy> getPendingCandidates() {
        return candidacyService.getCandidatesByStatus(StatusCandidacy.EN_ATTENTE);
    }


    //Selection Candidature Etudiant (UNIVERSITE)
    //http://localhost:8081/espritmobility/api/candidacy/put/{idCandidacy}
    @PutMapping("/put/{idCandidacy}")
    public Candidacy updateCandidatureStatus(@PathVariable Integer idCandidacy) {
        return candidacyService.updateCandidatureStatus(idCandidacy);
    }

    //http://localhost:8081/espritmobility/api/candidacy/save
    @PostMapping("/save")
    public Candidacy saveCandidature(@RequestBody Candidacy candidature) {
        return candidacyService.saveCandidature(candidature);
    }

    //Selection Candidature Enseignant
    //http://localhost:8081/espritmobility/api/candidacy/update1/{idCandidacy}
    @PutMapping("/update1/{idCandidacy}")
    public void accepterOuRefuserCandidature(@PathVariable Integer idCandidacy) {
        candidacyService.accepterOuRefuserCandidature(idCandidacy);
    }


    //http://localhost:8081/espritmobility/api/candidacy/mycandidacy
    /*@GetMapping("/mycandidacy")
    public ResponseEntity<List<Candidacy>> getAllCandidacy(){
        User user= authorisation();
        if(user == null)
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(candidacyService.getCandidacyByUser(user.getId()),HttpStatus.OK);
    }

    @PostMapping("/candidature")
    public ResponseEntity ajouterCandidacy (@RequestBody Candidacy candidacy){
        User user= authorisation();
        if(user == null)
            return new ResponseEntity<>("you should be connected",HttpStatus.FORBIDDEN);
        candidacyService.createCandidacy(candidacy, user.getId());
        return new ResponseEntity<>("candidacy registred successefully", HttpStatus.OK);
    }
    @GetMapping("/candidatures")
    public ResponseEntity<List<Candidacy>> getUserCandidacy(){
        User user= authorisation();
        if(user == null)
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(candidacyService.getAllCandidacy(),HttpStatus.OK);
    }*/

    @PutMapping("/employees/{id}")
    public ResponseEntity<Candidacy> updateEmployee(@PathVariable(value = "id") int id,
                                                    @RequestBody Candidacy employeeDetails) throws UpdateCandidacyException {
        Candidacy candidacy = candidacyService.findById(id)
                .orElseThrow(() -> new UpdateCandidacyException("Offer not found for this id :: " + id));

        candidacy.setCv(employeeDetails.getCv());
        candidacy.setCoverLetter(employeeDetails.getCoverLetter());
        candidacy.setCertificate(employeeDetails.getCertificate());
        candidacy.setFirstName(employeeDetails.getFirstName());
        candidacy.setLastName(employeeDetails.getLastName());
        candidacy.setEmail(employeeDetails.getEmail());
        candidacy.setDateCandidacy(employeeDetails.getDateCandidacy());
        candidacy.setTelephoneNumber(employeeDetails.getTelephoneNumber());
        candidacy.setAddress(employeeDetails.getAddress());
        candidacy.setPostalCode(employeeDetails.getPostalCode());
        candidacy.setDomainCandidacy(employeeDetails.getDomainCandidacy());
        candidacy.setStatusCandidacy(employeeDetails.getStatusCandidacy());
        candidacy.setDisponibilite(employeeDetails.getDisponibilite());
        candidacy.setMoyenneGenerale(employeeDetails.getMoyenneGenerale());
        //candidacy.setProfil(employeeDetails.getProfil());
        candidacy.setScoree(employeeDetails.getScoree());
        final Candidacy updatedEmployee = candidacyService.save(candidacy);
        return ResponseEntity.ok(updatedEmployee);
    }


    @PostMapping("/ajouterQR")
    public QRCode createQRCode(@RequestBody String link) throws Exception {
        return qrCodeService.createQRCode(link);
    }
    @GetMapping(value = "/afficher/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCodeImage(@PathVariable("id") Long id) throws IOException {
        byte[] image = qrCodeService.getQRCodeImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
    @GetMapping("/videochat2")
    public ModelAndView videochat2() {
        return new ModelAndView("videochat2");
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePDF() throws DocumentException, IOException {
        List<Candidacy> candidacies = candidacyService.getAllCandidacy1();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();


        Image logo = Image.getInstance("~/personal/SandBox/International_Mobility_Management_Plateform/angularTemplate/src/assets/frontassets/img/logo1.png");
        logo.setAlignment(Element.ALIGN_CENTER);
        logo.scaleToFit(200f, 200f);
        document.add(logo);

        // Ajouter un titre
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.RED);
        Paragraph titre = new Paragraph("Liste Des Candidatures", font);
        titre.setAlignment(Element.ALIGN_CENTER);
        document.add(titre);


        // Ajouter un tableau
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        // Ajouter des en-têtes de colonnes
        PdfPCell cell = new PdfPCell(new Phrase("FirstName"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("LastName"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Date de la candidature"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Adresse"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Email"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        // Ajouter les données
        for (Candidacy candidacy : candidacies) {
            table.addCell(candidacy.getFirstName());
            table.addCell(candidacy.getLastName());
            table.addCell(candidacy.getDateCandidacy().toString());
            //table.addCell(candidacy.getProfil().toString());
            table.addCell(candidacy.getEmail());
        }
        document.add(table);
        document.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename("candidacy.pdf").build());
        headers.setContentLength(baos.size());
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/offer/{offre-id}")
    public ResponseEntity<List<Candidacy>> findAllCandidacies(
            @PathVariable("offre-id") Integer offreId
    ) {
        return ResponseEntity.ok(candidacyServiceImpl.findAllCandidaciesByOffer(offreId));
    }

}