package Epicode.ProgettoSettimanale.servicies;

import Epicode.ProgettoSettimanale.entities.Dipendente;
import Epicode.ProgettoSettimanale.repositories.DipendenteRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente saveDipendente(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    public void saveMany() {
        Faker faker = new Faker(Locale.ITALY);
        List<Dipendente> dipendenti = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Dipendente dipendente = new Dipendente();
            dipendente.setNome(faker.name().firstName());
            dipendente.setCognome(faker.name().lastName());
            dipendente.setUsername(faker.name().username());
            dipendente.setEmail(faker.internet().emailAddress());
            dipendenti.add(dipendente);

        }
        for (Dipendente dipendente : dipendenti) {

            this.saveDipendente(dipendente);

        }
    }


    public Page<Dipendente> findAllDipendenti(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteRepository.findAll(pageable);
    }

    public Optional<Dipendente> findDipendenteById(Long id) {
        return dipendenteRepository.findById(id);
    }


}
