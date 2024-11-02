package Epicode.ProgettoSettimanale.servicies;

import Epicode.ProgettoSettimanale.entities.Viaggio;
import Epicode.ProgettoSettimanale.exceptions.ViaggioNotFoundException;
import Epicode.ProgettoSettimanale.payloads.ViaggioDTO;
import Epicode.ProgettoSettimanale.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public Page<Viaggio> findAllviaggi(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.viaggioRepository.findAll(pageable);
    }

    public Optional<Viaggio> findViaggioById(Long viaggioId) {
        return viaggioRepository.findById(viaggioId);
    }

    public Viaggio creaViaggio(ViaggioDTO viaggioDTO) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDataViaggio(viaggioDTO.dataViaggio());
        viaggio.setDestinazione(viaggioDTO.destinazione());
        viaggio.setStato(viaggioDTO.stato());
        return viaggioRepository.save(viaggio);
    }

    public Viaggio findViaggioByIdAndUpdate(Long viaggioId, ViaggioDTO dati) {
        return viaggioRepository.findById(viaggioId).map(viaggio -> {
            viaggio.setDataViaggio(dati.dataViaggio());
            viaggio.setDestinazione(dati.destinazione());
            viaggio.setStato(dati.stato());
            return viaggioRepository.save(viaggio);
        }).orElseThrow(() -> new ViaggioNotFoundException());
    }

    public void findViaggioByIdAndDelete(Long viaggioId) {
        if (!viaggioRepository.existsById(viaggioId)) {
            throw new ViaggioNotFoundException();
        }
        viaggioRepository.deleteById(viaggioId);
    }

    public void deleteAllViaggi() {
        viaggioRepository.deleteAll();
    }
}
