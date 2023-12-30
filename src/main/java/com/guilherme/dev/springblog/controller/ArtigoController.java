/*
* Autor: Guilherme Nascimento
* Data: 30/12/2023
* Objetivo: Criar um controlador REST para o projeto.
* */

package com.guilherme.dev.springblog.controller;

import com.guilherme.dev.springblog.model.*;
import com.guilherme.dev.springblog.service.ArtigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/artigos")
public class ArtigoController {
    @Autowired
    private ArtigoService artigoService;

    @GetMapping
    public List<Artigo> obterTodos() {
        return this.artigoService.obterTodos();
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<String> handleOptimistLockingFailureException(OptimisticLockingFailureException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro de concorrencia: O recurso foi atualizado por outro usuario. Por favor tente novamente.");
    }

    @GetMapping("/{codigo}")
    public Artigo obterPorCodigo(@PathVariable String codigo) {
        return this.artigoService.obterPorCodigo(codigo);
    }

/*    @PostMapping
    public Artigo criar(@RequestBody Artigo artigo){
        return this.artigoService.criar(artigo);
    }*/

/*    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Artigo artigo) {
        return this.artigoService.criar(artigo);
    }*/

    @PostMapping
    public ResponseEntity<?> criarArtigoComAutor(@RequestBody ArtigoComAutorRequest request) {
        Artigo artigo = request.getArtigo();
        Autor autor = request.getAutor();

        return this.artigoService.criarArtigoComAutor(artigo, autor);
    }

    @PutMapping("/atualiza-artigo/{id}")
    public ResponseEntity<?> atualizarArtigo(@PathVariable("id") String id, @Valid @RequestBody Artigo artigo) {
        return this.artigoService.atualizarArtigo(id, artigo);
    }

    @GetMapping("/maiordata")
    public List<Artigo> findByDataGreatherThan(@RequestParam("data") LocalDateTime data) {
        return this.artigoService.findByDataGreatherThan(data);
    }

    @GetMapping("/data-status")
    public List<Artigo> findByDataAndStatus(LocalDateTime data, Integer status) {
        return this.artigoService.findByDataAndStatus(data, status);
    }

    @PutMapping
    public void atualizar(@RequestBody Artigo artigo) {
        this.artigoService.atualizar(artigo);
    }

    @PutMapping("{id}")
    public void atualizarArtigo(@PathVariable String id, @RequestBody String novaURL) {
        this.artigoService.atualizarArtigo(id, novaURL);
    }

    @DeleteMapping("/delete-artigo-autor")
    public void excluirArtigoeAutor(@RequestBody Artigo artigo){
        this.artigoService.excluirArtigoEAutor(artigo);
    }

    @DeleteMapping("{id}")
    public void deleteArtigo(@PathVariable String id) {
        this.artigoService.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void deleteArtigoById(@RequestParam("Id") String id) {
        this.artigoService.deleteArtigoById(id);
    }

    @GetMapping("/status-maiordata")
    public List<Artigo> findByStatusAndDataGreatherThan(@RequestParam("status") Integer status, @RequestParam("data") LocalDateTime data) {
        return this.artigoService.findByStatusAndDataGreaterThan(status, data);
    }

    @GetMapping("/periodo")
    public List<Artigo> obterArtigoPorDataHora(@RequestParam("de") LocalDateTime de, @RequestParam("ate") LocalDateTime ate) {
        return this.artigoService.obterArtigoPorDataHora(de, ate);
    }

    @GetMapping("/artigo-complexo")
    public List<Artigo> encontrarArtigosComplexos(@RequestParam("status") Integer status, @RequestParam("data") LocalDateTime data, @RequestParam("texto") String titulo) {
        return this.artigoService.encontrarArtigosComplexos(status, data, titulo);
    }

    @GetMapping("/pagina-artigos")
    public ResponseEntity<Page<Artigo>> listaArtigos(Pageable pageable) {
        Page<Artigo> artigos = this.artigoService.listaArtigos(pageable);

        return ResponseEntity.ok(artigos);
    }

    @GetMapping("/status-ordernado")
    public List<Artigo> findByStatusOrderByTituloAsc(@RequestParam("status") Integer Status) {
        return this.artigoService.findByStatusOrderByTituloAsc(Status);
    }

    @GetMapping("/status-query-ordenacao")
    public List<Artigo> obterArtigoStatusComOrdenacao(@RequestParam("status") Integer Status) {
        return this.artigoService.obterArtigoStatusComOrdenacao(Status);
    }

    @GetMapping("/busca-texto")
    public List<Artigo> findByTexto(@RequestParam("searchTerm") String termo) {
        return this.artigoService.findByTexto(termo);
    }

    @GetMapping("/contar-artigo")
    public List<ArtigoStatusCount> contarArtigosPorStatus() {
        return this.artigoService.contarArtigosPorStatus();
    }


    @GetMapping("/total-artigo-autor-periodo")
    public List<AutorTotalArtigo> calcularTotalArtigosPorAutorNoPeriodo(
            @RequestParam("dataInicio") LocalDate dataInicio,
            @RequestParam("dataFim") LocalDate dataFim) {

        return this.artigoService.calcularTotalArtigosPorAutorNoPeriodo(dataInicio, dataFim);
    }


}