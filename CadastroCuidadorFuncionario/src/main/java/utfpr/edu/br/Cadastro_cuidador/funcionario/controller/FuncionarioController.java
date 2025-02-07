package utfpr.edu.br.Cadastro_cuidador.funcionario.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import utfpr.edu.br.Cadastro_cuidador.funcionario.model.Funcionario;
import utfpr.edu.br.Cadastro_cuidador.funcionario.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    private static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);
    @Autowired
    private FuncionarioService funcionarioService;

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
            Funcionario savedFuncionario = funcionarioService.salvarFuncionario(funcionario);
            return new ResponseEntity<>(savedFuncionario, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log da exceção para facilitar o diagnóstico
            logger.error("Error saving Funcionario", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(";");
         });
        return ResponseEntity.badRequest().body(errors.toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        try {
            Funcionario funcionario = funcionarioService.atualizarFuncionario(id, funcionarioAtualizado);
            return ResponseEntity.ok(funcionario); // Retorna o funcionário atualizado
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 caso não encontre
        }
    }

    @DeleteMapping("/{id}")
    public void deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
    }
}
