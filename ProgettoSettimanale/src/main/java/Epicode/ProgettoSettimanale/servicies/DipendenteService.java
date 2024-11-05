package Epicode.ProgettoSettimanale.servicies;

import Epicode.ProgettoSettimanale.entities.Dipendente;
import Epicode.ProgettoSettimanale.exceptions.BadRequestException;
import Epicode.ProgettoSettimanale.exceptions.DipendenteNotFoundException;
import Epicode.ProgettoSettimanale.payloads.DipendenteDTO;
import Epicode.ProgettoSettimanale.repositories.DipendenteRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.github.javafaker.Faker;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Dipendente saveDipendente(DipendenteDTO body) {
        this.dipendenteRepository.findByEmail(body.email()).ifPresent(
                dipendente -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso");

                }
        );
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(body.nome());
        dipendente.setCognome(body.cognome());
        dipendente.setEmail(body.email());
        dipendente.setUsername(body.username());

        return dipendenteRepository.save(dipendente);
    }

    public void saveMany() {
        Faker faker = new Faker(Locale.ITALY);
        for (int i = 0; i < 15; i++) {
            DipendenteDTO dipendentePayload = new DipendenteDTO(faker.name().firstName(), faker.name().lastName(), faker.name().username(), faker.internet().emailAddress());

            this.saveDipendente(dipendentePayload);

        }

    }


    public Page<Dipendente> findAllDipendenti(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(Long dipendenteId) {
        return dipendenteRepository.findById(dipendenteId).orElseThrow(() -> new DipendenteNotFoundException());
    }


    public Dipendente findByIdAndUpdate(Long dipendenteId, DipendenteDTO body) {

        Dipendente found = this.findById(dipendenteId);


        if (!found.getEmail().equals(body.email())) {
            this.dipendenteRepository.findByEmail(body.email()).ifPresent(

                    user -> {
                        throw new BadRequestException("Email " + body.email() + " già in uso!");
                    }
            );
        }


        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());
        found.setUsername(body.username());

        return this.dipendenteRepository.save(found);
    }

    public void findByIdAndDelete(Long dipendenteId) {
        Dipendente found = this.findById(dipendenteId);
        this.dipendenteRepository.delete(found);
    }

    public void deleteAllDipendenti() {
        dipendenteRepository.deleteAll();
    }

    public String uploadAvatar(Long dipendenteId, MultipartFile file) {

        Dipendente dipendente = findById(dipendenteId);

        try {
            String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");

            dipendenteRepository.save(dipendente);
            return url;
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }
    }


    public Dipendente findByEmail(@NotEmpty(message = "L'email è un campo obbligatorio!") @Email(message = "L'email inserita non è un'email valida") String email) {
    }

}
