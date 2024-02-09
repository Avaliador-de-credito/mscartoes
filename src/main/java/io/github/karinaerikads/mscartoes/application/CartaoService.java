package io.github.karinaerikads.mscartoes.application;

import io.github.karinaerikads.mscartoes.domain.Cartao;
import io.github.karinaerikads.mscartoes.infra.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    @Transactional
    public Cartao save (Cartao cartao){
        return cartaoRepository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenosIgual(Long renda){
        var rendaBigDecimal = BigDecimal.valueOf(renda);
        return cartaoRepository.findByRendaLessThanEqual(rendaBigDecimal);
    }
}
