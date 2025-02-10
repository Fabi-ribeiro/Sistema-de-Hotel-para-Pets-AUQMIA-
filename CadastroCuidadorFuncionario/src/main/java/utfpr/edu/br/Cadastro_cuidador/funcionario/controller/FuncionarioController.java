package utfpr.edu.br.Cadastro_cuidador.funcionario.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import utfpr.edu.br.Cadastro_cuidador.funcionario.model.Funcionario;
import utfpr.edu.br.Cadastro_cuidador.funcionario.producer.FuncionarioProducer;
import utfpr.edu.br.Cadastro_cuidador.funcionario.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    
    private static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);
    
    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioProducer funcionarioProducer;// Injeta o Producer

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos() {
        List<Funcionario> funcionarios =  funcionarioService.listarTodos();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(funcionario);
    }

    @PostMapping
    public ResponseEntity<Funcionario> salvarFuncionario(@Valid @RequestBody Funcionario funcionario) {
        try {
            Funcionario savedFuncionario = (Funcionario) funcionarioService.salvarFuncionario(funcionario);

            // Enviar mensagem para verificar a disponibilidade do cuidador
            funcionarioProducer.enviarMensagem("Verificar a disponibilidade do cuidador: " + savedFuncionario.getId());
             
            if(funcionario.isDisponivel()){
                funcionarioProducer.EnviarMensagemParaSucesso("Reserva confirmada para o cuidador: " + savedFuncionario.getId());
            } else {
                funcionarioProducer.enviarMensagemParaNaoAprovada("Reserva não aprovada para o cuidador: " + savedFuncionario.getId());
            }

            return new ResponseEntity<>(savedFuncionario, HttpStatus.CREATED);

        } catch (Exception e) {
            // Log da exceção para facilitar o diagnóstico
            logger.error("Error salvar Funcionario", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        Funcionario funcionario = funcionarioService.atualizarFuncionario(id, funcionarioAtualizado); // Tenta atualizar o funcionário
        return ResponseEntity.ok(funcionario); // Retorna o funcionário atualizado
    }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
       funcionarioService.deletarFuncionario(id);
         return ResponseEntity.ok().build();
   }

}

