package io.github.karinaerikads.mscartoes.application;

import io.github.karinaerikads.mscartoes.application.dto.CartaoSaveRequest;
import io.github.karinaerikads.mscartoes.application.dto.CartoesPorClienteResponse;
import io.github.karinaerikads.mscartoes.domain.Cartao;
import io.github.karinaerikads.mscartoes.domain.ClienteCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;
    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request){
        Cartao cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda){
        List<Cartao> listCartao = cartaoService.getCartoesRendaMenosIgual(renda);
        return ResponseEntity.ok(listCartao);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf){
        List<ClienteCartao> listClienteCartao = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> listCartoesPorCliente = listClienteCartao.stream()
                .map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listCartoesPorCliente);
    }
}
