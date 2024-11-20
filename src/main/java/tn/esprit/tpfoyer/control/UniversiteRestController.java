package tn.esprit.tpfoyer.control;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;

import java.util.List;
@OpenAPIDefinition
@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {
    IUniversiteService universiteService;


    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }

    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long uId) {
        return universiteService.retrieveUniversite(uId);
    }
    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteService.addUniversite(u);
    }

    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long uId) {
        universiteService.removeUniversite(uId);
    }

    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        return universiteService.modifyUniversite(u);
    }

}
