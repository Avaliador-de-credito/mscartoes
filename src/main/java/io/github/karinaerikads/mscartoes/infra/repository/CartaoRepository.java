package io.github.karinaerikads.mscartoes.infra.repository;

import io.github.karinaerikads.mscartoes.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
