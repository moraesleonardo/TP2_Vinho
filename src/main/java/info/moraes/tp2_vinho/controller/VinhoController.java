package info.moraes.tp2_vinho.controller;

import info.moraes.tp2_vinho.model.Vinho;
import info.moraes.tp2_vinho.model.VinhoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VinhoController {

    private final VinhoRepository vinhoRepository;


    public VinhoController(VinhoRepository vinhoRepository) {
        this.vinhoRepository = vinhoRepository;
        vinhoRepository.saveAll(List.of(
                new Vinho("Château Margaux", "2015", "França"),
                new Vinho("Penfolds Grange", "2010", "Austrália"),
                new Vinho("Sassicaia", "2016", "Itália"),
                new Vinho("Opus One", "2014", "Estados Unidos"),
                new Vinho("Vega Sicilia", "2009", "Espanha"),
                new Vinho("Porto Vintage", "2011", "Portugal")
        ));
    }

    @GetMapping("/vinhos")
    Iterable<Vinho> getVinhos() {
        //return vinhos;
        return vinhoRepository.findAll();
    }

    @GetMapping("/vinhos/{id}")
    Optional<Vinho> getVinhosPorId(@PathVariable int id) {
        return vinhoRepository.findById(id);
    }

    @DeleteMapping("/vinhos/{id}")
    ResponseEntity<Vinho> deleteVinho(@PathVariable int id) {
        if (vinhoRepository.existsById(id)) {
            vinhoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/vinhos")
    ResponseEntity<Vinho> addVinho(@RequestBody Vinho vinho) {
        if (!vinho.getNome().isEmpty() && !vinho.getAno().isEmpty()) {
            vinhoRepository.save(vinho);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/vinhos/{id}")
    ResponseEntity<Vinho> updateVinho(@PathVariable int id, @RequestBody Vinho vinho) {
        return vinhoRepository.findById(id)
                .map(existingVinho -> {
                    vinho.setId(existingVinho.getId());
                    return ResponseEntity.ok(vinhoRepository.save(vinho));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CREATED).body(vinhoRepository.save(vinho)));

    }
}